package com.ranking.presentation.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RankingDto {

	private Long id;

	@NotEmpty(message = "Field name is required")
	private String name;

	private Integer totalVotes;

	@NotEmpty(message = "Field items is required")
	@Size(min = 3, message = "Field items must have at least 3 elements")
	private List<RankingItemDto> items;

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

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

	public List<RankingItemDto> getItems() {
		return items;
	}

	public void setItems(List<RankingItemDto> items) {
		this.items = items;
	}

}
