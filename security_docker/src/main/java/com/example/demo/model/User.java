package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="is_used")
	private Is_used is_used;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "is_google_sign")
    private Is_google_sign is_google_sign;

	private String jwt;

	private String password;

	private Roles role;

	private String username;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Is_used getIsUsed() {
		return this.is_used;
	}

	public void setIsUsed(Is_used isUsed) {
		this.is_used = isUsed;
	}

	public String getJwt() {
		return this.jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return this.role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Is_google_sign getIs_google_sign() {
		return is_google_sign;
	}

	public void setIs_google_sign(Is_google_sign is_google_sign) {
		this.is_google_sign = is_google_sign;
	}

}