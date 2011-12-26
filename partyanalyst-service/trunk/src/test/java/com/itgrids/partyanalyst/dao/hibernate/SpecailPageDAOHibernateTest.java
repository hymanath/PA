package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageDAO;

public class SpecailPageDAOHibernateTest extends BaseDaoTestCase{
	
	private ISpecialPageDAO specialPageDAO;

	public void setSpecialPageDAO(ISpecialPageDAO specialPageDAO) {
		this.specialPageDAO = specialPageDAO;
	}
	
	public void test()
	{
		specialPageDAO.getAll();
	}

}
