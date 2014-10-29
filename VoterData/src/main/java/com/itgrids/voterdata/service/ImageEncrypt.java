package com.itgrids.voterdata.service;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Date;


public class ImageEncrypt {

	public static void main(String[] args)
	{
		ImageEncrypt imageEncrypt = new ImageEncrypt();
		File dir = new File("E:\\Guntur Images");
		for(File inDir : dir.listFiles())
		{
			if(inDir.isDirectory())
				imageEncrypt.imageEncrypt(inDir.getAbsolutePath(),inDir.getAbsolutePath()+".txt");
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void imageEncrypt(String filePath,String logPath)
	{
		try{
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File(logPath)));
		StringBuilder sb = new StringBuilder();
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		Date date = new Date();
		System.out.println("Start Time " + date.toGMTString());
		int totalPhotos = 0;
		    
		for (int i = 0; i < listOfFiles.length; i++) 
	    {
		  totalPhotos++;
	      if (listOfFiles[i].isFile()) 
	      {
	    	  RandomAccessFile raf = new RandomAccessFile(listOfFiles[i], "rw");
	    	  raf.seek(5L);
	    	  raf.write(17);
	    	  raf.close();
	      }
	      else if (listOfFiles[i].isDirectory()) 
	      {
	    	  int boothCount = 0;
	    	  File[] listOfFiles1 = listOfFiles[i].listFiles();
	    	  for (int j = 0; j < listOfFiles1.length; j++)
	    	  {
	    		  totalPhotos++;
	    		  boothCount++;
			      if (listOfFiles1[j].isFile())
			      {
			    	  RandomAccessFile raf = new RandomAccessFile(listOfFiles1[j], "rw");
			    	  raf.seek(5L);
			    	  raf.write(17);
			    	  raf.close();
			      }
	    	  }
	    	  System.out.println(listOfFiles[i].getName()+" Contains -->\t"+boothCount+"\tImages");
	    	  sb.append(listOfFiles[i].getName()+"\t"+boothCount+"\n");
	      }
	    }
		
		System.out.println("Total Images are --> "+totalPhotos);
		sb.append("Total Images are --> "+totalPhotos);
		outwriter.write(sb.toString());
        outwriter.close();
	    Date date1 = new Date();
	    System.out.println("End Time " + date1.toGMTString());
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	}
}
