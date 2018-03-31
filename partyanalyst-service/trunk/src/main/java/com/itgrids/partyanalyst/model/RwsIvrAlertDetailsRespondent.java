package com.itgrids.partyanalyst.model;

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
@Table(name = "rws_ivr_alert_details_respondent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RwsIvrAlertDetailsRespondent {

	private Long rwsIvrAlertDetailsRespondentId;
	private Long rwsIvrAlertDetailsId;
	private Long ivrRespondentId;
	private String rwsIvrStatus;
	private String isDeleted;
	private Long insertedBy;
	private Date insertedTime;
	
	private RwsIvrAlertDetails rwsIvrAlertDetails;
	private IvrRespondent ivrRespondent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rws_ivr_alert_details_respondent_id", unique = true, nullable = false)
	public Long getRwsIvrAlertDetailsRespondentId() {
		return rwsIvrAlertDetailsRespondentId;
	}
	public void setRwsIvrAlertDetailsRespondentId(
			Long rwsIvrAlertDetailsRespondentId) {
		this.rwsIvrAlertDetailsRespondentId = rwsIvrAlertDetailsRespondentId;
	}
	@Column(name = "rws_ivr_alert_details_id")
	public Long getRwsIvrAlertDetailsId() {
		return rwsIvrAlertDetailsId;
	}
	public void setRwsIvrAlertDetailsId(Long rwsIvrAlertDetailsId) {
		this.rwsIvrAlertDetailsId = rwsIvrAlertDetailsId;
	}
	@Column(name = "ivr_respondent_id")
	public Long getIvrRespondentId() {
		return ivrRespondentId;
	}
	public void setIvrRespondentId(Long ivrRespondentId) {
		this.ivrRespondentId = ivrRespondentId;
	}
	@Column(name = "rws_ivr_status")
	public String getRwsIvrStatus() {
		return rwsIvrStatus;
	}
	public void setRwsIvrStatus(String rwsIvrStatus) {
		this.rwsIvrStatus = rwsIvrStatus;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rws_ivr_alert_details_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RwsIvrAlertDetails getRwsIvrAlertDetails() {
		return rwsIvrAlertDetails;
	}
	public void setRwsIvrAlertDetails(RwsIvrAlertDetails rwsIvrAlertDetails) {
		this.rwsIvrAlertDetails = rwsIvrAlertDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ivr_respondent_id",insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public IvrRespondent getIvrRespondent() {
		return ivrRespondent;
	}
	public void setIvrRespondent(IvrRespondent ivrRespondent) {
		this.ivrRespondent = ivrRespondent;
	}

	
}
