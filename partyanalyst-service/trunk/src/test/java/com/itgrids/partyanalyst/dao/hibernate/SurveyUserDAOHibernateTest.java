package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyUserDAO;

public class SurveyUserDAOHibernateTest  extends BaseDaoTestCase
{
	private ISurveyUserDAO surveyUserDAO ;

	public ISurveyUserDAO getSurveyUserDAO() {
		return surveyUserDAO;
	}

	public void setSurveyUserDAO(ISurveyUserDAO surveyUserDAO) {
		this.surveyUserDAO = surveyUserDAO;
	}
	
	
	public void testResult()
	{
		Long userId = surveyUserDAO.getUserDetails("userName","password");
		System.out.println(userId);
	}
	
}
