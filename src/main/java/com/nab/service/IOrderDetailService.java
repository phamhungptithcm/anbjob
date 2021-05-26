package com.nab.service;

import java.util.List;

import com.nab.domain.Order;
import com.nab.domain.OrderDetail;
import com.nab.dto.OrderDetailCreationDTO;
import com.nab.dto.OrderDetailRequestDTO;
import com.nab.dto.OrderDetailResponseDTO;

public interface IOrderDetailService {
	public OrderDetailResponseDTO save(OrderDetailCreationDTO creationDTO, Order order);
	public OrderDetailResponseDTO get(Long id);
	public OrderDetail getRoot(Long id);
	public List<OrderDetailResponseDTO> gets(OrderDetailRequestDTO requestDTO);
	public OrderDetail convertDomain(OrderDetailCreationDTO creationDTO);
	public OrderDetailResponseDTO convertToDTO(OrderDetail orderDetail);
}
