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
@Table(name="cadre_calling_feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreCallingFeedback implements java.io.Serializable {
	private Long cadreCallingFeedbackId;
	private Long tdpCadreId;
	private Long callPurposeId;
	private Long callSupportTypeId;
	private Long callStatusId;
	private String callDescription;
	private Long calledBy;
	private Date callTime;
	
	private CallStatus callStatus;
	private CallSupportType callSupportType;
	private CallPurpose callPurpose;
	private TdpCadre tdpCadre;
	private User user;
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name = "cadre_calling_feedback_id",nullable = false, unique = true)
	public Long getCadreCallingFeedbackId() {
		return cadreCallingFeedbackId;
	}
	public void setCadreCallingFeedbackId(Long cadreCallingFeedbackId) {
		this.cadreCallingFeedbackId = cadreCallingFeedbackId;
	}
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name = "call_purpose_id")
	public Long getCallPurposeId() {
		return callPurposeId;
	}
	public void setCallPurposeId(Long callPurposeId) {
		this.callPurposeId = callPurposeId;
	}
	@Column(name = "call_support_type_id")
	public Long getCallSupportTypeId() {
		return callSupportTypeId;
	}
	public void setCallSupportTypeId(Long callSupportTypeId) {
		this.callSupportTypeId = callSupportTypeId;
	}
	@Column(name = "call_status_id")
	public Long getCallStatusId() {
		return callStatusId;
	}
	public void setCallStatusId(Long callStatusId) {
		this.callStatusId = callStatusId;
	}
	@Column(name = "call_description")
	public String getCallDescription() {
		return callDescription;
	}
	public void setCallDescription(String callDescription) {
		this.callDescription = callDescription;
	}
	@Column(name = "called_by")
	public Long getCalledBy() {
		return calledBy;
	}
	public void setCalledBy(Long calledBy) {
		this.calledBy = calledBy;
	}
	@Column(name = "call_time")
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="call_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CallStatus getCallStatus() {
		return callStatus;
	}
	public void setCallStatus(CallStatus callStatus) {
		this.callStatus = callStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="call_support_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CallSupportType getCallSupportType() {
		return callSupportType;
	}
	public void setCallSupportType(CallSupportType callSupportType) {
		this.callSupportType = callSupportType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="call_purpose_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CallPurpose getCallPurpose() {
		return callPurpose;
	}
	public void setCallPurpose(CallPurpose callPurpose) {
		this.callPurpose = callPurpose;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="called_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
