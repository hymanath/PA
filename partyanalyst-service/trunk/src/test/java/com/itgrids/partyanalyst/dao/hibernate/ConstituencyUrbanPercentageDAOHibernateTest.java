package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IConstituencyUrbanPercentageDAO;

public class ConstituencyUrbanPercentageDAOHibernateTest extends BaseDaoTestCase{
	
	private IConstituencyUrbanPercentageDAO constituencyUrbanPercentageDAO;

	public void setConstituencyUrbanPercentageDAO(
			IConstituencyUrbanPercentageDAO constituencyUrbanPercentageDAO) {
		this.constituencyUrbanPercentageDAO = constituencyUrbanPercentageDAO;
	}
	
	public void test()
	{
		constituencyUrbanPercentageDAO.getAll();
	}

}
