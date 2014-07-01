package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


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
	
	
	
}
