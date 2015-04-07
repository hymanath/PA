package com.itgrids.voterdata.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.language.RefinedSoundex;

import com.itgrids.voterdata.VO.VoterInfo;

public class SoundexAlg {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "root";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public static void main(String[] args)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("kamal");
			File file = new File("C:\\Users\\Client19\\Desktop\\names2.txt"); 
			File result = new File("C:\\Users\\Client19\\Desktop\\result.txt"); 
			BufferedReader br = new BufferedReader(new FileReader(file));
			BufferedWriter outWriter = new BufferedWriter(new FileWriter(result));
			List<String> namesList = new ArrayList<String>();
			List<String> resultList = new ArrayList<String>();
			String line = null; 
			StringBuilder sb = new StringBuilder();
			RefinedSoundex soundex = new RefinedSoundex();
			
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
			while((line = br.readLine()) != null)
            {
				namesList.add(line.toString());
            }
			
			int mainIndex = 0; 
			for(String name : namesList)
			{
				try{
					boolean flag = false;
					String nameSound = soundex.soundex(name.trim());
					System.out.println(nameSound);
					int subIndex = 0;
					mainIndex++;
					for(String resName:resultList)
					{
						subIndex++;
						String resSound = soundex.soundex(resName.trim());
						System.out.println("Main Index -- "+mainIndex+"\tSub Index -- "+subIndex);
						if(nameSound.equalsIgnoreCase(resSound))
						{
							System.out.println(name.trim()+" Equals ==> "+resName.trim());
							flag = true;
						}
					}
					if(!flag)
					{
						resultList.add(name.trim());
						System.out.println(name.trim());
						sb.append(name.trim()+"\n");
						insertData(name.trim());
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			br.close();
			outWriter.write(sb.toString());
			outWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertData(String str)
	{
			try{
			String insertQuery = "INSERT INTO names(name) VALUES ('"+str+"')";
			stmt.executeUpdate(insertQuery);
			}catch(Exception e)
			{
				System.out.println("Exception is -"+e);
			}
	}
}
