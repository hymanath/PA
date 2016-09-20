package com.itgrids.voterdata.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;

public class ImageDecript {

	public InputStream getDecriptImageStream(String zipFilePath,String imageURL,String password)
	{
		try{
			ZipFile zipFile = new ZipFile(zipFilePath);
			
			if(zipFile.isEncrypted())
				zipFile.setPassword(password);
			FileHeader fileHeader = zipFile.getFileHeader(imageURL);
			
			InputStream entryStream = zipFile.getInputStream(fileHeader);
	        BufferedImage image = ImageIO.read(entryStream);
	        
	        File voterImage = new File("E:/kamal/232/Kamal.jpg");
			voterImage.getParentFile().mkdirs();
			
	        ImageOutputStream iio = ImageIO.createImageOutputStream(voterImage);
			ImageIO.write(image, "JPG", iio);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		ImageDecript imageDecript = new ImageDecript();
		imageDecript.getDecriptImageStream("E:/kamal/232E.zip","Part1/RYT1363431.jpg","Kamal@karD");
	}
}
