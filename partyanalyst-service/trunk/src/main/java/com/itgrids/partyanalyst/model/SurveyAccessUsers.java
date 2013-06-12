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
@Table(name = "survey_access_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyAccessUsers extends BaseModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long surveyAccessUsersId;
	private Survey survey;
	private User user;
	private UpdationDetails updationDetails;
	
	
	public SurveyAccessUsers() {
	}
	
	public SurveyAccessUsers(Long surveyAccessUsersId,Survey survey,User user,UpdationDetails updationDetails) {
		this.surveyAccessUsersId = surveyAccessUsersId;
		this.survey = survey;
		this.user = user;
		this.updationDetails = updationDetails;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_access_users_id", unique = true, nullable = false)
	public Long getSurveyAccessUsersId() {
		return surveyAccessUsersId;
	}

	public void setSurveyAccessUsersId(Long surveyAccessUsersId) {
		this.surveyAccessUsersId = surveyAccessUsersId;
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
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
