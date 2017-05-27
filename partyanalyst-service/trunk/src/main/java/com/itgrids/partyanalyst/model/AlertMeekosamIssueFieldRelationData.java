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
@Table(name = "alert_meekosam_issue_field_relation_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertMeekosamIssueFieldRelationData extends BaseModel implements Serializable {
	private Long alertMeekosamIssueFieldRelationDataId;
	private Long alertId;
	private Long meekosamIssueFieldRelationId;
	private Long meekosamIssueFieldRelationDataId;
	private String data;
	private String isDeleted;
	
	private Alert alert;
	private MeekosamIssueFieldRelation meekosamIssueFieldRelation;
	private MeekosamIssueFieldRelationData meekosamIssueFieldRelationData;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_meekosam_issue_field_relation_data_id", unique = true, nullable = false)
	public Long getAlertMeekosamIssueFieldRelationDataId() {
		return alertMeekosamIssueFieldRelationDataId;
	}
	public void setAlertMeekosamIssueFieldRelationDataId(
			Long alertMeekosamIssueFieldRelationDataId) {
		this.alertMeekosamIssueFieldRelationDataId = alertMeekosamIssueFieldRelationDataId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "meekosam_issue_field_relation_id")
	public Long getMeekosamIssueFieldRelationId() {
		return meekosamIssueFieldRelationId;
	}
	public void setMeekosamIssueFieldRelationId(Long meekosamIssueFieldRelationId) {
		this.meekosamIssueFieldRelationId = meekosamIssueFieldRelationId;
	}
	@Column(name = "meekosam_issue_field_relation_data_id")
	public Long getMeekosamIssueFieldRelationDataId() {
		return meekosamIssueFieldRelationDataId;
	}
	public void setMeekosamIssueFieldRelationDataId(
			Long meekosamIssueFieldRelationDataId) {
		this.meekosamIssueFieldRelationDataId = meekosamIssueFieldRelationDataId;
	}
	@Column(name = "data")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_issue_field_relation_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueFieldRelation getMeekosamIssueFieldRelation() {
		return meekosamIssueFieldRelation;
	}
	public void setMeekosamIssueFieldRelation(
			MeekosamIssueFieldRelation meekosamIssueFieldRelation) {
		this.meekosamIssueFieldRelation = meekosamIssueFieldRelation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="meekosam_issue_field_relation_data_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MeekosamIssueFieldRelationData getMeekosamIssueFieldRelationData() {
		return meekosamIssueFieldRelationData;
	}
	public void setMeekosamIssueFieldRelationData(
			MeekosamIssueFieldRelationData meekosamIssueFieldRelationData) {
		this.meekosamIssueFieldRelationData = meekosamIssueFieldRelationData;
	}
}
