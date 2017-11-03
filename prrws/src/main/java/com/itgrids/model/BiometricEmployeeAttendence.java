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
@Table(name = "biometric-employee_attendence")
public class BiometricEmployeeAttendence {
	
	private Long employeeAttendencId;
	private Long id;
	private Long sNo;
	private Long bioMetricOrgDetailsId;
	private Date logDate ;
	private String empId;
	private String inDeviceId;
	private Date inTime;
	private String outDeviceId;
	private String atType;
	private String flag;
	private Date lastupdate;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	private BioMetricOrgDetails bioMetricOrgDetails;
	
	@Id
	@Column(name="employee_attendence_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getEmployeeAttendencId() {
		return employeeAttendencId;
	}
	public void setEmployeeAttendencId(Long employeeAttendencId) {
		this.employeeAttendencId = employeeAttendencId;
	}
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="s_no")
	public Long getsNo() {
		return sNo;
	}
	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}
	@Column(name="bio-metric_org_details_id")
	public Long getBioMetricOrgDetailsId() {
		return bioMetricOrgDetailsId;
	}
	public void setBioMetricOrgDetailsId(Long bioMetricOrgDetailsId) {
		this.bioMetricOrgDetailsId = bioMetricOrgDetailsId;
	}
	@Column(name="log_date")
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	@Column(name="emp_id")
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	@Column(name="in_device_id")
	public String getInDeviceId() {
		return inDeviceId;
	}
	public void setInDeviceId(String inDeviceId) {
		this.inDeviceId = inDeviceId;
	}
	@Column(name="in_time")
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	@Column(name="out_device_id ")
	public String getOutDeviceId() {
		return outDeviceId;
	}
	public void setOutDeviceId(String outDeviceId) {
		this.outDeviceId = outDeviceId;
	}
	@Column(name="at_type")
	public String getAtType() {
		return atType;
	}
	public void setAtType(String atType) {
		this.atType = atType;
	}
	@Column(name="flag")
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Column(name="lastupdate")
	public Date getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}
	@Column(name="inserted_time ")
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
	@JoinColumn(name = "bio-metric_org_details_id", insertable = false, updatable = false)
	public BioMetricOrgDetails getBioMetricOrgDetails() {
		return bioMetricOrgDetails;
	}
	public void setBioMetricOrgDetails(BioMetricOrgDetails bioMetricOrgDetails) {
		this.bioMetricOrgDetails = bioMetricOrgDetails;
	}
	
}
