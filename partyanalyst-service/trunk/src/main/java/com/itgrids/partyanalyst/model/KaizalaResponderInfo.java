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
@Table(name = "kaizala_responder_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KaizalaResponderInfo extends BaseModel implements Serializable {

	private Long kaizalaResponderInfoId;
	private String mobileNumber;
	private String name;
	private Long locationLevel;
	private Long locationLevelValue;
	private Long addressId;
	private String isDeleted;
	
	private UserAddress userAddress;
	
	@Id
	@Column(name="kaizala_responder_info_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getKaizalaResponderInfoId() {
		return kaizalaResponderInfoId;
	}
	public void setKaizalaResponderInfoId(Long kaizalaResponderInfoId) {
		this.kaizalaResponderInfoId = kaizalaResponderInfoId;
	}
	@Column(name="mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="location_level")
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	@Column(name="location_level_value")
	public Long getLocationLevelValue() {
		return locationLevelValue;
	}
	public void setLocationLevelValue(Long locationLevelValue) {
		this.locationLevelValue = locationLevelValue;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	
	
}
