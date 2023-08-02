package com.ranking.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ranking.business.UserManager;
import com.ranking.presentation.dto.AuthDto;
import com.ranking.presentation.dto.NewUserDto;
import com.ranking.presentation.dto.UserLoginDto;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserManager userManager;

	@PostMapping("/login")
	public AuthDto login(@Validated @RequestBody UserLoginDto userLoginDto) {
		return this.userManager.login(userLoginDto);
	}

	@PostMapping("/signup")
	public void signup(@Validated @RequestBody NewUserDto newUserDto) {
		this.userManager.signup(newUserDto);
	}

}
