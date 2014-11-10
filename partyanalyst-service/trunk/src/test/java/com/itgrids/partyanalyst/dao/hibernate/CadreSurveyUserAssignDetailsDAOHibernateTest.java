package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;

public class CadreSurveyUserAssignDetailsDAOHibernateTest extends BaseDaoTestCase{

	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;

	public void setCadreSurveyUserAssignDetailsDAO(
			ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO) {
		this.cadreSurveyUserAssignDetailsDAO = cadreSurveyUserAssignDetailsDAO;
	}
	
	public void testDetails()
	{
		/*List<Long> list = cadreSurveyUserAssignDetailsDAO.checkIsAlreadyAssigned(2L,1L,232L,232L);
		System.out.println(list.size());*/
	//	List<Object[]>  list = cadreSurveyUserAssignDetailsDAO.getUsersByConstituencyAndUserId(282l,1l);
		List<Object[]> list = cadreSurveyUserAssignDetailsDAO.getUsersByConstituencyAndUserId(282l,1l);
		System.out.println(list.size());
		
	}
}
