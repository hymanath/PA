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
@Table(name="respondent")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Respondent extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID=1L;
	
	private Long respondentId;
	private Voter voter;
	private SurveyorProfile surveyorProfile;
	private UpdationDetails updationDetails;
	
	public Respondent() {
	}
	
	public Respondent(Long respondentId,Voter voter,SurveyorProfile surveyorProfile,UpdationDetails updationDetails) {
			this.respondentId = respondentId;
			this.voter = voter;
			this.surveyorProfile = surveyorProfile;
			this.updationDetails = updationDetails;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="respondent_id", unique=true, nullable=false)
	public Long getRespondentId() {
		return respondentId;
	}

	public void setRespondentId(Long respondentId) {
		this.respondentId = respondentId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
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
	
	
}
