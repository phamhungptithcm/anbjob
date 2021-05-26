package com.nab.dto;

public class BranchRequestDTO {
	
	private String branchCode;
	private String branchName;
	private String createdBy;
	private String fromDate;
	private String toDate;
	private String formatDateStr;
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFormatDateStr() {
		return formatDateStr;
	}

	public void setFormatDateStr(String formatDateStr) {
		this.formatDateStr = formatDateStr;
	}
	
	

}
