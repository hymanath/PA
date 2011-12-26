package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISpecialPageUpdatesEmailDAO;

public class SpecialPageUpdatesEmailDAOHibernateTest extends BaseDaoTestCase {
	private ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO;
	
	
	public void setSpecialPageUpdatesEmailDAO(
			ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO) {
		this.specialPageUpdatesEmailDAO = specialPageUpdatesEmailDAO;
	}


	public void test()
	{
		specialPageUpdatesEmailDAO.getAll();
	}

}
