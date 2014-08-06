package com.itgrids.partyanalyst.dto;

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
@Table(name = "survey_wm_third_party_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SurveyWmThirdPartyStatus implements Serializable
{
	
	private static final long serialVersionUID = -8653630605725270944L;
	private Long surveyWmThirdPartyStatusId;
	private String description;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "survey_wm_third_party_status_id", unique = true, nullable = false)
	public Long getSurveyWmThirdPartyStatusId() {
		return surveyWmThirdPartyStatusId;
	}
	public void setSurveyWmThirdPartyStatusId(Long surveyWmThirdPartyStatusId) {
		this.surveyWmThirdPartyStatusId = surveyWmThirdPartyStatusId;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
