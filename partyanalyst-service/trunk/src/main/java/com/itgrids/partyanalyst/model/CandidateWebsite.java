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
@Table(name="candidate_website")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateWebsite extends BaseModel implements java.io.Serializable {
	
	private Long candidateWebsiteId;
	private Candidate candidate;
	private String websiteAddress;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_website_id", unique = true, nullable = false)
	public Long getCandidateWebsiteId() {
		return candidateWebsiteId;
	}
	public void setCandidateWebsiteId(Long candidateWebsiteId) {
		this.candidateWebsiteId = candidateWebsiteId;
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
	
	@Column(name="website_address", length = 50)
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
	
	

}
