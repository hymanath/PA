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
@Table(name="mobile_network_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileNetworkType extends BaseModel implements Serializable{
	private Long mobileNetworkTypeId;
	private String networkType;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mobile_network_type_id", unique=true, nullable=false)
	public Long getMobileNetworkTypeId() {
		return mobileNetworkTypeId;
	}
	public void setMobileNetworkTypeId(Long mobileNetworkTypeId) {
		this.mobileNetworkTypeId = mobileNetworkTypeId;
	}
	@Column(name="network_type")
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
}
