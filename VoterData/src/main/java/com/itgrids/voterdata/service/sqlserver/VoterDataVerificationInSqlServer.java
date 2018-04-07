package com.itgrids.voterdata.service.sqlserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VoterDataVerificationInSqlServer {

	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String DB_URL = "jdbc:sqlserver://192.168.11.4:1433;databaseName=";
	static final String USER = "sa";
	static final String PASS = "1tGrids";

	Connection conn = null;
	Statement stmt = null;
	
	private void getVotersCount(int dbName,BufferedWriter outwriter)
	{
		try{
    		conn = DriverManager.getConnection(DB_URL+"AC_"+dbName,USER,PASS);
    		stmt = conn.createStatement();
    		
    		ResultSet rs = stmt.executeQuery("SELECT COUNT(DISTINCT IDCARD_NO) FROM E_DETAIL");
    		
    		while(rs.next())
    		{
    			Integer count = rs.getInt(1);
    			System.out.println(dbName+"\t"+count);
        		outwriter.write(dbName+"\t"+count+"\n");
    		}
    		rs.close();
    		conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void getBoothWiseVotersCount(int dbName,BufferedWriter outwriter)
	{
		try{
    		conn = DriverManager.getConnection(DB_URL+"AC_"+dbName,USER,PASS);
    		stmt = conn.createStatement();
    		
    		ResultSet rs = stmt.executeQuery("SELECT PART_NO,COUNT(DISTINCT IDCARD_NO) FROM E_DETAIL GROUP BY PART_NO ORDER BY PART_NO");
    		
    		while(rs.next())
    		{
    			Integer booth = rs.getInt(1);
    			Integer count = rs.getInt(2);
    			System.out.println(dbName+"\t"+booth+"\t"+count);
        		outwriter.write(dbName+"\t"+booth+"\t"+count+"\n");
    		}
    		rs.close();
    		conn.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			VoterDataVerificationInSqlServer verifier = new VoterDataVerificationInSqlServer();
			BufferedWriter outwriter = new BufferedWriter(new FileWriter("C:/Users/Administrator/Desktop/VD2017/data2.txt"));
			
			for(int i=120;i<=294;i++)
				verifier.getBoothWiseVotersCount(i,outwriter);
			
			outwriter.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
