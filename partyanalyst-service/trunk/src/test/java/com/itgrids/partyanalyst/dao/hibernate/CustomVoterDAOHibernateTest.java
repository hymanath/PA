package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICustomVoterDAO;

public class CustomVoterDAOHibernateTest extends BaseDaoTestCase{
	
	private ICustomVoterDAO customVoterDAO;

	public ICustomVoterDAO getCustomVoterDAO() {
		return customVoterDAO;
	}

	public void setCustomVoterDAO(ICustomVoterDAO customVoterDAO) {
		this.customVoterDAO = customVoterDAO;
	}
	/*
	public void test()
	{
		customVoterDAO.getAll();
	}*/

	public void testgetCasteWiseCustomVotersCount()
	{
		List<Object[]> list = customVoterDAO.getCasteWiseCustomVotersCount(1l, 1l);
		System.out.println(list.size());
	}
}
