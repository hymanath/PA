package com.itgrids.model;

import java.io.Serializable;

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
@Table(name = "webservice")
public class Webservice implements Serializable{

	private static final long serialVersionUID = 7457615697443022305L;
	
	private Long webserviceId;
	private String url;
	private ServiceProvider serviceProvider;
	private ServiceModule serviceModule;

	@Id
	@Column(name="webservice_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getWebserviceId() {
		return webserviceId;
	}

	public void setWebserviceId(Long webserviceId) {
		this.webserviceId = webserviceId;
	}

	@Column(name="url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_provider_id")
	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_module_id")
	public ServiceModule getServiceModule() {
		return serviceModule;
	}

	public void setServiceModule(ServiceModule serviceModule) {
		this.serviceModule = serviceModule;
	}

}
