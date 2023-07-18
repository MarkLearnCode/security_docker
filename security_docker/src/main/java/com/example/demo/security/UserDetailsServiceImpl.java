package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Roles;
import com.example.demo.model.User;
import com.example.demo.model.UserDetail;
import com.example.demo.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
	        User user = userRepository.findByUsername(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }
	        
	     UserDetail userDetail = new UserDetail();
	        userDetail.setUsername(user.getUsername());
	        userDetail.setPassword(user.getPassword());
	        userDetail.setRole(user.getRole());
	        // 設定其他屬性
	        
//	        List<GrantedAuthority> authorities = new ArrayList<>();
//	        for (Roles role : user.getRoles()) {
//	            authorities.add(new SimpleGrantedAuthority(role.getName()));
//	        }
//	        userDetail.setAuthorities(authorities);  
	      
	        
	        return userDetail;
	    } catch (Exception e) {
	        System.out.println("Failed to load user: " + e.getMessage());
	        throw new UsernameNotFoundException("Failed to load user");
	    }
	}
}
