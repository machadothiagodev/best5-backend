package com.ranking.business.mapper;

import java.util.Calendar;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ranking.persistence.entity.Feedback;
import com.ranking.presentation.dto.FeedbackDto;
import com.ranking.presentation.dto.NewFeedbackDto;

@Mapper(componentModel = "spring", imports = Calendar.class)
public abstract class FeedbackMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdDate", expression = "java(Calendar.getInstance().getTime())")
	public abstract Feedback convertToEntity(NewFeedbackDto newFeedbackDto);

	public abstract FeedbackDto convertToDto(Feedback feedback);

}
