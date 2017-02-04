package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "govt_department_designation_officer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentDesignationOfficer extends BaseModel implements Serializable{

	private Long govtDepartmentDesignationOfficerId;
	private Long govtDepartmentDesignationLocationId;
	private Long offId;
	private String officerName;
	private String mobileNo;
	private String emailId;
	
	private GovtDepartmentDesignationLocation govtDepartmentDesignationLocation;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_designation_officer_id", unique = true, nullable = false)
	public Long getGovtDepartmentDesignationOfficerId() {
		return govtDepartmentDesignationOfficerId;
	}
	public void setGovtDepartmentDesignationOfficerId(
			Long govtDepartmentDesignationOfficerId) {
		this.govtDepartmentDesignationOfficerId = govtDepartmentDesignationOfficerId;
	}
	
	@Column(name = "govt_department_designation_location_id")
	public Long getGovtDepartmentDesignationLocationId() {
		return govtDepartmentDesignationLocationId;
	}
	public void setGovtDepartmentDesignationLocationId(
			Long govtDepartmentDesignationLocationId) {
		this.govtDepartmentDesignationLocationId = govtDepartmentDesignationLocationId;
	}
	
	@Column(name = "off_id")
	public Long getOffId() {
		return offId;
	}
	public void setOffId(Long offId) {
		this.offId = offId;
	}
	
	@Column(name = "officer_name")
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_designation_location_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignationLocation getGovtDepartmentDesignationLocation() {
		return govtDepartmentDesignationLocation;
	}
	public void setGovtDepartmentDesignationLocation(
			GovtDepartmentDesignationLocation govtDepartmentDesignationLocation) {
		this.govtDepartmentDesignationLocation = govtDepartmentDesignationLocation;
	}
}
