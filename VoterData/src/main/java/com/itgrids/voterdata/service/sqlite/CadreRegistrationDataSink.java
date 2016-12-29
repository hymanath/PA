package com.itgrids.voterdata.service.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CadreRegistrationDataSink {

	public static void main(String[] args)
	{
		List<CadreRegistrationVO> list = getDataFromSqlite("D:\\Workspace\\cadre_registration\\SINK\\cadre2016_361.sqlite");
		
		for(CadreRegistrationVO VO :list)
			synkData(VO);
		
		System.out.println(list.size());
	}

	public static CadreResponseVO synkData(CadreRegistrationVO cadreRegistrationVO)
	{  
		CadreResponseVO responceVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = "http://www.mydtp.in/WebService/saveFieldDataForCadre";
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         responceVO = webResource.accept("application/json").type("application/json").post(CadreResponseVO.class,cadreRegistrationVO);
	         
	         System.out.println(responceVO.getSaveStatus());
	        
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return responceVO;    
	  }
	
public static List<CadreRegistrationVO> getDataFromSqlite(String sqlitePath)
{
	List<CadreRegistrationVO> voterList  = new ArrayList<CadreRegistrationVO>(0);
	
	try{
		
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		Statement statement = null;
		ResultSet cader = null;
		 
		connection = DriverManager.getConnection("jdbc:sqlite:"+sqlitePath);
		statement = connection.createStatement();
		
		cader = statement.executeQuery("SELECT  C.cadre_id, C.tdp_cadre_id, C.voter_id, C.family_voter_id, C.cadre_name, C.relative_name, C.relation_id,"
				+ " C.gender, C.age,C.date_of_birth, C.mobile_no,C.house_no,C.family_voter_relation_id,C.caste_state_id,C.education_id, "
				+ " C.cadre_aadhar_no,C.image_path,C.image_base64_str,C.photo_type,C.state_id,district_id,C.consttuency_id,C.tehsil_id, "
				+ " C.local_election_body_id, C.panchayat_id, C.ward_id,C.booth_id, C.nominee_name, C.nominee_gender, C.nominee_age, "
				+ " C.nominee_relation_id, C.nominee_aadhar_no, C.survey_time,C.latitude, C.longitude,C.user_id, C.tab_user_info_id, "
				+ " C.is_deleted_voter,C.sync_type,C.relative_type,C.membership_no,C.occupation_id,C.is_new_voter,C.search_type,"
				+ " C.ref_num,C.unique_id,C.is_family_voter,C.sim_serial_num,C.is_new_voter,C.ward_id  FROM cadre C "
				+ " WHERE  C.is_synched = 'false'");
		
		while(cader.next())
		{
			try{
				CadreRegistrationVO caderinfo = new CadreRegistrationVO();
			    
				caderinfo.setCadreId(cader.getLong(0+1));
                caderinfo.setTabPrimaryKey(cader.getLong(+1));
                caderinfo.setTdpCadreId(cader.getLong(1+1));
                caderinfo.setVoterId(cader.getLong(2+1));
                caderinfo.setFamilyVoterId(cader.getLong(3+1));
                caderinfo.setCadreName(cader.getString(4+1));
                caderinfo.setGender(cader.getString(7+1));
                caderinfo.setAge(cader.getLong(8+1));
                caderinfo.setDateOfBirth(cader.getString(9+1));
                caderinfo.setMobileNumber(cader.getString(10+1));
                
                try
                {
                   String hno = cader.getString(11+1).trim();
                   hno = hno.replace("\u00AD","-");
                   hno = hno.replace("\u0090","");
                   caderinfo.setHouseNo(hno);
                   System.out.println(" ----> "+hno);
                   
                   caderinfo.setFamilyRelationTypeId(cader.getLong(12+1));
                   caderinfo.setCasteStateId(cader.getLong(13+1));
                   caderinfo.setEducationId(cader.getLong(14+1));
                   caderinfo.setImagePath(cader.getString(16+1));
                   caderinfo.setImageBase64String(cader.getString(17+1));
                   caderinfo.setPhotoType(cader.getString(18+1));
                   caderinfo.setStateId(cader.getLong(19+1));
                   caderinfo.setDistrictId(cader.getLong(20+1));
                   caderinfo.setConstituencyId(cader.getLong(21+1));
                   caderinfo.setTehsilId(cader.getLong(22+1));
                   caderinfo.setLocalElectionBodyId(cader.getLong(23+1));
                   caderinfo.setPanchayatId(cader.getLong(24+1));
                   
                   try
                   {
                      Long wardId=cader.getLong(25+1);
                      if(wardId>0)
                      {
                         caderinfo.setWardId(cader.getLong(25+1));
                      }
                   }catch (Exception e)
                   {
                      e.printStackTrace();
                   }
                   
                   caderinfo.setBoothId(cader.getLong(26+1));
                   caderinfo.setNomineeName(cader.getString(27+1));
                   caderinfo.setNomineeGender(cader.getString(28+1));
                   caderinfo.setNomineeAge(cader.getLong(29+1));
                   caderinfo.setNomineeRelationId(cader.getLong(30+1));
                   caderinfo.setNomineeAadharNo(cader.getString(31+1));
                   caderinfo.setSurveyTime(cader.getString(32+1));
                   caderinfo.setLatitude(cader.getString(33+1));
                   caderinfo.setLongititude(cader.getString(34+1));
                   caderinfo.setUserId(cader.getLong(35+1));
                   caderinfo.setTabUserInfoId(cader.getLong(36+1));
                   caderinfo.setIsDeletedVoter(cader.getString(37+1));
                   caderinfo.setSyncType(cader.getString(38+1));
                   caderinfo.setRelativeType(cader.getString(39+1));
                   caderinfo.setMemberShipNo(cader.getString(40+1));
                   caderinfo.setOccupationId(cader.getLong(41+1));
                   
                   try
                   {
                      caderinfo.setSearchType(cader.getString(43+1));
                   }catch (Exception e)
                   {
                      e.printStackTrace();
                   }
                   caderinfo.setRefNo(cader.getString(44+1));
                   try
                   {
                      caderinfo.setUniqueId(cader.getString(45+1));
                   }catch (Exception e)
                   {
                      e.printStackTrace();
                   }
                   try
                   {
                      caderinfo.setIsFamilyVoter(cader.getString(46+1));
                   }catch (Exception e)
                   {
                      e.printStackTrace();
                   }
                   try
                   {
                      caderinfo.setSimSerialNo(cader.getString(47+1));
                   }catch (Exception e)
                   {
                      e.printStackTrace();
                   }
                   try
                   {
                      caderinfo.setIsNewVoter(cader.getString(48+1));
                   }catch (Exception e)
                   {
                      e.printStackTrace();
                   }
                   try
                   {
                      int ward_id=cader.getInt(49+1);
                      caderinfo.setWardId(Long.valueOf(ward_id));
                   }catch (Exception e)
                   {
                      e.printStackTrace();
                   }
                }
                catch (Exception e){
                   e.printStackTrace();
                }
                
                voterList.add(caderinfo);
                
			}//Main Block
			catch (Exception e) {
				e.printStackTrace();
			}
		}//RS
	}catch (Exception e) {
		e.printStackTrace();
	}
	return voterList;
}

}