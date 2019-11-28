package com.msa.social.repository.dto;

public class AuthTokenData {
	private String token;
	private Long userId;

	public AuthTokenData() {
		super();
	}

	public AuthTokenData(String token, Long userId) {
		super();
		this.token = token;
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
