package com.ranking.business.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ranking.persistence.entity.RankingItem;
import com.ranking.presentation.dto.RankingItemDto;

@Mapper(componentModel = "spring")
public interface RankingItemMapper {

	public List<RankingItem> convertToEntityList(List<RankingItemDto> rankingItemsDto);

	@Mapping(target = "votes", ignore = true)
	@Mapping(target = "ranking", ignore = true)
	@Mapping(target = "createdDate", ignore = true)
	public RankingItem convertToEntity(RankingItemDto rankingItemDto);

	@Mapping(target = "votes", expression = "java(rankingItem.getVotes().size())")
	public RankingItemDto convertToDto(RankingItem rankingItem);

}
