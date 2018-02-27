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
@Table(name="biometric_device_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BiometricDeviceLog extends BaseModel implements Serializable{

	private static final long serialVersionUID = 4646275183106427484L;
	
	private Long biometricDeviceLogId;
	private BiometricDeviceLogTrack biometricDeviceLogTrack;
	private Long deviceLogId;
	private Long deviceId;
	private String employeeCode;
	private Date logTime;
	private String logMode;
	private Date insertedTime;
	
	public BiometricDeviceLog(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="biometric_device_log_id", unique=true, nullable=false)
	public Long getBiometricDeviceLogId() {
		return biometricDeviceLogId;
	}

	public void setBiometricDeviceLogId(Long biometricDeviceLogId) {
		this.biometricDeviceLogId = biometricDeviceLogId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="biometric_device_log_track_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BiometricDeviceLogTrack getBiometricDeviceLogTrack() {
		return biometricDeviceLogTrack;
	}

	public void setBiometricDeviceLogTrack(
			BiometricDeviceLogTrack biometricDeviceLogTrack) {
		this.biometricDeviceLogTrack = biometricDeviceLogTrack;
	}

	@Column(name="device_log_id")
	public Long getDeviceLogId() {
		return deviceLogId;
	}

	public void setDeviceLogId(Long deviceLogId) {
		this.deviceLogId = deviceLogId;
	}

	@Column(name="device_id")
	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name="employee_code")
	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	@Column(name="log_time")
	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@Column(name="log_mode")
	public String getLogMode() {
		return logMode;
	}

	public void setLogMode(String logMode) {
		this.logMode = logMode;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
}
