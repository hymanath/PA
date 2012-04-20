package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserTrackingDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserTrackingDAOHibernateTest extends BaseDaoTestCase{
	private IUserTrackingDAO userTrackingDAO;

	public void setUserTrackingDAO(IUserTrackingDAO userTrackingDAO) {
		this.userTrackingDAO = userTrackingDAO;
	}
	
	/*public void test()
	{
		userTrackingDAO.getAll();
	}*/
	/*public void testGetHostNameAndNoOfPagesForAVisitor()
	{
		List<Object[]> list = userTrackingDAO.getHostNameAndNoOfPagesForAVisitor(new Date() , new Date());
		System.out.println(list.size());
		if(list != null && list.size() > 0 )
			
			for(Object[] params : list)
			{
				System.out.println(list.size());
				System.out.println(params[0]+ " " +params[1]+ " " +params[2]+ " " +params[3]);
				Date max = (Date)params[2];
				Date min = (Date)params[3];
				
				Long SponOnPage = (max.getTime() - min.getTime())/(1000*60);
				System.out.println(max);
				System.out.println(min);
				System.out.println((max.getTime() - min.getTime())/(1000*60));
				Date dd = new Date(SponOnPage);
				System.out.println("spent on time is........" +dd.getMinutes());
				String s = Long.toString(dd.getMinutes());
				System.out.println(s.toString());
				
			}
	}
	*/
	
	/*public void testGetUniqueVisitorsBetweenDates(){
		Long sessCnt=(Long)userTrackingDAO.getUniqueVisitorsBetweenDates(new Date(2012-04-12),new Date() , IConstants.PARTY_ANALYST_USER);
		System.out.println("Session Count: \t"+sessCnt.longValue());
	}
	 public Date getDate(String dateStr){
		  String[] dateArray =  dateStr.split("-");
		  Calendar cal = Calendar.getInstance(); 
		  cal.set(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
		  return cal.getTime();
	  }*/
		
	public void testGetHostNameAndNoOfPagesForAUser(){
		List<Object[]> list = userTrackingDAO.getHostNameAndNoOfPagesForAUser(new Date(2012-4-1),new Date(),IConstants.PARTY_ANALYST_USER);
		System.out.println(list.size());
		if(list != null && list.size() > 0) 
		for(Object[] params : list){
			System.out.println(list.size());
			System.out.println(params[0] + "" + params[1] + "" +params[2] + "" +params[3]);
			System.out.println(params[1].toString()+" "+params[2].toString());
		}
	}
}
