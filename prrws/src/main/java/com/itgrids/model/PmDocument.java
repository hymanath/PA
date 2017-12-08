
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
@Table(name = "pm_document")
public class PmDocument {

	private Long pmDocumentId;
	private Long petitionId;
	private Long documentId;
	private Long orderNo;
	
	private Petition petition;
	private Document document;
	

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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_id", insertable = false, updatable = false)
	public Petition getPetition() {
		return petition;
	}
	public void setPetition(Petition petition) {
		this.petition = petition;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "document_id", insertable = false, updatable = false)
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
}
