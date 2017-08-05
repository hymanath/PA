package com.itgrids.model;

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
@Table(name = "light_wattage_info")
public class LightWattage {
	
	private Long lightWattageInfoId;
	private  Long wattage;
	private Long lightCount;
	private Date insertedTime;
	private String isDeleted; 
	
	private LightMonitoring lightMonitoring ;
	private Long lightMonitoringId;	
	
	
	@Id
	@Column(name="light_wattage_info_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getLightWattageInfoId() {
		return lightWattageInfoId;
	}
	public void setLightWattageInfoId(Long lightWattageInfoId) {
		this.lightWattageInfoId = lightWattageInfoId;
	}
	
	@Column(name="light_count")
	public Long getLightCount() {
		return lightCount;
	}
	@Column(name="wattege")
	public Long getWattage() {
		return wattage;
	}
	public void setWattage(Long wattage) {
		this.wattage = wattage;
	}
	public void setLightCount(Long lightCount) {
		this.lightCount = lightCount;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "light_monitoring_id",insertable = false, updatable = false)
	public LightMonitoring getLightMonitoring() {
		return lightMonitoring;
	}
	public void setLightMonitoring(LightMonitoring lightMonitoring) {
		this.lightMonitoring = lightMonitoring;
	}
	@Column(name="light_monitoring_id")
	public Long getLightMonitoringId() {
		return lightMonitoringId;
	}
	public void setLightMonitoringId(Long lightMonitoringId) {
		this.lightMonitoringId = lightMonitoringId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
