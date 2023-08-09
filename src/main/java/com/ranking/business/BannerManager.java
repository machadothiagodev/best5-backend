package com.ranking.business;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranking.persistence.BannerRepository;
import com.ranking.persistence.entity.Banner;

@Service
public class BannerManager {

	@Autowired
	private BannerRepository bannerRepository;

//	@Transactional
	public void click(Long bannerId) {
		Optional<Banner> optional = this.bannerRepository.findById(bannerId);

		if (optional.isPresent()) {
			Banner banner = optional.get();
			banner.setClicks(banner.getClicks() + 1);

			if (banner.getClicks() >= banner.getHiredClicks()) {
				banner.setActive(Boolean.FALSE);
			}

			this.bannerRepository.save(banner);
		}

		throw new EntityNotFoundException(String.format("Banner #%s does not exist", bannerId));
	}

}
