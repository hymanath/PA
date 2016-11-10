package com.itgrids.partyanalyst.model;

import javax.persistence.CascadeType;
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
@Table(name = "training_camp_details_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampDetailsInfo {

	private Long trainingCampDetailsInfoId;
	private Long trainingCampProgramId;
	private Long locationScopeId;
	private Long locationValue;
	private Long tdpCommitteeLevelId;
	private Long eligible;
	private Long attended;
	private Long yetToTrain;
	private Long invited;
	private Long absent;

	private TrainingCampProgram trainingCampProgram;
	private RegionScopes locationScope;
	private TdpCommitteeLevel tdpCommitteeLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "training_camp_details_info_id", unique = true, nullable = false)
	public Long getTrainingCampDetailsInfoId() {
		return trainingCampDetailsInfoId;
	}
	public void setTrainingCampDetailsInfoId(Long trainingCampDetailsInfoId) {
		this.trainingCampDetailsInfoId = trainingCampDetailsInfoId;
	}
	@Column(name = "training_camp_program_id")
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	@Column(name = "location_scope_id")
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	@Column(name = "location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	@Column(name = "tdp_committee_level_id")
	public Long getTdpCommitteeLevelId() {
		return tdpCommitteeLevelId;
	}
	public void setTdpCommitteeLevelId(Long tdpCommitteeLevelId) {
		this.tdpCommitteeLevelId = tdpCommitteeLevelId;
	}
	@Column(name = "eligible")
	public Long getEligible() {
		return eligible;
	}
	public void setEligible(Long eligible) {
		this.eligible = eligible;
	}
	@Column(name = "attended")
	public Long getAttended() {
		return attended;
	}
	public void setAttended(Long attended) {
		this.attended = attended;
	}
	@Column(name = "yet_to_train")
	public Long getYetToTrain() {
		return yetToTrain;
	}
	public void setYetToTrain(Long yetToTrain) {
		this.yetToTrain = yetToTrain;
	}
	@Column(name = "invited")
	public Long getInvited() {
		return invited;
	}
	public void setInvited(Long invited) {
		this.invited = invited;
	}
	@Column(name = "absent")
	public Long getAbsent() {
		return absent;
	}
	public void setAbsent(Long absent) {
		this.absent = absent;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="training_camp_program_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TrainingCampProgram getTrainingCampProgram() {
		return trainingCampProgram;
	}
	public void setTrainingCampProgram(TrainingCampProgram trainingCampProgram) {
		this.trainingCampProgram = trainingCampProgram;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_scope_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getLocationScope() {
		return locationScope;
	}
	public void setLocationScope(RegionScopes locationScope) {
		this.locationScope = locationScope;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_committee_level_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommitteeLevel getTdpCommitteeLevel() {
		return tdpCommitteeLevel;
	}
	public void setTdpCommitteeLevel(TdpCommitteeLevel tdpCommitteeLevel) {
		this.tdpCommitteeLevel = tdpCommitteeLevel;
	}
	
	
}
