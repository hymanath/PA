package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "redirect_url")
public class RedirectUrl {
	
	private Long redirectUrlId;
	private String url;
	private String isDeleted;
	
	@Id
	@Column(name="redirect_url_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRedirectUrlId() {
		return redirectUrlId;
	}
	public void setRedirectUrlId(Long redirectUrlId) {
		this.redirectUrlId = redirectUrlId;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
