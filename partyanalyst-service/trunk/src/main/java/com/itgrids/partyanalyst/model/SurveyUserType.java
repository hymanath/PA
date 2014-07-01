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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;


/**
 * 
 * @author Prasad Thiragabathina
 * 
 * This Model Describes About Survey User Comes Under Which Categoery(Data Collector,Data Verifier etc.....)
 *
 */
@Entity
@Table(name = "survey_user_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyUserType
{

	private Long surveyUsertypeId;
	private String description;
	private Date insertedTime;
	private Date updatedTime;
	private Set<SurveyUser> surveyUser = new HashSet<SurveyUser>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_user_type_id", unique = true, nullable = false)
	public Long getSurveyUsertypeId() {
		return surveyUsertypeId;
	}
	public void setSurveyUsertypeId(Long surveyUsertypeId) {
		this.surveyUsertypeId = surveyUsertypeId;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "surveyUserType")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<SurveyUser> getSurveyUser() {
		return surveyUser;
	}
	public void setSurveyUser(Set<SurveyUser> surveyUser) {
		this.surveyUser = surveyUser;
	}
	
	
	
}
