package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MobileNumbersExtractor {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://74.208.7.129:3372/survey";
	static final String USER = "kamal";
	static final String PASS = "Danduk3685";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public void getMobileNumbers()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		List<Integer> cIdsList = new ArrayList<Integer>(0);
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\TG.txt"));
    		StringBuilder sb = new StringBuilder();
    		
    		ResultSet rs = stmt.executeQuery("select constituency_id from constituency where district_id BETWEEN 1 and 10 and district_id <> 5 and" +
    				"election_scope_id = 2 and deform_date is null");
    		
    		 while(rs.next())
    			 cIdsList.add(rs.getInt("constituency_id"));
    		 
    		 for(Integer cid : cIdsList)
    		 {
    			 System.out.println("constituency -->"+cid);
    			 try{
    			 rs = stmt.executeQuery("select DISTINCT(MN.Mobile_Numbers),D.district_name from mobile_numbers MN,constituency C,district D where MN.constituency_id = C.constituency_id and C.district_id = D.district_id and " +
    			 		" MN.Constituency_ID = "+cid+" and" +
    			 		" MN.Mobile_Numbers IS NOT NULL AND LENGTH(MN.Mobile_Numbers) = 10 AND MN.Mobile_Numbers <> '9999999999' limit 500");
    			 while(rs.next())
    			 {
    				 try{
    				 sb.append(rs.getString(1)+"\n");
    				 }catch(Exception e)
    				 {
    					 e.printStackTrace();
    				 }
    			 }
    			 
    			 }catch(Exception e)
    			 {
    				 e.printStackTrace();
    			 }
    		 }
    		 outwriter.write(sb.toString());
			 outwriter.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		MobileNumbersExtractor extractor = new MobileNumbersExtractor();
		extractor.getMobileNumbers();
	}
}
