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
	public static Date convertStringToDate(String givenDate,String dateFormat){
		 DateFormat formatter ; 
	     Date date = null; 
		 try{
	              formatter = new SimpleDateFormat(dateFormat);
	              date = (Date)formatter.parse(givenDate);
	              return date;
	    }catch(Exception e){	    	
	    	System.out.println("Exception :"+e);
	    	return date;
	    } 
	    
	}
	
	/*
	 * To convert timestamp which is in yyyy-MM-dd hh:mm:ss format to dd-MM-yyyy hh:mm:ss format.
	 */
	public static String timeStampConversion(String idate){
		String convertedDated=null;
		SimpleDateFormat sdfInput =  
	        new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss") ;  
	     SimpleDateFormat sdfOutput =  
	        new SimpleDateFormat  ("dd-MM-yyyy hh:mm:ss") ;  		  
	  
	     Date date;
		try {
			date = sdfInput.parse (idate);
			convertedDated = sdfOutput.format(date).toString();
		} catch (ParseException e){
			e.printStackTrace();
		}
		return convertedDated;	
	}
	
	/*
	 * To convert timestamp which is in yyyy-MM-dd hh:mm:ss format to dd-MM-yyyy hh:mm:ss format.
	 */
	public static String timeStampConversionWithoutTime(String idate){
		String convertedDated=null;
		SimpleDateFormat sdfInput =  
	        new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss") ;  
	     SimpleDateFormat sdfOutput =  
	        new SimpleDateFormat  ("dd-MM-yyyy") ;  		  
	  
	     Date date;
		try {
			date = sdfInput.parse (idate);
			convertedDated = sdfOutput.format(date).toString();
		} catch (ParseException e){
			e.printStackTrace();
		}
		return convertedDated;	
	}
	
	/*
	 * To convert timestamp which is in yyyy-MM-dd format to dd-MM-yyyy format.
	 */
	public static String timeStampConversionToDDMMYY(String idate){
		String convertedDated=null;
		SimpleDateFormat sdfInput =  
	        new SimpleDateFormat ("yyyy-MM-dd") ;  
	     SimpleDateFormat sdfOutput =  
	        new SimpleDateFormat  ("dd/MM/yyyy") ;  		  
	  
	     Date date;
		try {
			date = sdfInput.parse (idate);
			convertedDated = sdfOutput.format(date).toString();
		} catch (ParseException e){
			e.printStackTrace();
		}
		return convertedDated;	
	}
	
	/*
	 * To convert timestamp which is in yyyy-MM-dd hh:mm:ss format to dd-MM-yyyy hh:mm:ss format.
	 */
	public String timeStampConversionToYYMMDD(String idate){
		String convertedDated=null;
		SimpleDateFormat sdfInput =  
	        new SimpleDateFormat ("yyyy/MM/dd");  
	     SimpleDateFormat sdfOutput =  
	        new SimpleDateFormat  ("dd/MM/yyyy");  		  
	  
	     Date date;
		try {
			date = sdfOutput.parse (idate);
			convertedDated = sdfInput.format(date).toString();
		} catch (ParseException e){
			e.printStackTrace();
		}
		return convertedDated;	
	}
}
