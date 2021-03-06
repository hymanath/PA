package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "key_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KeyCandidate extends BaseModel implements java.io.Serializable{

	private static final long serialVersionUID = -2235094721616290412L;
	private Long keyCandidateId;
	private Candidate candidate;
	private String description;
	
	// Constructors

	/** default constructor */
	public KeyCandidate(){
	}
	
	/** full constructor */
	public KeyCandidate(String description,Candidate candidate)
	{
		this.description = description;
		this.candidate = candidate;
	}
	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "key_candidate_id" , unique = true, nullable = false)
	public Long getKeyCandidateId() {
		return keyCandidateId;
	}
	
	public void setKeyCandidateId(Long keyCandidateId) {
		this.keyCandidateId = keyCandidateId;
	}
	@Column(name = "description" ,length = 500)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "candidate_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
}
