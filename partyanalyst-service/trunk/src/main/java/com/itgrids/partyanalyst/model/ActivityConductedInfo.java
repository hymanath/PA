package com.itgrids.partyanalyst.model;

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
@Table(name = "activity_conducted_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityConductedInfo implements java.io.Serializable{
	
	
	private Long activityConductedInfoId;
	private Long activityScopeId;
	private Long addressId;
	private Long infoCellCount;
	private Long ivrCount;
	private String ivrStatus;
	private String isDeleted;
	private Date insertedTime;
	private Date updatedTime;;
	private Long insertedBy;
	private Long updatedBy;
	private Date plannedDate;
	private Date conductedDate;
	private String updatedStatus;
	private String sourceType;
	private String tabUpdatedStatus;
	private Long tabDetailsId;
	
	private ActivityScope activityScope;
	private UserAddress address;
	private User insertedUser;
	private User updatedUser;
	private TabDetails tabDetails;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="activity_conducted_info_id" , unique=true, nullable=false)
	public Long getActivityConductedInfoId() {
		return activityConductedInfoId;
	}
	public void setActivityConductedInfoId(Long activityConductedInfoId) {
		this.activityConductedInfoId = activityConductedInfoId;
	}
	
	@Column(name="activity_scope_id")
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@Column(name="info_cell_count")
	public Long getInfoCellCount() {
		return infoCellCount;
	}
	public void setInfoCellCount(Long infoCellCount) {
		this.infoCellCount = infoCellCount;
	}
	
	@Column(name="ivr_count")
	public Long getIvrCount() {
		return ivrCount;
	}
	public void setIvrCount(Long ivrCount) {
		this.ivrCount = ivrCount;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_scope_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public ActivityScope getActivityScope() {
		return activityScope;
	}
	public void setActivityScope(ActivityScope activityScope) {
		this.activityScope = activityScope;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public UserAddress getAddress() {
		return address;
	}
	public void setAddress(UserAddress address) {
		this.address = address;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_by", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	@Column(name="ivr_status")
	public String getIvrStatus() {
		return ivrStatus;
	}
	public void setIvrStatus(String ivrStatus) {
		this.ivrStatus = ivrStatus;
	}
	
	@Column(name="planned_date")
	public Date getPlannedDate() {
		return plannedDate;
	}
	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}
	
	@Column(name="conducted_date")
	public Date getConductedDate() {
		return conductedDate;
	}
	public void setConductedDate(Date conductedDate) {
		this.conductedDate = conductedDate;
	}
	
	@Column(name="updated_status")
	public String getUpdatedStatus() {
		return updatedStatus;
	}
	public void setUpdatedStatus(String updatedStatus) {
		this.updatedStatus = updatedStatus;
	}
	@Column(name = "source_type")
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	@Column(name = "tab_updated_status")
	public String getTabUpdatedStatus() {
		return tabUpdatedStatus;
	}
	public void setTabUpdatedStatus(String tabUpdatedStatus) {
		this.tabUpdatedStatus = tabUpdatedStatus;
	}
	@Column(name = "tab_details_id")
	public Long getTabDetailsId() {
		return tabDetailsId;
	}
	public void setTabDetailsId(Long tabDetailsId) {
		this.tabDetailsId = tabDetailsId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "tab_details_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public TabDetails getTabDetails() {
		return tabDetails;
	}
	public void setTabDetails(TabDetails tabDetails) {
		this.tabDetails = tabDetails;
	}
	
			
}
