package com.itgrids.voterdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoterDataInsert {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL_TEMP = "jdbc:mysql://localhost/dakavara_pa";
	//static final String DB_URL_LIVE = "jdbc:mysql://199.85.212.11/dakavara_pa";
	static final String DB_URL_LIVE = "jdbc:mysql://localhost/dakavara_pa";
	static final String USER_TEMP = "root";
	static final String PASSWORD_TEMP = "root";
	
	static final String USER_LIVE = "root";
	static final String PASSWORD_LIVE = "root";
	//static final String USER_LIVE = "dakavara_pa";
	//static final String PASSWORD_LIVE = "1tGrids@123";
	
	static Connection connectionTemp = null;
	static Connection connectionLive = null;
	static Statement stmtTemp = null;
	static Statement stmtLive = null;
	
	public static void main(String[] args)
	{
		VoterDataInsert voterDataInsert = new VoterDataInsert();
		//System.out.println("----- Total Voters Inserted ----->"+voterDataInsert.insertVoterData(78, 6, 5000, 10000));
		voterDataInsert.getDetailsInAfamily(90,183);
		//voterDataInsert.getDetailsInAfamily(90,237);
		//voterDataInsert.getDetailsInAfamily(78,88);
		//voterDataInsert.getDetailsInAfamily(78,89);
	}
	
	public int insertVoterData(int constituencyId, int publicationId,int startIndex,int maxResults)
	{
		try{
			Class.forName(JDBC_DRIVER);
    		connectionTemp = DriverManager.getConnection(DB_URL_TEMP,USER_TEMP,PASSWORD_TEMP);
    		connectionLive = DriverManager.getConnection(DB_URL_LIVE,USER_LIVE,PASSWORD_LIVE);
    		
    		stmtTemp = connectionTemp.createStatement();
    		stmtLive = connectionLive.createStatement();
    		
    		ResultSet rs = stmtTemp.executeQuery(" select voter_id,name,house_no,guardian_name,relation,sex,age, " +
    				" booth_id from voter_temp where constituency_id = "+constituencyId+" limit "+startIndex+","+maxResults);
    		
    		String voterId = null;
    		String name = null;
    		String houseNo = null;
    		String relativeName = null;
    		String relationType = null;
    		String gender = null;
    		int age = 0;
    		int index = 0;
    		int index2 = 1;
    		Date timeBefore = new Date();
    		Date timeafter = null;
    		
    		List<VoterInfo> voterDeials = new ArrayList<VoterInfo>(0);
    		VoterInfo voter = null;
    		
    		System.out.println(timeBefore);
    		while(rs.next())
    		{
    			try{
    			voter = new VoterInfo();
    			voterId = rs.getString("voter_id");
    			name = rs.getString("name");
    			houseNo = rs.getString("house_no");
    			relativeName = rs.getString("guardian_name");
    			relationType = rs.getString("relation");
    			gender = rs.getString("sex");
    			age = Integer.parseInt(rs.getString("age"));
    			
    			voter.setBoothNo(rs.getString("booth_id"));
    			voter.setVoterId(voterId);
    			voterDeials.add(voter);
    			
    			if(gender.equalsIgnoreCase("Male"))
    				gender = "M";
    			else
    				gender = "F";
    			
    			String insertQuery = "INSERT INTO voter(voter_id_card_no,name,house_no,relative_name,relationship_type,gender,age,inserted_time) " +
        				" VALUES ('"+voterId+"','"+name+"','"+houseNo+"','"+relativeName+"','"+relationType+"','"+gender+"',"+age+",now())";
    			
    			stmtLive.executeUpdate(insertQuery);
    			index++;
    			System.out.println(index+")\tVoter Inserted with Voter ID - "+voterId);
    			    			
    			}catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    		
    		
    		for(VoterInfo voterInfo : voterDeials)
    		{
    			try{
    			Statement st = connectionLive.createStatement();
        		ResultSet rs2 = st.executeQuery(" select booth_id from booth where constituency_id = "+constituencyId+" and part_no = "+voterInfo.getBoothNo()+"" +
        				" and publication_date_id = "+publicationId);
        		int boothId = 0;
        		int vId = 0;
        		
        		while(rs2.next())
        		{
        			boothId = rs2.getInt("booth_id");
        		}
        		
        		Statement st2 = connectionLive.createStatement();
        		ResultSet rs3 = st2.executeQuery("select voter_id from voter where voter_id_card_no = '"+voterInfo.getVoterId()+"'");
        		
        		while(rs3.next())
        		{
        			vId = rs3.getInt("voter_id");
        		}
        		
        		String insertQuery = "INSERT INTO booth_publication_voter(booth_id,voter_id) VALUES("+boothId+","+vId+")"; 
        		Statement st4 = connectionLive.createStatement();
        		st4.executeUpdate(insertQuery);
        		index2++;
        		System.out.println(index2+")\tVoter Inserted in Booth Publication with Voter ID - "+vId);
        		        		
    			}catch (Exception e) {
        			e.printStackTrace();
        		}
    		}
    		
    		System.out.println("-------------- **************** -------------------------");
    		System.out.println("Voters inserted are --> "+index+"");
    		System.out.println("Voters inserted BP are --> "+index2+"");
    		System.out.println("-------------- **************** -------------------------");
    		
    		timeafter = new Date();
    		long diff = timeafter.getTime() - timeBefore.getTime();
    		System.out.println("Time Taken - "+diff/(1000*60));
    		
    		
			return index;
		}catch (Exception e) {
			e.printStackTrace();
    		return 0;
		}
		finally{
    		try {
    			connectionLive.close();
    			connectionTemp.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
	}
	
	public void getDetailsInAfamily(int constituencyId, int boothId)
	{
		try{
			Class.forName(JDBC_DRIVER);
    		
    		connectionLive = DriverManager.getConnection(DB_URL_LIVE,USER_LIVE,PASSWORD_LIVE);
    		
    		stmtLive = connectionLive.createStatement();
    		System.out.println();
    		
    		ResultSet rsF = stmtLive.executeQuery("select booth_id,booth_name,house_no,count(house_no) " +
    				" from voter_temp where constituency_id = '"+constituencyId+"' and booth_id = '"+boothId+"' group by house_no ");
    		
    		List<VoterFamilyInfo> resultList = new ArrayList<VoterFamilyInfo>(0);
    		
    		while(rsF.next())
    		{
    			VoterFamilyInfo voterFamilyInfo = new VoterFamilyInfo();
    			voterFamilyInfo.setBoothId(Integer.parseInt(rsF.getString("booth_id")));
    			voterFamilyInfo.setBoothName(rsF.getString("booth_name"));
    			voterFamilyInfo.setHouseNo(rsF.getString("house_no"));
    			voterFamilyInfo.setNoOfVotes(rsF.getInt(4));
    			resultList.add(voterFamilyInfo);
    		}
    		
    		for(VoterFamilyInfo info : resultList)
    		{
    			VoterInfo elderVoter = getEldestPersonDetailsInAHouse(constituencyId,boothId,info.getHouseNo());
    			info.setEldestVoterName(elderVoter.getVoterName());
    			info.setEldestVoterGender(elderVoter.getSex());
    			info.setEldestVoterAge(elderVoter.getAge());
    		}
    		
    		for(VoterFamilyInfo info : resultList)
    		{
    			VoterInfo youngVoter = getYoungestPersonDetailsInAHouse(constituencyId,boothId,info.getHouseNo());
    			info.setYoungestVoterName(youngVoter.getVoterName());
    			info.setYoungestVoterAge(youngVoter.getAge());
    			info.setYoungestVoterGender(youngVoter.getSex());
    		}
    		
    		for(VoterFamilyInfo info : resultList)
    		{
    			System.out.println();
    			System.out.print(info.getBoothId()+"\t");
    			System.out.print(info.getBoothName()+"\t");
    			System.out.print("# "+info.getHouseNo()+"\t");
    			System.out.print(info.getNoOfVotes()+"\t");
    			System.out.print(info.getEldestVoterName()+"\t");
    			System.out.print(info.getEldestVoterAge()+"\t");
    			System.out.print(info.getEldestVoterGender()+"\t");
    			System.out.print(info.getYoungestVoterName()+"\t");
    			System.out.print(info.getYoungestVoterGender()+"\t");
    			System.out.print(info.getYoungestVoterAge()+"\t");
    		}
    		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				connectionLive.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public VoterInfo getEldestPersonDetailsInAHouse(int constituencyId,int boothId,String houseNo)
	{
		VoterInfo voterInfo = new VoterInfo();
		Connection con = null;
		try{
			Class.forName(JDBC_DRIVER);
    		con = DriverManager.getConnection(DB_URL_LIVE,USER_LIVE,PASSWORD_LIVE);
    		
    		ResultSet rsE = stmtLive.executeQuery("select name,age,sex " +
    				" from voter_temp where constituency_id = '"+constituencyId+"' and booth_id = '"+boothId+"' and house_no = '"+houseNo+"'");
    		int age = 0;
    		String name = null;
    		String gender = null;
    		
    		while(rsE.next())
    		{
    			int ageTemp = Integer.parseInt(rsE.getString("age").trim());
    			if(ageTemp >= age)
    			{
    				age = ageTemp;
    				name = rsE.getString("name").trim();
    				gender = rsE.getString("sex").trim();
    			}
    		}
    		voterInfo.setVoterName(name);
    		voterInfo.setAge((new Integer(age)).toString());
    		voterInfo.setSex(gender);
    		return voterInfo;
    		
		}catch (Exception e) {
			return voterInfo;
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public VoterInfo getYoungestPersonDetailsInAHouse(int constituencyId,int boothId,String houseNo)
	{
		VoterInfo voterInfo = new VoterInfo();
		Connection con = null;
		try{
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL_LIVE,USER_LIVE,PASSWORD_LIVE);
    		
    		ResultSet rsY = stmtLive.executeQuery("select name,age,sex " +
    				" from voter_temp where constituency_id = '"+constituencyId+"' and booth_id = '"+boothId+"' and house_no = '"+houseNo+"'");
    		int age = 200;
    		String name = null;
    		String gender = null;
    		
    		while(rsY.next())
    		{
    			int ageTemp = Integer.parseInt(rsY.getString("age").trim());
    			if(ageTemp <= age)
    			{
    				age = ageTemp;
    				name = rsY.getString("name").trim();
    				gender = rsY.getString("sex").trim();
    			}
    		}
    		voterInfo.setVoterName(name);
    		voterInfo.setAge((new Integer(age)).toString());
    		voterInfo.setSex(gender);
    		return voterInfo;
    		
		}catch (Exception e) {
			return voterInfo;
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
