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
	private String serviceName;
	private ServiceProvider serviceProvider;
	private ServiceModule serviceModule;
	private WebserviceCallType webserviceCallType;
	private String methodType;
	private String serviceType;
	private String isDeleted;

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
	@Column(name="service_name")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "webservice_call_type_id")
	public WebserviceCallType getWebserviceCallType() {
		return webserviceCallType;
	}

	public void setWebserviceCallType(WebserviceCallType webserviceCallType) {
		this.webserviceCallType = webserviceCallType;
	}

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="method_type")
	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	@Column(name="service_type")
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
}
