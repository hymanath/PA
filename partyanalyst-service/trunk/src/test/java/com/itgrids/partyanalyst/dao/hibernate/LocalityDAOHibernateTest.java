package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ILocalityDAO;

public class LocalityDAOHibernateTest extends BaseDaoTestCase{
	
	private ILocalityDAO localityDAO;

	public ILocalityDAO getLocalityDAO() {
		return localityDAO;
	}

	public void setLocalityDAO(ILocalityDAO localityDAO) {
		this.localityDAO = localityDAO;
	}
	public void test()
	{
		localityDAO.getAll();
	}

}
