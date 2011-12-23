package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;

public class PanchayatHamletDAOHibernateTest extends BaseDaoTestCase{

	private IPanchayatHamletDAO panchayatHamletDAO;

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}
	
	public void test()
	{
		panchayatHamletDAO.getAll();
	}
}
