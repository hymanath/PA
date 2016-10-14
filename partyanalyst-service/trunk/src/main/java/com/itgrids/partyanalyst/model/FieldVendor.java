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
@Table(name = "field_vendor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FieldVendor extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -8895767560013537649L;
	
	private Long fieldVendorId;
	private String vendorName;
	private String isActive;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "field_vendor_id", unique = true, nullable = false)
	public Long getFieldVendorId() {
		return fieldVendorId;
	}
	public void setFieldVendorId(Long fieldVendorId) {
		this.fieldVendorId = fieldVendorId;
	}
	
	@Column(name="vendor_name")
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
	
}
