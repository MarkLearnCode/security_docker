package com.example.demo.oAuth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.GoogleUser;

@Service
public class OAuth2GoogleService {
	public GoogleUser getUserInfo(String accessToken) {
		String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo";

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accessToken);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<GoogleUser> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET,
				new HttpEntity<>(headers), GoogleUser.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			return response.getBody();
		} else {
			throw new RuntimeException("Failed to retrieve user info from Google.");
		}
	}
}
