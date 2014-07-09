package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;

public class SurveyUserRelationDAOHibernateTest extends BaseDaoTestCase{
	private ISurveyUserRelationDAO surveyUserRelationDAO;

	
	
	public void setSurveyUserRelationDAO(
			ISurveyUserRelationDAO surveyUserRelationDAO) {
		this.surveyUserRelationDAO = surveyUserRelationDAO;
	}



	public void test()
	{
		List<Object[]> list = surveyUserRelationDAO.getUsersForAssignedUser(16l,3l);
		System.out.println(list.size());
		
	}

}
