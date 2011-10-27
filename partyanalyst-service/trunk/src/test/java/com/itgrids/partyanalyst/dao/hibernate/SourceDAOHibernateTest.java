package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISourceDAO;

public class SourceDAOHibernateTest extends BaseDaoTestCase {

	private ISourceDAO sourceDAO;

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}
	
	public void test()
	{
		sourceDAO.getAll();
		
	}
}
