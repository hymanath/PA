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

public class SpecialCharacterChecker {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://tdpcluster-1.cluster-ch7baotjdda3.us-east-1.rds.amazonaws.com:3306/dakavara_pa";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Rd%2dWvdiwm";
	//static final String PASS = "root";
	
	static Connection conn = null;
	static Statement stmt = null;
	static List<Integer> ignoreList = new ArrayList<Integer>(0);
	
	public static void main(String[] args)
	{
		ignoreList.add(8205);
		ignoreList.add(8204);
		
		String str = "à°œà°¿.à°¸à±�à°§à°¾à°•à°°à±�";
		str = str.replace(" ","");
		
		/*for(char c :str.toCharArray())
		{
			String hex = StringEscapeUtils.escapeJava(new Character(c).toString()).replace("\\u","");
			System.out.println("'"+c+"' "+hex+" "+Integer.parseInt(hex, 16));
		}*/
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
			BufferedWriter outwriter = new BufferedWriter(new FileWriter("C:/Users/Administrator/Desktop/Workspace/Cadre_Image/SPD_SET-8.txt"));
			
			ResultSet rs = stmt.executeQuery("select constituency_id from constituency where district_id between 20 and 23 and constituency_id <> 282 and election_scope_id = 2 and deform_date is null");
			
			 while(rs.next())
				 cIdsList.add(rs.getInt("constituency_id"));
			 
			 for(int cId : cIdsList)
			 {
				 try{
					 System.out.println("-->  Started Constituency - "+cId);
					 StringBuilder sb = new StringBuilder();
					 
					// rs = stmt.executeQuery("SELECT tdp_cadre_card_print_id,cadre_name as print_string FROM tdp_cadre_card_print WHERE constituency_id = "+cId);
					 rs = stmt.executeQuery("SELECT panchayat_id as tdp_cadre_card_print_id,TRIM(CONCAT(IFNULL(mandal_name,''),' ',IFNULL(panchayat_name,''))) as print_string FROM "
							+ " tdp_cadre_card_print WHERE panchayat_id is not null and constituency_id = "+cId+" GROUP BY panchayat_id");
					/* rs = stmt.executeQuery("SELECT tdp_cadre_card_print_id,CONCAT(IFNULL(cadre_name,''),' ',IFNULL(district_name,''),' ',IFNULL(constituency_name,''),' ',"
					 		+ " IFNULL(mandal_name,''),' ',IFNULL(panchayat_name,''),' ',IFNULL(muncipality_name,'')) AS print_string FROM tdp_cadre_card_print  WHERE constituency_id = "+cId);*/
							
					 /*rs = stmt.executeQuery("SELECT VA.voter_id AS tdp_cadre_card_print_id,CONCAT(VA.firstname,' ',VA.lastname) AS print_string FROM tdp_cadre_enrollment_year EY,user_address UA,tdp_cadre TC,constituency C,voter_names VA "
					 		+ " WHERE TC.tdp_cadre_id = EY.tdp_cadre_id AND TC.address_id = UA.user_address_id AND UA.constituency_id = C.constituency_id AND TC.voter_id = VA.voter_id AND TC.is_deleted = 'N' AND EY.is_deleted = 'N' AND "
					 		+ " TC.enrollment_year = 2014 AND EY.enrollment_year_id = 4 AND TC.data_source_type = 'TAB' AND TC.cadre_verification_status_id = 1 AND TC.card_print_status_id = 2 AND (TC.is_csd IS NULL OR TC.is_csd = 'N') AND "
					 		+ " C.constituency_id = "+cId);*/
					 
					 while(rs.next())
					 {
						 try{
							 int tdpCadreCardPrintId = rs.getInt("tdp_cadre_card_print_id");
							 String printString = rs.getString("print_string");
							 printString = printString.trim();
							 printString = printString.replaceAll("  "," ");
							 printString = printString.replaceAll("  "," ");
							 printString = printString.replaceAll("  "," ");
							 
							// System.out.println(completeName);
							 printString = replaceIgonrableChars(printString);
							 
							 if(isNameHaveSpecialChars(printString))
							 {
								 sb.append(tdpCadreCardPrintId+"\t"+printString+"\n");
								 System.out.println(tdpCadreCardPrintId+"\t"+printString);
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
			name = name.replace(".","");
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
			//str = str.replace(",","");
			str = str.replace("â€�","");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
