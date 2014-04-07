package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterNamesTempDAO;

public class VoterNamesTempDAOHibernateTest extends BaseDaoTestCase{
	
	private IVoterNamesTempDAO voterNamesTempDAO;

	public void setVoterNamesTempDAO(IVoterNamesTempDAO voterNamesTempDAO) {
		this.voterNamesTempDAO = voterNamesTempDAO;
	}
	
	public void test()
	{
		voterNamesTempDAO.getAll();
		
	}
}
