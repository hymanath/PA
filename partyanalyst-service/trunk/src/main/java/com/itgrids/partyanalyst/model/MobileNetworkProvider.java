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
@Table(name = "mobile_network_provider")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileNetworkProvider extends BaseModel implements Serializable{
	
	private Long mobileNetworkProviderId;
	private String providerName;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mobile_network_provider_id", unique=true, nullable=false)
	public Long getMobileNetworkProviderId() {
		return mobileNetworkProviderId;
	}
	public void setMobileNetworkProviderId(Long mobileNetworkProviderId) {
		this.mobileNetworkProviderId = mobileNetworkProviderId;
	}
	@Column(name="provider_name")
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
}
