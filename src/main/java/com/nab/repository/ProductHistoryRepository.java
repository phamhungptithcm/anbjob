package com.nab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nab.domain.ProductHistory;
import com.nab.dto.ProductHistoryResponseDTO;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long>{
	public List<ProductHistoryResponseDTO> findByProductProductCode(String productCode);

}
