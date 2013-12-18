package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPingingTypeDAO;



public class PingingTypeDAOHibernateTest extends BaseDaoTestCase{
	private IPingingTypeDAO pingingTypeDAO;

	
	
public void setPingingTypeDAO(IPingingTypeDAO pingingTypeDAO) {
		this.pingingTypeDAO = pingingTypeDAO;
	}



public void test()
{
	pingingTypeDAO.getAll();
}
}
