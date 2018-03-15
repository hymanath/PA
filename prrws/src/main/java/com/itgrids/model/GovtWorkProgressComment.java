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
@Table(name = "govt_work_progress_comment")
public class GovtWorkProgressComment {
	private Long govtWorkProgressCommentId;
	private String comment;
	private Long updatedBy;
	private Date updatedTime;
	
	private MobileAppUser updatedByUser;
	
	
	
	
	
	@Id
	@Column(name="govt_work_progress_comment_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getGovtWorkProgressCommentId() {
		return govtWorkProgressCommentId;
	}
	public void setGovtWorkProgressCommentId(Long govtWorkProgressCommentId) {
		this.govtWorkProgressCommentId = govtWorkProgressCommentId;
	}
	
	@Column(name="comment")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", insertable = false, updatable = false)
	public MobileAppUser getUpdatedByUser() {
		return updatedByUser;
	}
	public void setUpdatedByUser(MobileAppUser updatedByUser) {
		this.updatedByUser = updatedByUser;
	}
}
