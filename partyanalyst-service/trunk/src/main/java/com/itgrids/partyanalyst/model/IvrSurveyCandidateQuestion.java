package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "ivr_survey_candidate_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IvrSurveyCandidateQuestion extends BaseModel implements Serializable {
    
	private Long ivrCandidateSurveyQuestionId;
	private Long candidateId;
	private Long ivrSurveyQuestionId;
	//private Long ivrOptionsId;
	private Long ivrSurveyQuestionOptionId;
	private String isLocationScope;
	private Long locationScopeId;
	private Long locationValue;
	private Long tdpCadreId;
	private Long addressId;
	
	private Candidate  candidate;
	//private IvrOption  ivrOption;
	private IvrSurveyQuestionOption ivrSurveyQuestionOption;
	private IvrSurveyQuestion ivrSurveyQuestion;
	private TdpCadre tdpCadre;
	private UserAddress userAddress;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ivr_candidate_survey_question_id", unique = true, nullable = false)
	public Long getIvrCandidateSurveyQuestionId() {
		return ivrCandidateSurveyQuestionId;
	}
	public void setIvrCandidateSurveyQuestionId(Long ivrCandidateSurveyQuestionId) {
		this.ivrCandidateSurveyQuestionId = ivrCandidateSurveyQuestionId;
	}
	@Column(name = "candidate_id")
	public Long getCandidateId() {
		return candidateId;
	}
	
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	@Column(name = "ivr_survey_question_id")
	public Long getIvrSurveyQuestionId() {
		return ivrSurveyQuestionId;
	}
	public void setIvrSurveyQuestionId(Long ivrSurveyQuestionId) {
		this.ivrSurveyQuestionId = ivrSurveyQuestionId;
	}
	/*@Column(name = "ivr_option_id")
	public Long getIvrOptionsId() {
		return ivrOptionsId;
	}
	public void setIvrOptionsId(Long ivrOptionsId) {
		this.ivrOptionsId = ivrOptionsId;
	}*/
	@Column(name = "is_location_scope")
	public String getIsLocationScope() {
		return isLocationScope;
	}
	public void setIsLocationScope(String isLocationScope) {
		this.isLocationScope = isLocationScope;
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
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
    public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	/*@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_option_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
     public IvrOption getIvrOption() {
		return ivrOption;
	}
	public void setIvrOption(IvrOption ivrOption) {
		this.ivrOption = ivrOption;
	}*/
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_question_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)

	public IvrSurveyQuestion getIvrSurveyQuestion() {
		return ivrSurveyQuestion;
	}
	public void setIvrSurveyQuestion(IvrSurveyQuestion ivrSurveyQuestion) {
		this.ivrSurveyQuestion = ivrSurveyQuestion;
	}
	
	@Column(name = "tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_cadre_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}
	
	@Column(name = "address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	@Column(name = "ivr_survey_question_option_id")
	public Long getIvrSurveyQuestionOptionId() {
		return ivrSurveyQuestionOptionId;
	}
	public void setIvrSurveyQuestionOptionId(Long ivrSurveyQuestionOptionId) {
		this.ivrSurveyQuestionOptionId = ivrSurveyQuestionOptionId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ivr_survey_question_option_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public IvrSurveyQuestionOption getIvrSurveyQuestionOption() {
		return ivrSurveyQuestionOption;
	}
	public void setIvrSurveyQuestionOption(
			IvrSurveyQuestionOption ivrSurveyQuestionOption) {
		this.ivrSurveyQuestionOption = ivrSurveyQuestionOption;
	}
	
	
}
