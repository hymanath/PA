package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyorDAO;

public class SurveyorDAOHibernateTest extends BaseDaoTestCase{

	private ISurveyorDAO surveyorDAO;

	public void setSurveyorDAO(ISurveyorDAO surveyorDAO) {
		this.surveyorDAO = surveyorDAO;
	}
	public void testGetAll(){
		surveyorDAO.getAll();
	}
}
