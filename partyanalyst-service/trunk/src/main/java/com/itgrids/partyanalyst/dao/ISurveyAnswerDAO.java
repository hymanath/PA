package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyAnswer;

public interface ISurveyAnswerDAO extends GenericDao<SurveyAnswer, Long>{

public List<Object[]> getSurveyAnalyseData(List<Long> surveyId);
	
	public List<Object[]> getCountForSurveyQuestion(List<Long> surveyQuestionIds);
	
	public List<Object[]> getCasteWiseSurveyInfo(List<Long> surveyQuestionIds);	
	
	public List<Object[]> getsurveyDetailsBasedOnGivenAgeRange(List<Long> questionIds,String minAge,String maxAge);
	
	public List<Object[]> getsurveyDetailsForAbove60Years(List<Long> questionIds,String age);
}
