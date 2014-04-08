package com.itgrids.voterdata.service;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.itgrids.voterdata.util.IConstants;

public class UpdateMobileNos {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://74.208.7.129:3372/dakavara_pa";
	static final String USER = "devuser";
	static final String PASS = "devuser@123";
    static Connection conn = null;
	static Statement stmt = null;
	
   public static String updateMobileNo()
	{
		Map<Long,String> availablemobileNoMap = null;
		String resultStr = "";
		Long userId = 1l;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String districtarr[] = IConstants.DISTRICT_IDS.split(",");
			
			for(String distict : districtarr)
			{
				availablemobileNoMap = new HashMap<Long, String>();
				Long districtId = Long.parseLong(distict);
				String selectQuery = " Select distinct model1.AC_NO from mobile_numbers model1 where model1.DIST_NO = "+districtId+" ";
				ResultSet rs = stmt.executeQuery(selectQuery);
				
				while(rs.next())
				{
					Long constitunecyNo = new Integer(rs.getInt("AC_NO")).longValue();

					String selectQuery1 = "Select distinct dc.constituency_id from delimitation_constituency dc,constituency c where dc.constituency_no = "+constitunecyNo+" and dc.year = 2009 and c.constituency_id = dc.constituency_id and c.deform_date is null and c.election_scope_id=2";	
					ResultSet rs1 = stmt.executeQuery(selectQuery1);
					
					while(rs1.next())
					{
						Long constituencyId = new Integer(rs1.getInt("constituency_id")).longValue();

						String selectQuery2 = "Select model1.mobile,model2.voter_id from mobile_numbers model1,voter model2,booth_publication_voter BPV, booth B where " +
								" model1.AC_NO = "+constitunecyNo+" and B.booth_id = BPV.booth_id AND BPV.voter_id = model2.voter_id AND model1.IDCARD_NO = model2.voter_id_card_no and " +
										" B.publication_date_id = 10 AND B.constituency_id = "+constituencyId;
						ResultSet rs2 = stmt.executeQuery(selectQuery2);
						
						while(rs2.next())
						{
							availablemobileNoMap.put( new Integer(rs2.getInt("voter_id")).longValue(), rs2.getString("mobile").toString())	;
						}

						List<Long> voterIds = new ArrayList<Long>(availablemobileNoMap.keySet());
						
						String selectQuery3 = " Select uvd.voter_id,uvd.mobile_no,uvd." +
								"user_voter_details_id from user_voter_details uvd where uvd.user_id = "+userId+" and uvd.voter_id in ("+voterIds+") ";	
						
						ResultSet rs3 = stmt.executeQuery(selectQuery3);
						List<Long> avilablevoterIds = new ArrayList<Long>();
						
						while(rs3.next())
						{
							String mobileNo = rs3.getString("mobile_no");
							Long userVoterDetailsId = new Integer(rs3.getInt("user_voter_details_id")).longValue();
							avilablevoterIds.add( new Integer(rs3.getInt("voter_id")).longValue());
							
							if(mobileNo == null || mobileNo.equalsIgnoreCase("N/A") || mobileNo.contains("99999") || mobileNo.contains("NA") || mobileNo.length() <= 5)
							{
								String updateQuery = "update user_voter_details set mobile_no = '"+mobileNo+"' where user_voter_details_id = "+userVoterDetailsId+"";
								stmt.executeUpdate(updateQuery);
								resultStr = "updated";
							}	
	
						}
						
						for(Map.Entry<Long,String> entry : availablemobileNoMap.entrySet())
						{
							try{
							if(!avilablevoterIds.contains(entry.getKey()))
							{
								Long voterId = entry.getKey();
								String mobileNo = entry.getValue();
								String insertQuery = "INSERT INTO user_voter_details(voter_id, constituency_id, user_id, mobile_no) VALUES ("+voterId+","+constituencyId+","+userId+",'"+mobileNo+"')";
								stmt.executeUpdate(insertQuery);
								resultStr = "inserted";
							}
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}
			}
		  }
		catch(Exception e)
		{
			System.out.println("Exception Occured in updateMobileNo()");
			System.out.println("Exception is -"+e);
		}
		finally{
    		try {
				conn.close();
			} catch (SQLException e) {
				
			}
    	}
		return resultStr;
		
	}
	
	 public static void main(String args[]) throws IOException {
		 
		 System.out.println("welcome");
		 try{
			 updateMobileNo();
		 }
		 catch(Exception e)
			{
				System.out.println("Exception Occured in main()");
				System.out.println("Exception is -"+e);
			}
	 }
}
