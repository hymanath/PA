package com.itgrids.voterdata.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CommitteDataInsertion {


	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "root";
    static Connection conn = null;
	static Statement stmt = null;
	static Statement stmt2 = null;
	
	
	
	
	public static String insertData()
	{
		
	try{
		
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			stmt2 = conn.createStatement();
			
			
			
			/* insert data in basic Commitee 
			
			String query = "select distinct sCommitteeName,pa_committee_type_id from n_committee_member";	
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int pa_committee_type_id = rs.getInt("pa_committee_type_id");
				String updateQuery = "insert into dakavara_pa.basic_committee(name, committee_type_id) VALUES ('"+sCommitteeName+"',"+pa_committee_type_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("basic Commitee inserted.......");	
			}
			
			System.out.println("close");*/
			
			 /* insert committee Data  for state level
			
			
			/*String query = "select distinct nCommitteeID,sCommitteeName,pa_basic_committee_id,pa_commiitee_level_id,pa_commiitee_level_value from n_committee_member where pa_commiitee_level_id = 1 and pa_commiitee_level_value is not null";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int stateId = rs.getInt("pa_commiitee_level_value");
				int pa_basic_committee_id =rs.getInt("pa_basic_committee_id");
				String updateQuery = "insert into dakavara_pa.committee(name,basic_committee_id,commiitee_level_id,commiitee_level_value) VALUES ('"+sCommitteeName+"',"+pa_basic_committee_id+",1,"+stateId+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for state");	
			}*/
			
			
		/*	 insert committee Data  for District level
			
			
			
			/*String query = "select distinct nCommitteeID,sCommitteeName,pa_basic_committee_id,pa_commiitee_level_id,pa_commiitee_level_value from n_committee_member where pa_commiitee_level_id = 2 and pa_commiitee_level_value is not null";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int district_id = rs.getInt("pa_commiitee_level_value");
				int pa_basic_committee_id =rs.getInt("pa_basic_committee_id");
				String updateQuery = "insert into dakavara_pa.committee(name,basic_committee_id,commiitee_level_id, commiitee_level_value) VALUES ('"+sCommitteeName+"',"+pa_basic_committee_id+",2,"+district_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for district");	
			}*/
			
			
			
/*	 insert committee Data  for constituency level*/
			
			
			/*String query = "select distinct nCommitteeID,sCommitteeName,pa_basic_committee_id,pa_commiitee_level_id,pa_commiitee_level_value from n_committee_member where pa_commiitee_level_id = 3 and pa_commiitee_level_value is not null";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int constituency_id = rs.getInt("pa_commiitee_level_value");
				int pa_basic_committee_id =rs.getInt("pa_basic_committee_id");
				String updateQuery = "insert into dakavara_pa.committee(name,basic_committee_id,commiitee_level_id, commiitee_level_value) VALUES ('"+sCommitteeName+"',"+pa_basic_committee_id+",3,"+constituency_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for constituency");	
			}*/
			
/*	 insert committee Data  for parliament constituency level 
			
	
			/*String query = "select distinct nCommitteeID,sCommitteeName,pa_basic_committee_id,pa_commiitee_level_id,pa_commiitee_level_value from n_committee_member where pa_commiitee_level_id = 4 and pa_commiitee_level_value is not null";	
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int parliament_constituency_id = rs.getInt("pa_commiitee_level_value");
				int pa_basic_committee_id =rs.getInt("pa_basic_committee_id");
				String updateQuery = "insert into dakavara_pa.committee(name,basic_committee_id,commiitee_level_id,commiitee_level_value) VALUES ('"+sCommitteeName+"',"+pa_basic_committee_id+",4,"+parliament_constituency_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for parliament constituency");	
			}*/
			
		
			
/*			
	 insert committee Data  for mandal level 
			
		
			/*String query = "select distinct nCommitteeID,sCommitteeName,pa_basic_committee_id,pa_commiitee_level_id,pa_commiitee_level_value from n_committee_member where pa_commiitee_level_id = 5 and pa_commiitee_level_value is not null";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int tehsil_id = rs.getInt("pa_commiitee_level_value");
				int pa_basic_committee_id =rs.getInt("pa_basic_committee_id");
				String updateQuery = "insert into dakavara_pa.committee(name,basic_committee_id,commiitee_level_id, commiitee_level_value) VALUES ('"+sCommitteeName+"',"+pa_basic_committee_id+",5,"+tehsil_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for mandal");	
			}*/
			
			
/*			
			
	 insert committee Data  for panchayat level */
			
		
			/*String query = "select distinct nCommitteeID,sCommitteeName,pa_basic_committee_id,pa_commiitee_level_id,pa_commiitee_level_value from n_committee_member where pa_commiitee_level_id = 6 and pa_commiitee_level_value is not null";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int panchayat_id = rs.getInt("pa_commiitee_level_value");
				int pa_basic_committee_id =rs.getInt("pa_basic_committee_id");
				String updateQuery = "insert into dakavara_pa.committee(name,basic_committee_id,commiitee_level_id, commiitee_level_value) VALUES ('"+sCommitteeName+"',"+pa_basic_committee_id+",6,"+panchayat_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for constituency");	
			}*/
/*			
	 insert committee Data  for booth level 
			
			/*String query = "select distinct nCommitteeID,sCommitteeName,pa_basic_committee_id,pa_commiitee_level_id,pa_commiitee_level_value from n_committee_member where pa_commiitee_level_id = 7 and pa_commiitee_level_value is not null";
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				String sCommitteeName = rs.getString("sCommitteeName");
				int booth_id = rs.getInt("pa_commiitee_level_value");
				String updateQuery = "insert into dakavara_pa.committee(name,basic_committee_id,commiitee_level_id, commiitee_level_value) VALUES ('"+sCommitteeName+"',"+pa_basic_committee_id+",7,"+booth_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for constituency");	
			}	
			System.out.println("completed inserted for constituency");*/
			
		
			
			/* insert committee_role data */
			/*String query = "select distinct pa_committee_id,pa_role_id from n_committee_member where pa_committee_id is not null";	
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next())
			{
				
				int pa_committee_id = rs.getInt("pa_committee_id");
				int pa_role_id = rs.getInt("pa_role_id");
				String updateQuery = "insert into dakavara_pa.committee_role(committee_id, role_id) VALUES ("+pa_committee_id+","+pa_role_id+")";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committe inserted for constituency");	
			}	
			System.out.println("completed inserted for committee_role...");*/
			
			

			/* insert committee_member data 
			String query = "select distinct c.cadre_id,CR.committee_role_id from n_committee_member CM,committee_role CR,Cadre c where CM.pa_committee_id = CR.committee_id and" +
					" CM.pa_role_id = CR.role_id and c.member_id = CM.nMemberID";	
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				
				int cadre_id = rs.getInt("c.cadre_id");
				int committee_role_id = rs.getInt("CR.committee_role_id");
				String isActive = "Y";
				String updateQuery = "insert into committee_member(committee_role_id,cadre_id,is_active) values ("+committee_role_id+","+cadre_id+",'Y')";
				stmt2.executeUpdate(updateQuery);
				System.out.println("committee_member inserted for constituency");	
			}	
			System.out.println("completed inserted for committee_member...");*/
			//System.out.println("completed");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	return null;
		
	}
	
	public static void main(String[] args) {
		System.out.println("welcome");
		try{
		insertData();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
