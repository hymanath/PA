package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "candidate_file_keyword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateFileKeyword extends BaseModel implements Serializable{
	
	
	private static final long serialVersionUID = -6750833615375084090L;
	private Long candidateFileKeywordId;
	private Candidate candidate;
	private File file;
	private Keyword keyword;
	
	public CandidateFileKeyword(){}
	
	public CandidateFileKeyword(Candidate candidate,File file,Keyword keyword){
	   this.candidate = candidate;
	   this.file = file;
	   this.keyword = keyword;
	}
    
	@Id
	@Column(name = "candidate_file_keyword_id", unique = true, nullable = false)
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	public Long getCandidateFileKeywordId() {
		return candidateFileKeywordId;
	}

	public void setCandidateFileKeywordId(Long candidateFileKeywordId) {
		this.candidateFileKeywordId = candidateFileKeywordId;
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
	@JoinColumn(name="file_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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
