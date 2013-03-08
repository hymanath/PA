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
@Table(name = "voter_modification_age_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterModificationAgeInfo implements Serializable{
	
	private Long voterModificationAgeInfoId;
	private Long voterModificationInfoId;
	private Long voterAgeRangeId;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	
	
	//DefaultConstructor
	public VoterModificationAgeInfo()
	{
		
	}
	
	//Full Constructor
	public VoterModificationAgeInfo(Long voterModificationAgeInfoId,Long voterModificationInfoId,
			                         Long voterAgeRangeId,Long totalVoters,Long maleVoters,Long femaleVoters)
	{
		
		this.voterModificationAgeInfoId = voterModificationAgeInfoId;
		this.voterModificationInfoId = voterModificationAgeInfoId;
		this.voterAgeRangeId =voterAgeRangeId;
		this.totalVoters =totalVoters;
		this.maleVoters=maleVoters;
		this.femaleVoters = femaleVoters;
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_modification_age_info_id", unique = true, nullable = false)
	public Long getVoterModificationAgeInfoId() {
		return voterModificationAgeInfoId;
	}

	public void setVoterModificationAgeInfoId(Long voterModificationAgeInfoId) {
		this.voterModificationAgeInfoId = voterModificationAgeInfoId;
	}
	@Column(name = "voter_modification_info_id", length = 15)
	public Long getVoterModificationInfoId() {
		return voterModificationInfoId;
	}

	public void setVoterModificationInfoId(Long voterModificationInfoId) {
		this.voterModificationInfoId = voterModificationInfoId;
	}
	@Column(name="voter_age_range_id",length = 15)
	public Long getVoterAgeRangeId() {
		return voterAgeRangeId;
	}

	public void setVoterAgeRangeId(Long voterAgeRangeId) {
		this.voterAgeRangeId = voterAgeRangeId;
	}
	@Column(name="total_voters" ,length = 15)
	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	@Column(name="male_voters",length = 15)
	public Long getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	@Column(name="female_voters",length = 15)
	public Long getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	

}
