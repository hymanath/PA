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
@Table(name="party_meeting_minute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingMinute extends BaseModel implements Serializable{

	private static final long serialVersionUID = 4980537130444290834L;
	
	private Long partyMeetingMinuteId;
	private PartyMeeting partyMeeting;
	private String minutePoint;
	private User insertedBy;
	private User updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private Long partyMeetingId;
	private Long insertedById;
	private Long updatedById;
	private String isDeleted;
	
	private String isActionable;
	private Long locationLevel;
	private Long locationValue;
	private Long statusId;
	
	private UserAddress userAddress;
	private Long userAddressId;
	
	private PartyMeetingMinuteStatus partyMeetingMinuteStatus;
	private Long momAtrSourceTypeId;
	
	
	public PartyMeetingMinute(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_minute_id", unique=true, nullable=false)
	public Long getPartyMeetingMinuteId() {
		return partyMeetingMinuteId;
	}

	public void setPartyMeetingMinuteId(Long partyMeetingMinuteId) {
		this.partyMeetingMinuteId = partyMeetingMinuteId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeeting getPartyMeeting() {
		return partyMeeting;
	}

	public void setPartyMeeting(PartyMeeting partyMeeting) {
		this.partyMeeting = partyMeeting;
	}

	@Column(name="minute_point")
	public String getMinutePoint() {
		return minutePoint;
	}

	public void setMinutePoint(String minutePoint) {
		this.minutePoint = minutePoint;
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

	@Column(name="party_meeting_id")
	public Long getPartyMeetingId() {
		return partyMeetingId;
	}

	public void setPartyMeetingId(Long partyMeetingId) {
		this.partyMeetingId = partyMeetingId;
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

	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name="is_actionable")
	public String getIsActionable() {
		return isActionable;
	}

	public void setIsActionable(String isActionable) {
		this.isActionable = isActionable;
	}

	@Column(name="location_level")
	public Long getLocationLevel() {
		return locationLevel;
	}

	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}

	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	@Column(name="status_id")
	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="status_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingMinuteStatus getPartyMeetingMinuteStatus() {
		return partyMeetingMinuteStatus;
	}

	public void setPartyMeetingMinuteStatus(
			PartyMeetingMinuteStatus partyMeetingMinuteStatus) {
		this.partyMeetingMinuteStatus = partyMeetingMinuteStatus;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name="user_address_id")
	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}

	@Column(name="mom_atr_source_type_id")
	public Long getMomAtrSourceTypeId() {
		return momAtrSourceTypeId;
	}

	public void setMomAtrSourceTypeId(Long momAtrSourceTypeId) {
		this.momAtrSourceTypeId = momAtrSourceTypeId;
	}
}
