package com.itgrids.voterdata.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;


public class KamalImage {

	public void imageView(String path)
	{
		try{
			ZipFile zipFile = new ZipFile(path);
	        ZipEntry entry = new ZipEntry("232/Part1/AP181250312001.jpg");
	        InputStream entryStream = zipFile.getInputStream(entry);
	        BufferedImage image = ImageIO.read(entryStream);
	        
	        File voterImage = new File("E:/kamal/232/Part1/AP181250312001.jpg");
			voterImage.getParentFile().mkdirs();
			
	        ImageOutputStream iio = ImageIO.createImageOutputStream(voterImage);
			ImageIO.write(image, "JPG", iio);
			
			zipFile.close();
		}catch (Exception e) {
			e.printStackTrace();
    	}

	}
	
	public static void main(String args[])
	{
		KamalImage2 image = new KamalImage2();
		//image.imageView("E:/kamal/232/Part1/AP181250312001.jpg");
		image.imageView("E:/kamal/232/Part1.zip");
	}
	
}
