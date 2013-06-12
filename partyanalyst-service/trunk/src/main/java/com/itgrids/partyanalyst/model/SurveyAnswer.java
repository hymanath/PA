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
@Table(name="survey_answer")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SurveyAnswer extends BaseModel implements java.io.Serializable{

	private long surveyAnswerId;
	private RegionScopes regionScopes;
	private long locationValue;
	private Respondent respondent;
	private Surveyor surveyor;
	private Surveyor teamLead;
	private Option option;
	private SurveyQuestion surveyQuestion;
	private String isSubOption;
	private UpdationDetails updationDetails;
	private UserAddress userAddress;
	
	public SurveyAnswer() {
	}
	
	public SurveyAnswer(long surveyAnswerId,RegionScopes regionScopes,long locationValue,Respondent respondent,Surveyor surveyor,Surveyor teamLead,Option option,SurveyQuestion surveyQuestion,String isSubOption,UpdationDetails updationDetails,UserAddress userAddress) {
		this.surveyAnswerId = surveyAnswerId;
		this.regionScopes = regionScopes;
		this.locationValue = locationValue;
		this.respondent = respondent;
		this.surveyor = surveyor;
		this.teamLead = teamLead;
		this.option = option;
		this.surveyQuestion = surveyQuestion;
		this.isSubOption = isSubOption;
		this.updationDetails = updationDetails;
		this.userAddress = userAddress;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="survey_answer_id", unique=true, nullable=false)
	public long getSurveyAnswerId() {
		return surveyAnswerId;
	}

	public void setSurveyAnswerId(long surveyAnswerId) {
		this.surveyAnswerId = surveyAnswerId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="region_scopes_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}

	@Column(name="location_value", length=20)
	public long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(long locationValue) {
		this.locationValue = locationValue;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="respondent_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Respondent getRespondent() {
		return respondent;
	}

	public void setRespondent(Respondent respondent) {
		this.respondent = respondent;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="surveyor_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Surveyor getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(Surveyor surveyor) {
		this.surveyor = surveyor;
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
	@JoinColumn(name="option_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="survey_question_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	@Column(name="is_sub_option", length=8)
	public String getIsSubOption() {
		return isSubOption;
	}

	public void setIsSubOption(String isSubOption) {
		this.isSubOption = isSubOption;
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
	@JoinColumn(name="user_address_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	
}
