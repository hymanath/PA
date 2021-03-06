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
@Table(name="surveyor")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Surveyor extends BaseModel implements java.io.Serializable{
	private Long surveyorId;
	private String address;
	private Surveyor teamLead;
	private UpdationDetails updationDetails;
	private SurveyorProfile surveyorProfile;
	public Surveyor() {
	}
	
	public Surveyor(Long surveyorId,String address,Surveyor teamLead,UpdationDetails updationDetails,SurveyorProfile surveyorProfile) {
		this.surveyorId       = surveyorId;
		this.address          = address;
		this.teamLead         = teamLead;
		this.updationDetails  = updationDetails;
		this.surveyorProfile  = surveyorProfile;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="surveyor_id", unique=true, nullable=false)
	public Long getSurveyorId() {
		return surveyorId;
	}

	public void setSurveyorId(Long surveyorId) {
		this.surveyorId = surveyorId;
	}

	@Column(name="address", length=60)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="team_lead_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Surveyor getTeamLead() {
		return teamLead;
	}

	public void setTeamLead(Surveyor teamLead) {
		this.teamLead = teamLead;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updation_details_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UpdationDetails getUpdationDetails() {
		return updationDetails;
	}

	public void setUpdationDetails(UpdationDetails updationDetails) {
		this.updationDetails = updationDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="surveyor_profile_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyorProfile getSurveyorProfile() {
		return surveyorProfile;
	}

	public void setSurveyorProfile(SurveyorProfile surveyorProfile) {
		this.surveyorProfile = surveyorProfile;
	}

	
}
