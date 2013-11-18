package com.itgrids.voterdata.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class VSUpdate {
	
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://199.85.212.11/dakavara_pa";
		static final String USER = "dakavara_pa";
		static final String PASS = "1tGrids@123";
		
		static Connection conn = null;
		static Statement stmt = null;
	
		public static void main(String args[]) throws IOException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            try{
        	stmt = conn.createStatement();
        	List<Long> addList = new ArrayList<Long>(0);
        	List<Long> delList = new ArrayList<Long>(0);
        	List<Long> movList = new ArrayList<Long>(0);
        	List<Long> relList = new ArrayList<Long>(0);
        	int constituency_id = 299;

        	
            ResultSet rs = stmt.executeQuery("select voter_modification_id,voter_id,status from voter_modification where " +
            		" constituency_id = "+constituency_id+" group by voter_id having count(voter_id) = 1");
            while(rs.next())
        	{
            	long temp = rs.getLong("voter_modification_id");
            	if(rs.getString("status").equalsIgnoreCase("Added"))
            		addList.add(temp);
            	else if(rs.getString("status").equalsIgnoreCase("Deleted"))
            		delList.add(temp);
        	}
            
            ResultSet rs2 = stmt.executeQuery("select voter_modification_id,voter_id,status from voter_modification where " +
            		" constituency_id = "+constituency_id+" group by voter_id having count(voter_id) = 2");
            while(rs2.next())
        	{
            	long temp = rs2.getLong("voter_modification_id");
            	if(rs2.getString("status").equalsIgnoreCase("Added"))
            		relList.add(temp);
            	else if(rs2.getString("status").equalsIgnoreCase("Deleted"))
            		movList.add(temp);
        	}
            
            for(Long addID : addList)
            {
            	stmt.executeUpdate("update voter_modification set voter_status_id = 1 where voter_modification_id = "+addID.intValue());
            }
            for(Long addID : delList)
            {
            	stmt.executeUpdate("update voter_modification set voter_status_id = 2 where voter_modification_id = "+addID.intValue());
            }
            for(Long addID : movList)
            {
            	stmt.executeUpdate("update voter_modification set voter_status_id = 3 where voter_modification_id = "+addID.intValue());
            }
            for(Long addID : relList)
            {
            	stmt.executeUpdate("update voter_modification set voter_status_id = 4 where voter_modification_id = "+addID.intValue());
            }
            
            ResultSet rs3 = stmt.executeQuery("select voter_modification_id,voter_id,status from voter_modification where " +
            		" constituency_id = "+constituency_id+" and voter_status_id is null ");
            
            movList = new ArrayList<Long>(0);
        	relList = new ArrayList<Long>(0);
            while(rs3.next())
        	{
            	long temp = rs3.getLong("voter_modification_id");
            	if(rs3.getString("status").equalsIgnoreCase("Added"))
            		relList.add(temp);
            	else if(rs3.getString("status").equalsIgnoreCase("Deleted"))
            		movList.add(temp);
        	}
            for(Long addID : movList)
            {
            	stmt.executeUpdate("update voter_modification set voter_status_id = 3 where voter_modification_id = "+addID.intValue());
            }
            for(Long addID : relList)
            {
            	stmt.executeUpdate("update voter_modification set voter_status_id = 4 where voter_modification_id = "+addID.intValue());
            }
            
        	}catch (Exception e) {
        		e.printStackTrace();
        	}
        }catch (Exception e) {
		}
	}
            

}
