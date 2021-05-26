package com.nab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nab.dto.ProductCreationDTO;
import com.nab.dto.ProductHistoryResponseDTO;
import com.nab.dto.ProductRequestDTO;
import com.nab.dto.ProductResponseDTO;
import com.nab.dto.ResultPagingDTO;
import com.nab.service.IProductHistoryService;
import com.nab.service.IProductService;

@RestController
@RequestMapping("${nabjob.rest.unsecurity.path}/productController")
public class ProductController {

	private Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductHistoryService historyService;

	@PostMapping("/productCreation")
	public ResponseEntity<ProductResponseDTO> productCreation(@RequestBody ProductCreationDTO request) {
		try {
			return ResponseEntity.ok(productService.save(request));
		} catch (Exception e) {
			logger.error("Cannot request productCreation  >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/getProductsByCondition")
	public ResponseEntity<ResultPagingDTO> getProductsByCondition(@RequestBody ProductRequestDTO request,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
		try {
			PageRequest paging = PageRequest.of(pageNo, pageSize);
			return ResponseEntity.ok(productService.gets(request,paging));
		} catch (Exception e) {
			logger.error("Cannot request productCreation  >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/getProductByCode/{code}")
	public ResponseEntity<ProductResponseDTO> getProductByCode(@PathVariable("code") String code) {
		try {
			return ResponseEntity.ok(productService.get(code));
		} catch (Exception e) {
			logger.error("Cannot request getProductByCode >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/getProductHistoryByProductCode/{code}")
	public ResponseEntity<List<ProductHistoryResponseDTO>> getProductHistoryByProductCode(
			@PathVariable("code") String code) {
		try {
			return ResponseEntity.ok(historyService.gets(code));
		} catch (Exception e) {
			logger.error("Cannot request getProductByCode >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
