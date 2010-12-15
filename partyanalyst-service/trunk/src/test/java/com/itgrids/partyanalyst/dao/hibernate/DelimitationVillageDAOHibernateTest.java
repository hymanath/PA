package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDelimitationVillageDAO;

public class DelimitationVillageDAOHibernateTest extends BaseDaoTestCase{
	
	private IDelimitationVillageDAO delimitationVillageDAO;

	public void setDelimitationVillageDAO(
			IDelimitationVillageDAO delimitationVillageDAO) {
		this.delimitationVillageDAO = delimitationVillageDAO;
	}
	
	public void test()
	{
		delimitationVillageDAO.getAll();
	}

}
