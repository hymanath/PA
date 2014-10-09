package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;

public class CadreSurveyUserDAOHibernateTest extends BaseDaoTestCase{

	private ICadreSurveyUserDAO cadreSurveyUserDAO;

	public void setCadreSurveyUserDAO(ICadreSurveyUserDAO cadreSurveyUserDAO) {
		this.cadreSurveyUserDAO = cadreSurveyUserDAO;
	}

	public void testDetails(){
		List<Long> ids = new ArrayList<Long>();	
		ids.add(2L);
	
		List<Object[]> list = cadreSurveyUserDAO.getCadreSurveyUsersList(ids);
		System.out.println(list.size());
	}
}
