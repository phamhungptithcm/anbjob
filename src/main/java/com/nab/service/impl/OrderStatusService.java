package com.nab.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nab.domain.OrderStatus;
import com.nab.dto.OrderStatusCreationDTO;
import com.nab.dto.OrderStatusRequestDTO;
import com.nab.dto.OrderStatusResponseDTO;
import com.nab.helper.CommonUtil;
import com.nab.repository.OrderStatusRepository;
import com.nab.service.IOrderStatusService;

@Service
@Transactional
public class OrderStatusService implements IOrderStatusService{
	
	private Logger logger = LoggerFactory.getLogger(BranchService.class);

	@Autowired
	private OrderStatusRepository statusRepository;

	@Value("${format.datetime.string:MM/dd/yyyy hh:mm:ssZ}")
	private String formatDateStr;

	private static final boolean IS_LETER = true;
	private static final boolean IS_NUMBER = false;
	private static final int CODE_LENGTH = 5;

	@Override
	public boolean delete(String code) {
		logger.debug("Start deleteing order status {}", code);
		boolean result = false;
		try {
			Optional<OrderStatus> optional = statusRepository.findByOrderStatusCode(code);
			if (optional.isPresent()) {
				OrderStatus orderStatus = optional.get();
				orderStatus.setActive(false);
				statusRepository.save(orderStatus);
				result = true;
				logger.debug("Branch {} is deleted", code);
			} else {
				logger.debug("Cannot find branch {} to delete", code);
			}

		} catch (Exception e) {
			logger.error("Cannot delete branch: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public OrderStatusResponseDTO save(OrderStatusCreationDTO creationDTO) {
		logger.debug("Start saving order status {}", creationDTO);
		OrderStatus orderStatus = this.convertDomain(creationDTO);
		statusRepository.save(orderStatus);
		return this.convertToDTO(orderStatus);
	}

	@Override
	public OrderStatusResponseDTO get(String code) {
		logger.debug("Start getting order status {}", code);
		OrderStatusResponseDTO responseDTO = null;
		if (StringUtils.isNotBlank(code)) {
			Optional<OrderStatus> optional = statusRepository.findByOrderStatusCode(code);
			if (optional.isPresent()) {
				OrderStatus orderStatus = optional.get();
				responseDTO = this.convertToDTO(orderStatus);
			}
		}
		return responseDTO;
	}

	@Override
	public OrderStatus getRoot(String code) {
		logger.debug("Start getting order status {}", code);
		return statusRepository.findByOrderStatusCode(code).get();
	}

	@Override
	public List<OrderStatusResponseDTO> gets(OrderStatusRequestDTO requestDTO) {
		logger.debug("Start getting order statuses {}", requestDTO);
		return null;
	}

	@Override
	public OrderStatus convertDomain(OrderStatusCreationDTO creationDTO) {
		logger.debug("Start converting branch {}", creationDTO);
		Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
		OrderStatus orderStatus = null;
		try {
			if (StringUtils.isNotBlank(creationDTO.getOrderStatusCode())) {
				Optional<OrderStatus> optional = statusRepository.findByOrderStatusCode(creationDTO.getOrderStatusCode());
				if (optional.isPresent()) {
					orderStatus = optional.get();
					orderStatus.setOrderStatusName(creationDTO.getOrderStatusName());
					orderStatus.setModifiedBy(creationDTO.getActionBy());
					orderStatus.setModifiedDate(currTimestamp);
				}
			} else {
				orderStatus = new OrderStatus();
				String orderStatusCode = null;
				do {
					orderStatusCode = CommonUtil.randomString(CODE_LENGTH, IS_LETER, IS_NUMBER);
				} while (checkCodeExist(orderStatusCode));
				orderStatus.setOrderStatusCode(orderStatusCode);
				orderStatus.setOrderStatusName(creationDTO.getOrderStatusName());
				orderStatus.setModifiedBy(creationDTO.getActionBy());
				orderStatus.setModifiedDate(currTimestamp);
				orderStatus.setCreatedBy(creationDTO.getActionBy());
				orderStatus.setCreatedDate(currTimestamp);
				orderStatus.setActive(true);
			}
		} catch (Exception e) {
			logger.error("Cannot convert to domain: " + e.getMessage(), e);
		}

		return orderStatus;
	}

	@Override
	public OrderStatusResponseDTO convertToDTO(OrderStatus orderStatus) {
		logger.debug("Start converting order status {}", orderStatus);
		OrderStatusResponseDTO responseDTO = null;
		try {
			if (orderStatus != null) {
				responseDTO = new OrderStatusResponseDTO();
				responseDTO.setOrderStatusCode(orderStatus.getOrderStatusCode());
				responseDTO.setOrderStatusName(orderStatus.getOrderStatusName());
				responseDTO.setModifiedBy(orderStatus.getModifiedBy());
				responseDTO.setCreatedBy(orderStatus.getCreatedBy());

				String createdDate = CommonUtil.convertTimestampToString(orderStatus.getCreatedDate(), formatDateStr);
				String modifiedDate = CommonUtil.convertTimestampToString(orderStatus.getModifiedDate(), formatDateStr);

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
	public boolean checkCodeExist(String code) {
		logger.debug("Start checking order status code {}", code);
		Optional<OrderStatus> optional = statusRepository.findByOrderStatusCode(code);
		return optional.isPresent() ? true : false;
	}

}
