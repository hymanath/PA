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
@Table(name = "candidate_news_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateNewsResponse extends BaseModel{
	
	private static final long serialVersionUID = 1L;
    
	private Long candidateNewsResponseId;	
	private FileGallary fileGallary;
	private FileGallary responseFileGallary;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_news_response_id", nullable = false, unique = true)
	public Long getCandidateNewsResponseId() {
		return candidateNewsResponseId;
	}
	public void setCandidateNewsResponseId(Long candidateNewsResponseId) {
		this.candidateNewsResponseId = candidateNewsResponseId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "file_gallery_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FileGallary getFileGallary() {
		return fileGallary;
	}
	public void setFileGallary(FileGallary fileGallary) {
		this.fileGallary = fileGallary;
	}
	
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "response_gallery_id")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public FileGallary getResponseFileGallary() {
		return responseFileGallary;
	}
	
	public void setResponseFileGallary(FileGallary responseFileGallary) {
		this.responseFileGallary = responseFileGallary;
	} 

}
