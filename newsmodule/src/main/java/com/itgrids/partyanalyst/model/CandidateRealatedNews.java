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
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "candidate_realated_news")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateRealatedNews extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private Long candidateRealatedNewsId;	
	private Candidate candidate;
	private FileGallary fileGallary;
	private String isResponse;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_realated_news_id", nullable = false, unique = true)
	public Long getCandidateRealatedNewsId() {
		return candidateRealatedNewsId;
	}

	public void setCandidateRealatedNewsId(Long candidateRealatedNewsId) {
		this.candidateRealatedNewsId = candidateRealatedNewsId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "file_gallary_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FileGallary getFileGallary() {
		return fileGallary;
	}

	public void setFileGallary(FileGallary fileGallary) {
		this.fileGallary = fileGallary;
	}

	
    @Column(name="is_response" , length = 5)
	public String getIsResponse() {
		return isResponse;
	}

	public void setIsResponse(String isResponse) {
		this.isResponse = isResponse;
	}
	

}
