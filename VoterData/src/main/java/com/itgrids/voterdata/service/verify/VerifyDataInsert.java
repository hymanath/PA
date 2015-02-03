package com.itgrids.voterdata.service.verify;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.itgrids.voterdata.VO.SinkVO;

public class VerifyDataInsert {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL1 = "jdbc:mysql://192.168.11.61:3306/dakavara_pa?autoReconnect=true";
	static final String USER1 = "root";
	static final String PASS1 = "kamalaakar";
	
	static final String DB_URL2 = "jdbc:mysql://10.0.1.219:3306/dakavara_pa?autoReconnect=true";
	static final String USER2 = "root";
	static final String PASS2 = "Danduk1634";
	
	static Connection conn1 = null;
	static Statement stmt1 = null;
	static Connection conn2 = null;
	static Statement stmt2 = null;
	
	public void insertRequestData()
	{
		try{
			conn1 = DriverManager.getConnection(DB_URL1,USER1,PASS1);
    		stmt1 = conn1.createStatement();
    		
    		ResultSet rs = stmt1.executeQuery("SELECT request_data FROM tdp_cadre_verfied_data where tdp_cadre_verfied_data_id = 3359");
    		Gson gson = new Gson();
    		List<SinkVO> requestsList = null;
    		BufferedWriter outwriter1 = new BufferedWriter(new FileWriter("H:\\KOTI\\UUID.txt"));
    		StringBuilder sb1 = new StringBuilder();
    		
    		while(rs.next())
    		{
    			System.out.println(rs.getString(1));
    			JSONArray arr = new JSONArray(rs.getString(1));
    			requestsList = new ArrayList<SinkVO>(0);
    			
    			for(int i=0;i<arr.length();i++)
    			{
    				JSONObject obj = arr.getJSONObject(i);
    				SinkVO sinkVO = gson.fromJson(obj.toString(),SinkVO.class);
    				requestsList.add(sinkVO);
    				System.out.println(sinkVO.getUid());
    				sb1.append(sinkVO.getVid()+"\n");
    			}
    			
    		}
    		outwriter1.write(sb1.toString());
    		outwriter1.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		VerifyDataInsert insert = new VerifyDataInsert();
		insert.insertRequestData();
	}
	
	
}
