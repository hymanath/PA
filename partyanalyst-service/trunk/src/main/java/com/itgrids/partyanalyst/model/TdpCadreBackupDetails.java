package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "tdp_cadre_backup_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class TdpCadreBackupDetails extends BaseModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long tdpCadreBackupDetailsId;
	private String refNo;
	private String cadreBasicInfo;
	private String cadrePreviousRoles;
	private String cadrePreviousElections;
	private String familyDetails;
	private String dataSourceType;
	private Long updatedBy;
	private Date insertedTime;
	private Long tdpMemberTypeId;
	
	private String exception;

	private String jsonObject;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_backup_details_id", unique = true, nullable = false)
	public Long getTdpCadreBackupDetailsId() {
		return tdpCadreBackupDetailsId;
	}
	
	public void setTdpCadreBackupDetailsId(Long tdpCadreBackupDetailsId) {
		this.tdpCadreBackupDetailsId = tdpCadreBackupDetailsId;
	}
	
	@Column(name="ref_no")
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	
	@Column(name="cadre_basicInfo")
	public String getCadreBasicInfo() {
		return cadreBasicInfo;
	}
	public void setCadreBasicInfo(String cadreBasicInfo) {
		this.cadreBasicInfo = cadreBasicInfo;
	}
	
	@Column(name="cadre_previousRoles")
	public String getCadrePreviousRoles() {
		return cadrePreviousRoles;
	}
	public void setCadrePreviousRoles(String cadrePreviousRoles) {
		this.cadrePreviousRoles = cadrePreviousRoles;
	}
	
	@Column(name="cadre_previousElections")
	public String getCadrePreviousElections() {
		return cadrePreviousElections;
	}
	public void setCadrePreviousElections(String cadrePreviousElections) {
		this.cadrePreviousElections = cadrePreviousElections;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="data_source_type")
	public String getDataSourceType() {
		return dataSourceType;
	}

	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name="exception_text")
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	@Column(name="family_Details")
	public String getFamilyDetails() {
		return familyDetails;
	}

	public void setFamilyDetails(String familyDetails) {
		this.familyDetails = familyDetails;
	}

	
	@Column(name="json_obj")
	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	@Column(name="tdp_member_type")
	public Long getTdpMemberTypeId() {
		return tdpMemberTypeId;
	}

	public void setTdpMemberTypeId(Long tdpMemberTypeId) {
		this.tdpMemberTypeId = tdpMemberTypeId;
	}
	
}
