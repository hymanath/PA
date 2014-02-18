package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;

public class HHOptionsDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHOptionsDAO ihhOptionsDAO;

	public IHHOptionsDAO getIhhOptionsDAO() {
		return ihhOptionsDAO;
	}
	
	public void test(){
		System.out.println("hhOptionsDAO");
	}

}
