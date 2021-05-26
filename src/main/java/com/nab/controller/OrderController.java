package com.nab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.dto.OrderCreationDTO;
import com.nab.dto.OrderResponseDTO;
import com.nab.dto.OrderStatusCreationDTO;
import com.nab.dto.OrderStatusResponseDTO;
import com.nab.service.IOrderService;
import com.nab.service.IOrderStatusService;

@RestController
@RequestMapping("${nabjob.rest.unsecurity.path}/orderController")
public class OrderController {
	
	private Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IOrderStatusService orderStatusService;

	@PostMapping("/orderCreation")
	public ResponseEntity<OrderResponseDTO> orderCreation(@RequestBody OrderCreationDTO request) {
		try {
			return ResponseEntity.ok(orderService.save(request));
		} catch (Exception e) {
			logger.error("Cannot request orderCreation  >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/getOrderByCode/{code}")
	public ResponseEntity<OrderResponseDTO> getOrdersByCode(@PathVariable("code") String code) {
		try {
			return ResponseEntity.ok(orderService.get(code));
		} catch (Exception e) {
			logger.error("Cannot request getOrdersByCode>>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PostMapping("/orderStatusCreation")
	public ResponseEntity<OrderStatusResponseDTO> orderStatusCreation(@RequestBody OrderStatusCreationDTO request) {
		try {
			return ResponseEntity.ok(orderStatusService.save(request));
		} catch (Exception e) {
			logger.error("Cannot request orderStatusCreation  >>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/getOrderStatusByCode/{code}")
	public ResponseEntity<OrderStatusResponseDTO> getOrderStatusByCode(@PathVariable("code") String code) {
		try {
			return ResponseEntity.ok(orderStatusService.get(code));
		} catch (Exception e) {
			logger.error("Cannot request getOrderStatusByCode>>> " + e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
}
