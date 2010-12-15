package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationConstituencyTownDAO;

public class DelimitationConstituencyTownDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO;

	public void setDelimitationConstituencyTownDAO(
			IDelimitationConstituencyTownDAO delimitationConstituencyTownDAO) {
		this.delimitationConstituencyTownDAO = delimitationConstituencyTownDAO;
	}
	
	public void test()
	{
		delimitationConstituencyTownDAO.getAll();
	}
	

}
