package com.ranking.business;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ranking.business.mapper.BannerMapper;
import com.ranking.persistence.BannerRepository;
import com.ranking.persistence.entity.Banner;
import com.ranking.persistence.entity.User;
import com.ranking.presentation.dto.NewBannerDto;

@Service
public class BannerManager {

	public static final String FILE_DIRECTORY = "/var/lib/best5/img/banner/";

	@Autowired
	private BannerMapper bannerMapper;

	@Autowired
	private BannerRepository bannerRepository;

	public Banner createBanner(NewBannerDto newBannerDto, MultipartFile image) {
		try {
			this.validateImage(image);
			this.persistFile(image);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getLocalizedMessage());
		}

		Banner banner = this.bannerMapper.convertToEntity(newBannerDto, image);
		banner.setCreatedBy((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		return this.bannerRepository.save(banner);
	}

	private void validateImage(MultipartFile image) {
		// Validate extension and Dimension
	}

	private void persistFile(MultipartFile image) throws IOException {
		FileUtils.writeByteArrayToFile(new File(FILE_DIRECTORY + image.getOriginalFilename()), image.getBytes());
	}

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
