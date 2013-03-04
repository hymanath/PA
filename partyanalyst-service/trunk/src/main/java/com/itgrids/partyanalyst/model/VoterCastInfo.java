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
@Table(name = "voter_cast_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterCastInfo extends BaseModel implements Serializable {
	private static final long serialVersionUID = 4117259734954203835L;

	private Long voterCastInfoId;
	private VoterReportLevel voterReportLevel;
	private Long reportLevelValue;
	private Long userId;
	private Long casteStateId;
	private Long casteVoters;
	private Long casteMaleVoters; 
	private Long casteFemaleVoters;
	private Double castePercentage;
	private Long publicationDateId;
	private Constituency constituency;
	
	/** Default Constructor */
	
	public VoterCastInfo()
	{
		
	}
	
	/** default constructor */
	public VoterCastInfo(Long voterCastInfoId,VoterReportLevel voterReportLevel,Long reportLevelValue, Long userId,
	   Long casteStateId,Long casteVoters,Long casteMaleVoters, 
	   Long casteFemaleVoters,Double castePercentage, Long publicationDateId, Constituency constituency) {
		
		this.voterCastInfoId = voterCastInfoId;
		this.voterReportLevel = voterReportLevel;
		this.reportLevelValue = reportLevelValue;
		this.userId = userId;
		this.casteStateId =casteStateId;
		this.casteVoters = casteVoters;
		this.casteMaleVoters = casteMaleVoters;
		this.casteFemaleVoters = casteFemaleVoters;
		this.castePercentage = castePercentage;
		this.publicationDateId = publicationDateId;
		this.constituency = constituency;
		}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_cast_info_id", unique = true, nullable = false)
	public Long getVoterCastInfoId() {
		return voterCastInfoId;
	}
	
	public void setVoterCastInfoId(Long voterCastInfoId) {
		this.voterCastInfoId = voterCastInfoId;
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
	
	@Column(name = "user_id", length = 15)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "caste_state_id", length = 15)
	public Long getCasteStateId() {
		return casteStateId;
	}

	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}

	@Column(name = "caste_voters", length = 15)
	public Long getCasteVoters() {
		return casteVoters;
	}

	public void setCasteVoters(Long casteVoters) {
		this.casteVoters = casteVoters;
	}

	@Column(name = "caste_male_voters", length = 15)
	public Long getCasteMaleVoters() {
		return casteMaleVoters;
	}

	public void setCasteMaleVoters(Long casteMaleVoters) {
		this.casteMaleVoters = casteMaleVoters;
	}

	@Column(name = "caste_female_voters", length = 15)
	public Long getCasteFemaleVoters() {
		return casteFemaleVoters;
	}

	public void setCasteFemaleVoters(Long casteFemaleVoters) {
		this.casteFemaleVoters = casteFemaleVoters;
	}

	@Column(name = "caste_percentage")
	public Double getCastePercentage() {
		return castePercentage;
	}

	public void setCastePercentage(Double castePercentage) {
		this.castePercentage = castePercentage;
	}

	@Column(name = "publication_date_id", length = 15)
	public Long getPublicationDateId() {
		return publicationDateId;
	}

	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
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

	
	
}
