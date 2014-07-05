package com.itgrids.partyanalyst.model;

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
 * This Model Describes About Surveyor Type (Data Collector, Verifier , etc....)
 *
 */
@Entity
@Table(name = "survey_surveior_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveySurveyorType 
{

	private Long surveySurveyorTypeId;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_surveior_type_id", unique = true, nullable = false)
	public Long getSurveySurveyorTypeId() {
		return surveySurveyorTypeId;
	}
	public void setSurveySurveyorTypeId(Long surveySurveyorTypeId) {
		this.surveySurveyorTypeId = surveySurveyorTypeId;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
