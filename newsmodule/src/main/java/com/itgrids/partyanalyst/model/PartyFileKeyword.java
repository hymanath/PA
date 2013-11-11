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
@Table(name = "party_file_keyword")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyFileKeyword extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -4482091875789935853L;
	private Long partyFileKeywordId;
	private Party party;
	private File file;
	private Keyword keyword;
	
	public PartyFileKeyword(){}
	
	public PartyFileKeyword(Party party,File file,Keyword keyword)
	{
	  this.party = party;
	  this.file = file;
	  this.keyword = keyword;
	}
   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_file_keyword_id", unique = true, nullable = false)
	public Long getPartyFileKeywordId() {
		return partyFileKeywordId;
	}

	public void setPartyFileKeywordId(Long partyFileKeywordId) {
		this.partyFileKeywordId = partyFileKeywordId;
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
