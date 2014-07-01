package com.itgrids.partyanalyst.model;

import java.util.Date;

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

/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Survey User Relation , means whis user comes under which leader.
 *
 */

@Entity
@Table(name = "survey_user_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyUserRelation 
{

	private Long surveyUserRelationId;
	private SurveyUserType surveyUserType;
	private SurveyUser surveyUser;
	private SurveyUser surveyLeader;
	private Constituency constituency;
	private String activeStatus;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_user_relation_id", unique = true, nullable = false)
	public Long getSurveyUserRelationId() {
		return surveyUserRelationId;
	}
	public void setSurveyUserRelationId(Long surveyUserRelationId) {
		this.surveyUserRelationId = surveyUserRelationId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUserType getSurveyUserType() {
		return surveyUserType;
	}
	public void setSurveyUserType(SurveyUserType surveyUserType) {
		this.surveyUserType = surveyUserType;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "survey_leader_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SurveyUser getSurveyLeader() {
		return surveyLeader;
	}
	public void setSurveyLeader(SurveyUser surveyLeader) {
		this.surveyLeader = surveyLeader;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@Column(name="active_status")
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
}
