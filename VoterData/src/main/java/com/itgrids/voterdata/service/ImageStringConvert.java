package com.itgrids.voterdata.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;

public class ImageStringConvert {

	 public static void main(String[] args) {
		 ImageStringConvert imageStringConvert = new ImageStringConvert();
		 String imgStr = imageStringConvert.convertImageToBase64String("C:\\Users\\Kamalakar\\Desktop\\Temp\\kamal.jpg");
		 System.out.println(imgStr);
		 System.out.println(imgStr.length());
		 imageStringConvert.convertBase64StringToImage(imgStr, "C:\\Users\\Kamalakar\\Desktop\\Temp\\kamal2.jpg");
	 }
	 
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
			 e.printStackTrace();
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
			 e.printStackTrace();
			 return false;
		 }
		 
	 }
}
