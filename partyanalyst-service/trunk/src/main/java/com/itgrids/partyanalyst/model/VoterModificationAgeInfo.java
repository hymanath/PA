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
@Table(name = "voter_modification_age_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterModificationAgeInfo implements Serializable{
	
	private Long voterModificationAgeInfoId;
	private VoterModificationInfo voterModificationInfo;
	private Long voterAgeRangeId;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	
	
	//DefaultConstructor
	public VoterModificationAgeInfo()
	{
		
	}
	
	//Full Constructor
	public VoterModificationAgeInfo(Long voterModificationAgeInfoId,VoterModificationInfo voterModificationInfo,
			                         Long voterAgeRangeId,Long totalVoters,Long maleVoters,Long femaleVoters)
	{
		
		this.voterModificationAgeInfoId = voterModificationAgeInfoId;
		this.voterModificationInfo = voterModificationInfo;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_modification_info_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterModificationInfo getVoterModificationInfo() {
		return voterModificationInfo;
	}

	public void setVoterModificationInfo(VoterModificationInfo voterModificationInfo) {
		this.voterModificationInfo = voterModificationInfo;
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
