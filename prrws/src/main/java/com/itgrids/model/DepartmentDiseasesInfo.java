package com.itgrids.model;

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

@Entity
@Table(name = "department_diseases_info")
public class DepartmentDiseasesInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long departmentDiseasesInfoId;
	private Long departmentId;
	private Long diseasesId;
	private Date reportedDate;
	private Long locationScopeId;
	private Long locationValue;
	private Long noOfCases;
	private Long locationAddressId;
	private String isDeleted;
	
	private Department department;
	private Diseases diseases;
	private LocationScope locationScope;
	private LocationAddress locationAddress;
	@Id
	@Column(name="department_diseases_info_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getDepartmentDiseasesInfoId() {
		return departmentDiseasesInfoId;
	}
	public void setDepartmentDiseasesInfoId(Long departmentDiseasesInfoId) {
		this.departmentDiseasesInfoId = departmentDiseasesInfoId;
	}
	@Column(name="department_id")
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	@Column(name="diseases_id")
	public Long getDiseasesId() {
		return diseasesId;
	}
	public void setDiseasesId(Long diseasesId) {
		this.diseasesId = diseasesId;
	}
	@Column(name="reported_date")
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
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
	@Column(name="no_of_cases")
	public Long getNoOfCases() {
		return noOfCases;
	}
	public void setNoOfCases(Long noOfCases) {
		this.noOfCases = noOfCases;
	}
	@Column(name="location_address_id")
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id", insertable = false, updatable = false)
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "diseases_id", insertable = false, updatable = false)
	public Diseases getDiseases() {
		return diseases;
	}
	public void setDiseases(Diseases diseases) {
		this.diseases = diseases;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_scope_id", insertable = false, updatable = false)
	public LocationScope getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(LocationScope locationScope) {
		this.locationScope = locationScope;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
	
	
}
