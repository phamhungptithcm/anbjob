package com.nab.dto;

import java.util.List;

public class ProductResponseDTO {

	private String productCode;

	private String productName;

	private double productPrice;

	private String productColor;

	private String createdBy;

	private String modifiedBy;

	private String createdDate;

	private String modifiedDate;
	
	private String formatDateStr;
	
	private List<CategoryResponseDTO> categoryResponseDTOs;
	
	private BranchResponseDTO branchResponseDTO;

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

	public List<CategoryResponseDTO> getCategoryResponseDTOs() {
		return categoryResponseDTOs;
	}

	public void setCategoryResponseDTOs(List<CategoryResponseDTO> categoryResponseDTOs) {
		this.categoryResponseDTOs = categoryResponseDTOs;
	}

	public BranchResponseDTO getBranchResponseDTO() {
		return branchResponseDTO;
	}

	public void setBranchResponseDTO(BranchResponseDTO branchResponseDTO) {
		this.branchResponseDTO = branchResponseDTO;
	}

	public String getFormatDateStr() {
		return formatDateStr;
	}

	public void setFormatDateStr(String formatDateStr) {
		this.formatDateStr = formatDateStr;
	}
	
}
