package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyDAO;

public class SurveyDAOHibernateTest extends BaseDaoTestCase {

	private ISurveyDAO surveyDAO;

	
		public void setSurveyDAO(ISurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}


	/*	public void testGetAll(){
			surveyDAO.getAll();
		}*/
		
		public void testupdateSurveyDetails(){
			int i = surveyDAO.updateSurveyDetails(1l);
			System.out.println(i);
		}
		
		/*public void testgetAllSurveysUsingIsDeleted(){
			List<Object[]> c = surveyDAO.getAllSurveysUsingIsDeleted();
			System.out.println(c);
		}*/
}
