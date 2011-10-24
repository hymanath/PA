package com.itgrids.partyanalyst.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
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
			return new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT).
			parse(getCurrentDateAndTimeInStringFormat());
		}catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTime() " +
			" check for log details");
			return null;
		}
	}

}
