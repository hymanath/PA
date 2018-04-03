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
@Table(name = "alert_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertComment extends BaseModel implements Serializable {
	private Long alertCommentId;
	private Long alertId;
	private String comments;
	private Long insertedBy;
	private Date insertedTime;
	private String isDeleted;
	private User user;
	private Alert alert;
	
	private String zohoCommentId;
	
/*	private TdpCadre assignTdpCadre;
	private Long assignTdpCadreId;
	*/

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_comment_id", unique = true, nullable = false)

	public Long getAlertCommentId() {
		return alertCommentId;
	}

	public void setAlertCommentId(Long alertCommentId) {
		this.alertCommentId = alertCommentId;
	}
	
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}


	public void setAlertId(Long alertId) {
		this.alertId = alertId;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@Column(name = "zoho_comment_id")
	public String getZohoCommentId() {
		return zohoCommentId;
	}

	public void setZohoCommentId(String zohoCommentId) {
		this.zohoCommentId = zohoCommentId;
	}
	
	
	
	/*@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="assign_tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getAssignTdpCadre() {
		return assignTdpCadre;
	}

	public void setAssignTdpCadre(TdpCadre assignTdpCadre) {
		this.assignTdpCadre = assignTdpCadre;
	}
	@Column(name = "assign_tdp_cadre_id")
	public Long getAssignTdpCadreId() {
		return assignTdpCadreId;
	}

	public void setAssignTdpCadreId(Long assignTdpCadreId) {
		this.assignTdpCadreId = assignTdpCadreId;
	}
	*/
	
	
	
	

}
