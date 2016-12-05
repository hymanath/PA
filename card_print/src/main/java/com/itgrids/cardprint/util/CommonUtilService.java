package com.itgrids.cardprint.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

public class CommonUtilService {
	private static Logger LOG = Logger.getLogger(CommonUtilService.class);
	
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

		public File isAvailableDirectiry(String dir)
		{
			File file = null;
			try {
				file = new File(dir);
			} catch (Exception e) {
				LOG.error("Exception raised in isAvailableDirectiry in CommonUtilService service", e);
			}
			return file;
		}
		public String createDirectoriesForDateFormat(String totalDate,String directoryNameStr){
		  	 try {
		  		 LOG.debug(" in images folder ");
		  		 
		  		String mainDir = IConstants.STATIC_CONTENT_FOLDER+directoryNameStr;
		  		 String mainDirSts = createFolderForArticles(mainDir);
		  		 if(!mainDirSts.equalsIgnoreCase("SUCCESS")){
					 return "FAILED";
				 }
				 
				String DateStr = totalDate;
				 String[] dateArr = DateStr.split("-");
				
				 String yr = dateArr[0]; // YEAR YYYY
				 String yrDir = IConstants.STATIC_CONTENT_FOLDER+directoryNameStr+"\\"+yr;
				 String yrFldrSts = createFolderForArticles(yrDir);
				 if(!yrFldrSts.equalsIgnoreCase("SUCCESS")){
					 return "FAILED";
				 }
				 
				 String mnth = dateArr[1]; //MM 
				 int monthNmbr = Integer.valueOf(mnth);
				 String mnthDir = IConstants.STATIC_CONTENT_FOLDER+directoryNameStr+"\\"+yr+"\\"+IConstants.MONTH_NAMES[monthNmbr-1];
				 String mnthDirSts = createFolderForArticles(mnthDir);
				 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
					 return "FAILED";
				 }
				 
				 String date = dateArr[2]; //DD
				 String ttlDateDir =IConstants.STATIC_CONTENT_FOLDER+directoryNameStr+"\\"+yr+"\\"+IConstants.MONTH_NAMES[monthNmbr-1]+"\\"+date;
				 String ttlDateDirStts = createFolderForArticles(ttlDateDir);
				 if(!ttlDateDirStts.equalsIgnoreCase("SUCCESS")){
					 return "FAILED";
				 }
				 
				 return yr+"/"+IConstants.MONTH_NAMES[monthNmbr-1]+"/"+date;
				 
			} catch (Exception e) {
				LOG.error(totalDate+" Failed to Create");
				return "FAILED";
			}
			 
		}
		
		public String createFolderForArticles(String dir){
		 	try {
				File theDir = isAvailableDirectiry(dir);
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
}
