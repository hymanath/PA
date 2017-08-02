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
@Table(name="party_meeting_minute_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingMinuteHistory extends BaseModel implements Serializable{

	private static final long serialVersionUID = -8203404044593658539L;
	
	private Long partyMeetingMinuteHistoryId;
	private PartyMeetingMinute partyMeetingMinute;
	private PartyMeeting partyMeeting;
	private String minutePoint;
	private User insertedBy;
	private User updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	private Long partyMeetingMinuteId;
	private Long partyMeetingId;
	private Long insertedById;
	private Long updatedById;
	private Long momAtrSourceTypeId;
	
	public PartyMeetingMinuteHistory(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_minute_history_id", unique=true, nullable=false)
	public Long getPartyMeetingMinuteHistoryId() {
		return partyMeetingMinuteHistoryId;
	}

	public void setPartyMeetingMinuteHistoryId(Long partyMeetingMinuteHistoryId) {
		this.partyMeetingMinuteHistoryId = partyMeetingMinuteHistoryId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_minute_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingMinute getPartyMeetingMinute() {
		return partyMeetingMinute;
	}

	public void setPartyMeetingMinute(PartyMeetingMinute partyMeetingMinute) {
		this.partyMeetingMinute = partyMeetingMinute;
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

	@Column(name="party_meeting_minute_id")
	public Long getPartyMeetingMinuteId() {
		return partyMeetingMinuteId;
	}

	public void setPartyMeetingMinuteId(Long partyMeetingMinuteId) {
		this.partyMeetingMinuteId = partyMeetingMinuteId;
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

	@Column(name="mom_atr_source_type_id")
	public Long getMomAtrSourceTypeId() {
		return momAtrSourceTypeId;
	}

	public void setMomAtrSourceTypeId(Long momAtrSourceTypeId) {
		this.momAtrSourceTypeId = momAtrSourceTypeId;
	}
}
