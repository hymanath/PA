package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IJobDAO;

public class JobDAOHibernateTest extends BaseDaoTestCase{
	
	private IJobDAO jobDAO;

	
	public void setJobDAO(IJobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}



	public void test()
	{
		jobDAO.getAll();
	}
	
}
