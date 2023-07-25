package com.ranking.business.mapper;

import java.util.Calendar;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ranking.persistence.entity.User;
import com.ranking.presentation.dto.NewUserDto;

@Mapper(componentModel = "spring", imports = { Calendar.class, BCryptPasswordEncoder.class })
public interface UserMapper {

	@Mapping(target = "createdDate", expression = "java(Calendar.getInstance().getTime())")
	@Mapping(target = "password", expression = "java(new BCryptPasswordEncoder().encode(newUserDto.getPassword()))")
	@Mapping(target = "rankings", ignore = true)
	User convertToEntity(NewUserDto newUserDto);

}
