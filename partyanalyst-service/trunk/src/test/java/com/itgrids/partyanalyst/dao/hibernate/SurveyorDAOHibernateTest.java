package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyorDAO;
import com.itgrids.partyanalyst.model.Surveyor;

public class SurveyorDAOHibernateTest extends BaseDaoTestCase{

	private ISurveyorDAO surveyorDAO;

	public void setSurveyorDAO(ISurveyorDAO surveyorDAO) {
		this.surveyorDAO = surveyorDAO;
	}
	/*public void testGetAll(){
		surveyorDAO.getAll();
	}*/
	public void testgetSurveyorDetails()
	{
		List<Surveyor> values = surveyorDAO.getSurveyorDetails();
		for (Surveyor surveyor : values) {
			System.out.println(surveyor.getSurveyorProfile().getName());
		}
	}
}
