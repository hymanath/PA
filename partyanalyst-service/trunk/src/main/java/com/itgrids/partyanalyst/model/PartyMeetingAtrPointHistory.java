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
@Table(name="party_meeting_atr_point_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingAtrPointHistory extends BaseModel implements Serializable{

	private static final long serialVersionUID = 8654577596618386827L;
	
	private Long partyMeetingAtrPointHistoryId;
	private PartyMeetingAtrPoint partyMeetingAtrPoint;
	private PartyMeeting partyMeeting;
	private String request;
	private String actionTaken;
	private String requestFrom;
	private RegionScopes locationScope;
	private Long locationValue;
	private UserAddress address;
	private String raisedBy;
	private User insertedBy;
	private User updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	private Long partyMeetingId;
	private Long locationScopeId;
	private Long addressId;
	private Long insertedById;
	private Long updatedById;
	private Long partyMeetingAtrPointId;
	
	public PartyMeetingAtrPointHistory(){}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_atr_point_history_id", unique=true, nullable=false)
	public Long getPartyMeetingAtrPointHistoryId() {
		return partyMeetingAtrPointHistoryId;
	}

	public void setPartyMeetingAtrPointHistoryId(Long partyMeetingAtrPointHistoryId) {
		this.partyMeetingAtrPointHistoryId = partyMeetingAtrPointHistoryId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_meeting_atr_point_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingAtrPoint getPartyMeetingAtrPoint() {
		return partyMeetingAtrPoint;
	}

	public void setPartyMeetingAtrPoint(PartyMeetingAtrPoint partyMeetingAtrPoint) {
		this.partyMeetingAtrPoint = partyMeetingAtrPoint;
	}

	@Column(name="party_meeting_atr_point_id")
	public Long getPartyMeetingAtrPointId() {
		return partyMeetingAtrPointId;
	}

	public void setPartyMeetingAtrPointId(Long partyMeetingAtrPointId) {
		this.partyMeetingAtrPointId = partyMeetingAtrPointId;
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

	@Column(name="request")
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	@Column(name="action_taken")
	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}

	@Column(name="request_from")
	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_scope_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getLocationScope() {
		return locationScope;
	}

	public void setLocationScope(RegionScopes locationScope) {
		this.locationScope = locationScope;
	}

	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}

	public void setAddress(UserAddress address) {
		this.address = address;
	}

	@Column(name="raised_by")
	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
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

	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}

	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}

	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
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
}
