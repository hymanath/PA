package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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

	/*public void testGetPartiesList()
	{
		System.out.println(partyDAO.getPartyShortName("INC345"));
	}*/
	public void testPartiesListByStateId(){
		//List<Object[]> parties = partyDAO.getPartiesListByStateId(1l);
		//System.out.println(parties.size());
	}
}
