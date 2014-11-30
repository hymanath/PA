package com.itgrids.voterdata.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataTransferToSqlite {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.1.219:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	static Connection connection = null;
	static Statement statement = null;
	
	public void updateVoterLocalNames()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.sqlite.JDBC");
			
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Kamalakar/Desktop/SQLITE/KAMAL/KUPPAM.sqlite");
			connection.setAutoCommit(false);
			statement = connection.createStatement();
    		
    		ResultSet rs = stmt.executeQuery("select voter_id,voter_name,relative_name from voter_telugu_names");
    		int i = 0;
			while(rs.next())
			 {
				 try{
					 int voterId = rs.getInt(1);
					 String name = rs.getString(2);
					 String relativeName = rs.getString(3);
					 
					 if(name == null)
						 name = "";
					 if(relativeName == null)
						 relativeName = "";
					 
					 name = name.trim();
					 relativeName = relativeName.trim();
					 
					 System.out.println(++i+" --> "+voterId);
					 /*System.out.println(name);
					 System.out.println(relativeName);*/
					 
					 statement.executeUpdate("update voter set name_in_telugu = '"+name+"',relative_name_in_telugu = '"+relativeName+"' where " +
					 		" voter_id = "+voterId);
				 }catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
			connection.commit();
			statement.close();
			connection.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateMemberLocalNames()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.sqlite.JDBC");
			
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Kamalakar/Desktop/SQLITE/KAMAL/KUPPAM.sqlite");
			connection.setAutoCommit(false);
			statement = connection.createStatement();
    		
    		ResultSet rs = stmt.executeQuery("SELECT nMemberID,sMemberTName from td_member_total where constituency_id_pa = 282");
    		int i = 0;
			while(rs.next())
			 {
				 try{
					 int memberId = rs.getInt(1);
					 String name = rs.getString(2);
					 
					 if(name == null)
						 name = "";
					 
					 name = name.trim();
					 
					 System.out.println(++i+" --> "+memberId);
					 
					 statement.executeUpdate("update member set member_telugu_name = '"+name+"' where " +
					 		" member_id = "+memberId+" and year = 2012");
				 }catch(Exception e)
				 {
					 e.printStackTrace();
				 }
			 }
			connection.commit();
			statement.close();
			connection.close();
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		DataTransferToSqlite extractor = new DataTransferToSqlite();
		extractor.updateMemberLocalNames();
	}
}
