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
		generator.genearateSqlite("E:/KP/ghmcVoterMain.sqlite","E:/KP/Sqlites",31910,107);

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
			
			rs = stmt.executeQuery("SELECT V.voter_id,V.house_no,V.name,V.relationship_type,V.relative_name,V.gender,V.age,V.voter_id_card_no,TC.tdp_cadre_id,TC.membership_id " +
					" FROM booth_publication_voter BPV,booth B,voter V LEFT OUTER JOIN tdp_cadre TC ON V.voter_id = TC.voter_id AND TC.is_deleted = 'N' AND TC.enrollment_year = 2014 " +
					" WHERE V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND " +
					" B.ward_id = "+wardId+"  AND B.publication_date_id = 17 GROUP BY V.voter_id");
			
			connection = DriverManager.getConnection("jdbc:sqlite:"+targetFile.getAbsolutePath());
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			int count = 0;
			while(rs.next())
			{
				try{
					Integer tdpCadreId = rs.getInt(9);
					String isCadre = "N";
					String tdpCadreStr = "NULL";
					String membershipstr = "NULL";
					
					if(tdpCadreId != null && tdpCadreId > 0)
					{
						isCadre = "Y";
						tdpCadreStr = tdpCadreId.toString();
						membershipstr = "'"+rs.getString(10)+"'";
					}
						
					statement.executeUpdate("INSERT INTO voter(voter_id,house_no,name,relationship_type,relative_name,gender,age,voter_id_card_no,is_sms_sent,tdp_cadre_id,membership_id,is_cadre) VALUES (" +
							"'"+rs.getInt(1)+"','"+rs.getString(2)+"','"+rs.getString(3)+"','"+rs.getString(4)+"','"+rs.getString(5)+"','"+rs.getString(6)+"'," +
									"'"+rs.getInt(7)+"','"+rs.getString(8)+"','N',"+tdpCadreStr+","+membershipstr+",'"+isCadre+"')");
					count++;
					
					if(count%1000 == 0)
						System.out.println(count+" --> Voters Insetred in Ward -- "+wardNo);
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
			
			connection = DriverManager.getConnection("jdbc:sqlite:"+targetFile.getAbsolutePath());
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			
			statement.executeUpdate("CREATE INDEX idx_voter_voter_id ON voter(voter_id)");
			statement.executeUpdate("CREATE INDEX idx_voter_house_no ON voter(house_no)");
			statement.executeUpdate("CREATE INDEX idx_voter_name ON voter(name)");
			statement.executeUpdate("CREATE INDEX idx_voter_voter_id_card_no ON voter(voter_id_card_no)");
			statement.executeUpdate("CREATE INDEX idx_voter_age ON voter(age)");
			statement.executeUpdate("CREATE INDEX idx_voter_gender ON voter(gender)");
			statement.executeUpdate("CREATE INDEX idx_voter_tdp_cadre_id ON voter(tdp_cadre_id)");
			statement.executeUpdate("CREATE INDEX idx_voter_is_sms_sent ON voter(is_sms_sent)");
			statement.executeUpdate("CREATE INDEX idx_voter_membership_id ON voter(membership_id)");
			statement.executeUpdate("CREATE INDEX idx_voter_is_cadre ON voter(is_cadre)");
			
			statement.executeUpdate("CREATE INDEX idx_booth_publication_voter_booth_id ON booth_publication_voter(booth_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_publication_voter_voter_id ON booth_publication_voter(voter_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_publication_voter_serial_no ON booth_publication_voter(serial_no)");
			
			statement.executeUpdate("CREATE INDEX idx_booth_booth_id ON booth(booth_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_part_no ON booth(part_no)");
			statement.executeUpdate("CREATE INDEX idx_booth_part_name ON booth(part_name)");
			statement.executeUpdate("CREATE INDEX idx_booth_tehsil_id ON booth(tehsil_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_year ON booth(year)");
			statement.executeUpdate("CREATE INDEX idx_booth_constituency_id ON booth(constituency_id);");
			statement.executeUpdate("CREATE INDEX idx_booth_local_election_body_id ON booth(local_election_body_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_publication_date_id ON booth(publication_date_id)");
			statement.executeUpdate("CREATE INDEX idx_booth_ward_id ON booth(ward_id)");
			
			statement.executeUpdate("CREATE INDEX idx_user_user_id ON user(user_id)");
			statement.executeUpdate("CREATE INDEX idx_user_username ON user(username)");
			statement.executeUpdate("CREATE INDEX idx_user_password ON user(password)");
			
			statement.executeUpdate("CREATE INDEX idx_user_access_location_user_id ON user_access_location(user_id)");
			statement.executeUpdate("CREATE INDEX idx_user_access_location_location_level_id ON user_access_location(location_level_id)");
			statement.executeUpdate("CREATE INDEX idx_user_access_location_location_value ON user_access_location(location_value)");
			
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_voter_id ON voter_sms_sent(voter_id)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_ward_id ON voter_sms_sent(ward_id)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_booth_id ON voter_sms_sent(booth_id)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_mobile_no ON voter_sms_sent(mobile_no)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_rating ON voter_sms_sent(rating)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_is_synched ON voter_sms_sent(is_synched)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_sent_status ON voter_sms_sent(sent_status)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_is_voted ON voter_sms_sent(is_voted)");
			statement.executeUpdate("CREATE INDEX idx_voter_sms_sent_tdp_cadre_id ON voter_sms_sent(tdp_cadre_id)");
			
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
