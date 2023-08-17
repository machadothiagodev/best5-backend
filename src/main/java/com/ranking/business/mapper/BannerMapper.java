package com.ranking.business.mapper;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.ranking.business.RankingManager;
import com.ranking.persistence.entity.Banner;
import com.ranking.persistence.entity.Ranking;
import com.ranking.presentation.dto.BannerDto;
import com.ranking.presentation.dto.NewBannerDto;

@Mapper(componentModel = "spring", imports = Calendar.class)
public abstract class BannerMapper {

	@Autowired
	private RankingManager rankingManager;

	public abstract BannerDto convertToDto(Banner banner);

	@Mapping(target = "fileName", source = "image.originalFilename")
	@Mapping(target = "createdDate", expression = "java(Calendar.getInstance().getTime())")
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "clicks", ignore = true)
	@Mapping(target = "active", ignore = true)
	@Mapping(target = "rankings", source = "newBannerDto.rankingsId")
	public abstract Banner convertToEntity(NewBannerDto newBannerDto, MultipartFile image);

	protected List<Ranking> convertToEntityList(List<Long> rankingsId) {
		return rankingsId.stream().map(i -> this.rankingManager.getRanking(i)).collect(Collectors.toList());
	}

}
