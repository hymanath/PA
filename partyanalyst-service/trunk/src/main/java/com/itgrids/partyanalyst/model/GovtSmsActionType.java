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
@Table(name = "govt_sms_action_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtSmsActionType extends BaseModel implements Serializable  {

	private Long govtSmsActionTypeId;
	private Long govtAlertActionTypeId;
	private Long govtSmsTypeId;
	private Long languageId;
	private Long govtUserTypeId;
	private String smsText;
	
	private GovtAlertActionType govtAlertActionType;
	private GovtSmsType govtSmsType;
	private Language language;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_sms_action_type_id", unique = true, nullable = false)
	public Long getGovtSmsActionTypeId() {
		return govtSmsActionTypeId;
	}
	public void setGovtSmsActionTypeId(Long govtSmsActionTypeId) {
		this.govtSmsActionTypeId = govtSmsActionTypeId;
	}
	
	@Column(name = "govt_alert_action_type_id")
	public Long getGovtAlertActionTypeId() {
		return govtAlertActionTypeId;
	}
	public void setGovtAlertActionTypeId(Long govtAlertActionTypeId) {
		this.govtAlertActionTypeId = govtAlertActionTypeId;
	}
	
	@Column(name = "govt_sms_type_id")
	public Long getGovtSmsTypeId() {
		return govtSmsTypeId;
	}
	public void setGovtSmsTypeId(Long govtSmsTypeId) {
		this.govtSmsTypeId = govtSmsTypeId;
	}
	
	@Column(name = "language_id")
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	
	@Column(name = "govt_user_type_id")
	public Long getGovtUserTypeId() {
		return govtUserTypeId;
	}
	public void setGovtUserTypeId(Long govtUserTypeId) {
		this.govtUserTypeId = govtUserTypeId;
	}
	
	@Column(name = "sms_text")
	public String getSmsText() {
		return smsText;
	}
	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_alert_action_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtAlertActionType getGovtAlertActionType() {
		return govtAlertActionType;
	}
	public void setGovtAlertActionType(GovtAlertActionType govtAlertActionType) {
		this.govtAlertActionType = govtAlertActionType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_sms_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtSmsType getGovtSmsType() {
		return govtSmsType;
	}
	public void setGovtSmsType(GovtSmsType govtSmsType) {
		this.govtSmsType = govtSmsType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}	
	
}
