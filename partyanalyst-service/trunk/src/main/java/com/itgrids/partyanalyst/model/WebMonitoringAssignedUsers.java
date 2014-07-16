package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "web_monitoring_assigned_users")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WebMonitoringAssignedUsers implements Serializable{
	
	private Long webMonitoringAssignedUsersId;
	private Long surveyUserId;
	private Long webMoniterUserId;
	private String isDelete;
	
	private SurveyUser surveyUser;
	private User webMonitorSurveyUser;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "web_monitoring_assigned_users_id", unique = true, nullable = false)
	public Long getWebMonitoringAssignedUsersId() {
		return webMonitoringAssignedUsersId;
	}
	public void setWebMonitoringAssignedUsersId(Long webMonitoringAssignedUsersId) {
		this.webMonitoringAssignedUsersId = webMonitoringAssignedUsersId;
	}
	
	@Column(name="survey_user_id")
	public Long getSurveyUserId() {
		return surveyUserId;
	}
	public void setSurveyUserId(Long surveyUserId) {
		this.surveyUserId = surveyUserId;
	}
	
	@Column(name="web_moniter_user_id")
	public Long getWebMoniterUserId() {
		return webMoniterUserId;
	}
	public void setWebMoniterUserId(Long webMoniterUserId) {
		this.webMoniterUserId = webMoniterUserId;
	}
	
	@Column(name="is_delete")
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "web_moniter_user_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getWebMonitorSurveyUser() {
		return webMonitorSurveyUser;
	}
	public void setWebMonitorSurveyUser(User webMonitorSurveyUser) {
		this.webMonitorSurveyUser = webMonitorSurveyUser;
	}
}
