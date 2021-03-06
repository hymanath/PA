package com.itgrids.partyanalyst.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

/**
 * This Class Provides Utility Services On Date,Time and Calander.
 * 
 * @author Kamalakar Dandu
 */
public class DateUtilService {

	private static final Logger log = Logger.getLogger(DateUtilService.class);

	/**
	 * This method returns Current Date in yyyy-MM-dd hh:mm:ss in String Format
	 * with default Indian Time Zone.
	 * 
	 * @return String
	 * @author Kamalakar Dandu
	 */
	public String getCurrentDateAndTimeInStringFormat() {
		try {
			Date updatedDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_AND_TIME_FORMAT);
			sdf.setTimeZone(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
			return sdf.format(updatedDate);

		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTimeInStringFormat() "
					+ " check for log details");
			return null;
		}
	}

	/**
	 * This method returns Current Date in yyyy-MM-dd hh:mm:ss Format of Date
	 * Object with default Indian Time Zone.
	 * 
	 * @return Date
	 * @author Kamalakar Dandu
	 */
	public Date getCurrentDateAndTime() {
		try {
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

		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTime() " + " check for log details");
			return null;
		}
	}

	public String getCurrentDateInStringFormat() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
			return sdf.format(new Date());

		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateInStringFormat() " + " check for log details");
			return null;
		}
	}

	public String getYesterdayDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return dateFormat.format(cal.getTime());
	}

	public Date getDayBeforeYesterDayDate() {
		try {
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
		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTime() " + " check for log details");
			return null;
		}
	}

	public Date getYesterDayDate() {
		try {
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
		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTime() " + " check for log details");
			return null;
		}
	}

	
	public Date getDateAndTime(String DateString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_WITH_SECONDS);
			return sdf.parse(DateString);
		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getDateAndTime() " + " check for log details");
			return null;
		}

	}

	public Long noOfDayBetweenDates(String startDate, String endDate) {

		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		Long diff = 0l;
		Long noOfDays = 0l;
		try {
			Date date1 = myFormat.parse(startDate);
			Date date2 = myFormat.parse(endDate);
			diff = date2.getTime() - date1.getTime();

			// System.out.println ("Days: " + TimeUnit.DAYS.convert(diff,
			// TimeUnit.MILLISECONDS));
			noOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			log.error("Exception Raised in noOfDayBetweenDates ");
		}
		return noOfDays + 1;
	}

	/*
	 * public String getCurrentDateInStringFormatYYYYMMDD(){ try {
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); return
	 * sdf.format(new Date());
	 * 
	 * }catch(Exception e){ log.error(
	 * "Exception Occured in DateUtilService.getCurrentDateInStringFormat() " +
	 * " check for log details"); return null; } }
	 */

	public String getCurrentDateInStringFormatYYYYMMDD() {
		try {
			Date updatedDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(TimeZone.getTimeZone(IConstants.TIME_ZONE_INDIA));
			return sdf.format(updatedDate);

		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateAndTimeInStringFormat() "
					+ " check for log details");
			return null;
		}
	}

	public Date getDateByStringAndFormat(String dateString, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateString.trim());
		} catch (Exception e) {
			log.error("Exception occured in getDateByStringAndFormat - ", e);
		}
		return null;
	}

	public String getDateInStringFormatByDate(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			log.error("Exception occured in getDateByStringAndFormat - ", e);
		}
		return "";
	}

	// June 30 2015 1:15pm (yyyy-MM-dd hh:mm:ss)
	public String convert12HoursDateFormat(String dateStr) {
		try {

			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat outputformat = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
			Date date = inputFormat.parse(dateStr);
			return outputformat.format(date);

		} catch (Exception e) {
			Log.error("Exception Occured in convert12HoursDateFormat() method, Exception - ", e);
			return null;
		}
	}

	// bellow method will return a list of seven dates of this current week[Week
	// starts from monday and ends on sunday according the logic]
	public static List<Date> getDatesOfCurrentWeek() {
		try {
			List<Date> listOfSevenDays = new ArrayList<Date>();
			Calendar now = Calendar.getInstance();
			int dayPosition = now.get(Calendar.DAY_OF_WEEK);
			dayPosition--;
			if (dayPosition == 0) {
				dayPosition = 7;
			}
			now.add(Calendar.DATE, -(dayPosition - 1));
			Date firstDate = now.getTime();
			Date nextDate = firstDate;
			for (int i = 1; i <= 7; i++) {
				now.setTime(nextDate);
				listOfSevenDays.add(nextDate);
				now.add(Calendar.DATE, 1);
				nextDate = now.getTime();
			}
			return listOfSevenDays;

		} catch (Exception e) {
			Log.error("Exception occured in getListOfDateOfCurrentWeek() method of DateUtilService class", e);
			return null;
		}
	}

	// bellow method will return a list of seven dates of the week after the
	// current week.
	public static List<Date> getDatesOfWeekAfterCurrentWeek() {
		try {
			List<Date> listOfSevenDays = new ArrayList<Date>();
			Calendar now = Calendar.getInstance();
			int dayPosition = now.get(Calendar.DAY_OF_WEEK);
			dayPosition--;
			if (dayPosition == 0) {
				dayPosition = 7;
			}
			dayPosition = 8 - dayPosition;
			now.add(Calendar.DATE, dayPosition);
			Date firstDate = now.getTime();
			Date nextDate = firstDate;
			for (int i = 1; i <= 7; i++) {
				now.setTime(nextDate);
				listOfSevenDays.add(nextDate);
				now.add(Calendar.DATE, 1);
				nextDate = now.getTime();
			}
			return listOfSevenDays;

		} catch (Exception e) {
			Log.error("Exception occured in getListOfDateOfWeekAfterCurrentWeek() method of DateUtilService class", e);
			return null;
		}
	}

	// bellow method will return a list of days of current month.
	public static List<Date> getDatesOfCurrentMonth() {
		try {
			List<Date> listOfDays = new ArrayList<Date>();
			Calendar now = Calendar.getInstance();
			int dayPosition = now.get(Calendar.DAY_OF_MONTH);
			dayPosition--;
			now.add(Calendar.DATE, -dayPosition);
			Date firstDate = now.getTime();
			Date nextDate = firstDate;
			int currMonth = firstDate.getMonth();
			for (;;) {
				now.setTime(nextDate);
				listOfDays.add(nextDate);
				now.add(Calendar.DATE, 1);
				nextDate = now.getTime();
				int nextMonth = nextDate.getMonth();
				if (currMonth != nextMonth)
					break;
			}
			return listOfDays;

		} catch (Exception e) {
			Log.error("Exception occured in getListOfDateOfWeekAfterCurrentWeek() method of DateUtilService class", e);
			return null;
		}
	}

	// bellow method will return a list of days of next month.
	public static List<Date> getDatesOfNextMonth() {
		try {
			List<Date> listOfDays = new ArrayList<Date>();
			Calendar nowCal = Calendar.getInstance();
			int month = nowCal.get(Calendar.MONTH) + 1;
			int year = nowCal.get(Calendar.YEAR);
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			Date firstDate = new Date(cal.getTimeInMillis());
			Date nextDate = firstDate;
			int currMonth = firstDate.getMonth();
			for (;;) {
				nowCal.setTime(nextDate);
				listOfDays.add(nextDate);
				nowCal.add(Calendar.DATE, 1);
				nextDate = nowCal.getTime();
				int nextMonth = nextDate.getMonth();
				if (currMonth != nextMonth)
					break;
			}
			return listOfDays;

		} catch (Exception e) {
			Log.error("Exception occured in getListOfDateOfWeekAfterCurrentWeek() method of DateUtilService class", e);
			return null;
		}
	}
	
	/*
	 * Santosh
	 */
	public  String getDayMonthAndYearsBetweenTwoDates(Date fromDate,Date toDate){
        String returnDate = "";
        Long year=0l;
        Long month=0l;
        Long day=0l;
        Long remenderValue=0l;
        String noOfYear="";
        String noOfMonth="";
        String noOfDay="";
        long diff = (toDate.getTime())-(fromDate.getTime());
        long numberOfDaysCount =   TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
      if(numberOfDaysCount < 0l){
        returnDate=" All Ready Expired";
      }else{
       //System.out.println(numberOfDaysCount);
        if(numberOfDaysCount > 365L){
          year = numberOfDaysCount /365L;
          remenderValue = numberOfDaysCount %365;
          if(remenderValue > 30L){
             month = remenderValue/30;
             day = remenderValue%30;
           }else {
             day=remenderValue;
           }
          if(year>1){
            noOfYear="Years";  
          }else{
            noOfYear="Year";    
          }
          if(month >1){
            noOfMonth="Months";
          }else{
            noOfMonth="Month";
          }
          if(day > 1 ){
            noOfDay="Days";
          }else{
            noOfDay="Day";
          }
          returnDate = year.toString()+" "+noOfYear+" "+month.toString()+" "+noOfMonth+" "+day.toString()+" "+noOfDay;
        }else if(numberOfDaysCount > 30L){
          month = numberOfDaysCount /30L;
          remenderValue = numberOfDaysCount%30;
          day = remenderValue;
          if(month >1){
            noOfMonth="Months";
          }else{
            noOfMonth="Month";
          }
          if(day > 1 ){
            noOfDay="Days";
          }else{
            noOfDay="Day";
          }
           returnDate =month.toString()+" "+noOfMonth+" "+day.toString()+" "+noOfDay;
            
        }else{
          day = numberOfDaysCount;
          if(day > 1 ){
            noOfDay="Days";
            returnDate =day.toString()+" "+noOfDay;
          }else{
           // noOfDay="Day";
            returnDate ="Today";
          }
          //returnDate =day.toString()+" "+noOfDay;
        }
      }
    return returnDate;
  }
	public static List<String> getDaysBetweenDatesStringFormat(Date startdate, Date enddate)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(enddate);
		c.add(Calendar.DATE, 1); 
		enddate = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> dateStrList = new ArrayList<String>();
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startdate);

	    while (calendar.getTime().before(enddate))   
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    if(dates != null && dates.size() > 0){
	    	for(Date dt : dates){
	    		dateStrList.add(sdf.format(dt));
			}
	    }
	    return dateStrList;
	}
	public String getCurrentDateInStringFormatNew(String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(new Date());

		} catch (Exception e) {
			log.error("Exception Occured in DateUtilService.getCurrentDateInStringFormat() " + " check for log details");
			return null;
		}
	}
	// date before n days
	public static Date getDateBeforeNDays(int noOfDays) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, -noOfDays);
			Date pastDate = now.getTime();
			String dateStr = sdf.format(pastDate);
			
			return sdf.parse(dateStr);

		} catch (Exception e) {
			Log.error("Exception occured in getListOfDateOfWeekAfterCurrentWeek() method of DateUtilService class", e);
			return null;
		}
	}
	//get start date of month.
	public static Date getStartDateOfMonth(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar now = Calendar.getInstance();  
		    now.set(Calendar.DAY_OF_MONTH, 1);
		    Date pastDate = now.getTime();
			String dateStr = sdf.format(pastDate);
			return sdf.parse(dateStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	//get last day of previous month.
	public static Date getLastDayOfPreiviousMonth(){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar now = Calendar.getInstance();  
		    now.set(Calendar.DAY_OF_MONTH, 1);
		    now.add(Calendar.DATE, -1);
		    Date pastDate = now.getTime();
			String dateStr = sdf.format(pastDate);
			return sdf.parse(dateStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String addMultipleTimes(List<String> dateListStr){
	    long hr = 0l;
	    long mm = 0l;
	    long ss = 0l;
	    List<Long> hourList = new ArrayList<Long>();
	    List<Long> minitiesList = new ArrayList<Long>();
	    List<Long> secondsList = new ArrayList<Long>();
	    for(String str : dateListStr){
	      String[] arrayTime=str.split(":");
	        hourList.add(Long.parseLong(arrayTime[0]));
	        minitiesList.add(Long.parseLong(arrayTime[1]));
	        secondsList.add(Long.parseLong(arrayTime[2]));
	    
	    }
	    for(long secVal : secondsList){
		      ss += secVal;
	    }
	    long ss1 = ss;
	    ss = ss % 60;
	    long nextTo = ss1 / 60;
	    minitiesList.add(nextTo);
	    for(long minVal : minitiesList){
		      mm += minVal;
		} 
	    long mm1 = mm;
	    mm = mm % 60;
	    nextTo = mm1 / 60;
	    hourList.add(nextTo);
	    for(long hourVal : hourList){
	      hr += hourVal;
	    }
	    return hr+":"+mm+":"+ss;
	  }
	public static LinkedHashMap<String,List<String>> getTotalWeeksMap(Date startdate, Date enddate){
		try{
			List<String> dayList = getDaysBetweenDatesStringFormat(startdate,enddate);
			Collections.reverse(dayList);
			int length = dayList.size();
			int noOfWeek = length / 7;
			int rem = length % 7;
			if(rem > 0){
				noOfWeek += 1;
			}
			List<String> weekList = new ArrayList<String>();
			for(int i = 1 ; i <= noOfWeek ; i++ ){
				weekList.add("week_"+i);
			}
			LinkedHashMap<String,List<String>> weekAndDaysMap = new LinkedHashMap<String,List<String>>();
			List<String> days = null;
			int j = 0;
			for(String str : weekList){
				days = new ArrayList<String>();
				for(int k=1;k<=7;k++){
					if(dayList.size() == j){
						break;
					}
					days.add(dayList.get(j));
					weekAndDaysMap.put(str, days);
					j++;
				}
			}
			return weekAndDaysMap;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public static LinkedHashMap<String,List<String>> getTotalMonthsMap(Date startdate, Date enddate){
		try{
			List<String> dayList = getDaysBetweenDatesStringFormat(startdate,enddate);
			Collections.reverse(dayList);
			int length = dayList.size();
			int noOfWeek = length / 30;
			int rem = length % 30;
			if(rem > 0){
				noOfWeek += 1;
			}
			List<String> weekList = new ArrayList<String>();
			for(int i = 1 ; i <= noOfWeek ; i++ ){
				weekList.add("month_"+i);
			}
			LinkedHashMap<String,List<String>> weekAndDaysMap = new LinkedHashMap<String,List<String>>();
			List<String> days = null;
			int j = 0;
			for(String str : weekList){
				days = new ArrayList<String>();
				for(int k=1;k<=30;k++){
					if(dayList.size() == j){
						break;
					}
					days.add(dayList.get(j));
					weekAndDaysMap.put(str, days);
					j++;
				}
			}
			return weekAndDaysMap;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Long getMinutesBetweenTwoDates(String fromDate,String toDate){//yyyy-MM-dd hh:mm
		Long diffMinutes = 0l;
		if(fromDate != null && !fromDate.trim().isEmpty() && toDate != null && !toDate.trim().isEmpty()){
			String fromDateArr[] = fromDate.split("-");
			String toDateArr[] = toDate.split("-");
			
			Calendar c1 = Calendar.getInstance();
			c1.set(Integer.parseInt(fromDateArr[0]),Integer.parseInt(fromDateArr[1]),Integer.parseInt(fromDateArr[2].split(" ")[0]),Integer.parseInt(fromDateArr[2].split(" ")[1].split(":")[0]),
					Integer.parseInt(fromDateArr[2].split(" ")[1].split(":")[1]));
			Calendar c2 = Calendar.getInstance();
			c2.set(Integer.parseInt(toDateArr[0]),Integer.parseInt(toDateArr[1]),Integer.parseInt(toDateArr[2].split(" ")[0]),Integer.parseInt(toDateArr[2].split(" ")[1].split(":")[0]),
					Integer.parseInt(toDateArr[2].split(" ")[1].split(":")[1]));
			long milliseconds1 = c1.getTimeInMillis();
			long milliseconds2 = c2.getTimeInMillis();
			long diff = milliseconds2 - milliseconds1;
			diffMinutes = diff / (60 * 1000);
		}
		return diffMinutes;
	}
	
	public static List<String> getMonthsBetweenDatesStringFormat(Date startdate, Date enddate)
	{
		
		Calendar  beginCalendar= Calendar.getInstance();
		beginCalendar.setTime(startdate);
		
		Calendar finishCalendar = Calendar.getInstance();
		finishCalendar.setTime(enddate);
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateStrList = new ArrayList<String>();
        List<Date> dates = new ArrayList<Date>();
        
        while (beginCalendar.before(finishCalendar)) {
            // add one month to date per loop
            Date date = beginCalendar.getTime();
            dates.add(date);
            beginCalendar.add(Calendar.MONTH, 1);
        }
        
        if(dates != null && dates.size() > 0){
	    	for(Date dt : dates){
	    		dateStrList.add(sdf.format(dt));
			}
	    }
	    return dateStrList;
	}
public static void main(String[] args) throws ParseException{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimalDaysInFirstWeek());	
		Date pastDate = calendar.getTime();
		//System.out.println(sdf.format(pastDate));
		calendar.setTime(pastDate);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date endDate = calendar.getTime();
		//System.out.println(sdf.format(endDate));
		
	}

}
