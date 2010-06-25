package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISmsHistoryDAO;
import com.itgrids.partyanalyst.dao.ISmsTrackDAO;
import com.itgrids.partyanalyst.model.SmsHistory;
import com.itgrids.partyanalyst.model.SmsTrack;

public class SmsHistoryDAOHibernateTest extends BaseDaoTestCase {
	
	private ISmsHistoryDAO smsHistoryDAO;
	private ISmsTrackDAO smsTrackDAO;
	
	public ISmsTrackDAO getSmsTrackDAO() {
		return smsTrackDAO;
	}

	public void setSmsTrackDAO(ISmsTrackDAO smsTrackDAO) {
		this.smsTrackDAO = smsTrackDAO;
	}

		 
	
	public void testGetDataByHistoryId(){
		java.util.List<SmsHistory> result = smsHistoryDAO.findByHistoryId(1l);
		System.out.println(result.size());
	}
	
	
	public ISmsHistoryDAO getSmsHistoryDAO() {
		return smsHistoryDAO;
	}

	public void setSmsHistoryDAO(ISmsHistoryDAO smsHistoryDAO) {
		this.smsHistoryDAO = smsHistoryDAO;
	}

}
