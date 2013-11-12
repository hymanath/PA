package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IFontDAO;

public class FontDAOHibernateTest extends BaseDaoTestCase{
	
	private IFontDAO fontDAO;

	public void setFontDAO(IFontDAO fontDAO) {
		this.fontDAO = fontDAO;
	}
	
	public void test()
	{
		fontDAO.getAll();	
	}
}
