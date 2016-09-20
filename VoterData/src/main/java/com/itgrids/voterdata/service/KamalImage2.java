package com.itgrids.voterdata.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;


public class KamalImage2 {

	public void imageView(String path)
	{
		try{
			ZipFile zipFile = new ZipFile(path);
			
			if(zipFile.isEncrypted())
				zipFile.setPassword("test123!");
			
	        FileHeader fileHeader = zipFile.getFileHeader("Part1_2/RYT1363431.jpg");
	        
	        InputStream entryStream = zipFile.getInputStream(fileHeader);
	        BufferedImage image = ImageIO.read(entryStream);
	        
	        File voterImage = new File("E:/kamal/232/RYT1363431.jpg");
			voterImage.getParentFile().mkdirs();
			
	        ImageOutputStream iio = ImageIO.createImageOutputStream(voterImage);
			ImageIO.write(image, "JPG", iio);
			
		}catch (Exception e) {
			e.printStackTrace();
    	}

	}
	
	public static void main(String args[])
	{
		KamalImage2 image = new KamalImage2();
		image.imageView("E:/kamal/232_1.zip");
	}
	
}
