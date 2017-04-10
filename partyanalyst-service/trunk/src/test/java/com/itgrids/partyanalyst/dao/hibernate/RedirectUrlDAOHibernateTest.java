package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IRedirectUrlDAO;

public class RedirectUrlDAOHibernateTest extends BaseDaoTestCase{

	private IRedirectUrlDAO redirectUrlDAO;

	public void setRedirectUrlDAO(IRedirectUrlDAO redirectUrlDAO) {
		this.redirectUrlDAO = redirectUrlDAO;
	}
	
	public void test()
	{
		redirectUrlDAO.getAll();
	}
}
