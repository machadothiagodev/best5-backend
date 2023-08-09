package com.ranking.presentation;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticContentController {

	@GetMapping(value = "/img/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getImage(@PathVariable String name) throws IOException {
		InputStream inputStream = this.getClass().getResourceAsStream("/static/" + name);
		return IOUtils.toByteArray(inputStream);
	}

}
