package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.security.JwtTokenProvider;

@Service
public class JwtService {
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	public String CreateJwt() {
		
		
		return null;
	}
}
