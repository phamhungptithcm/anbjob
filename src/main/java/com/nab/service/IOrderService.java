package com.nab.service;

import java.util.List;

import com.nab.domain.Order;
import com.nab.dto.OrderCreationDTO;
import com.nab.dto.OrderRequestDTO;
import com.nab.dto.OrderResponseDTO;

public interface IOrderService {
	public OrderResponseDTO save(OrderCreationDTO creationDTO);
	public OrderResponseDTO get(String code);
	public Order getRoot(String code);
	public List<OrderResponseDTO> gets(OrderRequestDTO requestDTO);
	public Order convertDomain(OrderCreationDTO creationDTO);
	public OrderResponseDTO convertToDTO(Order order);
	public boolean checkCodeExist(String code);
}
