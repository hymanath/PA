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
@Table(name = "petition_work_document")
public class PetitionWorkDocument {

	private Long petitionWorkDocumentId;
	private Long petitionWorkDetailsId ;
	private Long documentId;
	private String isDeleted;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private PetitionWorkDetails petitionWorkDetails ;
	private Document document ;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@Column(name="petition_work_document_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionWorkDocumentId() {
		return petitionWorkDocumentId;
	}
	public void setPetitionWorkDocumentId(Long petitionWorkDocumentId) {
		this.petitionWorkDocumentId = petitionWorkDocumentId;
	}
	@Column(name="petition_work_details_id")
	public Long getPetitionWorkDetailsId() {
		return petitionWorkDetailsId;
	}
	public void setPetitionWorkDetailsId(Long petitionWorkDetailsId) {
		this.petitionWorkDetailsId = petitionWorkDetailsId;
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
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_work_details_id", insertable = false, updatable = false)
	public PetitionWorkDetails getPetitionWorkDetails() {
		return petitionWorkDetails;
	}
	public void setPetitionWorkDetails(PetitionWorkDetails petitionWorkDetails) {
		this.petitionWorkDetails = petitionWorkDetails;
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
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
}
