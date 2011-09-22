package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;

public class SmsHistoryDAOHibernateTest extends BaseDaoTestCase {
	
	private ISmsHistoryDAO smsHistoryDAO;
	
	public void setSmsHistoryDAO(ISmsHistoryDAO smsHistoryDAO) {
		this.smsHistoryDAO = smsHistoryDAO;
	}
 
	/*public void testGetDataByHistoryId(){
		java.util.List<SmsHistory> result = smsHistoryDAO.findByHistoryId(1l);
		System.out.println(result.size());
	}*/
	
	
	public void test()
	{
		smsHistoryDAO.getAll();
	}
}
