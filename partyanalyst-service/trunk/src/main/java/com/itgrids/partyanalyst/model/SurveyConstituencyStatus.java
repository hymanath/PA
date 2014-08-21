package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "survey_constituency_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyConstituencyStatus extends BaseModel implements Serializable {
	
	private Long surveyConstituencyStatusId;
	private String description;
	
	public SurveyConstituencyStatus()
	{
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_constituency_status_id", unique = true, nullable = false)
	public Long getSurveyConstituencyStatusId() {
		return surveyConstituencyStatusId;
	}
	public void setSurveyConstituencyStatusId(Long surveyConstituencyStatusId) {
		this.surveyConstituencyStatusId = surveyConstituencyStatusId;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
