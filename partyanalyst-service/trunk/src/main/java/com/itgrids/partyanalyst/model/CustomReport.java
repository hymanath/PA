package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name="custom_report")
public class CustomReport implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long customReportId;
	public Long customReportProgramId;
	public String reportName;
	public String description;
	public String isSubmitted;
	public Date submittedDate;
	public Date updatedTime;
	public String isDeleted;
	public Long insertedBy;
	public Long updatedBy;
	
	public CustomReportProgram customReportProgram;
	public User insertedUser; 
	public User updatedUser;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="custom_report_id",nullable=false,unique=true)
	public Long getCustomReportId() {
		return customReportId;
	}
	public void setCustomReportId(Long customReportId) {
		this.customReportId = customReportId;
	}
	@Column(name="report_name")
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="is_submitted")
	public String getIsSubmitted() {
		return isSubmitted;
	}
	public void setIsSubmitted(String isSubmitted) {
		this.isSubmitted = isSubmitted;
	}
	@Column(name="submitted_date")
	public Date getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
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
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name="custom_report_program_id")
	public Long getCustomReportProgramId() {
		return customReportProgramId;
	}
	public void setCustomReportProgramId(Long customReportProgramId) {
		this.customReportProgramId = customReportProgramId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "custom_report_program_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public CustomReportProgram getCustomReportProgram() {
		return customReportProgram;
	}
	public void setCustomReportProgram(CustomReportProgram customReportProgram) {
		this.customReportProgram = customReportProgram;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_by", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	
}
