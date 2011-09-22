package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ISmsTrackDAO;

public class SmsTrackDAOHibernateTest extends BaseDaoTestCase {

	private ISmsTrackDAO smsTrackDAO;

	public void setSmsTrackDAO(ISmsTrackDAO smsTrackDAO) {
		this.smsTrackDAO = smsTrackDAO;
	}
	
	/*public void testGetDataBySmsTrackId(){
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
	}*/
	
	/*public void test()
	{
		smsTrackDAO.getAll();
	}*/
	
	/*public void testGetRemainingSMSCountForAUser()
	{
		Long count = (Long)smsTrackDAO.getRemainingSMSCountForAUser(1l);
		System.out.println(count);
	}*/
	
	/*public void testGetUserSmsDetails()
	{
		List<Object[]> list = smsTrackDAO.getUserSmsDetails(1l);
		
		System.out.println(list.size());
		
		for(Object[] param : list)
		{
			System.out.println(param[0]);
			System.out.println(param[1]);
			System.out.println(param[2]);
		}
		
	}*/
	
	public void testUpdateRemainingSmsLeftForUser()
	{
		Long count = (Long)smsTrackDAO.getRemainingSMSCountForAUser(1l);
		System.out.println(count);
		Integer i = smsTrackDAO.updateRemainingSmsLeftForUser(1l,100l);
		System.out.println("rows updated - "+ i);
		count = (Long)smsTrackDAO.getRemainingSMSCountForAUser(1l);
		System.out.println(count);
		
	}
}
