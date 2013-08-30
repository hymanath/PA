package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDemoRequestActionsDAO;

public class DemoRequestActionsDAOHibernateTest extends BaseDaoTestCase{
	
	private IDemoRequestActionsDAO demoRequestActionsDAO;

	public void setDemoRequestActionsDAO(
			IDemoRequestActionsDAO demoRequestActionsDAO) {
		this.demoRequestActionsDAO = demoRequestActionsDAO;
	}
    
	/*public void test()
	{
		demoRequestActionsDAO.getAll();
	}*/
	
	public void testgetDemoRequestActionsByDemoRequestId()
	{
		List<Object[]> list = demoRequestActionsDAO.getDemoRequestActionsByDemoRequestId(1l);
		System.out.println(list.size());
		
	}
	
}
