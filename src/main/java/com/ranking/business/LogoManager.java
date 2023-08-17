package com.ranking.business;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ranking.business.mapper.LogoMapper;
import com.ranking.persistence.LogoRepository;
import com.ranking.persistence.entity.Logo;
import com.ranking.persistence.entity.User;
import com.ranking.presentation.dto.NewLogoDto;

@Service
public class LogoManager {

	public static final String FILE_DIRECTORY = "/var/lib/best5/img/logo/";

	@Autowired
	private LogoMapper logoMapper;

	@Autowired
	private LogoRepository logoRepository;

	public Logo createLogo(NewLogoDto newLogoDto, MultipartFile image) {
		try {
			this.persistFile(image);
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getLocalizedMessage());
		}

		Logo logo = this.logoMapper.convertToEntity(newLogoDto, image);
		logo.setCreatedBy((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		return this.logoRepository.save(logo);
	}

	private void persistFile(MultipartFile image) throws IOException {
		FileUtils.writeByteArrayToFile(new File(FILE_DIRECTORY + image.getOriginalFilename()), image.getBytes());
	}

}
