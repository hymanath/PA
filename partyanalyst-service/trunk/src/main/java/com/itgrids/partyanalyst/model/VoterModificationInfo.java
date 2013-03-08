package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "voter_modification_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterModificationInfo extends BaseModel implements Serializable{

	private static final long serialVersionUID = -2554838062515450464L;
	private Long voterModificationInfoId;
	private VoterReportLevel voterReportLevel;
	private Long reportLevelValue;
	private PublicationDate publicationDate;
	private Long constituencyId;
	private VoterStatus voterStatus;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private Set<VoterModificationAgeInfo> voterModificationAgeInfos = new HashSet<VoterModificationAgeInfo>(0);
	
	public VoterModificationInfo(){}
	
	public VoterModificationInfo(VoterReportLevel voterReportLevel,Long reportLevelValue,PublicationDate publicationDate,
			Long constituencyId, VoterStatus voterStatus, Long totalVoters,Long maleVoters,Long femaleVoters)
	{
		this.voterReportLevel = voterReportLevel;
		this.reportLevelValue = reportLevelValue;
		this.publicationDate = publicationDate;
		this.constituencyId = constituencyId;
		this.voterStatus = voterStatus;
		this.totalVoters = totalVoters;
		this.maleVoters = maleVoters;
		this.femaleVoters = femaleVoters;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_modification_info_id", unique = true , nullable = false)
	public Long getVoterModificationInfoId() {
		return voterModificationInfoId;
	}
	public void setVoterModificationInfoId(Long voterModificationInfoId) {
		this.voterModificationInfoId = voterModificationInfoId;
	}
	
	@Column(name = "report_level_value", length = 15)
	public Long getReportLevelValue() {
		return reportLevelValue;
	}
	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
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

	@Column(name = "constituency_id", length = 15)
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_status_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VoterStatus getVoterStatus() {
		return voterStatus;
	}

	public void setVoterStatus(VoterStatus voterStatus) {
		this.voterStatus = voterStatus;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voterModificationInfo")
	public Set<VoterModificationAgeInfo> getVoterModificationAgeInfos() {
		return voterModificationAgeInfos;
	}

	public void setVoterModificationAgeInfos(
			Set<VoterModificationAgeInfo> voterModificationAgeInfos) {
		this.voterModificationAgeInfos = voterModificationAgeInfos;
	}
	
	
	
}
