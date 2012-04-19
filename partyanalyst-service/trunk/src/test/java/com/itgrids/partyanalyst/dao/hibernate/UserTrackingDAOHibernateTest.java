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
	
	public void test()
	{
		userTrackingDAO.getAll();
	}
	
	public void testGetUniqueVisitorsBetweenDates(){
		Long sessCnt=(Long)userTrackingDAO.getUniqueVisitorsBetweenDates(new Date(2012-04-12),new Date() , IConstants.PARTY_ANALYST_USER);
		System.out.println("Session Count: \t"+sessCnt.longValue());
	}
	 public Date getDate(String dateStr){
		  String[] dateArray =  dateStr.split("-");
		  Calendar cal = Calendar.getInstance(); 
		  cal.set(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1])-1, Integer.parseInt(dateArray[2]));
		  return cal.getTime();
	  }
	
}


