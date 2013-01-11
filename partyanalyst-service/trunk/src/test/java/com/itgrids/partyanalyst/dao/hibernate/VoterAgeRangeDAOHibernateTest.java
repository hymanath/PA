package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;

public class VoterAgeRangeDAOHibernateTest extends BaseDaoTestCase{

private IVoterAgeRangeDAO voterAgeRangeDAO;
	
	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}
	
	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	public void test()
	{
		voterAgeRangeDAO.getAll();
	}
}
