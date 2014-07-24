package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISurveyUserRelationDAO;

public class SurveyUserRelationDAOHibernateTest extends BaseDaoTestCase{
	private ISurveyUserRelationDAO surveyUserRelationDAO;

	
	
	public void setSurveyUserRelationDAO(
			ISurveyUserRelationDAO surveyUserRelationDAO) {
		this.surveyUserRelationDAO = surveyUserRelationDAO;
	}



	/*public void test()
	{
		List<Object[]> list = surveyUserRelationDAO.getUsersForAssignedUser(16l,3l);
		System.out.println(list.size());
		
	}*/
	
	public void testgetusersBysurveyUserIds()
	{
		List<Long> ids = new ArrayList<Long>();
		ids.add(148L);
		ids.add(3L);
		ids.add(44L);
		List<Object[]> list = surveyUserRelationDAO.getLeadersBysurveyUserIds(ids,3l);
		System.out.println(list.size());
		
	}
	
	
}
