package com.nab.service;

import java.util.List;

import com.nab.domain.Category;
import com.nab.dto.CategoryCreationDTO;
import com.nab.dto.CategoryRequestDTO;
import com.nab.dto.CategoryResponseDTO;

public interface ICategoryService {
	public boolean delete(String code);
	public CategoryResponseDTO save(CategoryCreationDTO creationDTO);
	public CategoryResponseDTO get(String code);
	public Category getRoot(String code);
	public List<CategoryResponseDTO> gets(CategoryRequestDTO requestDTO);
	public Category convertDomain(CategoryCreationDTO creationDTO);
	public CategoryResponseDTO convertToDTO(Category category);
	public boolean checkCodeExist(String code);
}
