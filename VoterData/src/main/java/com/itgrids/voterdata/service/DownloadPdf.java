package com.itgrids.voterdata.service;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class DownloadPdf {

	public static void main(String[] args) 
	{

		String dirName = "D:\\FileDownload";

		try {

	 
			//saveFileFromUrlWithJavaIO(dirName + "\\maven_eclipse_and_osgi_working_together.pdf","http://epaper.thehansindia.com/download/newspaperpage/1113713/d5fa7e61-3a51-433f-abc0-784b8afd4835");

			System.out.println("Downloaded \'Maven, Eclipse and OSGi working together\' PDF document.");

			//saveFileFromUrlWithCommonsIO(dirName + "\\innoq_ws-standards_poster_2007-02.pdf","http://epaper.thehansindia.com/download/newspaperpage/1113713/d5fa7e61-3a51-433f-abc0-784b8afd4835");

			System.out.println("Downloaded \'InnoQ Web Services Standards Poster\' PDF document.");
			
			download();

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

// Using Java IO
 public static void saveFileFromUrlWithJavaIO(String fileName, String fileUrl) throws MalformedURLException, IOException 
 {
	 BufferedInputStream in = null;
	 FileOutputStream fout = null;
	 try {
		 in = new BufferedInputStream(new URL(fileUrl).openStream());
		 fout = new FileOutputStream(fileName);

		 byte data[] = new byte[1024];
		 int count;
		 
		 while ((count = in.read(data, 0, 1024)) != -1) {
			 fout.write(data, 0, count);
		 }
	 } finally {
		 if (in != null)
			 in.close();
		 if (fout != null)
			 fout.close();
	 	}
 }


 public static void saveFileFromUrlWithCommonsIO(String fileName, String fileUrl) throws MalformedURLException, IOException 
 {
	 FileUtils.copyURLToFile(new URL(fileUrl), new File(fileName));
 }
 
 public static void download()
 {
	 try{
		 
		 
	 URL url = new URL("http://epaper.thehansindia.com/download/newspaperpage/1113713/d5fa7e61-3a51-433f-abc0-784b8afd4835");
     
     InputStream in = url.openStream();
       
     FileOutputStream fos = new FileOutputStream(new File("D:\\FileDownload\\1.pdf"));

     int length = -1;
     byte[] buffer = new byte[40960];
     while ((length = in.read(buffer)) > -1) {
         fos.write(buffer, 0, length);
     }
     fos.close();
     in.close();
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
 }

}