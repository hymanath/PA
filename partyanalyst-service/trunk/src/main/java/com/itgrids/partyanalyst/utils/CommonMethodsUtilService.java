package com.itgrids.partyanalyst.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.zip.Adler32;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dto.ActivityAttendanceInfoVO;
import com.itgrids.partyanalyst.dto.ActivityVO;


public class CommonMethodsUtilService {
	private static Logger LOG = Logger.getLogger(CommonMethodsUtilService.class);
	
	  public boolean isListOrSetValid(Collection t){
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
			LOG.error("Exception occured at dateTOStringConvertion() in CommonUtilService class .",e);
		}
		return null;
	}
	
	public String stringTODateConvertion(String dateStr,String dateFormatStr,String timeFormatStr)
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
				return format.format(dateStr);
			}
		} catch (Exception e) {
			LOG.error("Exception occured at stringTODateConvertion() in CommonUtilService class .",e);
		}
		return null;
	}
	
	  public String roundTo2DigitsFloatValueAsString(Float number){
		  
		  Log.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  Log.error("Exception raised in roundTo2DigitsFloatValue service method");
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
				LOG.error("Exception raised in uploadCadreImage in CadreRegistrationService service", e);
				return null;
				
			}
		}
		
		public Map<String,ActivityVO> getDatesWiseCounts(Date startDate,Date endDate,String name){
			
			Map<String,ActivityVO> returnMap = new LinkedHashMap<String,ActivityVO>();
			try {
				
				List<String> dates=getBetweenDatesInString(startDate,endDate);
				
				if(name != null && name.trim().length() > 0){
					if(dates != null && dates.size() > 0){
						for (int i = 0; i < dates.size(); i++) {
							ActivityVO vo = new ActivityVO();
							vo.setName(dates.get(i)+" ( "+name+"-"+(i+1)+" ) ");
							returnMap.put(dates.get(i), vo);
						}
					}
				}else{
					if(dates != null && dates.size() > 0){
						for (int i = 0; i < dates.size(); i++) {
							ActivityVO vo = new ActivityVO();
							vo.setName(dates.get(i));
							returnMap.put(dates.get(i), vo);
						}
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised in getDatesWiseCounts in ActivityService service", e);
			}
			return returnMap;
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
		
		
	public Map<String,ActivityAttendanceInfoVO> getDatesWiseCountsForAttendance(Date startDate,Date endDate,String name){
			
			Map<String,ActivityAttendanceInfoVO> returnMap = new LinkedHashMap<String,ActivityAttendanceInfoVO>();
			try {
				
				List<String> dates=getBetweenDatesInString(startDate,endDate);
				
				if(name != null && name.trim().length() > 0){
					if(dates != null && dates.size() > 0){
						for (int i = 0; i < dates.size(); i++) {
							ActivityAttendanceInfoVO vo = new ActivityAttendanceInfoVO();
							vo.setName(dates.get(i)+" ( "+name+"-"+(i+1)+" ) ");
							returnMap.put(dates.get(i), vo);
						}
					}
				}else{
					if(dates != null && dates.size() > 0){
						for (int i = 0; i < dates.size(); i++) {
							ActivityAttendanceInfoVO vo = new ActivityAttendanceInfoVO();
							vo.setName(dates.get(i));
							returnMap.put(dates.get(i), vo);
						}
					}
				}
			} catch (Exception e) {
				LOG.error("Exception raised in getDatesWiseCounts in ActivityService service", e);
			}
			return returnMap;
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
		
		public boolean isNameHaveSpecialChars(String name)
		{
			try{
				List<Integer> ignoreList = Arrays.asList(IConstants.specialCharsUnicodeIgnoreList);
				
				name = name.replace(".","");
				
				for(char c : name.replace(" ","").trim().toCharArray())
				{
					String hex = StringEscapeUtils.escapeJava(new Character(c).toString()).replace("\\u","");
					int value = Integer.parseInt(hex, 16);
					if(ignoreList.contains(value));
						
					else if(!(value >= 3072 && value <= 3199))
						return true;
				}
				return false;
			}catch (Exception e) {
				System.out.println(name);
				e.printStackTrace();
				return true;
			}
		}
}
