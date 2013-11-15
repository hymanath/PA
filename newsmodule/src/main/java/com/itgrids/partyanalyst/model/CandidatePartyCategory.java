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
@Table(name = "candidate_party_category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidatePartyCategory extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = -1969776487788436316L;
	private Long candidatePartyCategoryId;
	private Category category;
	private CandidatePartyFile candidatePartyFile;
	
	public CandidatePartyCategory(){}
	
	public CandidatePartyCategory(Category category,CandidatePartyFile candidatePartyFile){
		
		this.category = category;
		this.candidatePartyFile = candidatePartyFile;
	}
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_party_category_id",unique = true, nullable = false)
	public Long getCandidatePartyCategoryId() {
		return candidatePartyCategoryId;
	}

	public void setCandidatePartyCategoryId(Long candidatePartyCategoryId) {
		this.candidatePartyCategoryId = candidatePartyCategoryId;
	}
   
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_party_file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CandidatePartyFile getCandidatePartyFile() {
		return candidatePartyFile;
	}

	public void setCandidatePartyFile(CandidatePartyFile candidatePartyFile) {
		this.candidatePartyFile = candidatePartyFile;
	}
	
	

}
