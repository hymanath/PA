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
@Table(name = "alert_clarification_document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertClarificationDocument extends BaseModel implements Serializable {
	
	private Long alertClarificationDocumentId;
	private Long alertId;
	private String clarificationDocumentPath;
	private String isDeleted;
	private Date insertedTime;
	private Long insertedBy;
	
	private Alert alert;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_clarification_document_id", unique = true, nullable = false)
	public Long getAlertClarificationDocumentId() {
		return alertClarificationDocumentId;
	}
	public void setAlertClarificationDocumentId(Long alertClarificationDocumentId) {
		this.alertClarificationDocumentId = alertClarificationDocumentId;
	}

	@Column(name="alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	@Column(name="clarification_document_path")
	public String getClarificationDocumentPath() {
		return clarificationDocumentPath;
	}
	public void setClarificationDocumentPath(String clarificationDocumentPath) {
		this.clarificationDocumentPath = clarificationDocumentPath;
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
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
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


}
