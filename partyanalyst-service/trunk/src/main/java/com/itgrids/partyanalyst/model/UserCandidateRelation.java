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
 * user_candidate_relation entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "user_candidate_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserCandidateRelation implements Serializable{

	private static final long serialVersionUID = 4639514694972954499L;
	private Long userCandidateRelationId;
	private Registration registration;
	private Candidate candidate;
	
	public UserCandidateRelation()
	{}
	
	public UserCandidateRelation(Registration registration,Candidate candidate)
	{
		this.registration = registration;
		this.candidate = candidate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_candidate_relation_id",unique = true,nullable = false)
	public Long getUserCandidateRelationId() {
		return userCandidateRelationId;
	}

	public void setUserCandidateRelationId(Long userCandidateRelationId) {
		this.userCandidateRelationId = userCandidateRelationId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	
	

}
