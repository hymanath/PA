package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyUserConstituencyDAO;

public class SurveyUserConstituencyDAOHibernateTest  extends BaseDaoTestCase{

	private ISurveyUserConstituencyDAO surveyUserConstituencyDAO;


	public void setSurveyUserConstituencyDAO(
			ISurveyUserConstituencyDAO surveyUserConstituencyDAO) {
		this.surveyUserConstituencyDAO = surveyUserConstituencyDAO;
	}
	
	public void testgetSurveyConstituencyList(){
		
		/*List<Object[]> result = surveyUserConstituencyDAO.getSurveyConstituencyList();
		
		System.out.println(result);*/
		List<Long> TSDistrictList = new ArrayList<Long>();
		for(int i=1;i<11;i++)
			TSDistrictList.add(new Long(i));
		System.out.println(TSDistrictList);
	}
	
}
