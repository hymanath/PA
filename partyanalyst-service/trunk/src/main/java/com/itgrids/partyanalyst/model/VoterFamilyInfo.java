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
	private PublicationDate publicationDate;
	private Long reportLevelValue;
	private Long constituencyId;
	
	public VoterFamilyInfo(){}
	
	public VoterFamilyInfo(VoterReportLevel voterReportLevel, VoterFamilyRange voterFamilyRange, Long totalFamilies, Double familiesPercentage, PublicationDate publicationDate, Long reportLevelValue)
	{
		this.voterReportLevel = voterReportLevel;
		this.voterFamilyRange = voterFamilyRange;
		this.totalFamilies = totalFamilies;
		this.familiesPercentage = familiesPercentage;
		this.publicationDate = publicationDate;
		this.reportLevelValue = reportLevelValue;
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

	@Column(name = "report_level_value", length = 15)
	public Long getReportLevelValue() {
		return reportLevelValue;
	}

	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}
	
	@Column(name = "constituency_id", length = 15)
	public Long getConstituencyId() {
	return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
	this.constituencyId = constituencyId;
	}
	
}
