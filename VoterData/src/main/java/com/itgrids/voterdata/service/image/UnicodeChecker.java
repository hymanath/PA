package com.itgrids.voterdata.service.image;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

public class UnicodeChecker {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://tdpcluster-1.cluster-ch7baotjdda3.us-east-1.rds.amazonaws.com:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Rd%2dWvdiwm";
	
	static Connection conn = null;
	static Statement stmt = null;
	static List<Integer> ignoreList = new ArrayList<Integer>(0);
	
	public static void main(String[] args)
	{
		ignoreList.add(8205);
		ignoreList.add(8204);
		
		String str = "కాశీంబీ  డిదస్తగిరి";
		str = str.replace(" ","");
		
		for(char c :str.toCharArray())
		{
			String hex = StringEscapeUtils.escapeJava(new Character(c).toString()).replace("\\u","");
			System.out.println("'"+c+"' "+hex+" "+Integer.parseInt(hex, 16));
		}
		System.out.println(isNameHaveSpecialChars(str));
		
		checkForSpecialChars();
	}
	
	public static void checkForSpecialChars()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			List<Integer> cIdsList = new ArrayList<Integer>(0);
			BufferedWriter outwriter = new BufferedWriter(new FileWriter("C:/Users/Administrator/Desktop/Workspace/Cadre_Image/SP3.txt"));
			
			ResultSet rs = stmt.executeQuery("select constituency_id from constituency where district_id BETWEEN 1 and 23 and " +
					" election_scope_id = 2 and deform_date is null");
			
			 while(rs.next())
				 cIdsList.add(rs.getInt("constituency_id"));
			 
			 
			 for(int cId : cIdsList)
			 {
				 try{
					 System.out.println("-->  Started Constituency - "+cId);
					 StringBuilder sb = new StringBuilder();
					 
					/* rs = stmt.executeQuery("SELECT TC.tdp_cadre_id,TC.voter_id,VA.firstname,VA.lastname,VA.relative_firstname,VA.relative_lastname "
					 		+ " FROM tdp_cadre TC,user_address UA,voter_names VA,tdp_cadre_enrollment_year EY WHERE "
					 		+ " TC.address_id = UA.user_address_id AND TC.voter_id = VA.voter_id AND TC.tdp_cadre_id = EY.tdp_cadre_id AND "
					 		+ " TC.is_deleted = 'N' AND TC.enrollment_year = 2014 AND EY.enrollment_year_id = 4 AND EY.is_deleted = 'N' AND "
					 		+ " UA.constituency_id = "+cId);*/
					 
					 rs = stmt.executeQuery("SELECT VA.voter_id,VA.firstname,VA.lastname,VA.relative_firstname,VA.relative_lastname "
					 		+ " FROM booth_publication_voter BPV,booth B,voter_names VA WHERE "
					 		+ " BPV.booth_id = B.booth_id AND BPV.voter_id = VA.voter_id AND B.publication_date_id = 22 AND B.constituency_id = "+cId);
					 
					 while(rs.next())
					 {
						 try{
							 //int tdpCadreId = rs.getInt("tdp_cadre_id"); 
							 int voterId = rs.getInt("voter_id");
							 
							 String firstName = rs.getString("firstname");
							 String lastName = rs.getString("lastname");
							 String relativeFirstname = rs.getString("relative_firstname");
							 String relativeLastname = rs.getString("relative_lastname");
							 
							 String completeName = ""; 
							 completeName += firstName != null ? firstName+" " : "";
							 completeName += lastName != null ? lastName+" " : "";
							 completeName += relativeFirstname != null ? relativeFirstname+" " : "";
							 completeName += relativeLastname != null ? relativeLastname+"" : "";
							 completeName = completeName.trim();
							 completeName = completeName.replaceAll("  "," ");
							 completeName = completeName.replaceAll("  "," ");
							 
							// System.out.println(completeName);
							 completeName = replaceIgonrableChars(completeName);
							 
							 if(isNameHaveSpecialChars(completeName))
							 {
								 //sb.append(tdpCadreId+"\t"+voterId+"\t"+completeName+"\n");
								 sb.append(voterId+"\t"+completeName+"\n");
								 System.out.println(voterId+"\t"+completeName);
							 }
							 
						 }catch (Exception e) {
							e.printStackTrace(); 
						 }
					 }
					 outwriter.write(sb.toString());
					 System.out.println("-->  End Constituency - "+cId);
				 }catch (Exception e) {
					 e.printStackTrace();
				 }
			 }
			 outwriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean isNameHaveSpecialChars(String name)
	{
		try{
			for(char c : name.replace(" ","").trim().toCharArray())
			{
				String hex = StringEscapeUtils.escapeJava(new Character(c).toString()).replace("\\u","");
				int value = Integer.parseInt(hex, 16);
				if(ignoreList.contains(value));
					
				else if(!(value >= 3072 && value <= 3199))
					return true;
			}
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public static String replaceIgonrableChars(String str)
	{
		try{
			str = str.replace(".","");
			str = str.replace(",","");
			str = str.replace("‍","");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
