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
@Table(name = "voter_party_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterPartyInfo implements Serializable{

	private Long voterPartyInfoId;
	private Long reportLevelId;
	private Long reportLevelValue;
	private Long userId;
	private Long partyId;
	private Long partyVoters;
	private Long partyMaleVoters;
	private Long partyFemaleVoters;
	private Double partyPercentage;
	private Long constituencyId;
	private Long publicationDateId;
	

//Default Constructor
	
	public VoterPartyInfo()
	{
		
	}
//Full Constructor
	
	public VoterPartyInfo( Long voterPartyInfoId, Long reportLevelId,Long reportLevelValue,Long userId,
			 			Long partyId,Long partyVoters, Long partyMaleVoters,Long partyFemaleVoters,
			 			Double partyPercentage,Long constituencyId, Long publicationDateId)
	{
		this.voterPartyInfoId = voterPartyInfoId;
		this.reportLevelId = reportLevelId;
		this.reportLevelValue = reportLevelValue;
		this.userId =userId;
		this.partyId = partyId;
		this.partyVoters =partyVoters;
		this.partyMaleVoters = partyMaleVoters;
		this.partyFemaleVoters = partyFemaleVoters;
		this.partyPercentage =partyPercentage;
		this.constituencyId =constituencyId;
		this.publicationDateId =publicationDateId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_party_info_id", unique = true, nullable = false)
	public Long getVoterPartyInfoId() {
		return voterPartyInfoId;
	}


	public void setVoterPartyInfoId(Long voterPartyInfoId) {
		this.voterPartyInfoId = voterPartyInfoId;
	}

	@Column(name = "report_level_id", length = 15)
	public Long getReportLevelId() {
		return reportLevelId;
	}


	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}

	@Column(name = "report_level_value", length = 15)
	public Long getReportLevelValue() {
		return reportLevelValue;
	}


	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}

	@Column(name = "user_id", length = 15)
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "party_id", length = 15)
	public Long getPartyId() {
		return partyId;
	}


	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "party_voters", length = 15)
	public Long getPartyVoters() {
		return partyVoters;
	}


	public void setPartyVoters(Long partyVoters) {
		this.partyVoters = partyVoters;
	}

	@Column(name = "party_male_voters", length = 15)
	public Long getPartyMaleVoters() {
		return partyMaleVoters;
	}


	public void setPartyMaleVoters(Long partyMaleVoters) {
		this.partyMaleVoters = partyMaleVoters;
	}

	@Column(name = "party_female_voters", length = 15)
	public Long getPartyFemaleVoters() {
		return partyFemaleVoters;
	}


	public void setPartyFemaleVoters(Long partyFemaleVoters) {
		this.partyFemaleVoters = partyFemaleVoters;
	}

	@Column(name = "party_percentage", length = 15)
	public Double getPartyPercentage() {
		return partyPercentage;
	}


	public void setPartyPercentage(Double partyPercentage) {
		this.partyPercentage = partyPercentage;
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

