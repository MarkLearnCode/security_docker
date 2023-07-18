package com.example.demo.controller;

import java.util.HashMap;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Is_used;
import com.example.demo.model.User;
import com.example.demo.model.UserDetail;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api")
public class LoginController extends PublicController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		System.out.println("into Login");
//		String pwd = passwordEncoder.encode(user.getPassword());
//		user.setPassword(pwd);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUsername());
		System.out.println("check");
		// 進行驗證
		// 返回相關資訊或設定登入成功後的行為
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		String msg = "";
		

		try {
			// 進行驗證
			System.out.println("TRY");
			Authentication authenticated = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(authenticated);
			// 返回相關資訊或設定登入成功後的行為
			User userForUserDetail = userRepository.findByUsername(user.getUsername());
			UserDetail userDetail = new UserDetail();
			userDetail.setRole(userForUserDetail.getRole());
			userDetail.setUsername(userForUserDetail.getUsername());
			String jwt = jwtTokenProvider.generateToken(userDetail);
//			發送給前端及SAVE MYSQLQL
			userForUserDetail.setJwt(jwt);
			userRepository.save(userForUserDetail);

			
//			msg = "success";
			return new ResponseEntity<>(userForUserDetail,headers,HttpStatus.OK);
		} catch (AuthenticationException e) {
			// 身份驗證失敗的處理邏輯
			System.out.println("ERR");
			System.out.println(e);
			msg = "login error";
			return new ResponseEntity<>(msg,headers,HttpStatus.UNAUTHORIZED);
		}		
	}
	
	@PostMapping("/jwtLogin")
	public ResponseEntity<?> tokenLogin(HttpServletRequest request) {
		String jwt= "";
		String bearerToken = request.getHeader("Authorization");
		System.out.println(bearerToken);
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	        jwt= bearerToken.substring(7);
	        }
	    System.out.println(jwt);
	    HashMap<String,String> msg = new HashMap<>();
		msg.put("msg", "success");
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    if (jwtTokenProvider.validateToken(jwt)) {
	    	UserDetail userDetail = jwtTokenProvider.getUserDetailsFromToken(jwt);
	    	System.out.println(userDetail.getUsername());
	            // JWT 驗證成功
	    	System.out.println(msg);
	    	return new ResponseEntity<>(msg,headers,HttpStatus.OK);
	    }else {
	    	msg.put("msg", "error");
	    	System.out.println(msg);
	    	return new ResponseEntity<>(msg,headers,HttpStatus.UNAUTHORIZED);
			}		
	}

	
	@PostMapping("/sign")
	public ResponseEntity<?> sign(@RequestBody User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setIsUsed(Is_used.USED);
		UserDetail userDetail = new UserDetail();
		userDetail.setUsername(user.getUsername());
		System.out.println("user role = " + user.getRole());
		
		userDetail.setRole(user.getRole());
		
		String jwt = jwtTokenProvider.generateToken(userDetail);
		user.setPassword(encodePwd);
		
		user.setJwt(jwt);
		userRepository.save(user);
		HashMap<String,String> msg = new HashMap<>();
		msg.put("msg", "success");
		return ResponseEntity.ok(msg);
	}
}
