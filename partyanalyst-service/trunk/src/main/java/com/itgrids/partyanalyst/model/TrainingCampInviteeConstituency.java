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

@Entity
@Table(name = "training_camp_invitee_constituency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TrainingCampInviteeConstituency {
	private Long trainingCampInviteeConstituencyId;
	private Long trainingCampProgramId;
	private Long constituencyId;
	private String isInviteeConstituency;
	
	private Constituency constituency;
	private TrainingCampProgram trainingCampProgram;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="training_camp_invitee_constituency_id", unique=true, nullable=false)
	public Long getTrainingCampInviteeConstituencyId() {
		return trainingCampInviteeConstituencyId;
	}
	public void setTrainingCampInviteeConstituencyId(
			Long trainingCampInviteeConstituencyId) {
		this.trainingCampInviteeConstituencyId = trainingCampInviteeConstituencyId;
	}
	@Column(name="training_camp_program_id")
	public Long getTrainingCampProgramId() {
		return trainingCampProgramId;
	}
	public void setTrainingCampProgramId(Long trainingCampProgramId) {
		this.trainingCampProgramId = trainingCampProgramId;
	}
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name="is_invitee_constituency")
	public String getIsInviteeConstituency() {
		return isInviteeConstituency;
	}
	public void setIsInviteeConstituency(String isInviteeConstituency) {
		this.isInviteeConstituency = isInviteeConstituency;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id",insertable =false,updatable = false)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "training_camp_program_id",insertable =false,updatable = false)
	public TrainingCampProgram getTrainingCampProgram() {
		return trainingCampProgram;
	}
	public void setTrainingCampProgram(TrainingCampProgram trainingCampProgram) {
		this.trainingCampProgram = trainingCampProgram;
	}
	
	
	
}
