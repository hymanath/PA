package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;

public class VoterCastInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterCastInfoDAO voterCastInfoDAO;

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}
	
	public void test()
	{
		voterCastInfoDAO.getAll();
	}
}
