package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "committe_ivr_total_detail")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommitteIvrTotalDetail extends BaseModel implements Serializable{

	private Long committeIvrTotalDetailId;
	private Long totalVilages;
	private Long totalIvrCalls;
	private Long totalLifted;
	private String state;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="committe_ivr_total_detail_id", unique=true, nullable=false)
	public Long getCommitteIvrTotalDetailId() {
		return committeIvrTotalDetailId;
	}
	public void setCommitteIvrTotalDetailId(Long committeIvrTotalDetailId) {
		this.committeIvrTotalDetailId = committeIvrTotalDetailId;
	}
	@Column(name="total_villages")
	public Long getTotalVilages() {
		return totalVilages;
	}
	public void setTotalVilages(Long totalVilages) {
		this.totalVilages = totalVilages;
	}
	@Column(name="total_calls")
	public Long getTotalIvrCalls() {
		return totalIvrCalls;
	}
	public void setTotalIvrCalls(Long totalIvrCalls) {
		this.totalIvrCalls = totalIvrCalls;
	}
	@Column(name="total_lifted")
	public Long getTotalLifted() {
		return totalLifted;
	}
	public void setTotalLifted(Long totalLifted) {
		this.totalLifted = totalLifted;
	}
	@Column(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
}
