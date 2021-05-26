package com.nab.dto;

import java.util.List;

public class ProductRequestDTO {
	
	private String createdDateFrom;
	private String createdDateTo;
	private String formatDateStr;
	private List<String> categoryCodes;
	private String branchCode;
	private String fulltextSearch;
	private Boolean active;
	private String orderFieldName;
	private String orderType;
	public String getCreatedDateFrom() {
		return createdDateFrom;
	}
	public void setCreatedDateFrom(String createdDateFrom) {
		this.createdDateFrom = createdDateFrom;
	}
	public String getCreatedDateTo() {
		return createdDateTo;
	}
	public void setCreatedDateTo(String createdDateTo) {
		this.createdDateTo = createdDateTo;
	}
	public String getFormatDateStr() {
		return formatDateStr;
	}
	public void setFormatDateStr(String formatDateStr) {
		this.formatDateStr = formatDateStr;
	}
	public List<String> getCategoryCodes() {
		return categoryCodes;
	}
	public void setCategoryCodes(List<String> categoryCodes) {
		this.categoryCodes = categoryCodes;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getFulltextSearch() {
		return fulltextSearch;
	}
	public void setFulltextSearch(String fulltextSearch) {
		this.fulltextSearch = fulltextSearch;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getOrderFieldName() {
		return orderFieldName;
	}
	public void setOrderFieldName(String orderFieldName) {
		this.orderFieldName = orderFieldName;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
