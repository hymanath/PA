package com.itgrids.voterdata.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
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
	static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=AC_001;";
	static final String USER = "sa";
	static final String PASS = "1tGrids";

	static Connection conn = null;
	static Statement stmt = null;
	
	public void getImages(String folderPath)
	{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		Date d1 = new Date();
    		ResultSet rs = stmt.executeQuery("SELECT Ac_No,PART_NO,IDCARD_NO,PHOTO FROM E_DETAIL");
    		
    		byte[] imgData = null;
    		while(rs.next())
    		{
    			try{
    				Integer constituencyId = rs.getInt("Ac_No");
    				Integer partNo = rs.getInt("PART_NO");
    				String voterId = rs.getString("IDCARD_NO");
    				java.sql.Blob img  = rs.getBlob("PHOTO");
    				imgData = img.getBytes(1,(int)img.length());
    				File voterImage = new File(folderPath+"/"+constituencyId+"/PART"+partNo+"/"+voterId+".jpg");
    				voterImage.getParentFile().mkdirs();
    				BufferedImage image = ImageIO.read(new ByteArrayInputStream(imgData));
    				
    				image = Scalr.resize(image,Scalr.Method.ULTRA_QUALITY,Scalr.Mode.FIT_EXACT,60,80);
    				ImageOutputStream iio = ImageIO.createImageOutputStream(voterImage);
    				ImageIO.write(image, "JPG", iio);
    				
    			}catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		Date d2 = new Date();
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
			creator.getImages("E:/Voter_Images");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
