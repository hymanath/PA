package com.itgrids.partyanalyst.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

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
	 * @return Date
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
	
	public String getCurrentDateInStringFormat()
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			return sdf.format(new Date());
			
		}catch(Exception e){
			log.error("Exception Occured in DateUtilService.getCurrentDateInStringFormat() " +
					" check for log details");
			return null;
		}
	}
	
	public String getYesterdayDateString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);    
        return dateFormat.format(cal.getTime());
	}
	public Date getDayBeforeYesterDayDate()
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
			calendar.add(Calendar.DATE, -2);
			
			return calendar.getTime();
		}catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTime() " +
			" check for log details");
			return null;
		}
	}
	
	public Date getYesterDayDate()
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
			calendar.add(Calendar.DATE, -1);
			
			return calendar.getTime();
		}catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTime() " +
			" check for log details");
			return null;
		}
	}
	
	public static void main(String[] args) {
		/*System.out.println(	new DateUtilService().getDayBeforeYesterDayDate());
		System.out.println( new  DateUtilService().getYesterdayDateString());*/
		
		Long l = new DateUtilService().noOfDayBetweenDates("2014-11-03","2014-11-17");
		System.out.println(l);
	}
	
	public Date getDateAndTime(String DateString)
	{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_WITH_SECONDS);
			return sdf.parse(DateString);
		}catch(Exception e)
		{
			log.error("Exception Occured in DateUtilService.getDateAndTime() " +
					" check for log details");
					return null;
		}
		
	}
	
	public Long noOfDayBetweenDates(String startDate,String endDate){
		
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		Long diff = 0l;
		Long noOfDays = 0l;
		try {
		    Date date1 = myFormat.parse(startDate);
		    Date date2 = myFormat.parse(endDate);
		    diff = date2.getTime() - date1.getTime();
		    
		  //  System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
		    noOfDays =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
		    log.error("Exception Raised in noOfDayBetweenDates ");
		}
		return noOfDays+1;
	}
	
	/*public String getCurrentDateInStringFormatYYYYMMDD(){
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(new Date());
			
		}catch(Exception e){
			log.error("Exception Occured in DateUtilService.getCurrentDateInStringFormat() " +
					" check for log details");
			return null;
		}
	}*/
	
	public String getCurrentDateInStringFormatYYYYMMDD(){
		try	{
			Date updatedDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
			return sdf.format(updatedDate);
			
		}catch(Exception e){
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTimeInStringFormat() " +
					" check for log details");
			return null;
		}
	}
	
}
