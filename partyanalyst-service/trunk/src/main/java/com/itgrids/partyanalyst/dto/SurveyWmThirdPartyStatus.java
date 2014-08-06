package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class SurveyWmThirdPartyStatus implements Serializable
{
	private Long surveyWmThirdPartyStatusId;
	private String description;
	public Long getSurveyWmThirdPartyStatusId() {
		return surveyWmThirdPartyStatusId;
	}
	public void setSurveyWmThirdPartyStatusId(Long surveyWmThirdPartyStatusId) {
		this.surveyWmThirdPartyStatusId = surveyWmThirdPartyStatusId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
