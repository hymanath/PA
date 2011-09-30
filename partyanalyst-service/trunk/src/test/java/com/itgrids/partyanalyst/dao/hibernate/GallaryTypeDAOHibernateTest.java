package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGallaryTypeDAO;

public class GallaryTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IGallaryTypeDAO gallaryTypeDAO;

	public void setGallaryTypeDAO(IGallaryTypeDAO gallaryTypeDAO) {
		this.gallaryTypeDAO = gallaryTypeDAO;
	}
	
	public void test()
	{
		gallaryTypeDAO.getAll();
	}

}
