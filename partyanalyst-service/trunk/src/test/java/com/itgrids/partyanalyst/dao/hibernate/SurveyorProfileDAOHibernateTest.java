package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyorProfileDAO;

public class SurveyorProfileDAOHibernateTest extends BaseDaoTestCase{
	private ISurveyorProfileDAO surveyorProfileDAO;

	public void setSurveyorProfileDAO(ISurveyorProfileDAO surveyorProfileDAO) {
		this.surveyorProfileDAO = surveyorProfileDAO;
	}
  public void testGetAll(){
	  surveyorProfileDAO.getAll();
  }
}
