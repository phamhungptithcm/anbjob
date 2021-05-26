package com.nab.service;

import org.springframework.data.domain.PageRequest;

import com.nab.domain.Product;
import com.nab.dto.ProductCreationDTO;
import com.nab.dto.ProductRequestDTO;
import com.nab.dto.ProductResponseDTO;
import com.nab.dto.ResultPagingDTO;

public interface IProductService {
	public boolean delete(String code);
	public ProductResponseDTO save(ProductCreationDTO creationDTO);
	public ProductResponseDTO get(String code);
	public Product getRoot(String code);
	public ResultPagingDTO gets(ProductRequestDTO requestDTO,PageRequest pageRequest);
	public Product convertDomain(ProductCreationDTO creationDTO);
	public ProductResponseDTO convertToDTO(Product product);
	public boolean checkCodeExist(String code);
}
