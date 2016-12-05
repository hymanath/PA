package com.itgrids.cardprint.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.jfree.util.Log;


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
	public static void main(String[] args) {
		
	/*System.out.println(	new DateUtilService().getDayBeforeYesterDayDate());
	System.out.println( new  DateUtilService().getCurrentDateAndTime());
	System.out.println(new DateUtilService().convert12HoursDateFormatFrom24HoursDateFormat("2015-06-16 15:00:30"));*/
	
		List<String> list1 = new ArrayList<String>();
		list1.add("sasi");
		list1.add("kanth");
		list1.add("ravi");
		list1.add("itg");
		
		List<String> list2 = new ArrayList<String>();
		list2.add("jana");
		list2.add("re");
		
		boolean b = Collections.disjoint(list2, list1);
		System.out.println(b);
	}
	public String getCurrentDateInString()
	{
		try
		{
			String DATE_PATTERN = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			return sdf.format(new Date());
			
		}catch(Exception e){
			log.error("Exception Occured in DateUtilService.getCurrentDateInStringFormat() " +
					" check for log details");
			return null;
		}
	}
	
	
	public String convert12HoursDateFormatFrom24HoursDateFormat(String dateStr)
	{
		try{
			
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat outputformat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
			Date date = inputFormat.parse(dateStr);
			return outputformat.format(date);
			
			
		}catch (Exception e) {
			Log.error("Exception Occured in convert12HoursDateFormatFrom24HoursDateFormat() method, Exception - ",e);
			return null;
		}
	}
	
	//June 30 2015 1:15pm (yyyy-MM-dd hh:mm:ss)
	public String convert12HoursDateFormat(String dateStr)
	{
		try{
			
			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat outputformat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
			Date date = inputFormat.parse(dateStr);
			return outputformat.format(date);
			
			
		}catch (Exception e) {
			Log.error("Exception Occured in convert12HoursDateFormat() method, Exception - ",e);
			return null;
		}
	}
	
	public Date converToDateFromMilliSeconds(Long milliSeconds)
	{
		try{
			 Calendar geCal = new GregorianCalendar(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
    		 	Calendar calendar = Calendar.getInstance();
    		 	
    		 	calendar.setTimeInMillis(milliSeconds);
	  			calendar.set(Calendar.YEAR, geCal.get(Calendar.YEAR));
	  			calendar.set(Calendar.MONTH, geCal.get(Calendar.MONTH));
	  			calendar.set(Calendar.DAY_OF_MONTH, geCal.get(Calendar.DAY_OF_MONTH));
	  	            	calendar.set(Calendar.HOUR_OF_DAY, geCal.get(Calendar.HOUR_OF_DAY));
	  			calendar.set(Calendar.MINUTE, geCal.get(Calendar.MINUTE));
	  			calendar.set(Calendar.SECOND, geCal.get(Calendar.SECOND));
	  			calendar.set(Calendar.MILLISECOND, geCal.get(Calendar.MILLISECOND));
			
			return calendar.getTime();
		}catch (Exception e) {
			log.error("Exception Occured in DateUtilService.converToDateFromMilliSeconds() " +
			" check for log details");
			return null;
		}
	}
	
	public Date getDateByStringAndFormat(String dateString,String format)
	{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateString.trim());
		}catch(Exception e)
		{
			log.error("Exception occured in getDateByStringAndFormat - ",e);
		}
		return null;
	}
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
	
	public String getCurrentDateInStringFormatYYYYMMDDhhmmss(){
		try	{
			Date updatedDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			sdf.setTimeZone(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
			return sdf.format(updatedDate);
			
		}catch(Exception e){
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTimeInStringFormat() " +
					" check for log details");
			return null;
		}
	}
}
