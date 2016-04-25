package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="login_required_url")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LoginRequiredUrl extends BaseModel implements Serializable {
	
	private Long loginRequiredUrlId;
	private String url;
	private String isLoginRequired;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "login_required_url_id", unique = true, nullable = false)
	public Long getLoginRequiredUrlId() {
		return loginRequiredUrlId;
	}
	public void setLoginRequiredUrlId(Long loginRequiredUrlId) {
		this.loginRequiredUrlId = loginRequiredUrlId;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "is_login_required")
	public String getIsLoginRequired() {
		return isLoginRequired;
	}
	public void setIsLoginRequired(String isLoginRequired) {
		this.isLoginRequired = isLoginRequired;
	}	
}
