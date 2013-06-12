package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyAccessUsersDAO;

public class SurveyAccessUsersDAOHibernateTest extends BaseDaoTestCase  {

	private ISurveyAccessUsersDAO surveyAccessUsersDAO;

	public void setSurveyAccessUsersDAO(ISurveyAccessUsersDAO surveyAccessUsersDAO) {
		this.surveyAccessUsersDAO = surveyAccessUsersDAO;
	}
	public void testGetAll(){
		surveyAccessUsersDAO.getAll();
	}
}
