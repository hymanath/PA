package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IDemoRequestDAO;
import com.itgrids.partyanalyst.model.DemoRequest;

public class DemoRequestDAOHibernateTest extends BaseDaoTestCase{
	private IDemoRequestDAO demoRequestDAO;

	public void setDemoRequestDAO(IDemoRequestDAO demoRequestDAO) {
		this.demoRequestDAO = demoRequestDAO;
	}
	
	/*public void test()
	{
		demoRequestDAO.getAll();
	}*/
	public void testgetDemoRequestList()
	{
		List<DemoRequest> list = demoRequestDAO.getDemoRequestList();
		System.out.println(list.size());
	}
	
	/*public void testgetDemoRequestByDemoRequestId()
	{
		DemoRequest demoRequest = demoRequestDAO.getDemoRequestByDemoRequestId(1l);
		System.out.println(demoRequest.getMobileNo());
	}*/
	
	/*public void testdeleteDemoRequestByDemoRequestId()
	{
		System.out.println(demoRequestDAO.deleteDemoRequestByDemoRequestId(1l));
	}*/

}
