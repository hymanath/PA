package com.itgrids.partyanalyst.model;

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
@Table(name = "debate_candidate_designation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DebateCandidateDesignation extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = 2770484105252236590L;			
	
//`debate_id`int, `start_time` TIMESTAMP ,`end_time` TIMESTAMP,`channel_id`,`telecast_type_id`,`is_deleted` ENUM,`summary` TEXT	
	
	//fields
	private Long debateCandidateDesignationId;
	private Long candidateId;
	private Candidate  candidate;
	private String isDeleted;
    private String isActive;
	private Long debateRepresentativeTypeId;
	private DebateRepresentativeType debateRepresentativeType;
	

	
	//default constructor.
	 
    public DebateCandidateDesignation() {}


    //setters and getters.
    @Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	@Column(name = "debate_candidate_designation_id", unique = true, nullable = false)
	public Long getDebateCandidateDesignationId() {
		return debateCandidateDesignationId;
	}



	public void setDebateCandidateDesignationId(Long debateCandidateDesignationId) {
		this.debateCandidateDesignationId = debateCandidateDesignationId;
	}


	@Column(name ="candidate_id", insertable=false, updatable=false)
	public Long getCandidateId() {
		return candidateId;
	}



	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}


	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}



	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}


	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}



	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}


	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name ="debate_representative_type_id", insertable=false, updatable=false)
	public Long getDebateRepresentativeTypeId() {
		return debateRepresentativeTypeId;
	}


	public void setDebateRepresentativeTypeId(Long debateRepresentativeTypeId) {
		this.debateRepresentativeTypeId = debateRepresentativeTypeId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "debate_representative_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public DebateRepresentativeType getDebateRepresentativeType() {
		return debateRepresentativeType;
	}
	public void setDebateRepresentativeType(
			DebateRepresentativeType debateRepresentativeType) {
		this.debateRepresentativeType = debateRepresentativeType;
	}
	

	
}
