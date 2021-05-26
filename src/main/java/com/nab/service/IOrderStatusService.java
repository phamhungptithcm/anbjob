package com.nab.service;

import java.util.List;

import com.nab.domain.OrderStatus;
import com.nab.dto.OrderStatusCreationDTO;
import com.nab.dto.OrderStatusRequestDTO;
import com.nab.dto.OrderStatusResponseDTO;

public interface IOrderStatusService {
	public boolean delete(String code);
	public OrderStatusResponseDTO save(OrderStatusCreationDTO creationDTO);
	public OrderStatusResponseDTO get(String code);
	public OrderStatus getRoot(String code);
	public List<OrderStatusResponseDTO> gets(OrderStatusRequestDTO requestDTO);
	public OrderStatus convertDomain(OrderStatusCreationDTO creationDTO);
	public OrderStatusResponseDTO convertToDTO(OrderStatus orderStatus);
	public boolean checkCodeExist(String code);
}
