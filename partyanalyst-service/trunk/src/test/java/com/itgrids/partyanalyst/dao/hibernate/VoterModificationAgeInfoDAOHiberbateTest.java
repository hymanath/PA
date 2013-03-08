package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;

public class VoterModificationAgeInfoDAOHiberbateTest extends BaseDaoTestCase{
	
	private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;

	public void setVoterModificationAgeInfoDAO(
			IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
		this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
	}
	
	
	public void test()
	{
		voterModificationAgeInfoDAO.getAll();
	}

}
