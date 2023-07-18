package com.example.demo.oAuth;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.PublicController;
import com.example.demo.model.GoogleUser;
import com.example.demo.model.Is_google_sign;
import com.example.demo.model.Is_used;
import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.model.UserDetail;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

@RestController
@RequestMapping("/api")
public class OauthController extends PublicController {

	@Autowired
	private OAuth2GoogleService oAuth2GoogleService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/oauthLogin")
	public ResponseEntity<?> login(@RequestBody HashMap<String, String> googleToten) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(googleToten.get("gJwt"));
		try {
			GoogleUser googleUser = oAuth2GoogleService.getUserInfo(googleToten.get("gJwt"));
			User user = new User();
			user.setRole(Roles.GUEST);
			user.setIsUsed(Is_used.USED);
			user.setIs_google_sign(Is_google_sign.GOOGLE_SIGN);
			user.setUsername(googleUser.getEmail());
			UserDetail userDetail = new UserDetail();
//			userDetail.setIs_used(null);
			userDetail.setRole(user.getRole());
			userDetail.setUsername(user.getUsername());
			String jwt = jwtTokenProvider.generateToken(userDetail);
			user.setJwt(jwt);
			
			User oldUser = userRepository.findByUsername(user.getUsername());
			if (oldUser==null) {
				userRepository.save(user);
			}else {
				oldUser.setJwt(user.getJwt());
				oldUser.setRole(user.getRole());
				oldUser.setIs_google_sign(user.getIs_google_sign());
				oldUser.setIsUsed(user.getIsUsed());
				userRepository.save(oldUser);
			}
			
			
			System.out.println(googleUser.toString());

			return new ResponseEntity<>(user, headers, HttpStatus.OK);
		}

		catch (Exception e) {
			System.out.println("Google Auth Error");
			return new ResponseEntity<>(headers, HttpStatus.UNAUTHORIZED);
		}
	}

//	@PostMapping("/oauthLogin")
//	  public ResponseEntity<?> login(@AuthenticationPrincipal OAuth2User principal) {
//	    // 獲取已驗證的使用者資訊
//	    String userId = principal.getAttribute("sub");
//	    String userName = principal.getAttribute("name");
//	    String userEmail = principal.getAttribute("email");
//	    System.out.println("***oauthLogin***");
//
//	    // 在這裡執行相應的處理邏輯，例如驗證使用者、儲存資訊等等
//
//	    // 回應結果
//	    return ResponseEntity.ok("Login success");
//	  }
}
