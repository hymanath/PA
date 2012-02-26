package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IMinisterTypeDAO;

public class MinisterTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IMinisterTypeDAO ministerTypeDAO;

	public void setMinisterTypeDAO(IMinisterTypeDAO ministerTypeDAO) {
		this.ministerTypeDAO = ministerTypeDAO;
	}
	
	public void test()
	{
		ministerTypeDAO.getAll();
	}

}
