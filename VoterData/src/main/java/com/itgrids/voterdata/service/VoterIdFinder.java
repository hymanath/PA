package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VoterIdFinder {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL1 = "jdbc:mysql://localhost:3372/dakavara_pa";
	static final String USER1 = "root";
	static final String PASS1 = "root";
	static final String DB_URL2 = "jdbc:mysql://10.0.1.219/dakavara_pa";
	static final String USER2 = "root";
	static final String PASS2 = "Danduk1634";	
	static Connection conn1 = null;
	static Statement stmt = null;
	static Connection conn2 = null;
	static Statement stmt2 = null;
	static Statement stmt3 = null;
	
	public static void main(String[] args)
	{
		try{
		File resultFile  = new File(args[0]+"/result.txt");
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
		StringBuilder sb = new StringBuilder();
		Class.forName("com.mysql.jdbc.Driver");
        conn1 = DriverManager.getConnection(DB_URL1,USER1,PASS1);
        conn2 = DriverManager.getConnection(DB_URL2,USER2,PASS2);
        
        stmt = conn1.createStatement();
        stmt3 = conn1.createStatement();
        stmt2 = conn2.createStatement();
        
        ResultSet rs = stmt.executeQuery("select reg_id,name,mobile,constituency,cid,tehsil,tehsil_id from kamal_tmp where match_count is null or match_count = 0");
        int sno = 1;
        while(rs.next())
        {
        	try{
        		int rid = rs.getInt("reg_id");
        		String name = rs.getString("name").trim();
        		int cid = rs.getInt("cid");
        		int tehsil_id = rs.getInt("tehsil_id");
        		int count = 0;
        		String voter_id = "";
        		
        		String[] arr = name.trim().split(" ");
        		if(arr.length == 2)
        		{
        			name = arr[1] + " "+arr[0];
        		}
        		else 
        			continue;
        		
        		System.out.println(sno+++") Reading -->"+rid);
        		
        		ResultSet rs2 = stmt2.executeQuery("select voter_id_card_no from voter V,booth_publication_voter BPV,booth B WHERE V.voter_id = BPV.voter_id AND" +
        				" B.booth_id = BPV.booth_id AND B.constituency_id = "+cid+" AND B.publication_date_id = 10 AND B.tehsil_id = "+tehsil_id+" " +
        						" AND LTRIM(RTRIM(V.name)) = '"+name+"'");
        		while(rs2.next())
        		{
        			count++;
        			voter_id = rs2.getString("voter_id_card_no");
        		}
        		System.out.println("Number of Rows Matched -->"+count);
        		
        		if(count == 0)
        		{
        			sb.append("rid - "+rid+"\tName - "+name+"\tCount - 0");
        			stmt3.executeUpdate("update kamal_tmp set match_count = 0 where reg_id = "+rid);
        		}
        		else if (count == 1)
        		{
        			
        			sb.append("rid - "+rid+"\tName - "+name+"\tCount - 0\tVoter Id - "+voter_id);
        			stmt3.executeUpdate("update kamal_tmp set match_count = 1,voter_id = '"+voter_id+"' where reg_id = "+rid);
        		}
        		else if(count > 1)
        		{
        			sb.append("rid - "+rid+"\tName - "+name+"\tCount - "+count);
        			stmt3.executeUpdate("update kamal_tmp set match_count = "+count+" where reg_id = "+rid);
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
}
