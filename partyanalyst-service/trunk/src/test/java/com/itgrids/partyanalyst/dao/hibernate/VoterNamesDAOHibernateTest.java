package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterNamesDAO;

public class VoterNamesDAOHibernateTest extends BaseDaoTestCase {

	private IVoterNamesDAO voterNamesDAO;

	public void setVoterNamesDAO(IVoterNamesDAO voterNamesDAO) {
		this.voterNamesDAO = voterNamesDAO;
	}
	
	public void test()
	{
		voterNamesDAO.getAll();
	}
}
