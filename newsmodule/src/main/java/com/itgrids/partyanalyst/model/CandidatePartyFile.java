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
	private Candidate candidate;
	private File file;
	private Party party; 
	private Date createdDate;
	private Date updateddate;
	
	
	public CandidatePartyFile(){}
	
	public CandidatePartyFile(Candidate candidate,Party party,Date createdDate,Date updateddate,File file){
		
		this.candidate = candidate;
		this.party = party;
		this.createdDate = createdDate;
		this.updateddate = updateddate;
		this.file = file;
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
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
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

}
