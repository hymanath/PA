package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyPageCustomPagesDAO;

public class PartyPageCustomPagesDAOHibernateTest extends BaseDaoTestCase{
	
	private IPartyPageCustomPagesDAO partyPageCustomPagesDAO;

	public void setPartyPageCustomPagesDAO(
			IPartyPageCustomPagesDAO partyPageCustomPagesDAO) {
		this.partyPageCustomPagesDAO = partyPageCustomPagesDAO;
	}
	
	public void test()
	{
		partyPageCustomPagesDAO.getAll();
	}

}
