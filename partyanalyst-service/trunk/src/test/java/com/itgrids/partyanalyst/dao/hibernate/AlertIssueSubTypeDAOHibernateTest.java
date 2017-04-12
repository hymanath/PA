package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAlertIssueSubTypeDAO;

public class AlertIssueSubTypeDAOHibernateTest extends BaseDaoTestCase{

	private IAlertIssueSubTypeDAO alertIssueSubTypeDAO;

	public void setAlertIssueSubTypeDAO(IAlertIssueSubTypeDAO alertIssueSubTypeDAO) {
		this.alertIssueSubTypeDAO = alertIssueSubTypeDAO;
	}
	
	public void test()
	{
		alertIssueSubTypeDAO.getAll();
	}
	
	public void testGetSubTypesByAlertIssueType()
	{
		List<Object[]> list = alertIssueSubTypeDAO.getSubTypesByAlertIssueType(1L);
		System.out.println(list.size());
	}
}
