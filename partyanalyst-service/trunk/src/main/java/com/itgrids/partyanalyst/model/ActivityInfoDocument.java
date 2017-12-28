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
@Table(name="activity_info_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityInfoDocument extends BaseModel implements Serializable{
	
	private Long activityInfoDocumentId;
	private Long locationScopeId;
	private Long locationValueAddress;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private Long activityDocumentId;
	
	private ActivityDocument activityDocument;
	private User insertedUser;
	private User updatedUser;
	private Long day;
	private UserAddress userAddress;
	
	private String isDeleted;
	private String insertType;
	private Long activityLocationInfoId;
	private ActivityLocationInfo activityLocationInfo;
	private Long activityAddressId;
	private Long activityConductedInfoId;
	private ActivityConductedInfo activityConductedInfo;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_info_document_id", unique = true, nullable = false)
	public Long getActivityInfoDocumentId() {
		return activityInfoDocumentId;
	}
	public void setActivityInfoDocumentId(Long activityInfoDocumentId) {
		this.activityInfoDocumentId = activityInfoDocumentId;
	}
	
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	
	@Column(name = "location_value_address")
	public Long getLocationValueAddress() {
		return locationValueAddress;
	}
	public void setLocationValueAddress(Long locationValueAddress) {
		this.locationValueAddress = locationValueAddress;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_document_id",insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityDocument getActivityDocument() {
		return activityDocument;
	}
	public void setActivityDocument(ActivityDocument activityDocument) {
		this.activityDocument = activityDocument;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	@Column(name = "day")
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_address_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "insert_type")
	public String getInsertType() {
		return insertType;
	}
	public void setInsertType(String insertType) {
		this.insertType = insertType;
	}
	@Column(name = "activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_location_info_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}
	public void setActivityLocationInfo(ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
	}
	@Column(name = "activity_address_id")
	public Long getActivityAddressId() {
		return activityAddressId;
	}
	public void setActivityAddressId(Long activityAddressId) {
		this.activityAddressId = activityAddressId;
	}
	
	@Column(name = "activity_conducted_info_id")
	public Long getActivityConductedInfoId() {
		return activityConductedInfoId;
	}
	public void setActivityConductedInfoId(Long activityConductedInfoId) {
		this.activityConductedInfoId = activityConductedInfoId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_conducted_info_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityConductedInfo getActivityConductedInfo() {
		return activityConductedInfo;
	}
	public void setActivityConductedInfo(ActivityConductedInfo activityConductedInfo) {
		this.activityConductedInfo = activityConductedInfo;
	}
	@Column(name = "activity_document_id")
	public Long getActivityDocumentId() {
		return activityDocumentId;
	}
	public void setActivityDocumentId(Long activityDocumentId) {
		this.activityDocumentId = activityDocumentId;
	}
	
	
	
}
