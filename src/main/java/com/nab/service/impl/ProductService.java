package com.nab.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nab.domain.Category;
import com.nab.domain.Product;
import com.nab.dto.BranchResponseDTO;
import com.nab.dto.CategoryResponseDTO;
import com.nab.dto.ProductCreationDTO;
import com.nab.dto.ProductHistoryCreationDTO;
import com.nab.dto.ProductRequestDTO;
import com.nab.dto.ProductResponseDTO;
import com.nab.dto.ResultPagingDTO;
import com.nab.dto.SearchCriteria;
import com.nab.helper.CommonUtil;
import com.nab.helper.GenericSpesification;
import com.nab.helper.SearchOperation;
import com.nab.repository.ProductRepository;
import com.nab.service.IBranchService;
import com.nab.service.ICategoryService;
import com.nab.service.IProductService;

@Service
@Transactional
public class ProductService implements IProductService {

	private Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private IBranchService branchService;

	@Autowired
	private ICategoryService categoryService;

	@Value("${format.datetime.string:MM/dd/yyyy hh:mm:ssZ}")
	private String formatDateStr;

	@Value("${description.template.product.changed:}")
	private String productChangedDesc;

	private static final boolean IS_LETER = true;
	private static final boolean IS_NUMBER = true;
	private static final int CODE_LENGTH = 8;
	private static final String FULLTEXT_SEARCH = "fulltextSearch";
	private static final String CATEGORIES = "categories";
	private static final String ACTIVE = "active";
	private static final String BRANCH = "branch";
	private static final String CREATED_DATE = "createdDate";

