package com.ranking.business.mapper;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.ranking.persistence.entity.Banner;
import com.ranking.persistence.entity.Ranking;
import com.ranking.presentation.dto.BannerDto;
import com.ranking.presentation.dto.RankingDto;

@Mapper(componentModel = "spring", uses = RankingItemMapper.class, imports = Calendar.class)
public abstract class RankingMapper {

	@Autowired
	private BannerMapper bannerMapper;

	public abstract List<RankingDto> convertoToDtoList(List<Ranking> rankings);

	@Mapping(target = "banner", source = "banners")
	public abstract RankingDto convertToDto(Ranking ranking);

	protected BannerDto convertToDto(List<Banner> banners) {
		if (!CollectionUtils.isEmpty(banners)) {
			Optional<Banner> optional = banners.stream().filter(Banner::getActive).findFirst();

			if (optional.isPresent()) {
				return this.bannerMapper.convertToDto(optional.get());
			}
		}
		return null;
	}

	@Mapping(target = "createdDate", expression = "java(Calendar.getInstance().getTime())")
	@Mapping(target = "createdBy", ignore = true)
	@Mapping(target = "banners", ignore = true)
	public abstract Ranking convertToEntity(RankingDto rankingDto);

	@AfterMapping
	protected void addRelation(@MappingTarget Ranking ranking) {
		ranking.getItems().stream().forEach(i -> i.setRanking(ranking));
	}

}
