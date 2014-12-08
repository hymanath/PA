package com.itgrids.partyanalyst.model;

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



@Entity
@Table(name = "tdp_cadre_call_center_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TdpCadreCallCenterComment {
	
	
	private Long tdpCadreCallCenterCommentId;
	private TdpCadreCallCenterFeedback tdpCadreCallCenterFeedback;
	private Long tdpCadreCallCenterFeedbackId;
	private CallCenterFeedback callCenterFeedback;
	private Long callCenterFeedbackId;
	private String isDelete;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tdp_cadre_call_center_comment_id", unique = true, nullable = false)	
	public Long getTdpCadreCallCenterCommentId() {
		return tdpCadreCallCenterCommentId;
	}
	public void setTdpCadreCallCenterCommentId(Long tdpCadreCallCenterCommentId) {
		this.tdpCadreCallCenterCommentId = tdpCadreCallCenterCommentId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_call_center_feedback_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public TdpCadreCallCenterFeedback getTdpCadreCallCenterFeedback() {
		return tdpCadreCallCenterFeedback;
	}
	public void setTdpCadreCallCenterFeedback(
			TdpCadreCallCenterFeedback tdpCadreCallCenterFeedback) {
		this.tdpCadreCallCenterFeedback = tdpCadreCallCenterFeedback;
	}
	
	@Column(name="tdp_cadre_call_center_feedback_id")
	public Long getTdpCadreCallCenterFeedbackId() {
		return tdpCadreCallCenterFeedbackId;
	}
	public void setTdpCadreCallCenterFeedbackId(Long tdpCadreCallCenterFeedbackId) {
		this.tdpCadreCallCenterFeedbackId = tdpCadreCallCenterFeedbackId;
	}
	
	@Column(name="call_center_feedback_id")
	public Long getCallCenterFeedbackId() {
		return callCenterFeedbackId;
	}
	public void setCallCenterFeedbackId(Long callCenterFeedbackId) {
		this.callCenterFeedbackId = callCenterFeedbackId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "call_center_feedback_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public CallCenterFeedback getCallCenterFeedback() {
		return callCenterFeedback;
	}
	public void setCallCenterFeedback(CallCenterFeedback callCenterFeedback) {
		this.callCenterFeedback = callCenterFeedback;
	}
	
	@Column(name="is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

}
