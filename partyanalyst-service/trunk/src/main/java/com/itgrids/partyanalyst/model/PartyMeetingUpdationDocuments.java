package com.itgrids.partyanalyst.model;

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
@Table(name="party_meeting_updation_documents")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyMeetingUpdationDocuments implements java.io.Serializable{
	
	private Long partyMeetingUpdationDocumentsId;
	private Long partyMeetingUpdationDetailsId;
	private String documentPath;
	private String isDeleted;
	
	private PartyMeetingUpdationDetails partyMeetingUpdationDetails;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="party_meeting_updation_documents_id" , unique=true, nullable=false)
	public Long getPartyMeetingUpdationDocumentsId() {
		return partyMeetingUpdationDocumentsId;
	}

	public void setPartyMeetingUpdationDocumentsId(
			Long partyMeetingUpdationDocumentsId) {
		this.partyMeetingUpdationDocumentsId = partyMeetingUpdationDocumentsId;
	}
	
	@Column(name="party_meeting_updation_details_id")
	public Long getPartyMeetingUpdationDetailsId() {
		return partyMeetingUpdationDetailsId;
	}

	public void setPartyMeetingUpdationDetailsId(Long partyMeetingUpdationDetailsId) {
		this.partyMeetingUpdationDetailsId = partyMeetingUpdationDetailsId;
	}
	
	@Column(name="document_path")
	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JoinColumn(name = "party_meeting_updation_details_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyMeetingUpdationDetails getPartyMeetingUpdationDetails() {
		return partyMeetingUpdationDetails;
	}

	public void setPartyMeetingUpdationDetails(
			PartyMeetingUpdationDetails partyMeetingUpdationDetails) {
		this.partyMeetingUpdationDetails = partyMeetingUpdationDetails;
	}

	
}
