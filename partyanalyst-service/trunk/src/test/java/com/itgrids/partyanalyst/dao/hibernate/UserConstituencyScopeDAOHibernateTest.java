package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.model.UserConstituencyScope;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserConstituencyScopeDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserConstituencyScopeDAO userConstituencyScopeDAO;
	
	public void setUserConstituencyScopeDAO(
			IUserConstituencyScopeDAO userConstituencyScopeDAO) {
		this.userConstituencyScopeDAO = userConstituencyScopeDAO;
	}

	public void test()
	{
		userConstituencyScopeDAO.getAll();
	}

	/*	

      @Test
     public void testConstituencyId(){
    	  List annousDetails =  userConstituencyScopeDAO.getConstituencyId(60L);
    	  System.out.println(annousDetails.size());
    	  if(annousDetails.size()>0){
    			Object[] o	= (Object[])(annousDetails.get(0));
    			
    			System.out.println(o[0].toString());
    			System.out.println(o[1].toString());
    			
    			System.out.println(o[2].toString());
    			
    			System.out.println(o[3].toString());
    			}
}
	
	/*public void testGetUserConstituencyScopeByAnnouncementId()
	{
		List<UserConstituencyScope> list = userConstituencyScopeDAO.getUserConstituencyScopeByAnnouncementId(2l);
		System.out.println(list.size());
	}*/
	
	/*public void testGetAnnouncementDetailsByUserId()
	{
		List<Object[]> list = userConstituencyScopeDAO.getAnnouncementDetailsByUserId(1l);
		System.out.println(list.size());
		
		for(Object[] objects : list)
		{
			System.out.println();
			for(Object object : objects)
			{
				System.out.print("--- "+object.toString());
			}
		}
	}
	
	public void testFindAnnouncementsByConstituencyId()
	{
		List<Object[]> list = userConstituencyScopeDAO.findAnnouncementsByConstituencyId(232l,new Date());
		System.out.println(list.size());
		
		for(Object[] param : list)
		{
			System.out.println();
			for(Object object : param)
				System.out.print("--"+object.toString());
		}
	}*/
	
		@Test
	public void testFindAnnouncementDetailsByUserIdAndDate()
	{
		Date fromDate = null;
		Date toDate = null;
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter=  new SimpleDateFormat(IConstants.DATE_PATTERN);
		String dateNow = formatter.format(currentDate.getTime());
		System.out.println(dateNow);
		
		try {
			fromDate = formatter.parse("12/08/2011");
			toDate =  formatter.parse("25/08/2011");
			System.out.println(fromDate);
			System.out.println(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Object[]> list = userConstituencyScopeDAO.findAnnouncementDetailsByUserIdDateConstId(1l,fromDate,toDate,0L);
		System.out.println(list.size());
		
		for(Object[] objects : list)
		{
			System.out.println();
			for(Object object : objects)
			{
				System.out.print("--- "+object.toString());
			}
		}
	}
	
}
