package com.itgrids.partyanalyst.service.impl;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.IConstants;


public class CadreDetailsService implements ICadreDetailsService{

	private final static Logger LOG =  Logger.getLogger(CadreDetailsService.class);
	public ITdpCadreDAO tdpCadreDAO;
	public IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	public IConstituencyDAO constituencyDAO;
	public ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	public ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	public IBoothPublicationVoterDAO boothPublicationVoterDAO;
	public ICandidateResultDAO candidateResultDAO;
	public IBoothDAO boothDAO;
	public ICadreCommitteeService cadreCommitteeService;
	public IEventAttendeeDAO eventAttendeeDAO;
	public IWebServiceHandlerService webServiceHandlerService;
	private ICandidateDetailsService				candidateDetailsService;
	private IEventDAO		eventDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IPanchayatHamletDAO panchayatHamletDAO;
	private ICadreRegistrationService cadreRegistrationService;

	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}


	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}


	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}


	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}


	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}


	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}


	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}


	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}


	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}


	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}


	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public ICandidateResultDAO getCandidateResultDAO() {
		return candidateResultDAO;
	}

	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public ICadreCommitteeService getcadreCommitteeService() {
		return cadreCommitteeService;
	}


	public void setcadreCommitteeService(ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public IEventAttendeeDAO getEventAttendeeDAO() {
		return eventAttendeeDAO;
	}


	public void setEventAttendeeDAO(IEventAttendeeDAO eventAttendeeDAO) {
		this.eventAttendeeDAO = eventAttendeeDAO;
	}

	public IWebServiceHandlerService getWebServiceHandlerService() {
		return webServiceHandlerService;
	}


	public void setWebServiceHandlerService(
			IWebServiceHandlerService webServiceHandlerService) {
		this.webServiceHandlerService = webServiceHandlerService;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}


	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public IEventDAO getEventDAO() {
		return eventDAO;
	}


	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}


	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}


	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}


	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}


	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}


	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}


	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}


	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}


	public TdpCadreVO searchTdpCadreDetailsBySearchCriteriaForCommitte(Long locationLevel,Long locationValue, String searchName,String memberShipCardNo, 
			String voterCardNo, String trNumber, String mobileNo,Long casteStateId,String casteCategory,Long fromAge,Long toAge,String houseNo,String gender,int startIndex,int maxIndex)
	{
		TdpCadreVO returnVO = new TdpCadreVO();
		List<Long> ids = new ArrayList<Long>();
    	try {
    		
    		StringBuilder queryStr = new StringBuilder();
    		
    		if(locationLevel != null && locationLevel.longValue() != 0L && locationValue != null && locationValue.longValue() != 0L)
    		{
    			
    			if(locationLevel.longValue() == 2L) // State 
    			{
    				if(locationValue.longValue() == 1l)
    				queryStr.append(" and model.userAddress.district.districtId between 11 and 23 ");
    				else if(locationValue.longValue() == 2l)
    					queryStr.append(" and model.userAddress.district.districtId between 1 and 10 ");	
    				else
    					queryStr.append(" and model.userAddress.district.districtId between 1 and 23 ");	
    				locationValue = 0l;
    			}
    			
    			else if(locationLevel.longValue() == 3L)
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
    				/*
    				Object[] localBodyDetails = constituencyDAO.getlocalbodyName(locationValue);
    				boolean isGreater = false;
    				if(localBodyDetails != null && localBodyDetails.length>0)
    				{
    					String localBody =  localBodyDetails[1] != null ? localBodyDetails[1].toString().trim():null;
    					if(localBody != null && localBody.trim().equalsIgnoreCase("Greater Municipal Corp"))
    					{
    						Long tehsilId =  localBodyDetails[3] != null ? Long.valueOf(localBodyDetails[3].toString().trim()):0L;
    						if(tehsilId != null && tehsilId.longValue()>0)
    						{
    							List<Long> constituencyList = boothDAO.getConstituencyDetailsByTehsilId(tehsilId);
    							if(constituencyList != null && constituencyList.size()>0)
    							{
    								locationValue = constituencyList.get(0);
    								isGreater = true;
    								queryStr.append(" and model.userAddress.constituency.constituencyId =:locationValue ");
    							}
    						}
    					}
    				}

    				if(!isGreater)
    				{
    					queryStr.append(" and model.userAddress.ward.constituencyId =:locationValue ");
    				}
    				
    			*/
    				boolean isGreater = false;
    				Object[] localBodyDetails = constituencyDAO.getlocalbodyName(locationValue);
    				if(localBodyDetails != null && localBodyDetails.length>0)
    				{
    					String localBody =  localBodyDetails[1] != null ? localBodyDetails[1].toString().trim():null;
    					if(localBody != null && localBody.trim().equalsIgnoreCase("Greater Municipal Corp"))
    					{
    						Long localBodyId =  localBodyDetails[2] != null ? Long.valueOf(localBodyDetails[2].toString().trim()):0L;
    						if(localBodyId != null && localBodyId.longValue()>0)
    						{
    							locationValue = localBodyId;
    							isGreater = true;
    							queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValue ");
    						}
    					}
    				}
    				if(!isGreater)
    				{
    					queryStr.append(" and model.userAddress.ward.constituencyId =:locationValue ");
    				}
    			}
    			else if(locationLevel.longValue() == 9L)
    			{
    				queryStr.append(" and model.userAddress.booth.boothId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 10L) // MP
    			{
    				List<Object[]> assemblyList = delimitationConstituencyAssemblyDetailsDAO.getAllAssemblyDetailsOfParliament(locationValue,Long.valueOf(IConstants.PRESENT_ELECTION_YEAR));
    				
    				if(assemblyList != null && assemblyList.size() > 0)
    				{
    					for(Object[] params : assemblyList)
    						ids.add((Long)params[0]);
    					queryStr.append(" and model.userAddress.constituency.constituencyId in(:ids) ");
    				}
    				
    			}
    			
    		}
    		
    		if(searchName != null && searchName.trim().length()>0 && !searchName.trim().equalsIgnoreCase("0") && !searchName.equalsIgnoreCase("null"))
			{
				queryStr.append(" and model.firstname like '%"+searchName+"%' ");
			}
    		
			if(memberShipCardNo != null && memberShipCardNo.trim().length()>0  && !memberShipCardNo.trim().equalsIgnoreCase("0") && !memberShipCardNo.equalsIgnoreCase("null"))
			{
				queryStr.append(" and (model.memberShipNo = '"+memberShipCardNo.trim()+"') ");
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
				List<Object[]> cadreList = tdpCadreDAO.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString(),startIndex,maxIndex,ids);
				
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
						if(cadre[4] != null){
							if(cadre[4].toString().trim().length() > 8){
								cadreVO.setMemberShipNo(cadre[4].toString().trim().substring(cadre[4].toString().trim().length()-8));
							}else{
								cadreVO.setMemberShipNo(cadre[4].toString());
							}
						}else{
							cadreVO.setMemberShipNo("");
						}
						//cadreVO.setMemberShipNo(cadre[4] != null ? cadre[4].toString().substring(4):"");
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
						cadreVO.setDataSourceType(cadre[29] != null ? cadre[29].toString().trim():"");
						
						returnLsit.add(cadreVO);
					}
					
					returnVO.setResponseStatus("SUCCESS");					
					returnVO.setTotalCount(Long.valueOf(String.valueOf(returnLsit.size())));
					returnVO.setTdpCadreDetailsList(returnLsit);
					if(returnLsit != null && maxIndex != 0)
					{
					List<Object[]> cadreListCnt = tdpCadreDAO.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationValue,Long.valueOf(casteStateId), queryStr.toString(),0,0,ids);
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
    			if(locationLevel.longValue() == 2L || locationLevel.longValue() == 3L)
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
				queryStr.append(" and (model.memberShipNo = '"+memberShipCardNo.trim()+"') ");
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
	public CadreCommitteeMemberVO cadreFormalDetailedInformation(Long cadreId){
		
		CadreCommitteeMemberVO cadreDetailsVO=new CadreCommitteeMemberVO();
		try {
			
			if(cadreId !=null){

					
				//0,1,2,3,5,7,9,10,11,12
				//0.tdpCadreId,1.firstname,2.dateOfBirth,3.age,4.eduQualificationId,5.qualification,
				//6.occupationId,7.occupation,8.voterId,9.panchayatName,10.tehsilName,11.constName,12.mobileNo,13.ConstituencyId
				//14.,18.districtName,19.stateName,20.casteName,21.insertedWebUserId(registeredOn),22.registeredTime,23.emailId,24.dataSourceType
				//25.panchayatId,26.tehsilId,27.districtId,28.stateId,29.pConstId,30.pName
				Object[] cadreFormalDetails=tdpCadreDAO.cadreFormalDetailedInformation(cadreId,2014l);
				
				//0.tdpCommitteeLevel,1.role
				Object[] partyPositionDetails=tdpCommitteeMemberDAO.getPartyPositionBycadre(cadreId);
				//0.publicRepresentativeTypeId,1.type
				List<Object[]> publicRepDertails=tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadre(cadreId);
				
				Long candidateId=tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
				
				
				DateFormat dateFormat=null;
				Date convertedDate = null;
				//DateUtilService dateUtilService = new DateUtilService();
				
				if(cadreFormalDetails !=null){
					
					cadreDetailsVO.setId((Long)cadreFormalDetails[0]);
					cadreDetailsVO.setName(cadreFormalDetails[1] !=null ? cadreFormalDetails[1].toString() : "");
					
					if(cadreFormalDetails[2] !=null){
						dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						convertedDate = (Date) dateFormat.parse(cadreFormalDetails[2].toString());
						 String lines[] = convertedDate.toString().split(" ");
						 cadreDetailsVO.setDateOfBirth(lines[1]+ " "+lines[2] +" " + lines[5]);
					}else{
						cadreDetailsVO.setDateOfBirth("");
					}
					//cadreDetailsVO.setDateOfBirth(cadreFormalDetails[2] !=null ? cadreFormalDetails[2].toString() : "");
					cadreDetailsVO.setAge(cadreFormalDetails[3] !=null ? cadreFormalDetails[3].toString() :"" );
					cadreDetailsVO.setQualification(cadreFormalDetails[5] !=null ? cadreFormalDetails[5].toString() : "");
					
					cadreDetailsVO.setOccupation(cadreFormalDetails[7] !=null ? cadreFormalDetails[7].toString() : "");
					
					cadreDetailsVO.setVoterId(cadreFormalDetails[8] !=null ? cadreFormalDetails[8].toString() :"" );
					
					cadreDetailsVO.setPanchayatName(cadreFormalDetails[9] !=null ? cadreFormalDetails[9].toString() : "-" );
					
					cadreDetailsVO.setTehsilName(cadreFormalDetails[10] !=null ? cadreFormalDetails[10].toString() : "" );
					
					cadreDetailsVO.setConstituencyName(cadreFormalDetails[11] !=null ? cadreFormalDetails[11].toString() : "" );
					
					cadreDetailsVO.setMobileNo(cadreFormalDetails[12] !=null ? cadreFormalDetails[12].toString() : "");
					cadreDetailsVO.setConstituencyId(cadreFormalDetails[13] !=null ? (Long)cadreFormalDetails[13] : 0l );
					cadreDetailsVO.setVoterIdCardNo(cadreFormalDetails[14] !=null ? cadreFormalDetails[14].toString() : "");
					
					if(cadreFormalDetails[15] !=null){
						String image=cadreFormalDetails[15].toString();
						String imagePath="http://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+image+"";
						
						cadreDetailsVO.setImagePath(imagePath);
					}
					else{
						cadreDetailsVO.setImagePath("");
					}
					
					cadreDetailsVO.setMembershipNo(cadreFormalDetails[16] !=null ? cadreFormalDetails[16].toString() :"");
					cadreDetailsVO.setHouseNo(cadreFormalDetails[17] !=null ? cadreFormalDetails[17].toString() :"");
					cadreDetailsVO.setDistrictName(cadreFormalDetails[18] !=null ? cadreFormalDetails[18].toString() : "" );
					cadreDetailsVO.setStateName(cadreFormalDetails[19] !=null ? cadreFormalDetails[19].toString() : "" );
					cadreDetailsVO.setCasteName(cadreFormalDetails[20] !=null ? cadreFormalDetails[20].toString():"");
					
					String dataSourceType="";
					if(cadreFormalDetails[24] !=null){
						dataSourceType=cadreFormalDetails[24].toString();
					}
					
					if(cadreFormalDetails[21] !=null){
						
						String webUserId=cadreFormalDetails[21].toString();
						String[] partyOfficeIds=IConstants.PARTY_OFFICE_USER_IDS.split(",");
						String[] mahanaduIds=IConstants.MAHANADU_USER_IDS.split(",");
					
						boolean partyOffice=Arrays.asList(partyOfficeIds).contains(webUserId);
						if(partyOffice){
							cadreDetailsVO.setRegisteredOn(partyOfficeIds[0]);
						}
						else if(partyOffice ==false){
							boolean mahandu=Arrays.asList(mahanaduIds).contains(webUserId);
							if(mahandu){
								cadreDetailsVO.setRegisteredOn(mahanaduIds[0]);
							}
							else{
								cadreDetailsVO.setRegisteredOn(dataSourceType);
							}
						}
						
					}//registered On
					else{
						cadreDetailsVO.setRegisteredOn(dataSourceType);
					}
					cadreDetailsVO.setEmailId(cadreFormalDetails[23] !=null ? cadreFormalDetails[23].toString(): "");
					if(cadreFormalDetails[22] !=null){
						dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						convertedDate = (Date) dateFormat.parse(cadreFormalDetails[22].toString());
						 String lines[] = convertedDate.toString().split(" ");
						 cadreDetailsVO.setRegisteredTime(lines[1]+ " "+lines[2] +" " + lines[5]);
					}
					
					//Ids
					cadreDetailsVO.setPanchayatId(cadreFormalDetails[25] !=null ? Long.parseLong(cadreFormalDetails[25].toString()) :0l);
					cadreDetailsVO.setTehsilId(cadreFormalDetails[26] !=null ? Long.parseLong(cadreFormalDetails[26].toString()):0l);
					cadreDetailsVO.setDistrictId(cadreFormalDetails[27] !=null ? Long.parseLong(cadreFormalDetails[27].toString()):0l);
					cadreDetailsVO.setStateId(cadreFormalDetails[28] !=null ? Long.parseLong(cadreFormalDetails[28].toString()):0l);
					cadreDetailsVO.setpConstituencyId(cadreFormalDetails[29] !=null ? Long.parseLong(cadreFormalDetails[29].toString()):0l);
					cadreDetailsVO.setpConstituencyName(cadreFormalDetails[30] !=null ? cadreFormalDetails[30].toString(): "");
				}
				
				if(partyPositionDetails !=null)
				{
					String level=partyPositionDetails[0] != null ? partyPositionDetails[0].toString() : "" ;
					String role=partyPositionDetails[1] != null ? partyPositionDetails[1].toString() : "";
					
					cadreDetailsVO.setPartyPosition(level +" " +role);
				}
				else{
					cadreDetailsVO.setPartyPosition("N/A");
				}
				
				String publicRepresentTypeStr="N/A";
				if(publicRepDertails !=null && publicRepDertails.size()>0){
					publicRepresentTypeStr="";
					for(Object[] publicRepDertail:publicRepDertails){
						publicRepresentTypeStr=publicRepDertail[1].toString()+" , "+publicRepresentTypeStr;
					}
					if (publicRepresentTypeStr.endsWith(" , ")) {
						publicRepresentTypeStr = publicRepresentTypeStr.substring(0, publicRepresentTypeStr.length() - 2);
						}
				}
				
				cadreDetailsVO.setRepresentativeType(publicRepresentTypeStr);
				cadreDetailsVO.setCandidate(candidateId !=null ? candidateId.longValue():0l);
					
					//cadreDetailsVO.setRepresentativeType(publicRepDertails[1] !=null ? publicRepDertails[1].toString() : "");
				}
			
			if(cadreDetailsVO !=null){
				
				String voterIdStr=cadreDetailsVO.getVoterId();
				Long constituencyId = cadreDetailsVO.getConstituencyId();
				if(voterIdStr !=null && voterIdStr !=""){
					
					Object boothPartNo=boothPublicationVoterDAO.getBoothPartNumberByVoterId(voterIdStr);
					String s=null;
					Long l=null;
					if(boothPartNo instanceof String){
						s=(String)boothPartNo;
					}
					if(boothPartNo instanceof Long){
						l=(Long)boothPartNo;
					}
					if(l!=null){
						s=l.toString();
					}
					//CadreCommitteeMemberVO vo=	getCadreInformationInHisOwnBooth(s,constituencyId);
					//cadreDetailsVO.setCcmVO(vo);
				}
			}
			
			if(cadreDetailsVO.getTehsilId() !=null && cadreDetailsVO.getTehsilId() !=0l){
				Long localElctioniBodyId=boothDAO.getLocalElectionBody(cadreDetailsVO.getTehsilId());
				
				cadreDetailsVO.setLocalElectionBody(localElctioniBodyId !=null ? localElctioniBodyId.longValue() : 0l);
			} 
			
			return cadreDetailsVO;
			
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteriaForCommitte  method in CadreDetailsService.",e);
		}
		return cadreDetailsVO;
	}
	public CadreCommitteeMemberVO getCadreInformationInHisOwnBooth(String ownBoothId,Long constituencyId){
		
		CadreCommitteeMemberVO finalVo=new CadreCommitteeMemberVO();
		
	try{
		List<Long> naIds = new ArrayList<Long>();
		
		String constiPerc = null;
		Map<Long,String> constiPercMap =  new HashMap<Long,String>();
		
		naIds.add(103l);
		naIds.add(310l);
		
		Set<Long> ownMandalsList = new HashSet<Long>();
		Set<Long> ownMunicList = new HashSet<Long>();
		Set<Long> ownPanchayatList = new HashSet<Long>();
		Set<Long> ownWardList = new HashSet<Long>();
		Set<Long> assMandalsList = new HashSet<Long>();
		Set<Long> assMunicList = new HashSet<Long>();
		Set<Long> assPanchayatList = new HashSet<Long>();
		Set<Long> assWardList = new HashSet<Long>();
		
		
		Set<String> ownBoothsList = new HashSet<String>();
		Set<String> assigBoothsList = new HashSet<String>();
		Set<String> allBooths = new HashSet<String>();
		
		if(ownBoothId != null){
			ownBoothsList.add(ownBoothId);
		}
		
		allBooths.addAll(ownBoothsList);
		allBooths.addAll(assigBoothsList);
		constiPerc = constiPercMap.get(constituencyId);
		if(constiPerc == null){
			List<Long> partyIds = new ArrayList<Long>();
	    	partyIds.add(163l);
	    	partyIds.add(872l);
	    	//0 partyId,1 votesearnedPerc
	    	List<Object[]> candidateResult = candidateResultDAO.getResultPercByPartyIds(partyIds,constituencyId,258l);
	    	if(candidateResult.size() == 1){
	    		constiPerc = candidateResult.get(0)[1].toString();
	    	}else{
	    		for(Object[] result:candidateResult){
	    			if(((Long)result[0]).longValue() == 872l){
	    				constiPerc =result[1].toString();
	    			}
	    		}
	    	}
	    	
	    	constiPercMap.put(constituencyId, constiPerc);
		}
		
		String ownMandalPerc = "";
		String ownMunicPerc = "";
		String ownPancPerc = "";
		String ownWardPerc = "";
		String ownBoothPerc = "";
		
		
		String assMandalPerc = "";
		String assMunicPerc = "";
		String assPancPerc = "";
		String assWardPerc = "";
		String assBoothPerc = "";

		
		Map<String,VoterVO> boothsInfoMap = new HashMap<String,VoterVO>();
		
		List<Object[]> boothsInfo = new ArrayList<Object[]>();
		//0boothId, 1partNo, 2tehsilId, 3lclBodyId, 4panchayatId, 5wardId
		if(!(allBooths == null || allBooths.size() == 0))
		    boothsInfo = boothDAO.getBoothsInfo(constituencyId,allBooths);
		for(Object[] info:boothsInfo){
			VoterVO vo = new VoterVO();
			vo.setBoothId((Long)info[0]);
			vo.setPartNo(Long.valueOf(info[1].toString()));
			vo.setCandidateId((Long)info[2]);//tehsilId
			vo.setLocalAreaId((Long)info[3]);//lclBodyId
			vo.setPanchayatId((Long)info[4]);
			vo.setHamletId((Long)info[5]);//wardId
			boothsInfoMap.put(info[1].toString(), vo);
		}
		Long electionId = 258l;//2014 Assembly ElectionId
		List<Long> reqPartyIds = new ArrayList<Long>();
		if(!naIds.contains(constituencyId)){
			if(constituencyId.longValue() == 243l || constituencyId.longValue() == 229l || constituencyId.longValue() == 297l || constituencyId.longValue() == 301l){
				reqPartyIds.add( 872l);
			}else{
				reqPartyIds.add( 872l);
				reqPartyIds.add( 163l);
			}
		}else{
			reqPartyIds.add( 872l);
			reqPartyIds.add( 163l);
			electionId = 260l;
		}
		
		List<Long> assBtIds = new ArrayList<Long>();
			for(String partNo:assigBoothsList){
				VoterVO vo = boothsInfoMap.get(partNo);
				if(vo != null){
					assBtIds.add(vo.getBoothId());
					if(vo.getLocalAreaId() == null && vo.getCandidateId() != null){
						assMandalsList.add(vo.getCandidateId());
					
					}
					
					if(vo.getLocalAreaId() != null && vo.getHamletId() == null){
						assMunicList.add(vo.getLocalAreaId());
						
					}
					
					if(vo.getPanchayatId() != null){
						assPanchayatList.add(vo.getPanchayatId());
						
					}
					
					if(vo.getHamletId() != null){
						assWardList.add(vo.getHamletId());
						
					}
				}
			}
			if(assMandalsList.size() > 0){
				assMandalPerc = cadreCommitteeService.calculateMandalPerc(assMandalsList,constituencyId,reqPartyIds, electionId);			
			}if(assMunicList.size() > 0){
				assMunicPerc = cadreCommitteeService.calculateMunicPerc(assMunicList,constituencyId,reqPartyIds, electionId);
			}if(assPanchayatList.size() > 0){
				assPancPerc = cadreCommitteeService.calculatePancPerc(assPanchayatList,constituencyId,reqPartyIds, electionId);
			}if(assWardList.size() > 0){
				assWardPerc = cadreCommitteeService.calculateWardPerc(assWardList,constituencyId,reqPartyIds, electionId);
			}
			
			if(assBtIds.size() > 0){
			   assBoothPerc = cadreCommitteeService.calculateBootPerc(assBtIds,constituencyId,reqPartyIds, electionId);
			}
          if(constiPerc == null){
        	  List<Long> boothIds = boothDAO.getBoothsDetailIds("", constituencyId, null);
        	  constiPerc = cadreCommitteeService.calculateBootPerc(boothIds, constituencyId, reqPartyIds, electionId);
        	  constiPercMap.put(constituencyId, constiPerc);
	    	}
			List<Long> ownBtIds = new ArrayList<Long>();
			for(String partNo:ownBoothsList){
				VoterVO vo = boothsInfoMap.get(partNo);
				if(vo != null){
					ownBtIds.add(vo.getBoothId());
					if(vo.getLocalAreaId() == null && vo.getCandidateId() != null){
						ownMandalsList.add(vo.getCandidateId());
					}
					
					if(vo.getLocalAreaId() != null && vo.getHamletId() == null){
						ownMunicList.add(vo.getLocalAreaId());
					}
					
					if(vo.getPanchayatId() != null){
						ownPanchayatList.add(vo.getPanchayatId());
					}
					
					if(vo.getHamletId() != null){
						ownWardList.add(vo.getHamletId());
					}
				}
			}
			
			finalVo.setOwnConstiPerc(constiPerc);
			
			  if(ownMandalsList.size() > 0){
				  ownMandalPerc = cadreCommitteeService.calculateMandalPerc(ownMandalsList,constituencyId,reqPartyIds,electionId);
				  finalVo.setOwnMandalPerc(ownMandalPerc);
				}if(ownMunicList.size() > 0){
					ownMunicPerc = cadreCommitteeService.calculateMunicPerc(ownMunicList,constituencyId,reqPartyIds,electionId);
					finalVo.setOwnMunciPerc(ownMunicPerc);
				}if(ownWardList.size() > 0){
					ownWardPerc =cadreCommitteeService.calculateWardPerc(ownWardList,constituencyId,reqPartyIds,electionId);
					finalVo.setOwnWardPerc(ownWardPerc);
				}if(ownPanchayatList.size() > 0){
					ownPancPerc = cadreCommitteeService.calculatePancPerc(ownPanchayatList,constituencyId,reqPartyIds,electionId);
					finalVo.setOwnPanchPerc(ownPancPerc);
				}
				
			if(ownBtIds.size() > 0){
				ownBoothPerc = cadreCommitteeService.calculateBootPerc(ownBtIds,constituencyId,reqPartyIds,electionId);
				finalVo.setOwnBoothPerc(ownBoothPerc);
			}
			
			if(finalVo.getOwnMandalPerc()!=null && finalVo.getOwnBoothPerc()!=null){
				double boothPer = Double.parseDouble(finalVo.getOwnBoothPerc());
				double mandalPer = Double.parseDouble(finalVo.getOwnMandalPerc());
				double prfrmancePerc = boothPer-mandalPer;
				finalVo.setMandalBoothCmpr(String.valueOf(prfrmancePerc));
				if(prfrmancePerc<0){
					finalVo.setLowPerformance(true);
				}else{
					finalVo.setLowPerformance(false);
				}
			}
			if(finalVo.getOwnMunciPerc()!=null && finalVo.getOwnBoothPerc()!=null){
				double boothPer = Double.parseDouble(finalVo.getOwnBoothPerc());
				double munciPer = Double.parseDouble(finalVo.getOwnMunciPerc());
				double prfrmancePerc = boothPer-munciPer;
				finalVo.setMandalBoothCmpr(String.valueOf(prfrmancePerc));
				if(prfrmancePerc<0){
					finalVo.setLowPerformance(true);
				}else{
					finalVo.setLowPerformance(false);
				}
			}
		
			return finalVo;
		
		}catch (Exception e) {
			LOG.error("Exception raised in getCadreInformationInHisOwnBooth service  method in CadreDetailsService.",e);
		}
			return finalVo;
	}
	
	public CadreCommitteeMemberVO complaintDetailsOfCadre(Long cadreId,String memberShipId){
		
		
		CadreCommitteeMemberVO finalVo=new CadreCommitteeMemberVO();
		List<CadreCommitteeMemberVO> listForFinalVo=new ArrayList<CadreCommitteeMemberVO>();
		
		try{
			
			 DateFormat formatter = null;
		     Date convertedDate = null;
			//0.Complaint_id,1.Subject,2.Seviority,3.Raised_Date,4.Completed_Status,5.issue_type,6.type_of_grevience
			List<Object[]> complaintDetails=tdpCadreDAO.complaintDetailsOfCadre(memberShipId);
			
			if(complaintDetails !=null){
				
				for (Object[] objects : complaintDetails) {
					
					CadreCommitteeMemberVO eachComplaint=new CadreCommitteeMemberVO();
					
					eachComplaint.setId(Long.parseLong(objects[0].toString()));//Complaint_id
					eachComplaint.setName(objects[1]!=null  ? objects[1].toString():"");//Subject
					eachComplaint.setOccupation(objects[2] !=null ? objects[2].toString():"");//seviority
					
					if(objects[3] !=null){
						 String date = objects[3].toString();
						 String date1 = date.substring(0, 10);
						 formatter = new SimpleDateFormat("yyyy-MM-dd");
						 convertedDate = (Date) formatter.parse(date1);
						 String lines[] = convertedDate.toString().split(" ");
						 String time = date.substring(10);
						 eachComplaint.setDateOfBirth(lines[1]+ " "+lines[2] +" " + lines[5]+" ("+lines[0]+")");
						
					}else{
						eachComplaint.setDateOfBirth("");
					}
					
					//eachComplaint.setDateOfBirth(objects[3].toString());//Raised_Date
					eachComplaint.setStatus(objects[4] !=null ? objects[4].toString():"");//Completed_Status
					eachComplaint.setRepresentativeType(objects[5] !=null ? objects[5].toString() :"" );//issue_type
					eachComplaint.setType(objects[6] !=null ? objects[6].toString() :"");//type_of_grevience
					
					listForFinalVo.add(eachComplaint);
				}
				
				finalVo.setKnownList(listForFinalVo);
			}
			return finalVo;
			
		}catch (Exception e) {
			LOG.error("Exception raised in complaintDetailsOfCadre service  method in CadreDetailsService.",e);
		}
		return finalVo;
	}
	
	public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(Long cadreId){
		
		List<CadreCommitteeMemberVO> finalList=new ArrayList<CadreCommitteeMemberVO>();
		
		try{
			//0.tdpCadreId,1.eventId,2.name,3.description,4.parentEventId,5.count
			List<Object[]> eventDetails=eventAttendeeDAO.getEventDetailsOfCadre(cadreId);
			
			Map<Long,Map<Long,CadreCommitteeMemberVO>> finalMap=new HashMap<Long, Map<Long,CadreCommitteeMemberVO>>();
			//parentId,eventId
			List<CadreCommitteeMemberVO> list=new ArrayList<CadreCommitteeMemberVO>();
			
			
			if(eventDetails !=null){
				
				for (Object[] event : eventDetails) {
					CadreCommitteeMemberVO subEventDetails = null;
					Map<Long,CadreCommitteeMemberVO>  mainEventMap=finalMap.get(Long.parseLong(event[4].toString()));
					
					if(mainEventMap ==null){
						subEventDetails = new CadreCommitteeMemberVO();
						mainEventMap=new HashMap<Long, CadreCommitteeMemberVO>();
						finalMap.put(Long.parseLong(event[4].toString()),mainEventMap);//parentEventId,Map<EventId,CadreCommitteeMemberVO>
					}else{
						subEventDetails=mainEventMap.get(Long.parseLong(event[1].toString()));
						if(subEventDetails ==null){ 
							subEventDetails=new CadreCommitteeMemberVO();
						}
					}
							subEventDetails.setVtrId(Long.parseLong(event[0].toString()));//cadreId
							subEventDetails.setId(Long.parseLong(event[1].toString()));//eventId
							subEventDetails.setName(event[2] !=null ? event[2].toString():"");//eventName
							subEventDetails.setLevel(Long.parseLong(event[4].toString()));//parentEventId
							
							String parentEventName=eventDAO.getEventName(subEventDetails.getLevel());
							
							if(parentEventName !=null){
								subEventDetails.setType(parentEventName);//parentEventName
							}
							
							subEventDetails.setTotal(event[5] !=null ? Long.parseLong(event[5].toString()) :0l);
							mainEventMap.put(Long.parseLong(event[1].toString()), subEventDetails);
							
							finalMap.put(Long.parseLong(event[4].toString()), mainEventMap);
					 }
				
					if(finalMap !=null && finalMap.size()>0){
						for (Map.Entry<Long,Map<Long,CadreCommitteeMemberVO>> entry : finalMap.entrySet())
						{
							CadreCommitteeMemberVO cadreCommitteeMemberVO = new CadreCommitteeMemberVO();
							
							cadreCommitteeMemberVO.setId(entry.getKey());//parentEventId
							
							String parentEventName=eventDAO.getEventName(cadreCommitteeMemberVO.getId());
							if(parentEventName !=null){
								cadreCommitteeMemberVO.setName(parentEventName);
							}
							
							Map<Long,CadreCommitteeMemberVO> subMap = entry.getValue();//subEventId,vo
							
							if(subMap !=null && subMap.size()>0){
								cadreCommitteeMemberVO.setKnownList(new ArrayList<CadreCommitteeMemberVO>(subMap.values()));//List Of SubEvents
							}
							finalList.add(cadreCommitteeMemberVO);
						}
					}	
			}
			return finalList;

		}catch (Exception e) {
			LOG.error("Exception raised in getEventDetailsOfCadre service  method in CadreDetailsService.",e);
		}
		
		return finalList;
	}
	public VerifierVO getTdpCadreSurveyDetails(Long cadreId,Long surveyId){
		VerifierVO verifierVO=new VerifierVO();
		try{
			verifierVO=webServiceHandlerService.getTdpCadreSurveyDetails(cadreId,surveyId);
			return verifierVO;
		}
		catch(Exception e){
			LOG.error("Exception raised in getTdpCadreSurveyDetails service  method in CadreDetailsService.",e);
		}
		
		return verifierVO;
	}
	public List<CandidateDetailsVO>  getCandidateElectDetatails(Long cadreId){
		
		List<CandidateDetailsVO> candidateElecDatails=new ArrayList<CandidateDetailsVO>();
		try{
			Long candidateId=tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
			if(candidateId !=null){
				 candidateElecDatails=candidateDetailsService.getCandidateElectionDetails(candidateId);
			}
			
		}catch(Exception e){
			LOG.error("Exception raised in getCandidateElectDetatails() service  method in CadreDetailsService.",e);
		}
		
		return candidateElecDatails;
	}
	

	/*public RegisteredMembershipCountVO getTotalMemberShipRegistrationsInCadreLocation(Long tdpCadreId)
	{
		RegisteredMembershipCountVO countVO = new RegisteredMembershipCountVO();
		try{
			
			Long electionId = 258l;//2014 
			Long electionYear = 2014l;
			UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
			if(userAddress != null)
			{
			Long constituencyId = userAddress.getConstituency().getConstituencyId();
			countVO.setAreaType(userAddress.getConstituency().getAreaType());
			
			
			countVO.setConsTotalVoters(getTotalVotersByLocationId(constituencyId, "Constituency", electionId, constituencyId,null));
			countVO.setConstituencyCount(getMemberShipCount("Constituency", constituencyId, electionYear, constituencyId,null));
		   
			String percentage = null;
			if(countVO.getConsTotalVoters() != null && countVO.getConsTotalVoters() > 0)
			   percentage = (new BigDecimal((countVO.getConstituencyCount() * 100.0)/countVO.getConsTotalVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			countVO.setConstiPerc(percentage);
			
			
			
			if(userAddress.getPanchayat() != null && userAddress.getPanchayat().getPanchayatId() != null)
			{
				countVO.setPanchayatCount(getMemberShipCount("Panchayat", userAddress.getPanchayat().getPanchayatId(), electionYear, constituencyId,null));
				countVO.setPanchayatTotVoters(getTotalVotersByLocationId(userAddress.getPanchayat().getPanchayatId(), "Panchayat", electionId, constituencyId,null));
				String panPer = null;
				if(countVO.getPanchayatTotVoters() != null && countVO.getPanchayatTotVoters() > 0)
				{
				 panPer = (new BigDecimal((countVO.getPanchayatCount() * 100.0)/countVO.getPanchayatTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			      countVO.setPanchPerc(panPer);
			      
			        if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() != null)
					{
					  countVO.setMandalCount(getMemberShipCount("Mandal", userAddress.getTehsil().getTehsilId(), electionYear, constituencyId,null));	
					  countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId,null));
					  String manPer = null;
					  if(countVO.getMandalTotVoters() != null && countVO.getMandalTotVoters() > 0)
					   manPer = (new BigDecimal((countVO.getMandalCount() * 100.0)/countVO.getMandalTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
					   countVO.setMandalPerc(manPer);
					
					}
			      
				}
			}
			
			if(userAddress.getBooth() != null && userAddress.getBooth().getBoothId() != null)
			{
				countVO.setBoothCount(getMemberShipCount("Booth", userAddress.getBooth().getBoothId(), electionYear, constituencyId,null))	;
				countVO.setBoothTotVoters(getTotalVotersByLocationId(userAddress.getBooth().getBoothId(), "booth", electionId, constituencyId,null));
				String boothPer =null;
				if(countVO.getBoothTotVoters() != null && countVO.getBoothTotVoters() > 0)
				 boothPer = (new BigDecimal((countVO.getBoothCount() * 100.0)/countVO.getBoothTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			     countVO.setBoothPerc(boothPer);
			
			}
			
			if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() != null)
			{
				countVO.setMunCount(getMemberShipCount("Muncipality", userAddress.getLocalElectionBody().getLocalElectionBodyId(), electionYear, constituencyId,null));
				countVO.setMunTotVoters(getTotalVotersByLocationId(userAddress.getLocalElectionBody().getLocalElectionBodyId(), "Muncipality", electionId, constituencyId,null));
				String  munPer =null;
				if(countVO.getMunTotVoters() != null && countVO.getMunTotVoters() > 0)
				 munPer = (new BigDecimal((countVO.getMunCount() * 100.0)/countVO.getMunTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			     countVO.setMunPerc(munPer);
			
			}
			
			if(userAddress.getDistrict() != null && userAddress.getDistrict().getDistrictId() != null)
			{
				
				countVO.setDistrictCount(getMemberShipCount("District", userAddress.getDistrict().getDistrictId(), electionYear, constituencyId,null));
				countVO.setDistrictTotVoters(getTotalVotersByLocationId(userAddress.getDistrict().getDistrictId(), "District", electionId, constituencyId,null));
				String  disPer =null;
				if(countVO.getDistrictTotVoters() != null && countVO.getDistrictTotVoters() > 0)
				 disPer = (new BigDecimal((countVO.getDistrictCount() * 100.0)/countVO.getDistrictTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			     countVO.setDistrictPerc(disPer);
				
			}
			
			Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
			if(parliamentConId != null && parliamentConId > 0)
			{
				List<Long> assConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
				if(assConstituencyIds != null && assConstituencyIds.size() > 0)
				{
					countVO.setParConsCount(getMemberShipCount("Parliament", null, electionYear, null,assConstituencyIds));
					countVO.setParConsTotVoters(getTotalVotersByLocationId(null, "Parliament", electionId, null,assConstituencyIds));
					String  parConPer =null;
					if(countVO.getParConsTotVoters() != null && countVO.getParConsTotVoters() > 0)
						parConPer = (new BigDecimal((countVO.getParConsCount() * 100.0)/countVO.getParConsTotVoters().doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
				     countVO.setParConsPerc(parConPer);
				}
			}
			
		}	
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalMemberShipRegistrationsInCadreLocation() method, Exception - ",e);
		}
		return countVO;
	}*/
	
	
	
  public RegisteredMembershipCountVO getTotalMemberShipRegistrationsInCadreLocation(Long tdpCadreId)
  {
	 RegisteredMembershipCountVO countVO = new RegisteredMembershipCountVO();
	  try{
			
			Long electionId = 258l;//2014 
			Long electionYear = 2014l;
			UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
			if(userAddress != null)
			{
			   Long constituencyId = userAddress.getConstituency().getConstituencyId();
			   countVO.setAreaType(userAddress.getConstituency().getAreaType());
			
			
			   countVO.setConsTotalVoters(getTotalVotersByLocationId(constituencyId, "Constituency", electionId, constituencyId,null));
			   countVO.setConstituencyCount(getMemberShipCount("Constituency", constituencyId, electionYear, constituencyId,null));
			   countVO.setConstiPerc(calculatePercentage(countVO.getConsTotalVoters(), countVO.getConstituencyCount()));
			
			
			 if(userAddress.getBooth() != null && userAddress.getBooth().getBoothId() != null)
			 {
				countVO.setBoothCount(getMemberShipCount("Booth", userAddress.getBooth().getBoothId(), electionYear, constituencyId,null))	;
				countVO.setBoothTotVoters(getTotalVotersByLocationId(userAddress.getBooth().getBoothId(), "booth", electionId, constituencyId,null));
				countVO.setBoothPerc(calculatePercentage(countVO.getBoothTotVoters(),countVO.getBoothCount()));
			
			 }
			
			if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() != null)
			{
				countVO.setMandalCount(getMemberShipCount("Muncipality", userAddress.getLocalElectionBody().getLocalElectionBodyId(), electionYear, constituencyId,null));
				countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getLocalElectionBody().getLocalElectionBodyId(), "Muncipality", electionId, constituencyId,null));
				countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
				countVO.setCadreLocation("Muncipality");
			
			}
			else
			{
				 countVO.setCadreLocation("Mandal");
				if(userAddress.getPanchayat() != null && userAddress.getPanchayat().getPanchayatId() != null)
				{
					countVO.setPanchayatCount(getMemberShipCount("Panchayat", userAddress.getPanchayat().getPanchayatId(), electionYear, constituencyId,null));
					countVO.setPanchayatTotVoters(getTotalVotersByLocationId(userAddress.getPanchayat().getPanchayatId(), "Panchayat", electionId, constituencyId,null));
					countVO.setPanchPerc(calculatePercentage(countVO.getPanchayatTotVoters(), countVO.getPanchayatCount()));
					if(countVO.getPanchayatTotVoters() != null && countVO.getPanchayatTotVoters() > 0)
					{
				      
				        if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() != null)
						{
						  countVO.setMandalCount(getMemberShipCount("Mandal", userAddress.getTehsil().getTehsilId(), electionYear, constituencyId,null));	
						  countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId,null));
						  countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
						
						}
				      
					}
				}
				
			}
			
			if(userAddress.getDistrict() != null && userAddress.getDistrict().getDistrictId() != null)
			{
				
				countVO.setDistrictCount(getMemberShipCount("District", userAddress.getDistrict().getDistrictId(), electionYear, constituencyId,null));
				countVO.setDistrictTotVoters(getTotalVotersByLocationId(userAddress.getDistrict().getDistrictId(), "District", electionId, constituencyId,null));
				countVO.setDistrictPerc(calculatePercentage(countVO.getDistrictTotVoters(),countVO.getDistrictCount()));
			}
			
			Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
			if(parliamentConId != null && parliamentConId > 0)
			{
				List<Long> assConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
				if(assConstituencyIds != null && assConstituencyIds.size() > 0)
				{
					countVO.setParConsCount(getMemberShipCount("Parliament", null, electionYear, null,assConstituencyIds));
					countVO.setParConsTotVoters(getTotalVotersByLocationId(null, "Parliament", electionId, null,assConstituencyIds));
					countVO.setParConsPerc(calculatePercentage(countVO.getParConsTotVoters(), countVO.getParConsCount()));
				}
			}
			
		}	
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalMemberShipRegistrationsInCadreLocation() method, Exception - ",e);
		}
		return countVO;
	}
	
	
	public Long getTotalVotersByLocationId(Long locationId,String locationType,Long electionId,Long constituencyId,List<Long> constituencyIdsList)
	{
		try{
			if(electionId.longValue() == 38l && locationType.equalsIgnoreCase("Panchayat"))
			{
				List<Long> boothIdsList = panchayatHamletDAO.getBoothIdsByPanchayatId(locationId, electionId);
				if(boothIdsList != null && boothIdsList.size() > 0)
					return boothConstituencyElectionDAO.getTotalVotersByBoothIdsList(boothIdsList, electionId);
				return null;
			}
			else
			  return boothConstituencyElectionDAO.getTotalVotesByLocationId(locationId, locationType, electionId, constituencyId,constituencyIdsList);
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getTotalVotersByLocationId() method, Exception - ",e);
			return null;
		}
	}
	
	
	public String calculatePercentage(Long totalVoters,Long count)
	{
		try{
			if(totalVoters != null && totalVoters.longValue() > 0l)
			  return (new BigDecimal((count * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
	public Long getMemberShipCount(String locationType, Long locationId,Long year,Long constituencyId,List<Long> constituencyIds)
	{
		Long count = 0l;
		try{
			count =  tdpCadreDAO.getMemberShipRegistrationsInCadreLocation(locationType, locationId, year,constituencyId,constituencyIds);
			return (count != null?count:0l);
		}catch (Exception e) {
			LOG.error("Exception Occured in setMemberShipCount() method, Exception - ",e);
			return count;
		}
		
	}
	
	public List<RegisteredMembershipCountVO> getElectionPerformanceInCadreLocation(Long tdpCadreId)
	{
		List<RegisteredMembershipCountVO> resultList = new ArrayList<RegisteredMembershipCountVO>();
		try{
			
			UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
			if(userAddress != null)
			{
				List<Long> partyIds = new ArrayList<Long>();
				partyIds.add(872l);//TDP partyId
				partyIds.add(163l);//BJP partyId
				
				if(userAddress != null)
				{
					RegisteredMembershipCountVO countVO = null;
					 countVO = setElectionPerformanceDetailsInCadreLocation(2014l, userAddress, partyIds);
					if(countVO != null)
						resultList.add(countVO);
					
					countVO = setElectionPerformanceDetailsInCadreLocation(2009l, userAddress, partyIds);
					if(countVO != null)
						resultList.add(countVO);
				}
		}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getElectionPerformanceInCadreLocation() method, Exception - ",e);
		}
		return resultList;
	}
	
	
	public RegisteredMembershipCountVO setElectionPerformanceDetailsInCadreLocation(Long year,UserAddress userAddress,List<Long> partyIds)
	{
		RegisteredMembershipCountVO countVO = new RegisteredMembershipCountVO();
		try{
			Long electionId = 258l;//2014 Assembly
			if(year.longValue() == 2009l)
				electionId = 38l;
			
			Long constituencyId = userAddress.getConstituency().getConstituencyId();
			countVO.setAreaType(userAddress.getConstituency().getAreaType());
			
			countVO.setYear(year.toString());
			countVO.setConsTotalVoters(getTotalVotersByLocationId(constituencyId,"Constituency", electionId, constituencyId,null));
			countVO.setConstituencyCount(getTotalVotesEarnedForLocation(constituencyId, "Constituency", electionId, constituencyId, partyIds,null));
			countVO.setConstiPerc(calculatePercentage(countVO.getConsTotalVoters(),countVO.getConstituencyCount()));
			
			
			
			if(userAddress.getBooth() != null && userAddress.getBooth().getBoothId() != null)
			{
				countVO.setBoothCount(getTotalVotesEarnedForLocation(userAddress.getBooth().getBoothId(), "booth", electionId, constituencyId, partyIds,null))	;
				countVO.setBoothTotVoters(getTotalVotersByLocationId(userAddress.getBooth().getBoothId(), "booth", electionId, constituencyId,null));
				countVO.setBoothPerc(calculatePercentage(countVO.getBoothTotVoters(), countVO.getBoothCount()));
			}
			
			if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() != null)
			{
				countVO.setMandalCount(getTotalVotesEarnedForLocation(userAddress.getLocalElectionBody().getLocalElectionBodyId(), "Muncipality", electionId, constituencyId, partyIds,null));
				countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getLocalElectionBody().getLocalElectionBodyId(), "Muncipality", electionId, constituencyId,null));
				countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
				countVO.setCadreLocation("Muncipality");
			}
			else
			{
				countVO.setCadreLocation("Mandal");
				if(userAddress.getPanchayat() != null && userAddress.getPanchayat().getPanchayatId() != null)
				{
					countVO.setPanchayatCount(getTotalVotesEarnedForLocation(userAddress.getPanchayat().getPanchayatId(), "Panchayat", electionId, constituencyId, partyIds,null));
					countVO.setPanchayatTotVoters(getTotalVotersByLocationId(userAddress.getPanchayat().getPanchayatId(), "Panchayat", electionId, constituencyId,null));
					countVO.setPanchPerc(calculatePercentage(countVO.getPanchayatTotVoters(), countVO.getPanchayatCount()));
					
					if(countVO.getPanchayatTotVoters() != null && countVO.getPanchayatTotVoters() > 0)
					{
				        if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() != null)
						{
						  countVO.setMandalCount(getTotalVotesEarnedForLocation(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId, partyIds,null));	
						  countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId,null));
						  countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
						}
					}
				}
				
			}
			
			
			if(userAddress.getDistrict() != null && userAddress.getDistrict().getDistrictId() != null)
			{
				countVO.setDistrictCount(getTotalVotesEarnedForLocation(userAddress.getDistrict().getDistrictId(), "District", electionId, constituencyId, partyIds,null));
				countVO.setDistrictTotVoters(getTotalVotersByLocationId(userAddress.getDistrict().getDistrictId(), "District", electionId, constituencyId,null));
				countVO.setDistrictPerc(calculatePercentage(countVO.getDistrictTotVoters(), countVO.getDistrictCount()));
			}
			
			Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
			if(parliamentConId != null && parliamentConId > 0)
			{
					List<Long> assConstituencyIds = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
					if(assConstituencyIds != null && assConstituencyIds.size() > 0)
					{
						countVO.setParConsCount(getTotalVotesEarnedForLocation(null, "Parliament", electionId, null, partyIds,assConstituencyIds));
						countVO.setParConsTotVoters(getTotalVotersByLocationId(null, "Parliament", electionId, null,assConstituencyIds));
						countVO.setParConsPerc(calculatePercentage(countVO.getParConsTotVoters(), countVO.getParConsCount()));
					}
			}
			
			
		}catch (Exception e) {
			
			LOG.error(" Exception Occured in setElectionPerformanceDetailsInCadreLocation() method, Exception - ",e);
		}
		return countVO;
	}
	
	
	public Long getTotalVotesEarnedForLocation(Long locationId,String locationType,Long electionId,Long constituencyId,List<Long> partyIds,List<Long> constituencyIds)
	{
		Long count = 0l;
		try{
			if(electionId.longValue() == 38l && locationType.equalsIgnoreCase("Panchayat"))
			{
				List<Long> boothIdsList = panchayatHamletDAO.getBoothIdsByPanchayatId(locationId, electionId);
				if(boothIdsList != null && boothIdsList.size() > 0);
				  return candidateBoothResultDAO.getTotalEarnedVotesByBoothIdsList(boothIdsList, electionId,partyIds);
			}
			else
			{	
		       count = candidateBoothResultDAO.getTotalAndEarnedVotesForLocation(locationId, locationType, electionId, constituencyId, partyIds,constituencyIds);
		       return (count != null?count:0l);	
			}
		}catch (Exception e) {
			LOG.error(" Exception Occured in getTotalVotesEarnedForLocation() method, Exception - ",e);
		}
		return count;
	}
	
	
	public List<GrievanceAmountVO> getApprovedFinancialSupprotForCadre(Long tdpCadreId)
	{
		List<GrievanceAmountVO> resultList = new ArrayList<GrievanceAmountVO>();
		try{
			String memberShipId = tdpCadreDAO.getMemberShipIdByCadreId(tdpCadreId);
			if(memberShipId != null)
			{
				String regex = "[0-9]+";
			  List<Object[]> list = tdpCadreDAO.getPartyApprovedFundByMembershipId(memberShipId);
			  setGrievanceAmountVO(resultList, list, "Party Fund");
			 
			  List<Object[]> list2 = tdpCadreDAO.getGovtApprovedFundByMembershipId(memberShipId);
			  setGrievanceAmountVO(resultList, list2, "CM Relief Fund");
			  
			  List<Object[]> expectedAmtList = tdpCadreDAO.getRequestedAmountByMembershipId(memberShipId);
			  if(expectedAmtList != null && expectedAmtList.size() > 0)
			  {
				  Long amount = 0l;
				  Long count = 0l;
				  for(Object[] params:expectedAmtList)
				  {
					  if(params[0] != null && params[0].toString().matches(regex))
						 amount = amount + (Long)params[0]; 
					  if(params[1] != null && params[1].toString().matches(regex))
						  amount = amount + (Long)params[1]; 
					  
					  count = params[2] != null?Long.parseLong(params[2].toString()):0l;
					  
					  
				  }
				  resultList.get(0).setTotalRequests(amount); 
				  resultList.get(0).setTotalRequests(count);
			  }
			  
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getApprovedFinancialSupprotForCadre() method, Exception - ",e);
		}
		
		return resultList;
		
	}
	
	public void setGrievanceAmountVO(List<GrievanceAmountVO> resultList,List<Object[]> list,String name)
	{
		try{
			if(list != null && list.size() > 0)
			  {
				String regex = "[0-9]+";
				  GrievanceAmountVO amountVO = new GrievanceAmountVO();
				  amountVO.setName(name);
				  Object[] obj = list.get(0);
				  amountVO.setCount(obj[0] != null?Long.parseLong(obj[0].toString()):0l);
				  if(obj[1] != null && obj[1].toString().matches(regex))
					  amountVO.setDonationAmount((Long)obj[1]); 
				  resultList.add(amountVO);
			  }
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in setGrievanceAmountVO() method, Exception - ",e);
		}
	}
	
	public List<TdpCadreFamilyDetailsVO> getCadreFamilyDetails(Long tdpCadreId)
	{
		List<TdpCadreFamilyDetailsVO> resultList = null;
		try{
			resultList = cadreRegistrationService.getFamilyDetailsByCadreId(tdpCadreId);
			if(resultList != null && resultList.size() > 0)
			{
				List<String> voterIdCardNoList = new ArrayList<String>(0);
				for(TdpCadreFamilyDetailsVO detailsVO:resultList)
					if(detailsVO.getVotercardNo() != null)
						voterIdCardNoList.add(detailsVO.getVotercardNo());
				
				if(voterIdCardNoList != null && voterIdCardNoList.size() > 0)
				{
					List<Object[]> list = tdpCadreDAO.getSurveyPaticipatedCountByVoterIdcardNoList(voterIdCardNoList);
					if(list != null && list.size() > 0)
					{
						for(Object[] params: list)
						{
							if(params[1] != null)
							{
								TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(resultList, params[1].toString());
							    if(VO != null)
							    	VO.setCount(params[1] != null?Long.parseLong(params[1].toString()):0l);
							}
						}
					}
				}
				
			}
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCadreFamilyDetails() method, Exception - ",e);
		}
		return resultList;
	}
	
	
	public TdpCadreFamilyDetailsVO getMatchedTdpCadreFamilyDetailsVO(List<TdpCadreFamilyDetailsVO> resultList,String voterIdCardNO)
	{
		try{
			for(TdpCadreFamilyDetailsVO detailsVO:resultList)
				if(detailsVO.getVotercardNo() != null && detailsVO.getVotercardNo().equalsIgnoreCase(voterIdCardNO))
					return detailsVO;
			return null;
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getMatchedTdpCadreFamilyDetailsVO() method, Exception - ",e);
			return null;
		}
	}
	public WebServiceResultVO getCandidateAndLocationSummaryNews(String startDate,String endDate,String locationType,Long locationId,Long candidateId){
		WebServiceResultVO webServiceResultVO=new WebServiceResultVO();
		try{
			webServiceResultVO=webServiceHandlerService.getCandidateAndLocationSummaryNews(startDate,endDate,locationType,locationId,candidateId);
			
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCandidateAndLocationSummaryNews() method, Exception - ",e);
			return null;
		}
		
		return webServiceResultVO;
	}
	
	
	public Long getCadreIdByMembershipId(String memberShipNo)
	{
		try{
			Long cadreId =  tdpCadreDAO.getCadreIdByMembershipId(memberShipNo) ;
			return cadreId;
		}catch (Exception e) {
			LOG.error(" Exception Occured in getCadreIdByMembershipId() method, Exception - ",e);
			return null;
		}
		
	}
	
	
  public ComplaintStatusCountVO getCategoryWiseStatusCount(Long tdpCadreId)
  {
	  ComplaintStatusCountVO returnVo = null;
		try{
			String memberShipNo = tdpCadreDAO.getMemberShipIdByCadreId(tdpCadreId);
			 if(memberShipNo != null)
			 {
				   List<Object[]> list = tdpCadreDAO.getCategorywiseStatusCount(memberShipNo);//0typeOfissue,1count,2 completedstatus
				    
				   if(list != null && list.size() > 0)
				   {
					   Long totalComplaints = 0l;
				    	 returnVo = new ComplaintStatusCountVO();
					     List<ComplaintStatusCountVO> statusList = new ArrayList<ComplaintStatusCountVO>();
					     returnVo.setSubList(statusList);
				    	
						 setStatusList1(statusList);//ProblemStatus
						 for(Object[] params: list)
						 {
							 ComplaintStatusCountVO statusVO = getMatchedVO(params[2].toString(),statusList);
							 totalComplaints += (params[1] != null?(Long)params[1]:0l);
							 if(statusVO != null)
							 {
								 statusVO.setCount(params[1] != null?statusVO.getCount()+(Long)params[1]:0l);
							 }
						 }
						 
						 for(ComplaintStatusCountVO vo : statusList)//typeOfissue
						 {
							 List<ComplaintStatusCountVO> typeOfIssueVOList = new ArrayList<ComplaintStatusCountVO>();
							 setTypeOfIssueVO(typeOfIssueVOList);
							 vo.setSubList(typeOfIssueVOList);
						 }
						 
						 for(Object[] params: list)
						 {
							 ComplaintStatusCountVO statusVO = getMatchedVO(params[2].toString(),statusList);
							 if(statusVO != null)
							 {
								 ComplaintStatusCountVO typeOfIssueVO = getMatchedVO(params[0].toString(),statusVO.getSubList());
								 if(typeOfIssueVO != null)
								 {
									 typeOfIssueVO.setCount(params[1] != null?(Long)params[1]:0l);
								 }
							 }
							 
					 }
					 returnVo.setCount(totalComplaints);
				}
			}
		 }
			catch (Exception e) {
				LOG.error("Exception Occured in getCategoryWiseStatusCount() method, Exception - ",e);
			}
			return returnVo;
	 }
	
	
	public ComplaintStatusCountVO getMatchedVO(String status,List<ComplaintStatusCountVO> statusList)
	{
		try{
			for(ComplaintStatusCountVO statusCountVO: statusList)
			{
				if(statusCountVO.getStatus().equalsIgnoreCase(status))
					return statusCountVO;
			}
			
			return null;
		}catch (Exception e) {
			LOG.error("Exception Occured in getMatchedVO() method, Exception -",e);
			return null;
		}
	}
	
	public void setStatusList1(List<ComplaintStatusCountVO> statusList)
	{
	   ComplaintStatusCountVO vo = null;
	
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Verified");
		vo.setColor("#D64D54");
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Verified");
		vo.setColor("#481557");
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Eligible");
		vo.setColor("#663300");
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("In Progress");
		vo.setColor("#66CDCC");
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Possible");
		vo.setColor("#FF9933");
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Approved");
		vo.setColor("#69A78F");
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Completed");
		vo.setColor("#00B17D");
		statusList.add(vo);
	}
	 
	 public void setTypeOfIssueVO(List<ComplaintStatusCountVO> list)
	 {
		 try{
			 
			    ComplaintStatusCountVO vo = null;
			    
			    vo = new ComplaintStatusCountVO();
				vo.setStatus("Govt");
				list.add(vo);
				
				vo = new ComplaintStatusCountVO();
				vo.setStatus("Party");
				list.add(vo);
				
				vo = new ComplaintStatusCountVO();
				vo.setStatus("Welfare");
				list.add(vo);
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setTypeOfIssueVO() method, Exception - ",e);
		}
		 
	 }
	
	
	
}
