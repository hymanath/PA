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
	private Long schemeId;
	private String schemeName;
	private Long agreementAmount;
	private Long technicalsancAmount;
	private Date agrementDate;
	private Date adminSanctionDate;
	private Date groundedDate;
	private Date completionDate;
	private Date techSanctionDate;
	private Date targetDate;
	private Long mandalId;
	private String mandalName;
	private Long districtId;
	private String ditrictName;
	private Long assemblyId;
	private String assemblyName;
	private Long habCode;
	private String habName;
	private Long parlimentId;
	private String parlimentName;
	
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
	
	@Column(name="scheme_id")
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
	
	@Column(name="scheme_name")
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	
	@Column(name="agreement_amount")
	public Long getAgreementAmount() {
		return agreementAmount;
	}
	public void setAgreementAmount(Long agreementAmount) {
		this.agreementAmount = agreementAmount;
	}
	
	@Column(name="technical_sanc_amount")
	public Long getTechnicalsancAmount() {
		return technicalsancAmount;
	}
	public void setTechnicalsancAmount(Long technicalsancAmount) {
		this.technicalsancAmount = technicalsancAmount;
	}
	
	@Column(name="agrement_date")
	public Date getAgrementDate() {
		return agrementDate;
	}
	
	public void setAgrementDate(Date agrementDate) {
		this.agrementDate = agrementDate;
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
	
	@Column(name="hab_code")
	public Long getHabCode() {
		return habCode;
	}
	public void setHabCode(Long habCode) {
		this.habCode = habCode;
	}
	
	@Column(name="hab_name")
	public String getHabName() {
		return habName;
	}
	public void setHabName(String habName) {
		this.habName = habName;
	}
	
	@Column(name="parliment_id")
	public Long getParlimentId() {
		return parlimentId;
	}
	public void setParlimentId(Long parlimentId) {
		this.parlimentId = parlimentId;
	}
	
	@Column(name="parliment_name")
	public String getParlimentName() {
		return parlimentName;
	}
	public void setParlimentName(String parlimentName) {
		this.parlimentName = parlimentName;
	}

	

}
