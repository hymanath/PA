package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="biometric_device_log_track")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BiometricDeviceLogTrack extends BaseModel implements Serializable{

	private static final long serialVersionUID = 4576833370163647143L;
	
	private Long biometricDeviceLogTrackId;
	private Date startTime;
	private Date endTime;
	private Long recordsDownloaded;
	private String status;
	
	public BiometricDeviceLogTrack(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="biometric_device_log_track_id", unique=true, nullable=false)
	public Long getBiometricDeviceLogTrackId() {
		return biometricDeviceLogTrackId;
	}

	public void setBiometricDeviceLogTrackId(Long biometricDeviceLogTrackId) {
		this.biometricDeviceLogTrackId = biometricDeviceLogTrackId;
	}

	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="records_downloaded")
	public Long getRecordsDownloaded() {
		return recordsDownloaded;
	}

	public void setRecordsDownloaded(Long recordsDownloaded) {
		this.recordsDownloaded = recordsDownloaded;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
