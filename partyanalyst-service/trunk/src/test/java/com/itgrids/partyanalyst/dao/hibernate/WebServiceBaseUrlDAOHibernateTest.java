package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;

public class WebServiceBaseUrlDAOHibernateTest extends BaseDaoTestCase{

	public IWebServiceBaseUrlDAO webServiceBaseUrlDAO;

	public void setWebServiceBaseUrlDAO(IWebServiceBaseUrlDAO webServiceBaseUrlDAO) {
		this.webServiceBaseUrlDAO = webServiceBaseUrlDAO;
	}
	
	/*public void test()
	{
		webServiceBaseUrlDAO.getAll();
	}*/
	
	public void testGetBaseURLForAnApp()
	{
		webServiceBaseUrlDAO.getAll();
		System.out.println(webServiceBaseUrlDAO.getBaseURLForAnApp("IPAD"));
	}
	
}
