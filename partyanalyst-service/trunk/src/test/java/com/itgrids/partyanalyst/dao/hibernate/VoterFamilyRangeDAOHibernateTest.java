package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterFamilyRangeDAO;

public class VoterFamilyRangeDAOHibernateTest extends BaseDaoTestCase{

private IVoterFamilyRangeDAO voterFamilyRangeDAO;
	
	public void setVoterFamilyRangeDAO(IVoterFamilyRangeDAO voterFamilyRangeDAO) {
		this.voterFamilyRangeDAO = voterFamilyRangeDAO;
	}
	
	public void test()
	{
		voterFamilyRangeDAO.getAll();
	}
}
