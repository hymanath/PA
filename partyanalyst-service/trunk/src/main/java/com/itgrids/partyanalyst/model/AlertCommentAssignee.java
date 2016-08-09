package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "alert_comment_assignee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertCommentAssignee extends BaseModel implements Serializable {
	private Long alertCommentAssigneeId;
	private TdpCadre assignTdpCadre;
	private Long assignTdpCadreId;
	private AlertComment alertComment;
	private Long alertCommentId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_comment_assignee_id", unique = true, nullable = false)
	public Long getAlertCommentAssigneeId() {
		return alertCommentAssigneeId;
	}

	public void setAlertCommentAssigneeId(Long alertCommentAssigneeId) {
		this.alertCommentAssigneeId = alertCommentAssigneeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "assign_tdp_cadre_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_comment_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertComment getAlertComment() {
		return alertComment;
	}

	public void setAlertComment(AlertComment alertComment) {
		this.alertComment = alertComment;
	}

	@Column(name = "alert_comment_id")
	public Long getAlertCommentId() {
		return alertCommentId;
	}

	public void setAlertCommentId(Long alertCommentId) {
		this.alertCommentId = alertCommentId;
	}

}
