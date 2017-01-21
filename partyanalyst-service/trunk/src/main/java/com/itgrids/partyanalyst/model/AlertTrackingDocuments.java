package com.itgrids.partyanalyst.model;

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
@Table(name = "alert_tracking_documents")
public class AlertTrackingDocuments {

	public Long alertTrackingDocumentsId;
	public Long alertTrackingId;
	public String path;
	public String isDeleted;
	
	public AlertTracking alertTracking;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_tracking_documents_id",unique = true,nullable = false)
	public Long getAlertTrackingDocumentsId() {
		return alertTrackingDocumentsId;
	}

	public void setAlertTrackingDocumentsId(Long alertTrackingDocumentsId) {
		this.alertTrackingDocumentsId = alertTrackingDocumentsId;
	}

	@Column(name = "alert_tracking_id")
	public Long getAlertTrackingId() {
		return alertTrackingId;
	}

	public void setAlertTrackingId(Long alertTrackingId) {
		this.alertTrackingId = alertTrackingId;
	}

	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_tracking_id",insertable = false,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertTracking getAlertTracking() {
		return alertTracking;
	}

	public void setAlertTracking(AlertTracking alertTracking) {
		this.alertTracking = alertTracking;
	}
	
	
}
