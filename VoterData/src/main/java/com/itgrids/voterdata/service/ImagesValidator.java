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
		
		validator.validateImages("D:\\itgsharing\\Voter_Images\\", "D:\\itgsharing\\Kamal\\IV_Logs5\\",17,3200000,200000);
		
		//validator.validateImages("D:\\itgsharing\\Voter_Images\\", "D:\\itgsharing\\Kamal\\IV_Logs7\\",5,0,200000);
	}
	
	public void validateImages(String srcStr,String opStr,int idx,int stInd,int records)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
    		BufferedWriter outwriter1 = new BufferedWriter(new FileWriter(opStr+"TC_Success"+idx+".txt"));
    		BufferedWriter outwriter2 = new BufferedWriter(new FileWriter(opStr+"TC_fail"+idx+".txt"));
    		BufferedWriter outwriter3 = new BufferedWriter(new FileWriter(opStr+"TC_encrypt"+idx+".txt"));
    		
    		ResultSet rs = stmt.executeQuery("select TC.tdp_cadre_id,CONCAT(B.constituency_id,'/Part',B.part_no,'/',V.voter_id_card_no,'.jpg') FROM tdp_cadre TC,voter V,booth_publication_voter BPV,booth B " +
    				" where TC.voter_id = V.voter_id and V.voter_id = BPV.voter_id and BPV.booth_id = B.booth_id and TC.photo_type = 'VOTER' and B.publication_date_id = 11 and TC.is_deleted = 'N' and TC.enrollment_year = 2014" +
    				" order by TC.tdp_cadre_id limit "+stInd+","+records);
    				
    		/*ResultSet rs = stmt.executeQuery("select TC.tdp_cadre_id,CONCAT(B.constituency_id,'/Part',B.part_no,'/',V.voter_id_card_no,'.jpg') FROM tdp_cadre TC,voter V,booth_publication_voter BPV,booth B " +
    				" where TC.voter_id = V.voter_id and V.voter_id = BPV.voter_id and BPV.booth_id = B.booth_id and TC.photo_type = 'VOTER' and B.publication_date_id = 11 and TC.is_deleted = 'N' and " +
    				" TC.enrollment_year = 2014 and B.constituency_id in(260)" +
    				" order by TC.tdp_cadre_id limit "+stInd+","+records);*/
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
