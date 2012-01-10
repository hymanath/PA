package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomPageDAO;

public class CustomPageDAOHibernateTest extends BaseDaoTestCase{
	
	private ICustomPageDAO customPageDAO;

	public void setCustomPageDAO(ICustomPageDAO customPageDAO) {
		this.customPageDAO = customPageDAO;
	}
	
	public void test()
	{
		customPageDAO.getAll();
	}

}
