package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IBoothActivitiesDAO;

public class BoothActivitiesDAOHibernateTest extends BaseDaoTestCase{

	private IBoothActivitiesDAO boothActivitiesDAO;

	public void setBoothActivitiesDAO(IBoothActivitiesDAO boothActivitiesDAO) {
		this.boothActivitiesDAO = boothActivitiesDAO;
	}
	
	public void test()
	{
		boothActivitiesDAO.getAll();
	}
}
