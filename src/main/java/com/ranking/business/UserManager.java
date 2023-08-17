package com.ranking.business;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ranking.business.mapper.UserMapper;
import com.ranking.exception.AuthenticationException;
import com.ranking.exception.UserExistsException;
import com.ranking.exception.UserInactiveException;
import com.ranking.exception.UserNotFoundException;
import com.ranking.persistence.UserRepository;
import com.ranking.persistence.entity.User;
import com.ranking.presentation.dto.AuthDto;
import com.ranking.presentation.dto.NewUserDto;
import com.ranking.presentation.dto.UserLoginDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

@Service
public class UserManager {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired	
	private OneTimePasswordManager oneTimePasswordManager;

	public User getUser(String email) {
		Optional<User> optional = this.userRepository.findById(email);

		if (optional.isPresent()) {
			return optional.get();
		}

		throw new UserNotFoundException("User %s does not exist", email);
	}

	public AuthDto login(UserLoginDto userLoginDto) {
		Optional<User> optional = this.userRepository.findById(userLoginDto.getEmail());

		if (optional.isPresent()) {
			User user = optional.get();

			if (BooleanUtils.isFalse(user.getActive())) {
				this.oneTimePasswordManager.send(user.getEmail());
				throw new UserInactiveException();
			}

			if (new BCryptPasswordEncoder().matches(userLoginDto.getPassword(), user.getPassword())) {
				return this.getAuth(user);
			}

			throw new AuthenticationException("Invalid username or password");
		}

		throw new AuthenticationException("User %s not found", userLoginDto.getEmail());
	}

	public void signup(NewUserDto newUserDto) {
		Optional<User> optional = this.userRepository.findById(newUserDto.getEmail());

		if (optional.isPresent()) {
			throw new UserExistsException("User %s already exists", newUserDto.getEmail());
		}

		User user = this.userRepository.save(this.userMapper.convertToEntity(newUserDto));
		this.oneTimePasswordManager.send(user.getEmail());
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public AuthDto getAuth(User user) {
		Date expirationDate = this.getTokenExpiration();

		return new AuthDto(
				Jwts.builder()
					.setId(UUID.randomUUID().toString()).setSubject(user.getEmail())
					.setExpiration(expirationDate).claim("name", user.getFirstName())
					.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
					.compact(),
				expirationDate.getTime());
	}

	private Date getTokenExpiration() {
		Calendar tokenExpiration = Calendar.getInstance();
		tokenExpiration.add(Calendar.DAY_OF_MONTH, 1);

		return tokenExpiration.getTime();
	}

	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=").parseClaimsJws(token);

			System.out.println(claims.getBody().getExpiration());
			return true;
		} catch (JwtException | IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public String getUsername(String token) {
        return Jwts.parser().setSigningKey("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=").parseClaimsJws(token).getBody().getSubject();
	}

}
