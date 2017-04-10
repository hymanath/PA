package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dto.CadreDataVO;
import com.itgrids.partyanalyst.dto.CadreResponseVO;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class CadreRegistrationDataSink {

	static DateUtilService dateUtilService = new DateUtilService();
	
	public static void main(String[] args)
	{
		
		File mainDir = new File("D:/SHARE/TS/");
		
		if(mainDir.isDirectory())
		{
			for(File districtDir : mainDir.listFiles())
			{
				try{
					if(districtDir.isDirectory())
					{
						for(File constituencyDir : districtDir.listFiles())
						{
							try{
								if(constituencyDir.isDirectory())
								{
									for(File sqlDir : constituencyDir.listFiles())
									{
										try{
											if(sqlDir.isDirectory())
											{
												for(File sqlite : sqlDir.listFiles())
												{
													try{
														System.out.println("Syncing --> "+sqlite.getAbsolutePath());
														
														List<CadreDataVO> recordsList = getDataFromSqlite(sqlite.getAbsolutePath());
														System.out.println(recordsList.size());
														
														for(CadreDataVO VO :recordsList)
														{
															try{
																Long familyVoterId = null;
																
																if( (VO.getVoterId() == null || VO.getVoterId().longValue() == 0) && 
																		(VO.getFamilyVoterId() == null || VO.getFamilyVoterId().longValue() == 0))
																{
																	familyVoterId = getFamilyVoterId(sqlite.getAbsolutePath(),VO.getCadreId());
																	
																	if(familyVoterId != null)
																		VO.setFamilyVoterId(familyVoterId);
																}
																CadreResponseVO cadreResponseVO = synkData(VO);
																
																if(cadreResponseVO.getSaveStatus().equalsIgnoreCase("Success"))
																	updateResponse(cadreResponseVO,sqlite.getAbsolutePath());
															}catch(Exception e)
															{
																e.printStackTrace();
															}
														}
														
														
													}catch(Exception e)
													{
														e.printStackTrace();
													}
												}
											}
										}catch(Exception e)
										{
											e.printStackTrace();
										}
									}
								}
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public static CadreResponseVO synkData(CadreDataVO cadreRegistrationVO)
	{  
		CadreResponseVO responceVO = null;
	    try {
	         ClientConfig clientConfig = new DefaultClientConfig();
	         
	         clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	         Client client = Client.create(clientConfig);
	       
	         String webServiceUrl  = "http://kafka-866687595.us-east-1.elb.amazonaws.com/WebService/saveFieldDataForCadre";
	           
	         WebResource webResource = client.resource( webServiceUrl );
	           
	         responceVO = webResource.accept("application/json").type("application/json").post(CadreResponseVO.class,cadreRegistrationVO);
	         
	         System.out.println(responceVO.getSaveStatus());
	        
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return responceVO;    
	  }
	
	  public static void updateResponse(CadreResponseVO cadreResponseVO,String sqlitePath)
	  {
		  try{
			  Class.forName("org.sqlite.JDBC");
				Connection connection = null;
				Statement statement = null;
				 
				connection = DriverManager.getConnection("jdbc:sqlite:"+sqlitePath);
				statement = connection.createStatement();
				
				int count = statement.executeUpdate("UPDATE cadre SET is_synched = 'true', sync_type = 'WS', sync_time= '"+dateUtilService.getCurrentDateAndTimeInStringFormat()+"', " +
						" server_tdp_cadre_id = '"+cadreResponseVO.getTdpCadreId()+"' WHERE cadre_id = '"+cadreResponseVO.getTabPrimaryKey()+"'");
				System.out.println(count);
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  
	  }
	  
	  public static Long getFamilyVoterId(String sqlitePath,Long cadreId)
	  {
		  Long voterId = null;
		  try{
			  Class.forName("org.sqlite.JDBC");
				Connection connection = null;
				Statement statement = null;
				ResultSet rs = null;
				 
				connection = DriverManager.getConnection("jdbc:sqlite:"+sqlitePath);
				statement = connection.createStatement();
				
				rs = statement.executeQuery("SELECT voter_id FROM cadre WHERE cadre_id < "+cadreId+" AND voter_id IS NOT NULL ORDER BY cadre_id DESC LIMIT 1");
				
				while(rs.next())
				{
					voterId = rs.getLong("voter_id");
				}
				
		  }catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  return voterId;
	  }
	
public static List<CadreDataVO> getDataFromSqlite(String sqlitePath)
{
	List<CadreDataVO> voterList  = new ArrayList<CadreDataVO>(0);
	
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
				CadreDataVO caderinfo = new CadreDataVO();
			    
				caderinfo.setCadreId(cader.getLong(0+1));
                caderinfo.setTabPrimaryKey(cader.getLong(0+1));
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
                
                caderinfo.setImei("1234567890");
                caderinfo.setDeviceName("Freedom ABB-100-NIR");
                caderinfo.setAppVersion("5.0");
                caderinfo.setSyncType("WS");
                
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