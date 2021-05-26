package com.nab.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nab.domain.Order;
import com.nab.domain.OrderDetail;
import com.nab.domain.Product;
import com.nab.dto.OrderDetailCreationDTO;
import com.nab.dto.OrderDetailRequestDTO;
import com.nab.dto.OrderDetailResponseDTO;
import com.nab.helper.CommonUtil;
import com.nab.repository.OrderDetailRepository;
import com.nab.service.IOrderDetailService;
import com.nab.service.IProductService;

@Service
@Transactional
public class OrderDetailService implements IOrderDetailService {

	private Logger logger = LoggerFactory.getLogger(OrderDetailService.class);

	@Autowired
	private OrderDetailRepository detailRepository;
	
	@Autowired
	private IProductService productService;

	@Value("${format.datetime.string:MM/dd/yyyy hh:mm:ssZ}")
	private String formatDateStr;

	@Override
	public OrderDetailResponseDTO save(OrderDetailCreationDTO creationDTO, Order order) {
		logger.debug("Start saving order detail {}", creationDTO);
		OrderDetail orderDetail = this.convertDomain(creationDTO);
		orderDetail.setOrder(order);
		detailRepository.save(orderDetail);
		return this.convertToDTO(orderDetail);
	}

	@Override
	public OrderDetailResponseDTO get(Long id) {
		logger.debug("Start getting order detail {}", id);
		OrderDetailResponseDTO responseDTO = null;
		if (id != null && id > 0) {
			Optional<OrderDetail> optional = detailRepository.findById(id);
			if (optional.isPresent()) {
				OrderDetail orderDetail = optional.get();
				responseDTO = this.convertToDTO(orderDetail);
			}
		}
		return responseDTO;
	}

	@Override
	public List<OrderDetailResponseDTO> gets(OrderDetailRequestDTO requestDTO) {
		logger.debug("Start getting order details {}", requestDTO);
		return null;
	}

	@Override
	public OrderDetail convertDomain(OrderDetailCreationDTO creationDTO) {
		logger.debug("Start converting order detail {}", creationDTO);
		Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
		OrderDetail orderDetail = null;
		try {
			orderDetail = new OrderDetail();
			orderDetail.setOrderDetailId(creationDTO.getOrderDetailId());
			Product product = productService.getRoot(creationDTO.getProductCode());
			orderDetail.setPrice(product.getProductPrice());
			orderDetail.setQuantity(creationDTO.getQuantity());
			orderDetail.setProduct(product);
			orderDetail.setModifiedBy(creationDTO.getActionBy());
			orderDetail.setModifiedDate(currTimestamp);
			orderDetail.setCreatedBy(creationDTO.getActionBy());
			orderDetail.setCreatedDate(currTimestamp);
		} catch (Exception e) {
			logger.error("Cannot convert to domain: " + e.getMessage(), e);
		}

		return orderDetail;
	}

	@Override
	public OrderDetailResponseDTO convertToDTO(OrderDetail orderDetail) {
		logger.debug("Start converting order detail {}", orderDetail);
		OrderDetailResponseDTO responseDTO = null;
		try {
			if (orderDetail != null) {
				responseDTO = new OrderDetailResponseDTO();
				responseDTO.setOrderDetailId(orderDetail.getOrderDetailId());
				responseDTO.setPrice(orderDetail.getPrice());
				responseDTO.setQuantity(orderDetail.getQuantity());
				responseDTO.setModifiedBy(orderDetail.getModifiedBy());
				responseDTO.setCreatedBy(orderDetail.getCreatedBy());
				responseDTO.setProductResp(productService.convertToDTO(orderDetail.getProduct()));
				String createdDate = CommonUtil.convertTimestampToString(orderDetail.getCreatedDate(), formatDateStr);
				String modifiedDate = CommonUtil.convertTimestampToString(orderDetail.getModifiedDate(), formatDateStr);
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
	public OrderDetail getRoot(Long id) {
		return detailRepository.findById(id).get();
	}

}
