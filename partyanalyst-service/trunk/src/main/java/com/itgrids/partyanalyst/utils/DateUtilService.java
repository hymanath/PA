package com.itgrids.partyanalyst.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;

/**
 * This Class Provides Utility Services On Date,Time and Calander.
 *  @author Kamalakar Dandu
 */
public class DateUtilService {
	
	private static final Logger log = Logger.getLogger(DateUtilService.class);
	/**
	 * This method returns Current Date in yyyy-MM-dd hh:mm:ss in String 
	 * Format with default Indian Time Zone.
	 * @return String
	 * @author Kamalakar Dandu
	 */
	public String getCurrentDateAndTimeInStringFormat()
	{
		try
		{
			Date updatedDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
			return sdf.format(updatedDate);
			
		}catch(Exception e){
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTimeInStringFormat() " +
					" check for log details");
			return null;
		}
	}
	
	/**
	 * This method returns Current Date in yyyy-MM-dd hh:mm:ss 
	 * Format of Date Object with default Indian Time Zone.
	 * @return String
	 * @author Kamalakar Dandu
	 */
	public Date getCurrentDateAndTime()
	{
		try{
			Date updatedDate = new Date();
			Calendar geCal = new GregorianCalendar(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
			geCal.setTimeInMillis(updatedDate.getTime());

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, geCal.get(Calendar.YEAR));
			calendar.set(Calendar.MONTH, geCal.get(Calendar.MONTH));
			calendar.set(Calendar.DAY_OF_MONTH, geCal.get(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, geCal.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, geCal.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, geCal.get(Calendar.SECOND));
			calendar.set(Calendar.MILLISECOND, geCal.get(Calendar.MILLISECOND));
			
			return calendar.getTime();
			
		}catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTime() " +
			" check for log details");
			return null;
		}
	}

}
