package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterTempDAO;

public class VoterTempDAOHibernateTest extends BaseDaoTestCase{

	private IVoterTempDAO voterTempDAO;

	public void setVoterTempDAO(IVoterTempDAO voterTempDAO) {
		this.voterTempDAO = voterTempDAO;
	}
	
	public void test()
	{
		voterTempDAO.get(9l);
	}
}
