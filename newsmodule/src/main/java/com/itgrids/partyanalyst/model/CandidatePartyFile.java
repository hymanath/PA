package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "candidate_party_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidatePartyFile extends BaseModel implements Serializable{
	

	private static final long serialVersionUID = -3590360726983582784L;
	private Long candidatePartyFileId;
	private Candidate sourceCandidate;
	private Candidate destinationCandidate;
	private File file;
	private Party sourceParty; 
	private Party destinationParty;
	private Date createdDate;
	private Date updateddate;
	private Long mediaId;
	private Benefit sourceBenefit;
	private Benefit destinationBenefit;
	
    private Set<NewsResponse> newsResponse = new HashSet<NewsResponse>(0);
	public CandidatePartyFile(){}
	
	public CandidatePartyFile(Candidate sourceCandidate,Candidate destinationCandidate,Party sourceParty,Party destinationParty,
			Date createdDate,Date updateddate,File file,Benefit sourceBenefit,Benefit destinationBenefit,Long mediaId){
		
		this.sourceCandidate = sourceCandidate;
		this.destinationCandidate = destinationCandidate;
		this.sourceParty = sourceParty;
		this.destinationParty = destinationParty;
		this.createdDate = createdDate;
		this.updateddate = updateddate;
		this.file = file;
		this.sourceBenefit = sourceBenefit;
		this.mediaId = mediaId;
		this.destinationBenefit = destinationBenefit;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "candidate_party_file_id", unique= true, nullable = false)
	public Long getCandidatePartyFileId() {
		return candidatePartyFileId;
	}

	public void setCandidatePartyFileId(Long candidatePartyFileId) {
		this.candidatePartyFileId = candidatePartyFileId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="source_candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getSourceCandidate() {
		return sourceCandidate;
	}

	public void setSourceCandidate(Candidate sourceCandidate) {
		this.sourceCandidate = sourceCandidate;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="destination_candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getDestinationCandidate() {
		return destinationCandidate;
	}

	public void setDestinationCandidate(Candidate destinationCandidate) {
		this.destinationCandidate = destinationCandidate;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="source_party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getSourceParty() {
		return sourceParty;
	}

	public void setSourceParty(Party sourceParty) {
		this.sourceParty = sourceParty;
	}
    
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="destination_party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getDestinationParty() {
		return destinationParty;
	}

	public void setDestinationParty(Party destinationParty) {
		this.destinationParty = destinationParty;
	}
   
	@Column(name = "media_id", length=15)
	public Long getMediaId() {
		return mediaId;
	}

	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}

	@Column(name = "created_date", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "updated_date", length = 10)
	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
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
	@JoinColumn(name="source_benefit_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Benefit getSourceBenefit() {
		return sourceBenefit;
	}

	public void setSourceBenefit(Benefit sourceBenefit) {
		this.sourceBenefit = sourceBenefit;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="destination_benefit_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Benefit getDestinationBenefit() {
		return destinationBenefit;
	}

	public void setDestinationBenefit(Benefit destinationBenefit) {
		this.destinationBenefit = destinationBenefit;
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "candidatePartyFile")
	public Set<NewsResponse> getNewsResponse() {
		return newsResponse;
	}

	public void setNewsResponse(Set<NewsResponse> newsResponse) {
		this.newsResponse = newsResponse;
	}
	

}
