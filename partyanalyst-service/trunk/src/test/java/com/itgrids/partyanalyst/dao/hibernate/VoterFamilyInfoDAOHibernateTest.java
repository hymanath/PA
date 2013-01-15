package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterFamilyInfoDAO;

public class VoterFamilyInfoDAOHibernateTest extends BaseDaoTestCase{

	private IVoterFamilyInfoDAO voterFamilyInfoDAO;

	public void setVoterFamilyInfoDAO(IVoterFamilyInfoDAO voterFamilyInfoDAO) {
		this.voterFamilyInfoDAO = voterFamilyInfoDAO;
	}

	public void test()
	{
		voterFamilyInfoDAO.getAll();
	}
}
