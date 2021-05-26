package com.nab.dto;

import java.util.List;

public class OrderCreationDTO {
	
	private String orderCode;

	private double orderAmount;

	private String orderedBy;

	private String actionBy;
	
	private String orderStatusCode;
	
	private List<OrderDetailCreationDTO> detailCreationDTOs;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	
	public String getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}

	public List<OrderDetailCreationDTO> getDetailCreationDTOs() {
		return detailCreationDTOs;
	}

	public void setDetailCreationDTOs(List<OrderDetailCreationDTO> detailCreationDTOs) {
		this.detailCreationDTOs = detailCreationDTOs;
	}

	
}
