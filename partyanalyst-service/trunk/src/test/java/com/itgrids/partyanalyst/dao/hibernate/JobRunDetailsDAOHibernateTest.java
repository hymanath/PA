package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IJobRunDetailsDAO;

public class JobRunDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private IJobRunDetailsDAO jobRunDetailsDAO;


	public void setJobRunDetailsDAO(IJobRunDetailsDAO jobRunDetailsDAO) {
		this.jobRunDetailsDAO = jobRunDetailsDAO;
	}
	
	public void test()
	{
		jobRunDetailsDAO.getAll();
	}

}
