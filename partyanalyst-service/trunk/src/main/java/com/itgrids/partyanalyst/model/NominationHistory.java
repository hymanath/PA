package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "nomination_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NominationHistory extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 2606222177791046525L;
	private Long nominationHistoryId;
	private CandidateConsolidationTask candidateConsolidationTask;
	private Candidate candidate;
	private Nomination nomination;
	private Date modifiedDate;
	
	public NominationHistory(){
		
	}
	
	public NominationHistory(Long nominationHistoryId){
		this.nominationHistoryId = nominationHistoryId;
	}

	public NominationHistory(CandidateConsolidationTask candidateConsolidationTask,
			Candidate candidate, Nomination nomination, Date modifiedDate) {
		this.candidateConsolidationTask = candidateConsolidationTask;
		this.candidate = candidate;
		this.nomination = nomination;
		this.modifiedDate = modifiedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "nomination_history_id", unique = true, nullable = false)
	public Long getNominationHistoryId() {
		return nominationHistoryId;
	}

	public void setNominationHistoryId(Long nominationHistoryId) {
		this.nominationHistoryId = nominationHistoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_consolidation_task_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CandidateConsolidationTask getCandidateConsolidationTask() {
		return candidateConsolidationTask;
	}

	public void setCandidateConsolidationTask(
			CandidateConsolidationTask candidateConsolidationTask) {
		this.candidateConsolidationTask = candidateConsolidationTask;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nomination_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Nomination getNomination() {
		return nomination;
	}

	public void setNomination(Nomination nomination) {
		this.nomination = nomination;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
