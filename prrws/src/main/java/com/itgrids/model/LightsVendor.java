package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "lights_vendor")
public class LightsVendor {

	private Long lightsVendorId;
	private String vendorName;
	
	@Id
	@Column(name="lights_vendor_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLightsVendorId() {
		return lightsVendorId;
	}
	public void setLightsVendorId(Long lightsVendorId) {
		this.lightsVendorId = lightsVendorId;
	}
	@Column(name="vendor_name")
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	
}
