package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;

public class VoterAgeInfoDAOHibernateTest extends BaseDaoTestCase{
	
	private IVoterAgeInfoDAO voterAgeInfoDAO;

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}
	
	public void test()
	{
		voterAgeInfoDAO.getAll();
	}

}
