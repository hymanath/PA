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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateContestedLocationDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.IEventTypeDAO;
import com.itgrids.partyanalyst.dao.IInsuranceTypeDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInsuranceInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreOverviewVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.ComplaintStatusCountVO;
import com.itgrids.partyanalyst.dto.GrievanceAmountVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.TdpCadreFamilyDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
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
	private ITdpCommitteeDAO tdpCommitteeDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private ITdpCadreInsuranceInfoDAO tdpCadreInsuranceInfoDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IInsuranceTypeDAO insuranceTypeDAO;
	private ICandidateContestedLocationDAO candidateContestedLocationDAO; 
	private IEventTypeDAO eventTypeDAO;
	private IEventInviteeDAO eventInviteeDAO;
	private ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO;
	private IPartyMeetingService partyMeetingService;
	private IMahaNaduService mahaNaduService;
	private IVoterDAO voterDAO;
	private IMobileNumbersDAO mobileNumbersDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPublicRepresentativeDAO publicRepresentativeDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	private IDistrictDAO districtDAO;
	private IStateDAO stateDAO;
	
	
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IPublicRepresentativeDAO getPublicRepresentativeDAO() {
		return publicRepresentativeDAO;
	}

	public void setPublicRepresentativeDAO(
			IPublicRepresentativeDAO publicRepresentativeDAO) {
		this.publicRepresentativeDAO = publicRepresentativeDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IMobileNumbersDAO getMobileNumbersDAO() {
		return mobileNumbersDAO;
	}

	public void setMobileNumbersDAO(IMobileNumbersDAO mobileNumbersDAO) {
		this.mobileNumbersDAO = mobileNumbersDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}

	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}

	public IPartyMeetingService getPartyMeetingService() {
		return partyMeetingService;
	}

	public void setPartyMeetingService(IPartyMeetingService partyMeetingService) {
		this.partyMeetingService = partyMeetingService;
	}

	public IEventInviteeDAO getEventInviteeDAO() {
		return eventInviteeDAO;
	}

	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}

	public IEventTypeDAO getEventTypeDAO() {
		return eventTypeDAO;
	}

	public void setEventTypeDAO(IEventTypeDAO eventTypeDAO) {
		this.eventTypeDAO = eventTypeDAO;
	}


	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}


	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}


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

	public ITdpCommitteeDAO getTdpCommitteeDAO() {
		return tdpCommitteeDAO;
	}
	public void setTdpCommitteeDAO(ITdpCommitteeDAO tdpCommitteeDAO) {
		this.tdpCommitteeDAO = tdpCommitteeDAO;
	}

	public ITdpCadreInsuranceInfoDAO getTdpCadreInsuranceInfoDAO() {
		return tdpCadreInsuranceInfoDAO;
	}

	public void setTdpCadreInsuranceInfoDAO(
			ITdpCadreInsuranceInfoDAO tdpCadreInsuranceInfoDAO) {
		this.tdpCadreInsuranceInfoDAO = tdpCadreInsuranceInfoDAO;
	}

	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}


	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	
	public IInsuranceTypeDAO getInsuranceTypeDAO() {
		return insuranceTypeDAO;
	}


	public void setInsuranceTypeDAO(IInsuranceTypeDAO insuranceTypeDAO) {
		this.insuranceTypeDAO = insuranceTypeDAO;
	}
	public ICandidateContestedLocationDAO getCandidateContestedLocationDAO() {
		return candidateContestedLocationDAO;
	}


	public void setCandidateContestedLocationDAO(
			ICandidateContestedLocationDAO candidateContestedLocationDAO) {
		this.candidateContestedLocationDAO = candidateContestedLocationDAO;
	}
	
	public ITdpCadreEnrollmentYearDAO getTdpCadreEnrollmentYearDAO() {
		return tdpCadreEnrollmentYearDAO;
	}

	public void setTdpCadreEnrollmentYearDAO(
			ITdpCadreEnrollmentYearDAO tdpCadreEnrollmentYearDAO) {
		this.tdpCadreEnrollmentYearDAO = tdpCadreEnrollmentYearDAO;
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
    			
    			else if(locationLevel.longValue() == 3L) //district
    			{
    				queryStr.append(" and model.userAddress.district.districtId =:locationValue ");
    			}
    			
    			else if(locationLevel.longValue() == 4L)//constituency
    			{
    				queryStr.append(" and model.userAddress.constituency.constituencyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 5L)//tehsil
    			{
    				queryStr.append(" and model.userAddress.tehsil.tehsilId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 6L)//panchayat
    			{
    				queryStr.append(" and model.userAddress.panchayat.panchayatId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 7L)//localElectionBody
    			{
    				queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId =:locationValue ");
    			}
    			else if(locationLevel.longValue() == 8L)//greater cities
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
				//25.panchayatId,26.tehsilId,27.districtId,28.stateId,29.pConstId,30.pName,35.areaType
				Object[] cadreFormalDetails=tdpCadreDAO.cadreFormalDetailedInformation(cadreId,2014l);
				
				//0.tdpCommitteeLevel,1.role
				Object[] partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionBycadre(cadreId);
				//0.publicRepresentativeTypeId,1.type
				List<Object[]> publicRepDertails=tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadre(cadreId);
				
				List<Long> candidateList =tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
				
				
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
					//cadreDetailsVO.setpConstituencyId(cadreFormalDetails[29] !=null ? Long.parseLong(cadreFormalDetails[29].toString()):0l);
					//cadreDetailsVO.setpConstituencyName(cadreFormalDetails[30] !=null ? cadreFormalDetails[30].toString(): "");
					cadreDetailsVO.setBoothId(cadreFormalDetails[31] !=null ? Long.parseLong(cadreFormalDetails[31].toString()):0l);
					cadreDetailsVO.setPartNo(cadreFormalDetails[32] !=null ? cadreFormalDetails[32].toString():"0");
					
					if(cadreDetailsVO.getPanchayatId() == null || cadreDetailsVO.getPanchayatId().longValue() == 0L)
						cadreDetailsVO.setPanchayatId(cadreFormalDetails[33] !=null ? Long.parseLong(cadreFormalDetails[33].toString()) :0l);
					
					List<Object[]> pList = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(cadreDetailsVO.getConstituencyId(),2009L);
					if(pList != null && pList.size()>0){
						Object[] parliament = pList.get(0);
						cadreDetailsVO.setpConstituencyId(commonMethodsUtilService.getLongValueForObject(parliament[0]));
						cadreDetailsVO.setpConstituencyName(commonMethodsUtilService.getStringValueForObject(parliament[1]));
					}
				}
				
				if(partyPositionDetails !=null)
				{
					String level=partyPositionDetails[0] != null ? partyPositionDetails[0].toString() : "" ;
					String role=partyPositionDetails[1] != null ? partyPositionDetails[1].toString() : "";
					String commiteestr=partyPositionDetails[2] != null ? partyPositionDetails[2].toString() : "";
					
					cadreDetailsVO.setPartyPosition(level +" " +role+" ( "+commiteestr+" )");
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
				cadreDetailsVO.setCandidate(candidateList !=null && candidateList.size()>0? candidateList.get(0).longValue():0l);
					
				cadreDetailsVO.setCadreId(cadreId);
				cadreDetailsVO.setAreaType(cadreFormalDetails[35] !=null ? cadreFormalDetails[35].toString() :"");
				
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
				List<Long> localElctioniBodyIds=boothDAO.getLocalElectionBody(cadreDetailsVO.getTehsilId());
				if(localElctioniBodyIds != null && localElctioniBodyIds.size()>0)
				{
					Long localElctioniBodyId = localElctioniBodyIds.get(0);
					cadreDetailsVO.setLocalElectionBody(localElctioniBodyId !=null ? localElctioniBodyId.longValue() : 0l);
				}
				
			}
			
			//adding previous Enrollment Years
			
			//Long maxEnrollmentYear=tdpCadreEnrollmentYearDAO.getMaxRecordFromEnrollmentYear(cadreId);
			List<Long> enrollmentYears = tdpCadreEnrollmentYearDAO.getPreviousElectionYearsOfCadre(cadreId);
			
			String cadreEnrollmentYears = "";
			if(enrollmentYears !=null && enrollmentYears.size() >0 ){
				for (Long long1 : enrollmentYears) {
					cadreEnrollmentYears += long1.toString()+",";
				}
			}
			if(cadreEnrollmentYears !=null && !cadreEnrollmentYears.isEmpty()){
				cadreEnrollmentYears = cadreEnrollmentYears.substring(0,cadreEnrollmentYears.length()-1);
			}
			
			cadreDetailsVO.setEnrollmentYears(cadreEnrollmentYears);
			
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
			
			
			List<Object[]> eventTypeList = eventTypeDAO.getEventTypeDetails();
			Map<Long,String> evetTypeNamesMap = new LinkedHashMap<Long, String>(0);
			if(eventTypeList != null && eventTypeList.size()>0)
			{
				for (Object[] event : eventTypeList) {
					evetTypeNamesMap.put(event[0] !=null ? Long.parseLong(event[0].toString()) :0l, event[1] !=null ? event[1].toString():"");
				}
			}
			List<Object[]> eventInviteesList = eventInviteeDAO.getInvitationCountforCandidate(cadreId);
			Map<Long,Long> eventInviteeMap = new LinkedHashMap<Long, Long>(0);
			if(eventInviteesList != null && eventInviteesList.size()>0)
			{
				for (Object[] event : eventInviteesList) {
					eventInviteeMap.put(event[0] !=null ? Long.parseLong(event[0].toString()) :0l,event[2] !=null ? Long.parseLong(event[2].toString()) :0l);
				}
			}
			
			//0.tdpCadreId,1.eventId,2.name,3.description,4.parentEventId,5.count
			List<Object[]> eventDetails=eventAttendeeDAO.getEventDetailsOfCadre(cadreId);
			
			
			//parentId,eventId
			List<CadreCommitteeMemberVO> list=new ArrayList<CadreCommitteeMemberVO>();
			
			Map<Long,Map<Long,Map<Long,CadreCommitteeMemberVO>>> eventTypeWiseEventsMap = new LinkedHashMap<Long, Map<Long,Map<Long,CadreCommitteeMemberVO>>>();
			if(eventDetails !=null && eventDetails.size()>0){
				
				for (Object[] event : eventDetails) {
					CadreCommitteeMemberVO subEventDetails = null;
					Map<Long,CadreCommitteeMemberVO>  mainEventMap = null;
					Map<Long,Map<Long,CadreCommitteeMemberVO>> finalMap=new HashMap<Long, Map<Long,CadreCommitteeMemberVO>>();
					if(eventTypeWiseEventsMap.get(event[6] !=null ? Long.parseLong(event[6].toString()) :0l) != null)
					{
						finalMap = eventTypeWiseEventsMap.get(event[6] !=null ? Long.parseLong(event[6].toString()) :0l);
					}
					
					mainEventMap=finalMap.get(Long.parseLong(event[4].toString()));
					
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
							subEventDetails.setInvitationCount(eventInviteeMap.get(event[4] !=null ? Long.parseLong(event[4].toString()) :0l));
							String parentEventName=eventDAO.getEventName(subEventDetails.getLevel());
							
							if(parentEventName !=null){
								subEventDetails.setType(parentEventName);//parentEventName
							}
							
							subEventDetails.setTotal(event[5] !=null ? Long.parseLong(event[5].toString()) :0l);
							subEventDetails.setEventTypeId(event[6] !=null ? Long.parseLong(event[6].toString()) :0l);
							subEventDetails.setEventTypeStr(evetTypeNamesMap.get(subEventDetails.getEventTypeId()).trim());
							
							if(subEventDetails.getInvitationCount() != null && subEventDetails.getInvitationCount()>0L)
							{
								Long attendedCount = subEventDetails.getTotal();
								if(attendedCount != null && attendedCount.longValue() ==0L)
									subEventDetails.setAbsentCount(0L);
							}
							else
							{
								subEventDetails.setInvitationCount(0L);
							}
							mainEventMap.put(Long.parseLong(event[1].toString()), subEventDetails);
							
							finalMap.put(Long.parseLong(event[4].toString()), mainEventMap);
							eventTypeWiseEventsMap.put(subEventDetails.getEventTypeId(), finalMap);
					 }
				
			}
			else if(eventInviteeMap != null && eventInviteeMap.size()>0)
			{
				if(eventInviteesList != null && eventInviteesList.size()>0)
				{
					for (Object[] events : eventInviteesList) {
						CadreCommitteeMemberVO subEventDetails = null;
						Map<Long,CadreCommitteeMemberVO>  mainEventMap = null;
						Map<Long,Map<Long,CadreCommitteeMemberVO>> finalMap=new HashMap<Long, Map<Long,CadreCommitteeMemberVO>>();
						if(eventTypeWiseEventsMap.get(events[3] !=null ? Long.parseLong(events[3].toString()) :0l) != null)
						{
							finalMap = eventTypeWiseEventsMap.get(events[3] !=null ? Long.parseLong(events[3].toString()) :0l);
						}
						
						mainEventMap=finalMap.get(Long.parseLong(events[0].toString()));
						
						if(mainEventMap ==null){
							subEventDetails = new CadreCommitteeMemberVO();
							mainEventMap=new HashMap<Long, CadreCommitteeMemberVO>();
							finalMap.put(Long.parseLong(events[0].toString()),mainEventMap);//parentEventId,Map<EventId,CadreCommitteeMemberVO>
						}else{
							subEventDetails=mainEventMap.get(Long.parseLong(events[1].toString()));
							if(subEventDetails ==null){ 
								subEventDetails=new CadreCommitteeMemberVO();
							}
						}
								subEventDetails.setVtrId(cadreId);//cadreId
								subEventDetails.setId(Long.parseLong(events[0].toString()));//eventId
								subEventDetails.setName(" - ");//eventName
								subEventDetails.setLevel(Long.parseLong(events[0].toString()));//parentEventId
								subEventDetails.setInvitationCount(eventInviteeMap.get(events[0] !=null ? Long.parseLong(events[0].toString()) :0l));
								String parentEventName=eventDAO.getEventName(subEventDetails.getLevel());
								
								if(parentEventName !=null){
									subEventDetails.setType(parentEventName);//parentEventName
								}
								
								subEventDetails.setTotal(0l);
								subEventDetails.setEventTypeId(events[3] !=null ? Long.parseLong(events[3].toString()) :0l);
								subEventDetails.setEventTypeStr(evetTypeNamesMap.get(subEventDetails.getEventTypeId()).trim());
								
								if(subEventDetails.getInvitationCount() != null && subEventDetails.getInvitationCount()>0L)
								{
									Long attendedCount = subEventDetails.getTotal();
									if(attendedCount != null && attendedCount.longValue() ==0L)
										subEventDetails.setAbsentCount(subEventDetails.getInvitationCount());
								}
								
								mainEventMap.put(Long.parseLong(events[0].toString()), subEventDetails);
								
								finalMap.put(Long.parseLong(events[0].toString()), mainEventMap);
								eventTypeWiseEventsMap.put(subEventDetails.getEventTypeId(), finalMap);
						 }
				}
			}
			
			if(eventTypeWiseEventsMap != null && eventTypeWiseEventsMap.size()>0)
			{
				for (Long eventTypeId : eventTypeWiseEventsMap.keySet()) {
					CadreCommitteeMemberVO eventTypeWiseVO = new CadreCommitteeMemberVO();
					eventTypeWiseVO.setEventTypeStr(evetTypeNamesMap.get(eventTypeId));
					List<CadreCommitteeMemberVO> eventsList = new ArrayList<CadreCommitteeMemberVO>(0);
					Map<Long,Map<Long,CadreCommitteeMemberVO>> finalMap = eventTypeWiseEventsMap.get(eventTypeId);
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
							eventsList.add(cadreCommitteeMemberVO);
						}
					}
					eventTypeWiseVO.setKnownList(eventsList);
					finalList.add(eventTypeWiseVO);
				}
			}

			return finalList;

		}catch (Exception e) {
			LOG.error("Exception raised in getEventDetailsOfCadre service  method in CadreDetailsService.",e);
		}
		
		return finalList;
	}
	public VerifierVO getTdpCadreSurveyDetails(Long cadreId,Long surveyId,String searchTypeStr,Long boothId,String isPriority,String voterCardNo){
		VerifierVO verifierVO=new VerifierVO();
		try{
			verifierVO=webServiceHandlerService.getTdpCadreSurveyDetails(cadreId,surveyId,searchTypeStr,boothId,isPriority,voterCardNo);
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
			List<Long> candidateList=tdpCadreCandidateDAO.getTdpCadreCandidate(cadreId);
			if(candidateList !=null && candidateList.size()>0L){
				 candidateElecDatails=candidateDetailsService.getCandidateElectionDetails(candidateList.get(0));
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
			else{
				return "0";
			}
			
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
			
			
			boolean municpal2009=true;
			
			if(electionId == 38l){
				Long publicationDateId=3l;
				if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() != null){
					
					Long electionBodyId=boothDAO.getLocalElectionBodyDetails(userAddress.getLocalElectionBody().getLocalElectionBodyId(),constituencyId,publicationDateId);
					if(electionBodyId == null){
						municpal2009=false;
					}
					
				}
			}
			
			if(municpal2009){
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
			}
			else{
				if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() != null)
				{
				  countVO.setMandalCount(getTotalVotesEarnedForLocation(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId, partyIds,null));	
				  countVO.setMandalTotVoters(getTotalVotersByLocationId(userAddress.getTehsil().getTehsilId(), "Mandal", electionId, constituencyId,null));
				  countVO.setMandalPerc(calculatePercentage(countVO.getMandalTotVoters(), countVO.getMandalCount()));
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
			  setGrievanceAmountVO(resultList, list);
			 
			  List<Object[]> list2 = tdpCadreDAO.getGovtApprovedFundByMembershipId(memberShipId);
			  setGrievanceAmountVO(resultList, list2);
			  
			  List<Object[]> expectedAmtList = tdpCadreDAO.getRequestedAmountByMembershipId(memberShipId);
			  Long amount = 0l;
			  Long count = 0l;
			  if(expectedAmtList != null && expectedAmtList.size() > 0)
			  {
				  for(Object[] params:expectedAmtList)
				  {
					  if(params[0] != null && params[0].toString().matches(regex))
						 amount = amount + (Long)params[0]; 
					  if(params[1] != null && params[1].toString().matches(regex))
						  amount = amount + (Long)params[1]; 
					  
					  count = params[2] != null?Long.parseLong(params[2].toString()):0l;
					  
					  
				  }
			  }
			  
			  List<Object[]> educationalAmt = tdpCadreDAO.getEducationalRequestedAmountByMembershipId(memberShipId);
			  if(educationalAmt != null && educationalAmt.size() > 0)
			  {
				  for(Object[] params:educationalAmt)
				  {
					  if(params[0] != null && params[0].toString().matches(regex))
						amount = amount + (Long)params[0]; 
					  
					  count = count + (params[1] != null?Long.parseLong(params[1].toString()):0l);
				  }
			  }
			  resultList.get(0).setTotalRequests(amount); 
			  resultList.get(0).setTotalComplaints(count);
			  
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getApprovedFinancialSupprotForCadre() method, Exception - ",e);
		}
		
		return resultList;
		
	}
	
	/*public void setGrievanceAmountVO(List<GrievanceAmountVO> resultList,List<Object[]> list,String name)
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
	}*/
	
	public void setGrievanceAmountVO(List<GrievanceAmountVO> resultList,List<Object[]> list)
	{
		try{
			if(list != null && list.size() > 0)
			  {
				String regex = "[0-9]+";
				for(Object[] obj: list)
				{
					  GrievanceAmountVO amountVO = new GrievanceAmountVO();
					  amountVO.setName(obj[2] != null ?obj[2].toString():"");
					  amountVO.setCount(obj[0] != null?Long.parseLong(obj[0].toString()):0l);
					  if(obj[1] != null && obj[1].toString().matches(regex))
						  amountVO.setDonationAmount((Long)obj[1]); 
					  resultList.add(amountVO);
				}
				
				  
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
							    	VO.setCount(params[0] != null?Long.parseLong(params[0].toString()):0l);
							}
						}
					}
					
					List<Object[]> cadreMembersInFamilyList = tdpCadreDAO.checkVoterCardNosCadreNosOrNot(voterIdCardNoList);
					List<Long> tdpCadreIDsList = new ArrayList<Long>(0);
					if(cadreMembersInFamilyList != null && cadreMembersInFamilyList.size() > 0)
					{
						for(Object[] params: cadreMembersInFamilyList)
						{
							if(params[0] != null)
							{
								TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(resultList, params[0].toString());
							    if(VO != null){
							    	VO.setTdpCadreId(params[1] != null?Long.parseLong(params[1].toString()):0l);
							    	VO.setMembershipNo(params[2] != null?params[2].toString():"");
							    	tdpCadreIDsList.add(params[1] != null?Long.parseLong(params[1].toString()):0l);
							    }
							}
							
						}
					}
					
					if(tdpCadreIDsList != null && tdpCadreIDsList.size()>0)
					{
						List<Object[]> cadrePublicRepresentativList = tdpCadreCandidateDAO.getPublicRepresentaativesDetailsForCadreIdsList(tdpCadreIDsList);
						if(cadrePublicRepresentativList != null && cadrePublicRepresentativList.size()>0)
						{
							for (Object[] publicRepresentativ : cadrePublicRepresentativList) {
								if(publicRepresentativ[1] != null)
								{
									TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(resultList, publicRepresentativ[1].toString());
								    if(VO != null)
								    	VO.setPublicRepresentativeStr(publicRepresentativ[3] != null ? publicRepresentativ[3].toString():"");
								}
							}
						}
						
						List<Object[]> cadrePartyPositionDetals =  tdpCommitteeMemberDAO.getMembersInfoByTdpCadreIdsList(tdpCadreIDsList);
						if(cadrePartyPositionDetals != null && cadrePartyPositionDetals.size()>0)
						{
							for (Object[] partyPosition : cadrePartyPositionDetals) {
								if(partyPosition[1] != null)
								{
									TdpCadreFamilyDetailsVO vo =getMatchedTdpCadreFamilyDetailsVO(resultList, partyPosition[1].toString());
								    if(vo != null)
								    {
								    	Long committeeLevelId = partyPosition[2] != null?Long.parseLong(partyPosition[2].toString()):0l;
								    	
								    	if(committeeLevelId!= null && committeeLevelId.longValue()>0L)
								    	{
								    		//Long committeeLevelValue = partyPosition[3] != null?Long.parseLong(partyPosition[3].toString()):0l;
									    	String committeeStr = partyPosition[5] != null?partyPosition[5].toString():"";
									    	String roleStr = partyPosition[7] != null?partyPosition[7].toString():"";
									    	String committeeLevelStr = partyPosition[8] != null?partyPosition[8].toString():"";
									    	String committeeLocation ="";
									    	/*if(committeeLevelId == 5L)
									    	{
									    		committeeLocation = tehsilDAO.get(committeeLevelValue).getTehsilName()+" Mandal ";
									    	}
									    	else if(committeeLevelId == 6L)
									    	{
									    		committeeLocation = panchayatDAO.get(committeeLevelValue).getPanchayatName()+" Panchayat ";
									    	}
									    	else if(committeeLevelId == 7L || committeeLevelId == 9L) // town/division
									    	{
									    		LocalElectionBody localbody = localElectionBodyDAO.get(committeeLevelValue);
									    		if(localbody.getElectionType().getElectionTypeId() != 7L)
									    			committeeLocation = localbody.getName()+" "+localbody.getElectionType().getElectionType();
									    	}
									    	else if(committeeLevelId == 8L)
									    	{
									    		Constituency constituency = constituencyDAO.get(committeeLevelValue);
									    		committeeLocation = constituency.getName()+" ( "+constituency.getLocalElectionBody().getName()+" "+constituency.getLocalElectionBody().getElectionType().getElectionType()+" )";
									    	}
									    	else if(committeeLevelId == 10L)
									    	{
									    		committeeLocation = stateDAO.get(committeeLevelValue).getStateName();
									    	}
									    	else if(committeeLevelId == 11L)
									    	{
									    		committeeLocation = districtDAO.get(committeeLevelValue).getDistrictName();
									    	}
									    	vo.setPartyPositionStr(committeeStr+" - "+roleStr+" ( "+committeeLocation+" )");*/
									    	
									    	if(!committeeLocation.isEmpty())
									    		vo.setPartyPositionStr(committeeStr+" - "+roleStr+" ( "+committeeLocation+" )");
									    	else
									    		vo.setPartyPositionStr(committeeStr+" -"+committeeLevelStr+" "+roleStr);
								    	}
								    }
								}
							}
						}
						
						if(resultList != null && resultList.size()>0)
						{
							TdpCadreFamilyDetailsVO vo = resultList.get(0);
							vo.setFamilySurveyCount(Long.valueOf(String.valueOf(list.size())));
							vo.setPartyPositionsCount(Long.valueOf(String.valueOf(cadrePublicRepresentativList.size())));
							vo.setPartyPositionsCount(Long.valueOf(String.valueOf(cadrePartyPositionDetals.size())));
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
	
	
	public Long getCadreIdByMembershipId(String memberShipNo,Long constituencyId)
	{
		try{
			Long cadreId = null;
			List<Long> cadreIdsList =  tdpCadreDAO.getCadreIdByMembershipId(memberShipNo,constituencyId) ;
			if(cadreIdsList != null && cadreIdsList.size() > 0)
				cadreId = cadreIdsList.get(0);
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
		vo.setStatus("Completed");
		vo.setColor("#00B17D");
		vo.setStatusOrder(1l);
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("In Progress");
		vo.setColor("#66CDCC");
		vo.setStatusOrder(2l);
		statusList.add(vo);
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Verified");
		vo.setColor("#D64D54");
		vo.setStatusOrder(3l);
		statusList.add(vo);
		
		/*vo = new ComplaintStatusCountVO();
		vo.setStatus("Verified");
		vo.setColor("#481557");
		statusList.add(vo);*/
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Eligible");
		vo.setColor("#663300");
		vo.setStatusOrder(4l);
		statusList.add(vo);
		
		
		
		vo = new ComplaintStatusCountVO();
		vo.setStatus("Not Possible");
		vo.setColor("#FF9933");
		vo.setStatusOrder(5l);
		statusList.add(vo);
		
		/*vo = new ComplaintStatusCountVO();
		vo.setStatus("Approved");
		vo.setColor("#69A78F");
		statusList.add(vo);*/
		
		
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
	 
	 
	 public List<CommitteeBasicVO> getLocationwiseCommitteesCount(String locationType,Long tdpCadreId)
	 {
		 List<CommitteeBasicVO> committeeBasicVOList = null;
		
		try{
			UserAddress userAddress = tdpCadreDAO.get(tdpCadreId).getUserAddress();
			String areaType = userAddress.getConstituency().getAreaType();
			if(userAddress != null)
			{
				if(locationType.equalsIgnoreCase("district"))
					return getDistrictwiseCommitteCount(userAddress.getDistrict().getDistrictId(),areaType);
					
				else if(locationType.equalsIgnoreCase("mandal"))
				{
					return getMandalwiseCommitteCount(userAddress);
					
				}
				else if(locationType.equalsIgnoreCase("panchayat") && (areaType != null && (areaType.trim().equalsIgnoreCase("RURAL") || areaType.trim().equalsIgnoreCase("RURAL-URBAN"))))
				{
					if(userAddress.getPanchayat() != null && userAddress.getPanchayat().getPanchayatId() != null)
					{
						committeeBasicVOList = new ArrayList<CommitteeBasicVO>();
						List<Long> levelIdsList = new ArrayList<Long>();
						levelIdsList.add(6l);
						//levelIdsList.add(8l);
						CommitteeBasicVO villageVO = new CommitteeBasicVO();
						getPanchayatlwiseCommitteCount(userAddress.getPanchayat().getPanchayatId(),null,villageVO,levelIdsList,"Village");
						committeeBasicVOList.add(villageVO);
						return committeeBasicVOList;
					}
					else if(userAddress.getWard() !=null && userAddress.getWard().getConstituencyId() !=null){
						committeeBasicVOList = new ArrayList<CommitteeBasicVO>();
						List<Long> levelIdsList = new ArrayList<Long>();
						levelIdsList.add(8l);
						CommitteeBasicVO wardVO = new CommitteeBasicVO();
						getWardWiseCommitteeCount(userAddress.getWard().getConstituencyId(),null,wardVO,levelIdsList,"Ward");
						committeeBasicVOList.add(wardVO);
						return committeeBasicVOList;
						
					}
					
				}
				else if(locationType.equalsIgnoreCase("assemblyConstituency"))
					return  getConstituencyCommittesCount(userAddress.getConstituency().getConstituencyId(),"assemblyConstituency");
				
				else if(locationType.equalsIgnoreCase("parliamentConstituency"))
					return getConstituencyCommittesCount(userAddress.getConstituency().getConstituencyId(),"parliamentConstituency");
				
				
			}
				
			return committeeBasicVOList;
		}catch (Exception e) {
			LOG.error("Exception Occured in getLocationwiseCommitteesCount() method, Exception - ",e);
			return null;
		}
		
	 }
	 
	 
	 public List<CommitteeBasicVO> getDistrictwiseCommitteCount(Long districtId,String areaType)
	 {
		 List<CommitteeBasicVO> resultList = new ArrayList<CommitteeBasicVO>();
		 try{
			 
			 List<Long> levelIds = new ArrayList<Long>();
			 levelIds.add(11l);
			 
			 List<Long> districtIds = new ArrayList<Long>();
			 districtIds.add(districtId);
			 
			 setVillageCommitteesCount(districtIds, resultList);
			 setMandalCommitteesCount(districtIds,resultList);
			 setDistrictCommitteesCount(districtIds,resultList);
			 
			 if(resultList !=null && resultList.size()>0){
				 resultList.get(0).setAreaType(areaType);
			 }
			 
		 }catch (Exception e) {
				LOG.error("Exception Occured in getDistrictwiseCommitteCount() method, Exception - ",e);
			}
		 return resultList;
		 
	 }
	 
	 public void setDistrictCommitteesCount(List<Long> districtIds,List<CommitteeBasicVO> resultList)
	 {
		try{
			List<Long> levelIds = new ArrayList<Long>();
			 levelIds.add(11l);
			 
			 CommitteeBasicVO districtVO = new CommitteeBasicVO();
			 districtVO.setLocationType("District");
			
			 List<Object[]> list = tdpCommitteeMemberDAO.membersCountDistrictWise(levelIds, null, null, districtIds);//Total Committee Members Count
			  setTotalCommitteeMembersCount(list, districtVO);
			 
		     List<Object[]> list2 = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(districtIds, levelIds);//total committes Count
		     setTotalCommitteesCount(list2, districtVO);
		     
		     //0committeeId,1committeeTypeid
		     List<Object[]> stResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(levelIds, null, null, "started", districtIds); //changed
		     setStartedCommitteesCount(stResLst,districtVO);
		     
		   //0committeeId,1committeeTypeid
			 List<Object[]> endResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(levelIds, null, null, "completed", districtIds);//changed
			 setCompletedCommitteesCount(endResLst, districtVO);
			 
			 resultList.add(districtVO);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in setDistrictwiseCountToVO() method, Exception - ",e);
		}
	 }
	 
	 public void setMandalCommitteesCount(List<Long> districtIds,List<CommitteeBasicVO> resultList)
	 {
		try{
			CommitteeBasicVO mandalVO = new CommitteeBasicVO();
			mandalVO.setLocationType("Mandal");
			
			List<Long> mandalMunciDivisionIds = new ArrayList<Long>();
			mandalMunciDivisionIds.add(5l);
			mandalMunciDivisionIds.add(7l);
			mandalMunciDivisionIds.add(9l);
			
			List<Object[]> memResLst = tdpCommitteeMemberDAO.membersCountDistrictWise(mandalMunciDivisionIds, null, null, districtIds);
			setTotalCommitteeMembersCount(memResLst, mandalVO);
			
			List<Object[]> ttlList = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(districtIds, mandalMunciDivisionIds);
			setTotalCommitteesCount(ttlList, mandalVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> stResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(mandalMunciDivisionIds, null, null, "started", districtIds);//changed
			setStartedCommitteesCount(stResLst, mandalVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> endResLst = tdpCommitteeDAO.getcommitteesCountByDistrict(mandalMunciDivisionIds, null, null, "completed", districtIds);//changed
			setCompletedCommitteesCount(endResLst, mandalVO);
			
			resultList.add(mandalVO);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in setMandalCommitteesCountToVO() method, Exception - ",e);
		}
	 }
	 
	 
	 public void setVillageCommitteesCount(List<Long> districtIds,List<CommitteeBasicVO> resultList)
	 {
		try{
			CommitteeBasicVO villageVO = new CommitteeBasicVO();
			villageVO.setLocationType("Village");
			
			List<Long> villageWardIds = new ArrayList<Long>();
			villageWardIds.add(6l);
			villageWardIds.add(8l);
			
			List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountDistrictWise(villageWardIds, null, null, districtIds);
			setTotalCommitteeMembersCount(memResLstVill, villageVO);
			
			List<Object[]> ttlListVill = tdpCommitteeDAO.getCommitteesCountByDistrictIdAndLevel(districtIds, villageWardIds);
			setTotalCommitteesCount(ttlListVill, villageVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> stResLstVill = tdpCommitteeDAO.getcommitteesCountByDistrict(villageWardIds, null, null, "started", districtIds);//changged
			setStartedCommitteesCount(stResLstVill, villageVO);
			
			//0committeeId,1committeeTypeid
			List<Object[]> endResLstVill = tdpCommitteeDAO.getcommitteesCountByDistrict(villageWardIds, null, null, "completed", districtIds);//changed
			setCompletedCommitteesCount(endResLstVill, villageVO);
			
			resultList.add(villageVO);
		}catch (Exception e) {
			LOG.error("Exception Occured in setVillageCommitteesCount() method, Exception - ",e);
		}
	 }
	 
	 
	 
	 public void setTotalCommitteeMembersCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
						basicVO.setMainCommTotalMembers(basicVO.getMainCommTotalMembers()+(Long)params[0]); //total Main Committee Members
					 else
					   basicVO.setAffiCommTotalMembers(basicVO.getAffiCommTotalMembers()+(Long)params[0]);//total Affiliated Committees Members
				 } 
			 }
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setTotalCommitteeMembersCount() method, Exception - ",e);
		}
	 }
	 
	 
	 public void setTotalCommitteesCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
							basicVO.setMainCommTotalCount(basicVO.getMainCommTotalCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommTotalCount(basicVO.getAffiCommTotalCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setTotalCommitteesCount() method, Exception - ",e);
		}
	 }
	 
	 public void  setStartedCommitteesCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[1] != null && new Long(params[1].toString()).equals(1l))
							basicVO.setMainCommStartedCount(basicVO.getMainCommStartedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommStartedCount(basicVO.getAffiCommStartedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 public void  setStartedCommitteesCount1(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
							basicVO.setMainCommStartedCount(basicVO.getMainCommStartedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommStartedCount(basicVO.getAffiCommStartedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 public void  setCompletedCommitteesCount(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[1] != null && new Long(params[1].toString()).equals(1l))
							basicVO.setMainCommCompletedCount(basicVO.getMainCommCompletedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommCompletedCount(basicVO.getAffiCommCompletedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 public void  setCompletedCommitteesCount1(List<Object[]> list,CommitteeBasicVO basicVO)
	 {
		 try{
			 if(list != null && list.size() > 0)
			 {
				 for(Object[] params:list)
				 {
					 if(params[2] != null && new Long(params[2].toString()).equals(1l))
							basicVO.setMainCommCompletedCount(basicVO.getMainCommCompletedCount()+(Long)params[0]); //total Main Committees Count
					else
					  basicVO.setAffiCommCompletedCount(basicVO.getAffiCommCompletedCount()+(Long)params[0]);//total Affiliated Committees Count
				 }
			 }
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setStartedCommitteesCount() method, Exception - ",e);
		} 
	 }
	 
	 
	 public List<CommitteeBasicVO> getConstituencyCommittesCount(Long constituencyId ,String constituencyType)
	 {
		 List<CommitteeBasicVO> resultList = new ArrayList<CommitteeBasicVO>(); 
		 try{
			 
			 List<Long> constituenyIdsList = new ArrayList<Long>();
			 if(constituencyType.equalsIgnoreCase("assemblyConstituency"))
			   constituenyIdsList.add(constituencyId);
			 else
			 {
				 Long parliamentConId = delimitationConstituencyAssemblyDetailsDAO.getParliamentConstituencyId(constituencyId);
				   if(parliamentConId != null && parliamentConId > 0)
					  constituenyIdsList = delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentConId);
			 }
			 if(constituenyIdsList != null && constituenyIdsList.size() > 0)
			 {
				 setVillageCommitteesCountInConstituency(constituenyIdsList, resultList);
				 SetMandalCommitteesCountInConstituency(constituenyIdsList, resultList);
			 }
			 
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getConstituencyCommittesCount() method, Exception - ",e);
		}
		 return resultList;
	 }

	 
	 public void setVillageCommitteesCountInConstituency(List<Long> constituenyIdsList,List<CommitteeBasicVO> resultList)
	 {
		 try{
			 
			 CommitteeBasicVO villageCommitteeVO = new CommitteeBasicVO();
			 villageCommitteeVO.setLocationType("Village");
			 
			   List<Long> villageWardIds = new ArrayList<Long>();
				villageWardIds.add(6l);
				villageWardIds.add(8l);
				
				List<Object[]> memResLstVill = tdpCommitteeMemberDAO.membersCountConstituencyWise(villageWardIds, null, null, constituenyIdsList);
				setTotalCommitteeMembersCount(memResLstVill, villageCommitteeVO);
				
				List<Object[]> ttlV = tdpCommitteeDAO.getCommitteesCountByConstituencyIdAndLevel(constituenyIdsList, villageWardIds);
				setTotalCommitteesCount(ttlV, villageCommitteeVO);
				
				//0committeeId,1committeeTypeid
				List<Object[]> stResLstVill = tdpCommitteeDAO.getcommitteesCountByConstituency(villageWardIds, null, null, "started", constituenyIdsList);
				setStartedCommitteesCount(stResLstVill, villageCommitteeVO);
				
				//0committeeId,1committeeTypeid
				List<Object[]> endResLstVill = tdpCommitteeDAO.getcommitteesCountByConstituency(villageWardIds, null, null, "completed", constituenyIdsList);
				setCompletedCommitteesCount(endResLstVill, villageCommitteeVO);
				
				resultList.add(villageCommitteeVO);
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in setVillageCommitteesCountInConstituency() method, Exception - ",e);
		}
	 }
	 
	 public void SetMandalCommitteesCountInConstituency(List<Long> constituencyIds,List<CommitteeBasicVO> resultList)
	 {
		 try{
			   
			    CommitteeBasicVO mandalVO = new CommitteeBasicVO();
			    mandalVO.setLocationType("Mandal");
			   
			    Map<Long,List<Long>> mandalIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
				Map<Long,List<Long>> localBodiesMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
				List<Long> divisionLclIds = new ArrayList<Long>();//Map<id,List<1constituencyId>>
				Map<Long,List<Long>> divisionIdsMap = new HashMap<Long,List<Long>>();//Map<id,List<1constituencyId>>
				
				cadreCommitteeService.getLocationsInfo(constituencyIds, divisionLclIds, localBodiesMap, divisionIdsMap, mandalIdsMap);
				if(mandalIdsMap.size() > 0){
					List<Long> levelValues = new ArrayList<Long>(mandalIdsMap.keySet());
					
					List<Object[]> totalMandalMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(5l,null, null,levelValues);
					setTotalCommitteeMembersCount(totalMandalMainMembers, mandalVO);
					
					List<Object[]> totalMandalCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(5l,levelValues);
					setTotalCommitteesCount(totalMandalCommittees, mandalVO);
					
					//0count,1locationID,2typeId
					 List<Object[]> totalMandalStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,null,null,"started");
					 setStartedCommitteesCount1(totalMandalStartedCommittees, mandalVO);
					 
					//0count,1locationID,2typeId
					 List<Object[]> totalMandalCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(5l,levelValues,null,null,"completed");
					 setCompletedCommitteesCount1(totalMandalCompletedCommittees,mandalVO);
					
				}
				 if(localBodiesMap.size() > 0){
					 
					 List<Long> levelValues = new ArrayList<Long>(localBodiesMap.keySet());
					//0 count,1levelId
					 List<Object[]> totalLocalBodMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(7l,null,null,levelValues);
					 setTotalCommitteeMembersCount(totalLocalBodMainMembers, mandalVO);
					 
					 //0count,1locationID
					 List<Object[]> totalLocalBodCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(7l,levelValues);
					 setTotalCommitteesCount(totalLocalBodCommittees, mandalVO);
					 
					//0count,1locationID,2typeId
					 List<Object[]> totalLocalBodStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,null,null,"started");
					 setStartedCommitteesCount1(totalLocalBodStartedCommittees, mandalVO);
					 
					 //0count,1locationID,2typeId
					 List<Object[]> totalLocalBodCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(7l,levelValues,null,null,"completed");
					 setCompletedCommitteesCount1(totalLocalBodCompletedCommittees,mandalVO);
					 
				 }
				 
				 
				 if(divisionIdsMap.size() > 0){
					 List<Long> levelValues = new ArrayList<Long>(divisionIdsMap.keySet());
					 
					//0 count,1levelId
					 List<Object[]> totalDivisionMainMembers = tdpCommitteeMemberDAO.totalMainMembersCountLocationsWise(9l,null,null,levelValues);
					 setTotalCommitteeMembersCount(totalDivisionMainMembers, mandalVO);
					 
					 //0count,1locationID
					 List<Object[]> totalDivisionCommittees = tdpCommitteeDAO.totalCommitteesCountByLocationIds(9l,levelValues);
					 setTotalCommitteesCount(totalDivisionCommittees, mandalVO);
					 
					//0count,1locationID,2typeId
					 List<Object[]> totalDivisionStartedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,null,null,"started");
					 setStartedCommitteesCount1(totalDivisionStartedCommittees, mandalVO);
					 
					 //0count,1locationID,2typeId
					 List<Object[]> totalDivisionCompletedCommittees = tdpCommitteeDAO.committeesCountByLocationIds(9l,levelValues,null,null,"completed");
					 setCompletedCommitteesCount1(totalDivisionCompletedCommittees,mandalVO);
					 
				 }
				 resultList.add(mandalVO);
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in SetMandalCommitteesCountInConstituency() method, Exception - ",e);
		}
		 
	 }
	 
	 
	
	 
	 
	 public void getPanchayatlwiseCommitteCount(Long locationId,Long constituencyId,CommitteeBasicVO villageVO,List<Long> levelIdsList,String locationType)
	 {
		 try{
			 
				villageVO.setLocationType(locationType);
				
				List<Object[]> committeeMembersList = new ArrayList<Object[]>();//Total committee members count
				List<Object[]> committeesList = new ArrayList<Object[]>();//Total committees count
				List<Object[]> startedCommitteesList = new ArrayList<Object[]>();//Total started committees count
				List<Object[]> completedCommitteesList = new ArrayList<Object[]>();//Total completed committees count
				
				for(Long levelId: levelIdsList)
				{
					setTdpCommitteeMembersCount(levelId, locationId, null, committeeMembersList,locationType);	
					setTdpCommitteesCount(levelId, locationId, null, committeesList,locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, startedCommitteesList, "Started",locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, completedCommitteesList, "completed",locationType);
				}
				
				setTotalCommitteeMembersCount(committeeMembersList, villageVO);
				setTotalCommitteesCount(committeesList, villageVO);
				setStartedCommitteesCount(startedCommitteesList, villageVO);
				setCompletedCommitteesCount(completedCommitteesList, villageVO);
				
				
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getPanchayatlwiseCommitteCount() method, Exception - ",e);
		}
	 }
	 
	 public void getWardWiseCommitteeCount(Long locationId,Long constituencyId,CommitteeBasicVO wardVo,List<Long> levelIdsList,String locationType){
		 
		 try{
			 
			 	wardVo.setLocationType(locationType);
				
				List<Object[]> committeeMembersList = new ArrayList<Object[]>();//Total committee members count
				List<Object[]> committeesList = new ArrayList<Object[]>();//Total committees count//
				List<Object[]> startedCommitteesList = new ArrayList<Object[]>();//Total started committees count
				List<Object[]> completedCommitteesList = new ArrayList<Object[]>();//Total completed committees count
				
				for(Long levelId: levelIdsList)
				{
					setTdpCommitteeMembersCount(levelId, locationId, null, committeeMembersList,locationType);//clear	
					setTdpCommitteesCount(levelId, locationId, null, committeesList,locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, startedCommitteesList, "Started",locationType);
					setTdpStartedOrCompletedCommitteesCount(levelId, locationId, null, completedCommitteesList, "completed",locationType);
				}
				
				setTotalCommitteeMembersCount(committeeMembersList, wardVo);
				setTotalCommitteesCount(committeesList, wardVo);
				setStartedCommitteesCount(startedCommitteesList, wardVo);
				setCompletedCommitteesCount(completedCommitteesList, wardVo);
				
				
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getPanchayatlwiseCommitteCount() method, Exception - ",e);
		}
		 
		 
	 }
	 

	 
	
	public void setTdpCommitteeMembersCount(Long levelId,Long levelValue,Long constituencyId,List<Object[]> resultList,String locationType)
	{
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("mandalLevelVillage"))
				 list = tdpCommitteeMemberDAO.getTotalCommittesCountByLevelIdAndLevelValueForVillage(levelId, levelValue,constituencyId);
			else if(locationType.equalsIgnoreCase("municipalWiseWards")){
				list=tdpCommitteeMemberDAO.getTotalCommittesCountByLevelIdAndLevelValueForWards(levelId,levelValue,constituencyId);
			}
			else
				list= tdpCommitteeMemberDAO.getTotalCommittesCountByLevelIdAndLevelValue(levelId, levelValue,constituencyId);
			 if(list != null && list.size() > 0)
				 resultList.addAll(list);
			
		}catch (Exception e) {
			 LOG.error("Exception Occured in setTdpCommitteeMembersCount() method, Exception - ",e);
		}
		
	}
	
	public void setTdpCommitteesCount(Long levelId,Long levelValue,Long constituencyId,List<Object[]> resultList,String locationType)
	{
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("mandalLevelVillage"))
				 list = tdpCommitteeDAO.getCommitteesCountByLevelIdAndLevelValueForVillage(levelId, levelValue,constituencyId);
			else if(locationType.equalsIgnoreCase("municipalWiseWards")){
				list = tdpCommitteeDAO.getCommitteesCountByLevelIdAndLevelValueForVillageMunicipal(levelId, levelValue,constituencyId);
			}
			else
		     list = tdpCommitteeDAO.getCommitteesCountByLevelIdAndLevelValue(levelId, levelValue, null);
			 if(list != null && list.size() > 0)
				 resultList.addAll(list);
			
		}catch (Exception e) {
			 LOG.error("Exception Occured in setTdpCommitteesCount() method, Exception - ",e);
		}
		
	}
	
	public void setTdpStartedOrCompletedCommitteesCount(Long levelId,Long levelValue,Long constituencyId,List<Object[]> resultList,String status,String locationType)
	{
		try{
			List<Object[]> list = null;
			if(locationType.equalsIgnoreCase("mandalLevelVillage"))
				list = tdpCommitteeDAO.getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillage(levelId, levelValue, constituencyId, status);
			else if(locationType.equalsIgnoreCase("municipalWiseWards")){
				list = tdpCommitteeDAO.getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillageMunicipal(levelId, levelValue, constituencyId, status);
			}
			else
			list = tdpCommitteeDAO.getStartedOrComplcommitteesCountByLevelIdAndLevelValue(levelId, levelValue, constituencyId, status);
			 if(list != null && list.size() > 0)
				 resultList.addAll(list);
			
		}catch (Exception e) {
			 LOG.error("Exception Occured in setTdpStartedOrCompletedCommitteesCount() method, Exception - ",e);
		}
		
	}
	
	
	
	//Mandal wise Committees Count
	
	public List<CommitteeBasicVO> getMandalwiseCommitteCount(UserAddress userAddress)
	{
		List<CommitteeBasicVO> resultList = new ArrayList<CommitteeBasicVO>();
		try{
			
			Long constituencyId = userAddress.getConstituency().getConstituencyId();
			List<Long> localEleIdsList = new ArrayList<Long>();
			localEleIdsList.add(7l);
			
			List<Long> mandalIds = new ArrayList<Long>();
			mandalIds.add(5l);
			
			
			CommitteeBasicVO mandalMunciVO = new CommitteeBasicVO();
			
			if(userAddress.getLocalElectionBody() != null && userAddress.getLocalElectionBody().getLocalElectionBodyId() > 0)
			{
				getPanchayatlwiseCommitteCount(userAddress.getLocalElectionBody().getLocalElectionBodyId(), constituencyId, mandalMunciVO, localEleIdsList, "localElection");
				
				//ward committees count in municipality
				List<Long> levelIdsList = new ArrayList<Long>();
				levelIdsList.add(8l);
				CommitteeBasicVO villBasicVO = new CommitteeBasicVO();
			    getPanchayatlwiseCommitteCount(userAddress.getLocalElectionBody().getLocalElectionBodyId(), constituencyId, villBasicVO,levelIdsList,"municipalWiseWards");
			    
			    resultList.add(villBasicVO);
				
			}
			
			else if(userAddress.getTehsil() != null && userAddress.getTehsil().getTehsilId() > 0)
			{
				getPanchayatlwiseCommitteCount(userAddress.getTehsil().getTehsilId(), constituencyId, mandalMunciVO, mandalIds, "Mandal");
				
				
				//panchayat committees count in mandal
				List<Long> levelIdsList = new ArrayList<Long>();
				levelIdsList.add(6l);
				//levelIdsList.add(8l);
				CommitteeBasicVO villBasicVO = new CommitteeBasicVO();
			    getPanchayatlwiseCommitteCount(userAddress.getTehsil().getTehsilId(), constituencyId, villBasicVO,levelIdsList,"mandalLevelVillage");
			    
			    resultList.add(villBasicVO);
			   
			}
			
			
			resultList.add(mandalMunciVO);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getMandalwiseCommitteCount() method, Exception - ",e);
		}
		
		return resultList;
	}
/*	public List<CommitteeBasicVO> getLocalElectionBodyWiseCount(UserAddress userAddress){
		
		
		return null;
	}*/
	
	public void setInsuranceType(Map<String,VerifierVO> mapVo){
		
		List<Object[]> insuranceType=insuranceTypeDAO.getAllInsuranceType();
		
		
		if(insuranceType !=null && insuranceType.size()>0){
			
			for(Object[] object:insuranceType){
				
				VerifierVO verifierVo=new VerifierVO();
				verifierVo.setCount(0l);
				verifierVo.setId(Long.parseLong(object[0].toString()));//insurance typeId
				verifierVo.setName(object[1].toString());
				
				mapVo.put(verifierVo.getName(), verifierVo);
				
				
			}
			
		}
		
	}
	
	
	public VerifierVO getDeathsAndHospitalizationDetails(Long panchayatId,Long mandalId,Long constituencyId,Long parliamentId,Long districtId){
		
		VerifierVO finalVo=new VerifierVO();

		List<VerifierVO> mainList=new ArrayList<VerifierVO>();
		
		try{
			if(panchayatId !=0l){
				
				VerifierVO panchayatVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				panchayatVo.setVerifierVOMap(mapVerifierVo);
				
				List<Object[]> panchaytDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(panchayatId,"panchayat");
				if(panchaytDeathDetails !=null){
					for (Object[] objects : panchaytDeathDetails) {
						
						if(objects[2]!=null){
							VerifierVO reqTypeVO=panchayatVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
					}
					panchayatVo.setId(panchayatId);
					panchayatVo.setName("Panchayat");
					mainList.add(panchayatVo);
				}
				if(panchayatVo.getVerifierVOMap()!=null && panchayatVo.getVerifierVOMap().size()>0){
					panchayatVo.getVerifierVOList().addAll(panchayatVo.getVerifierVOMap().values());
				}
				panchayatVo.getVerifierVOMap().clear();
			}
			if(mandalId !=0l){
				
				VerifierVO tehsilVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				tehsilVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> tehsilList=new ArrayList<VerifierVO>();
				List<Object[]> mandalDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(mandalId,"mandal");
				if(mandalDeathDetails !=null){
					for (Object[] objects : mandalDeathDetails) {
							
						if(objects[2]!=null){
							VerifierVO reqTypeVO=tehsilVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
					}
					//tehsilVo.setVerifierVOList(tehsilList);
					tehsilVo.setId(mandalId);
					tehsilVo.setName("Mandal");
					mainList.add(tehsilVo);
				}
				if(tehsilVo.getVerifierVOMap()!=null && tehsilVo.getVerifierVOMap().size()>0){
					tehsilVo.getVerifierVOList().addAll(tehsilVo.getVerifierVOMap().values());
				}
				tehsilVo.getVerifierVOMap().clear();
			}
			if(constituencyId !=0l){
				
				VerifierVO constituencyVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				constituencyVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> constituencyList=new ArrayList<VerifierVO>();
				List<Object[]> constituencyDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(constituencyId,"constituency");
				if(constituencyDeathDetails !=null){
					for (Object[] objects : constituencyDeathDetails) {
						
						if(objects[2]!=null){
							VerifierVO reqTypeVO=constituencyVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
					}
					//constituencyVo.setVerifierVOList(constituencyList);
					constituencyVo.setId(constituencyId);
					constituencyVo.setName("Constituency");
					mainList.add(constituencyVo);
				}
				if(constituencyVo.getVerifierVOMap()!=null && constituencyVo.getVerifierVOMap().size()>0){
					constituencyVo.getVerifierVOList().addAll(constituencyVo.getVerifierVOMap().values());
				}
				constituencyVo.getVerifierVOMap().clear();
			}
			if(parliamentId !=0){
				VerifierVO parliamentVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				parliamentVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> parliamentDeathList=new ArrayList<VerifierVO>();
				
				List<Long> asemConstIds=null;
				
				asemConstIds=delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(parliamentId);
				
				if(asemConstIds !=null && asemConstIds.size()>0){
					List<Object[]> parliamentDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetailsForParliament(asemConstIds,"parliament");
					
					if(parliamentDeathDetails !=null){
						for (Object[] objects : parliamentDeathDetails) {
							if(objects[2]!=null){
								VerifierVO reqTypeVO=parliamentVo.getVerifierVOMap().get(objects[2].toString());
								reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
							}
						}
						//parliamentVo.setVerifierVOList(parliamentDeathList);
						parliamentVo.setId(parliamentId);
						parliamentVo.setName("Parliament");
						mainList.add(parliamentVo);
					}
					if(parliamentVo.getVerifierVOMap()!=null && parliamentVo.getVerifierVOMap().size()>0){
						parliamentVo.getVerifierVOList().addAll(parliamentVo.getVerifierVOMap().values());
					}
					parliamentVo.getVerifierVOMap().clear();
				} 
				
			}
			
			
			if(districtId !=0l){
				
				VerifierVO districtVo=new VerifierVO();
				Map<String,VerifierVO> mapVerifierVo=new LinkedHashMap<String, VerifierVO>();
				setInsuranceType(mapVerifierVo);
				districtVo.setVerifierVOMap(mapVerifierVo);
				
				//List<VerifierVO> districtList=new ArrayList<VerifierVO>();
				List<Object[]> districtDeathDetails=tdpCadreInsuranceInfoDAO.getDeathsAndHospitalizationDetails(districtId,"district");
				if(districtDeathDetails !=null){
					for (Object[] objects : districtDeathDetails) {
						if(objects[2]!=null){
							VerifierVO reqTypeVO=districtVo.getVerifierVOMap().get(objects[2].toString());
							reqTypeVO.setCount(objects[0] !=null ? (Long)objects[0]:0l);
						}
						
					}
					districtVo.setId(districtId);
					districtVo.setName("District");
					mainList.add(districtVo);
				}
				if(districtVo.getVerifierVOMap()!=null && districtVo.getVerifierVOMap().size()>0){
					districtVo.getVerifierVOList().addAll(districtVo.getVerifierVOMap().values());
				}
				districtVo.getVerifierVOMap().clear();
			}
			if(mainList !=null && mainList.size()>0){
				finalVo.setVerifierVOList(mainList);
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getDeathsAndHospitalizationDetails() method, Exception - ",e);
		}
		
		return finalVo;
	}
	
	public Long getTdpCadreIdBymembershipId(String membershipId)
	{
		try{
			List<Long> list = tdpCadreDAO.getTdpCadreIdByMembershipId(membershipId);
			if(list != null && list.size() > 0)
				return list.get(0);
		}catch(Exception e)
		{
			LOG.error("Exception occured in getTdpCadreIdBymembershipId() Method - ",e);
		}
		return null;
	}
	public CadreCommitteeMemberVO getCadresDetailsOfDeathsAndHospitalization(Long locationId,String locationType,Long insuranceTypeId){
		
		CadreCommitteeMemberVO finalcadreVo=new CadreCommitteeMemberVO();
		try{
			List<Long> cadreIds=null;
			if(!locationType.equalsIgnoreCase("parliament")){
				cadreIds=tdpCadreInsuranceInfoDAO.getCadresIdsByInsuranceType(locationId,locationType,insuranceTypeId);
			}
			else if(locationType.equalsIgnoreCase("parliament")){
				
				List<Long> asemConstIds=null;
				
				asemConstIds=delimitationConstituencyAssemblyDetailsDAO.getAssemblyConstituencyIdsByPCId(locationId);
				if(asemConstIds !=null && asemConstIds.size()>0)
				cadreIds=tdpCadreInsuranceInfoDAO.getCadresIdsByParliament(asemConstIds,locationType,insuranceTypeId);
				
			}
			if(cadreIds !=null && cadreIds.size()>0){
				finalcadreVo=getCadresDetailsOfDeathsAndHospitalization(cadreIds);
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception occured in getTdpCadreIdBymembershipId() Method - ",e);
		}
		return finalcadreVo;
	}
	public CadreCommitteeMemberVO getCadresDetailsOfDeathsAndHospitalization(List<Long> cadreIds){
		
		CadreCommitteeMemberVO finalcadreVo=new CadreCommitteeMemberVO();
		
		List<CadreCommitteeMemberVO> listOfCadreVO=new ArrayList<CadreCommitteeMemberVO>();
		try{
			
					if(cadreIds !=null && cadreIds.size()>0){
						List<Object[]> details=tdpCadreDAO.cadresInformationOfCandidate(cadreIds,2014l);
						
						DateFormat dateFormat=null;
						Date convertedDate = null;
						if(details !=null && details.size()>0){
						 for (Object[] cadreFormalDetails : details) {
							 
							 CadreCommitteeMemberVO cadreDetailsVO=new CadreCommitteeMemberVO();
							 
								cadreDetailsVO.setCadreId((Long)cadreFormalDetails[0]);//cadreId
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
								//cadreDetailsVO.setpConstituencyId(cadreFormalDetails[29] !=null ? Long.parseLong(cadreFormalDetails[29].toString()):0l);
								//cadreDetailsVO.setpConstituencyName(cadreFormalDetails[30] !=null ? cadreFormalDetails[30].toString(): "");
								cadreDetailsVO.setBoothId(cadreFormalDetails[31] !=null ? Long.parseLong(cadreFormalDetails[31].toString()):0l);
								
								if(cadreDetailsVO.getPanchayatId() == null || cadreDetailsVO.getPanchayatId().longValue() == 0L)
									cadreDetailsVO.setPanchayatId(cadreFormalDetails[33] !=null ? Long.parseLong(cadreFormalDetails[33].toString()) :0l);
								cadreDetailsVO.setRelativeName(cadreFormalDetails[35] !=null ? cadreFormalDetails[35].toString() :"");
								
								List<Object[]> pList = delimitationConstituencyAssemblyDetailsDAO.findParliamentForAssemblyForTheGivenYear(cadreDetailsVO.getConstituencyId(),2009L);
								if(pList != null && pList.size()>0){
									Object[] parliament = pList.get(0);
									cadreDetailsVO.setpConstituencyId(commonMethodsUtilService.getLongValueForObject(parliament[0]));
									cadreDetailsVO.setpConstituencyName(commonMethodsUtilService.getStringValueForObject(parliament[1]));
								}
							
								listOfCadreVO.add(cadreDetailsVO);
						}
						 finalcadreVo.setKnownList(listOfCadreVO);
					}
				}
			
		}catch(Exception e){
			LOG.error("Exception occured in getCadresDetailsOfDeathsAndHospitalization() Method - ",e);
		}
		
		return finalcadreVo;
	}
	
	
	public CadreOverviewVO getTdpcadreDetailsByTdpCadreId(Long tdpCadreId,String houseNo,String voterCardNo)
	{
		CadreOverviewVO returnVO = new CadreOverviewVO();
		try {
			List<Object[]> voterDetails = null;
			if(tdpCadreId != null && tdpCadreId.longValue() ==0L)
			{
				voterDetails = boothPublicationVoterDAO.getConstyPublicationIdByVoterId(voterCardNo);
				if(voterDetails != null && voterDetails.size()>0)
				{
					for (Object[] voter : voterDetails) {
						Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						if(publicationDateId != null && publicationDateId.longValue() == IConstants.VOTER_DATA_PUBLICATION_ID)
						{
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<TdpCadre> tdpCadreList = tdpCadreDAO.getVoterByVoterId(voterId);
							if(tdpCadreList != null && tdpCadreList.size()>0)
							{
								TdpCadre tdpCadre =  (TdpCadre) tdpCadreList.get(0);
								
								if(tdpCadre != null)
								{
									tdpCadreId = tdpCadre.getTdpCadreId();
								}
							}
							break;
						}
					}
				}
			}
			
			if(tdpCadreId != null && tdpCadreId.longValue()>0L)
			{
				CadreCommitteeMemberVO cadreVO = cadreFormalDetailedInformation(tdpCadreId);
				
				if(cadreVO != null )
				{
					returnVO.setAge(cadreVO.getAge());
					returnVO.setAreaType(cadreVO.getAreaType());
					returnVO.setBoothId(cadreVO.getBoothId());
					returnVO.setCadreId(cadreVO.getCadreId());
					returnVO.setCandidateId(cadreVO.getCandidate());
					returnVO.setCasteName(cadreVO.getCasteName());
					returnVO.setConstituencyName(cadreVO.getConstituencyName());
					returnVO.setDob(cadreVO.getDateOfBirth());
					returnVO.setDistrictId(cadreVO.getDistrictId());
					returnVO.setDistrictName(cadreVO.getDistrictName());
					returnVO.setEmail(cadreVO.getEmailId());
					returnVO.setEnrollmentYears(cadreVO.getEnrollmentYears());
					returnVO.setHouseNo(cadreVO.getHouseNo());
					returnVO.setImagePath(cadreVO.getImagePath());
					returnVO.setLocalElectionBodyId(cadreVO.getLocalElectionBody().toString());
					returnVO.setMembershipNo(Long.valueOf(cadreVO.getMembershipNo()));
					returnVO.setMobileNo(cadreVO.getMobileNo());
					returnVO.setCandidateName(cadreVO.getName());
					returnVO.setOccupation(cadreVO.getOccupation());
					returnVO.setParliamentId(cadreVO.getpConstituencyId());
					returnVO.setParlimentName(cadreVO.getpConstituencyName());
					returnVO.setPanchayatId(cadreVO.getPanchayatId());
					returnVO.setPanchayatName(cadreVO.getPanchayatName());
					returnVO.setPartNo(Long.valueOf(cadreVO.getPartNo()));
					returnVO.setPartyPosition(cadreVO.getPartyPosition());
					returnVO.setRegisteredAt(cadreVO.getRegisteredOn());
					returnVO.setRegisteredTime(cadreVO.getRegisteredTime());
					returnVO.setRepresentativeType(cadreVO.getRepresentativeType());
					returnVO.setStateId(cadreVO.getStateId());
					returnVO.setStateName(cadreVO.getStateName());
					returnVO.setTehsilId(cadreVO.getTehsilId());
					returnVO.setTehsilName(cadreVO.getTehsilName());
					returnVO.setVoterId(Long.valueOf(cadreVO.getVoterId()));
					returnVO.setVoterCardNo(cadreVO.getVoterIdCardNo());
					
					List<TdpCadreFamilyDetailsVO> familyVOList = getCadreFamilyDetails(tdpCadreId);
					if(familyVOList != null && familyVOList.size()>0)
					{
						returnVO.getFamilyMembersList().addAll(familyVOList);
						
						TdpCadreFamilyDetailsVO vo = familyVOList.get(0);
						returnVO.setFamilyMembersSurveyCount(vo.getFamilySurveyCount());
						returnVO.setPartyPositionsCount(vo.getPartyPositionsCount());
						returnVO.setPublicRepresentativesCount(vo.getPartyRepresentativesCount());
					}
					
					returnVO.getElectionResultsPerfList().addAll(getElectionPerformanceInCadreLocation(tdpCadreId));
					
					PartyMeetingVO partyMeetingVO = partyMeetingService.getPartyMeetingDetailsBySearchType(tdpCadreId);
					if(partyMeetingVO != null)
					{
						returnVO.setMeetingAttendenceCount(partyMeetingVO.getAttendedCount());
						returnVO.setMeetingInvitationCount(partyMeetingVO.getInvitedCount());
						returnVO.setMeetingAbsentCount(partyMeetingVO.getAbsentCount());
					}
					
					PartyMeetingVO eventsVO =  mahaNaduService.getParticipatedCandidateEventDetails(tdpCadreId);
					if(eventsVO != null)
					{
						returnVO.setEventAttendedCount(eventsVO.getAttendedCount());
						returnVO.setEventInvitationCount(eventsVO.getInvitedCount());
						returnVO.setEventAbsentCount(eventsVO.getAbsentCount());
					}
				}
				
			}
			else
			{
				//List<VoterInfoVO> getCandidateInfoBySearchCriteria(String searchType, Long candidateId,String staticContentLoc,String constituencyId)
				//List<Object[]> voterDetails = boothPublicationVoterDAO.getConstyPublicationIdByVoterId(voterCardNo);
				if(voterDetails != null && voterDetails.size()>0)
				{
					for (Object[] voter : voterDetails) {
						Long publicationDateId = voter[2] != null ? Long.valueOf(voter[2].toString()):0L;
						if(publicationDateId != null && publicationDateId.longValue() == IConstants.VOTER_DATA_PUBLICATION_ID)
						{
							Long constituencyId = voter[0] != null ? Long.valueOf(voter[0].toString()):0L;
							Long boothId = voter[1] != null ? Long.valueOf(voter[1].toString()):0L;
							Long districtId = voter[7] != null ? Long.valueOf(voter[7].toString()):0L;
							String districtName= voter[12] != null ? voter[12].toString():"";
							String constituencyName= voter[11] != null ? voter[11].toString():"";
							
							Long voterId = voter[3] != null ? Long.valueOf(voter[3].toString()):0L;
							List<Long> voterIdsList = new ArrayList<Long>(0);
							voterIdsList.add(voterId);
							
							String voterName =  voter[4] != null ? voter[4].toString():"";
							String age =  voter[5] != null ? voter[5].toString():"";
							String gender =  voter[6] != null ? voter[6].toString():"";
							String relativeName =  voter[8] != null ? voter[8].toString():"";
							String relativeType =  voter[10] != null ? voter[10].toString():"";
							String hNo = voter[9] != null ? voter[9].toString():"";;
							String partNo= voter[13] != null ? voter[13].toString():"";
							LocalElectionBody localElectionBody = null;//voter[14] != null ? (LocalElectionBody) voter[14]:null;
							Long panchayatId = voter[15] != null ? Long.valueOf(voter[15].toString()):0L;
							String panchayatName= voter[16] != null ? voter[16].toString():"";
							Long tehsilId = voter[17] != null ? Long.valueOf(voter[17].toString()):0L;
							String tehsilName= voter[18] != null ? voter[18].toString():"";
							Long stateId = voter[19] != null ? Long.valueOf(voter[19].toString()):0L;
							String stateName= voter[20] != null ? voter[20].toString():"";
							
							List<Object[]> parliamentList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constituencyId);
							if(parliamentList != null && parliamentList.size()>0)
							{
								Object[] parliament = parliamentList.get(0);
								if(parliament == null)
								{
									if(parliamentList.size()>1)
										parliament = parliamentList.get(1);
								}
								else
								{
									Long parliamentId = parliament[0] != null ? Long.valueOf(parliament[0].toString()):0L;
									String parliamentName= parliament[1] != null ? parliament[1].toString():"";
									
									returnVO.setParliamentId(parliamentId);
									returnVO.setParlimentName(parliamentName);
								}
							}
							
							
							String filePath = "voter_images"+IConstants.FILE_SEPARATOR+constituencyId+IConstants.FILE_SEPARATOR+"Part"+partNo.trim()+IConstants.FILE_SEPARATOR+voterCardNo+".jpg";
							//List<VoterInfoVO> voterDetaildInfo = cadreRegistrationService.getCandidateInfoBySearchCriteria("voter",voterId,"",constituencyId.toString());
							//System.out.println(voterDetaildInfo);

							returnVO.setCandidateName(voterName);									
							returnVO.setAge(age);
							returnVO.setGender(gender);
							returnVO.setRelativeName(relativeName);
							returnVO.setRelationType(relativeType);
							returnVO.setConstituencyId(constituencyId);									
							returnVO.setConstituencyName(constituencyName);
							returnVO.setBoothId(boothId);	
							returnVO.setPartNo(Long.valueOf(partNo));
							
							List<Object[]> voterCasteInfo = userVoterDetailsDAO.getCasteByVoterId(1L,voterIdsList);
							if(voterCasteInfo != null && voterCasteInfo.size()>0)
							{
								Object[] casteInfo = voterCasteInfo.get(0);
								String casteName = casteInfo[1] != null ? casteInfo[1].toString().trim():"";
								returnVO.setCasteName(casteName);
							}
															
							returnVO.setDistrictId(districtId);
							returnVO.setDistrictName(districtName);
							returnVO.setHouseNo(hNo);									
							returnVO.setImagePath(filePath);
							returnVO.setPanchayatId(panchayatId);
							returnVO.setPanchayatName(panchayatName);
							returnVO.setTehsilId(tehsilId);
							returnVO.setTehsilName(tehsilName);
							returnVO.setStateId(stateId);
							returnVO.setStateName(stateName);
							if(localElectionBody != null)
								returnVO.setLocalElectionBodyId(localElectionBody.getLocalElectionBodyId().toString());
							
							
							List<Object[]> mobileNosList = mobileNumbersDAO.getVotersMobileNumberDetails(voterIdsList);
							
							if(mobileNosList != null && mobileNosList.size()>0)
							{
								String mobileNumber = "";
								for (Object[] mobile : mobileNosList) {
									String mobileNo = mobile[1] != null ? mobile[1].toString().trim():"";
									if(!mobileNo.isEmpty())
									{
										mobileNumber = mobileNumber+","+mobileNo;
									}
								}
								returnVO.setMobileNo(mobileNumber);
							}
															
							returnVO.setVoterId(voterId);
							returnVO.setVoterCardNo(voterCardNo);
							
							List<Object[]> familyInfo = boothPublicationVoterDAO.getFamilyDetaislByHouseNoAndBoothId(boothId,hNo);
							List<TdpCadreFamilyDetailsVO> familyVOList = new ArrayList<TdpCadreFamilyDetailsVO>(0);
							if(familyInfo != null && familyInfo.size()>0)
							{
								List<String> voterIdCardNoList = new ArrayList<String>(0);
								
								for (Object[] family : familyInfo) 
								{
									Long familyVoterID = family[0] != null ? Long.valueOf(family[0].toString().trim()):0L;
									
									if( familyVoterID.longValue() != voterId.longValue())
									{
										TdpCadreFamilyDetailsVO fmilyVO = new TdpCadreFamilyDetailsVO();
										fmilyVO.setVoterId(family[0] != null ? Long.valueOf(family[0].toString().trim()):0L);
										fmilyVO.setName(family[1] != null ? family[1].toString():"");
										fmilyVO.setGender(family[4] != null ? family[4].toString():"");
										fmilyVO.setAge(family[5] != null ? Long.valueOf(family[5].toString()):0L);								
										fmilyVO.setVotercardNo(family[6] != null ? family[6].toString():"");
										fmilyVO.setRelation(family[3] != null ? family[3].toString():"");
										fmilyVO.setRelativeName(family[7] != null ? family[7].toString():null);
										voterIdCardNoList.add(fmilyVO.getVotercardNo());
										familyVOList.add(fmilyVO);
										//familyMap.put(fmilyVO.getVotercardNo().trim(), fmilyVO);
									}								
								}
								
								if(voterIdCardNoList != null && voterIdCardNoList.size() > 0)
								{
									List<Object[]> list = tdpCadreDAO.getSurveyPaticipatedCountByVoterIdcardNoList(voterIdCardNoList);
									if(list != null && list.size() > 0)
									{
										for(Object[] params: list)
										{
											if(params[1] != null)
											{
												TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, params[1].toString());
											    if(VO != null)
											    	VO.setCount(params[0] != null?Long.parseLong(params[0].toString()):0l);
											}
										}
										
										returnVO.setFamilyMembersSurveyCount(Long.valueOf(String.valueOf(list.size())));
									}
									
									List<Object[]> cadreMembersInFamilyList = tdpCadreDAO.checkVoterCardNosCadreNosOrNot(voterIdCardNoList);
									List<Long> tdpCadreIDsList = new ArrayList<Long>(0);
									if(cadreMembersInFamilyList != null && cadreMembersInFamilyList.size() > 0)
									{
										for(Object[] params: cadreMembersInFamilyList)
										{
											if(params[0] != null)
											{
												TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, params[0].toString());
											    if(VO != null){
											    	VO.setTdpCadreId(params[1] != null?Long.parseLong(params[1].toString()):0l);
											    	VO.setMembershipNo(params[2] != null?params[2].toString():"");
											    	tdpCadreIDsList.add(VO.getTdpCadreId());
											    }
											}
										}
									}
									
									if(tdpCadreIDsList != null && tdpCadreIDsList.size()>0)
									{
										List<Object[]> cadrePublicRepresentativList = tdpCadreCandidateDAO.getPublicRepresentaativesDetailsForCadreIdsList(tdpCadreIDsList);
										if(cadrePublicRepresentativList != null && cadrePublicRepresentativList.size()>0)
										{
											for (Object[] publicRepresentativ : cadrePublicRepresentativList) {
												if(publicRepresentativ[1] != null)
												{
													TdpCadreFamilyDetailsVO VO =getMatchedTdpCadreFamilyDetailsVO(familyVOList, publicRepresentativ[1].toString());
												    if(VO != null)
												    	VO.setPublicRepresentativeStr(publicRepresentativ[3] != null ? publicRepresentativ[3].toString():"");
												}
											}
											
											returnVO.setPublicRepresentativesCount(Long.valueOf(String.valueOf(cadrePublicRepresentativList.size())));
										}
										
										List<Object[]> cadrePartyPositionDetals =  tdpCommitteeMemberDAO.getMembersInfoByTdpCadreIdsList(tdpCadreIDsList);
										if(cadrePartyPositionDetals != null && cadrePartyPositionDetals.size()>0)
										{
											for (Object[] partyPosition : cadrePartyPositionDetals) {
												if(partyPosition[1] != null)
												{
													TdpCadreFamilyDetailsVO vo =getMatchedTdpCadreFamilyDetailsVO(familyVOList, partyPosition[1].toString());
												    if(vo != null)
												    {
												    	Long committeeLevelId = partyPosition[2] != null?Long.parseLong(partyPosition[2].toString()):0l;
												    	
												    	if(committeeLevelId!= null && committeeLevelId.longValue()>0L)
												    	{
												    		//Long committeeLevelValue = partyPosition[3] != null?Long.parseLong(partyPosition[3].toString()):0l;
													    	String committeeStr = partyPosition[5] != null?partyPosition[5].toString():"";
													    	String roleStr = partyPosition[7] != null?partyPosition[7].toString():"";
													    	String committeeLevelStr = partyPosition[8] != null?partyPosition[8].toString():"";
													    	
													    	String committeeLocation ="";
													    	/*if(committeeLevelId == 5L)
													    	{
													    		committeeLocation = tehsilDAO.get(committeeLevelValue).getTehsilName()+" Mandal ";
													    	}
													    	else if(committeeLevelId == 6L)
													    	{
													    		committeeLocation = panchayatDAO.get(committeeLevelValue).getPanchayatName()+" Panchayat ";
													    	}
													    	else if(committeeLevelId == 7L || committeeLevelId == 9L) // town/division
													    	{
													    		LocalElectionBody localbody = localElectionBodyDAO.get(committeeLevelValue);
													    		if(localbody.getElectionType().getElectionTypeId() != 7L)
													    			committeeLocation = localbody.getName()+" "+localbody.getElectionType().getElectionType();
													    	}
													    	else if(committeeLevelId == 8L)
													    	{
													    		Constituency constituency = constituencyDAO.get(committeeLevelValue);
													    		committeeLocation = constituency.getName()+" ( "+constituency.getLocalElectionBody().getName()+" "+constituency.getLocalElectionBody().getElectionType().getElectionType()+" )";
													    	}
													    	else if(committeeLevelId == 10L)
													    	{
													    		committeeLocation = stateDAO.get(committeeLevelValue).getStateName();
													    	}
													    	else if(committeeLevelId == 11L)
													    	{
													    		committeeLocation = districtDAO.get(committeeLevelValue).getDistrictName();
													    	}
													    	*/
													    	if(!committeeLocation.isEmpty())
													    		vo.setPartyPositionStr(committeeStr+" - "+roleStr+" ( "+committeeLocation+" )");
													    	else
													    		vo.setPartyPositionStr(committeeStr+" -"+committeeLevelStr+" "+roleStr);
												    	}
												    }
												}
											}
											
											returnVO.setPartyPositionsCount(Long.valueOf(String.valueOf(cadrePartyPositionDetals.size())));
										}
									}
								}
							}
							returnVO.getFamilyMembersList().addAll(familyVOList);
							List<RegisteredMembershipCountVO> resultList = new ArrayList<RegisteredMembershipCountVO>();
							UserAddress userAddress = new UserAddress();
							
							userAddress.setBooth(boothDAO.get(returnVO.getBoothId()));
							userAddress.setPanchayat(panchayatDAO.get(returnVO.getPanchayatId()));
							userAddress.setTehsil(tehsilDAO.get(returnVO.getTehsilId()));
							
							if(returnVO.getLocalElectionBodyId() != null && Long.valueOf(returnVO.getLocalElectionBodyId())>0L)
								userAddress.setLocalElectionBody(localElectionBodyDAO.get(Long.valueOf(returnVO.getLocalElectionBodyId())));
							
							userAddress.setConstituency(constituencyDAO.get(returnVO.getConstituencyId()));
							userAddress.setDistrict(districtDAO.get(returnVO.getDistrictId()));
							userAddress.setState(stateDAO.get(1L));
							
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
									
									/*
									   countVO = setElectionPerformanceDetailsInCadreLocation(2009l, userAddress, partyIds);
										if(countVO != null)
											resultList.add(countVO);
									*/
								}
						  }
							
							if(resultList != null && resultList.size()>0)
								returnVO.setElectionResultsPerfList(resultList);
							
							break; // iteration only once 
							
						}
					}
				}
				
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in getTdpcadreDetailsByTdpCadreId() Method - ",e);
		}
		
		return returnVO;
	}
}
