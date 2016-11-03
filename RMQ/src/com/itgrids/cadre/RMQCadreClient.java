package com.itgrids.cadre;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;


public class RMQCadreClient {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://10.0.3.56:3306/dakavara_pa";
	static final String USER = "root";
	static final String PASS = "Danduk1634";

	static java.sql.Connection dbConn = null;
	static Statement stmt = null;
	
	static int cadreId = 200001;
	
	public static void main(String[] args)
	{
		RMQCadreClient client = new RMQCadreClient();
		RMQCadreProducer producer = new RMQCadreProducer();
		
		/*producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("VOTER",5118500,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("VOTER",6118000,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("VOTER",7118000,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("VOTER",8118000,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("VOTER",9118000,10));*/
		
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("CADRE",108100,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("CADRE",128000,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("CADRE",148100,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("CADRE",168500,10));
		producer.sendMessagesWithReplyAck("cadre_exchange","Dandu",client.getData("CADRE",188500,10));
		
		//client.getData("VOTER",0,10);
	}
	
	public List<String> getData(String type,int minValue,int maxRecords)
	{
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			dbConn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = dbConn.createStatement();
			
			List<String> imeiset = new ArrayList<String>(0);
			imeiset.add("imei0");
			imeiset.add("imei1");
			imeiset.add("imei2");
			imeiset.add("imei3");
			imeiset.add("imei4");
 
			if(type.equalsIgnoreCase("VOTER"))
			{
				ResultSet rs = stmt.executeQuery("SELECT V.voter_id,V.voter_id_card_no,V.name,V.gender,V.age,V.house_no,V.relationship_type,V.relative_name"+ 
							" FROM booth_publication_voter BPV,booth B,voter V WHERE " +
							" BPV.booth_id = B.booth_id AND "+
							" BPV.voter_id = V.voter_id AND "+
							" B.publication_date_id = 22 "+
							" LIMIT "+minValue+","+maxRecords);
				
				String mobileNumber = "3333333333";
				String casteStateId = "285";
				String educationId = "2";
				String occupationId = "1";
				String aadharNo = "123456789012";
				String nomineeAadharNo = "345678901234";
				String photoType = "VOTER";
				
				List<String> requestData = new ArrayList<String>(0);
								
				while(rs.next())
				{
					try{
						String reqString = "{"
								+ "\"cadreId\":\""+cadreId++ +"\","
								+ "\"tdpCadreId\": \"\","
								+ "\"voterId\": \""+rs.getInt("voter_id")+"\","
								+ "\"cadreName\": \""+rs.getString("name")+"\","
								+ "\"houseNo\": \""+rs.getString("house_no")+"\","
								+ "\"gender\": \""+rs.getString("gender")+"\","
								+ "\"age\": \""+rs.getInt("age")+"\","
								+ "\"dateOfBirth\": \"1989-10-25\","
								+ "\"mobileNumber\": \""+mobileNumber+"\","
								+ "\"casteStateId\": \""+casteStateId+"\","
								+ "\"educationId\": \""+educationId+"\","
								+ "\"occupationId\": \""+occupationId+"\","
								+ "\"aadharNo\": \""+aadharNo+"\","
								+ "\"photoType\": \""+photoType+"\","
								+ "\"isDeletedVoter\": \"N\","
								+ "\"isNewVoter\": \"N\","
								+ "\"dataSourceType\": \"TAB\","
								+ "\"syncType\": \"WS\","
								+ "\"userId\": \"1\","
								+ "\"tabUserInfoId\": \"738\","
								+ "\"longititude\": \"0.0\","
								+ "\"latitude\": \"0.0\","
								+ "\"surveyTime\": \""+getCurrentDateAndTimeInStringFormat()+"\","
								+ "\"uniqueId\": \""+UUID.randomUUID().toString()+"\","
								+ "\"boothId\": \"\","
								+ "\"panchayatId\": \"\","
								+ "\"tehsilId\": \"\","
								+ "\"wardId\": \"28858\","
								+ "\"constituencyId\": \"\","
								+ "\"localElectionBodyId\": \"\","
								+ "\"districtId\":\"\","
								+ "\"stateId\":\"\","
								+ "\"relativeName\": \""+rs.getString("relative_name")+"\","
								+ "\"isFamilyVoter\": \"N\","
								+ "\"familyVoterId\": \"\","
								+ "\"familyRelationTypeId\": \"\","
								+ "\"nomineeName\": \""+rs.getString("relative_name")+"\","
								+ "\"nomineeGender\": \"M\","
								+ "\"nomineeAge\": \"56\","
								+ "\"nomineeRelationId\": \"7\","
								+ "\"nomineeAadharNo\": \""+nomineeAadharNo+"\","
								+ "\"imageBase64String\": \"\","
								+ "\"isUpdate\": \"false\","
								+ "\"refNo\": \""+new Random().nextInt(1000000000)+"\","
								+ "\"tabPrimaryKey\": \""+cadreId+"\","
								+ "\"searchType\": \"F\","
								+ "\"imei\": \""+imeiset.get(cadreId%5)+"\","
								+ "\"deviceName\": \"Freedom ABB-100-NIR\","
								+ "\"appVersion\": \"4.0\","
								+ "\"simSerialNo\": \"89914902900013909839\""
								+ "}";
						requestData.add(reqString);
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				return requestData;
			}
			
			else if(type.equalsIgnoreCase("CADRE"))
			{
				ResultSet rs = stmt.executeQuery("SELECT TC.tdp_cadre_id,TC.voter_id,TC.membership_id,TC.first_name AS cadre_name,TC.relative_name,TC.relative_type AS relation,TC.house_no,TC.image,TC.mobile_no,"+
						" TC.land_phone_no,TC.blood_group_id,TC.gender,TC.education_id,TC.occupation_id,TC.date_of_birth,TC.age,TC.caste_state_id,TC.inserted_time,"+
						" TC.update_time,TC.card_number,TC.nominee_name,TC.aadhar_no AS nominee_aadhar,TC.voter_relation_id AS nominee_relation_id,TC.nominee_age,TC.nominee_gender,"+
						" TC.photo_type,TC.cadre_aadher_no,TC.family_voterId,TC.card_no,TC.email_id,UA.state_id,UA.district_id,UA.constituency_id,UA.tehsil_id,UA.address_lane1,"+
						" UA.address_lane2,UA.street,UA.pincode,UA.local_area,UA.local_election_body AS local_election_body_id,UA.ward AS ward_id,UA.parliament_constituency_id,"+
						" UA.booth_id,UA.panchayat_id,'' AS landmark"+
						" FROM tdp_cadre TC,user_address UA,booth_publication_voter BPV,booth B "+
						" WHERE "+
						" TC.address_id = UA.user_address_id AND "+
						" TC.enrollment_year = 2014 AND TC.voter_id = BPV.voter_id AND "+ 
						" BPV.booth_id = B.booth_id AND B.publication_date_id = 22 AND"+
						" TC.is_deleted = 'N' LIMIT "+minValue+","+maxRecords);
				
				String aadharNo = "123456789012";
				String photoType = "VOTER";
				
				List<String> requestData = new ArrayList<String>(0);
				
				while(rs.next())
				{
					try{
						
						String reqString = "{"
								+ "\"cadreId\":\""+cadreId++ +"\","
								+ "\"tdpCadreId\": \""+rs.getInt("tdp_cadre_id")+"\","
								+ "\"voterId\": \""+rs.getInt("voter_id")+"\","
								+ "\"cadreName\": \""+rs.getString("cadre_name")+"\","
								+ "\"houseNo\": \""+rs.getString("house_no")+"\","
								+ "\"gender\": \""+rs.getString("gender")+"\","
								+ "\"age\": \""+rs.getInt("age")+"\","
								+ "\"dateOfBirth\": \"1989-10-25\","
								+ "\"mobileNumber\": \""+rs.getString("mobile_no")+"\","
								+ "\"casteStateId\": \""+rs.getInt("caste_state_id")+"\","
								+ "\"educationId\": \""+rs.getInt("education_id")+"\","
								+ "\"occupationId\": \""+rs.getInt("occupation_id")+"\","
								+ "\"aadharNo\": \""+aadharNo+"\","
								+ "\"photoType\": \""+photoType+"\","
								+ "\"isDeletedVoter\": \"N\","
								+ "\"isNewVoter\": \"N\","
								+ "\"dataSourceType\": \"TAB\","
								+ "\"syncType\": \"WS\","
								+ "\"userId\": \"1\","
								+ "\"tabUserInfoId\": \"738\","
								+ "\"longititude\": \"0.0\","
								+ "\"latitude\": \"0.0\","
								+ "\"surveyTime\": \""+getCurrentDateAndTimeInStringFormat()+"\","
								+ "\"uniqueId\": \""+UUID.randomUUID().toString()+"\","
								+ "\"boothId\": \"\","
								+ "\"panchayatId\": \"\","
								+ "\"tehsilId\": \"\","
								+ "\"localElectionBodyId\": \"\","
								+ "\"wardId\": \"28858\","
								+ "\"constituencyId\": \"\","
								+ "\"districtId\":\"\","
								+ "\"stateId\":\"\","
								+ "\"relativeName\": \""+rs.getString("relative_name")+"\","
								+ "\"isFamilyVoter\": \"N\","
								+ "\"familyVoterId\": \"\","
								+ "\"familyRelationTypeId\": \"\","
								+ "\"nomineeName\": \""+rs.getString("nominee_name")+"\","
								+ "\"nomineeGender\": \""+rs.getString("nominee_gender")+"\","
								+ "\"nomineeAge\": \""+rs.getString("nominee_age")+"\","
								+ "\"nomineeRelationId\": \""+rs.getString("nominee_relation_id")+"\","
								+ "\"nomineeAadharNo\": \""+rs.getString("nominee_aadhar")+"\","
								+ "\"imageBase64String\": \"\","
								+ "\"isUpdate\": \"false\","
								+ "\"refNo\": \""+new Random().nextInt(1000000000)+"\","
								+ "\"tabPrimaryKey\": \""+cadreId+"\","
								+ "\"searchType\": \"F\","
								+ "\"imei\": \""+imeiset.get(cadreId%5)+"\","
								+ "\"deviceName\": \"Freedom ABB-100-NIR\","
								+ "\"appVersion\": \"4.0\","
								+ "\"simSerialNo\": \"89914902900013909839\""
								+ "}";
						requestData.add(reqString);
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				return requestData;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCurrentDateAndTimeInStringFormat() {
		try {
			Date updatedDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
			return sdf.format(updatedDate);

		} catch (Exception e) {
			System.out.println("Exception Occured in DateUtilService.getCurrentDateAndTimeInStringFormat() "
					+ " check for log details");
			return null;
		}
	}
}
