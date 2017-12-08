package com.itgrids.model;

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
@Table(name = "pm_representee_ref_document")
public class PmRepresenteeRefDocument {
	
	private Long pmRepresenteeRefDocumentId;
	private Long pmRepresenteeRefDetailsId;
	private Long documentId;
	private String isDeleted;
	
	private PmRepresenteeRefDetails pmRepresenteeRefDetails;
    
	@Id
	@Column(name="pm_representee_ref_document_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmRepresenteeRefDocumentId() {
		return pmRepresenteeRefDocumentId;
	}
	public void setPmRepresenteeRefDocumentId(Long pmRepresenteeRefDocumentId) {
		this.pmRepresenteeRefDocumentId = pmRepresenteeRefDocumentId;
	}
	@Column(name="pm_representee_ref_details_id")
	public Long getPmRepresenteeRefDetailsId() {
		return pmRepresenteeRefDetailsId;
	}
	public void setPmRepresenteeRefDetailsId(Long pmRepresenteeRefDetailsId) {
		this.pmRepresenteeRefDetailsId = pmRepresenteeRefDetailsId;
	}
	@Column(name="document_id")
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_representee_ref_details_id", insertable = false, updatable = false)
	public PmRepresenteeRefDetails getPmRepresenteeRefDetails() {
		return pmRepresenteeRefDetails;
	}
	public void setPmRepresenteeRefDetails(PmRepresenteeRefDetails pmRepresenteeRefDetails) {
		this.pmRepresenteeRefDetails = pmRepresenteeRefDetails;
	}
}
