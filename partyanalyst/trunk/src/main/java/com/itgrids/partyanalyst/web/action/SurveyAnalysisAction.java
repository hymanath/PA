package com.itgrids.partyanalyst.web.action;

import com.itgrids.partyanalyst.service.ISurveyAnalysisService;
import com.opensymphony.xwork2.Action;

public class SurveyAnalysisAction {
	
     private ISurveyAnalysisService surveyAnalysisService;
  
	 public ISurveyAnalysisService getSurveyAnalysisService() {
		return surveyAnalysisService;
	}
		
	public void setSurveyAnalysisService(
			ISurveyAnalysisService surveyAnalysisService) {
		this.surveyAnalysisService = surveyAnalysisService;
	}
		
	public String execute(){
		 System.out.println("In");
		 return Action.SUCCESS;
	 }
	 
}
