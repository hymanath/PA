package com.itgrids.voterdata.service;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.imgscalr.Scalr;

public class VoterImageCreator {

	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String DB_URL = "jdbc:sqlserver://192.168.11.4:1433;databaseName=";
	static final String USER = "sa";
	static final String PASS = "1tGrids";

	static Connection conn = null;
	static Statement stmt = null;
	
	public void getImages(String folderPath,String dbName)
	{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		conn = DriverManager.getConnection(DB_URL+dbName,USER,PASS);
    		stmt = conn.createStatement();
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("D:\\Voter_Images_2016\\TS\\Result_"+dbName+".txt"));
    		System.out.println("------------------------- "+dbName+" Started -------------------------\n ");
    		outwriter.write("------------------------- "+dbName+" Started -------------------------\n");
    		Date d1 = new Date();
    		ResultSet rs = stmt.executeQuery("SELECT Ac_No,PART_NO,IDCARD_NO,PHOTO FROM E_DETAIL");
    		int total = 0;
    		int success = 0;
    		int fail = 0;
    		
    		byte[] imgData = null;
    		while(rs.next())
    		{
    			String voterId = "";
    			try{
    				total++;
    				Integer constituencyId = rs.getInt("Ac_No");
    				Integer partNo = rs.getInt("PART_NO");
    				voterId = rs.getString("IDCARD_NO");
    				java.sql.Blob img  = rs.getBlob("PHOTO");
    				imgData = img.getBytes(1,(int)img.length());
    				File voterImage = new File(folderPath+"/"+constituencyId+"/Part"+partNo+"/"+voterId+".jpg");
    				voterImage.getParentFile().mkdirs();
    				BufferedImage image = ImageIO.read(new ByteArrayInputStream(imgData));
    				
    				image = Scalr.resize(image,Scalr.Method.ULTRA_QUALITY,Scalr.Mode.FIT_EXACT,60,80);
    				ImageOutputStream iio = ImageIO.createImageOutputStream(voterImage);
    				ImageIO.write(image, "JPG", iio);
    				success++;
    			}catch (Exception e) {
    				fail++;
    				e.printStackTrace();
    			}
    		}
    		conn.close();
    		Date d2 = new Date();
    		System.out.println(dbName+"\tTotal - "+total+"\tSuccess - "+success+"\tFail - "+fail);
    		outwriter.write(dbName+"\tTotal - "+total+"\tSuccess - "+success+"\tFail - "+fail+"\n");
    		outwriter.close();
    		System.out.println("------------------------- "+dbName+" Ended -------------------------\n ");
    		System.out.println("Time Taken in Mins - "+(d2.getTime()-d1.getTime())/(1000*60));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try{
			VoterImageCreator creator = new VoterImageCreator();
			
			creator.getImages("D:/Voter_Images_2016/TS","AC_119");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
