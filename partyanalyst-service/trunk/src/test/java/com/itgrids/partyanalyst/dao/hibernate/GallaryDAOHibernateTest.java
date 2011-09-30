package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGallaryDAO;
public class GallaryDAOHibernateTest extends BaseDaoTestCase{
	
	private IGallaryDAO gallaryDAO;

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}
	
	public void test()
	{
		gallaryDAO.getAll();
	}

}
