package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MobileInserter {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.1.219:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	/*public void getAndInsertMobileNumbers(int districtId)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		int total = 0;
    		
    		ResultSet rs = stmt.executeQuery("select count(mobile) from mobile_kml where district_id = "+districtId);
    		
    		while(rs.next())
    			total = rs.getInt(1);
    		
    		System.out.println("Total Mobiles Available --> "+total);
    		int index = 0;
    		for(;;)
    		{
    			try{
    			
    			System.out.println("Now index at -->"+index+" Time -->"+new Date());
    			
    			if(index >= total)
    				break;
    			
    			List<String> mobilesList = new ArrayList<String>(0);
    			List<String> aviMobilesList = new ArrayList<String>(0);
    			List<String> inMobilesList = new ArrayList<String>(0);
    			
    			rs = stmt.executeQuery("select mobile from mobile_kml where district_id = "+districtId+" LIMIT "+index+",10000");
    			
    			while(rs.next())
    				mobilesList.add(rs.getString(1));
    			
    			String mobileStr = getListToString(mobilesList);
    			
    			rs = stmt.executeQuery("select mobile_number from mobile_numbers where is_deleted = 'N' and mobile_number in("+mobileStr+")");
    			
    			while(rs.next())
    				aviMobilesList.add(rs.getString(1));
    			
    			for(String ms : mobilesList)
    			if(!aviMobilesList.contains(ms))
    				inMobilesList.add(ms);
    			
    			insertMobileNumbers(districtId,inMobilesList);
    			
    			index += 10000;
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	
	public void getAndInsertMobileNumbers(int districtId,String sourcePath,String destPath)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		int total = 0;
    		List<String> mobilesList = new ArrayList<String>(0);
    		
    		BufferedReader br =  new BufferedReader(new FileReader(new File(sourcePath)));
    		BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File(destPath)));
			StringBuilder sb = new StringBuilder();
    		String line = null;
			
			while((line = br.readLine()) != null)
			{
				if(line.trim().length() == 10)
				{
					mobilesList.add(line.trim());
					total++;
				}
			}
			br.close();
    		
    		System.out.println("Total Mobiles Available --> "+total);
    		
    		int index = 0;
    		int toIndex = 50000;
    		for(;;)
    		{
    			try{
    			
    			System.out.println("Now index at -->"+index+" Time -->"+new Date());
    			
    			if(toIndex > total)
    				toIndex = total;
    			
    			if(index >= total)
    				break;
    			
    			List<String> mobilesSubList = mobilesList.subList(index, toIndex);
    			List<String> aviMobilesList = new ArrayList<String>(0);
    			
    			String mobileStr = getListToString(mobilesSubList);
    			
    			ResultSet rs = stmt.executeQuery("select mobile_number from mobile_numbers where is_deleted = 'N' and mobile_number in("+mobileStr+")");
    			
    			while(rs.next())
    				aviMobilesList.add(rs.getString(1));
    			
    			for(String ms : mobilesSubList)
    			if(!aviMobilesList.contains(ms))
    				sb.append(ms+"\n");
    			
    			index += 50000;
    			toIndex += 50000;
    			
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
	
	public String getListToString(List<String> mobileList)
	{
		try{
			String mobStr = "";
			for(String str : mobileList)
				mobStr = mobStr+"'"+str+"',";
			return mobStr.substring(0,mobStr.length()-1);
				
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void insertMobileNumbers(int districtId,List<String> mobilesList)
	{
		try{
			System.out.println("Entering insertMobileNumbers"+new Date());
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		//conn.setAutoCommit(false);
    		
    		for(String mobile : mobilesList)
    		{
    			try{
    			String insertQuery = "INSERT INTO mobile_numbers(mobile_number,mobile_source_type_id,district_id,is_deleted,is_used) values (" +
        				"'"+mobile+"',6,"+districtId+",'N','N')";
    			stmt.executeUpdate(insertQuery);
    			}catch(Exception e)
    			{
    				e.printStackTrace();
    			}
    		}
    		//conn.commit();
    		System.out.println("Existing insertMobileNumbers"+new Date());
    		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		MobileInserter inserter = new MobileInserter();
		
		File dir = new File("C:\\Users\\Kamalakar\\Desktop\\mobile\\DSK");
		
		if(dir.isDirectory())
			for(File file : dir.listFiles())
				if(!file.isDirectory())
				inserter.getAndInsertMobileNumbers(17,file.getAbsolutePath(),file.getAbsolutePath()+"_2K");
	}
}
