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
import com.ranking.presentation.dto.NewBannerDto;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

	@Autowired
	private BannerManager bannerManager;

	@PostMapping
	public void createBanner(@Valid @RequestPart NewBannerDto banner, @RequestPart MultipartFile image) {
		System.out.println("Uploaded File: ");
		System.out.println("Type : " + image.getContentType());
		System.out.println("Name : " + image.getOriginalFilename());
		System.out.println("Size : " + image.getSize());
	}

	@PutMapping("/{id}/click")
	public void click(@PathVariable("id") Long bannerId) {
		this.bannerManager.click(bannerId);
	}

}
