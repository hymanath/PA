package com.itgrids.partyanalyst.model;

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
@Table(name = "updation_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UpdationDetails extends BaseModel implements java.io.Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Long updationDetailsId;
	private Date insertedTime;
	private Date lastUpdatedTime;
	private User createdBy;
	private User updatedBy;
	
	private Set<Survey> survey = new HashSet<Survey>(0);
	private Set<SurveyAccessUsers> surveyAccessUsers = new HashSet<SurveyAccessUsers>(0);
	private Set<SurveyQuestion> surveyQuestion = new HashSet<SurveyQuestion>(0);
	private Set<Option> option = new HashSet<Option>(0);
	private Set<Respondent> respondent = new HashSet<Respondent>(0);
	private Set<Surveyor> surveyor = new HashSet<Surveyor>(0);
	private Set<QuestionOptions> questionOptions = new HashSet<QuestionOptions>(0);

	public UpdationDetails() {
	}
	
	public UpdationDetails(Long updationDetailsId,User createdBy,User updatedBy,Date insertedTime,Date lastUpdatedTime){
		this.updationDetailsId =updationDetailsId;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.insertedTime = insertedTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "updation_details_id", unique = true, nullable = false)
	public Long getUpdationDetailsId() {
		return updationDetailsId;
	}

	public void setUpdationDetailsId(Long updationDetailsId) {
		this.updationDetailsId = updationDetailsId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="created_by")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	@Column(name="last_updated_time")
	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "updationDetails")
	public Set<Survey> getSurvey() {
		return survey;
	}

	public void setSurvey(Set<Survey> survey) {
		this.survey = survey;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "updationDetails")
	public Set<SurveyAccessUsers> getSurveyAccessUsers() {
		return surveyAccessUsers;
	}

	public void setSurveyAccessUsers(Set<SurveyAccessUsers> surveyAccessUsers) {
		this.surveyAccessUsers = surveyAccessUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "updationDetails")
	public Set<SurveyQuestion> getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(Set<SurveyQuestion> surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "updationDetails")
	public Set<Option> getOption() {
		return option;
	}

	public void setOption(Set<Option> option) {
		this.option = option;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "updationDetails")
	public Set<Respondent> getRespondent() {
		return respondent;
	}

	public void setRespondent(Set<Respondent> respondent) {
		this.respondent = respondent;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "updationDetails")
	public Set<Surveyor> getSurveyor() {
		return surveyor;
	}

	public void setSurveyor(Set<Surveyor> surveyor) {
		this.surveyor = surveyor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "updationDetails")
	public Set<QuestionOptions> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(Set<QuestionOptions> questionOptions) {
		this.questionOptions = questionOptions;
	}
	
	
}
