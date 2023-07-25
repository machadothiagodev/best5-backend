package com.ranking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ranking.business.UserManager;
import com.ranking.filter.AuthTokenFilter;

@Configuration
public class WebSecurityConfig {

//	@Autowired
//	private UserManager userManager;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, UserManager userManager) throws Exception {
		return http.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(c -> c.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.authorizeHttpRequests(authorize -> authorize.antMatchers(HttpMethod.POST, "/api/rankings", "/api/rankings/**").authenticated())
				.addFilterBefore(new AuthTokenFilter(userManager), UsernamePasswordAuthenticationFilter.class).build();
	}

}
