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
@Table(name = "mobile_app_pinging")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppPinging extends BaseModel implements Serializable{
	
	private Long mobileAppPingingId;
	private MobileAppUser mobileAppUser;
	private PingingType pingingType;
	private Double longitude;
	private Double latitude;
	private Date pingTime;
	
	
	public MobileAppPinging()
	{
		
	}
	public MobileAppPinging(Long mobileAppPingingId,MobileAppUser mobileAppUser,PingingType pingingType,
			Double longitude,Double latitude,Date pingTime)
	{
		this.mobileAppPingingId = mobileAppPingingId;
		this.mobileAppUser = mobileAppUser;
		this.pingingType = pingingType;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mobile_app_pinging_id", unique=true, nullable=false)
	public Long getMobileAppPingingId() {
		return mobileAppPingingId;
	}
	public void setMobileAppPingingId(Long mobileAppPingingId) {
		this.mobileAppPingingId = mobileAppPingingId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_app_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}
	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="pinging_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PingingType getPingingType() {
		return pingingType;
	}
	public void setPingingType(PingingType pingingType) {
		this.pingingType = pingingType;
	}
	@Column(name = "longitude")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(name = "latitude")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Column(name = "ping_time")
	public Date getPingTime() {
		return pingTime;
	}
	public void setPingTime(Date pingTime) {
		this.pingTime = pingTime;
	}
	
	

}
