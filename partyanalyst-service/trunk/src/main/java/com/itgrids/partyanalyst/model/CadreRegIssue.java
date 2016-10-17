package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "cadre_reg_issue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegIssue extends BaseModel implements Serializable {
	
	
	private Long cadreRegIssueId;
	private Long cadreRegIssueTypeId;
	private Long cadreSurveyUserId;
	private Long tabUserInfoId;
	private String description;
	private Long locationScopeId;
	private Long locationValue;
	private Long userAddressId;
    private Long createdBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	
	private CadreRegIssueType cadreRegIssueType;
	private CadreSurveyUser cadreSurveyUser;
	private TabUserInfo tabUserInfo;
	private UserAddress userAddress;
	private CadreRegUser insertedUser;
	private CadreRegUser updatedUser;
	private RegionScopes regionScope;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_issue_id", unique = true, nullable = false)
	public Long getCadreRegIssueId() {
		return cadreRegIssueId;
	}
	public void setCadreRegIssueId(Long cadreRegIssueId) {
		this.cadreRegIssueId = cadreRegIssueId;
	}
	@Column(name="cadre_reg_issue_type_id")
	public Long getCadreRegIssueTypeId() {
		return cadreRegIssueTypeId;
	}
	public void setCadreRegIssueTypeId(Long cadreRegIssueTypeId) {
		this.cadreRegIssueTypeId = cadreRegIssueTypeId;
	}
	@Column(name="cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	@Column(name="tab_user_info_id")
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name="user_address_id")
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	@Column(name="created_by")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
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
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_reg_issue_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegIssueType getCadreRegIssueType() {
		return cadreRegIssueType;
	}
	public void setCadreRegIssueType(CadreRegIssueType cadreRegIssueType) {
		this.cadreRegIssueType = cadreRegIssueType;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_survey_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}
	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tab_user_info_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TabUserInfo getTabUserInfo() {
		return tabUserInfo;
	}
	public void setTabUserInfo(TabUserInfo tabUserInfo) {
		this.tabUserInfo = tabUserInfo;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_address_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "created_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegUser getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(CadreRegUser insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "updated_by" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegUser getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(CadreRegUser updatedUser) {
		this.updatedUser = updatedUser;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "location_scope_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScope() {
		return regionScope;
	}
	public void setRegionScope(RegionScopes regionScope) {
		this.regionScope = regionScope;
	}
	
	
	

	
}
