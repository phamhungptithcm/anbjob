package com.nab.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nab.domain.Category;
import com.nab.dto.CategoryCreationDTO;
import com.nab.dto.CategoryRequestDTO;
import com.nab.dto.CategoryResponseDTO;
import com.nab.helper.CommonUtil;
import com.nab.repository.CategoryRepository;
import com.nab.service.ICategoryService;

@Service
@Transactional
public class CategoryService implements ICategoryService{
	
	private Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Value("${format.datetime.string:MM/dd/yyyy hh:mm:ssZ}")
	private String formatDateStr;

	private static final boolean IS_LETER = true;
	private static final boolean IS_NUMBER = false;
	private static final int CODE_LENGTH = 5;

	@Override
	public boolean delete(String code) {
		logger.debug("Start deleteing category {}", code);
		boolean result = false;
		try {
			Optional<Category> optional = categoryRepository.findByCategoryCode(code);
			if (optional.isPresent()) {
				Category category = optional.get();
				category.setActive(false);
				categoryRepository.save(category);
				result = true;
				logger.debug("Category {} is deleted", code);
			} else {
				logger.debug("Cannot find category {} to delete", code);
			}

		} catch (Exception e) {
			logger.error("Cannot delete category: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public CategoryResponseDTO save(CategoryCreationDTO creationDTO) {
		logger.debug("Start saving category {}", creationDTO);
		Category category = this.convertDomain(creationDTO);
		categoryRepository.save(category);
		return this.convertToDTO(category);
	}

	@Override
	public CategoryResponseDTO get(String code) {
		logger.debug("Start getting category {}", code);
		CategoryResponseDTO responseDTO = null;
		if (StringUtils.isNotBlank(code)) {
			Optional<Category> optional = categoryRepository.findByCategoryCode(code);
			if (optional.isPresent()) {
				Category category = optional.get();
				responseDTO = this.convertToDTO(category);
			}
		}
		return responseDTO;
	}

	@Override
	public List<CategoryResponseDTO> gets(CategoryRequestDTO requestDTO) {
		logger.debug("Start getting categories {}", requestDTO);
		return null;
	}

	@Override
	public Category convertDomain(CategoryCreationDTO creationDTO) {
		logger.debug("Start converting category {}", creationDTO);
		Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
		Category category = null;
		try {
			if (StringUtils.isNotBlank(creationDTO.getCategoryCode())) {
				Optional<Category> optional = categoryRepository.findByCategoryCode(creationDTO.getCategoryCode());
				if (optional.isPresent()) {
					category = optional.get();
					category.setCategoryName(creationDTO.getCategoryName());
					category.setModifiedBy(creationDTO.getActionBy());
					category.setModifiedDate(currTimestamp);
				}
			} else {
				category = new Category();
				String categoryCode = null;
				do {
					categoryCode = CommonUtil.randomString(CODE_LENGTH, IS_LETER, IS_NUMBER);
				} while (checkCodeExist(categoryCode));
				category.setCategoryCode(categoryCode);
				category.setCategoryName(creationDTO.getCategoryName());
				category.setModifiedBy(creationDTO.getActionBy());
				category.setModifiedDate(currTimestamp);
				category.setCreatedBy(creationDTO.getActionBy());
				category.setCreatedDate(currTimestamp);
				category.setActive(true);
			}
		} catch (Exception e) {
			logger.error("Cannot convert to domain: " + e.getMessage(), e);
		}

		return category;
	}

	@Override
	public CategoryResponseDTO convertToDTO(Category category) {
		logger.debug("Start converting branch {}", category);
		CategoryResponseDTO responseDTO = null;
		try {
			if (category != null) {
				responseDTO = new CategoryResponseDTO();
				responseDTO.setCategoryCode(category.getCategoryCode());
				responseDTO.setCategoryName(category.getCategoryName());
				responseDTO.setModifiedBy(category.getModifiedBy());
				responseDTO.setCreatedBy(category.getCreatedBy());

				String createdDate = CommonUtil.convertTimestampToString(category.getCreatedDate(), formatDateStr);
				String modifiedDate = CommonUtil.convertTimestampToString(category.getModifiedDate(), formatDateStr);

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
	public boolean checkCodeExist(String code) {
		logger.debug("Start checking category code {}", code);
		Optional<Category> optional = categoryRepository.findByCategoryCode(code);
		return optional.isPresent() ? true : false;
	}

	@Override
	public Category getRoot(String code) {
		return categoryRepository.findByCategoryCode(code).get();
	}

}
