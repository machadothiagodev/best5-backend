package com.ranking.presentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.business.BannerManager;
import com.ranking.business.LogoManager;
import com.ranking.exception.FileNotFoundException;

@RestController
@RequestMapping("img")
public class StaticContentController {

	@GetMapping(value = "/banner/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getBanner(@PathVariable String name) {
		try {
			return this.getImage(BannerManager.FILE_DIRECTORY + name);
		} catch (IOException e) {
			throw new FileNotFoundException();
		}
	}

	@GetMapping(value = "/logo/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getLogo(@PathVariable String name) {
		try {
			return this.getImage(LogoManager.FILE_DIRECTORY + name);
		} catch (IOException e) {
			throw new FileNotFoundException();
		}
	}

	private byte[] getImage(String filePath) throws IOException {
		Path path = Path.of(filePath);
		return IOUtils.toByteArray(Files.newInputStream(path));
	}

}
