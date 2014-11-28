package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpecialCharactersRemover {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.1.219:3306/dakavara_pa?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public void removeCharacters()
	{
		try{
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		Map<Integer,String> map = new HashMap<Integer, String>(0);
    		Date d1 = new Date();
    		ResultSet rs = stmt.executeQuery("select zebra_print_details_id,voter_name from zebra_print_details limit 0,100");
    		
    		int index = 0;
    		while(rs.next())
    		{
    			String str = rs.getString(2);
    			str = str.replaceAll("\\p{C}", "");
    			map.put(rs.getInt(1),str);
    		}
    		
    		for(Map.Entry<Integer,String> entry : map.entrySet())
    		{
    			try{
    				System.out.println("We are at --> "+ ++index);
    				stmt.executeUpdate("update zebra_print_details set voter_name = '"+entry.getValue().trim()+"' where zebra_print_details_id = "+entry.getKey());
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		Date d2 = new Date();
    		System.out.println("For "+index+" Records, Time taken is --> "+(d2.getTime()-d1.getTime())+" Milli Seconds");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		 SpecialCharactersRemover remover = new SpecialCharactersRemover();
		 remover.removeCharacters();
	}
	
}
