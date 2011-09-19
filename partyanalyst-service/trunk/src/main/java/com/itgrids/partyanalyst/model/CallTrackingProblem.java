package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="calltracking_problem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CallTrackingProblem extends BaseModel implements Serializable {
	
	private Long problemId;
	private String problemPurpose;
	private String referenceNo;
	private CallTrackingDetail callTrackingDetail;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "problem_id", unique = true, nullable = false)
	public Long getProblemId() {
		return problemId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	
	@Column(name = "problem_purpose" , length = 100)
	public String getProblemPurpose() {
		return problemPurpose;
	}
	public void setProblemPurpose(String problemPurpose) {
		this.problemPurpose = problemPurpose;
	}
	
	@Column(name = "reference_no" , length = 200)
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "callTrackingProblem")
	public CallTrackingDetail getCallTrackingDetail() {
		return callTrackingDetail;
	}
	
	public void setCallTrackingDetail(CallTrackingDetail callTrackingDetail) {
		this.callTrackingDetail = callTrackingDetail;
	}

}
