package com.itgrids.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_provider")
public class ServiceProvider implements Serializable{

	private static final long serialVersionUID = -4074964122846729503L;
	
	private Long serviceProviderId;
	private Long providerName;
	
	@Id
	@Column(name="service_provider_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(Long serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	@Column(name="provider_name")
	public Long getProviderName() {
		return providerName;
	}

	public void setProviderName(Long providerName) {
		this.providerName = providerName;
	}
	
}
