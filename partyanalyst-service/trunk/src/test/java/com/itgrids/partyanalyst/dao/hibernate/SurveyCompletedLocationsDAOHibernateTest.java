package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.IHouseHoldsDAO;
import com.itgrids.partyanalyst.dao.ISurveyCompletedLocationsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;
import com.itgrids.partyanalyst.model.HouseHolds;

public class SurveyCompletedLocationsDAOHibernateTest extends BaseDaoTestCase{
	
	private ISurveyCompletedLocationsDAO surveyCompletedLocationsDAO;
	
	

	public ISurveyCompletedLocationsDAO getSurveyCompletedLocationsDAO() {
		return surveyCompletedLocationsDAO;
	}



	public void setSurveyCompletedLocationsDAO(
			ISurveyCompletedLocationsDAO surveyCompletedLocationsDAO) {
		this.surveyCompletedLocationsDAO = surveyCompletedLocationsDAO;
	}



	/*public void test(){
		List<Object[]> list=surveyCompletedLocationsDAO.getBoothsOfConstituecyByStatus(217l, 1l, 9l);
		System.out.println(list.size());
	}*/
	
	public void test(){
			List<Long> list = surveyCompletedLocationsDAO.getBoothsOfTPWithStatus(217l,8l);
			System.out.println(list.size());
		
	}

}
