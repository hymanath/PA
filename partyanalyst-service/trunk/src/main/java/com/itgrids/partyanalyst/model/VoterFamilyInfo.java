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
@Table(name = "voter_family_info")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class VoterFamilyInfo extends BaseModel implements Serializable{

	
	private static final long serialVersionUID = 8674796025422461412L;
	private Long voterFamilyInfoId;
	private VoterReportLevel voterReportLevel;
	private VoterFamilyRange voterFamilyRange;
	private Long totalFamilies;
	private Double familiesPercentage;
	
	public VoterFamilyInfo(){}
	
	public VoterFamilyInfo(VoterReportLevel voterReportLevel, VoterFamilyRange voterFamilyRange, Long totalFamilies, Double familiesPercentage)
	{
		this.voterReportLevel = voterReportLevel;
		this.voterFamilyRange = voterFamilyRange;
		this.totalFamilies = totalFamilies;
		this.familiesPercentage = familiesPercentage;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_family_info_id",unique = true, nullable=false)
	public Long getVoterFamilyInfoId() {
		return voterFamilyInfoId;
	}

	public void setVoterFamilyInfoId(Long voterFamilyInfoId) {
		this.voterFamilyInfoId = voterFamilyInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "report_level_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public VoterReportLevel getVoterReportLevel() {
		return voterReportLevel;
	}

	public void setVoterReportLevel(VoterReportLevel voterReportLevel) {
		this.voterReportLevel = voterReportLevel;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "family_range_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public VoterFamilyRange getVoterFamilyRange() {
		return voterFamilyRange;
	}

	public void setVoterFamilyRange(VoterFamilyRange voterFamilyRange) {
		this.voterFamilyRange = voterFamilyRange;
	}

	@Column(name = "total_families", length = 15)
	public Long getTotalFamilies() {
		return totalFamilies;
	}

	public void setTotalFamilies(Long totalFamilies) {
		this.totalFamilies = totalFamilies;
	}

	@Column(name = "families_percentage")
	public Double getFamiliesPercentage() {
		return familiesPercentage;
	}

	public void setFamiliesPercentage(Double familiesPercentage) {
		this.familiesPercentage = familiesPercentage;
	}
	
	
	
}
