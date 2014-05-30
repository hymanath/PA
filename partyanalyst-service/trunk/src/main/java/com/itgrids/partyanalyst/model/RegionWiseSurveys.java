package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "region_wise_surveys")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RegionWiseSurveys {
	
	private Long regionWiseSurveysId;
	private Long regionId;
	private Long surveyId;
	private String surveyName;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "region_wise_surveys_id", unique = true, nullable = false)
	public Long getRegionWiseSurveysId() {
		return regionWiseSurveysId;
	}
	public void setRegionWiseSurveysId(Long regionWiseSurveysId) {
		this.regionWiseSurveysId = regionWiseSurveysId;
	}
	
	@Column(name="region_id")
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	
	@Column(name="survey_id")
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	
	@Column(name="survey_name")
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

}
