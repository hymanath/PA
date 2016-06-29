package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "alert_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertComment extends BaseModel implements Serializable {
	private Long alert_comment_id;
	private Long alert_id;
	private String comments;
	private Long insertedBy;
	private Date insertedTime;
	private String isDeleted;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_comment_id", unique = true, nullable = false)
	public Long getAlert_comment_id() {
		return alert_comment_id;
	}

	public void setAlert_comment_id(Long alert_comment_id) {
		this.alert_comment_id = alert_comment_id;
	}

	@Column(name = "alert_id")
	public Long getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(Long alert_id) {
		this.alert_id = alert_id;
	}

	@Column(name = "comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
