package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "voter_data_available_constituencies")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterDataAvailableConstituencies extends BaseModel implements Serializable{
	private Long voterDataAvailableConstituenciesId;
	private Long constituencyId;
	private Long publicationDateId;
	
	
	public VoterDataAvailableConstituencies()
	{
		
	}
	public VoterDataAvailableConstituencies(Long voterDataAvailableConstituenciesId,Long constituencyId,Long publicationDateId)
	{
		this.voterDataAvailableConstituenciesId = voterDataAvailableConstituenciesId;
		this.constituencyId = constituencyId;
		this.publicationDateId =publicationDateId;
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
	@Column(name = "constituency_id", length = 15)
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name = "publication_date_id", length = 15)
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	
	

}
