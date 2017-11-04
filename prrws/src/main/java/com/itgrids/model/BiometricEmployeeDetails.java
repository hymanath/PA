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

@Entity
@Table(name = "biometric_employee_details")
public class BiometricEmployeeDetails {

	private Long biometricEmployeeDetailsId;
	private Long id;
	private String empId;
	private Long bioMetricOrgDetailsId;
	private String districtCode;
	private String stateCode;
	private String locationCode;
	private String districtName;
	private String stateName;
	private String locationName;
	private String aadhaarNo;
	private String designation;
	private String empName;
	private Date empDob;
	private String gender;
	private String email;
	private String empMobile;
	private Date creationDate;
	private Date lastUpdatedDate;
	private String locationType;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	private BioMetricOrgDetails bioMetricOrgDetails;
	
	@Id
	@Column(name="biometric_employee_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getBiometricEmployeeDetailsId() {
		return biometricEmployeeDetailsId;
	}
	public void setBiometricEmployeeDetailsId(Long biometricEmployeeDetailsId) {
		this.biometricEmployeeDetailsId = biometricEmployeeDetailsId;
	}
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="emp_id")
	public String getEmpId() {
		return empId;
	}
	
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	@Column(name="bio_metric_org_details_id")
	public Long getBioMetricOrgDetailsId() {
		return bioMetricOrgDetailsId;
	}
	public void setBioMetricOrgDetailsId(Long bioMetricOrgDetailsId) {
		this.bioMetricOrgDetailsId = bioMetricOrgDetailsId;
	}
	@Column(name="district_code")
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	@Column(name="state_code")
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@Column(name="location_code")
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	@Column(name="district_name")
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	@Column(name="state_name")
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	@Column(name="location_name")
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	@Column(name="aadhaar_no")
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	@Column(name = "designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name="emp_name")
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Column(name="emp_dob")
	public Date getEmpDob() {
		return empDob;
	}
	public void setEmpDob(Date empDob) {
		this.empDob = empDob;
	}
	@Column(name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="emp_mobile")
	public String getEmpMobile() {
		return empMobile;
	}
	public void setEmpMobile(String empMobile) {
		this.empMobile = empMobile;
	}
	@Column(name="creation_date")
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@Column(name="last_updated_date")
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	@Column(name="location_Type")
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
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
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "bio_metric_org_details_id", insertable = false, updatable = false)
	public BioMetricOrgDetails getBioMetricOrgDetails() {
		return bioMetricOrgDetails;
	}
	public void setBioMetricOrgDetails(BioMetricOrgDetails bioMetricOrgDetails) {
		this.bioMetricOrgDetails = bioMetricOrgDetails;
	}
	
}