	@Override
	public boolean delete(String code) {
		logger.debug("Start deleteing product {}", code);
		boolean result = false;
		try {
			Optional<Product> optional = productRepository.findByProductCode(code);
			if (optional.isPresent()) {
				Product product = optional.get();
				product.setActive(false);
				productRepository.save(product);
				result = true;
				logger.debug("Product {} is deleted", code);
			} else {
				logger.debug("Cannot find product {} to delete", code);
			}

		} catch (Exception e) {
			logger.error("Cannot delete product: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public ProductResponseDTO save(ProductCreationDTO creationDTO) {
		logger.debug("Start saving product {}", creationDTO);
		if (StringUtils.isNotBlank(creationDTO.getProductCode())) {
			Product product = this.getRoot(creationDTO.getProductCode());
			// Generate history
			ProductHistoryCreationDTO history = new ProductHistoryCreationDTO();
			history.setActionBy(creationDTO.getActionBy());
			Map<String, String> keyValue = new HashMap<String, String>();
			keyValue.put("oldPrice", String.valueOf(product.getProductPrice()));
			keyValue.put("newPrice", String.valueOf(product.getProductPrice()));
			keyValue.put("productCode", product.getProductCode());
			history.setDescription(CommonUtil.replaceStrByName(productChangedDesc, keyValue));
			history.setProductCode(creationDTO.getProductCode());
		}
		Product product = this.convertDomain(creationDTO);
		productRepository.save(product);

		return this.convertToDTO(product);
	}

	@Override
	public ProductResponseDTO get(String code) {
		logger.debug("Start getting product {}", code);
		ProductResponseDTO responseDTO = null;
		if (StringUtils.isNotBlank(code)) {
			Optional<Product> optional = productRepository.findByProductCode(code);
			if (optional.isPresent()) {
				Product product = optional.get();
				responseDTO = this.convertToDTO(product);
			}
		}
		return responseDTO;
	}

	@Override
	public ResultPagingDTO gets(ProductRequestDTO requestDTO, PageRequest pageRequest) {
		logger.debug("Start getting products {}", requestDTO);
		ResultPagingDTO result = null;
		try {
			Page page = processSearchProductByCondititon(requestDTO,pageRequest);
			
			List<Product> products = page.getContent();
			if (!products.isEmpty()) {
				result = new ResultPagingDTO();
				result.setTotalItem(page.getTotalElements());
				result.setTotalItem(page.getTotalPages());
				List<ProductResponseDTO> responseDTOs = new ArrayList<ProductResponseDTO>();
				for (Product product : products) {
					responseDTOs.add(this.convertToDTO(product));
				}
			}
		} catch (Exception e) {
			logger.error("Cannot search product: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public Product convertDomain(ProductCreationDTO creationDTO) {
		logger.debug("Start converting product {}", creationDTO);
		Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
		Product product = null;
		try {
			if (StringUtils.isNotBlank(creationDTO.getProductCode())) {
				Optional<Product> optional = productRepository.findByProductCode(creationDTO.getProductCode());
				if (optional.isPresent()) {
					product = optional.get();
					product.setProductName(creationDTO.getProductCode());
					product.setModifiedBy(creationDTO.getActionBy());
					product.setModifiedDate(currTimestamp);
				}
			} else {
				product = new Product();
				String productCode = null;
				do {
					productCode = CommonUtil.randomString(CODE_LENGTH, IS_LETER, IS_NUMBER);
				} while (checkCodeExist(productCode));
				List<Category> categories = new ArrayList<Category>();
				for (String cateCode : creationDTO.getCategoryCode()) {
					categories.add(categoryService.getRoot(cateCode));
				}
				product.setCategories(categories);
				product.setBranch(branchService.getRoot(creationDTO.getBranchCode()));
				product.setProductCode(productCode);
				product.setProductName(creationDTO.getProductName());
				product.setProductColor(creationDTO.getProductColor());
				product.setProductPrice(creationDTO.getProductPrice());
				product.setModifiedBy(creationDTO.getActionBy());
				product.setModifiedDate(currTimestamp);
				product.setCreatedBy(creationDTO.getActionBy());
				product.setCreatedDate(currTimestamp);
				product.setActive(true);
			}
		} catch (Exception e) {
			logger.error("Cannot convert to domain: " + e.getMessage(), e);
		}

		return product;
	}

	@Override
	public ProductResponseDTO convertToDTO(Product product) {
		logger.debug("Start converting product {}", product);
		ProductResponseDTO responseDTO = null;
		try {
			if (product != null) {
				responseDTO = new ProductResponseDTO();
				responseDTO.setProductCode(product.getProductCode());
				responseDTO.setProductColor(product.getProductColor());
				responseDTO.setProductPrice(product.getProductPrice());
				responseDTO.setProductName(product.getProductName());
				responseDTO.setModifiedBy(product.getModifiedBy());
				responseDTO.setCreatedBy(product.getCreatedBy());
				String createdDate = CommonUtil.convertTimestampToString(product.getCreatedDate(), formatDateStr);
				String modifiedDate = CommonUtil.convertTimestampToString(product.getModifiedDate(), formatDateStr);

				responseDTO.setCreatedDate(createdDate);
				responseDTO.setModifiedDate(modifiedDate);
				responseDTO.setFormatDateStr(formatDateStr);
				List<CategoryResponseDTO> cateResp = new ArrayList<CategoryResponseDTO>();
				for (Category category : product.getCategories()) {
					cateResp.add(categoryService.convertToDTO(category));
				}
				BranchResponseDTO branchResp = branchService.convertToDTO(product.getBranch());
				responseDTO.setBranchResponseDTO(branchResp);
				responseDTO.setCategoryResponseDTOs(cateResp);
			}
		} catch (Exception e) {
			logger.error("Cannot convert to Dto: " + e.getMessage(), e);
		}
		return responseDTO;
	}

	@Override
	public boolean checkCodeExist(String code) {
		logger.debug("Start checking product code {}", code);
		Optional<Product> optional = productRepository.findByProductCode(code);
		return optional.isPresent() ? true : false;
	}

	@Override
	public Product getRoot(String code) {
		return productRepository.findByProductCode(code).get();
	}

	private Page processSearchProductByCondititon(ProductRequestDTO condition, PageRequest pageRequest)
			throws Exception {
		GenericSpesification genericSpesification = new GenericSpesification<Product>();
		if (condition != null) {

			if (StringUtils.isNotBlank(condition.getFulltextSearch())) {
				genericSpesification
						.add(new SearchCriteria(FULLTEXT_SEARCH, condition.getFulltextSearch(), SearchOperation.MATCH));
			}
			if (StringUtils.isNotBlank(condition.getCreatedDateFrom())
					&& StringUtils.isNotBlank(condition.getCreatedDateTo())) {
				LocalDateTime createdDateFrom = CommonUtil
						.convertStrToLocalDate(condition.getCreatedDateFrom(), condition.getFormatDateStr())
						.atTime(0, 0, 0);
				LocalDateTime createdDateTo = CommonUtil
						.convertStrToLocalDate(condition.getCreatedDateTo(), condition.getFormatDateStr())
						.atTime(23, 59, 59);
				genericSpesification
						.add(new SearchCriteria(CREATED_DATE, createdDateFrom, SearchOperation.GREATER_THAN_EQUAL));
				genericSpesification
						.add(new SearchCriteria(CREATED_DATE, createdDateTo, SearchOperation.LESS_THAN_EQUAL));
			}
			if (condition.getActive() != null) {
				genericSpesification.add(new SearchCriteria(ACTIVE, condition.getActive(), SearchOperation.EQUAL));
			}
			if (StringUtils.isNotBlank(condition.getOrderFieldName())
					&& StringUtils.isNotBlank(condition.getOrderType())) {
				if ("desc".equals(condition.getOrderType().toLowerCase())) {
					genericSpesification
							.add(new SearchCriteria(null, condition.getOrderFieldName(), SearchOperation.DESC));
				} else {
					genericSpesification
							.add(new SearchCriteria(null, condition.getOrderFieldName(), SearchOperation.ASC));
				}
			}

		}
		return productRepository.findAll(genericSpesification, pageRequest);

	}
}
