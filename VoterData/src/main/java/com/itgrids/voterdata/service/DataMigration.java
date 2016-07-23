package com.itgrids.voterdata.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataMigration {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL1 = "jdbc:mysql://192.168.11.4:3306/community_news?autoReconnect=true";
	static final String USER1 = "root";
	static final String PASS1 = "itg$36593^";
	
	static final String DB_URL2 = "jdbc:mysql://192.168.11.4:3306/cnp3?autoReconnect=true";
	static final String USER2 = "root";
	static final String PASS2 = "itg$36593^";
	
	static Connection conn1 = null;
	static Statement stmt1 = null;
	static Connection conn2 = null;
	static Statement stmt2 = null;
	
	public static void startMigration(int startIndex,int maxCount)
	{
		try{
			conn1 = DriverManager.getConnection(DB_URL1,USER1,PASS1);
    		stmt1 = conn1.createStatement();
			ResultSet rs = stmt1.executeQuery("SELECT DISTINCT(article_id) FROM candidate_party_article limit "+startIndex+","+maxCount);
			List<Integer> articlesList = new ArrayList<Integer>(0);
			
			while(rs.next())
			{
				articlesList.add(rs.getInt(1));
			}
			
			for(Integer articleId : articlesList)
			{
				try{
					rs = stmt1.executeQuery("select candidate_party_article_id,source_candidate_id,destination_candidate_id,source_party_id," +
							"destination_party_id,article_id,media_id,source_benefit_id,destination_benefit_id,created_date,updated_date," +
							"for_source_category,source_state_id,destination_state_id from candidate_party_article where article_id = "+articleId);
					while(rs.next())
					{
						
					}
				}catch(Exception e)
				{
					System.out.println("Exception Occured at : "+articleId);
					e.printStackTrace();
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
