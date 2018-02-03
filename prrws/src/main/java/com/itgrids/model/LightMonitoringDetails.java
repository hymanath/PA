package com.itgrids.model;

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

@Entity
@Table(name = "light_monitoring_details")
public class LightMonitoringDetails extends BaseModel implements Serializable{
	
	private Long lightMonitoringDetailsId;
	private Long lightVendorId;
	private Long panchayatId;
	private Date workDate;
	private Long lightsFitted;
	private Long teamWorked;
	private Long insertedBy;
	private Date insertedTime;
	private Long updatedBy;
	private Date updatedTime;
	
	private LightsVendor lightsVendor;
	private Panchayat panchayat;
	
	@Id
	@Column(name="light_monitoring_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLightMonitoringDetailsId() {
		return lightMonitoringDetailsId;
	}
	public void setLightMonitoringDetailsId(Long lightMonitoringDetailsId) {
		this.lightMonitoringDetailsId = lightMonitoringDetailsId;
	}
	
	@Column(name="light_vendor_id")
	public Long getLightVendorId() {
		return lightVendorId;
	}
	public void setLightVendorId(Long lightVendorId) {
		this.lightVendorId = lightVendorId;
	}
	
	@Column(name="panchayat_id")
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	
	@Column(name="work_date")
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	
	@Column(name="lights_fitted")
	public Long getLightsFitted() {
		return lightsFitted;
	}
	public void setLightsFitted(Long lightsFitted) {
		this.lightsFitted = lightsFitted;
	}
	
	@Column(name="team_worked")
	public Long getTeamWorked() {
		return teamWorked;
	}
	public void setTeamWorked(Long teamWorked) {
		this.teamWorked = teamWorked;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "light_vendor_id", insertable = false, updatable = false)
	public LightsVendor getLightsVendor() {
		return lightsVendor;
	}
	public void setLightsVendor(LightsVendor lightsVendor) {
		this.lightsVendor = lightsVendor;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "panchayat_id", insertable = false, updatable = false)
	public Panchayat getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(Panchayat panchayat) {
		this.panchayat = panchayat;
	}
}
