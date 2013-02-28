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
@Table(name = "voter_age_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterAgeInfo extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 3782433950691131454L;
	
	private Long voterAgeInfoId;
	private VoterReportLevel voterReportLevel;
	private VoterAgeRange voterAgeRange;
	private Long reportLevelValue;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private Double femaleVotersPercentage;
	private Double maleVotersPercentage;
	private Double totalVotersPercentage;
	private PublicationDate publicationDate;
	private Long constituencyId;
	
	public VoterAgeInfo(){}
	
	public VoterAgeInfo(VoterReportLevel voterReportLevel,VoterAgeRange voterAgeRange,
			Long reportLevelValue,Long totalVoters,Long maleVoters, Long femaleVoters,
			Double femaleVotersPercentage, Double maleVotersPercentage,Double totalVotersPercentage, PublicationDate publicationDate)
	{
		this.voterReportLevel = voterReportLevel;
		this.voterAgeRange = voterAgeRange;
		this.reportLevelValue = reportLevelValue;
		this.totalVoters = totalVoters;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.femaleVotersPercentage = femaleVotersPercentage;
		this.maleVotersPercentage = maleVotersPercentage;
		this.totalVotersPercentage = totalVotersPercentage;
		this.publicationDate = publicationDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_age_info_id", unique = true, nullable = false)
	public Long getVoterAgeInfoId() {
		return voterAgeInfoId;
	}

	public void setVoterAgeInfoId(Long voterAgeInfoId) {
		this.voterAgeInfoId = voterAgeInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "report_level_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterReportLevel getVoterReportLevel() {
		return voterReportLevel;
	}

	public void setVoterReportLevel(VoterReportLevel voterReportLevel) {
		this.voterReportLevel = voterReportLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "age_range_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterAgeRange getVoterAgeRange() {
		return voterAgeRange;
	}

	public void setVoterAgeRange(VoterAgeRange voterAgeRange) {
		this.voterAgeRange = voterAgeRange;
	}

	@Column(name = "report_level_value", length = 15)
	public Long getReportLevelValue() {
		return reportLevelValue;
	}

	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}

	@Column(name = "total_voters", length = 15)
	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	@Column(name = "male_voters", length = 15)
	public Long getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}

	@Column(name = "female_voters", length = 15)
	public Long getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}

	@Column(name = "female_voters_percentage")
	public Double getFemaleVotersPercentage() {
		return femaleVotersPercentage;
	}

	public void setFemaleVotersPercentage(Double femaleVotersPercentage) {
		this.femaleVotersPercentage = femaleVotersPercentage;
	}

	@Column(name = "male_voters_percentage")
	public Double getMaleVotersPercentage() {
		return maleVotersPercentage;
	}

	public void setMaleVotersPercentage(Double maleVotersPercentage) {
		this.maleVotersPercentage = maleVotersPercentage;
	}

	@Column(name = "total_voters_percentage")
	public Double getTotalVotersPercentage() {
		return totalVotersPercentage;
	}

	public void setTotalVotersPercentage(Double totalVotersPercentage) {
		this.totalVotersPercentage = totalVotersPercentage;
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
	
	@Column(name = "constituency_id", length = 15)
	public Long getConstituencyId() {
	return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
	this.constituencyId = constituencyId;
	}

}
