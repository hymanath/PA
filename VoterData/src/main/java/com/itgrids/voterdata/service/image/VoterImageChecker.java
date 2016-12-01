package com.itgrids.voterdata.service.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoterImageChecker {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://tdpcluster-1.cluster-ch7baotjdda3.us-east-1.rds.amazonaws.com:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Rd%2dWvdiwm";
	
	static Connection conn = null;
	static Statement stmt = null;
	
	static String voterImgPath = "/mnt/tdp-img/voter_images/";
	static String previousVoterImgPath = "/mnt/tdp-img/voter_images/PUB11/";
	static String resultDir = "/mnt/tdp-img/voter_check_logs/";
	
	public static void main(String[] args)
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		stmt = conn.createStatement();
		List<Integer> cIdsList = new ArrayList<Integer>(0);
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultDir+"1U.txt"));

		/*ResultSet rs = stmt.executeQuery("select constituency_id from constituency where district_id BETWEEN 21 and 23 and " +
				" election_scope_id = 2 and deform_date is null");
		
		 while(rs.next())
			 cIdsList.add(rs.getInt("constituency_id"));*/
		cIdsList.add(102);
		cIdsList.add(10);
		cIdsList.add(11);
		cIdsList.add(12);
		cIdsList.add(13);
		cIdsList.add(15);
		cIdsList.add(16);
		cIdsList.add(18);
		cIdsList.add(1);
		cIdsList.add(20);
		 for(Integer cid : cIdsList)
			 checkVoterImage(cid,outwriter);
		 
		 outwriter.close();
		 conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void checkVoterImage(int constituencyId,BufferedWriter outwriter)
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			Map<Integer,String> previousPathMap = new HashMap<Integer,String>(0);
			
			StringBuilder sb = new StringBuilder();
			
			ResultSet rs = stmt.executeQuery("SELECT V.voter_id,B.part_no,V.voter_id_card_no FROM voter V,booth_publication_voter BPV,booth B WHERE "
					+ " V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND B.publication_date_id = 11 AND B.constituency_id = "+constituencyId
					+ " GROUP BY V.voter_id ");
			
			while(rs.next())
			 {
				 try{
					 Integer voterId = rs.getInt("voter_id");
					 String voterIdCardNo = rs.getString("voter_id_card_no");
					 String partNo = rs.getString("part_no");
					 String path = constituencyId+"/Part"+partNo+"/"+voterIdCardNo+".jpg";
					 previousPathMap.put(voterId,path);
				 }catch (Exception e) {
					e.printStackTrace();
				}
			 }
			
			rs = stmt.executeQuery("SELECT V.voter_id,V.voter_id_card_no,V.image_path,B.booth_id,B.part_no FROM voter V,booth_publication_voter BPV,booth B WHERE "
					+ " V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND B.publication_date_id = 22 AND B.constituency_id = "+constituencyId
					+ " ORDER BY B.booth_id,BPV.serial_no");
			 while(rs.next())
			 {
				 try{
				 Integer voterId = rs.getInt("voter_id");
				 String voterIdCardNo = rs.getString("voter_id_card_no");
				 String imgPath = rs.getString("image_path");
				 Integer boothId = rs.getInt("booth_id");
				 String partNo = rs.getString("part_no");
				 
				 File file = new File(voterImgPath+imgPath);
				 
				 if(!file.exists())
				 {
					 String oldPath = previousPathMap.get(voterId);
					 if(oldPath != null)
					 {
						 File oldFile = new File(previousVoterImgPath+oldPath);
						 if(oldFile.exists())
						 {
							 boolean result = oldFile.renameTo(file);
							 if(result)
								 sb.append(constituencyId+"\t"+voterId+"\t"+voterIdCardNo+"\t"+imgPath+"\t"+boothId+"\t"+partNo+"\tSuccess\t"+oldFile.getAbsolutePath()+"\t"+file.getAbsolutePath()+"\n");
							 else
								 sb.append(constituencyId+"\t"+voterId+"\t"+voterIdCardNo+"\t"+imgPath+"\t"+boothId+"\t"+partNo+"\tFail\t"+oldFile.getAbsolutePath()+"\t"+file.getAbsolutePath()+"\n");
						 }
					 }
					 else
						 sb.append(constituencyId+"\t"+voterId+"\t"+voterIdCardNo+"\t"+imgPath+"\t"+boothId+"\t"+partNo+"\n");
				 }
				 
				 }catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
			 outwriter.write(sb.toString());
			 conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

