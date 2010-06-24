package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;
import com.itgrids.partyanalyst.model.SmsHistory;

public class SmsHistoryDAOHibernateTest extends BaseDaoTestCase {
	
	private ISmsHistoryDAO SmsHistoryDAO;

	public ISmsHistoryDAO getSmsHistoryDAO() {
		return SmsHistoryDAO;
	}

	public void setSmsHistoryDAO(ISmsHistoryDAO smsHistoryDAO) {
		SmsHistoryDAO = smsHistoryDAO;
	}
	 
	
	public void testGetDataByHistoryId(){
		java.util.List<SmsHistory> result = SmsHistoryDAO.findByHistoryId(1l);
		System.out.println(result.size());
	}
}
