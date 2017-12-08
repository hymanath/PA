package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_document")
public class PmDocument {

	private Long pmDocumentId;
	private Long petitionId;
	private Long documentId;
	private Long orderNo;
	

	@Id
	@Column(name="pm_document_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmDocumentId() {
		return pmDocumentId;
	}
	public void setPmDocumentId(Long pmDocumentId) {
		this.pmDocumentId = pmDocumentId;
	}
	@Column(name="petition_id")
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="document_id")
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
