package com.ranking.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.business.OneTimePasswordManager;
import com.ranking.presentation.dto.AuthDto;

@RestController
@RequestMapping("api/otp")
public class OneTimePasswordController {
	
	@Autowired
	private OneTimePasswordManager oneTimePasswordManager;

	@PostMapping("/send")
	public void send(@RequestParam(name = "email", required = true) String email) {
		this.oneTimePasswordManager.send(email);
	}

	@PostMapping("/validate")
	public AuthDto send(@RequestParam(name = "otp", required = true) Integer otp, @RequestParam(name = "email", required = true) String email) {
		return this.oneTimePasswordManager.validate(otp, email);
	}
	
}
