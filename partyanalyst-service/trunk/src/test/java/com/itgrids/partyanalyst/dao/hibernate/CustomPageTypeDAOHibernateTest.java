package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomPageTypeDAO;

public class CustomPageTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private ICustomPageTypeDAO customPageTypeDAO;

	public void setCustomPageTypeDAO(ICustomPageTypeDAO customPageTypeDAO) {
		this.customPageTypeDAO = customPageTypeDAO;
	}

	public void test()
	{
		customPageTypeDAO.getAll();
	}
}
