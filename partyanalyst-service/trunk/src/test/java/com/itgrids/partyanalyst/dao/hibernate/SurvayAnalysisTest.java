package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IOptionDAO;
import com.itgrids.partyanalyst.dao.IQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.service.impl.SurveyAnalysisService;

public class SurvayAnalysisTest  extends BaseDaoTestCase{
	ISurveyQuestionDAO surveyQuestionDAO;
	IQuestionOptionsDAO questionOptionsDAO;
	IOptionDAO optionDAO;
	SurveyAnalysisService surveyAnalysisService;
	public ISurveyQuestionDAO getSurveyQuestionDAO() {
		return surveyQuestionDAO;
	}
	public void setSurveyQuestionDAO(ISurveyQuestionDAO surveyQuestionDAO) {
		this.surveyQuestionDAO = surveyQuestionDAO;
	}
	public IQuestionOptionsDAO getQuestionOptionsDAO() {
		return questionOptionsDAO;
	}
	public void setQuestionOptionsDAO(IQuestionOptionsDAO questionOptionsDAO) {
		this.questionOptionsDAO = questionOptionsDAO;
	}
	public IOptionDAO getOptionDAO() {
		return optionDAO;
	}
	public void setOptionDAO(IOptionDAO optionDAO) {
		this.optionDAO = optionDAO;
	}
	
	public void testAll(){
		surveyAnalysisService = new SurveyAnalysisService();
		surveyAnalysisService.setSurveyQuestionDAO(surveyQuestionDAO);
		surveyAnalysisService.setQuestionOptionsDAO(questionOptionsDAO);
		surveyAnalysisService.setOptionDAO(optionDAO);
		String result = surveyAnalysisService.getSurveyForm(3l);
		System.out.println(result);
	}
}
