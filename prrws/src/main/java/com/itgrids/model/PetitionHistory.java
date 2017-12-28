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
@Table(name = "petition_history")
public class PetitionHistory {
	
	private Long petitionHistoryId;
	private Long  petitionId;
	private Long  pmSubWorkDetailsId;
	private Long  pmRepresenteeRefDetailsId;
	private Long  pmRepresenteeRefDocumentId;
	private Long  pmDocumentId;
	private Long insertedUserId;
	private Long updatedUserId;
	
	private User insertedUser;
	private Date insertedTime;
	
	
	private Petition petition;
	private PmSubWorkDetails pmSubWorkDetails;
	private PmRepresenteeRefDetails pmRepresenteeRefDetails;
	private PmRepresenteeRefDocument pmRepresenteeRefDocument;
	//private PmDocument pmDocument;
	
	@Id
	@Column(name="petition_history_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionHistoryId() {
		return petitionHistoryId;
	}
	public void setPetitionHistoryId(Long petitionHistoryId) {
		this.petitionHistoryId = petitionHistoryId;
	}
	@Column(name="petition_id")
	public Long getPetitionId() {
		return petitionId;
	}
	public void setPetitionId(Long petitionId) {
		this.petitionId = petitionId;
	}
	@Column(name="pm_sub_work_details_id")
	public Long getPmSubWorkDetailsId() {
		return pmSubWorkDetailsId;
	}
	public void setPmSubWorkDetailsId(Long pmSubWorkDetailsId) {
		this.pmSubWorkDetailsId = pmSubWorkDetailsId;
	}
	@Column(name="pm_representee_ref_details_id")
	public Long getPmRepresenteeRefDetailsId() {
		return pmRepresenteeRefDetailsId;
	}
	public void setPmRepresenteeRefDetailsId(Long pmRepresenteeRefDetailsId) {
		this.pmRepresenteeRefDetailsId = pmRepresenteeRefDetailsId;
	}
	@Column(name="pm_representee_ref_document_id")
	public Long getPmRepresenteeRefDocumentId() {
		return pmRepresenteeRefDocumentId;
	}
	public void setPmRepresenteeRefDocumentId(Long pmRepresenteeRefDocumentId) {
		this.pmRepresenteeRefDocumentId = pmRepresenteeRefDocumentId;
	}
	@Column(name="pm_document_id")
	public Long getPmDocumentId() {
		return pmDocumentId;
	}
	public void setPmDocumentId(Long pmDocumentId) {
		this.pmDocumentId = pmDocumentId;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@Column(name="updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
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
	@JoinColumn(name = "pm_sub_work_details_id", insertable = false, updatable = false)
	public PmSubWorkDetails getPmSubWorkDetails() {
		return pmSubWorkDetails;
	}
	public void setPmSubWorkDetails(PmSubWorkDetails pmSubWorkDetails) {
		this.pmSubWorkDetails = pmSubWorkDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_representee_ref_details_id", insertable = false, updatable = false)
	public PmRepresenteeRefDetails getPmRepresenteeRefDetails() {
		return pmRepresenteeRefDetails;
	}
	public void setPmRepresenteeRefDetails(PmRepresenteeRefDetails pmRepresenteeRefDetails) {
		this.pmRepresenteeRefDetails = pmRepresenteeRefDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_representee_ref_document_id", insertable = false, updatable = false)
	public PmRepresenteeRefDocument getPmRepresenteeRefDocument() {
		return pmRepresenteeRefDocument;
	}
	public void setPmRepresenteeRefDocument(PmRepresenteeRefDocument pmRepresenteeRefDocument) {
		this.pmRepresenteeRefDocument = pmRepresenteeRefDocument;
	}
	/*@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_document_id", insertable = false, updatable = false)
	public PmDocument getPmDocument() {
		return pmDocument;
	}
	public void setPmDocument(PmDocument pmDocument) {
		this.pmDocument = pmDocument;
	}	*/
}
