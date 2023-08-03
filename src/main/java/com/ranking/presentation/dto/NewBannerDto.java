package com.ranking.presentation.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class NewBannerDto {

	@NotNull(message = "Field hiredClicks is required")
	private Integer hiredClicks;

	@NotEmpty(message = "Field rankingsId is required")
	private List<Long> rankingsId;

	@NotEmpty(message = "Field redirectUrl is required")
	private String redirectUrl;

	public Integer getHiredClicks() {
		return hiredClicks;
	}

	public void setHiredClicks(Integer hiredClicks) {
		this.hiredClicks = hiredClicks;
	}

	public List<Long> getRankingsId() {
		return rankingsId;
	}

	public void setRankingsId(List<Long> rankingsId) {
		this.rankingsId = rankingsId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
