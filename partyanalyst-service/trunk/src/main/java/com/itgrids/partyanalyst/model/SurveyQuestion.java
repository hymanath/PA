package com.itgrids.partyanalyst.model;

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
@Table(name = "survey_question")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyQuestion extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long surveyQuestionId;
	private Survey survey;
	private OptionType optionType;
	private String question;
	private String description;
	private Long orderId;
	private UpdationDetails updationDetails;
	private String isDeleted;
	private String hasRemarks;
	private String isAnalyse;
	private Set<QuestionOptions> questionOptions = new HashSet<QuestionOptions>(0);
	
	public SurveyQuestion() {
	}
	
	public SurveyQuestion(Long surveyQuestionId,Survey survey,OptionType optionType,String question,String description,Long orderId,UpdationDetails updationDetails,String isDeleted,String hasRemarks) {
		this.surveyQuestionId = surveyQuestionId;
		this.survey = survey;
		this.optionType = optionType;
		this.question = question;
		this.description = description;
		this.orderId = orderId;
		this.updationDetails = updationDetails;
		this.isDeleted = isDeleted;
		this.hasRemarks = hasRemarks;
	}

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
	@JoinColumn(name="survey_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="option_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public OptionType getOptionType() {
		return optionType;
	}

	public void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}

	@Column(name = "question", length = 200)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "order_id", length = 15)
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	@Column(name = "is_deleted", length = 8)
	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyQuestion")
	public Set<QuestionOptions> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(Set<QuestionOptions> questionOptions) {
		this.questionOptions = questionOptions;
	}
	
	@Column(name = "has_remarks", length = 100)
	public String getHasRemarks() {
		return hasRemarks;
	}

	public void setHasRemarks(String hasRemarks) {
		this.hasRemarks = hasRemarks;
	}
	@Column(name="is_analyse",length = 45)
	public String getIsAnalyse() {
		return isAnalyse;
	}

	public void setIsAnalyse(String isAnalyse) {
		this.isAnalyse = isAnalyse;
	}

}
