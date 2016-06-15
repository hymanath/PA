package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="mobile_device_feature")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileDeviceFeature extends BaseModel implements Serializable{
	
	private Long mobileDeviceFeatureId;
	private String type;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mobile_device_feature_id", unique=true, nullable=false)
	public Long getMobileDeviceFeatureId() {
		return mobileDeviceFeatureId;
	}
	public void setMobileDeviceFeatureId(Long mobileDeviceFeatureId) {
		this.mobileDeviceFeatureId = mobileDeviceFeatureId;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
