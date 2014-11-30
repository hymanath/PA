package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TotalRecordsOfDB {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.1.219:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public void getTotalNoOfRecords()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		List<String> tablesList = new ArrayList<String>(0);
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter("H:\\KOTI\\Result.txt"));
    		StringBuilder sb = new StringBuilder();
    		
    		ResultSet rs = stmt.executeQuery("show tables");
    		
    		 while(rs.next())
    			 tablesList.add(rs.getString(1));
    		 
    		 for(String table : tablesList)
    		 {
    			if(!(table.equalsIgnoreCase("booth_publication_voter") || table.equalsIgnoreCase("voter") || table.equalsIgnoreCase("tempbooth_publication_voter")))
    			{
    			 System.out.print(table);
    			 try{
    			 rs = stmt.executeQuery("select count(*) from "+table);
    			 while(rs.next())
    			 {
    				 try{
    				 sb.append(table+"\t"+rs.getInt(1)+"\n");
    				 System.out.println("\t"+(rs.getInt(1)));
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
		TotalRecordsOfDB extractor = new TotalRecordsOfDB();
		extractor.getTotalNoOfRecords();
	}
}
