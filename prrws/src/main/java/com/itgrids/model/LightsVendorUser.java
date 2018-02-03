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
@Table(name = "lights_vendor_user")
public class LightsVendorUser extends BaseModel implements Serializable{

	private Long lightsVendorUserId;
	private Long lightsVendorId;
	private Long userId;
	private String isDeleted;
	
	private LightsVendor lightsVendor;
	private User user;
	
	@Id
	@Column(name="lights_vendor_user_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLightsVendorUserId() {
		return lightsVendorUserId;
	}
	public void setLightsVendorUserId(Long lightsVendorUserId) {
		this.lightsVendorUserId = lightsVendorUserId;
	}
	
	@Column(name="lights_vendor_id")
	public Long getLightsVendorId() {
		return lightsVendorId;
	}
	public void setLightsVendorId(Long lightsVendorId) {
		this.lightsVendorId = lightsVendorId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "lights_vendor_id", insertable = false, updatable = false)
	public LightsVendor getLightsVendor() {
		return lightsVendor;
	}
	public void setLightsVendor(LightsVendor lightsVendor) {
		this.lightsVendor = lightsVendor;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
