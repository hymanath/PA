package com.itgrids.partyanalyst.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Table(name = "keyword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Keyword extends BaseModel implements java.io.Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9209553303248814427L;
	private Long keywordId;
	private String type;
	private String description;
	private Date createdDate;
	private Long createdBy;
	private Set<GallaryKeyword> gallarykeywords = new HashSet<GallaryKeyword>(0);
	private Set<CandidateFileKeyword> candidateFileKeywords = new HashSet<CandidateFileKeyword>(0);
    private Set<PartyFileKeyword> partyFileKeywords = new HashSet<PartyFileKeyword>(0);
	
	
	public Keyword() {

	}

	/** full constructor */
	public Keyword(Long keywordId, String type,  String description,Date createdDate, Long createdBy,Set<GallaryKeyword> gallarykeywords) {
		this.keywordId = keywordId;
		this.type = type;
		this.description = description;	
		this.createdDate = createdDate;
		this.createdBy=createdBy;
		this.gallarykeywords=gallarykeywords;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "keyword_id", unique = true, nullable = false)
	public Long getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}
    
	@Column(name = "type", length = 150)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date", length = 10)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
   
	@Column(name = "created_by", length = 10)
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "keyword")
	public Set<GallaryKeyword> getGallaryKeywords() {
		return gallarykeywords;
	}

	public void setGallaryKeywords(Set<GallaryKeyword> gallarykeywords) {
		this.gallarykeywords = gallarykeywords;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "keyword")
	public Set<CandidateFileKeyword> getCandidateFileKeywords() {
		return candidateFileKeywords;
	}

	public void setCandidateFileKeywords(
			Set<CandidateFileKeyword> candidateFileKeywords) {
		this.candidateFileKeywords = candidateFileKeywords;
	}

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "keyword")
	public Set<PartyFileKeyword> getPartyFileKeywords() {
		return partyFileKeywords;
	}

	public void setPartyFileKeywords(Set<PartyFileKeyword> partyFileKeywords) {
		this.partyFileKeywords = partyFileKeywords;
	}
	
	

}
