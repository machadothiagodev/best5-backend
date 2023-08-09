package com.ranking.business.mapper;

import org.mapstruct.Mapper;

import com.ranking.persistence.entity.Banner;
import com.ranking.presentation.dto.BannerDto;

@Mapper(componentModel = "spring")
public interface BannerMapper {

	public BannerDto convertToDto(Banner banner);

}
