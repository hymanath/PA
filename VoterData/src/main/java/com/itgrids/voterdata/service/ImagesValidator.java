package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

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
		//validator.verifyEncode("D:\\Cadre\\voter_images\\Enc\\UXN0969487_2.jpg");
	}
	
	public void validateImages(String srcStr,String opStr)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
    		BufferedWriter outwriter1 = new BufferedWriter(new FileWriter(opStr+"Success.txt"));
    		BufferedWriter outwriter2 = new BufferedWriter(new FileWriter(opStr+"fail.txt"));
    		BufferedWriter outwriter3 = new BufferedWriter(new FileWriter(opStr+"encrypt.txt"));
    		
    		ResultSet rs = stmt.executeQuery("select tdp_cadre_id,voter_image_path FROM zebra_print_details where photo_type = 'VOTER' limit 100");
    		int sindex = 0;
    		int findex = 0;
    		int eIndex = 0;
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
    						
        					
        					File imageFile = new File(srcStr+arr[0]+"\\"+arr[1]+"\\"+arr[2]);
        					FileInputStream imageInFile = new FileInputStream(imageFile);
        		            byte imageData[] = new byte[(int) imageFile.length()];
        		            imageInFile.read(imageData);
        		 
        		            String imageDataString = encodeImage(imageData);
        		            String str = imageDataString.substring(7,8);
        		            imageInFile.close();
        		            
        		            if(str.equalsIgnoreCase("Q"))
        		            {
        		            	sindex++;
        		            	System.out.println(sindex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis Available & Valid");
            					sb1.append(sindex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis Available & Valid\n");
        		            }
        		            else
        		            {
        		            	findex++;
        		            	eIndex++;
        						System.out.println(findex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis Available but Encrypted");
            					sb2.append(findex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis Available but Encrypted\n");
            					sb3.append(eIndex+") tdp_cadre_id --> "+tdpCadreId+"\t Path --> "+path+"\tis Encrypted\n");
        		            }
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
    		outwriter3.write(sb3.toString());
    		outwriter3.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void verifyEncode(String filePath)
	{
		try{
			File file = new File(filePath);
			FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
 
            String imageDataString = encodeImage(imageData);
            System.out.println(imageDataString);
            
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static String encodeImage(byte[] imageByteArray) {
	        return Base64.encodeBase64URLSafeString(imageByteArray);
	    }
	 
	 
}
