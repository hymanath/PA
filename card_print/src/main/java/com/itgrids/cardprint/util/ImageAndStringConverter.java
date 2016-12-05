package com.itgrids.cardprint.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

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

}
