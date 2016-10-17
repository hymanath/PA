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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "cadre_reg_issue_track")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreRegIssueTrack extends BaseModel implements Serializable {
	

	public Long cadreRegIssueTrackId;
	public Long cadreRegIssueId;
	public Long cadreRegIssueTypeId;
	private String description;
	private Long cadreRegIssueStatusId;
	private Long insertedBy;
	private Date insertedTime;
	
	private CadreRegIssue cadreRegIssue;
	private CadreRegIssueType cadreRegIssueType;
	private CadreRegUser insertedUser;
	private CadreRegIssueStatus cadreRegIssueStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cadre_reg_issue_track_id", unique = true, nullable = false)
	public Long getCadreRegIssueTrackId() {
		return cadreRegIssueTrackId;
	}
	public void setCadreRegIssueTrackId(Long cadreRegIssueTrackId) {
		this.cadreRegIssueTrackId = cadreRegIssueTrackId;
	}
	@Column(name="cadre_reg_issue_id")
	public Long getCadreRegIssueId() {
		return cadreRegIssueId;
	}
	public void setCadreRegIssueId(Long cadreRegIssueId) {
		this.cadreRegIssueId = cadreRegIssueId;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_reg_issue_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegIssue getCadreRegIssue() {
		return cadreRegIssue;
	}
	public void setCadreRegIssue(CadreRegIssue cadreRegIssue) {
		this.cadreRegIssue = cadreRegIssue;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="cadre_reg_issue_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegIssueType getCadreRegIssueType() {
		return cadreRegIssueType;
	}
	public void setCadreRegIssueType(CadreRegIssueType cadreRegIssueType) {
		this.cadreRegIssueType = cadreRegIssueType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegUser getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(CadreRegUser insertedUser) {
		this.insertedUser = insertedUser;
	}
	
	@Column(name="cadre_reg_issue_status_id")
	public Long getCadreRegIssueStatusId() {
		return cadreRegIssueStatusId;
	}
	public void setCadreRegIssueStatusId(Long cadreRegIssueStatusId) {
		this.cadreRegIssueStatusId = cadreRegIssueStatusId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_reg_issue_status_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreRegIssueStatus getCadreRegIssueStatus() {
		return cadreRegIssueStatus;
	}
	public void setCadreRegIssueStatus(CadreRegIssueStatus cadreRegIssueStatus) {
		this.cadreRegIssueStatus = cadreRegIssueStatus;
	}
	
	@Column(name="cadre_reg_issue_type_id")
	public Long getCadreRegIssueTypeId() {
		return cadreRegIssueTypeId;
	}
	public void setCadreRegIssueTypeId(Long cadreRegIssueTypeId) {
		this.cadreRegIssueTypeId = cadreRegIssueTypeId;
	}
	
}
