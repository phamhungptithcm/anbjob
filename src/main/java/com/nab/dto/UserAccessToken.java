package com.nab.dto;

public class UserAccessToken {
	private String id;
	private String access_token;
	private long token_refresh_interval_sec;

	public UserAccessToken() {
	}

	public UserAccessToken(String id, String access_token, long token_refresh_interval_sec) {
		this.id = id;
		this.access_token = access_token;
		this.token_refresh_interval_sec = token_refresh_interval_sec;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public long getToken_refresh_interval_sec() {
		return token_refresh_interval_sec;
	}

	public void setToken_refresh_interval_sec(long token_refresh_interval_sec) {
		this.token_refresh_interval_sec = token_refresh_interval_sec;
	}
}
