package com.itgrids.voterdata.service.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONObject;

import com.itgrids.voterdata.service.ImageAndStringConverter;

public class CadreImageCreatorForNewType {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://tdpcluster-1.cluster-ch7baotjdda3.us-east-1.rds.amazonaws.com:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Rd%2dWvdiwm";
	
	static Connection conn = null;
	static Statement stmt = null;
	static final String cadreImgFolder = "D:/Workspace/cadre_images/2014/";
	
	public static void main(String[] args)
	{
		createMissedImages("2016-08-01","2016-12-31");
	}
	
	public static void createMissedImages(String startDate,String endDate)
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		ImageAndStringConverter converter = new ImageAndStringConverter();
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("D:/Workspace/cadre_images/result.txt"));
    		StringBuilder sb = new StringBuilder();
    		
    		ResultSet rs = stmt.executeQuery("SELECT TC.tdp_cadre_id,UA.constituency_id,TC.membership_id,BD.json_obj "
    				+ " FROM tdp_cadre_enrollment_year EY,tdp_cadre TC,user_address UA,tdp_cadre_response_details RD,tdp_cadre_backup_details BD WHERE "
    				+ " EY.tdp_cadre_id = TC.tdp_cadre_id AND  TC.address_id = UA.user_address_id AND TC.tdp_cadre_id = RD.tdp_cadre_id AND "
    				+ " RD.backup_id = BD.tdp_cadre_backup_details_id AND EY.is_deleted = 'N' AND TC.is_deleted = 'N' AND TC.enrollment_year = 2014 AND "
    				+ " EY.enrollment_year_id = 4 AND TC.image IS NULL AND  TC.photo_type = 'NEW' AND RD.save_status = 'Success'");
    				//+ " AND DATE(TC.inserted_time) BETWEEN '"+startDate+"' AND '"+endDate+"'");
    		
    		while(rs.next())
    		{
    			try{
    				Integer tdpCadreId = rs.getInt("tdp_cadre_id");
    				Integer constituencyId = rs.getInt("constituency_id");
    				String membershipId = rs.getString("membership_id");
    				String jsonObjStr = rs.getString("json_obj");
    				
    				String imgPath = constituencyId+"/"+membershipId+".jpg";
    				JSONObject jsonObj = new JSONObject(jsonObjStr);
    				String imageJson = jsonObj.getString("imageBase64String");
    				
    				if(imageJson != null)
    				{
    					File imgFile = new File(cadreImgFolder+imgPath);
    					imgFile.getParentFile().mkdirs();
    					boolean status = converter.convertBase64StringToImage(imageJson,cadreImgFolder+imgPath);
    					
    					if(status)
    					{
    						System.out.println(tdpCadreId+" Success");
    						sb.append("UPDATE tdp_cadre SET image = '"+imgPath+"' WHERE tdp_cadre_id = "+tdpCadreId+";\n");
    					}
    					else
    						System.out.println(tdpCadreId+" Failed");
    				}
    				
    			}catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		outwriter.write(sb.toString());
    		outwriter.close();
    		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
