package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itgrids.partyanalyst.service.IDateService;
import com.itgrids.partyanalyst.utils.IConstants;

public class DateService implements IDateService {

	
	/**
	 * The below method is used to get present or previous or future days based on the 
	 * Number of days and based on choice
	 * 
	 * Choices has 3 options
	 * 1) present
	 * 2) previous
	 * 3) future
	 * 
	 * @param dateFormat
	 * @param noOfDays
	 * @param choice
	 * @return Date
	 * 
	 * @author Ravi Kiran.Y
	 * @date 29-09-10
	 */
	public Date  getPresentPreviousAndCurrentDayDate(String dateFormat,int noOfDays,String choice) {
		long backDateMS = 0L;
		if(choice.equalsIgnoreCase(IConstants.FUTURE_DAY)){
			backDateMS = System.currentTimeMillis() + ((long)noOfDays) *24*60*60*1000;  
		}else{
			backDateMS = System.currentTimeMillis() - ((long)noOfDays) *24*60*60*1000;
		}
		Date backDate = new Date();  
		backDate.setTime(backDateMS);  		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String strDateNew = sdf.format(backDate) ;
		try {
			backDate = sdf.parse(strDateNew);
		}catch (ParseException e) {
			e.printStackTrace();
		}						
		return backDate;  
	}
	
	
	/**
	 * This method converts the the date which is in string format to the date format.
	 * 
	 * @param date
	 * @return
	 * 
	 * @author Ravi Kiran.Y
	 * @date 30-09-10
	 */
	public Date convertStringToDate(String givenDate,String dateFormat){
		 DateFormat formatter ; 
	     Date date = null; 
	     System.out.println("1");
		 try{
			 System.out.println("1");
	              formatter = new SimpleDateFormat(dateFormat);
	              System.out.println("1");
	              date = (Date)formatter.parse(givenDate);
	              System.out.println("1");
	              System.out.println("Today is " + date);
	              return date;
	    }catch(Exception e){	    	
	    	System.out.println("Exception :"+e);
	    	return date;
	    } 
	    
	}
}
