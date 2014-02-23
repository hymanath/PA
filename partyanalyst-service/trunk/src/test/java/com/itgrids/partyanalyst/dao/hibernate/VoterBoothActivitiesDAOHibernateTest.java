package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;

public class VoterBoothActivitiesDAOHibernateTest extends BaseDaoTestCase{

	private IVoterBoothActivitiesDAO voterBoothActivitiesDAO;

	public void setVoterBoothActivitiesDAO(
			IVoterBoothActivitiesDAO voterBoothActivitiesDAO) {
		this.voterBoothActivitiesDAO = voterBoothActivitiesDAO;
	}
	
	public void test()
	{
		voterBoothActivitiesDAO.getAll();
	}
}
