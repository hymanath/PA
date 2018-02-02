package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_document_type")
public class PmDocumentType {
	
	private Long pmDocumentTypeId;
	private String pmDocumentType;
	private String isDeleted;
	
	@Id
	@Column(name="pm_document_type_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDocumentTypeId() {
		return pmDocumentTypeId;
	}
	public void setPmDocumentTypeId(Long pmDocumentTypeId) {
		this.pmDocumentTypeId = pmDocumentTypeId;
	}
	@Column(name="pm_document_type")
	public String getPmDocumentType() {
		return pmDocumentType;
	}
	public void setPmDocumentType(String pmDocumentType) {
		this.pmDocumentType = pmDocumentType;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
