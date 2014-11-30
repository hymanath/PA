package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class ImagesValidator {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.1.219:3306/dakavara_pa?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public static void main(String[] args)
	{
		ImagesValidator validator = new ImagesValidator();
		validator.validateImages("D:\\Cadre\\voter_images\\", "D:\\Cadre\\voter_images\\");
	}
	
	public void validateImages(String srcStr,String opStr)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
    		BufferedWriter outwriter1 = new BufferedWriter(new FileWriter(opStr+"Success.txt"));
    		BufferedWriter outwriter2 = new BufferedWriter(new FileWriter(opStr+"fail.txt"));
    		
    		ResultSet rs = stmt.executeQuery("select tdp_cadre_id,voter_image_path FROM zebra_print_details where photo_type = 'VOTER' limit 100");
    		int sindex = 0;
    		int findex = 0;
    		while(rs.next())
    		{
    			try{
    				String path = rs.getString(2);
    				int tdpCadreId = rs.getInt(1);
    				
    				String[] arr = path.split("/");
    				File boothDir = new File(srcStr+arr[0]+"\\"+arr[1]+"\\");
    				if(boothDir.isDirectory())
    				{
    					String[] farr = boothDir.list();
    					List<String> flist = Arrays.asList(farr); 
    					if(flist.contains(arr[2]))
    					{
    						sindex++;
    						System.out.println(sindex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis Available");
        					sb1.append(sindex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis Available\n");
    					}
    					else
    					{
    						findex++;
    						System.out.println(findex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis not Available");
        					sb2.append(findex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis not Available\n");
    					}
    				}
    				else
    				{
    					findex++;
    					System.out.println(findex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis not Available");
    					sb2.append(findex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis not Available\n");
    				}
    				
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		outwriter1.write(sb1.toString());
    		outwriter1.close();
    		outwriter2.write(sb2.toString());
    		outwriter2.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
