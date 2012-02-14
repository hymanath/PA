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
@Table(name= "constituency_lead_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  public class ConstituencyLeadCandidate extends BaseModel implements Serializable {
    private Long constituencyLeadCandidateId;
    private ConstituencyElection constituencyElection;
    private Candidate candidate;
    private String status;
    
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="constituency_lead_candidate_id", unique = true, nullable = false)
	public Long getConstituencyLeadCandidateId() {
		return constituencyLeadCandidateId;
	}
	public void setConstituencyLeadCandidateId(Long constituencyLeadCandidateId) {
		this.constituencyLeadCandidateId = constituencyLeadCandidateId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "consti_elec_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ConstituencyElection getConstituencyElection() {
		return constituencyElection;
	}
	public void setConstituencyElection(ConstituencyElection constituencyElection) {
		this.constituencyElection = constituencyElection;
	}
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	@Column(name = "status", length = 50)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
  
   
	
  
}
