package com.ranking.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("api/recaptcha/verify")
public class ReCaptchaController {

	@PutMapping("/{token}")
	public ReCaptchaResponse verify(@PathVariable String token, HttpServletRequest request) {
		ReCaptchaResponse reCaptchaResponse = null;

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			ObjectMapper mapper = new ObjectMapper();
			
		    final List<NameValuePair> params = new ArrayList<NameValuePair>();
		    params.add(new BasicNameValuePair("secret", "6LdmdPAmAAAAAFnyFDnH6B9Ne7bQLKI3qAuldqDp"));
		    params.add(new BasicNameValuePair("response", token));
		    params.add(new BasicNameValuePair("remoteip", request.getRemoteAddr()));
		    
			ClassicHttpRequest httpPost = ClassicRequestBuilder.post("https://www.google.com/recaptcha/api/siteverify")
					.setEntity(new UrlEncodedFormEntity(params)).build();

			reCaptchaResponse = httpclient.execute(httpPost, response -> {
//				System.out.println(response.getCode() + " " + response.getReasonPhrase());
//				final HttpEntity entity1 = response.getEntity();
//				System.out.println(EntityUtils.toString(entity1));
				return mapper.readValue(response.getEntity().getContent(), ReCaptchaResponse.class);
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return reCaptchaResponse;
	}

	private static class ReCaptchaResponse {
		private String success;
		private Date challengeTs;
		private String hostname;
		private Object[] errorCodes;

		public String getSuccess() {
			return success;
		}

		public void setSuccess(String success) {
			this.success = success;
		}

		public Date getChallengeTs() {
			return challengeTs;
		}

		@JsonProperty("challenge_ts")
		public void setChallengeTs(Date challengeTs) {
			this.challengeTs = challengeTs;
		}

		public String getHostname() {
			return hostname;
		}

		public void setHostname(String hostname) {
			this.hostname = hostname;
		}

		public Object[] getErrorCodes() {
			return errorCodes;
		}

		@JsonProperty("error-codes")
		public void setErrorCodes(Object[] errorCodes) {
			this.errorCodes = errorCodes;
		}
	}
}
