package com.itgrids.partyanalyst.service.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.service.ICadreDetailsService;

public class CadreDetailsService implements ICadreDetailsService{

	private final static Logger LOG =  Logger.getLogger(CadreDetailsService.class);
	public ITdpCadreDAO tdpCadreDAO;


	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}


	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}


	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex)
	{
		TdpCadreVO returnVO = new TdpCadreVO();
    	try {
    		
    		StringBuilder queryStr = new StringBuilder();
    		
    		if(locationLevel != null && locationLevel.longValue() != 0L && locationValue != null && locationValue.longValue() != 0L)
    		{
    			
    			if(locationLevel.longValue() == 2L)
    			{
    				if(locationValue.longValue() == 1l)
    				queryStr.append(" and model.userAddress.district.districtId between 11 and 23 ");
    				else if(locationValue.longValue() == 2l)
    					queryStr.append(" and model.userAddress.district.districtId between 1 and 10 ");	
    				else
    					queryStr.append(" and model.userAddress.district.districtId between 1 and 23 ");	
    				locationValue = 0l;
    			}
    			if(locationLevel.longValue() == 3L)
    			{
    				queryStr.append(" and model.userAddress.district.districtId =:locationValue ");
    			}
    			if(locationLevel.longValue() == 2L)
    			{
    				queryStr.append(" and model.userAddress.district.districtId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 4L)
    			{
    				queryStr.append(" and model.userAddress.constituency.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 5L)
    			{
    				queryStr.append(" and model.userAddress.tehsil.tehsilId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 6L)
    			{
    				queryStr.append(" and model.userAddress.panchayat.panchayatId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 7L)
    			{
    				queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 8L)
    			{
    				queryStr.append(" and model.userAddress.ward.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 9L)
    			{
    				queryStr.append(" and model.userAddress.booth.boothId =:locationValue ");
    			}
    		}
    		
    		if(searchName != null && searchName.trim().length()>0 && !searchName.trim().equalsIgnoreCase("0") && !searchName.equalsIgnoreCase("null"))
			{
				queryStr.append(" and model.firstname like '%"+searchName+"%' ");
			}
    		
			if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.memberShipNo like '%"+memberShipCardNo.trim()+"') ");
			}
			if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
			{							
				queryStr.append(" and (model.mobileNo like '%"+mobileNo.trim()+"%') ");
			}
			if(voterCardNo != null && voterCardNo.trim().length()>0  && !voterCardNo.trim().equalsIgnoreCase("0") && !voterCardNo.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.voter.voterIDCardNo like '%"+voterCardNo.trim()+"%' or (familyVoter.voterId is not null and familyVoter.voterIDCardNo like '%"+voterCardNo.trim()+"%'))  ");
			}
			if(trNumber != null && trNumber.trim().length()>0 && !trNumber.trim().equalsIgnoreCase("0") && !trNumber.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.refNo like '%"+trNumber.trim()+"%') ");
			}
			if(casteStateId != null && casteStateId.toString().trim().length()>0 && !casteStateId.toString().trim().equalsIgnoreCase("0") && !casteStateId.toString().equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteStateId = :casteStateId ");
			}
			if(casteCategory != null && casteCategory.trim().length()>0 && !casteCategory.trim().equalsIgnoreCase("0") && !casteCategory.equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteCategoryGroup.casteCategoryGroupName like '%"+casteCategory+"%'");
			}			
			if((fromAge != null && fromAge.longValue()!=0L ) && (toAge != null && toAge.longValue() !=0L))
			{
				queryStr.append(" and model.age >="+fromAge+" and model.age <= "+toAge+"");
			}
			if(gender != null && gender.trim().length()>0)
			{
				if(gender.trim().equalsIgnoreCase("Male") || gender.trim().equalsIgnoreCase("M"))
				{
					queryStr.append(" and (model.gender like 'Male' or model.gender like 'M')");
				}
				if(gender.trim().equalsIgnoreCase("Female") || gender.trim().equalsIgnoreCase("F"))
				{
					queryStr.append(" and (model.gender like 'Female' or model.gender like 'F')");
				}
			}
			if(queryStr != null && queryStr.toString().trim().length()>0)
			{
				List<Object[]> cadreList = tdpCadreDAO.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString(),startIndex,maxIndex);
				
				List<TdpCadreVO> returnLsit = new ArrayList<TdpCadreVO>();
				
				if(cadreList != null && cadreList.size()>0)
				{
					SimpleDateFormat format  = new SimpleDateFormat("yy-MM-dd");
					for (Object[] cadre : cadreList) 
					{
						TdpCadreVO cadreVO = new TdpCadreVO();

						cadreVO.setId(cadre[0] != null ? Long.valueOf(cadre[0].toString().trim()):0L);
						cadreVO.setCadreName(cadre[1] != null ? cadre[1].toString():"");
						cadreVO.setRelativeName(cadre[2] != null ? cadre[2].toString():"");
						cadreVO.setGender(cadre[3] != null ? cadre[3].toString():"");
						cadreVO.setMemberShipNo(cadre[4] != null ? cadre[4].toString().substring(4):"");
						cadreVO.setTrNo(cadre[5] != null ? cadre[5].toString():"");
						cadreVO.setMobileNo(cadre[6] != null ? cadre[6].toString():"");
						cadreVO.setImageURL(cadre[7] != null ? cadre[7].toString():"");
						
						if(cadre[9] != null)
						{
							cadreVO.setAge(cadre[9] != null ? Long.valueOf(cadre[9].toString().trim()):0L);
						}
						else if((cadreVO.getAge() == null || cadreVO.getAge().toString().trim().length()<=0) && cadre[10]  != null)
						{
							String dateOfBirth = 	cadre[10] != null ? cadre[10].toString().substring(0,10):" "	;
							if(dateOfBirth != null && dateOfBirth.trim().length()>0)
							{
								Calendar startDate = new GregorianCalendar();
								Calendar endDate = new GregorianCalendar();
								
								startDate.setTime(format.parse(dateOfBirth.trim()));
								
								endDate.setTime(new Date());

								int diffYear = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
								
								cadreVO.setAge(Long.valueOf(String.valueOf(diffYear)));
							}
						}
						
						cadreVO.setConstituency(cadre[11] != null ? cadre[11].toString().trim():"");
						
						if(cadreVO.getAge() != null && cadreVO.getAge().toString().trim().length()==0)
						{
							cadreVO.setAge(cadre[12] != null ? Long.valueOf(cadre[12].toString().trim()):0L);
						}
						
						cadreVO.setOccupation(cadre[13] != null ? cadre[13].toString().trim():"");
						cadreVO.setTehsil(cadre[14] != null ? cadre[14].toString().trim()+" Mandal":"");
						cadreVO.setPanchayat(cadre[15] != null ? cadre[15].toString().trim():"");
						
						String electionType = cadre[20] != null ? cadre[20].toString().trim():""; // municipality/corporation/ghmc....
						cadreVO.setLocalElectionBody(cadre[16] != null ? cadre[16].toString().trim()+" "+electionType:"");
						
						cadreVO.setDistrict(cadre[17] != null ? cadre[17].toString().trim():"");
						cadreVO.setCasteName(cadre[18] != null ? cadre[18].toString().trim():"");
						cadreVO.setVoterCardNo(cadre[19] != null ? cadre[19].toString().trim():"");
						
						cadreVO.setHouseNo(cadre[21] != null ? cadre[21].toString().trim():"");
						cadreVO.setConstituencyId(cadre[22] != null ? Long.valueOf(cadre[22].toString().trim()):0L);
						cadreVO.setTehsilId(cadre[23] != null ? Long.valueOf(cadre[23].toString().trim()):0L);
						cadreVO.setPanchayatId(cadre[24] != null ? Long.valueOf(cadre[24].toString().trim()):0L);
						cadreVO.setLocalElectionBodyId(cadre[25] != null ? Long.valueOf(cadre[25].toString().trim()):0L);				
						cadreVO.setDistrictId(cadre[26] != null ? Long.valueOf(cadre[26].toString().trim()):0L);		
						cadreVO.setAadharNo(cadre[28] != null ? cadre[28].toString().trim():"");
						
						returnLsit.add(cadreVO);
					}
					
					returnVO.setResponseStatus("SUCCESS");					
					returnVO.setTotalCount(Long.valueOf(String.valueOf(returnLsit.size())));
					returnVO.setTdpCadreDetailsList(returnLsit);
					if(returnLsit != null && maxIndex != 0)
					{
					List<Object[]> cadreListCnt = tdpCadreDAO.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString(),0,0);
					returnLsit.get(0).setTotalCount(new Long(cadreListCnt.size()));
					}
				}
				else
				{
					if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
					{
						returnVO.setResponseStatus(" No Cadre information is available with this Search details...");
					}					
					else if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
					{	
						returnVO.setResponseStatus(mobileNo+" Mobile Number is not Registered for any Cadre...");
					}
				}
				returnVO.setResponseCode("");
			}
			else
			{
				returnVO.setResponseStatus("FAILURE");
				returnVO.setResponseCode("Atleast one Attribute is Required...");
			}    		
    	} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCommitte  method in CadreDetailsService.",e);
			returnVO.setResponseStatus("FAILURE");
			returnVO.setResponseCode("SERVER ISSUE");			
		}
    	
    	return returnVO;
	}
	
	public TdpCadreVO tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender)
	{
		TdpCadreVO returnVO = new TdpCadreVO();
    	try {
    		
    		StringBuilder queryStr = new StringBuilder();
    		
    		if(locationLevel != null && locationLevel.longValue() != 0L && locationValue != null && locationValue.longValue() != 0L)
    		{
    			if(locationLevel.longValue() == 2L)
    			{
    				queryStr.append(" and model.userAddress.district.districtId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 4L)
    			{
    				queryStr.append(" and model.userAddress.constituency.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 5L)
    			{
    				queryStr.append(" and model.userAddress.tehsil.tehsilId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 6L)
    			{
    				queryStr.append(" and model.userAddress.panchayat.panchayatId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 7L)
    			{
    				queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 8L)
    			{
    				queryStr.append(" and model.userAddress.ward.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 9L)
    			{
    				queryStr.append(" and model.userAddress.booth.boothId =:locationValue ");
    			}
    		}
    		
    		if(searchName != null && searchName.trim().length()>0 && !searchName.trim().equalsIgnoreCase("0") && !searchName.equalsIgnoreCase("null"))
			{
				queryStr.append(" and model.firstname like '%"+searchName+"%' ");
			}						
			if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.memberShipNo like '%"+memberShipCardNo.trim()+"') ");
			}
			if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
			{							
				queryStr.append(" and (model.mobileNo like '%"+mobileNo.trim()+"%') ");
			}
			if(voterCardNo != null && voterCardNo.trim().length()>0  && !voterCardNo.trim().equalsIgnoreCase("0") && !voterCardNo.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.voter.voterIDCardNo like '%"+voterCardNo.trim()+"%' or (familyVoter.voterId is not null and familyVoter.voterIDCardNo like '%"+voterCardNo.trim()+"%'))  ");
			}
			if(trNumber != null && trNumber.trim().length()>0 && !trNumber.trim().equalsIgnoreCase("0") && !trNumber.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.refNo like '%"+trNumber.trim()+"%') ");
			}
			if(casteStateId != null && casteStateId.toString().trim().length()>0 && !casteStateId.toString().trim().equalsIgnoreCase("0") && !casteStateId.toString().equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteStateId = :casteStateId ");
			}
			if(casteCategory != null && casteCategory.trim().length()>0 && !casteCategory.trim().equalsIgnoreCase("0") && !casteCategory.equalsIgnoreCase("null"))
			{
				queryStr.append(" and  model.casteState.casteCategoryGroup.casteCategoryGroupName like '%"+casteCategory+"%'");
			}			
			if((fromAge != null && fromAge.longValue()!=0L ) && (toAge != null && toAge.longValue() !=0L))
			{
				queryStr.append(" and model.age >="+fromAge+" and model.age <= "+toAge+"");
			}
			if(gender != null && gender.trim().length()>0)
			{
				if(gender.trim().equalsIgnoreCase("Male") || gender.trim().equalsIgnoreCase("M"))
				{
					queryStr.append(" and (model.gender like 'Male' or model.gender like 'M')");
				}
				if(gender.trim().equalsIgnoreCase("Female") || gender.trim().equalsIgnoreCase("F"))
				{
					queryStr.append(" and (model.gender like 'Female' or model.gender like 'F')");
				}
			}
			if(queryStr != null && queryStr.toString().trim().length()>0)
			{
				queryStr.append("  group by model.casteState.casteStateId ");
				List<Object[]> cadreList = tdpCadreDAO.tdpCadreCasteCountDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString());
				
				List<TdpCadreVO> returnLsit = new ArrayList<TdpCadreVO>();
				if(cadreList != null && cadreList.size()>0)
				{
					for (Object[] cadre : cadreList) 
					{
						TdpCadreVO cadreVO = new TdpCadreVO();
						cadreVO.setId(cadre[1] != null ? Long.valueOf(cadre[1].toString().trim()):0L);
						cadreVO.setCasteName(cadre[0] != null ? cadre[0].toString().trim():"");						
						cadreVO.setTotalCount(cadre[2] != null ? Long.valueOf(cadre[2].toString().trim()):0L);
						
						returnLsit.add(cadreVO);
					}
					
					returnVO.setResponseStatus("SUCCESS");					
					returnVO.setTotalCount(Long.valueOf(String.valueOf(returnLsit.size())));
					returnVO.setTdpCadreDetailsList(returnLsit);
				}
				else
				{
					if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
					{
						returnVO.setResponseStatus(" No Cadre information is available with this Search details...");
					}					
					else if(mobileNo != null && mobileNo.trim().length()>0  && !mobileNo.trim().equalsIgnoreCase("0") && !mobileNo.equalsIgnoreCase("null"))
					{	
						returnVO.setResponseStatus(mobileNo+" Mobile Number is not Registered for any Cadre...");
					}
				}
				returnVO.setResponseCode("");
			}
			else
			{
				returnVO.setResponseStatus("FAILURE");
				returnVO.setResponseCode("Atleast one Attribute is Required...");
			}    		
    	} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCommitte  method in CadreDetailsService.",e);
			returnVO.setResponseStatus("FAILURE");
			returnVO.setResponseCode("SERVER ISSUE");			
		}
    	
    	return returnVO;
	}
}
