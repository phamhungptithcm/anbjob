package com.nab.service;

import java.util.List;

import com.nab.domain.ProductHistory;
import com.nab.dto.ProductHistoryCreationDTO;
import com.nab.dto.ProductHistoryResponseDTO;

public interface IProductHistoryService {
	public ProductHistoryResponseDTO save(ProductHistoryCreationDTO creationDTO);
	public ProductHistoryResponseDTO get(Long id);
	public ProductHistory getRoot(Long id);
	public List<ProductHistoryResponseDTO> gets(String productCode);
	public ProductHistory convertDomain(ProductHistoryCreationDTO creationDTO);
	public ProductHistoryResponseDTO convertToDTO(ProductHistory productHistory);
}
