package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;




@Entity
@Table(name = "call_center_feedback")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CallCenterFeedback extends BaseModel implements java.io.Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1631883714869101027L;
	private Long callCenterFeedbackId;
	private String comments;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "call_center_feedback_id", unique = true, nullable = false)
	public Long getCallCenterFeedbackId() {
		return callCenterFeedbackId;
	}
	public void setCallCenterFeedbackId(Long callCenterFeedbackId) {
		this.callCenterFeedbackId = callCenterFeedbackId;
	}
	
	@Column(name="comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
