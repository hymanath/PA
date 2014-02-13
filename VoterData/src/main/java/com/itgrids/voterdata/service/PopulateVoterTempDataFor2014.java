package com.itgrids.voterdata.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itgrids.voterdata.VO.VoterInfo;
import com.itgrids.voterdata.util.IConstants;

public class PopulateVoterTempDataFor2014 {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/"+IConstants.DB_NAME;
	static final String INSERT_DB_URL = "jdbc:mysql://localhost/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "root";
	
	static Connection connRead = null;
	static Statement stmtRead = null;
	static Connection connWrite = null;
	static Statement stmtWrite = null;
	
	public static void main(String[] args)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        connRead = DriverManager.getConnection(DB_URL,USER,PASS);
	        connWrite = DriverManager.getConnection(INSERT_DB_URL,USER,PASS);
	        stmtRead = connRead.createStatement();
	        stmtWrite = connWrite.createStatement();
	        
	        List<VoterInfo> votersList = new ArrayList<VoterInfo>(0);
	        List<VoterInfo> newVotersList = new ArrayList<VoterInfo>(0);
	        List<VoterInfo> deletedVotersList = new ArrayList<VoterInfo>(0);
	        
	        VoterInfo voter = null;
	        Map<String,String> sexMap = new HashMap<String, String>(0);
	        Map<String,String> relationMap = new HashMap<String, String>(0);
	        
	        sexMap.put("M","Male");
	        sexMap.put("F","Female");
	        sexMap.put("-","Male");
	        sexMap.put("O","Male");
	        
	        relationMap.put("H","Husband");
	        relationMap.put("F","Father");
	        relationMap.put("M","Mother");
	        relationMap.put("O","Other");
	        relationMap.put("-","Other");
	        
	        int voterDelCount = stmtWrite.executeUpdate("delete from voter_temp");
	        System.out.println("No of Records Deleted --> "+voterDelCount);
	        
	        int modVoterDelCount = stmtWrite.executeUpdate("delete from voter_modification_temp");
	        System.out.println("No of Records Deleted --> "+modVoterDelCount);
	        
	        ResultSet rs = stmtRead.executeQuery("SELECT IDCARD_NO,FM_NAME,LASTNAME,SEX,AGE,HOUSE_NO,RLN_FM_NM,RLN_L_NM,RLN_TYPE," +
	        		"Ac_No,PART_NO,slnoinpart from data where statustype = 'N'");
            
	        while(rs.next())
	        {
	        	try{
	        		voter = new VoterInfo();
	        		String name = (rs.getString("FM_NAME")+" "+rs.getString("LASTNAME")).trim();
	        		String guardianName = (rs.getString("RLN_FM_NM")+" "+rs.getString("RLN_L_NM")).trim();
	        		voter.setVoterId(rs.getString("IDCARD_NO"));
	        		voter.setVoterName(name);
	        		voter.setSex(sexMap.get(rs.getString("SEX").toUpperCase()));
	        		voter.setAge(Integer.valueOf(rs.getInt("AGE")).toString());
	        		voter.setHouseNumber(rs.getString("HOUSE_NO"));
	        		voter.setGuardianName(guardianName);
	        		voter.setGuardianRelation(relationMap.get(rs.getString("RLN_TYPE").toUpperCase()));
	        		voter.setConstituencyId(Integer.valueOf(rs.getInt("Ac_No")).toString());
	        		voter.setConstituency(IConstants.CONSTITUENCY_NAME);
	        		voter.setBoothNo(Integer.valueOf(rs.getInt("PART_NO")).toString());
	        		voter.setBoothName(IConstants.CONSTITUENCY_NAME);
	        		voter.setsNo(Integer.valueOf(rs.getInt("slnoinpart")).longValue());
	        		if(voter.getSex() == null)
	        			voter.setSex("Male");
	        		votersList.add(voter);
	        	}catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}
	        }
	        
	        if(votersList.size() > 0)
	        {
	        	ReadVoterDataFromPdfForAP2014.saveVotersData(votersList);
	        	System.out.println("Total No Of Voters --> "+votersList.size());
	        }
	        
	        ResultSet rsAdd = stmtRead.executeQuery("SELECT IDCARD_NO,Ac_No,PART_NO from data where statustype = 'N' and Orgn_type = 'F6'");
	        
	        while(rsAdd.next())
	        {
	        	try{
	        		voter = new VoterInfo();
	        		voter.setBoothNo(Integer.valueOf(rsAdd.getInt("PART_NO")).toString());
	        		voter.setBoothName(IConstants.CONSTITUENCY_NAME);
	        		voter.setConstituencyId(Integer.valueOf(rsAdd.getInt("Ac_No")).toString());
	        		voter.setConstituency(IConstants.CONSTITUENCY_NAME);
	        		voter.setVoterId(rsAdd.getString("IDCARD_NO"));
	        		newVotersList.add(voter);
	        	}catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}
	        }
	        
	        if(newVotersList.size() > 0)
	        {
	        	VotersModificationReader2014.saveVotersInModificationTempTable(newVotersList,IConstants.ADDED);
	        	System.out.println("Total Added Voters --> "+newVotersList.size());
	        }
	        
	        ResultSet rsDel = stmtRead.executeQuery("SELECT IDCARD_NO,Ac_No,PART_NO from data where statustype = 'D'");
	        
	        while(rsDel.next())
	        {
	        	try{
	        		voter = new VoterInfo();
	        		voter.setBoothNo(Integer.valueOf(rsDel.getInt("PART_NO")).toString());
	        		voter.setBoothName(IConstants.CONSTITUENCY_NAME);
	        		voter.setConstituencyId(Integer.valueOf(rsDel.getInt("Ac_No")).toString());
	        		voter.setConstituency(IConstants.CONSTITUENCY_NAME);
	        		voter.setVoterId(rsDel.getString("IDCARD_NO"));
	        		deletedVotersList.add(voter);
	        	}catch(Exception e)
	        	{
	        		e.printStackTrace();
	        	}
	        }
	        
	        if(deletedVotersList.size() > 0)
	        {
	        	VotersModificationReader2014.saveVotersInModificationTempTable(deletedVotersList,IConstants.DELETED);
	        	System.out.println("Total Deleted Voters -->"+deletedVotersList.size());
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
