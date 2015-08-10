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
@Table(name="attendance_error")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AttendanceError extends BaseModel implements Serializable{

	private static final long serialVersionUID = -8717404393498758241L;

	private Long attendanceErrorId;
	private Long partMeetingId;
	private Long trainingCampScheduleId;
	private Long trainingCampBatchId;
	private Long trainingCampTopicId;
	private Date attendedTime;
	private String rfid;
	private String imei;
	private String uniqueKey;
	private Date insertedTime;
	private String latitude;
	private String longitude;
	private Long tabUserId;
	private Long currentTabUserId;
	private String syncSource;
	private String errorDescription;
	private String membershopId;
	private Long tdpCadreId;
	
	public AttendanceError(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="attendance_error_id", unique=true, nullable=false)
	public Long getAttendanceErrorId() {
		return attendanceErrorId;
	}

	public void setAttendanceErrorId(Long attendanceErrorId) {
		this.attendanceErrorId = attendanceErrorId;
	}

	@Column(name="party_meeting_id")
	public Long getPartMeetingId() {
		return partMeetingId;
	}

	public void setPartMeetingId(Long partMeetingId) {
		this.partMeetingId = partMeetingId;
	}

	@Column(name="training_camp_schedule_id")
	public Long getTrainingCampScheduleId() {
		return trainingCampScheduleId;
	}

	public void setTrainingCampScheduleId(Long trainingCampScheduleId) {
		this.trainingCampScheduleId = trainingCampScheduleId;
	}

	@Column(name="training_camp_batch_id")
	public Long getTrainingCampBatchId() {
		return trainingCampBatchId;
	}

	public void setTrainingCampBatchId(Long trainingCampBatchId) {
		this.trainingCampBatchId = trainingCampBatchId;
	}

	@Column(name="training_camp_topic_id")
	public Long getTrainingCampTopicId() {
		return trainingCampTopicId;
	}

	public void setTrainingCampTopicId(Long trainingCampTopicId) {
		this.trainingCampTopicId = trainingCampTopicId;
	}

	@Column(name="attended_time")
	public Date getAttendedTime() {
		return attendedTime;
	}

	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
	}

	@Column(name="rfid")
	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	@Column(name="imei")
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	@Column(name="unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name="tab_user_id")
	public Long getTabUserId() {
		return tabUserId;
	}

	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}

	@Column(name="current_tab_user_id")
	public Long getCurrentTabUserId() {
		return currentTabUserId;
	}

	public void setCurrentTabUserId(Long currentTabUserId) {
		this.currentTabUserId = currentTabUserId;
	}

	@Column(name="sync_source")
	public String getSyncSource() {
		return syncSource;
	}

	public void setSyncSource(String syncSource) {
		this.syncSource = syncSource;
	}

	@Column(name="error_description")
	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	@Column(name="membership_id")
	public String getMembershopId() {
		return membershopId;
	}

	public void setMembershopId(String membershopId) {
		this.membershopId = membershopId;
	}

	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}

	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
}
