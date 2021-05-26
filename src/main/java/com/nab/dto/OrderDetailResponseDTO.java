package com.nab.dto;

public class OrderDetailResponseDTO {
	
	private Long orderDetailId;
	
	private int quantity;

	private double price;

	private String createdBy;

	private String modifiedBy;

	private String createdDate;

	private String modifiedDate;
	
	private String formatDateStr;
	
	private ProductResponseDTO productResp;

	public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getFormatDateStr() {
		return formatDateStr;
	}

	public void setFormatDateStr(String formatDateStr) {
		this.formatDateStr = formatDateStr;
	}

	public ProductResponseDTO getProductResp() {
		return productResp;
	}

	public void setProductResp(ProductResponseDTO productResp) {
		this.productResp = productResp;
	}
}
