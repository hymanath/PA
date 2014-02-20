package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="hh_survey_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HHSurveyQuestion extends BaseModel implements Serializable {
	
	  private Long surveyQuestionId;
	  private Long surveyId;
	  private HHSurvey hhSurvey;
	  //private Long optionTypeId;
	  private HHOptionType hhoptionType;
	  private String question;
	  private String isDeleted;
	  private String questionCode;
	  private String isVisible;
	  private Long surveySubTypeId;
	  private HHSurveySubType surveySubType;
	  private Set<HHQuestionOptions> hhQuestionOptions = new HashSet<HHQuestionOptions>(0);
	  private String remarks;
	  
	  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_question_id", unique = true, nullable = false)
	public Long getSurveyQuestionId() {
		return surveyQuestionId;
	}
	public void setSurveyQuestionId(Long surveyQuestionId) {
		this.surveyQuestionId = surveyQuestionId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="survey_id",insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHSurvey getHhSurvey() {
		return hhSurvey;
	}
	public void setHhSurvey(HHSurvey hhSurvey) {
		this.hhSurvey = hhSurvey;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="option_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHOptionType getHhoptionType() {
		return hhoptionType;
	}
	public void setHhoptionType(HHOptionType hhoptionType) {
		this.hhoptionType = hhoptionType;
	}
	
	
	@Column(name = "question", length = 1000)
	public String getQuestion() {
		return question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Column(name = "is_deleted", length = 8)
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="question_code",length=5)
	public String getQuestionCode() {
		return questionCode;
	}
	public void setQuestionCode(String questionCode) {
		this.questionCode = questionCode;
	}
	
	@Column(name="is_visible")
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hhSurveyQuestion")
	public Set<HHQuestionOptions> getHhQuestionOptions() {
		return hhQuestionOptions;
	}
	public void setHhQuestionOptions(Set<HHQuestionOptions> hhQuestionOptions) {
		this.hhQuestionOptions = hhQuestionOptions;
	}
	
	@Column(name="survey_sub_type_id")
	public Long getSurveySubTypeId() {
		return surveySubTypeId;
	}
	public void setSurveySubTypeId(Long surveySubTypeId) {
		this.surveySubTypeId = surveySubTypeId;
	}
	
	  
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_sub_type_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public HHSurveySubType getSurveySubType() {
		return surveySubType;
	}
	public void setSurveySubType(HHSurveySubType surveySubType) {
		this.surveySubType = surveySubType;
	}
	
	@Column(name="remarks", length=500)
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Column(name="survey_id")
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	
	
	  
}
