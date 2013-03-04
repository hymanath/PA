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
@Table(name = "voter_cast_basic_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterCastBasicInfo extends BaseModel implements Serializable{

	private static final long serialVersionUID = 7326667312473579978L;
	private Long voterCastBasicInfoId;
	private VoterReportLevel voterReportLevel;
	private Long reportLevelValue;
	private Long userId;
	private Long totalCastes;
	private Long casteAssignedVoters;
	private Long casteNotAssignedVoters;
	private Long ocVoters;
	private Long bcVoters;
	private Long scVoters;
	private Long stVoters;
	private Long publicationDateId;
	private Constituency constituency;
	
	public VoterCastBasicInfo(){}
	
	public VoterCastBasicInfo(VoterReportLevel voterReportLevel,Long reportLevelValue,Long userId,
			Long totalCastes,Long casteAssignedVoters,Long casteNotAssignedVoters, Long ocVoters,
			Long bcVoters, Long scVoters, Long stVoters, Long publicationDateId, Constituency constituency){
		
		this.voterReportLevel = voterReportLevel;
		this.reportLevelValue = reportLevelValue;
		this.userId = userId;
		this.totalCastes = totalCastes;
		this.casteAssignedVoters = casteAssignedVoters;
		this.casteNotAssignedVoters = casteNotAssignedVoters;
		this.ocVoters = ocVoters;
		this.bcVoters = bcVoters;
		this.scVoters = scVoters;
		this.stVoters = stVoters;
		this.publicationDateId = publicationDateId;
		this.constituency = constituency;
		
		}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "voter_cast_basic_info_id", unique = true , nullable = false)
	public Long getVoterCastBasicInfoId() {
		return voterCastBasicInfoId;
	}

	public void setVoterCastBasicInfoId(Long voterCastBasicInfoId) {
		this.voterCastBasicInfoId = voterCastBasicInfoId;
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

	@Column(name ="total_castes", length = 15)
	public Long getTotalCastes() {
		return totalCastes;
	}

	public void setTotalCastes(Long totalCastes) {
		this.totalCastes = totalCastes;
	}

	@Column(name = "caste_assigned_voters", length = 15)
	public Long getCasteAssignedVoters() {
		return casteAssignedVoters;
	}

	public void setCasteAssignedVoters(Long casteAssignedVoters) {
		this.casteAssignedVoters = casteAssignedVoters;
	}
	
	@Column(name = "caste_not_assigned_voters", length = 15)
	public Long getCasteNotAssignedVoters() {
		return casteNotAssignedVoters;
	}

	public void setCasteNotAssignedVoters(Long casteNotAssignedVoters) {
		this.casteNotAssignedVoters = casteNotAssignedVoters;
	}

	@Column(name = "oc_voters", length = 15)
	public Long getOcVoters() {
		return ocVoters;
	}

	public void setOcVoters(Long ocVoters) {
		this.ocVoters = ocVoters;
	}

	@Column(name = "bc_voters", length = 15)
	public Long getBcVoters() {
		return bcVoters;
	}

	public void setBcVoters(Long bcVoters) {
		this.bcVoters = bcVoters;
	}

	@Column(name = "sc_voters", length = 15)
	public Long getScVoters() {
		return scVoters;
	}

	public void setScVoters(Long scVoters) {
		this.scVoters = scVoters;
	}

	@Column(name = "st_voters", length = 15)
	public Long getStVoters() {
		return stVoters;
	}

	public void setStVoters(Long stVoters) {
		this.stVoters = stVoters;
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
