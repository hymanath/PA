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
@Table(name = "news_response")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NewsResponse  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2824845915064319394L;
	private Long newsResponseId;
	private File file;
	private CandidatePartyFile candidatePartyFile;
	
	public NewsResponse(){};
	public NewsResponse(File file,CandidatePartyFile candidatePartyFile)
	{
	 this.file = file;
	 this.candidatePartyFile = candidatePartyFile;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="news_response_id", unique=true, nullable=false)
	public Long getNewsResponseId() {
		return newsResponseId;
	}
	
	public void setNewsResponseId(Long newsResponseId) {
		this.newsResponseId = newsResponseId;
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
