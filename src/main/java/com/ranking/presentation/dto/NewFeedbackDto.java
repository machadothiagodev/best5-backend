package com.ranking.presentation.dto;

import javax.validation.constraints.NotNull;

public class NewFeedbackDto {

	@NotNull(message = "Field rating is required")
	private Integer rating;
	private String comment;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
