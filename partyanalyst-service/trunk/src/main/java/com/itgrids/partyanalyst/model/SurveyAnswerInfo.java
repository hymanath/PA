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
	@Table(name="survey_answer_info")
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	public class SurveyAnswerInfo extends BaseModel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6688327239665073287L;
	private Long surveyAnswerInfoId;
	private RegionScopes regionScopes;
	private Long locationValue;
	private Respondent respondent;
	private Surveyor surveyor;
	private Surveyor teamLead;
	private UpdationDetails updationDetails;
	private UserAddress UserAddress;
	
	
	public SurveyAnswerInfo() {
	}

	public SurveyAnswerInfo(Long surveyAnswerInfoId, RegionScopes regionScopes,
			Long locationValue, Respondent respondent, Surveyor surveyor,
			Surveyor teamLead, UpdationDetails updationDetails,
			com.itgrids.partyanalyst.model.UserAddress userAddress) {
		super();
		this.surveyAnswerInfoId = surveyAnswerInfoId;
		this.regionScopes = regionScopes;
		this.locationValue = locationValue;
		this.respondent = respondent;
		this.surveyor = surveyor;
		this.teamLead = teamLead;
		this.updationDetails = updationDetails;
		UserAddress = userAddress;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="survey_answer_info_id", unique=true, nullable=false)
	public Long getSurveyAnswerInfoId() {
		return surveyAnswerInfoId;
	}

	public void setSurveyAnswerInfoId(Long surveyAnswerInfoId) {
		this.surveyAnswerInfoId = surveyAnswerInfoId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}

	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
	
	@Column(name="location_scope_value")
	public Long getLocationValue() {
		return locationValue;
	}

	public void setLocationValue(Long locationValue) {
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
		return UserAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		UserAddress = userAddress;
	}
	
}
