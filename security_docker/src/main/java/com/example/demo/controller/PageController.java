package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserDetail;
import com.example.demo.security.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin("*")
@RequestMapping("/page")
public class PageController extends PublicController{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@PostMapping("/pageA")
	public ResponseEntity<?> pageA(HttpServletRequest request) {
		String jwt= "";
		String bearerToken = request.getHeader("Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	        jwt= bearerToken.substring(7);
	        }
		System.out.println("JWT = "+jwt);
		UserDetail userDetail = jwtTokenProvider.getUserDetailsFromToken(jwt);
		System.out.println(userDetail);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (userDetail.getUsername()!=null) {
			System.out.println(userDetail.getUsername());
			System.out.println(userDetail.getRole());
		}
		return new ResponseEntity<>(userDetail,headers, HttpStatus.OK);
	}
	
	@PostMapping("/pageB")
	public ResponseEntity<?> pageB(HttpServletRequest request) {
		String jwt= "";
		String bearerToken = request.getHeader("Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	        jwt= bearerToken.substring(7);
	        }
		System.out.println("JWT = "+jwt);
		UserDetail userDetail = jwtTokenProvider.getUserDetailsFromToken(jwt);
		System.out.println(userDetail);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (userDetail.getUsername()!=null) {
			System.out.println(userDetail.getUsername());
			System.out.println(userDetail.getRole());
		}
		return new ResponseEntity<>(userDetail,headers, HttpStatus.OK);
	}
	
	@PostMapping("/pageC")
	public ResponseEntity<?> pageC(HttpServletRequest request) {
		String jwt= "";
		String bearerToken = request.getHeader("Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	        jwt= bearerToken.substring(7);
	        }
		System.out.println("JWT = "+jwt);
		UserDetail userDetail = jwtTokenProvider.getUserDetailsFromToken(jwt);
		System.out.println(userDetail);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (userDetail.getUsername()!=null) {
			System.out.println(userDetail.getUsername());
			System.out.println(userDetail.getRole());
		}
		return new ResponseEntity<>(userDetail,headers, HttpStatus.OK);
	}
	
	@PostMapping("/pageD")
	public ResponseEntity<?> pageD(HttpServletRequest request) {
		String jwt= "";
		String bearerToken = request.getHeader("Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	        jwt= bearerToken.substring(7);
	        }
		System.out.println("JWT = "+jwt);
		UserDetail userDetail = jwtTokenProvider.getUserDetailsFromToken(jwt);
		System.out.println(userDetail);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (userDetail.getUsername()!=null) {
			System.out.println(userDetail.getUsername());
			System.out.println(userDetail.getRole());
		}
		return new ResponseEntity<>(userDetail,headers, HttpStatus.OK);
	}
}
