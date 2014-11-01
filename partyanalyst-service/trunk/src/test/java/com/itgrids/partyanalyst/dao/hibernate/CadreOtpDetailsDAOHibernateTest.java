package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;

public class CadreOtpDetailsDAOHibernateTest extends BaseDaoTestCase {

	private ICadreOtpDetailsDAO cadreOtpDetailsDAO;

	public void setCadreOtpDetailsDAO(ICadreOtpDetailsDAO cadreOtpDetailsDAO) {
		this.cadreOtpDetailsDAO = cadreOtpDetailsDAO;
	}
	
	/*public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		
		cal.add(Calendar.DATE, -1);
		Date yesterDay = cal.getTime();
		
		try {
			//System.out.println(format1.parse("2014-10-29 15:37:05"));
			List<Object[]> list = cadreOtpDetailsDAO.getOtpDetailsForDates(null, yesterDay);
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		
		cal.add(Calendar.DATE, -25);
		Date yesterDay = cal.getTime();
		
		try {
			//System.out.println(format1.parse("2014-10-29 15:37:05"));
			List<Object[]> list = cadreOtpDetailsDAO.getOfflineTxnDetailsIdsForDates(today,yesterDay);
			System.out.println(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*public void testDetails()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		
		cal.add(Calendar.DATE, -25);
		Date yesterDay = cal.getTime();
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(232L);
		locationIds.add(282L);
		
			//System.out.println(format1.parse("2014-10-29 15:37:05"));
			List<Object[]> list = cadreOtpDetailsDAO.getLocationWiseTransactionsByDates(yesterDay,today,locationIds," and model.cadreTxnDetails.constituency.constituencyId in (:locationIdList) ");
			System.out.println(list);
		

	}
	*/
}
