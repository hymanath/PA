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
@Table(name = "candidate_party_keyword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidatePartyKeyword extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = -5559210717151124245L;
	private Long candidatePartyKeywordId;
	private CandidatePartyFile candidatePartyFile;
	private Keyword keyword;
	
	
	public CandidatePartyKeyword(){}
	
	public CandidatePartyKeyword(CandidatePartyFile candidatePartyFile,Keyword keyword)
	{
		this.candidatePartyFile = candidatePartyFile;
		this.keyword = keyword;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="candidate_party_keyword_id", unique = true, nullable = false)
	public Long getCandidatePartyKeywordId() {
		return candidatePartyKeywordId;
	}

	public void setCandidatePartyKeywordId(Long candidatePartyKeywordId) {
		this.candidatePartyKeywordId = candidatePartyKeywordId;
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

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="keyword_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

		

}
