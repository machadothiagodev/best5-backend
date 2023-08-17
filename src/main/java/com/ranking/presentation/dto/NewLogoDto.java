package com.ranking.presentation.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NewLogoDto {

	@NotNull(message = "Field hiredDate is required")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date hiredDate;

	@NotEmpty(message = "Field rankingItemsId is required")
	private List<Long> rankingItemsId;

	@NotEmpty(message = "Field redirectUrl is required")
	private String redirectUrl;

	public Date getHiredDate() {
		return hiredDate;
	}

	public void setHiredDate(Date hiredDate) {
		this.hiredDate = hiredDate;
	}

	public List<Long> getRankingItemsId() {
		return rankingItemsId;
	}

	public void setRankingItemsId(List<Long> rankingItemsId) {
		this.rankingItemsId = rankingItemsId;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
