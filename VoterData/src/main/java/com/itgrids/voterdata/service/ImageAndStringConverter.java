package com.itgrids.voterdata.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.codec.binary.Base64;

public class ImageAndStringConverter {
	
	
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
	
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		try{
		ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
		System.out.println(imageAndStringConverter.convertImageToBase64String("C:\\Users\\Kamalakar\\Dropbox\\photo_2014-12-03_05-31-41.jpg"));
		
		/*File file = new File("H:\\Kamals\\IMG\\IMG.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String imageStr = "";
		String imgStr = "";
		while((imgStr = br.readLine()) != null)
			imageStr = imageStr + imgStr;
		
		imageAndStringConverter.convertBase64StringToImage(imageStr,"H:\\Kamals\\IMG\\IMG2.jpg");*/
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
