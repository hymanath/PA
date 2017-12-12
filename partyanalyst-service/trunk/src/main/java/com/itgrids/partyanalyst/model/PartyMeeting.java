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
@Table(name="party_meeting")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeeting extends BaseModel implements Serializable{

	private static final long serialVersionUID = -5885376341735731134L;
	
	private Long partyMeetingId;
	private String meetingName;
	private PartyMeetingType partyMeetingType;
	private PartyMeetingLevel partyMeetingLevel;
	private Long locationValue;
	private UserAddress meetingAddress;
	private PartyMeetingOccurrence partyMeetingOccurrence;
	private Date startDate;
	private Date endDate;
	private String dailyStartTime;
	private String dailyEndTime;
	private Date startTime;
	private Date endTime;
	private User insertedBy;
	private User updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	private Long partyMeetingTypeId;
	private Long partyMeetingLevelId;
	private Long meetingAddressId;
	private Long partyMeetingOccurrenceId;
	private Long insertedById;
	private Long updatedById;
	private String isActive;
	
	private String isConducted;
	private Date conductedDate;
	private String remarks;
	private String isConductedByIvr;
	
	private String thirdPartyStatus;
	private String attendanceEnrolmentYear;
	private Long appUserId;
	
	public PartyMeeting(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_id", unique=true, nullable=false)
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}
	
	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
	}
	
	@Column(name="meeting_name")
	public String getMeetingName() {
		return meetingName;
	}
	
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_type_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingType getPartyMeetingType() {
		return partyMeetingType;
	}
	
	public void setPartyMeetingType(PartyMeetingType partyMeetingType) {
		this.partyMeetingType = partyMeetingType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingLevel getPartyMeetingLevel() {
		return partyMeetingLevel;
	}
	
	public void setPartyMeetingLevel(PartyMeetingLevel partyMeetingLevel) {
		this.partyMeetingLevel = partyMeetingLevel;
	}
	
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meeting_address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getMeetingAddress() {
		return meetingAddress;
	}
	
	public void setMeetingAddress(UserAddress meetingAddress) {
		this.meetingAddress = meetingAddress;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_occurrence_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingOccurrence getPartyMeetingOccurrence() {
		return partyMeetingOccurrence;
	}
	
	public void setPartyMeetingOccurrence(
			PartyMeetingOccurrence partyMeetingOccurrence) {
		this.partyMeetingOccurrence = partyMeetingOccurrence;
	}
	
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name="daily_start_time")
	public String getDailyStartTime() {
		return dailyStartTime;
	}
	
	public void setDailyStartTime(String dailyStartTime) {
		this.dailyStartTime = dailyStartTime;
	}
	
	@Column(name="daily_end_time")
	public String getDailyEndTime() {
		return dailyEndTime;
	}
	
	public void setDailyEndTime(String dailyEndTime) {
		this.dailyEndTime = dailyEndTime;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}
	
	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name="party_meeting_type_id")
	public Long getPartyMeetingTypeId() {
		return partyMeetingTypeId;
	}
	
	public void setPartyMeetingTypeId(Long partyMeetingTypeId) {
		this.partyMeetingTypeId = partyMeetingTypeId;
	}
	
	@Column(name="party_meeting_level_id")
	public Long getPartyMeetingLevelId() {
		return partyMeetingLevelId;
	}
	
	public void setPartyMeetingLevelId(Long partyMeetingLevelId) {
		this.partyMeetingLevelId = partyMeetingLevelId;
	}
	
	@Column(name="meeting_address_id")
	public Long getMeetingAddressId() {
		return meetingAddressId;
	}
	
	public void setMeetingAddressId(Long meetingAddressId) {
		this.meetingAddressId = meetingAddressId;
	}
	
	@Column(name="party_meeting_occurrence_id")
	public Long getPartyMeetingOccurrenceId() {
		return partyMeetingOccurrenceId;
	}
	
	public void setPartyMeetingOccurrenceId(Long partyMeetingOccurrenceId) {
		this.partyMeetingOccurrenceId = partyMeetingOccurrenceId;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedById() {
		return insertedById;
	}
	
	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedById() {
		return updatedById;
	}
	
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	@Column(name="is_conducted")
	public String getIsConducted() {
		return isConducted;
	}

	public void setIsConducted(String isConducted) {
		this.isConducted = isConducted;
	}

	@Column(name="conducted_date")
	public Date getConductedDate() {
		return conductedDate;
	}

	public void setConductedDate(Date conductedDate) {
		this.conductedDate = conductedDate;
	}

	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="third_party_status")
	public String getThirdPartyStatus() {
		return thirdPartyStatus;
	}

	public void setThirdPartyStatus(String thirdPartyStatus) {
		this.thirdPartyStatus = thirdPartyStatus;
	}
	@Column(name="is_conducted_by_ivr")
	public String getIsConductedByIvr() {
		return isConductedByIvr;
	}

	public void setIsConductedByIvr(String isConductedByIvr) {
		this.isConductedByIvr = isConductedByIvr;
	}

	@Column(name="attendance_enrolment_year")
	public String getAttendanceEnrolmentYear() {
		return attendanceEnrolmentYear;
	}

	public void setAttendanceEnrolmentYear(String attendanceEnrolmentYear) {
		this.attendanceEnrolmentYear = attendanceEnrolmentYear;
	}
	@Column(name="app_user_id")
	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}
	
	
	
}
