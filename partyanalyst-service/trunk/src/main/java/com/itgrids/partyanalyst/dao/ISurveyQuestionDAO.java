package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.SurveyQuestion;

public interface ISurveyQuestionDAO extends GenericDao<SurveyQuestion, Long> {
 
	public List<SurveyQuestion> getAllQuestionsForSurvey(Long surveyId);
	
	public List<Long> getSurveyQuestionIdsById(Long surveyId);
}
