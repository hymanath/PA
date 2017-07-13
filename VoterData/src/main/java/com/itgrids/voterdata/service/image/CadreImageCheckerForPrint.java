package com.itgrids.voterdata.service.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CadreImageCheckerForPrint {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://tdpcluster-1.cluster-ch7baotjdda3.us-east-1.rds.amazonaws.com:3306/dakavara_pa";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Rd%2dWvdiwm";
	//static final String PASS = "root";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	static String cadreImagePath = "C:/Users/Administrator/Desktop/Workspace/Cadre_Image/CHECK/SET8/";
	static String resultDir = "C:/Users/Administrator/Desktop/Workspace/Cadre_Image/CHECK/SET8/";
	
	public static void main(String[] args)
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		
		int constituencyId = 8;
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultDir+constituencyId+".txt"));
		checkCadreImage(constituencyId,outwriter);
		outwriter.close();
		
		conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void checkCadreImage(int setNo,BufferedWriter outwriter)
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			StringBuilder sb = new StringBuilder();
			
			ResultSet rs = stmt.executeQuery("SELECT tdp_cadre_card_print_id,tdp_cadre_id,image_path FROM tdp_cadre_card_print WHERE set_no = "+setNo);
			int index = 0;
			
			 while(rs.next())
			 {
				 try{
					 Integer tdpCadreCardPrintId = rs.getInt("tdp_cadre_card_print_id");
					 Integer tdpCadreId = rs.getInt("tdp_cadre_id");
					 String image = rs.getString("image_path");
					 
					 File file = new File(cadreImagePath+image);
					 
					 index++;
						
					 if(index%10000 == 0)
						System.out.println(index);
						
					 if(!file.exists())
					 {
						System.out.println(tdpCadreCardPrintId+"\t"+tdpCadreId+"\t"+image);
						sb.append(tdpCadreCardPrintId+"\t"+tdpCadreId+"\t"+image+"\n");
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

