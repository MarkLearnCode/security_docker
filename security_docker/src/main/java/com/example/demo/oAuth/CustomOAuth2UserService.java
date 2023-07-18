package com.example.demo.oAuth;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>  {
//
//	  private final ClientRegistrationRepository clientRegistrationRepository;
//	    private final RestOperations restOperations;
//
//	    public CustomOAuth2UserService(ClientRegistrationRepository clientRegistrationRepository,
//	                                   RestOperations restOperations) {
//	        this.clientRegistrationRepository = clientRegistrationRepository;
//	        this.restOperations = restOperations;
//	    }
//
//	    
//	    
//	    @Override
//	    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//	    	System.out.println(userRequest);
//	        ClientRegistration clientRegistration = 
//	        		clientRegistrationRepository.findByRegistrationId(userRequest.getClientRegistration().getRegistrationId());
//	        if (clientRegistration == null) {
//	            throw new OAuth2AuthenticationException(new OAuth2Error(
//	                    OAuth2ErrorCodes.INVALID_REQUEST,
//	                    "Invalid client registration: " + userRequest.getClientRegistration().getRegistrationId(),
//	                    null
//	            ));
//	        }
//	        
//	        // Retrieve the access token from the user request
//	        OAuth2AccessToken accessToken = userRequest.getAccessToken();
//	        System.out.println("*****************run oAuth service*****************");
//	     // Call the Google API to retrieve user information using the access token
//	        String userInfoUrl = clientRegistration.getProviderDetails().getUserInfoEndpoint().getUri();
//	        RestTemplate restTemplate = new RestTemplate();
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setBearerAuth(accessToken.getTokenValue());
//	        HttpEntity<String> entity = new HttpEntity<>(headers);
//	        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);
//	        Map<String, Object> userInfo = response.getBody();
//
//	        // Create the OAuth2User instance with the retrieved user information
//	        OAuth2User oAuth2User = new DefaultOAuth2User(
//	                Collections.emptyList(),
//	                userInfo,
//	                clientRegistration.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName()
//	        );
//	        System.out.println("*****************Loaded OAuth2User: " + oAuth2User);
//	        return oAuth2User;
//	    }
//
//}
