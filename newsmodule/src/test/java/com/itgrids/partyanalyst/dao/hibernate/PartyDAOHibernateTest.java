package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyDAO;

public class PartyDAOHibernateTest extends BaseDaoTestCase{
	
	public IPartyDAO partyDAO;

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	
	/*public void test()
	{
		partyDAO.getAll();
	}*/

	public void testGetPartiesList()
	{
		System.out.println(partyDAO.getPartyShortName("INC345"));
	}
}
