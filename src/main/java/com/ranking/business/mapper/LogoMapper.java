package com.ranking.business.mapper;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.ranking.business.RankingItemManager;
import com.ranking.persistence.entity.Logo;
import com.ranking.persistence.entity.RankingItem;
import com.ranking.presentation.dto.LogoDto;
import com.ranking.presentation.dto.NewLogoDto;

@Mapper(componentModel = "spring", imports = Calendar.class)
public abstract class LogoMapper {

	@Autowired
	private RankingItemManager rankingItemManager;

	public abstract LogoDto convertToDto(Logo logo);
	
	@Mapping(target = "fileName", source = "image.originalFilename")
	@Mapping(target = "createdDate", expression = "java(Calendar.getInstance().getTime())")
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "rankingItems", source = "newLogoDto.rankingItemsId")
	public abstract Logo convertToEntity(NewLogoDto newLogoDto, MultipartFile image);
	
	protected List<RankingItem> convertToEntityList(List<Long> rankingItemsId) {
		return rankingItemsId.stream().map(i -> this.rankingItemManager.getRankingItem(i)).collect(Collectors.toList());
	}

}
