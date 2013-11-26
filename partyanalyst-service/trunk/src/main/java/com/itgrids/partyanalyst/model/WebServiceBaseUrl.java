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
@Table(name="webservice_base_url")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WebServiceBaseUrl extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1477485703200270701L;
	
	private Long webServiceBaseUrlId;
	private String appName;
	private String url;
	
    public WebServiceBaseUrl()
    {}
    
    public WebServiceBaseUrl(String appName, String url)
    {
    	this.appName = appName;
    	this.url = url;
    }
    
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="webservice_base_url_id", unique=true, nullable=false)
	public Long getWebServiceBaseUrlId() {
		return webServiceBaseUrlId;
	}
	public void setWebServiceBaseUrlId(Long webServiceBaseUrlId) {
		this.webServiceBaseUrlId = webServiceBaseUrlId;
	}
	
	@Column(name="app_name",length=50)
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	@Column(name="url",length=200)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
