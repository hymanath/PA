package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="enc_works")
public class EncWorks {
	
	private Long encWorkId;
	private Long workId;
	private String workName;
	private String workStatus;
	private Long grantid;
	private String grantName;
	private Long subGrantId;
	private String subGrantName;
	private Long sanctionedAmount;
	private Date adminSanctionDate;
	private Date techSanctionDate;
	private Date entrustedDate;
	private Date groundedDate;
	private Date completionDate;
	private Date targetDate;
	private Long mandalId;
	private String mandalName;
	private Long districtId;
	private String ditrictName;
	private Long assemblyId;
	private String assemblyName;
	
	@Id
	@Column(name="enc_works_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getEncWorkId() {
		return encWorkId;
	}
	public void setEncWorkId(Long encWorkId) {
		this.encWorkId = encWorkId;
	}
	
	@Column(name="work_id")
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	
	@Column(name="grant_id")
	public Long getGrantid() {
		return grantid;
	}
	public void setGrantid(Long grantid) {
		this.grantid = grantid;
	}
	@Column(name="sub_grant_id")
	public Long getSubGrantId() {
		return subGrantId;
	}
	public void setSubGrantId(Long subGrantId) {
		this.subGrantId = subGrantId;
	}
	
	@Column(name="sub_grant_name")
	public String getSubGrantName() {
		return subGrantName;
	}
	public void setSubGrantName(String subGrantName) {
		this.subGrantName = subGrantName;
	}
	
	@Column(name="grant_name")
	public String getGrantName() {
		return grantName;
	}
	public void setGrantName(String grantName) {
		this.grantName = grantName;
	}
		
	@Column(name="sanctioned_amount")
	public Long getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(Long sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	
	@Column(name="entrusted_date")
	public Date getEntrustedDate() {
		return entrustedDate;
	}
	
	public void setEntrustedDate(Date entrustedDate) {
		this.entrustedDate = entrustedDate;
	}
	
	@Column(name="admin_sanction_date")
	public Date getAdminSanctionDate() {
		return adminSanctionDate;
	}
	public void setAdminSanctionDate(Date adminSanctionDate) {
		this.adminSanctionDate = adminSanctionDate;
	}
	
	@Column(name="grounded_date")
	public Date getGroundedDate() {
		return groundedDate;
	}
	public void setGroundedDate(Date groundedDate) {
		this.groundedDate = groundedDate;
	}
	
	@Column(name="completion_date")
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	@Column(name="tech_sanction_date")
	public Date getTechSanctionDate() {
		return techSanctionDate;
	}
	public void setTechSanctionDate(Date techSanctionDate) {
		this.techSanctionDate = techSanctionDate;
	}
	
	@Column(name="target_date")
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	
	@Column(name="mandal_id")
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	
	@Column(name="mandal_name")
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	
	@Column(name="district_id")
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	@Column(name="ditrict_name")
	public String getDitrictName() {
		return ditrictName;
	}
	public void setDitrictName(String ditrictName) {
		this.ditrictName = ditrictName;
	}
	
	@Column(name="assembly_id")
	public Long getAssemblyId() {
		return assemblyId;
	}
	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}
	@Column(name="assembly_name")
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}
	
	@Column(name="work_status")
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	

}
