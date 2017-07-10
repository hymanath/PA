package com.rwss.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aprwssuser.rws_min_workscomp_view")
public class RwsMinWorkscompView {
	
	private String workId;
	private String workName;
	private String programCode;
	private String adminDate;
	private String adminNo;
	private Long sanctionAmount;
	private Date dateOfCompletion;
	private Date dateOfComm;
	private Date targetDateComp;
	
	@Id
	@Column(name = "WORK_ID")
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	@Column(name = "WORK_NAME")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	@Column(name = "PROGRAMME_CODE")
	public String getProgramCode() {
		return programCode;
	}
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	@Column(name = "ADMIN_DATE")
	public String getAdminDate() {
		return adminDate;
	}
	public void setAdminDate(String adminDate) {
		this.adminDate = adminDate;
	}
	@Column(name = "ADMIN_NO")
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	@Column(name = "SANCTION_AMOUNT")
	public Long getSanctionAmount() {
		return sanctionAmount;
	}
	public void setSanctionAmount(Long sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}
	@Column(name = "DATE_OF_COMPLETION")
	public Date getDateOfCompletion() {
		return dateOfCompletion;
	}
	public void setDateOfCompletion(Date dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}
	@Column(name="date_of_comm")
	public Date getDateOfComm() {
		return dateOfComm;
	}
	public void setDateOfComm(Date dateOfComm) {
		this.dateOfComm = dateOfComm;
	}
	
	@Column(name="target_date_comp")
	public Date getTargetDateComp() {
		return targetDateComp;
	}
	public void setTargetDateComp(Date targetDateComp) {
		this.targetDateComp = targetDateComp;
	}
	
}
