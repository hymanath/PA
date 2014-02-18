package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;

public class HHOptionTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHOptionTypeDAO ihhOptionTypeDAO;
	
	
	public IHHOptionTypeDAO getIhhOptionTypeDAO() {
		return ihhOptionTypeDAO;
	}

	public void test(){
		System.out.println("ihhOptionTypeDAO");
	}

}
