package com.ranking.presentation.dto;

import javax.validation.constraints.NotEmpty;

public class RankingItemDto {

	private Long id;

	@NotEmpty(message = "Field name is required")
	private String name;

	private Integer votes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

}
