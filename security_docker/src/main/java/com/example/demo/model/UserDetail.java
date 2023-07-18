package com.example.demo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {
	private String username;
	private String password;
	private Roles role;
	private Is_used is_used;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public void setIs_used(Is_used is_used) {
		this.is_used = is_used;
	}
	
	

	public Roles getRole() {
		return role;
	}

	public Is_used getIs_used() {
		return is_used;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == null) {
			System.out.println(this.role);
	        return Collections.emptyList();
	    }
		System.out.println(this.role);
		  return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+this.role.toString()));
	}

	@Override
	public String getPassword() {		
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (this.is_used==is_used.DELETE) {
			return false;
		}
		return true;
	}

}
