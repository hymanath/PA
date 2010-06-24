package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ISmsTrackDAO;
import com.itgrids.partyanalyst.model.SmsTrack;

public class SmsTrackDAOHibernateTest extends BaseDaoTestCase {

	private ISmsTrackDAO smsTrackDAO;

	public ISmsTrackDAO getSmsTrackDAO() {
		return smsTrackDAO;
	}

	public void setSmsTrackDAO(ISmsTrackDAO smsTrackDAO) {
		this.smsTrackDAO = smsTrackDAO;
	}
	
	public void testGetDataBySmsTrackId(){
		java.util.List result = smsTrackDAO.findBySsmsTrackId(1l);
		System.out.println(result.size());
	}
	
	public void testGetCount(){
		int smsTrackCount = smsTrackDAO.getAll().size();
		System.out.println("Count-->"+smsTrackCount);		
	}
	
	public void testGetLatestRenewalDateForUser(){
		List<SmsTrack> result = smsTrackDAO.findLatestRenewalDate(7l);
		for(SmsTrack latestDate : result){
			System.out.println(latestDate.getRenewalDate()+"\t\t"+latestDate.getRegistration().getRegistrationId());
		}
	}
}
