package com.itgrids.voterdata.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class VoterImageCreator {

	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=AC285;";
	static final String USER = "sa";
	static final String PASS = "123";

	static Connection conn = null;
	static Statement stmt = null;
	
	public void getImages()
	{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		
    		System.out.println(conn.getMetaData().getURL());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try{
			VoterImageCreator creator = new VoterImageCreator();
			creator.getImages();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
}
