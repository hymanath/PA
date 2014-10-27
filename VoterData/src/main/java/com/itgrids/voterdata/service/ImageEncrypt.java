package com.itgrids.voterdata.service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;


public class ImageEncrypt {

	public static void main(String[] args)
	{
		ImageEncrypt imageEncrypt = new ImageEncrypt();
		imageEncrypt.imageEncrypt("D:\\Photos\\344\\");
	}
	
	public void imageEncrypt(String filePath)
	{
		try{
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		Date date = new Date();
		System.out.println("Start Time " + date.toGMTString());
		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  RandomAccessFile raf = new RandomAccessFile(listOfFiles[i], "rw");
		    	  raf.seek(5L);
		    	  raf.write(17);
		    	  raf.close();
		        //System.out.println("File " + listOfFiles[i].getName());
		      }
		      else if (listOfFiles[i].isDirectory()) {
		    	  System.out.println("Folder ::" + listOfFiles[i].getName());
		    	  File[] listOfFiles1 = listOfFiles[i].listFiles();
		    	  for (int j = 0; j < listOfFiles1.length; j++) {
				      if (listOfFiles1[j].isFile()) {
				    	  RandomAccessFile raf = new RandomAccessFile(listOfFiles1[j], "rw");
				    	  raf.seek(5L);
				    	  raf.write(17);
				    	  raf.close();
				       
				      }
		    	  }
		       
		      }
		    }
		    Date date1 = new Date();
		    System.out.println("End Time " + date1.toGMTString());
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}
