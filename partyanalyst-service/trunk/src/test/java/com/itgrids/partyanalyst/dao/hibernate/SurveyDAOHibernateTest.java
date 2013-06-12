package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyDAO;

public class SurveyDAOHibernateTest extends BaseDaoTestCase {

	private ISurveyDAO surveyDAO;

	
		public void setSurveyDAO(ISurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}


		public void testGetAll(){
			surveyDAO.getAll();
		}
}
