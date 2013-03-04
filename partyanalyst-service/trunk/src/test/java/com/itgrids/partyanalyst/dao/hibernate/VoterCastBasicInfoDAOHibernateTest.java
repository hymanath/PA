package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;

public class VoterCastBasicInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;

	public void setVoterCastBasicInfoDAO(
			IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
		this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
	}
	
	public void test()
	{
		voterCastBasicInfoDAO.getAll();
	}
}
