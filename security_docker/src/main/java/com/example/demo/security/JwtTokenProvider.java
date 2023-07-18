package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.model.Roles;
import com.example.demo.model.UserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtTokenProvider {
//	private String secret = "justkey";
	SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//	byte[] salt = secret.getBytes();
//	SecretKey secretKey = new SecretKeySpec(salt, SignatureAlgorithm.HS256.getJcaName());
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	public String generateToken(UserDetail userDetail) {
		// 生成JWT令牌並簽署
		// ...
		Map<String, String> claimsMap = new HashMap<>();
		claimsMap.put("role", userDetail.getRole().toString());
		

		return Jwts.builder().setClaims(claimsMap).setSubject(userDetail.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
				.signWith(secretKey).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature -> Message: {} ", e);
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token -> Message: {}", e);
		} catch (ExpiredJwtException e) {
			logger.error("Expired JWT token -> Message: {}", e);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token -> Message: {}", e);
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty -> Message: {}", e);
		}

		return false;
	}

	public UserDetail getUserDetailsFromToken(String token) {
		   try {
			   System.out.println("DO getUserDetailsFromToken");
//			   
//			   Jws<Claims> jws;
//
//			   try {
//			       jws = Jwts.parserBuilder()  // (1)
//			           
//			       .setKeyLocator(keyLocator)  // (2) dynamically lookup verification keys based on each JWS    
//			       //.verifyWith(key)          //     or a static key used to verify all encountered JWSs
//			           
//			       .build()                    // (3)
//			       .parseClaimsJws(jwsString); // (4) or parseContentJws(jwsString)
//			       
//			       // we can safely trust the JWT
//			        
//			   catch (JwtException ex) {       // (5)
//			       
//			       // we *cannot* use the JWT as intended by its creator
//			   }
			   
			   
		        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
		        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
		        Claims claims = claimsJws.getBody();
		        System.out.println("claims = "+claims);
		        // 從claims中獲取使用者資訊
		        String username = claims.getSubject();
		        // 其他資訊的獲取方式根據你在產生JWT時的設定而定

		        // 建立並返回UserDetail物件
		        UserDetail userDetail = new UserDetail();
		        String roleString = claims.get("role", String.class); // 获取角色字符串
		        Roles role = Roles.valueOf(roleString); // 根据角色字符串获取对应的枚举值
		        userDetail.setRole(role);
		        userDetail.setUsername(username);
		        // 其他使用者資訊的設定
		        System.out.println("userDetail = "+userDetail);
		        return userDetail;
		    } catch (JwtException e) {
		        // JWT解析或驗證失敗的處理邏輯
		        logger.error("Failed to parse JWT token: {}", e.getMessage());
		        return null;
		    }
	}
}
