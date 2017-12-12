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
	private MomAtrSourceType momAtrSourceType;
	
	private Long locationScopeId;
	private Long locationScopeValue;
	private Long loactionAddressId;
	private Long createdLocationScopeId; 
	private Long createdLocationValue;
	private Long createdAddressId;
	private Long assignedLocationScopeId;
	private Long assignedLocationValue;
	private Long assignedAddressId;
	private Long momPriorityId;
	private Long itdpAppUserId;
	
	private RegionScopes locationScope;
	private UserAddress locationAddress;
	private MomAppUserAccess createdLocationScope;
	private RegionScopes assignedLocationScope;
	private UserAddress createdAddress;
	private UserAddress assignedAddress;
	private MomPriority momPriority;
	
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mom_atr_source_type_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MomAtrSourceType getMomAtrSourceType() {
		return momAtrSourceType;
	}

	public void setMomAtrSourceType(MomAtrSourceType momAtrSourceType) {
		this.momAtrSourceType = momAtrSourceType;
	}

	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}

	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name="location_scope_value")
	public Long getLocationScopeValue() {
		return locationScopeValue;
	}

	public void setLocationScopeValue(Long locationScopeValue) {
		this.locationScopeValue = locationScopeValue;
	}
	@Column(name="loaction_address_id")
	public Long getLoactionAddressId() {
		return loactionAddressId;
	}

	public void setLoactionAddressId(Long loactionAddressId) {
		this.loactionAddressId = loactionAddressId;
	}
	@Column(name="itdp_app_user_id")
	public Long getItdpAppUserId() {
		return itdpAppUserId;
	}

	public void setItdpAppUserId(Long itdpAppUserId) {
		this.itdpAppUserId = itdpAppUserId;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="loaction_address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(UserAddress locationAddress) {
		this.locationAddress = locationAddress;
	}

	@Column(name="created_location_scope_id")
	public Long getCreatedLocationScopeId() {
		return createdLocationScopeId;
	}

	public void setCreatedLocationScopeId(Long createdLocationScopeId) {
		this.createdLocationScopeId = createdLocationScopeId;
	}
	@Column(name="created_location_value")
	public Long getCreatedLocationValue() {
		return createdLocationValue;
	}

	public void setCreatedLocationValue(Long createdLocationValue) {
		this.createdLocationValue = createdLocationValue;
	}
	@Column(name="created_address_id")
	public Long getCreatedAddressId() {
		return createdAddressId;
	}

	public void setCreatedAddressId(Long createdAddressId) {
		this.createdAddressId = createdAddressId;
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
	@JoinColumn(name="assigned_location_scope_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getAssignedLocationScope() {
		return assignedLocationScope;
	}

	public void setAssignedLocationScope(RegionScopes assignedLocationScope) {
		this.assignedLocationScope = assignedLocationScope;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="created_address_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getCreatedAddress() {
		return createdAddress;
	}

	public void setCreatedAddress(UserAddress createdAddress) {
		this.createdAddress = createdAddress;
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
    @Column(name = "mom_priority_id")
	public Long getMomPriorityId() {
		return momPriorityId;
	}

	public void setMomPriorityId(Long momPriorityId) {
		this.momPriorityId = momPriorityId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mom_priority_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MomPriority getMomPriority() {
		return momPriority;
	}

	public void setMomPriority(MomPriority momPriority) {
		this.momPriority = momPriority;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="created_location_scope_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MomAppUserAccess getCreatedLocationScope() {
		return createdLocationScope;
	}

	public void setCreatedLocationScope(MomAppUserAccess createdLocationScope) {
		this.createdLocationScope = createdLocationScope;
	}
	
	
}
