package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterBasicInfoDAO;

public class VoterBasicInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterBasicInfoDAO voterBasicInfoDAO;

	public void setVoterBasicInfoDAO(IVoterBasicInfoDAO voterBasicInfoDAO) {
		this.voterBasicInfoDAO = voterBasicInfoDAO;
	}
	
	public void test()
	{
		voterBasicInfoDAO.getAll();
	}
}
