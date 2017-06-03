package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User{
	
	
	private static final long serialVersionUID = -2853930539938433902L;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long userId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="mobile_no")
	private String mobileNoo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password_hash_text")
	private String passwordHashText;
	
	@Column(name="hash_key")
	private String hashKey;
	
	@Column(name="is_enabled")
	private String isEnabled;
	
	@Column(name="is_deleted")
	private String isDeleted;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobileNoo() {
		return mobileNoo;
	}

	public void setMobileNoo(String mobileNoo) {
		this.mobileNoo = mobileNoo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHashText() {
		return passwordHashText;
	}

	public void setPasswordHashText(String passwordHashText) {
		this.passwordHashText = passwordHashText;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
}
