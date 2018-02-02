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
@Table(name = "govt_work_progress_document")
public class GovtWorkProgressDocument {
	private Long govtWorkProgressDocumentId;
	private Long govtWorkProgressUpdateId;
	private Long govtWorkProgressId;
	private Long documentId;
	private Long updatedBy;
	private Date updatedTime;
	private String isDeleted;
	
	private GovtWorkProgressUpdate govtWorkProgressUpdate;
	private GovtWorkProgress govtWorkProgress;
	private Document document;
	private MobileAppUser updatedByUser;
	
	@Id
	@Column(name="govt_work_progress_document_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGovtWorkProgressDocumentId() {
		return govtWorkProgressDocumentId;
	}
	public void setGovtWorkProgressDocumentId(Long govtWorkProgressDocumentId) {
		this.govtWorkProgressDocumentId = govtWorkProgressDocumentId;
	}
	
	@Column(name="govt_work_progress_update_id")
	public Long getGovtWorkProgressUpdateId() {
		return govtWorkProgressUpdateId;
	}
	public void setGovtWorkProgressUpdateId(Long govtWorkProgressUpdateId) {
		this.govtWorkProgressUpdateId = govtWorkProgressUpdateId;
	}
	
	@Column(name="govt_work_progress_id")
	public Long getGovtWorkProgressId() {
		return govtWorkProgressId;
	}
	public void setGovtWorkProgressId(Long govtWorkProgressId) {
		this.govtWorkProgressId = govtWorkProgressId;
	}
	
	@Column(name="document_id")
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	@JoinColumn(name = "govt_work_progress_update_id", insertable = false, updatable = false)
	public GovtWorkProgressUpdate getGovtWorkProgressUpdate() {
		return govtWorkProgressUpdate;
	}
	public void setGovtWorkProgressUpdate(GovtWorkProgressUpdate govtWorkProgressUpdate) {
		this.govtWorkProgressUpdate = govtWorkProgressUpdate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_work_progress_id", insertable = false, updatable = false)
	public GovtWorkProgress getGovtWorkProgress() {
		return govtWorkProgress;
	}
	public void setGovtWorkProgress(GovtWorkProgress govtWorkProgress) {
		this.govtWorkProgress = govtWorkProgress;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", insertable = false, updatable = false)
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	public MobileAppUser getUpdatedByUser() {
		return updatedByUser;
	}
	public void setUpdatedByUser(MobileAppUser updatedByUser) {
		this.updatedByUser = updatedByUser;
	}
	
	
	
	
}
