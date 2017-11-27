package com.itgrids.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.Adler32;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Service
public class CommonMethodsUtilService {
	private static Logger LOG = Logger.getLogger(CommonMethodsUtilService.class);
	
	  public static boolean isListOrSetValid(Collection t){
		  return t != null && t.size()>0;
		}
	  
	  public boolean isMapValid(Map<?,?> t){
		  return t != null && t.size()>0;
		}
	  public boolean isTextEmpty(String value){
		  return value != null && value.trim().length()>0;
		}
	  
	  
	public String getStringValueForObject(Object param)
	{
		try {
			if(param != null && !param.toString().isEmpty())
			{
				return param.toString().trim();
			}
			else
			{
				return "";
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getStringValueForObject() in CommonUtilService class .",e);
		}
		return null;
	}
	
	public Long getLongValueForObject(Object param)
	{
		try {
			if(param != null && !param.toString().isEmpty())
			{
				return Long.valueOf(param.toString().trim().replace(",", ""));
			}
			else
			{
				return 0L;
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getStringValueForObject() in CommonUtilService class .",e);
		}
		return null;
	}
	public Double getDoubleValueForObject(Object param)
	{
		try {
			if(param != null && !param.toString().isEmpty())
			{
				return Double.valueOf(param.toString().trim());
			}
			else
			{
				return 0.0D;
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getStringValueForObject() in CommonUtilService class .",e);
		}
		return null;
	}
	public Long getLongValueForString(String param)
	{
		try {
			if(param != null && !param.toString().isEmpty())
			{
				return Long.valueOf(param.toString().trim());
			}
			else
			{
				return 0L;
			}
		} catch (Exception e) {
			LOG.error("Exception occured at getStringValueForObject() in CommonUtilService class .",e);
		}
		return null;
	}
	
	public Long getIntegerToLong(int id)
	{
		try {
			return getStringToLong(String.valueOf(id));
		} catch (Exception e) {
			LOG.error("Exception occured at getStringValueForObject() in CommonUtilService class .",e);
		}
		return null;
	}
	
	public Long getStringToLong(String value)
	{
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
			LOG.error("Exception occured at getStringValueForObject() in CommonUtilService class .",e);
		}
		return null;
	}
	
	public String dateTOStringConvertion(Date date,String dateFormatStr,String timeFormatStr)
	{
		SimpleDateFormat format = null;
		try {
			if(date != null)
			{
				if(dateFormatStr != null  && !dateFormatStr.trim().isEmpty())
				{
					format = new SimpleDateFormat(dateFormatStr);
				}
				if(timeFormatStr != null && !timeFormatStr.trim().isEmpty())
				{
					format = new SimpleDateFormat(dateFormatStr+" "+timeFormatStr);
				}
				return format.format(date);
			}
		} catch (Exception e) {
			LOG.error("Exception occured at dateTOStringConvertion() in CommonMethodsUtilService class .",e);
		}
		return null;
	}
	
	public Date stringTODateConvertion(String dateStr,String dateFormatStr,String timeFormatStr)
	{
		SimpleDateFormat format = null;
		try {
			if(dateStr != null && !dateStr.trim().isEmpty())
			{
				if(dateFormatStr != null  && !dateFormatStr.trim().isEmpty())
				{
					format = new SimpleDateFormat(dateFormatStr);
				}
				if(timeFormatStr != null && !timeFormatStr.trim().isEmpty())
				{
					format = new SimpleDateFormat(dateFormatStr+" "+timeFormatStr);
				}
				return format.parse(dateStr);
			}
		} catch (Exception e) {
			LOG.error("Exception occured at stringTODateConvertion() in CommonUtilService class .",e);
		}
		return null;
	}
	
	  public String roundTo2DigitsFloatValueAsString(Float number){
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  return result;
	  }
	  
	  public boolean isNumber(String value)
	  {
		  boolean isNumber =false;
		  if(value != null && !value.isEmpty())
		  {
			  try {
				  Long longValue = Long.valueOf(value);
				  isNumber= true;
			} catch (NumberFormatException e) {
				isNumber = false;
			}
			 
		  }
		  return isNumber;
	  }
	  
	  public String checkStringForNull(String value)
		{
			try
			{
				return value != null && !value.isEmpty()?value.trim():null;
				
			}catch(Exception e){
				return null;
			}
		}
		public Long checkLongForNull(Long value)
		{
			try
			{
				return value != null && value.longValue()>0L?value:null;
			}catch(Exception e)
			{
				return null;
			}
		}
		

		public String copyFile(String sourcePath,String destinationPath){
			//synchronized ("copyFile"){
		 try{
			File file = new File(sourcePath);
			if(file.exists()){
				FileUtils.copyFile(file,  new File(destinationPath));
				LOG.error("Copy success");
				return "success";
			}
		  }catch(Exception e){
			  LOG.error("Exception raised in copyFile ", e);
			  LOG.error("Copy error");
			  return "error";
		  }
		// }
		 return "failure";
		}
		
		public boolean checkFileExistingOrNot(String path){
			File f = new File(path);
			if(f.exists()) { 
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * 
		 * @param uniqueKey
		 * @param uploadImageContentType
		 * @param uploadImage
		 * @param url
         * @param fileSepatator
		 * @return
		 */
		public String uploadImage(String  uniqueKey ,String uploadImageContentType,File uploadImage,String folderurl,String fileSepatator)
		{
			try{
				
				 if(uploadImageContentType != null && uploadImage != null && uniqueKey != null){
					 String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
					  String destinationPath = folderurl + pathSeperator + uniqueKey+".jpg";
					  String sourcePath =   folderurl + pathSeperator + uniqueKey;
					  String status = copyFile(sourcePath,destinationPath);
					  
					
					BufferedImage image = ImageIO.read(uploadImage);
					
					if(image == null)
						return null;
					LOG.info("Image is Read");
					String contentTypeArr[] = uploadImageContentType.split("/"); // ex : application/jpeg
					String fileName = sourcePath+uniqueKey.toString()+"."+contentTypeArr[1];
					LOG.info("file name -- "+fileName);
					//String imageName =  cadreId.toString()+"."+constiName[1];
					
					FileImageOutputStream filName = new FileImageOutputStream(new File(fileName));
					
					ImageIO.write(image, contentTypeArr[1],filName);
					LOG.info("file uploaded");
		            filName.close();
				 }
	            return "success";
			}
			catch(Exception e)
			{
				LOG.error("Exception raised in uploadCadreImage in CommonMethodsUtilService service", e);
				return null;
				
			}
		}
		
		
		public List<String> getBetweenDatesInString(Date fromDate,Date toDate){
			
			List<String> dateStr = new ArrayList<String>(0);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(fromDate);
			cal.add(Calendar.DATE, -1);
			
			while (cal.getTime().before(toDate)) {
			    cal.add(Calendar.DATE, 1);
			    dateStr.add(sdf.format(cal.getTime()));
			}
			return dateStr;
		}
		
		public List<String> getBetweenWeeks(Date fromDate,Date toDate,String formatStr){
			List<String> datesBetween = new ArrayList<String>();
			try {
				SimpleDateFormat format = new SimpleDateFormat(formatStr);
	        	Calendar startDateCal = Calendar.getInstance() ;
	    		startDateCal.setTime(fromDate);
	    			
	    		Calendar toDateCal =Calendar.getInstance() ;
	    		toDateCal.setTime(toDate);
	    		if(DateUtils.isSameDay(startDateCal, toDateCal)){
    				datesBetween.add(format.format(startDateCal.getTime())+" to "+format.format(toDateCal.getTime()));
	    		}else{
	    			int i=0;
	    			while(startDateCal.getTime().before(toDateCal.getTime())){
	    				String dateStr = format.format(startDateCal.getTime());
	    				if(i>0){
	    					startDateCal.add(Calendar.DATE, 1);
	    					dateStr = format.format(startDateCal.getTime());
	    				}
	    				startDateCal.add(Calendar.DATE, 6);
	    				if(startDateCal.getTime().before(toDateCal.getTime()))
	    					dateStr = dateStr+" to "+format.format(startDateCal.getTime());
	    				else
	    					dateStr = dateStr+" to "+format.format(toDateCal.getTime());
	    				
	    				datesBetween.add(dateStr);
	    				//System.out.println(dateStr);
	    				i=i+1;
	    			}
	    		}
			} catch (Exception e) {
				LOG.error("Exception raised in getBetweenWeeks in CommonMethodsUtilService service", e);
			}
			return datesBetween;
		}
		public List<String> getAvailableDates(Set<String> datesList, String startDateStr,String endDateStr){
			
			List<String> availableList = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date startDate = sdf.parse(startDateStr.substring(0, 10));
				Date endDate = sdf.parse(endDateStr.substring(0, 10));
				if(datesList != null && datesList.size() > 0){
					for (String dateString : datesList) {
						Date testDate = format.parse(dateString);
						if(startDate != null && endDate != null){
							if(testDate.equals(startDate) || (testDate.after(startDate) && testDate.before(endDate)) || testDate.equals(endDate)){
								String dateStr = sdf.format(testDate);
								availableList.add(dateStr);
							}
						}
					}
				}
				
			} catch (Exception e) {
				LOG.error("Exception raised in getAvailableDates in ActivityService service", e);
			}
			
			return availableList;
		}
		
		public String  getUniCodeMessage(String message){
	        String returnMessage = "";
			for(int i=0;i<message.length();i++){
				String character = Integer.toHexString(message.charAt(i));
				if(character.length() == 1){
					returnMessage=returnMessage+"000"+character;
				}else if(character.length() == 2){
					returnMessage=returnMessage+"00"+character;
				}else if(character.length() == 3){
					returnMessage=returnMessage+"0"+character;
				}else{
					returnMessage=returnMessage+""+character;
				}
				
			}
		return returnMessage; 
	}
	
		  public String escapeUnicode(String input) {
				StringBuilder b = new StringBuilder(input.length());
				Formatter f = new Formatter(b);
				for (char c : input.toCharArray()) {
					if (c < 128) {
					  b.append(c);
					} else {
					  f.format("\\u%04x", (int) c);
					}
				}
				return b.toString();
			}
		  
		  public boolean isActiveForSomeSeconds(Date lastData,String unit,long interval){
			  try{
				  if(unit.equalsIgnoreCase("second")){
					  long totalTime = 1000 * interval;
					  long currMiliSec = java.lang.System.currentTimeMillis();
					  long lastMiliSec = lastData.getTime();
					  long diff =currMiliSec - lastMiliSec;
					  if(diff>=totalTime){
						  return false;
					  }else{
						  return true;
					  }
				  }
				
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return true;
		  }
		  public boolean isActiveForSomeMinutes(Date lastData,String unit,long interval){
			  try{
				  if(unit.equalsIgnoreCase("minute")){
					  long totalTime = 60000 * interval;
					  long currMiliSec = java.lang.System.currentTimeMillis();
					  long lastMiliSec = lastData.getTime();
					  long diff =currMiliSec - lastMiliSec;
					  if(diff>=totalTime){
						  return false;
					  }else{
						  return true;
					  }
				  }
				
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return true;
		  }
		  public boolean isActiveForSomeHours(Date lastData,String unit,long interval){
			  try{
				  if(unit.equalsIgnoreCase("hour")){
					  long totalTime = 3600000 * interval;
					  long currMiliSec = java.lang.System.currentTimeMillis();
					  long lastMiliSec = lastData.getTime();
					  long diff =currMiliSec - lastMiliSec;
					  if(diff>=totalTime){
						  return false;
					  }else{
						  return true;
					  }
				  }
				
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return true;
		  }
		  public boolean isActiveForSomeWeeks(Date lastData,String unit,long interval){
			  try{
				  if(unit.equalsIgnoreCase("week")){
					  long totalTime = 604800000 * interval;
					  long currMiliSec = java.lang.System.currentTimeMillis();
					  long lastMiliSec = lastData.getTime();
					  long diff =currMiliSec - lastMiliSec;
					  if(diff>=totalTime){
						  return false;
					  }else{
						  return true;
					  }
				  }
				
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return true;
		  }
		  public boolean isActiveForSomeMonths(Date lastData,String unit,long interval){
			  try{
				  if(unit.equalsIgnoreCase("month")){
					  long totalTime = 2628000000l * interval;
					  long currMiliSec = java.lang.System.currentTimeMillis();
					  long lastMiliSec = lastData.getTime();
					  long diff =currMiliSec - lastMiliSec;
					  if(diff>=totalTime){
						  return false;
					  }else{
						  return true;
					  }
				  }
				
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return true;
		  }
		  public boolean isActiveForSomeYears(Date lastData,String unit,long interval){
			  try{
				  if(unit.equalsIgnoreCase("year")){
					  long totalTime = 31540000000l * interval;
					  long currMiliSec = java.lang.System.currentTimeMillis();
					  long lastMiliSec = lastData.getTime();
					  long diff =currMiliSec - lastMiliSec;
					  if(diff>=totalTime){
						  return false;
					  }else{
						  return true;
					  }
				  }
				
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  return true;
		  }
		 
		  public String getChecksumDetails(String OrderId,String  AmountStr,String  redirectUrl){
		        String WorkingKey ="0kag9s53yyi788y3prdk8ydhf8glfj9e";
		        String MerchantId ="M_tdpcbn_2144";
		        String Amount = "1";
		        String str = MerchantId + "|" + OrderId + "|" + Amount + "|" + redirectUrl + "|" + WorkingKey;
		        Adler32  adl = new Adler32();
		        adl.update(str.getBytes());
		        return (Long.valueOf(adl.getValue()).toString());
		      }
		  
		  public <T> T setValueToModel(Object destinationModel, Object sourceVO){
			    try{
			    Field[] fields = sourceVO.getClass().getDeclaredFields();
			    for(Field sourceField: fields){
			      sourceField.setAccessible(true);
			      Object value = sourceField.get(sourceVO);
			      Field destinationField = destinationModel.getClass().getDeclaredField(sourceField.getName());
			      destinationField.setAccessible(true);
			      destinationField.set(destinationModel,value);
			    }
			    }catch(Exception e){
			      e.printStackTrace();
			    }
			    return (T) destinationModel;
			  }
		  
		  public List<Date> getBetweenDates(Date fromDate,Date toDate){
				
				List<Date> dates = new ArrayList<Date>(0);
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(fromDate);
				cal.add(Calendar.DATE, -1);
				if(cal.getTime() != null && toDate != null)
					while (cal.getTime().before(toDate)) {
					    cal.add(Calendar.DATE, 1);
					    dates.add(cal.getTime());
					}
				return dates;
			}
		    
		  public static String createFolder(String dir){
			 	try {
					File theDir = new File(dir);
					  // if the directory does not exist, create it
					  if (!theDir.exists()) {
					    boolean result = false;
					    try{
					        theDir.mkdir();
					        result = true;
					     } catch(SecurityException se){
					        //handle it
					     }        
					     if(result) {    
					      LOG.debug("DIR With Name "+dir+" created");  
					     }
					  }else{
						  LOG.debug("DIR With Name "+dir+" EXISTS");
					  }
					  return "SUCCESS";
				} catch (Exception e) {
					LOG.error(dir+" Failed to Create");
					return "FAILED";
				}
			}
			
		  
		  public  String createInnerFolders(String addFolderStr){
			 	try {
			  		 LOG.debug(" in createInnerFolders ");
			  		
			  		Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					 int year = calendar.get(Calendar.YEAR);
					 int month = calendar.get(Calendar.MONTH);
					 int day = calendar.get(Calendar.DAY_OF_MONTH);
					
					 String dirStr = createFolder(addFolderStr);
					 if(!dirStr.equalsIgnoreCase("SUCCESS")){
						 return "FAILED";
					 }
					 
					 String yr = String.valueOf(year); // YEAR YYYY
					 String yrDir = addFolderStr+"/"+yr;
					 
					 String yrFldrSts = createFolder(yrDir);
					 if(!yrFldrSts.equalsIgnoreCase("SUCCESS")){
						 return "FAILED";
					 }
					 
					 StringBuilder str = new StringBuilder();
					 int temp = month+1;
					 str.append(temp).append("-").append(day);
					 
					 
					 String mnth = str.toString();
					 String mnthDir = addFolderStr+"/"+yr+"/"+mnth;
					 String mnthDirSts = createFolder(mnthDir);
					 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
						 return "FAILED";
					 }
					 
					 return "/"+yr+"/"+mnth;
					 
				
				} catch (Exception e) {
					LOG.error(addFolderStr+" Failed to Create");
					return "FAILED";
				}
			}
		  
		  public boolean fileCopy(String sourcePath,String destinationPath)
		  {
			   try{
				   File file = new File(sourcePath);
				   
				   if(file.exists())
				   {
					   FileUtils.copyFile(file,  new File(destinationPath));
					   return true;
				   }
				   else
					   return false;
			    }catch(Exception e){
			      LOG.error("Exception raised in copyFile in CommonMethodsUtilService", e);
			      return false;
			    }
		}  
		  
		public String percentageMergeintoTwoDecimalPlaces(Double perc){
			try {
				 return new BigDecimal(perc).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			} catch (Exception e) {
				LOG.error("Exception raised in percentageMergeintoTwoDecimalPlaces in CommonMethodsUtilService ", e);
			}
			return perc.toString();
		}
		
		public void readAllFileINaDirectory(String filePath,String content1, String content2){
			try {
		        
				File folder = new File(filePath);
		         Set<String> membershipNoStrList = new HashSet<String>(0);
		         Scanner scanner =  null;
		         Long totalCount =0L;
		         for (final File file : folder.listFiles()) {
		             if (file.isDirectory()) {
		            	  System.out.println(file.getName());
		             } else {
		            	 scanner = new Scanner(file);
		            	 boolean isStarted=false;
		    	         while (scanner.hasNextLine()) {
		    	        	 String str = scanner.nextLine();
		    	        	 if(str.contains(content1)){	        	//content1 = "ENUE Exception occuredin  generating payment gateway basic details  with"	 
		    	        		 isStarted=true;
		    	        	 }else if (isStarted && str.contains(content2)){	     // content2 = "AuthDesc: Y"   		 
		    	        		 membershipNoStrList.add(str.substring(42, 50));
		    	        		 isStarted=false;
		    	        		 totalCount=totalCount+1;
		    	        		 System.out.println("Membership No : "+str.substring(42, 50)+" -----> TOTAL COUNT :"+totalCount);
		    	        	 }
		    	         }
		             
		             }
		         }
		        
		         System.out.println("Unique Memberships Count : "+membershipNoStrList.size());
		         System.out.println(membershipNoStrList);
		         scanner.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public void readFileByFilePath(String filePath,String content1, String content2){
			try {
		         File file = new File("D:\\static_content\\PAErrors.log.1");
		         Long totalCount = 0L;
		         Scanner scanner = new Scanner(file);
		         List<String> membershipNoStrList = new ArrayList<String>(0);
		         boolean isStarted=false;
		         while (scanner.hasNextLine()) {
    	        	 String str = scanner.nextLine();
    	        	 if(str.contains(content1)){	        	//content1 = "ENUE Exception occuredin  generating payment gateway basic details  with"	 
    	        		 isStarted=true;
    	        	 }else if (isStarted && str.contains(content2)){	     // content2 = "AuthDesc: Y"   		 
    	        		 membershipNoStrList.add(str.substring(42, 50));
    	        		 isStarted=false;
    	        		 totalCount=totalCount+1;
    	        		 System.out.println("TOTAL COUNT :"+totalCount);
    	        	 }
    	         }
		         scanner.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		public Double calculatePercantage(Long subCount,Long totalCount){
		    Double d=0.0d;
		    if(subCount.longValue()>0l && totalCount.longValue()==0l)
		      LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount+" .So, Unable to calculate percentage.");

		    if(totalCount.longValue() > 0l){
		       d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();   
		    }
		    return d;
		  }
		
		/**
		 * @author  Srishailam Pittala 
		 * @Date 8th June,2017
		 * @description if List<Long> contains zero value , we need to clear all data
		 * @param List<Long>
		 * @return List<Long> if contains zero empty list otherwise same input list we are returning.
		 */
		public List<Long> makeEmptyListByZeroValue(List<Long> list){
			List<Long> returnList = null;
			try {
				if(isListOrSetValid(list)){
					if(list.contains(0L))
						returnList =  new ArrayList<Long>(0);
				}
				if(returnList != null)
					return returnList;
				else
					return list;

			} catch (Exception e) {
				LOG.error(" Exception Occured in makeEmptyListByZeroValue() , CommonMethodsUtilService class.");
			}
			return null;
		}
		/**
		 * @author  Swadhin Lenka 
		 * @Date 9th June,2017
		 * @description we need to keep two decimal place
		 * @param List<Long>
		 * @return if argument is not null java.lang.Double else 0.0
		 */
		public Double roundUptoTwoDecimalPoint(Double value){
			try{
				 DecimalFormat df = new DecimalFormat("##.00");
				if(value != null && value.doubleValue() > 0){
					return Double.parseDouble(df.format(value));
				}else{
					return 0.0d;
				}
			}catch(Exception e){
				LOG.error(" Exception Occured in roundUptoTwoDecimalPoint() , CommonMethodsUtilService class.");
			}
			return null;
		}
		/**
		 * @author  Swadhin Lenka 
		 * @Date 9th June,2017
		 * @description we need to keep three decimal place
		 * @param List<Long>
		 * @return if argument is not null java.lang.Double else 0.0
		 */
		public static Double roundUptoThreeDecimalPoint(Double value){
			try{
				 DecimalFormat df = new DecimalFormat("##.000");
				if(value != null && value.doubleValue() > 0){
					return Double.parseDouble(df.format(value));
				}else{
					return 0.0d;
				}
			}catch(Exception e){
				LOG.error(" Exception Occured in roundUptoTwoDecimalPoint() , CommonMethodsUtilService class.");
			}
			return null;
		}
		/*public  String calculateAmountInWords(Long number){
		      String amountStr = number.toString();
		      int lenght = amountStr.trim().length();
		      int maxLength=0;
		      String tempAmount = amountStr;
		      String decAmount = "";
		      if(lenght>5){
		    	 tempAmount = amountStr.substring(0, amountStr.length()-5);
		    	 //decimal point is required
		    	 int len = amountStr.length()-5;
		    	 decAmount = amountStr.substring(len,len+2);
		      }else{
		    	  return "0."+String.valueOf(number/10000);
		      }
		      amountStr = tempAmount;
		      lenght = tempAmount.trim().length();
		      if(amountStr.length()>3){
		    	  String temp="";
		    	  String temp1="";
		    	  for (int i = 0; i < amountStr.length(); i++) {
		    		  if(i==2){
		    			  temp = amountStr.substring(lenght-(i+1),lenght);
		    			  temp1=temp;
		    		  }else if(i>2 && i%2==0 ){
		    			  maxLength=lenght-(i+1)+2;
		    			  temp = amountStr.substring(lenght-(i+1),maxLength)+","+temp;
		    			  temp1 = temp1+amountStr.substring(lenght-(i+1),maxLength);
		    		  }	else if(temp.length()>0 && temp1.length()>0 && lenght-temp1.length()==1){
		    			  temp = amountStr.substring(0,1)+","+temp;
		    		  }	    		  
				}
		    	  amountStr = temp;
		      }
		      return amountStr+"."+decAmount;  
		    }*/
	
		 /**
			 * @author  raghu 
			 * @Date 14th June,2017
			 * @description calculate amount in words
			 * @param Long number
			 * @return String if contains (lenght==6) we will seprate by comma
			 */
			public  String seperateNumberByComma(Long number) {
				try{
				String amountStr = number.toString();
				int lenght = amountStr.trim().length();
				String amountStr2;
				String amountStr1;
				if(lenght == 6){
					amountStr1=amountStr.substring(lenght-5,lenght);
					amountStr2=amountStr.substring(0, 1);
					 amountStr =amountStr2+","+amountStr1 ;
				}
				if(lenght >=7){
					amountStr1=amountStr.substring(lenght-5,lenght);
					amountStr2=amountStr.substring(0,lenght-5 );
					 Long d =Long.parseLong(amountStr2);
					String amountStr3=new DecimalFormat("##,##").format(d);
					 amountStr =amountStr3+","+amountStr1 ;
					
				}
				   return amountStr;
				   
				 }catch(Exception e){
					LOG.error(" Exception Occured in calculateAmountInWords() , CommonMethodsUtilService class.");
				}
				return null;
			}
			/*public static List<String> buildIntervals(Double amount,int intervalsCount){
				List<String> returnList = new ArrayList<String>();
				try {
					List<String> intervalList = new ArrayList<String>();
					if(intervalsCount>0){
						Double value= amount/intervalsCount;
						Double tempValue=0.00d;
						for (int i = 0; i < intervalsCount; i++) {
							if(i==0){
								intervalList.add(i+"-"+value);
								tempValue =tempValue+value;
							}else{
								intervalList.add(tempValue+1+"-"+(tempValue+value));
								tempValue =tempValue+value;
							}
						}
					}					
					String lhs = "";
					String rhs = "";
					Long lastVal = 0L;
					if(isListOrSetValid(intervalList)){
						for (String interval : intervalList) {
							String[] rangeArr = interval.split("-");
							if(rangeArr != null && rangeArr.length>0){
								if(rangeArr[0].length() > 1){
									lhs = rangeArr[0].substring(2);
								}
								rhs = rangeArr[1].substring(2);
								if(lhs != null && lhs.trim().length() > 0){
									lastVal = (Double.valueOf(rangeArr[1]).longValue())-(Double.valueOf(rhs).longValue());
									returnList.add(((Double.valueOf(rangeArr[0]).longValue())-(Double.valueOf(lhs).longValue()))+" - "+((Double.valueOf(rangeArr[1]).longValue())-(Double.valueOf(rhs).longValue())));
								}else{
									returnList.add((Double.valueOf(rangeArr[0]).longValue())+" - "+((Double.valueOf(rangeArr[1]).longValue())-(Double.valueOf(rhs).longValue())));
								}
							}
						}
					}
					returnList.add(0,"0-0");
					returnList.add(9,lastVal.toString().trim());
				} catch (Exception e) {
					LOG.error(" Exception Occured in buildIntervals() , CommonMethodsUtilService class.");
				}
				return returnList;
			}*/
			public static List<String> buildIntervals(Double amount,int intervalsCount){
				List<String> returnList = new ArrayList<String>();
				try {
					List<String> intervalList = new ArrayList<String>();
					if(intervalsCount>0){
						Double value= amount/intervalsCount;
						Double tempValue=0.00d;
						for (int i = 0; i < intervalsCount; i++) {
							if(i==0){
								intervalList.add(i+"-"+value);
								tempValue =tempValue+value;
							}else{
								intervalList.add(tempValue+"-"+(tempValue+value));
								tempValue =tempValue+value;
							}
						}
					}					

					if(isListOrSetValid(intervalList)){
						for (String interval : intervalList) {
							String[] rangeArr = interval.split("-");
							if(rangeArr != null && rangeArr.length>0){
								returnList.add(roundUptoThreeDecimalPoint(Double.valueOf(rangeArr[0]))+" - "+roundUptoThreeDecimalPoint(Double.valueOf(rangeArr[1])));
							}
						}
					}
					returnList.add(0,"0-0");
				} catch (Exception e) {
					LOG.error(" Exception Occured in buildIntervals() , CommonMethodsUtilService class.");
				}
				return returnList;
			}
			public  static String calculatetempAmountInWords(Long number){
			      String amountStr = number.toString();
			      int lenght = amountStr.trim().length();
			      int maxLength=0;
			      String tempAmount = amountStr;
			      if(lenght>5){
			    	 //tempAmount = amountStr.substring(0, amountStr.length()-5);
			      }
			      amountStr = tempAmount;
			      lenght = tempAmount.trim().length();
			      if(amountStr.length()>3){
			    	  String temp="";
			    	  String temp1="";
			    	  for (int i = 0; i < amountStr.length(); i++) {
			    		  if(i==2){
			    			  temp = amountStr.substring(lenght-(i+1),lenght);
			    			  temp1=temp;
			    		  }else if(i>2 && i%2==0 ){
			    			  maxLength=lenght-(i+1)+2;
			    			  temp = amountStr.substring(lenght-(i+1),maxLength)+","+temp;
			    			  temp1 = temp1+amountStr.substring(lenght-(i+1),maxLength);
			    		  }	else if(temp.length()>0 && temp1.length()>0 && lenght-temp1.length()==1){
			    			  temp = amountStr.substring(0,1)+","+temp;
			    		  }	    		  
					}
			    	  amountStr = temp;
			      }
			      return amountStr;  
			    }
			
			
		/*public static void main(String args[]){
			List<String> intervals = buildIntervals(163014.00d,8);
		}*/
			
		/**
			 * @author  Babu kurakula <href:kondababu.kurakula@itgrids.com >
			 * @Date 15th June,2017
			 * @description to convert String  in Title Case
			 * @param String inputStr
			 * @return String in Title case formt
		 */
			
		public String toConvertStringToTitleCase(String inputStr) {
				StringBuilder camelCase = new StringBuilder();
		         boolean nextCamelCase = true;
				  String inputLowerStr=inputStr.toLowerCase();
				    for (char c : inputLowerStr.toCharArray()) {
				        if (Character.isSpaceChar(c)) {
				        	nextCamelCase = true;
				        } else if (nextCamelCase) {
				            c = Character.toTitleCase(c);
				            nextCamelCase = false;
				        }
				        camelCase.append(c);
				    }
				    return camelCase.toString();
		}
		
		public WebResource getWebResourceObject(String url){
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	        Client client = Client.create(clientConfig);
	         
	        WebResource webResource = client.resource(url);
	        
	        return webResource;
		}
		public  String calculateAmountInWords(Long number){
			NumberFormat formatter = new DecimalFormat("###.#####");
			
			String amountStr = number.toString();
		      int lenght = amountStr.trim().length();
		      int maxLength=0;
		      String tempAmount = amountStr;
		      String decAmount = "";
		      if(lenght>7){
		    	 tempAmount = amountStr.substring(0, amountStr.length()-7);
		    	 //decimal point is required
		    	 int len = amountStr.length()-7;
		    	 decAmount = amountStr.substring(len,len+3);
		      }else{
		    	  //System.out.println("else:"+String.valueOf(number/10000000.0));
		    	  String f = formatter.format(number/10000000.0);
		    	  //System.out.print(f);
		    	  return f;
		      }
		      amountStr = tempAmount;
		      lenght = tempAmount.trim().length();
		      if(amountStr.length()>3){
		    	  String temp="";
		    	  String temp1="";
		    	  for (int i = 0; i < amountStr.length(); i++) {
		    		  if(i==2){
		    			  temp = amountStr.substring(lenght-(i+1),lenght);
		    			  temp1=temp;
		    		  }else if(i>2 && i%2==0 ){
		    			  maxLength=lenght-(i+1)+2;
		    			  temp = amountStr.substring(lenght-(i+1),maxLength)+","+temp;
		    			  temp1 = temp1+amountStr.substring(lenght-(i+1),maxLength);
		    		  }	else if(temp.length()>0 && temp1.length()>0 && lenght-temp1.length()==1){
		    			  temp = amountStr.substring(0,1)+","+temp;
		    		  }	    		  
				}
		    	  amountStr = temp;
		      }
		      //System.out.println(amountStr+"."+decAmount);
		      return amountStr+"."+decAmount;  
		     
		    }
		public Session getNewSessionObject(String host)
		{
			try{
				Session session = null;
				Properties props = null;
				
				if(host.equalsIgnoreCase(IConstants.SERVER))
				{
			        props = new Properties();
			 
			        props.put("mail.smtp.host", IConstants.HOST);
			        props.put("mail.smtp.port", IConstants.PORT);
			        props.put("mail.smtp.user", IConstants.EMAIL_USERNAME);
			        props.put("mail.smtp.socketFactory.port", IConstants.PORT);
			        props.put("mail.smtp.auth", "true");
			        props.put("mail.smtp.starttls.enable", "true");
			        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			        props.put("mail.smtp.socketFactory.fallback", "true");
			 
			        try {
			            	session = Session.getInstance(props, new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(IConstants.EMAIL_USERNAME,IConstants.EMAIL_PASSWORD);
							}
						});
			            
			            session.setDebug(true);
			        }catch (Exception e) {
			        	return null;
					}
			            
				}
				else if(host.equalsIgnoreCase(IConstants.SERVER))
				{
					props = System.getProperties();
					session = Session.getDefaultInstance(props);
				}
				else
					LOG.warn("Please specify the host to send the Emails");
				
				return session;
			}catch (Exception e) {
				LOG.error("Error During Creating MimeMessage Object - Please Check Once, Exception is - "+e);
				return null;
			}
		}
		
		public String getDateInStringFrormat(String dateInMilisecondeFormat) {
			 try {
				 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:MM:YY");
				 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:MM:YY");
				 sdf.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
		         if (dateInMilisecondeFormat != null && dateInMilisecondeFormat.length() > 0 ) {
		             Date date = new Date(Long.valueOf(dateInMilisecondeFormat));
			         String formatted = sdf.format(date);
			         Date dateFormat = sdf.parse(formatted);
			         String returnDate = sdf1.format(dateFormat);
			         return returnDate;     	 
		         }
		     } catch (Exception e) {
				 LOG.error("Exception Occured in getDateInStringFrormat() method, Exception - ",e);
			 }
			return null;
		}
		public String getAuthenticationString(String name,String password){
			try {			
		        String authString = name + ":" + password;
		        return new BASE64Encoder().encode(authString.getBytes());
				
			} catch (Exception e) {
				LOG.error("Exception raised at getAuthenticationString - RWSNICService service", e);
			}
			return null;
		}
}
