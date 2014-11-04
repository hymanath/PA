package com.itgrids.cadrecardprinting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CardPrintingDAO {
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	public static void main(String[] args)
	{
		CardPrintingDAO obj = new CardPrintingDAO();
		//obj.getAllConstituencies();
		//obj.getMandalsByConstituency(282l);
		//obj.getLocalbodysInAConstituency(281l);
		//obj.getAllPanchayatsInAMandal(1102l,11l,282l);
		// obj.getAllBoothsInPanchayat(3280l,11l);
		 obj.getAllBoothsInLocalBodies(103l);
	}
	
	public List<DataVO> getAllDistricts(){
		List<DataVO> districts = new ArrayList<DataVO>();
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Client/Desktop/189.sqlite");
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT distinct D.district_id,D.district_name FROM " +
					" District D WHERE D.state_id = 1 order by D.district_name asc ");
			//System.out.println(rs);
			while(rs.next())
			{
				int districtId = rs.getInt(1);
				String name = rs.getString(2);
				DataVO vo = new DataVO();
				vo.setId(new Long(districtId));
				vo.setName(name);
				districts.add(vo);
				//System.out.println(name.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return districts;
	}
	
	
	/* get All constituencies */
	public List<DataVO> getAllConstituencies(Long districtId)
	{
		List<DataVO> constituencies = new ArrayList<DataVO>();
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Client/Desktop/189.sqlite");
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT distinct C.constituency_id,C.constituency_name FROM " +
					" booth B,constituency C WHERE C.constituency_id = B.constituency_id AND" +
					" B.publication_date_id = 11 and C.district_id = "+districtId+" order by C.constituency_name asc");
			//System.out.println(rs);
			while(rs.next())
			{
				int constituencyId = rs.getInt(1);
				String name = rs.getString(2);
				DataVO vo = new DataVO();
				vo.setId(new Long(constituencyId));
				vo.setName(name);
				constituencies.add(vo);
				//System.out.println(name.toString());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return constituencies;
	}
	/* get All Mandals in Constituency */
	public List<DataVO> getMandalsByConstituency(Long constituencyId)
	{
		List<DataVO> mandals = new ArrayList<DataVO>();
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Client/Desktop/189.sqlite");
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT distinct T.tehsil_id,T.tehsil_name FROM " +
					" booth B,tehsil T WHERE T.tehsil_id = B.tehsil_id AND" +
					" B.constituency_id = "+constituencyId+" AND B.publication_date_id = 11" +
					" AND B.tehsil_id is not null order by T.tehsil_name  asc");
			//System.out.println(rs);
			while(rs.next())
			{
				int tehsilId = rs.getInt(1);
				String tehsilName = rs.getString(2);
				DataVO vo = new DataVO();
				vo.setId(new Long(tehsilId));
				vo.setName(tehsilName);
				mandals.add(vo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mandals;
	}
	/* get All LocalElebody in constituency */
	public  List<DataVO> getLocalbodysInAConstituency(Long constituencyId)
	{
		List<DataVO> localbodies = new ArrayList<DataVO>();
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Client/Desktop/189.sqlite");
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT distinct LEB.local_election_body_id,LEB.name FROM " +
					" booth B,local_election_body LEB WHERE LEB.local_election_body_id = B.local_election_body_id AND" +
					" B.constituency_id = "+constituencyId+" AND B.publication_date_id = 11" +
					" AND B.local_election_body_id is not null order by LEB.name asc");
			//System.out.println(rs);
			while(rs.next())
			{
				int localbodyId = rs.getInt(1);
				String localbodyName = rs.getString(2);
				DataVO vo = new DataVO();
				vo.setId(new Long(localbodyId));
				vo.setName(localbodyName);
				localbodies.add(vo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return localbodies;
	}
	
	/* get All panchhayts in Mandal */
	public  List<DataVO>  getAllPanchayatsInAMandal(Long tehsilId,Long constituencyId)
	{
		List<DataVO> pancahayats = new ArrayList<DataVO>();
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Client/Desktop/189.sqlite");
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT distinct P.panchayat_id,P.panchayat_name FROM " +
					" booth B,panchayat P WHERE P.panchayat_id = B.panchayat_id AND" +
					" B.constituency_id = "+constituencyId+" AND B.publication_date_id = 11" +
					" AND B.panchayat_id is not null order by P.panchayat_name asc");
			//System.out.println(rs);
			while(rs.next())
			{
				int panchayatId = rs.getInt(1);
				String panchayatName = rs.getString(2);
				DataVO vo = new DataVO();
				vo.setId(new Long(panchayatId));
				vo.setName(panchayatName);
				pancahayats.add(vo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return pancahayats;	
	}
	/* get All booths in Panchayat */
	public  List<DataVO> getAllBoothsInPanchayat(Long panchayatId,Long publicationId)
	{
		List<DataVO> booths = new ArrayList<DataVO>();
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Client/Desktop/189.sqlite");
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT distinct B.booth_id,B.part_no FROM " +
					" booth B,panchayat P WHERE P.panchayat_id = B.panchayat_id AND" +
					" B.panchayat_id = "+panchayatId+" AND B.publication_date_id = 11 order by B.part_no asc");
			//System.out.println(rs);
			while(rs.next())
			{
				int boothId = rs.getInt(1);
				String partNo = rs.getString(2);
				DataVO vo = new DataVO();
				vo.setId(new Long(boothId));
				vo.setName(partNo);
				booths.add(vo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return booths;		
	}
	/* get All booths in localElectionBody */
	public  List<DataVO> getAllBoothsInLocalBodies(Long localBodyId)
	{
		List<DataVO> booths = new ArrayList<DataVO>();
		try{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Client/Desktop/189.sqlite");
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT distinct B.booth_id,B.part_no FROM " +
					" booth B,local_election_body LEB WHERE LEB.local_election_body_id = B.local_election_body_id AND" +
					" B.local_election_body_id = "+localBodyId+" AND B.publication_date_id = 11 order by B.part_no asc");
			//System.out.println(rs);
			while(rs.next())
			{
				int boothId = rs.getInt(1);
				String partNo = rs.getString(2);
				DataVO vo = new DataVO();
				vo.setId(new Long(boothId));
				vo.setName(partNo);
				booths.add(vo);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return booths;		
	}
	
}
