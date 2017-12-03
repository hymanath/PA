package com.itgrids.model;

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

/**
 * @author sys
 *
 */
@Entity
@Table(name = "petition_sub_work_location_details")
public class PetitionSubWorkLocationDetails {

	private Long petitionSubWorkLocationDetailsId;
	private Long petitionWorkDetailsId ;
	private Long petitionSubjectId;
	private Long costEstimation;
	private Long petitionDepartmentId;
	private String description;
	private Long addressId ;
	private String isDeleted;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	private Long locationScopeId;
	private Long locationScopeValue;
	
	private LocationScope locationScope;
	private PetitionSubject petitionSubject;
	private PetitionWorkDetails petitionWorkDetails;
	private PetitionDepartment petitionDepartment;
	private LocationAddress locationAddress;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@Column(name="petition_sub_work_location_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionSubWorkLocationDetailsId() {
		return petitionSubWorkLocationDetailsId;
	}
	public void setPetitionSubWorkLocationDetailsId(Long petitionSubWorkLocationDetailsId) {
		this.petitionSubWorkLocationDetailsId = petitionSubWorkLocationDetailsId;
	}
	@Column(name="petition_work_details_id")
	public Long getPetitionWorkDetailsId() {
		return petitionWorkDetailsId;
	}
	public void setPetitionWorkDetailsId(Long petitionWorkDetailsId) {
		this.petitionWorkDetailsId = petitionWorkDetailsId;
	}
	@Column(name="petition_department_id")
	public Long getPetitionDepartmentId() {
		return petitionDepartmentId;
	}
	public void setPetitionDepartmentId(Long petitionDepartmentId) {
		this.petitionDepartmentId = petitionDepartmentId;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@Column(name="updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_work_details_id", insertable = false, updatable = false)
	public PetitionWorkDetails getPetitionWorkDetails() {
		return petitionWorkDetails;
	}
	public void setPetitionWorkDetails(PetitionWorkDetails petitionWorkDetails) {
		this.petitionWorkDetails = petitionWorkDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_department_id", insertable = false, updatable = false)
	public PetitionDepartment getPetitionDepartment() {
		return petitionDepartment;
	}
	public void setPetitionDepartment(PetitionDepartment petitionDepartment) {
		this.petitionDepartment = petitionDepartment;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	@Column(name="cost_estimation")
	public Long getCostEstimation() {
		return costEstimation;
	}
	public void setCostEstimation(Long costEstimation) {
		this.costEstimation = costEstimation;
	}
	
	@Column(name="petition_subject_id")
	public Long getPetitionSubjectId() {
		return petitionSubjectId;
	}
	public void setPetitionSubjectId(Long petitionSubjectId) {
		this.petitionSubjectId = petitionSubjectId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_subject_id", insertable = false, updatable = false)
	public PetitionSubject getPetitionSubject() {
		return petitionSubject;
	}
	public void setPetitionSubject(PetitionSubject petitionSubject) {
		this.petitionSubject = petitionSubject;
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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public LocationScope getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(LocationScope locationScope) {
		this.locationScope = locationScope;
	}
	
}
