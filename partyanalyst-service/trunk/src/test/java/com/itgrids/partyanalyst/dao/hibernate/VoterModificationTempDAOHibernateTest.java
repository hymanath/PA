package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterModificationTempDAO;

public class VoterModificationTempDAOHibernateTest extends BaseDaoTestCase{

	private IVoterModificationTempDAO voterModificationTempDAO;

	public void setVoterModificationTempDAO(
			IVoterModificationTempDAO voterModificationTempDAO) {
		this.voterModificationTempDAO = voterModificationTempDAO;
	}
	
	public void test()
	{
		voterModificationTempDAO.getAll();
	}
}
