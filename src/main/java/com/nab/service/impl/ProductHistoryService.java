package com.nab.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nab.domain.ProductHistory;
import com.nab.dto.ProductHistoryCreationDTO;
import com.nab.dto.ProductHistoryResponseDTO;
import com.nab.helper.CommonUtil;
import com.nab.repository.ProductHistoryRepository;
import com.nab.service.IProductHistoryService;
import com.nab.service.IProductService;

@Service
@Transactional
public class ProductHistoryService implements IProductHistoryService{
	
	private Logger logger = LoggerFactory.getLogger(ProductHistoryService.class);
	
	@Autowired
	private ProductHistoryRepository historyRepository;
	
	@Autowired
	private IProductService productService;

	@Value("${format.datetime.string:MM/dd/yyyy hh:mm:ssZ}")
	private String formatDateStr;

	@Override
	public ProductHistoryResponseDTO save(ProductHistoryCreationDTO creationDTO) {
		logger.debug("Start saving branch {}", creationDTO);
		ProductHistory productHistory = this.convertDomain(creationDTO);
		historyRepository.save(productHistory);
		return this.convertToDTO(productHistory);
	}

	@Override
	public ProductHistoryResponseDTO get(Long id) {
		logger.debug("Start getting product history {}", id);
		ProductHistoryResponseDTO responseDTO = null;
		if (id != null && id >0) {
			Optional<ProductHistory> optional = historyRepository.findById(id);
			if (optional.isPresent()) {
				ProductHistory productHistory = optional.get();
				responseDTO = this.convertToDTO(productHistory);
			}
		}
		return responseDTO;
	}

	@Override
	public List<ProductHistoryResponseDTO> gets(String productCode) {
		logger.debug("Start getting product history {}", productCode);
		return historyRepository.findByProductProductCode(productCode);
	}

	@Override
	public ProductHistory convertDomain(ProductHistoryCreationDTO creationDTO) {
		logger.debug("Start converting branch {}", creationDTO);
		Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
		ProductHistory productHistory = null;
		try {
				productHistory = new ProductHistory();
				productHistory.setProduct(productService.getRoot(creationDTO.getProductCode()));
				productHistory.setDescription(creationDTO.getDescription());
				productHistory.setModifiedBy(creationDTO.getActionBy());
				productHistory.setModifiedDate(currTimestamp);
				productHistory.setCreatedBy(creationDTO.getActionBy());
				productHistory.setCreatedDate(currTimestamp);
		} catch (Exception e) {
			logger.error("Cannot convert to domain: " + e.getMessage(), e);
		}

		return productHistory;
	}

	@Override
	public ProductHistoryResponseDTO convertToDTO(ProductHistory productHistory) {
		logger.debug("Start converting branch {}", productHistory);
		ProductHistoryResponseDTO responseDTO = null;
		try {
			if (productHistory != null) {
				responseDTO = new ProductHistoryResponseDTO();
				responseDTO.setDescription(productHistory.getDescription());
				responseDTO.setProductCode(productHistory.getProduct().getProductCode());
				responseDTO.setModifiedBy(productHistory.getModifiedBy());
				responseDTO.setCreatedBy(productHistory.getCreatedBy());

				String createdDate = CommonUtil.convertTimestampToString(productHistory.getCreatedDate(), formatDateStr);
				String modifiedDate = CommonUtil.convertTimestampToString(productHistory.getModifiedDate(), formatDateStr);

				responseDTO.setCreatedDate(createdDate);
				responseDTO.setModifiedDate(modifiedDate);
				responseDTO.setFormatDateStr(formatDateStr);
			}
		} catch (Exception e) {
			logger.error("Cannot convert to Dto: " + e.getMessage(), e);
		}

		return responseDTO;
	}

	@Override
	public ProductHistory getRoot(Long id) {
		return historyRepository.findById(id).get();
	}

}
