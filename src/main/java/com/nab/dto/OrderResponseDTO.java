package com.nab.dto;

import java.util.List;

public class OrderResponseDTO {
	
	private String orderCode;
	private double orderAmount;
	private OrderStatusResponseDTO statusResp;
	private List<OrderDetailResponseDTO> detailResps;
	private String formatDateStr;
	private String createdBy;
	private String modifiedBy;
	private String createdDate;
	private String modifiedDate;
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
	public OrderStatusResponseDTO getStatusResp() {
		return statusResp;
	}
	public void setStatusResp(OrderStatusResponseDTO statusResp) {
		this.statusResp = statusResp;
	}
	public List<OrderDetailResponseDTO> getDetailResps() {
		return detailResps;
	}
	public void setDetailResps(List<OrderDetailResponseDTO> detailResps) {
		this.detailResps = detailResps;
	}
	public String getFormatDateStr() {
		return formatDateStr;
	}
	public void setFormatDateStr(String formatDateStr) {
		this.formatDateStr = formatDateStr;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	

}
