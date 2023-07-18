package com.example.demo.oAuth;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.GoogleUser;

@RestController
@RequestMapping("/api")
public class OauthController {
	
	@Autowired
 	private OAuth2GoogleService oAuth2GoogleService;
	
	@PostMapping("/oauthLogin")
	public ResponseEntity<?> login(@RequestBody HashMap<String, String> googleToten){
		
		GoogleUser googleUser = oAuth2GoogleService.getUserInfo(googleToten.get("gJwt"));
		System.out.println(googleUser.toString());
		return null;
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
