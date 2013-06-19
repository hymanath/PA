package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Survey;

public interface ISurveyDAO extends GenericDao<Survey, Long> {

	public List<Object[]> getAllSurveys();
	public List<Object[]> getAllSurveysUsingIsDeleted();
	public int updateSurveyDetails(Long surveyId);
	public List<Survey> getSurveyDataBySurveyId(Long surveyId);
}
