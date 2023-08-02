package com.ranking.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ranking.persistence.entity.OneTimePassword;
import com.ranking.persistence.entity.User;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(OneTimePassword otp, User user) throws MessagingException {
		MimeMessage msg = this.mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo(otp.getEmail());
		helper.setSubject("Validação do Email");
		helper.setText(String.format("Caro %s. %n%n Informe o código a seguir para validar seu email. %n%n %s",
				user.getFirstName(), otp.getCode()), false);

		this.mailSender.send(msg);
	}

}
