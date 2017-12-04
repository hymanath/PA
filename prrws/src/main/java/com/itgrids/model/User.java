package com.itgrids.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User{
	
	private static final long serialVersionUID = -2853930539938433902L;

	private Long userId;
	private String username;
	private String mobileNoo;
	private String email;
	private String passwordHashText;
	private String hashKey;
	private String isEnabled;
	private String isDeleted;
	
	private String password;
	private Long redirectUrlId;
	private RedirectUrl redirectUrl;

	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="mobile_no")
	public String getMobileNoo() {
		return mobileNoo;
	}
	public void setMobileNoo(String mobileNoo) {
		this.mobileNoo = mobileNoo;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="password_hash_text")
	public String getPasswordHashText() {
		return passwordHashText;
	}
	public void setPasswordHashText(String passwordHashText) {
		this.passwordHashText = passwordHashText;
	}

	@Column(name="hash_key")
	public String getHashKey() {
		return hashKey;
	}
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	@Column(name="is_enabled")
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="redirect_url_id")
	public Long getRedirectUrlId() {
		return redirectUrlId;
	}
	public void setRedirectUrlId(Long redirectUrlId) {
		this.redirectUrlId = redirectUrlId;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "redirect_url_id", insertable = false, updatable = false)
	public RedirectUrl getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(RedirectUrl redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
