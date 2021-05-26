package com.nab.dto;

public class ResultPagingDTO {
	private Object resp;
	private int totalPage;
	private long totalItem;
	public Object getResp() {
		return resp;
	}
	public void setResp(Object resp) {
		this.resp = resp;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public long getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(long totalItem) {
		this.totalItem = totalItem;
	}
	
	

}
