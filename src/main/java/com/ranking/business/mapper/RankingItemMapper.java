package com.ranking.business.mapper;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.ranking.persistence.entity.Logo;
import com.ranking.persistence.entity.RankingItem;
import com.ranking.presentation.dto.LogoDto;
import com.ranking.presentation.dto.RankingItemDto;

@Mapper(componentModel = "spring")
public abstract class RankingItemMapper {

	@Autowired
	private LogoMapper logoMapper;

	public abstract List<RankingItem> convertToEntityList(List<RankingItemDto> rankingItemsDto);

	@Mapping(target = "votes", ignore = true)
	@Mapping(target = "ranking", ignore = true)
	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "logos", ignore = true)
	public abstract RankingItem convertToEntity(RankingItemDto rankingItemDto);

	@Mapping(target = "votes", expression = "java(rankingItem.getVotes().size())")
	@Mapping(target = "logo", source = "logos")	
	public abstract RankingItemDto convertToDto(RankingItem rankingItem);

	protected LogoDto convertToDto(List<Logo> logos) {
		if (!CollectionUtils.isEmpty(logos)) {
			Optional<Logo> optional = logos.stream().filter(l -> l.getActive() && l.getHiredDate().after(Calendar.getInstance().getTime())).findFirst();

			if (optional.isPresent()) {
				return this.logoMapper.convertToDto(optional.get());
			}
		}
		return null;
	}

}
