package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IHHOptionTypeDAO;
import com.itgrids.partyanalyst.dao.IHHOptionsDAO;
import com.itgrids.partyanalyst.dao.IHHQuestionOptionsDAO;
import com.itgrids.partyanalyst.dao.ISurveyQuestionDAO;

public class HHQuestionOptionsDAOHibernateTest extends BaseDaoTestCase{
	
	private IHHQuestionOptionsDAO ihhQuestionOptionsDAO;
	
	public IHHQuestionOptionsDAO getIhhQuestionOptionsDAO() {
		return ihhQuestionOptionsDAO;
	}

	public void test(){
		System.out.println("ihhQuestionOptionsDAO");
	}

}
