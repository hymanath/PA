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

/**
 * gallary_type entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "candidate_gallary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateGallary implements Serializable{

	private static final long serialVersionUID = 4116973597357848823L;
	
	private Long candidateGallaryId;
	private Candidate candidate;
	private Gallary gallary;
	
	public CandidateGallary(){
	}
	
	public CandidateGallary(Candidate candidate,Gallary gallary){
		this.candidate = candidate;
		this.gallary = gallary;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_gallary_id", unique=true, nullable=false)
	public Long getCandidateGallaryId() {
		return candidateGallaryId;
	}

	public void setCandidateGallaryId(Long candidateGallaryId) {
		this.candidateGallaryId = candidateGallaryId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="gallary_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Gallary getGallary() {
		return gallary;
	}

	public void setGallary(Gallary gallary) {
		this.gallary = gallary;
	}
	
}
