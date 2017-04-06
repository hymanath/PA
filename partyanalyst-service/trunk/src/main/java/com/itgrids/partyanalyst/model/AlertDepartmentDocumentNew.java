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
@Table(name = "alert_department_document_new")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertDepartmentDocumentNew {

	private Long alertDepartmentDocumentId;
	private String document;
	private Long insertedBy;
	private Date insertedTime;
	
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
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
}
