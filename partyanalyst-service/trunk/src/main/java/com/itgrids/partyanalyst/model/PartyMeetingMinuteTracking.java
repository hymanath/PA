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
@Table(name="party_meeting_minute_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingMinuteTracking extends BaseModel implements Serializable {
	
	private Long partyMeetingMinuteTrackingId;
	private Long partyMeetingMinuteId;
	private Long partyMeetingMinuteStatusId;
	
	private PartyMeetingMinute partyMeetingMinute;
	private PartyMeetingMinuteStatus partyMeetingMinuteStatus;
	
	private String comment;
	private Long partyMeetingoDcumentId;
	private Long assignedLocationScopeId;
	private Long assignedLocationValue;
	private Long assignedAddressId;
	private Long itdpAppUserId;
	private Date insertedTime;
	
	private PartyMeetingDocument partyMeetingDocument;
	private UserAddress assignedAddress;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_minute_tracking_id", unique=true, nullable=false)
	public Long getPartyMeetingMinuteTrackingId() {
		return partyMeetingMinuteTrackingId;
	}
	public void setPartyMeetingMinuteTrackingId(Long partyMeetingMinuteTrackingId) {
		this.partyMeetingMinuteTrackingId = partyMeetingMinuteTrackingId;
	}
	
	@Column(name="party_meeting_minute_id")
	public Long getPartyMeetingMinuteId() {
		return partyMeetingMinuteId;
	}
	public void setPartyMeetingMinuteId(Long partyMeetingMinuteId) {
		this.partyMeetingMinuteId = partyMeetingMinuteId;
	}
	
	@Column(name="party_meeting_minute_status_id")
	public Long getPartyMeetingMinuteStatusId() {
		return partyMeetingMinuteStatusId;
	}
	public void setPartyMeetingMinuteStatusId(Long partyMeetingMinuteStatusId) {
		this.partyMeetingMinuteStatusId = partyMeetingMinuteStatusId;
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
	@JoinColumn(name="party_meeting_minute_status_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingMinuteStatus getPartyMeetingMinuteStatus() {
		return partyMeetingMinuteStatus;
	}
	public void setPartyMeetingMinuteStatus(
			PartyMeetingMinuteStatus partyMeetingMinuteStatus) {
		this.partyMeetingMinuteStatus = partyMeetingMinuteStatus;
	}
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Column(name="party_meeting_document_id")
	public Long getPartyMeetingoDcumentId() {
		return partyMeetingoDcumentId;
	}
	public void setPartyMeetingoDcumentId(Long partyMeetingoDcumentId) {
		this.partyMeetingoDcumentId = partyMeetingoDcumentId;
	}
	@Column(name="itdp_app_user_id")
	public Long getItdpAppUserId() {
		return itdpAppUserId;
	}
	public void setItdpAppUserId(Long itdpAppUserId) {
		this.itdpAppUserId = itdpAppUserId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_document_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingDocument getPartyMeetingDocument() {
		return partyMeetingDocument;
	}
	public void setPartyMeetingDocument(PartyMeetingDocument partyMeetingDocument) {
		this.partyMeetingDocument = partyMeetingDocument;
	}
    @Column(name = "assigned_location_scope_id")
	public Long getAssignedLocationScopeId() {
		return assignedLocationScopeId;
	}

	public void setAssignedLocationScopeId(Long assignedLocationScopeId) {
		this.assignedLocationScopeId = assignedLocationScopeId;
	}
    @Column(name = "assigned_location_value")
	public Long getAssignedLocationValue() {
		return assignedLocationValue;
	}

	public void setAssignedLocationValue(Long assignedLocationValue) {
		this.assignedLocationValue = assignedLocationValue;
	}
    @Column(name = "assigned_address_id")
	public Long getAssignedAddressId() {
		return assignedAddressId;
	}

	public void setAssignedAddressId(Long assignedAddressId) {
		this.assignedAddressId = assignedAddressId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="assigned_address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAssignedAddress() {
		return assignedAddress;
	}
	public void setAssignedAddress(UserAddress assignedAddress) {
		this.assignedAddress = assignedAddress;
	}

	
}
