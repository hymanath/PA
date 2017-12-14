package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "light_installation_target")
public class LightInstallationTarget {

	private Long lightInstallationTargetId;
	private Date targetDate; 
	private Long lightsVendorId;
	private Long ledTarget;
	private Long ccmsBoxTarget;
	
	@Id
	@Column(name="light_installation_target_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLightInstallationTargetId() {
		return lightInstallationTargetId;
	}
	public void setLightInstallationTargetId(Long lightInstallationTargetId) {
		this.lightInstallationTargetId = lightInstallationTargetId;
	}
	@Column(name="target_date")
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	@Column(name="lights_vendor_id")
	public Long getLightsVendorId() {
		return lightsVendorId;
	}
	public void setLightsVendorId(Long lightsVendorId) {
		this.lightsVendorId = lightsVendorId;
	}
	@Column(name="led_target")
	public Long getLedTarget() {
		return ledTarget;
	}
	public void setLedTarget(Long ledTarget) {
		this.ledTarget = ledTarget;
	}
	@Column(name="ccms_box_target")
	public Long getCcmsBoxTarget() {
		return ccmsBoxTarget;
	}
	public void setCcmsBoxTarget(Long ccmsBoxTarget) {
		this.ccmsBoxTarget = ccmsBoxTarget;
	}
	
	
}
