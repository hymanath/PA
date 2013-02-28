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
@Table(name = "voter_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterInfo extends BaseModel implements Serializable{


	private static final long serialVersionUID = -9150872003511725992L;

	private Long voterInfoId;
	private VoterReportLevel voterReportLevel;
	private Long reportLevelValue;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private Long totalFamilies;
	private Double femaleVotersPercentage;
	private Double maleVotersPercentage;
	private Double familiesPercentage;
	private Double totalVotersPercentage;
	private PublicationDate publicationDate;
	private Long constituencyId;
	
	public VoterInfo(){}
	public VoterInfo(VoterReportLevel voterReportLevel,Long reportLevelValue,
	 Long totalVoters, Long maleVoters, Long femaleVoters, Long totalFamilies,
	 Double femaleVotersPercentage, Double maleVotersPercentage, Double familiesPercentage, Double totalVotersPercentage, PublicationDate publicationDate)
	{
		this.voterReportLevel = voterReportLevel;
		this.reportLevelValue = reportLevelValue;
		this.totalVoters = totalVoters;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
		this.totalFamilies = totalFamilies;
		this.femaleVotersPercentage = femaleVotersPercentage;
		this.maleVotersPercentage = maleVotersPercentage;
		this.familiesPercentage = familiesPercentage;
		this.totalVotersPercentage = totalVotersPercentage;
		this.publicationDate = publicationDate;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_info_id", unique = true , nullable = false)
	public Long getVoterInfoId() {
		return voterInfoId;
	}
	public void setVoterInfoId(Long voterInfoId) {
		this.voterInfoId = voterInfoId;
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
	
	@Column(name = "total_families", length = 15)
	public Long getTotalFamilies() {
		return totalFamilies;
	}
	public void setTotalFamilies(Long totalFamilies) {
		this.totalFamilies = totalFamilies;
	}
	
	@Column(name = "female_voters_percentage")
	public Double getFemaleVotersPercentage() {
		return femaleVotersPercentage;
	}
	public void setFemaleVotersPercentage(Double femaleVotersPercentage) {
		this.femaleVotersPercentage = femaleVotersPercentage;
	}
	
	@Column(name = "male_voters_percentage" )
	public Double getMaleVotersPercentage() {
		return maleVotersPercentage;
	}
	public void setMaleVotersPercentage(Double maleVotersPercentage) {
		this.maleVotersPercentage = maleVotersPercentage;
	}
	
	@Column(name = "families_percentage" )
	public Double getFamiliesPercentage() {
		return familiesPercentage;
	}
	public void setFamiliesPercentage(Double familiesPercentage) {
		this.familiesPercentage = familiesPercentage;
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
