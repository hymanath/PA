package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;

public class VoterModificationInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationInfoDAO voterModificationInfoDAO;

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}
	
	public void test()
	{
		voterModificationInfoDAO.getAll();
	}
}
