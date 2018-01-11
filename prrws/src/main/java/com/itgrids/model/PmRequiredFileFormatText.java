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
@Table(name = "pm_required_file_format_text")
public class PmRequiredFileFormatText {
	
	private Long pmRequiredFileFormatTextId;
	private String formatText;
	private Long  pmSubjectId;
	private String  isDeleted;
	private Date  insertedTime;
	private Date   updatedTime;
	private Long  insertedUserId;
	private Long  updatedUserId;
	private PmSubject pmSubject;

	@Id
	@Column(name="pm_required_file_format_text_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmRequiredFileFormatTextId() {
		return pmRequiredFileFormatTextId;
	}
	public void setPmRequiredFileFormatTextId(Long pmRequiredFileFormatTextId) {
		this.pmRequiredFileFormatTextId = pmRequiredFileFormatTextId;
	}
	@Column(name="format_text")
	public String getFormatText() {
		return formatText;
	}
	public void setFormatText(String formatText) {
		this.formatText = formatText;
	}
	@Column(name="pm_subject_id")
	public Long getPmSubjectId() {
		return pmSubjectId;
	}
	public void setPmSubjectId(Long pmSubjectId) {
		this.pmSubjectId = pmSubjectId;
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
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_subject_id", insertable = false, updatable = false)
	public PmSubject getPmSubject() {
		return pmSubject;
	}
	public void setPmSubject(PmSubject pmSubject) {
		this.pmSubject = pmSubject;
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
	
	
}
