package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyFileKeywordDAO;

public class PartyFileKeywordDAOHibernateTest extends BaseDaoTestCase{
	
	private IPartyFileKeywordDAO partyFileKeywordDAO;

	public void setPartyFileKeywordDAO(IPartyFileKeywordDAO partyFileKeywordDAO) {
		this.partyFileKeywordDAO = partyFileKeywordDAO;
	}
	
	public void test()
	{
	  partyFileKeywordDAO.getAll();
	}

}
