package com.ranking.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ranking.business.LogoManager;
import com.ranking.business.mapper.LogoMapper;
import com.ranking.presentation.dto.LogoDto;
import com.ranking.presentation.dto.NewLogoDto;

@RestController
@RequestMapping("/api/logos")
public class LogoController {
	
	@Autowired
	private LogoMapper logoMapper;

	@Autowired
	private LogoManager logoManager;

	@PostMapping
	public LogoDto createBanner(@Valid @RequestPart("logo") NewLogoDto newlogoDto, @RequestPart("image") MultipartFile image) {
		return this.logoMapper.convertToDto(this.logoManager.createLogo(newlogoDto, image));
	}

}
