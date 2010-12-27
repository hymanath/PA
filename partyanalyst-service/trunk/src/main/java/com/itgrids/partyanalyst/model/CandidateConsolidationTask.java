package com.itgrids.partyanalyst.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "candidate_consolidation_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateConsolidationTask extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Long candidateConsolidationTaskId;
	private Candidate candidate;
	private String remarks;
	private Date performedDate;
	private Set<NominationHistory> nominationHistories = new HashSet<NominationHistory>(0);
	
	public CandidateConsolidationTask(){
		
	}
	
	public CandidateConsolidationTask(Long candidateConsolidationTaskId){
		this.candidateConsolidationTaskId = candidateConsolidationTaskId;
	}

	public CandidateConsolidationTask(Candidate candidate, Date performedDate, String remarks, Set<NominationHistory> nominationHistories) {
		this.candidate = candidate;
		this.performedDate = performedDate;
		this.remarks = remarks;
		this.nominationHistories = nominationHistories;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_consolidation_task_id", unique = true, nullable = false)
	public Long getCandidateConsolidationTaskId() {
		return candidateConsolidationTaskId;
	}

	public void setCandidateConsolidationTaskId(Long candidateConsolidationTaskId) {
		this.candidateConsolidationTaskId = candidateConsolidationTaskId;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "performed_date", length = 10)
	public Date getPerformedDate() {
		return performedDate;
	}

	public void setPerformedDate(Date performedDate) {
		this.performedDate = performedDate;
	}

	@Column(name = "remarks", length = 100)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "candidateConsolidationTask")
	public Set<NominationHistory> getNominationHistories() {
		return nominationHistories;
	}

	public void setNominationHistories(Set<NominationHistory> nominationHistories) {
		this.nominationHistories = nominationHistories;
	}

}
