package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;

public class VoterPartyInfoDAOHibernateTest extends BaseDaoTestCase{
	private IVoterPartyInfoDAO voterPartyInfoDAO;

	

	public void setVoterPartyInfoDAO(IVoterPartyInfoDAO voterPartyInfoDAO) {
		this.voterPartyInfoDAO = voterPartyInfoDAO;
	}
	
	public void test()
	{
		voterPartyInfoDAO.getAll();
	}
}
