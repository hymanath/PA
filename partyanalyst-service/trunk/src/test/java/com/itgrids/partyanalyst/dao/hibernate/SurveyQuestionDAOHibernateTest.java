package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;

public class SurveyQuestionDAOHibernateTest extends BaseDaoTestCase  {

	private ISurveyQuestionDAO surveyQuestionDAO;

	public void setSurveyQuestionDAO(ISurveyQuestionDAO surveyQuestionDAO) {
		this.surveyQuestionDAO = surveyQuestionDAO;
	}
	public void testGetAll(){
		surveyQuestionDAO.getAll();
	}
}
