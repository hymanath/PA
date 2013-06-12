package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyAnswerDAO;

public class SurveyAnswerDAOHibernateTest extends BaseDaoTestCase {

	private ISurveyAnswerDAO surveyAnswerDAO;

	public void setSurveyAnswerDAO(ISurveyAnswerDAO surveyAnswerDAO) {
		this.surveyAnswerDAO = surveyAnswerDAO;
	}
	public void testGetAll(){
		surveyAnswerDAO.getAll();
	}
}
