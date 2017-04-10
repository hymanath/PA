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
@Table(name="redirect_url")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RedirectUrl extends BaseModel implements Serializable{

	private static final long serialVersionUID = 4935050646329209739L;
	
	private Long redirectUrlId;
	private String redirectName;
	private String url;
	
	RedirectUrl(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="redirect_url_id", unique=true, nullable=false)
	public Long getRedirectUrlId() {
		return redirectUrlId;
	}

	public void setRedirectUrlId(Long redirectUrlId) {
		this.redirectUrlId = redirectUrlId;
	}

	@Column(name="redirect_name")
	public String getRedirectName() {
		return redirectName;
	}

	public void setRedirectName(String redirectName) {
		this.redirectName = redirectName;
	}

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
