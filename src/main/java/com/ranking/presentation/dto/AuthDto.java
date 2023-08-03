package com.ranking.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthDto {

	@JsonProperty("access_token")
	private String token;

	@JsonProperty("expires_in")
	private Long expiresIn;

	public AuthDto(String token, Long expiresIn) {
		this.token = token;
		this.expiresIn = expiresIn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

}
