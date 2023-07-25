package com.ranking.business.mapper;

import java.util.Calendar;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ranking.persistence.entity.Ranking;
import com.ranking.presentation.dto.RankingDto;

@Mapper(componentModel = "spring", uses = RankingItemMapper.class, imports = { Calendar.class })
public abstract class RankingMapper {

	public abstract List<RankingDto> convertoToDtoList(List<Ranking> rankings);

	public abstract RankingDto convertToDto(Ranking ranking);

	@Mapping(target = "createdDate", expression = "java(Calendar.getInstance().getTime())")
	@Mapping(target = "createdBy", ignore = true)
	public abstract Ranking convertToEntity(RankingDto rankingDto);

	@AfterMapping
	protected void addRelation(@MappingTarget Ranking ranking) {
		ranking.getItems().stream().forEach(i -> i.setRanking(ranking));
	}

}
