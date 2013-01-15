package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterInfoDAO;

public class VoterInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterInfoDAO voterInfoDAO;

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}
	
	public void test()
	{
		voterInfoDAO.getAll();
	}
}
