package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.IHHSurveyDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;

public class HHSurveyDAOHibernateTest extends BaseDaoTestCase{

	private IHHSurveyDAO ihhSurveyDAO;
	
	public IHHSurveyDAO getIhhSurveyDAO() {
		return ihhSurveyDAO;
	}


	public void test(){
		System.out.println("ihhSurveyDAO");
	}

}
