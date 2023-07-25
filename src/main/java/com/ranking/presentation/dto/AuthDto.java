package com.ranking.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthDto {

	private @JsonProperty("access_token") String token;
	private @JsonProperty("expires_in") Long expiresIn;

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
