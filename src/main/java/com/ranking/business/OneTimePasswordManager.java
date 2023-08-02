package com.ranking.business;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranking.exception.OneTimePasswordException;
import com.ranking.persistence.OneTimePasswordRepository;
import com.ranking.persistence.entity.OneTimePassword;
import com.ranking.persistence.entity.User;
import com.ranking.presentation.dto.AuthDto;
import com.ranking.service.MailService;

@Service
public class OneTimePasswordManager {

	private static final Integer OTP_LENGTH = 4;
	private static final Random RANDOM = new Random();

	private static final Long EXPIRY_INTERVAL = 5L * 60 * 1000;

	@Autowired
	private OneTimePasswordRepository oneTimePasswordRepository;

	@Autowired
	private UserManager usermanager;

	@Autowired
	private MailService mailService;

	public void send(String email) {
		User user = this.usermanager.getUser(email);

		OneTimePassword otp = new OneTimePassword(user.getEmail(), this.getCode(), this.getExpires());

		oneTimePasswordRepository.save(otp);

		try {
			this.mailService.sendEmail(otp, user);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private Integer getCode() {
		StringBuilder code = new StringBuilder();

		for (int i = 0; i < OTP_LENGTH; i++) {
			code.append(RANDOM.nextInt(9) + 1);
		}

		return Integer.parseInt(code.toString());
	}

	private Date getExpires() {
		return new Date(System.currentTimeMillis() + EXPIRY_INTERVAL);
	}

	public AuthDto validate(Integer code, String email) {
		User user = this.usermanager.getUser(email);
		
		Optional<OneTimePassword> optional = this.oneTimePasswordRepository.findFirstByEmailOrderByIdDesc(email);
		if (!optional.isPresent()) {
			throw new OneTimePasswordException("User %s not found", email);
		}
		
		OneTimePassword otp = optional.get();

		if (!otp.getCode().equals(code)) {
			throw new OneTimePasswordException("Invalid code");	
		}

		if (!otp.getExpires().after(Calendar.getInstance().getTime())) {
			throw new OneTimePasswordException("Expired code");	
		}

		this.oneTimePasswordRepository.delete(otp);
		
		user.setActive(Boolean.TRUE);
		this.usermanager.save(user);
		
		return this.usermanager.getAuth(user);
	}

}
