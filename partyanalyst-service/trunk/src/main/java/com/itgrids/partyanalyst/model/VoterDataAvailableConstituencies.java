package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "voter_data_available_constituencies")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterDataAvailableConstituencies extends BaseModel implements Serializable{
	private Long voterDataAvailableConstituenciesId;
	private Constituency constituency;
	private PublicationDate publicationDate;
	
	
	public VoterDataAvailableConstituencies()
	{
		
	}
	public VoterDataAvailableConstituencies(Long voterDataAvailableConstituenciesId,Constituency constituency,PublicationDate publicationDate)
	{
		this.voterDataAvailableConstituenciesId = voterDataAvailableConstituenciesId;
		this.constituency = constituency;
		this.publicationDate =publicationDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="voter_data_available_constituencies_id", unique=true, nullable=false)
	public Long getVoterDataAvailableConstituenciesId() {
		return voterDataAvailableConstituenciesId;
	}
	public void setVoterDataAvailableConstituenciesId(
			Long voterDataAvailableConstituenciesId) {
		this.voterDataAvailableConstituenciesId = voterDataAvailableConstituenciesId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_date_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PublicationDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(PublicationDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	

}
