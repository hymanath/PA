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
@Table(name = "pm_required_letters_images")
public class PmRequiredLettersImages {
	
	private Long pmRequiredLettersImagesId;
	private Long pmOfficerDesignationId;
	private Long pmOfficerId;
	private String letterType;
	private String filePath;
	private String  imageType;
	private String isDeleted;
	private Date  insertedTime;
	private Long   insertedUserId;
	private PmOfficerDesignation pmOfficerDesignation;
	private PmOfficer pmOfficer;
	
	@Id
	@Column(name="pm_required_letters_images_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmRequiredLettersImagesId() {
		return pmRequiredLettersImagesId;
	}
	public void setPmRequiredLettersImagesId(Long pmRequiredLettersImagesId) {
		this.pmRequiredLettersImagesId = pmRequiredLettersImagesId;
	}
	@Column(name="pm_officer_designation_id")
	public Long getPmOfficerDesignationId() {
		return pmOfficerDesignationId;
	}
	public void setPmOfficerDesignationId(Long pmOfficerDesignationId) {
		this.pmOfficerDesignationId = pmOfficerDesignationId;
	}
	@Column(name="letter_type")
	public String getLetterType() {
		return letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	@Column(name="file_path")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Column(name="image_type")
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_officer_designation_id", insertable = false, updatable = false)
	public PmOfficerDesignation getPmOfficerDesignation() {
		return pmOfficerDesignation;
	}
	public void setPmOfficerDesignation(PmOfficerDesignation pmOfficerDesignation) {
		this.pmOfficerDesignation = pmOfficerDesignation;
	}
	
	@Column(name="pm_officer_id")
	public Long getPmOfficerId() {
		return pmOfficerId;
	}
	public void setPmOfficerId(Long pmOfficerId) {
		this.pmOfficerId = pmOfficerId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_officer_id", insertable = false, updatable = false)
	public PmOfficer getPmOfficer() {
		return pmOfficer;
	}
	public void setPmOfficer(PmOfficer pmOfficer) {
		this.pmOfficer = pmOfficer;
	}
	
	
}
