package com.itgrids.voterdata.service.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class GhmcVoterSlipSqliteGenerator {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.3.56:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	public static void main(String[] args) {
		
		GhmcVoterSlipSqliteGenerator generator = new GhmcVoterSlipSqliteGenerator();
		generator.genearateSqlite("E:/KP/ghmcVoterMain.sqlite","E:/KP/Sqlites",31917,114);
	}
	
	public boolean genearateSqlite(String basePath,String targetFolder,int wardId,int wardNo)
	{
		try{
			Date startTime = new Date();
			ResultSet rs = null;
			Class.forName("com.mysql.jdbc.Driver");
    		conn = DriverManager.getConnection(DB_URL,USER,PASS);
    		stmt = conn.createStatement();
    		
    		Class.forName("org.sqlite.JDBC");
    		Connection connection = null;
			Statement statement = null;
			
			File targetFile = new File(targetFolder+"/"+wardNo+".sqlite");
			FileUtils.copyFile(new File(basePath), targetFile);
			
			rs = stmt.executeQuery("SELECT V.voter_id,V.house_no,V.name,V.relationship_type,V.relative_name,V.gender,V.age,V.voter_id_card_no " +
					" FROM voter V,booth_publication_voter BPV,booth B WHERE V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND " +
					" B.ward_id = "+wardId+"  AND B.publication_date_id = 17 GROUP BY V.voter_id");
			
			connection = DriverManager.getConnection("jdbc:sqlite:"+targetFile.getAbsolutePath());
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			
			while(rs.next())
			{
				try{
					statement.executeUpdate("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no,is_sms_sent) VALUES (" +
							"'"+rs.getInt(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+rs.getString(6)+"'," +
									"'"+rs.getInt(7)+"','"+rs.getString(8)+"','N')");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			rs = stmt.executeQuery("SELECT BPV.booth_publication_voter_id,BPV.booth_id,BPV.voter_id,serial_no FROM booth_publication_voter BPV,booth B WHERE BPV.booth_id = B.booth_id AND B.ward_id = "+wardId+" AND B.publication_date_id = 17");
			
			while(rs.next())
			{
				try{
					statement.executeUpdate("INSERT INTO booth_publication_voter(booth_publication_voter_id,booth_id,voter_id,serial_no) VALUES (" +
							"'"+rs.getInt(1)+"','"+rs.getInt(2)+"','"+rs.getInt(3)+"','"+rs.getInt(4)+"')");
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			connection.commit();
			statement.close();
			connection.close();
			Date endTime = new Date();
			System.out.println("Time Taken - "+(endTime.getTime() - startTime.getTime())/(1000*60)+" Minutes");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

}
