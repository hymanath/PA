package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessDAO;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class MobileAppUserAccessDAOHibernateTest extends BaseDaoTestCase{
	
	private IMobileAppUserAccessDAO mobileAppUserAccessDAO;


	public void setMobileAppUserAccessDAO(
			IMobileAppUserAccessDAO mobileAppUserAccessDAO) {
		this.mobileAppUserAccessDAO = mobileAppUserAccessDAO;
	}
	
	/*public void test()
	{
		mobileAppUserAccessDAO.getAll();
	}*/

	public void testgetAuthorisedRecords()
	{
	DateUtilService date = new DateUtilService();
	List<Object> list = mobileAppUserAccessDAO.getAuthorisedRecords("u1","m1");
	System.out.println(list.get(0));
	}
}
