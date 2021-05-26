package com.nab.dto;

import java.util.List;

public class ProductCreationDTO {

	private String productCode;

	private String productName;

	private double productPrice;

	private String productColor;
	
	private String branchCode;
	
	private List<String> categoryCode;
	
	private String actionBy;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public List<String> getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(List<String> categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}
}
