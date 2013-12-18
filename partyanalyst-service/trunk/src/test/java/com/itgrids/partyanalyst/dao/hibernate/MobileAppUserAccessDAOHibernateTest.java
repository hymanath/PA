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
	List<Object[]> list = mobileAppUserAccessDAO.getMobileAppUserdetailsByMobileAppUserId(48l);
		for(Object[] params : list)
		{
			System.out.println(params[0] +" "+ params[1] +" "+params[2]);
			System.out.println(params[3] +" "+ params[4] +" "+params[5]);
			System.out.println(params[6] +" "+ params[7] +" "+params[8]);
			System.out.println(params[9] +" "+ params[10] +" "+params[11]);
			System.out.println(params[12] +" "+ params[13] +" "+params[14]);
			System.out.println(params[15] +" "+ params[16]);
			
		}
	}
}
