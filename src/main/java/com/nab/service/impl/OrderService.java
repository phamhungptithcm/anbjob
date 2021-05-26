package com.nab.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nab.domain.Order;
import com.nab.domain.OrderDetail;
import com.nab.dto.OrderCreationDTO;
import com.nab.dto.OrderDetailCreationDTO;
import com.nab.dto.OrderDetailResponseDTO;
import com.nab.dto.OrderRequestDTO;
import com.nab.dto.OrderResponseDTO;
import com.nab.helper.CommonUtil;
import com.nab.repository.OrderRepository;
import com.nab.service.IOrderDetailService;
import com.nab.service.IOrderService;
import com.nab.service.IOrderStatusService;

@Service
@Transactional
public class OrderService implements IOrderService{
	
	private Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private IOrderStatusService statusService;
	
	@Autowired
	private IOrderDetailService detailService;
	
	@Value("${format.datetime.string:MM/dd/yyyy hh:mm:ssZ}")
	private String formatDateStr;

	private static final boolean IS_LETER = true;
	private static final boolean IS_NUMBER = false;
	private static final int CODE_LENGTH = 8;

	@Override
	public OrderResponseDTO save(OrderCreationDTO creationDTO) {
		logger.debug("Start saving order {}", creationDTO);
		Order order = this.convertDomain(creationDTO);
		orderRepository.save(order);
		for (OrderDetailCreationDTO orderDetailCreationDTO : creationDTO.getDetailCreationDTOs()) {
			detailService.save(orderDetailCreationDTO,order);
		}
		return this.convertToDTO(order);
	}

	@Override
	public OrderResponseDTO get(String code) {
		logger.debug("Start getting order {}", code);
		OrderResponseDTO responseDTO = null;
		if (StringUtils.isNotBlank(code)) {
			Optional<Order> optional = orderRepository.findByOrderCode(code);
			if (optional.isPresent()) {
				Order order = optional.get();
				responseDTO = this.convertToDTO(order);
			}
		}
		return responseDTO;
	}

	@Override
	public List<OrderResponseDTO> gets(OrderRequestDTO requestDTO) {
		logger.debug("Start getting orders {}", requestDTO);
		return null;
	}

	@Override
	public Order convertDomain(OrderCreationDTO creationDTO) {
		logger.debug("Start converting branch {}", creationDTO);
		Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
		Order order = null;
		try {
			if (StringUtils.isNotBlank(creationDTO.getOrderCode())) {
				Optional<Order> optional = orderRepository.findByOrderCode(creationDTO.getOrderCode());
				if (optional.isPresent()) {
					order = optional.get();
					order.setOrderStatus(statusService.getRoot(creationDTO.getOrderStatusCode()));
					order.setModifiedBy(creationDTO.getActionBy());
					order.setModifiedDate(currTimestamp);
				}
			} else {
				order = new Order();
				String orderCode = null;
				do {
					orderCode = CommonUtil.randomString(CODE_LENGTH, IS_LETER, IS_NUMBER);
				} while (checkCodeExist(orderCode));
				order.setOrderCode(orderCode);
				order.setOrderAmount(creationDTO.getOrderAmount());
				order.setOrderStatus(statusService.getRoot(creationDTO.getOrderStatusCode()));
				order.setModifiedBy(creationDTO.getActionBy());
				order.setModifiedDate(currTimestamp);
				order.setCreatedBy(creationDTO.getActionBy());
				order.setCreatedDate(currTimestamp);
				
			}
		} catch (Exception e) {
			logger.error("Cannot convert to domain: " + e.getMessage(), e);
		}

		return order;
	}

	@Override
	public OrderResponseDTO convertToDTO(Order order) {
		logger.debug("Start converting order {}", order);
		OrderResponseDTO responseDTO = null;
		try {
			if (order != null) {
				responseDTO = new OrderResponseDTO();
				responseDTO.setOrderCode(order.getOrderCode());
				responseDTO.setOrderAmount(order.getOrderAmount());
				responseDTO.setStatusResp(statusService.convertToDTO(order.getOrderStatus()));
				List<OrderDetailResponseDTO> detailResps = new ArrayList<OrderDetailResponseDTO>();
				for (OrderDetail orderDetail : order.getOrderDetails()) {
					detailResps.add(detailService.convertToDTO(orderDetail));
				}
				responseDTO.setDetailResps(detailResps);
				responseDTO.setModifiedBy(order.getModifiedBy());
				responseDTO.setCreatedBy(order.getCreatedBy());

				String createdDate = CommonUtil.convertTimestampToString(order.getCreatedDate(), formatDateStr);
				String modifiedDate = CommonUtil.convertTimestampToString(order.getModifiedDate(), formatDateStr);

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
	public Order getRoot(String code) {
		return orderRepository.findByOrderCode(code).get();
	}

	@Override
	public boolean checkCodeExist(String code) {
		logger.debug("Start checking order code {}", code);
		Optional<Order> optional = orderRepository.findByOrderCode(code);
		return optional.isPresent() ? true : false;
	}

}
