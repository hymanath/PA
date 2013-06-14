package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyAccessUsersDAO;

public class SurveyAccessUsersDAOHibernateTest extends BaseDaoTestCase  {

	private ISurveyAccessUsersDAO surveyAccessUsersDAO;

	public void setSurveyAccessUsersDAO(ISurveyAccessUsersDAO surveyAccessUsersDAO) {
		this.surveyAccessUsersDAO = surveyAccessUsersDAO;
	}
	/*public void testGetAll(){
		surveyAccessUsersDAO.getAll();
	}*/
	
	public void testcheckForDuplicateRecords(){
		Long s = surveyAccessUsersDAO.checkForDuplicateRecords(1l,1l);
		System.out.println(s);
	}
}
