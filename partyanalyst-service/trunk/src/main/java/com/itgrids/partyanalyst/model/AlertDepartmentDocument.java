package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "alert_department_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertDepartmentDocument extends BaseModel implements Serializable{

	private Long alertDepartmentDocumentId;
	private String document;
	private String pathName;
	private Long alertDepartmentId;
	private Long govtDepartmentDesignationOfficerId;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	private AlertDepartment alertDepartment;
	private GovtDepartmentDesignationOfficer govtDepartmentDesignationOfficer;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_department_document_id", unique = true, nullable = false)
	public Long getAlertDepartmentDocumentId() {
		return alertDepartmentDocumentId;
	}
	public void setAlertDepartmentDocumentId(Long alertDepartmentDocumentId) {
		this.alertDepartmentDocumentId = alertDepartmentDocumentId;
	}
	
	@Column(name = "document")
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
	@Column(name = "path_name")
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	
	@Column(name = "alert_department_id")
	public Long getAlertDepartmentId() {
		return alertDepartmentId;
	}
	public void setAlertDepartmentId(Long alertDepartmentId) {
		this.alertDepartmentId = alertDepartmentId;
	}
	
	@Column(name = "govt_department_designation_officer_id")
	public Long getGovtDepartmentDesignationOfficerId() {
		return govtDepartmentDesignationOfficerId;
	}
	public void setGovtDepartmentDesignationOfficerId(
			Long govtDepartmentDesignationOfficerId) {
		this.govtDepartmentDesignationOfficerId = govtDepartmentDesignationOfficerId;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartment getAlertDepartment() {
		return alertDepartment;
	}
	public void setAlertDepartment(AlertDepartment alertDepartment) {
		this.alertDepartment = alertDepartment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_department_designation_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignationOfficer getGovtDepartmentDesignationOfficer() {
		return govtDepartmentDesignationOfficer;
	}
	public void setGovtDepartmentDesignationOfficer(
			GovtDepartmentDesignationOfficer govtDepartmentDesignationOfficer) {
		this.govtDepartmentDesignationOfficer = govtDepartmentDesignationOfficer;
	}
}
