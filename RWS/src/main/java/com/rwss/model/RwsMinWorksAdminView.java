package com.rwss.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aprwssuser.rws_min_worksadmin_view")
public class RwsMinWorksAdminView {

	private String workId;
	private String workName;
	private String programCode;
	private String adminDate;
	private String adminNo;
	private Long sanctionAmount;
	private Date groundingDate;
	private String typeOfAssestName;
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
	@Column(name = "GROUNDING_DATE")
	public Date getGroundingDate() {
		return groundingDate;
	}
	public void setGroundingDate(Date groundingDate) {
		this.groundingDate = groundingDate;
	}
	
	@Column(name ="TYPE_OF_ASSET_NAME")
	public String getTypeOfAssestName() {
		return typeOfAssestName;
	}
	public void setTypeOfAssestName(String typeOfAssestName) {
		this.typeOfAssestName = typeOfAssestName;
	}
	@Column(name="target_date_comp")
	public Date getTargetDateComp() {
		return targetDateComp;
	}
	public void setTargetDateComp(Date targetDateComp) {
		this.targetDateComp = targetDateComp;
	}
	
}
