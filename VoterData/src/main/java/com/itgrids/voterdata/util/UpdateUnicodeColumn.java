package com.itgrids.voterdata.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

public class UpdateUnicodeColumn {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://103.241.136.117:3306/public_pulse?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8";
	static final String USER = "itgrids";
	static final String PASS = "";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public static void main(String[] args)
	{
		updateUnicode(1,100000);
	}
	
	public static void updateUnicode(int fromId,int toId)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		Map<Integer,String> optionsMap = new HashMap<Integer,String>(0);
    		
    		ResultSet rs = stmt.executeQuery("SELECT options_id,options FROM options WHERE options_id BETWEEN "+fromId+" AND "+toId);
    		
    		while(rs.next())
    		{
    			try{
    				optionsMap.put(rs.getInt("options_id"),rs.getString("options"));
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		
    		for(Map.Entry<Integer,String> entry : optionsMap.entrySet())
    		{
    			try{
    				String options = entry.getValue();
    				int optionsId = entry.getKey();
    				
    				String optionsUnicode = StringEscapeUtils.unescapeJava(options);
    				
    				System.out.println(options+" --> "+optionsUnicode);
    				
    				stmt.executeUpdate("UPDATE options SET options_local = '"+optionsUnicode+"' WHERE options_id = "+optionsId);
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
