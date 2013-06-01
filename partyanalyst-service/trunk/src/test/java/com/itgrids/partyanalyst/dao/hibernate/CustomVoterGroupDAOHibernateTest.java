package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterGroupDAO;

public class CustomVoterGroupDAOHibernateTest extends BaseDaoTestCase{

	private ICustomVoterGroupDAO customVoterGroupDAO;

	public ICustomVoterGroupDAO getCustomVoterGroupDAO() {
		return customVoterGroupDAO;
	}

	public void setCustomVoterGroupDAO(ICustomVoterGroupDAO customVoterGroupDAO) {
		this.customVoterGroupDAO = customVoterGroupDAO;
	}
	
	/*public void test()
	{
		customVoterGroupDAO.getAll();
	}*/
	
	public void testGetCustomVoterGroupNameByGroupId()
	{
	  System.out.println(customVoterGroupDAO.getCustomVoterGroupNameByGroupId(1l));
	}
	
}
