package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bio-metric_org_details")
public class BioMetricOrgDetails {

	private Long bioMetricOrgDetailsId;
	private String orgId;
	private String orgName;
	private String orgType;
	private String deptCode;
	private Date insertedTime;
	private String isDeleted;
	
	@Id
	@Column(name="bio-metric_org_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getBioMetricOrgDetailsId() {
		return bioMetricOrgDetailsId;
	}
	public void setBioMetricOrgDetailsId(Long bioMetricOrgDetailsId) {
		this.bioMetricOrgDetailsId = bioMetricOrgDetailsId;
	}
	@Column(name="org_id")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name="org_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="org_type")
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	@Column(name="dept_code")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
