package com.itgrids.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rws_work")
public class RwsWork {
	

	private int rwsWorkId;
	private String workId;
	private String workName;
	private String workStatus;
	private String programCode;
	private String programName;
	private String adminNo;
	private double sanctionedAmount; 
	private String assetType;
	private Date adminDate;
	private Date groundedDate;
	private Date targetDate;
	private Date completedDate;
	private Date commissionedDate;
	private Date stipulatedTargetDate;
	
	@Id
	@Column(name="rws_work_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public int getRwsWorkId() {
		return rwsWorkId;
	}
	public void setRwsWorkId(int rwsWorkId) {
		this.rwsWorkId = rwsWorkId;
	}
	
	@Column(name="work_id")
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	
	@Column(name="work_status")
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	
	@Column(name="program_code")
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	
	@Column(name="program_name")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	@Column(name="admin_no")
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	
	@Column(name="sanction_amount")
	public double getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(double sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	
	@Column(name="asset_type")
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	
	@Column(name="admin_date")
	public Date getAdminDate() {
		return adminDate;
	}
	public void setAdminDate(Date adminDate) {
		this.adminDate = adminDate;
	}
	
	@Column(name="target_date")
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	
	@Column(name="grounded_date")
	public Date getGroundedDate() {
		return groundedDate;
	}
	public void setGroundedDate(Date groundedDate) {
		this.groundedDate = groundedDate;
	}

	@Column(name="commissioned_date")
	public Date getCommissionedDate() {
		return commissionedDate;
	}
	public void setCommissionedDate(Date commissionedDate) {
		this.commissionedDate = commissionedDate;
	}
	
	@Column(name="completed_date")
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	@Column(name="stipulated_target_date")
	public Date getStipulatedTargetDate() {
		return stipulatedTargetDate;
	}
	public void setStipulatedTargetDate(Date stipulatedTargetDate) {
		this.stipulatedTargetDate = stipulatedTargetDate;
	}
	
	
}
