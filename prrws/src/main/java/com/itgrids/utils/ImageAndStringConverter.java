package com.itgrids.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageAndStringConverter {
	
	private static final Logger LOG = Logger.getLogger(ImageAndStringConverter.class);
	
	public String convertImageToBase64String(String imagePath)
	 {
		 try{
			 File file = new File(imagePath);
			 FileInputStream imageInFile = new FileInputStream(file);
	         byte imageData[] = new byte[(int) file.length()];
	         imageInFile.read(imageData);
	         imageInFile.close();
	         return Base64.encodeBase64URLSafeString(imageData);
		 }catch(Exception e)
		 {
			 LOG.error(e);
			 return null;
		 }
	 }
	
	public boolean convertBase64StringToImage(String imageDataString,String imagePath)
	 {
		 try{
			 byte[] imageByteArray = Base64.decodeBase64(imageDataString);
			 FileOutputStream imageOutFile = new FileOutputStream(imagePath);
			 imageOutFile.write(imageByteArray);
			 imageOutFile.close();
			 return true;
		 }catch(Exception e)
		 {
			 LOG.error(e);
			 return false;
		 }
	 }

	public String convertImageFileToBase64String(File file)
	{
     try{
    	// File file = new File(imagePath);
    	 FileInputStream imageInFile = new FileInputStream(file);
         byte imageData[] = new byte[(int) file.length()];
         imageInFile.read(imageData);
         imageInFile.close();
         return Base64.encodeBase64URLSafeString(imageData);
     }catch(Exception e)
     {
    	 LOG.error(e);
    	 return null;
     }
   }
	public String convertXmlFileToBase64String(File file)
	{
     try{
    	// File file = new File(imagePath);
    	// FileInputStream imageInFile = new FileInputStream(file);
         byte imageData[] = loadFile(file);
   
         byte[] encoded = Base64.encodeBase64(imageData);
         String encodedString = new String(encoded);
         return encodedString;
         
     }catch(Exception e)
     {
    	 LOG.error(e);
    	 return null;
     }
   }
	
	private static byte[] loadFile(File file) throws IOException {
	    InputStream is = new FileInputStream(file);

	    long length = file.length();
	    if (length > Integer.MAX_VALUE) {
	        // File is too large
	    }
	    byte[] bytes = new byte[(int)length];
	    
	    int offset = 0;
	    int numRead = 0;
	    while (offset < bytes.length
	           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	        offset += numRead;
	    }

	    if (offset < bytes.length) {
	        throw new IOException("Could not completely read file "+file.getName());
	    }

	    is.close();
	    return bytes;
	}

	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException 
	{
	    File convFile = new File( multipart.getOriginalFilename());
	    multipart.transferTo(convFile);
	    return convFile;
	}
	
	public File convert(MultipartFile file) throws IllegalStateException, IOException 
	{    
		try {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile(); 
	    FileOutputStream fos;
		fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close(); 
	    
	    return convFile;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
