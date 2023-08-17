package com.ranking.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ranking.business.BannerManager;
import com.ranking.business.mapper.BannerMapper;
import com.ranking.presentation.dto.BannerDto;
import com.ranking.presentation.dto.NewBannerDto;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

	@Autowired
	private BannerMapper bannerMapper;

	@Autowired
	private BannerManager bannerManager;

	@PostMapping
	public BannerDto createBanner(@Valid @RequestPart("banner") NewBannerDto newBannerDto, @RequestPart("image") MultipartFile image) {
		return this.bannerMapper.convertToDto(this.bannerManager.createBanner(newBannerDto, image));
	}

	@PutMapping("/{id}/click")
	public void click(@PathVariable("id") Long bannerId) {
		this.bannerManager.click(bannerId);
	}

}
