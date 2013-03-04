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
@Table(name = "voter_cast_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterCastInfo implements Serializable {
	private static final long serialVersionUID = 4117259734954203835L;

	private Long voterCastInfoId;
	private Long reportLevelId;
	private Long reportLevelValue;
	private Long userId;
	private Long totalCastes;
	private Long casteAssignedVoters;
	private Long casteNotAssignedVoters;
	private Long ocVoters;
	private Long bcVoters;
	private Long scVoters;
	private Long stVoters;
	private Long casteStateId;
	private Long casteVoters;
	private Long casteMaleVoters; 
	private Long casteFemaleVoters;
	private Double castePercentage;
	
	/** Default Constructor */
	
	public VoterCastInfo()
	{
		
	}
	
	/** default constructor */
	public VoterCastInfo(Long voterCastInfoId,Long reportLevelId,Long reportLevelValue, Long userId,Long totalCastes,
	
							Long casteAssignedVoters,Long casteNotAssignedVoters,Long ocVoters, Long bcVoters,
							Long scVoters,Long stVoters,Long casteStateId,Long casteVoters,Long casteMaleVoters, Long casteFemaleVoters,
							double castePercentage) {
		
		this.voterCastInfoId = voterCastInfoId;
		this.reportLevelId = reportLevelId;
		this.reportLevelValue = reportLevelValue;
		this.userId = userId;
		this.totalCastes = totalCastes;
		this.casteAssignedVoters = casteAssignedVoters;
		this.casteNotAssignedVoters = casteNotAssignedVoters;
		this.ocVoters = ocVoters;
		this.bcVoters =bcVoters;
		this.scVoters = scVoters;
		this.stVoters = stVoters;
		this.casteStateId =casteStateId;
		this.casteVoters = casteVoters;
		this.casteMaleVoters = casteMaleVoters;
		this.casteFemaleVoters = casteFemaleVoters;
		this.castePercentage = castePercentage;
		
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
	@Column(name = "total_castes ", length = 15)
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
	public double getCastePercentage() {
		return castePercentage;
	}

	public void setCastePercentage(double castePercentage) {
		this.castePercentage = castePercentage;
	}

	
	
	
	

}
