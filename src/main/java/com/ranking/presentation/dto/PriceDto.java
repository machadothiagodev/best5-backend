package com.ranking.presentation.dto;

import java.math.BigDecimal;

public class PriceDto {

	private BigDecimal value;

	public PriceDto(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
