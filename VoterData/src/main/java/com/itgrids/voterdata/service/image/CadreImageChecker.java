package com.itgrids.voterdata.service.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CadreImageChecker {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://tdpcluster-1.cluster-ch7baotjdda3.us-east-1.rds.amazonaws.com:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Rd%2dWvdiwm";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	static String cadreImagePath = "/mnt/tdp-img/cadre_images/2014/";
	static String resultDir = "/mnt/tdp-img/cadre_check_logs/";
	
	public static void main(String[] args)
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultDir+"1U.txt"));
		checkCadreImage("2016-11-01","2016-11-23",outwriter);
		outwriter.close();
		conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void checkCadreImage(String fromDate,String toDate,BufferedWriter outwriter)
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			StringBuilder sb = new StringBuilder();
			
			ResultSet rs = stmt.executeQuery("SELECT UA.constituency_id,TC.tdp_cadre_id,TC.membership_id,TC.voter_id,TC.image,TC.photo_type "
					+ " FROM tdp_cadre_enrollment_year EY,tdp_cadre TC,user_address UA WHERE "
					+ " EY.tdp_cadre_id = TC.tdp_cadre_id AND TC.address_id = UA.user_address_id AND EY.is_deleted = 'N' AND TC.is_deleted = 'N' AND TC.enrollment_year = 2014 AND "
					+ " EY.enrollment_year_id = 4 AND TC.image IS NOT NULL AND DATE(TC.inserted_time) BETWEEN '"+fromDate+"' AND '"+toDate+"'");
			 while(rs.next())
			 {
				 try{
				 Integer constituencyId = rs.getInt("constituency_id");
				 Integer tdpCadreId = rs.getInt("tdp_cadre_id");
				 Integer voterId = rs.getInt("voter_id");
				 String membershipId = rs.getString("membership_id");
				 String image = rs.getString("image");
				 String photoType = rs.getString("photo_type");
				 
				 File file = new File(cadreImagePath+image);
				 
				 if(!file.exists())
				 {
					sb.append(constituencyId+"\t"+tdpCadreId+"\t"+membershipId+"\t"+voterId+"\t"+image+"\t"+photoType+"\n");
				 }
				 
				 }catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
			 outwriter.write(sb.toString());
			 conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

