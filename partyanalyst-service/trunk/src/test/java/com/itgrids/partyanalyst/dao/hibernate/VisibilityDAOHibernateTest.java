package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVisibilityDAO;

public class VisibilityDAOHibernateTest extends BaseDaoTestCase{

	private IVisibilityDAO visibilityDAO;

	public void setVisibilityDAO(IVisibilityDAO visibilityDAO) {
		this.visibilityDAO = visibilityDAO;
	}
	
	public void test()
	{
		visibilityDAO.getAll();
	}
}
