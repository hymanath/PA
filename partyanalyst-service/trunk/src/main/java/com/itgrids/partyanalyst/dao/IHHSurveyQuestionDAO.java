
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HHSurveyQuestion;

public interface IHHSurveyQuestionDAO extends GenericDao<HHSurveyQuestion, Long>{
	
	public List<HHSurveyQuestion> getModelBySurveyId(Long surveyId);
	
}