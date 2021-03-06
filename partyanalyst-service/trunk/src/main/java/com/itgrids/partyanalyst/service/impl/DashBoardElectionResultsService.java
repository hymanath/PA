package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.velocity.util.StringUtils;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IStateSubRegionDAO;
import com.itgrids.partyanalyst.dao.IStateSubRegionDistrictDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.security.PBKDF2;
import com.itgrids.partyanalyst.service.IDashBoardElectionResultsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.webservice.client.WebServiceClient;
import com.itgrids.survey.soa.endpoints.ElectionComparisonVO;
import com.itgrids.survey.soa.endpoints.OptionVO;
import com.itgrids.survey.soa.endpoints.SurveyReportVO;

public class DashBoardElectionResultsService implements
		IDashBoardElectionResultsService {
	
	private static final Logger LOG = Logger.getLogger(DashBoardElectionResultsService.class);
	private DateUtilService dateUtilService = new DateUtilService();
	
	
	@Autowired
	private IDistrictDAO districtDAO;
	
	@Autowired
	private IStateDAO stateDAO;
	
	@Autowired
	private IConstituencyDAO constituencyDAO;
	
	@Autowired
	private INominationDAO nominationDAO;
	
	@Autowired
	private IElectionAllianceDAO electionAllianceDAO;
	
	@Autowired
	private IAllianceGroupDAO allianceGroupDAO;
	
	@Autowired
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
	@Autowired
	private IPartyDAO partyDAO;
	
	@Autowired
	IStateSubRegionDistrictDAO stateSubRegionDistrictDAO;
	
	@Autowired
	private IConstituencyElectionDAO constituencyElectionDAO;
	
	@Autowired
	private IStateSubRegionDAO stateSubRegionDAO;
	
	@Autowired
	private ICandidateResultDAO candidateResultDAO;
	
	@Autowired
	private IConstituencyElectionResultDAO constituencyElectionResultDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/*private WebServiceClient webServiceClient;

	public WebServiceClient getWebServiceClient() {
		return webServiceClient;
	}

	public void setWebServiceClient(WebServiceClient webServiceClient) {
		this.webServiceClient = webServiceClient;
	}*/

	public DashBoardResultsVO getElectionResultsSummary()
	{
		LOG.debug("Entered into the getElectionResultsSummary service method");
		DashBoardResultsVO resultVO = new DashBoardResultsVO();
		//List<DashBoardResultsVO> subList = new ArrayList<DashBoardResultsVO>();
		try
		{
			
			Long scopeId = 2L;
			List<Object[]> list = null;
			
			if(scopeId.longValue() == 1L)
			{
				list = new ArrayList<Object[]>();
				Object[] obj = new Object[2];
				obj[0] = "1";
				obj[1] = "Andhra Pradesh";
				list.add(obj);
				
			}else if(scopeId.longValue() == 2L)
			{
				 list = districtDAO.getDistrictIdAndNameByState(1L);
				
			}/*else if(scopeId.longValue() == 3L)
			{
				 list = constituencyDAO.getParliamentConstituencies();
				 
			}*/

			for(Object[] obj:list)
			{
				DashBoardResultsVO locationVO = new DashBoardResultsVO();
				locationVO.setId(Long.parseLong(obj[0].toString()));
				locationVO.setName(obj[1].toString());
				
				DashBoardResultsVO sc  = new DashBoardResultsVO();
				sc.setName("SC");
				
				DashBoardResultsVO st  = new DashBoardResultsVO();
				st.setName("ST");
				
				DashBoardResultsVO general  = new DashBoardResultsVO();
				general.setName("GENERAL");
				
				locationVO.getReservationDetails().add(sc);
				locationVO.getReservationDetails().add(st);
				locationVO.getReservationDetails().add(general);
				
				
				DashBoardResultsVO rural  = new DashBoardResultsVO();
				rural.setName("RURAL");
				
				DashBoardResultsVO urban  = new DashBoardResultsVO();
				urban.setName("URBAN");
				
				DashBoardResultsVO ruralUrban  = new DashBoardResultsVO();
				ruralUrban.setName("RURAL-URBAN");
				
				locationVO.getConstituencyWiseDetails().add(rural);
				locationVO.getConstituencyWiseDetails().add(urban);
				locationVO.getConstituencyWiseDetails().add(ruralUrban);
				
				resultVO.getSubList().add(locationVO);
			}
			
			List<Long> partyIds = new ArrayList<Long>();
			
			partyIds.add(872L);
			partyIds.add(362L);
			partyIds.add(1117L);
			partyIds.add(886L);
			partyIds.add(163L);
			partyIds.add(9999L);
			
			Map<Long,String> partyMap = new HashMap<Long, String>();
			
			partyMap.put(872L, "TDP");
			partyMap.put(362L, "INC");
			partyMap.put(1117L, "YCP");
			partyMap.put(886L, "TRS");
			partyMap.put(163L, "BJP");
			partyMap.put(9999L, "OTH");
			
			for(DashBoardResultsVO locationVO:resultVO.getSubList())
			{
				for(DashBoardResultsVO constnTypeVO:locationVO.getReservationDetails())
				{
					for(Long partyId:partyIds)
					{
						DashBoardResultsVO partyVO = new DashBoardResultsVO();
						partyVO.setId(partyId);
						partyVO.setName(partyMap.get(partyId));
						constnTypeVO.getPartiesDetails().add(partyVO);
					}
				}
				
				for(DashBoardResultsVO constnTypeVO:locationVO.getConstituencyWiseDetails())
				{
					for(Long partyId:partyIds)
					{
						DashBoardResultsVO partyVO = new DashBoardResultsVO();
						partyVO.setId(partyId);
						partyVO.setName(partyMap.get(partyId));
						constnTypeVO.getPartiesDetails().add(partyVO);
					}
				}
			}
			
			
		List<Object[]> winningCandidatesList = nominationDAO.getWinningCandidatesDetailsForConstituenciesByElectionId(258L);
			
		//0 - regionId 1-regionName  2 - districtId 3 - districtName 4 - constituencyId 5 - constituencyName 6 - partyid 7 - partyName 8- reservation
		// 9 -areatype 10- parliamentId 11 - parliamentName
		
		
		DashBoardResultsVO  locationVO = null;
		
		for(Object[] obj:winningCandidatesList)
		{
			 if(scopeId.longValue() == 1L)
				    locationVO =  getMacthedVO(resultVO.getSubList(),1L);
				 else  if(scopeId.longValue() == 2L)
					 locationVO =  getMacthedVO(resultVO.getSubList(),Long.parseLong(obj[2].toString()));
			 
			 String reservationStatus =!obj[8].toString().equals("") ?obj[8].toString():"GENERAL";
			 
			 DashBoardResultsVO reservationVO =  getMacthedVoByName(locationVO.getReservationDetails(),reservationStatus);
			 
			 DashBoardResultsVO partyVO = null;
			 
			 if(partyIds.contains(Long.parseLong(obj[6].toString())))
			 {
			  partyVO =  getMacthedVO(reservationVO.getPartiesDetails(),Long.parseLong(obj[6].toString()));
			 // partyVO.setName(obj[7].toString());
			 }
			 else
			 {
			  partyVO =  getMacthedVO(reservationVO.getPartiesDetails(),9999L); 
			  //partyVO.setName("OTHERS");
			 }
			 
			
			 
			 
			 partyVO.setCount(partyVO.getCount() +1);
			 
			 
			 DashBoardResultsVO constnTypeVO =  getMacthedVoByName(locationVO.getConstituencyWiseDetails(),obj[9].toString());
			 
			 
			 if(partyIds.contains(Long.parseLong(obj[6].toString())))
			 {
				  partyVO =  getMacthedVO(constnTypeVO.getPartiesDetails(),Long.parseLong(obj[6].toString()));
				 // partyVO.setName(obj[7].toString());
			 }
			 else
			 {
			  partyVO =  getMacthedVO(constnTypeVO.getPartiesDetails(),9999L); 
			 // partyVO.setName("OTHERS");
			 }
			 
			 partyVO.setCount(partyVO.getCount() +1);
		}
			
		}catch(Exception e)
		{
			LOG.error("Exception raised in getWinningCandidatesDetailsBySubReportType service method");
			e.printStackTrace();
		}
		
		return resultVO;
	}
	
	
	
	public DashBoardResultsVO getElectionResultsSummary1()
	{
		LOG.debug("Entered into the getElectionResultsSummary service method");
		DashBoardResultsVO resultVO = new DashBoardResultsVO();
		//List<DashBoardResultsVO> subList = new ArrayList<DashBoardResultsVO>();
		try
		{
			
			Long scopeId = 1L;
			List<Object[]> list = null;
			
			if(scopeId.longValue() == 1L)
			{
				
			}else if(scopeId.longValue() == 2L)
			{
				list = districtDAO.getDistrictIdAndNameByState(1L);
				
			}else if(scopeId.longValue() == 3L)
			{
				 list = constituencyDAO.getParliamentConstituencies();
				 
			}

			for(Object[] obj:list)
			{
				DashBoardResultsVO locationVO = new DashBoardResultsVO();
				locationVO.setId(Long.parseLong(obj[0].toString()));
				locationVO.setName(obj[1].toString());
				
				resultVO.getSubList().add(locationVO);
			}
			
			getWinningCandidatesDetailsBySubReportType(resultVO,scopeId);
			
		
			
		}catch(Exception e)
		{
			LOG.error("Exception raised in getWinningCandidatesDetailsBySubReportType service method");
			e.printStackTrace();
		}
		
		return resultVO;
	}
	
	
	public void getWinningCandidatesDetailsBySubReportType(DashBoardResultsVO resultVO,Long scopeId)
	{
		LOG.debug("Entered into the getWinningCandidatesDetailsBySubReportType service method");

		
		try
		{
			List<Object[]> winningCandidatesList = nominationDAO.getWinningCandidatesDetailsForConstituenciesByElectionId(38L);
			
			//0 - regionId 1-regionName  2 - districtId 3 - districtName 4 - constituencyId 5 - constituencyName 6 - partyid 7 - partyName 8- reservation
			// 9 -areatype 10- parliamentId 11 - parliamentName
			
			
			
			/* if(scopeId.longValue() == 2L)
			 {*/
			 	DashBoardResultsVO locationVO = null;
			 	
				 for(Object[] obj:winningCandidatesList)
				 {
					 if(scopeId.longValue() == 2L)
					    locationVO =  getMacthedVO(resultVO.getSubList(),Long.parseLong(obj[3].toString()));
					 else  if(scopeId.longValue() == 3L)
						 locationVO =  getMacthedVO(resultVO.getSubList(),Long.parseLong(obj[11].toString()));
					 
					 locationVO.getPartyIds().add(Long.parseLong(obj[7].toString()));
					 
					 String reservationStatus =obj[8] != null ?obj[8].toString():"GENERAL";
					 
					 DashBoardResultsVO reservationVO =  getMacthedVoByName(resultVO.getSubList(),reservationStatus);
					 
					 if(reservationVO == null)
					 {
						 DashBoardResultsVO resrvtnVO = new DashBoardResultsVO();
						 resrvtnVO.setName(reservationStatus);
						 
						 locationVO.getReservationDetails().add(resrvtnVO);
						 
					 }
				 }
				 
				 
				 for(DashBoardResultsVO lctnVO:resultVO.getSubList())
				 {
					 DashBoardResultsVO rural = new DashBoardResultsVO();
					 rural.setName("RURAL");
					 
					 DashBoardResultsVO ruralUrban = new DashBoardResultsVO();
					 ruralUrban.setName("RURAL-URBAN");
					 
					 DashBoardResultsVO urban = new DashBoardResultsVO();
					 urban.setName("URBAN");
					 
					 lctnVO.getReservationDetails().add(rural);
					 lctnVO.getReservationDetails().add(ruralUrban);
					 lctnVO.getReservationDetails().add(urban);
					 
					 
					 Set<Long> partyIds = lctnVO.getPartyIds();
					 
					 for(DashBoardResultsVO reservationVO:lctnVO.getReservationDetails())
					 {
						 for(Long partyId :partyIds)
						 {
							 DashBoardResultsVO partyVO = new DashBoardResultsVO();
							 partyVO.setPartyId(partyId);
							 reservationVO.getPartiesDetails().add(partyVO);
						 }
						 
					 }
				 }
				 
				 
				 for(DashBoardResultsVO lctnVO:resultVO.getSubList())
				 {
					 Set<Long> partyIds = lctnVO.getPartyIds();
					 
					 for(DashBoardResultsVO reservationVO:lctnVO.getReservationDetails())
					 {
						 for(Long partyId :partyIds)
						 {
							 DashBoardResultsVO partyVO = new DashBoardResultsVO();
							 partyVO.setPartyId(partyId);
							 reservationVO.getPartiesDetails().add(partyVO);
						 }
						 
					 }
				 }
				 
				 
				 for(Object[] obj:winningCandidatesList)
				 {
					 if(scopeId.longValue() == 2L)
						    locationVO =  getMacthedVO(resultVO.getSubList(),Long.parseLong(obj[3].toString()));
						 else  if(scopeId.longValue() == 3L)
							 locationVO =  getMacthedVO(resultVO.getSubList(),Long.parseLong(obj[11].toString()));
					 
					 locationVO.getPartyIds().add(Long.parseLong(obj[7].toString()));
					 
					 String reservationStatus =obj[8] != null ?obj[8].toString():"GENERAL";
					 
					 DashBoardResultsVO reservationVO =  getMacthedVoByName(resultVO.getSubList(),reservationStatus);
					 
					 DashBoardResultsVO partyVO =  getMacthedVO(reservationVO.getPartiesDetails(),Long.parseLong(obj[7].toString()));
					 
					 partyVO.setCount(partyVO.getCount() +1);
					 
				 }
				 
				 
				 for(Object[] obj:winningCandidatesList)
				 {
					 if(scopeId.longValue() == 2L)
						    locationVO =  getMacthedVO(resultVO.getSubList(),Long.parseLong(obj[3].toString()));
						 else  if(scopeId.longValue() == 3L)
							 locationVO =  getMacthedVO(resultVO.getSubList(),Long.parseLong(obj[11].toString()));
					 
					 locationVO.getPartyIds().add(Long.parseLong(obj[7].toString()));
					 
					 String reservationStatus =obj[8] != null ?obj[8].toString():"GENERAL";
					 
					 DashBoardResultsVO reservationVO =  getMacthedVoByName(resultVO.getSubList(),reservationStatus);
					 DashBoardResultsVO constnTypeVO =  getMacthedVoByName(resultVO.getSubList(),obj[8].toString());
					 
					 DashBoardResultsVO partyVO =  getMacthedVO(reservationVO.getPartiesDetails(),Long.parseLong(obj[7].toString()));
					 DashBoardResultsVO constnTypeVOParty =  getMacthedVO(constnTypeVO.getPartiesDetails(),Long.parseLong(obj[7].toString()));
					 
					 
					 partyVO.setCount(partyVO.getCount() +1);
					 constnTypeVOParty.setCount(constnTypeVOParty.getCount() +1);
					 
				 }
				 
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getWinningCandidatesDetailsBySubReportType service method");

		}
		
		
	}
	
	public DashBoardResultsVO getMacthedVO(List<DashBoardResultsVO> list, Long id)
	{
		for(DashBoardResultsVO vo:list)
			if(vo.getId().longValue() == id.longValue())
				return vo;
		return null;
	}

	public DashBoardResultsVO getMacthedVoByName(List<DashBoardResultsVO> list, String name)
	{
		for(DashBoardResultsVO vo:list)
			if(vo.getName().equalsIgnoreCase(name))
				return vo;
		return null;
	}
	public DashBoardResultsVO getPartyVo(List<DashBoardResultsVO> parties,Long id)
	{
		for(DashBoardResultsVO vo:parties)
			if(vo.getId().longValue() == id)
				return vo;
		return null;
	}
	
	public List<Object[]> getConstituenciesDetailsForSubReport(String type,Long partyId,Long locationId,Long scopeId )
	{
		LOG.debug("Entered into the getConstituenciesDetailsForSubReport service method");
		List<Object[]> list = null;

		try
		{
			
			if(type.equalsIgnoreCase("RURAL") || type.equalsIgnoreCase("URBAN") ||type.equalsIgnoreCase("RURAL-URBAN"))
			{
				list = nominationDAO.getConstituencyDetailsByConstituencyType(type,partyId,38L,locationId,scopeId);
			}else
			{
				list = nominationDAO.getConstituencyDetailsByReservationType(type,partyId,38L,locationId,scopeId);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getConstituenciesDetailsForSubReport service method");

		}
		return list;
	}
	
	
	public List<DashBoardResultsVO>  getMatrixReportForElectionResult(Long electionId,List<Long> locationIds,Long scopeId)
	{
		List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();
		
		try
		{
			
			//List<ElectionAlliance> allianceDetails = electionAllianceDAO.getAllianceElectionByElectionIdAndStateId(electionId, 1L);
			
			List<Long> groupIds = new ArrayList<Long>();
			Map<Long,String> groupMap = new HashMap<Long, String>();
			
			/*for(ElectionAlliance alliance:allianceDetails)
			{
				groupIds.add(alliance.getGroup().getGroupId());
				groupMap.put(alliance.getGroup().getGroupId(), alliance.getGroup().getGroupName());
			}
			*/
			List<Object[]> alliancePartyDetails = allianceGroupDAO.getAlliancesAndPartiesForAnElection(electionId);
			
			Map<Long,List<Long>> allianceGroupMap = new HashMap<Long, List<Long>>();
			
			List<Long> alliancePartyIds = new ArrayList<Long>();
			
			
			for(Object[] obj:alliancePartyDetails)
			{
				groupIds.add(Long.parseLong(obj[0].toString()));
				groupMap.put(Long.parseLong(obj[0].toString()), obj[1].toString());
				
				if(allianceGroupMap.get(Long.parseLong(obj[0].toString())) != null)
				{
					allianceGroupMap.get(Long.parseLong(obj[0].toString())).add(Long.parseLong(obj[2].toString()));
				}else
				{
				  List<Long> partyIds = new ArrayList<Long>();
				  partyIds.add(Long.parseLong(obj[2].toString()));
				  allianceGroupMap.put(Long.parseLong(obj[0].toString()), partyIds);
				}
				
				if(!alliancePartyIds.contains(Long.parseLong(obj[2].toString())))
					alliancePartyIds.add(Long.parseLong(obj[2].toString()));
			}
			
			
			
List<Object[]> list = nominationDAO.getMatrixReportForElectionResult(electionId, locationIds, scopeId);
			
			List<Long> locationsIds = new ArrayList<Long>();
			List<Long> partyIds = new ArrayList<Long>();
			
			List<Long> prtyIds = new ArrayList<Long>();
				
			prtyIds.add(872L);
			prtyIds.add(362L);
			prtyIds.add(1117L);
			prtyIds.add(886L);
			prtyIds.add(163L);
			prtyIds.add(662L);
			prtyIds.add(9999L);
			
			Map<Long,String> partyMap = new HashMap<Long, String>();
			
			
			partyMap.put(872L, "TDP");
			partyMap.put(362L, "INC");
			partyMap.put(1117L, "YCP");
			partyMap.put(886L, "TRS");
			partyMap.put(163L, "BJP");
			partyMap.put(662L, "PRP");
			partyMap.put(9999L, "OTH"); //for others we considered id as 9999
			
			
			for(Object[] obj:list)
			{
				if(!locationsIds.contains(Long.parseLong(obj[1].toString())))
					locationsIds.add(Long.parseLong(obj[1].toString()));
				
				if(prtyIds.contains(Long.parseLong(obj[3].toString())) && !partyIds.contains(Long.parseLong(obj[3].toString())))
					partyIds.add(Long.parseLong(obj[3].toString()));
			}
			partyIds.add(9999L);
			
			for(Long locationId:locationsIds)
			{
				DashBoardResultsVO locationVO = new DashBoardResultsVO();
				locationVO.setId(locationId);
				for(Long partyId:partyIds)
				{
					DashBoardResultsVO partyVO = new DashBoardResultsVO();
					partyVO.setId(partyId);
					partyVO.setName(partyMap.get(partyId));
					
					locationVO.getPartiesDetails().add(partyVO);
				}
				
				resultList.add(locationVO);
			}
			
			
			
			for(Object[] obj:list)
			{
				DashBoardResultsVO locVO = getMacthedVO(resultList,Long.parseLong(obj[1].toString()));
				locVO.setName(obj[2].toString());
				
				DashBoardResultsVO partyVO  = null;
				 partyVO = getMacthedVO(locVO.getPartiesDetails(),Long.parseLong(obj[3].toString()));
				 
				 if(partyVO == null)
				 {
					 partyVO = getMacthedVO(locVO.getPartiesDetails(),9999L);
					 partyVO.setName("OTH");
					 
					 if(Long.parseLong(obj[5].toString()) == 1L)
							partyVO.setWinCount(partyVO.getWinCount() + Long.parseLong(obj[0].toString()));
						else
							partyVO.setLeadCount(partyVO.getLeadCount() + Long.parseLong(obj[0].toString()));
					 
					 
					 
					 partyVO.setGainedVotes((long)Double.parseDouble(obj[6].toString()));

					 partyVO.setPercent((long)Double.parseDouble(obj[7].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[6].toString())
					 * 100f
					 /(long) Double.parseDouble(obj[7].toString())) : "0.00");


					 
				 }else
				 {
				    partyVO.setName(obj[4].toString());

					if(Long.parseLong(obj[5].toString()) == 1L)
						partyVO.setWinCount(Long.parseLong(obj[0].toString()));
					else
						partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
					
					
					partyVO.setGainedVotes((long)Double.parseDouble(obj[6].toString()));

					partyVO.setPercent((long)Double.parseDouble(obj[7].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[6].toString())
					* 100f
					/(long) Double.parseDouble(obj[7].toString())) : "0.00");


				 }
			}
			
			
			List<DashBoardResultsVO> summaryDetails = new ArrayList<DashBoardResultsVO>();
			
			for(Long partyId:prtyIds)
			{
				DashBoardResultsVO party = new DashBoardResultsVO();
				party.setId(partyId);
				party.setName(partyMap.get(partyId));
				summaryDetails.add(party);
			}
			
			/*for(Long partyId:prtyIds)
			{*/
				for(DashBoardResultsVO locationVO:resultList)
				{
					
					for(DashBoardResultsVO partyVO:locationVO.getPartiesDetails())
					{
						/*if(partyVO.getId().longValue()== partyId.longValue())
						{*/
							DashBoardResultsVO prtVO = getMacthedVO(summaryDetails,partyVO.getId());
							
							prtVO.setWinTotalCount(prtVO.getWinTotalCount() + partyVO.getWinCount());
							prtVO.setLeadTotalCount(prtVO.getLeadTotalCount() + partyVO.getLeadCount());
							
						//}
						
					}
					
				}
				
			//}
				
				Collections.sort(resultList,sortByLocation);
				
			
			if(resultList != null && resultList.size() >0)
				resultList.get(0).setSummaryDetails(summaryDetails);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
	
	public static Comparator<DashBoardResultsVO> sortByLocation = new Comparator<DashBoardResultsVO>()
    {
        public int compare(DashBoardResultsVO locationVO1, DashBoardResultsVO locationVO2)
        {
            return locationVO1.getName().compareTo(locationVO2.getName());
        }
    };
	
	
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyReservationType(Long electionId,List<Long> locationIds,Long scopeId)
	{
		List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();

		try
		{
			List<Long> locationsIds = new ArrayList<Long>();
			List<String> types = new ArrayList<String>();
			List<Long> partyIds = new ArrayList<Long>();
			
			List<Long> prtyIds = new ArrayList<Long>();
				
			prtyIds.add(872L);
			prtyIds.add(362L);
			prtyIds.add(1117L);
			prtyIds.add(886L);
			prtyIds.add(163L);
			prtyIds.add(662L);
			prtyIds.add(9999L);
			
			Map<Long,String> partyMap = new HashMap<Long, String>();
			
			
			partyMap.put(872L, "TDP");
			partyMap.put(362L, "INC");
			partyMap.put(1117L, "YCP");
			partyMap.put(886L, "TRS");
			partyMap.put(163L, "BJP");
			partyMap.put(662L, "PRP");
			partyMap.put(9999L, "OTH"); //for others we considered id as 9999
			
			
		List<Object[]> list = 	nominationDAO.getSubReportForElectionResultByConstituencyReservationType(electionId,locationIds,scopeId);
		
		
		for(Object[]  obj:list)
		{
			if(!locationsIds.contains(Long.parseLong(obj[1].toString())))
				locationsIds.add(Long.parseLong(obj[1].toString()));
			
			if(prtyIds.contains(Long.parseLong(obj[3].toString())) && !partyIds.contains(Long.parseLong(obj[3].toString())))
				partyIds.add(Long.parseLong(obj[3].toString()));
			
			if(obj[6] == null && !types.contains(""))
				types.add("");
			
			if((obj[6] != null && !types.contains(obj[6].toString())))
				types.add(obj[6].toString());
		}
		
		partyIds.add(9999L);
		
		
		for(Long locationId:locationsIds)
		{
			DashBoardResultsVO locationVO = new DashBoardResultsVO();
			locationVO.setId(locationId);
			
			for(String type:types)
			{
				DashBoardResultsVO typeVO = new DashBoardResultsVO();
				typeVO.setName(type);
				
				for(Long partyId:partyIds)
				{
					DashBoardResultsVO partyVO = new DashBoardResultsVO();
					partyVO.setId(partyId);
					partyVO.setName(partyMap.get(partyId));
					
					typeVO.getPartiesDetails().add(partyVO);
				}
				locationVO.getReservationDetails().add(typeVO);
			}
			
			resultList.add(locationVO);
		}
			
		
		
		for(Object[]  obj:list)
		{
			DashBoardResultsVO locationVO = getMacthedVO(resultList,Long.parseLong(obj[1].toString()));
			locationVO.setName(obj[2].toString());

			DashBoardResultsVO typeVO = null;
			if(obj[6]  != null)
			 typeVO = getMacthedVoByName(locationVO.getReservationDetails(),obj[6].toString());
			else
				typeVO = 	getMacthedVoByName(locationVO.getReservationDetails(),"");
			DashBoardResultsVO partyVO = getMacthedVO(typeVO.getPartiesDetails(),Long.parseLong(obj[3].toString()));

			 if(partyVO == null)
			 {
				 partyVO = getMacthedVO(typeVO.getPartiesDetails(),9999L);
				 partyVO.setName("OTH");
				 
				 if(Long.parseLong(obj[5].toString()) == 1L)
						partyVO.setWinCount(partyVO.getWinCount() + Long.parseLong(obj[0].toString()));
					else
						partyVO.setLeadCount(partyVO.getLeadCount() + Long.parseLong(obj[0].toString()));
				 
				 partyVO.setGainedVotes((long)Double.parseDouble(obj[7].toString()));
				 
					partyVO.setPercent((long)Double.parseDouble(obj[8].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[7].toString())
							* 100f
							/(long) Double.parseDouble(obj[8].toString())) : "0.00");
				 
				 
			 }else
			 {
			  //  partyVO.setName(obj[4].toString());

				if(Long.parseLong(obj[5].toString()) == 1L)
					partyVO.setWinCount(Long.parseLong(obj[0].toString()));
				else
					partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
				
				partyVO.setGainedVotes((long)Double.parseDouble(obj[7].toString()));
				 
				partyVO.setPercent((long)Double.parseDouble(obj[8].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[7].toString())
							* 100f
							/(long) Double.parseDouble(obj[8].toString())) : "0.00");
				 
			 }

		}
		
		Collections.sort(resultList,sortByLocation);
		
		
		List<DashBoardResultsVO> reservationDetails = getTotalConstituenciesCountByReservationCategory(electionId,
				locationIds, scopeId);
		
		if(resultList != null && resultList.size() >0)
			resultList.get(0).setSubList(reservationDetails);
		
		
	/*	List<DashBoardResultsVO> reservationTypVoterShare = getTotalVoterShareByReservationType(electionId,
				locationIds, scopeId);
		
		if(resultList != null && resultList.size() >0)
			resultList.get(0).setReservationTypeVoterShare(reservationTypVoterShare);*/
		
		
		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
	}
	
	public String roundTo2DigitsFloatValue(Float number){
		  
		LOG.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  LOG.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	  }
	
	public List<DashBoardResultsVO> getTotalConstituenciesCountByReservationCategory(Long electionId,List<Long> locationIds,Long scopeId)
	{
		LOG.debug("Entered into the getTotalConstituenciesCountByReservationCategory service method");
		
			List<DashBoardResultsVO> reservationDetails = new ArrayList<DashBoardResultsVO>();
			
			try
			{
	
				List<Object[]> reservationTypesList = constituencyElectionDAO
						.getConstituenciesCountByReservationCategory(electionId,
								locationIds, scopeId);	
				
				for(Object[] obj:reservationTypesList)
				{
					DashBoardResultsVO vo = new DashBoardResultsVO();
					
					vo.setName(obj[1].toString());
					vo.setCount(Long.parseLong(obj[0].toString()));
					
					reservationDetails.add(vo);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception occured in getTotalConstituenciesCountByReservationCategory service method");
			}
			return reservationDetails;
	}
	
	
	public List<DashBoardResultsVO> getSubReportForElectionResultByConstituencyType(Long electionId,List<Long> locationIds,Long scopeId)
	{
		List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();

		try
		{
			List<Long> locationsIds = new ArrayList<Long>();
			List<String> types = new ArrayList<String>();
			List<Long> partyIds = new ArrayList<Long>();
			
			List<Long> prtyIds = new ArrayList<Long>();
				
			prtyIds.add(872L);
			prtyIds.add(362L);
			prtyIds.add(1117L);
			prtyIds.add(886L);
			prtyIds.add(163L);
			prtyIds.add(662L);
			prtyIds.add(9999L);
			
			Map<Long,String> partyMap = new HashMap<Long, String>();
			
			
			partyMap.put(872L, "TDP");
			partyMap.put(362L, "INC");
			partyMap.put(1117L, "YCP");
			partyMap.put(886L, "TRS");
			partyMap.put(163L, "BJP");
			partyMap.put(662L, "PRP");
			partyMap.put(9999L, "OTH"); //for others we considered id as 9999
			
			
		List<Object[]> list = 	nominationDAO.getSubReportForElectionResultByConstituencyType(electionId,locationIds,scopeId);
		
		
		for(Object[]  obj:list)
		{
			if(!locationsIds.contains(Long.parseLong(obj[1].toString())))
				locationsIds.add(Long.parseLong(obj[1].toString()));
			
			if(prtyIds.contains(Long.parseLong(obj[3].toString())) && !partyIds.contains(Long.parseLong(obj[3].toString())))
				partyIds.add(Long.parseLong(obj[3].toString()));
			
			if(!types.contains(obj[6].toString()))
				types.add(obj[6].toString());
			
		}
		
		List<DashBoardResultsVO> typesList = new ArrayList<DashBoardResultsVO>();
		
		
	/*	for(String type:types)
		{
			DashBoardResultsVO typeVO = new DashBoardResultsVO();
			typeVO.setName(type);
			
			for(Long partyId:partyIds)
			{
				DashBoardResultsVO partyVO = new DashBoardResultsVO();
				partyVO.setId(Long.parseLong());
				
			}
			
		}
		*/
		
		
		partyIds.add(9999L);
		
		for(Long locationId:locationsIds)
		{
			DashBoardResultsVO locationVO = new DashBoardResultsVO();
			locationVO.setId(locationId);
			
			for(String type:types)
			{
				DashBoardResultsVO typeVO = new DashBoardResultsVO();
				typeVO.setName(type);
				
				for(Long partyId:partyIds)
				{
					DashBoardResultsVO partyVO = new DashBoardResultsVO();
					partyVO.setId(partyId);
					partyVO.setName(partyMap.get(partyId));
					
					typeVO.getPartiesDetails().add(partyVO);
				}
				locationVO.getReservationDetails().add(typeVO);
			}
			
			resultList.add(locationVO);
		}
			
		
		
		for(Object[]  obj:list)
		{
			DashBoardResultsVO locationVO = getMacthedVO(resultList,Long.parseLong(obj[1].toString()));
			locationVO.setName(obj[2].toString());
			DashBoardResultsVO typeVO = getMacthedVoByName(locationVO.getReservationDetails(),obj[6].toString());
			DashBoardResultsVO partyVO = getMacthedVO(typeVO.getPartiesDetails(),Long.parseLong(obj[3].toString()));

			 if(partyVO == null)
			 {
				 partyVO = getMacthedVO(typeVO.getPartiesDetails(),9999L);
				 partyVO.setName("OTH");
				 
				 if(Long.parseLong(obj[5].toString()) == 1L)
						partyVO.setWinCount(partyVO.getWinCount() + Long.parseLong(obj[0].toString()));
					else
						partyVO.setLeadCount(partyVO.getLeadCount() + Long.parseLong(obj[0].toString()));
				 
				 partyVO.setGainedVotes((long)Double.parseDouble(obj[7].toString()));
				 
					partyVO.setPercent((long)Double.parseDouble(obj[8].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[7].toString())
							* 100f
							/(long) Double.parseDouble(obj[8].toString())) : "0.00");
				 
			 }else
			 {
			   // partyVO.setName(obj[4].toString());

				if(Long.parseLong(obj[5].toString()) == 1L)
					partyVO.setWinCount(Long.parseLong(obj[0].toString()));
				else
					partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
				
				 partyVO.setGainedVotes((long)Double.parseDouble(obj[7].toString()));
				 
					partyVO.setPercent((long)Double.parseDouble(obj[8].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[7].toString())
							* 100f
							/(long) Double.parseDouble(obj[8].toString())) : "0.00");
			 }

		}
		
		
		
		List<DashBoardResultsVO> constnTypeDetails = getTotalConstituenciesCountByConstituencyType(electionId,
				locationIds, scopeId);
		
		if(resultList != null && resultList.size() >0)
			resultList.get(0).setSubList(constnTypeDetails);
		
		
	/*	List<DashBoardResultsVO> constnTypeVoterShare = getTotalVoterShareByConstituencyType(electionId,
				locationIds, scopeId);
		
		if(resultList != null && resultList.size() >0)
			resultList.get(0).setConstnTypeVoterShare(constnTypeVoterShare);*/

			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<DashBoardResultsVO> getTotalVoterShareByConstituencyType(Long electionId,List<Long> locationIds,Long scopeId)
	{
		LOG.debug("Entered into the getTotalVoterShareByConstituencyType service method");
		
			List<DashBoardResultsVO> voterShareDetails = new ArrayList<DashBoardResultsVO>();
			
			try
			{
				
				List<Long> prtyIds = new ArrayList<Long>();
				
				prtyIds.add(872L);
				prtyIds.add(362L);
				prtyIds.add(1117L);
				prtyIds.add(886L);
				prtyIds.add(163L);
				prtyIds.add(662L);
				prtyIds.add(9999L);
	
				List<Object[]> voterShareByConstnType = nominationDAO
						.getVoterShareByConstituencyType(electionId,
								locationIds, scopeId);
				
				List<Long> partyIds = new ArrayList<Long>();
				
				for(Object[] obj:voterShareByConstnType)
					if(prtyIds.contains(Long.parseLong(obj[1].toString())) && !partyIds.contains(Long.parseLong(obj[1].toString())))
					partyIds.add(Long.parseLong(obj[1].toString()));
				
				List<String> types = new ArrayList<String>();
				
				for(Object[] obj:voterShareByConstnType)
					if(!types.contains(obj[0].toString()))
					types.add(obj[0].toString());
				
				
				for(Long partyId:partyIds)
				{
					DashBoardResultsVO partyVO = new DashBoardResultsVO();
					partyVO.setId(partyId);
					
					for(String type:types)
					{
						DashBoardResultsVO typeVO =new DashBoardResultsVO();
						typeVO.setName(type);
						
						partyVO.getSubList().add(typeVO);
						
					}
					voterShareDetails.add(partyVO);
					
				}
				
				for(Object[] obj:voterShareByConstnType)
				{
					DashBoardResultsVO partyVO = getMacthedVO(voterShareDetails,Long.parseLong(obj[1].toString()));
					
					partyVO.setName(obj[2].toString());
					DashBoardResultsVO typeVO = getMacthedVoByName(partyVO.getSubList(),obj[0].toString());
					typeVO.setName(obj[0].toString());
					
					typeVO.setGainedVotes((long)Double.parseDouble(obj[3].toString()));
					typeVO.setPercent((long)Double.parseDouble(obj[4].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[3].toString())
							* 100f
							/(long) Double.parseDouble(obj[4].toString())) : "0.00");
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception occured in getTotalVoterShareByConstituencyType service method");
			}
			return voterShareDetails;
	}
	
	
	public List<DashBoardResultsVO> getTotalVoterShareByReservationType(Long electionId,List<Long> locationIds,Long scopeId)
	{
		LOG.debug("Entered into the getTotalVoterShareByReservationType service method");
		
			List<DashBoardResultsVO> voterShareDetails = new ArrayList<DashBoardResultsVO>();
			
			try
			{
	
				List<Object[]> voterShareByConstnType = nominationDAO
						.getVoterShareByReservationCategory(electionId,
								locationIds, scopeId);
				
				
				List<Long> prtyIds = new ArrayList<Long>();
				
				prtyIds.add(872L);
				prtyIds.add(362L);
				prtyIds.add(1117L);
				prtyIds.add(886L);
				prtyIds.add(163L);
				prtyIds.add(662L);
				//prtyIds.add(9999L);
				
				List<Long> partyIds = new ArrayList<Long>();
				
				for(Object[] obj:voterShareByConstnType)
					if(prtyIds.contains(Long.parseLong(obj[1].toString())) && !partyIds.contains(Long.parseLong(obj[1].toString())))
					partyIds.add(Long.parseLong(obj[1].toString()));
				
				List<String> types = new ArrayList<String>();
				
				for(Object[] obj:voterShareByConstnType)
					if(!types.contains(obj[0].toString()))
					types.add(obj[0].toString());
				
				partyIds.add(9999L);
				
				for(Long partyId:partyIds)
				{
					DashBoardResultsVO partyVO = new DashBoardResultsVO();
					partyVO.setId(partyId);
					
					for(String type:types)
					{
						DashBoardResultsVO typeVO =new DashBoardResultsVO();
						typeVO.setName(type);
						
						partyVO.getSubList().add(typeVO);
						
					}
					voterShareDetails.add(partyVO);
					
				}
				
				for(Object[] obj:voterShareByConstnType)
				{
					DashBoardResultsVO partyVO = null;
					if(prtyIds.contains(Long.parseLong(obj[1].toString())))
					   partyVO = getMacthedVO(voterShareDetails,Long.parseLong(obj[1].toString()));
					else
						partyVO = getMacthedVO(voterShareDetails,9999L);
						
					
					partyVO.setName(obj[2].toString());
					//DashBoardResultsVO typeVO = getMacthedVO(partyVO.getSubList(),Long.parseLong(obj[1].toString()));
					DashBoardResultsVO typeVO = getMacthedVoByName(partyVO.getSubList(),obj[0].toString());
					typeVO.setName(obj[0].toString());
					
					typeVO.setGainedVotes((long)Double.parseDouble(obj[3].toString()));
					typeVO.setPercent((long)Double.parseDouble(obj[4].toString()) != 0 ? roundTo2DigitsFloatValue((float) (long)Double.parseDouble(obj[3].toString())
							* 100f
							/(long) Double.parseDouble(obj[4].toString())) : "0.00");
				}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception occured in getTotalVoterShareByReservationType service method");
			}
			return voterShareDetails;
	}
	public List<DashBoardResultsVO> getTotalConstituenciesCountByConstituencyType(Long electionId,List<Long> locationIds,Long scopeId)
	{
		LOG.debug("Entered into the getTotalConstituenciesCountByConstituencyType service method");
		
			List<DashBoardResultsVO> reservationDetails = new ArrayList<DashBoardResultsVO>();
			
			try
			{
	
				List<Object[]> reservationTypesList = constituencyElectionDAO
						.getTotalConstituenciesCountByConstituencyType(electionId,
								locationIds, scopeId);	
				
				for(Object[] obj:reservationTypesList)
				{
					DashBoardResultsVO vo = new DashBoardResultsVO();
					
					vo.setName(obj[1].toString());
					vo.setCount(Long.parseLong(obj[0].toString()));
					
					reservationDetails.add(vo);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				LOG.error("Exception occured in getTotalConstituenciesCountByConstituencyType service method");
			}
			return reservationDetails;
	}
	
	 public List<DashBoardResultsVO> getConstituencyWiseLiveResults(Long electionId,List<Long> constituencyIds){
 		  LOG.debug("Entered Into getConstituencyWiseLiveResults()"); 
 		  List<DashBoardResultsVO> resultList  = new ArrayList<DashBoardResultsVO>();
 		  try {
 			  
 			  List<Object[]> list = 	 nominationDAO.getConstituencyWiseResults1(electionId,constituencyIds);
 			  
                			 
 			 /* 
 				" model.constituencyElection.constituency.constituencyId," +
				" model.constituencyElection.constituency.name," +
				" model.candidate.lastname," +
				" model.candidateResult.marginVotes," +
				" model.candidateResult.rank," +
				" model.party.shortName," +
				" model.party.shortName," +
				" model1.constituencyNO," +
				" model2.delimitationConstituency.constituency.name ," +
				" model2.delimitationConstituency.constituencyNO from " +
				" Nomination model , DelimitationConstituency model1 ,DelimitationConstituencyAssemblyDetails model2 " +
				" where  model.constituencyElection.election.electionId = :electionId and model.candidateResult.rank in (1,2) and" +
				"model.constituencyElection.constituency.constituencyId = model1.constituency.constituencyId and " +
				"model1.constituencyElection.year = 2009 and " +
				"model.constituencyElection.constituency.constituencyId in(:constituencyIds) " +
				"model.constituencyElection.constituency.constituencyId = model2.constituency.constituencyId ");
		*/
 			  
 			  for(Object[] obj:list)
 			  {
 				 DashBoardResultsVO constituencyVO  = null;
 					 constituencyVO = getMacthedVO(resultList,Long.parseLong(obj[0].toString()));
 					 if(constituencyVO == null)
 					 {
 						constituencyVO = new DashBoardResultsVO();
 						constituencyVO.setId(Long.parseLong(obj[0].toString()));
 						resultList.add(constituencyVO);
 					 }
 					
 					constituencyVO.setAssemblyNo(Long.parseLong(obj[7].toString()));
 					constituencyVO.setAssemblyName(obj[1].toString());
 					constituencyVO.setParliamentNo(Long.parseLong(obj[9].toString()));
 					constituencyVO.setParliamentName(obj[8].toString());
 					
 					if(Long.parseLong(obj[4].toString()) == 1L)
 						constituencyVO.setFirstRankCandidateName(obj[5].toString());
 					else
 					constituencyVO.setSecondRankCandidateName(obj[5].toString());
 					
 					Double lead = new Double(obj[3].toString());
 	 				
 					constituencyVO.setLeadBy(lead.longValue());
 					
 					String ttlVotes = obj[10].toString();
					String ttlVotesEarn[] = StringUtils.split(ttlVotes, ".");
					
					String validVts = obj[11].toString();
					String validVotesEarn[] = StringUtils.split(validVts, ".");
 					
 					constituencyVO.setTotalVotes(ttlVotesEarn[0]);
 					constituencyVO.setValidVotes(validVotesEarn[0]);
 				  
 			  }
 			  Collections.sort(resultList);
 			  
 			  /*
 			Map<Long,DashBoardResultsVO> constiWiseResults = new HashMap<Long,DashBoardResultsVO>();
 			DashBoardResultsVO dashBoardResultsVO = null;
 			List<Object[]> dtlsList = nominationDAO.getConstituencyWiseResults(electionId);  
 			
 			for(Object[] objects : dtlsList)
 			{
 				dashBoardResultsVO = constiWiseResults.get((Long)objects[0]);
 				if(dashBoardResultsVO == null){
 				   dashBoardResultsVO = new DashBoardResultsVO();
 				   constiWiseResults.put((Long)objects[0],dashBoardResultsVO);
 				}
 				if((Long)objects[4] == 1)
 				{
 				  // dashBoardResultsVO.setFirstRankCandidateName(objects[2].toString()+"("+objects[5].toString()+")");
 					 dashBoardResultsVO.setFirstRankCandidateName(objects[5].toString());
 				 
 				}
 				if((Long)objects[4] == 2){
 				//dashBoardResultsVO.setSecondRankCandidateName(objects[2].toString()+"("+objects[5].toString()+")");
 					dashBoardResultsVO.setSecondRankCandidateName(objects[5].toString());
 				}
 				Double obj = new Double(objects[3].toString());
 				dashBoardResultsVO.setLeadBy((obj.longValue()));
 				dashBoardResultsVO.setAssemblyNo((Long)objects[0]);
 				dashBoardResultsVO.setAssemblyName(objects[1].toString());  
 				
 				
 			}  
 			List<Long> assemblyIds = new ArrayList<Long>(constiWiseResults.keySet());
 			List<Object[]> list = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(assemblyIds);
 			for(Object[] param:list){
 				dashBoardResultsVO = constiWiseResults.get(param[1]);
 				dashBoardResultsVO.setParliamentName(param[2].toString());
 				dashBoardResultsVO.setParliamentNo((Long)param[0]);
 			}
 			
 			for(long acId:constiWiseResults.keySet())
 			{
 				DashBoardResultsVO vo = new DashBoardResultsVO();
 				vo.setAssemblyNo(acId);
 				vo.setAssemblyName(constituencyDAO.get(acId).getName());
 				DashBoardResultsVO vo1 = constiWiseResults.get(acId);
 				List<DashBoardResultsVO> subList = new ArrayList<DashBoardResultsVO>();
 				subList.add(vo1);
 				vo.setConstituencyWiseDetails(subList);
 				resultList.add(vo);				
 			} 			
		  */}catch (Exception e) {
			  e.printStackTrace();
			  LOG.error("Exception Occured in getConstituencyWiseLiveResults()" ,e);
		  }
 		  return resultList;
  }
	 
	 
	 public  List<DashBoardResultsVO> getWonAndLeadCountPartyWise(Long electionId,List<Long> locationIds,Long scopeId,Long electionScopeId)
	 {
		 List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();
		 try
		 {
			 
			 List<Long> prtyIds = new ArrayList<Long>();
				
				prtyIds.add(872L);
				prtyIds.add(362L);
				prtyIds.add(1117L);
				prtyIds.add(886L);
				prtyIds.add(163L);
				prtyIds.add(662L);
				prtyIds.add(9999L);
				
				Map<Long,String> partyMap = new HashMap<Long, String>();
				List<Long> partyIds = new ArrayList<Long>();
				
				partyMap.put(872L, "TDP");
				partyMap.put(362L, "INC");
				partyMap.put(1117L,"YCP");
				partyMap.put(886L, "TRS");
				partyMap.put(163L, "BJP");
				partyMap.put(662L, "PRP");
				partyMap.put(9999L,"OTH"); //for others we considered id as 9999
				
				Map<Long,Long> locationwiseMap = new HashMap<Long, Long>();
			 if(scopeId.longValue() == 4L){ // for  parliament wise win and lead report
				 
				 List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(locationIds);
					List<Long> constiIds = null;
					
					if(constiList != null && constiList.size()>0){
						constiIds = new ArrayList<Long>();
						for (Object[] constituency : constiList) {
							constiIds.add((Long)constituency[0]);
						}
					}
					
					List<Object[]> parliaments = null ;
					if(electionScopeId.longValue() == 1L){
						parliaments = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constiIds);
						if(parliaments != null && parliaments.size()>0){
							locationIds.clear();
							for (Object[] constituency : parliaments) {
								if(!locationIds.contains((Long)constituency[0]))
									locationIds.add((Long)constituency[0]);
							}					
						}
					}
					
					
					 List<Object[]> list = nominationDAO.getWonAndLeadCountPartyWise(electionId, locationIds, scopeId);
					 
					 
					 if(list != null && list.size()>0){
						 
						 for (Object[] param : list) {
							 DashBoardResultsVO locationVO = new DashBoardResultsVO();
							 locationVO.setId((Long)param[1]);
							 locationVO.setName(partyMap.get((Long)param[1]) != null? partyMap.get((Long)param[1]).toString() :"OTH");
							 locationVO.setWinTotalCount((Long)param[0]);	
							 
							 if(Long.parseLong(param[3].toString()) == 1L)
								 locationVO.setWinCount(Long.parseLong(param[0].toString()));
								else
									locationVO.setLeadCount(Long.parseLong(param[0].toString()));
							 
							 resultList.add(locationVO);
						}
					 }
					 
			 }
			 else{
				 List<Object[]> list = nominationDAO.getWonAndLeadCountPartyWise(electionId, locationIds, scopeId);
				 
				 
				 List<Long> locationsIds = new ArrayList<Long>();
					
					for(Object[] obj:list)
					{
						if(!locationsIds.contains(Long.parseLong(obj[1].toString())))
							locationsIds.add(Long.parseLong(obj[1].toString()));
						
						if(prtyIds.contains(Long.parseLong(obj[3].toString())) && !partyIds.contains(Long.parseLong(obj[3].toString())))
							partyIds.add(Long.parseLong(obj[3].toString()));
					}
					partyIds.add(9999L);
					
					
					for(Long locationId:locationsIds)
					{
						DashBoardResultsVO locationVO = new DashBoardResultsVO();
						locationVO.setId(locationId);
						
						for(Long partyId:partyIds)
						{
							DashBoardResultsVO partyVO = new DashBoardResultsVO();
							partyVO.setId(partyId);
							partyVO.setName(partyMap.get(partyId));
							
							locationVO.getPartiesDetails().add(partyVO);
							
						}
						resultList.add(locationVO);
					}
					
					Long winCount1 = 0L;
					for(Object[] obj:list)
					{
						DashBoardResultsVO locationVO =  getMacthedVO(resultList,Long.parseLong(obj[1].toString()));
						locationVO.setName(obj[2].toString());
						
						DashBoardResultsVO partyVO = null;
						if(partyIds.contains(Long.parseLong(obj[3].toString())))
						 partyVO =  getMacthedVO(locationVO.getPartiesDetails(),Long.parseLong(obj[3].toString()));
						
					
						
						 if(partyVO == null)
						 {
							 partyVO = getMacthedVO(locationVO.getPartiesDetails(),9999L);
							 partyVO.setName("OTH");
							 
							 if(Long.parseLong(obj[5].toString()) == 1L){
								 
								Long winCount2 = locationVO.getWinCount()!=null?locationVO.getWinCount():0L;
								 
								 winCount1 = winCount1 + Long.parseLong(obj[0].toString());
								 winCount2 = winCount2 + Long.parseLong(obj[0].toString());
								 
								// if(scopeId.longValue() == 2L){
									 locationVO.setWinCount(winCount2);
									 locationVO.setWinTotalCount(winCount1);
								// }
								 partyVO.setWinCount(partyVO.getWinCount() + Long.parseLong(obj[0].toString()));
							 }
							else{ 
								partyVO.setLeadCount(partyVO.getLeadCount() + Long.parseLong(obj[0].toString()));
							}
							 
						 }else
						 {
						    partyVO.setName(obj[4].toString());

							if(Long.parseLong(obj[5].toString()) == 1L){
								 
								Long winCount2 = locationVO.getWinCount()!=null?locationVO.getWinCount():0L;
								 
								 winCount1 = winCount1 + Long.parseLong(obj[0].toString());
								 winCount2 = winCount2 + Long.parseLong(obj[0].toString());
								 
								// if(scopeId.longValue() == 2L){
									 locationVO.setWinCount(winCount2);
									 locationVO.setWinTotalCount(winCount1);
								// }
								 
								partyVO.setWinCount(Long.parseLong(obj[0].toString()));
							}
							else{
								partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
							}
						 }

						/*if(Long.parseLong(obj[5].toString()) == 1L)
							partyVO.setWinCount(Long.parseLong(obj[0].toString()));
						else
							partyVO.setLeadCount(Long.parseLong(obj[0].toString()));*/
						
					}
					
				
					LinkedHashMap<Long,DashBoardResultsVO> countMap = new LinkedHashMap<Long, DashBoardResultsVO>();
					
					if(resultList != null && resultList.size()>0){
						for (DashBoardResultsVO locationVO : resultList) {	
							
								for(Long partyId:partyIds)
								{	
									DashBoardResultsVO vo1 = new DashBoardResultsVO();
									DashBoardResultsVO vo = getMacthedVO(locationVO.getPartiesDetails(),partyId); //tdp 									
									Long winCount = 0L;
									vo1.setName(vo.getName());
									vo1.setId(vo.getId());
									
									if(countMap.get(partyId) != null){
										vo1 = countMap.get(partyId);
										winCount = vo1.getWinCount();
									}
									
									winCount = winCount + vo.getWinCount();
										
									vo1.setWinCount(winCount);
									vo1.setWinTotalCount(winCount1); // total count
									countMap.put(partyId, vo1);	
								}									
						}
					}
					
					
					if(countMap != null && countMap.size()>0){
						DashBoardResultsVO partyWiseCountVO = new DashBoardResultsVO();	
						partyWiseCountVO.setName("TOTAL");
						List<DashBoardResultsVO> partyVOList = new ArrayList<DashBoardResultsVO>();
						for (Long partyId : countMap.keySet()) {							
							DashBoardResultsVO partyVO = countMap.get(partyId);
							partyVOList.add(partyVO);
						}
						partyWiseCountVO.setPartiesDetails(partyVOList);
						resultList.add(partyWiseCountVO);
					}
					
					
			 }
										
		 }catch(Exception e)
		 {
			  e.printStackTrace();	
			  LOG.error("Entered Into getWonAndLeadCountPartyWise() in DashboardElectionResultsService class."); 
		 }
		 return resultList;
	 }
	 public List<GenericVO> getPartiesInConsituenciesOfElection(Long electionId,List<Long> constituencyIds){
		  LOG.debug("Entered Into getPartiesInConsituenciesOfElection"); 
		  List<GenericVO> resultList  = new ArrayList<GenericVO>();
		  try {
			  
			  List<Object[]> list =  nominationDAO.getPartysInfoForAParticularElectionYearInConsitutencies(electionId,constituencyIds);
			  
			  List<Long> partyIds = new ArrayList<Long>();
			  
			    partyIds.add(872L);
				partyIds.add(362L);
				partyIds.add(1117L);
				partyIds.add(886L);
				partyIds.add(163L);
				
			  if(list!=null && list.size()>0){
				  for(Object[] lst:list){
					  GenericVO gv = new GenericVO();
					  gv.setId(Long.valueOf(lst[1].toString()));
					  gv.setName(lst[0].toString());				  
					  
					  if(partyIds.contains(Long.valueOf(lst[1].toString())))
						  resultList.add(0,gv);
					  else
					      resultList.add(gv);
				  }
			  }
		  }catch (Exception e) {
			  Log.error("Exception Raised in getPartiesInConsituenciesOfElection"+e);
		}
		return resultList;
	 }
	 
	 public List<PartyResultVO> partysVotesShareInConstituenciesOfElection(Long electionId,List<Long> constituencyIds,List<Long> partyIds){
		  LOG.debug("Entered Into partysVotesShareInConstituenciesOfElection"); 
		  List<PartyResultVO> resultList  = new ArrayList<PartyResultVO>();
		  try {
			  
			  List<Object[]> list =  nominationDAO.partysVotesShareInConstituenciesOfElection(electionId,constituencyIds,partyIds);
			  
			  Map<Long,PartyResultVO> constiMap = new HashMap<Long, PartyResultVO>();
			  
			  List<Object[]> parties = partyDAO.getPartyShortNameByIds(partyIds);
			  
			  if(list!=null && list.size()>0){
				  for(Object[] param:list){
					  PartyResultVO  pv = constiMap.get(Long.valueOf(param[0].toString()));
					  if(pv == null){
						  pv = new PartyResultVO();
						  pv.setConstituencyId(Long.valueOf(param[0].toString()));
						  pv.setConstituencyName(param[1].toString());
						  pv.setAcNo(Long.valueOf(param[5].toString()));
						  pv.setPcName(param[6].toString());
						  pv.setPcNo(Long.valueOf(param[7].toString()));
						  pv.setPartyResultVo(getPartiesForConstituency(parties));
						  
						  String ttlVotes = param[8].toString();
						  String ttlVotesEarn[] = StringUtils.split(ttlVotes, ".");
							
						  String validVotes = param[9].toString();
						  String VldvotesEarn[] = StringUtils.split(validVotes, ".");
						  
						  pv.setTtlVts(ttlVotesEarn[0]);
						  pv.setValidVts(VldvotesEarn[0]);
						  
					  }
					  
					  	List<PartyResultVO> partyResults = pv.getPartyResultVo();
					  	if(partyResults!=null && partyResults.size()>=0){
					  		
					  		PartyResultVO party = getMatchedParty(partyResults, Long.valueOf(param[4].toString()));
					  		
					  		String votes = param[10].toString();
							String votesEarn[] = StringUtils.split(votes, ".");
							
					  		if(party.getPartyVotes()==null){
					  			party.setPartyVotes(votesEarn[0]);
					  		}else{
					  			Double vtsEarned = Double.parseDouble(param[10].toString());
					  			Double vtsAlrdyErned  = Double.parseDouble(party.getPartyVotes());
					  			
					  			String votes1 = String.valueOf(vtsEarned+vtsAlrdyErned);
								String votesEarn1[] = StringUtils.split(votes1, ".");
					  			
					  			party.setPartyVotes(votesEarn1[0]);
					  		}
					  		
					  	}
					  	
					  	 Collections.sort(partyResults, sortByPartyId);
					  	 pv.setPartyResultVo(partyResults);
					  	 
					  	constiMap.put(Long.valueOf(param[0].toString()), pv);
				  }
			  }
			  
			  resultList = new ArrayList<PartyResultVO>(constiMap.values());
			  
			  for(PartyResultVO mainVO:resultList)
			  {
				  for(PartyResultVO partyVO:mainVO.getPartyResultVo())
				  {
					  if(partyVO.getPartyVotes() != null)
					partyVO.setPercent(Long.parseLong(mainVO.getValidVts()) != 0L ? roundTo2DigitsFloatValue((float) Long
							.parseLong(partyVO.getPartyVotes())
							* 100
							/ Long.parseLong(mainVO.getValidVts())) : "0.00");
				  }
				  
			  }
			  
		  }catch (Exception e) {
			  Log.error("Exception Raised in partysVotesShareInConstituenciesOfElection"+e);
		}
		  
		  Collections.sort(resultList,sortByConstieucnyName);
		return resultList;
	 }
	 
	 public static Comparator<PartyResultVO> sortByConstieucnyName = new Comparator<PartyResultVO>()
			    {
			        public int compare(PartyResultVO locationVO1, PartyResultVO locationVO2)
			        {
			            return locationVO1.getConstituencyName().compareTo(locationVO2.getConstituencyName());
			        }
			    };
	 
	 public List<PartyResultVO> getPartiesForConstituency(List<Object[]> prtyRslts){
		 List<PartyResultVO> partyResults = new ArrayList<PartyResultVO>();
		 
		 if(prtyRslts!=null && prtyRslts.size()>0){
			 for(Object[] obj:prtyRslts){
				 PartyResultVO pv = new PartyResultVO();
				 pv.setPartyId(Long.valueOf(obj[0].toString()));
				 pv.setPartyName(obj[1].toString());
				 
				 partyResults.add(pv);
			 }
		 }
		 
		 Collections.sort(partyResults, sortByPartyId);
		 return partyResults;
	 }
	 
	 public PartyResultVO getMatchedParty(List<PartyResultVO> partyResults,Long partyId){
		 if(partyResults!=null && partyResults.size()>0 && partyId!=null){
			 for(PartyResultVO party:partyResults){
				 if(party.getPartyId().equals(partyId)){
					 return party;
				 }
			 }
		 }
		 
		 return null;
	 }
	 
	 public static Comparator<PartyResultVO> sortByPartyId = new Comparator<PartyResultVO>()
    {
  
        public int compare(PartyResultVO resultList1, PartyResultVO resultList2)
        {
            if(resultList1.getPartyId() == null || resultList2.getPartyId() == null){
                return 0;
            }
            else{
           	 return (resultList1.getPartyId()).compareTo(resultList2.getPartyId());
            }
        }
    };
    
	public DashBoardResultsVO getPartyWiseWinningSeatsCount(
			Long electionId, List<Long> locationIds, Long scopeId,
			Long percent, Long partyId)    {
    	LOG.debug("Entered into the getConstituencyResultDetailsByElectionId service method");

    	List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();
    	Map<String,Long> partyCountBefore = null;
    	Map<String,Long> partyCountAfter = null;
    	try
    	{
    		List<Object[]> list = nominationDAO.getConstituencyResultDetailsByElectionId(electionId, locationIds, scopeId);
    		
    		Set<Long> constituencyIds = new HashSet<Long>();
    		for(Object[] obj:list)
    			constituencyIds.add(Long.parseLong(obj[2].toString()));
    		
    		for(Long constituencyId:constituencyIds)
    		{
    			DashBoardResultsVO constituency = new DashBoardResultsVO();
    			constituency.setId(constituencyId);
    			resultList.add(constituency);
    		}
    		
    		for(Object[] obj:list)
    		{
    			DashBoardResultsVO constituencyVO = getMacthedVO(resultList, Long.parseLong(obj[2].toString()));
    			constituencyVO.setName(obj[3].toString());
    			
    			DashBoardResultsVO partyVO = new DashBoardResultsVO();
    			
    			partyVO.setId(Long.parseLong(obj[0].toString()));
    			partyVO.setName(obj[1].toString());
    			partyVO.setRank(Long.parseLong(obj[4].toString()));
    			partyVO.setGainedVotes((long)Double.parseDouble(obj[5].toString()));
    			constituencyVO.getPartyIds().add(Long.parseLong(obj[0].toString()));
    			
    			constituencyVO.getSubList().add(partyVO);
    		}
    		
    		
    		Iterator<DashBoardResultsVO> itr = resultList.iterator();
    		
    		while(itr.hasNext())
    		{
    			DashBoardResultsVO constituencyVO = itr.next();
    			
    			if(!constituencyVO.getPartyIds().contains(partyId))
    				itr.remove();
    		}
    		
    		
    		
    		 partyCountBefore = getPartyWiseWinningCount(resultList);
    		
    		caluculateValidVotesForConstituency(resultList);//this method calculates all the valid votes of constituencies
    		
    		Map<String,Long> voterCountMap = new HashMap<String, Long>();
    		
    		Long totalValidVotersCount = 0L;
    		
    		for(DashBoardResultsVO constituencyVO:resultList)
    		{
    			totalValidVotersCount  = totalValidVotersCount + constituencyVO.getVotesCount();
    		}
    		
    		
    		for(DashBoardResultsVO constituencyVO:resultList)
    		{
    			for(DashBoardResultsVO partyVO:constituencyVO.getSubList())
        		{
    				if(voterCountMap.get(partyVO.getName()+","+partyVO.getId()) == null)
    				{
    					voterCountMap.put(partyVO.getName()+","+partyVO.getId(), partyVO.getGainedVotes());
    				}else
    				{
    					voterCountMap.put(partyVO.getName()+","+partyVO.getId(), voterCountMap.get(partyVO.getName()+","+partyVO.getId()) + partyVO.getGainedVotes());
    				}
        		}
    		}
    		
    		List<Long> partyIds = new ArrayList<Long>();
			
    			partyIds.add(72L);
				partyIds.add(872L);
				partyIds.add(362L);
				partyIds.add(1117L);
				partyIds.add(886L);
				partyIds.add(163L);
				partyIds.add(9999L);
				
			List<DashBoardResultsVO> percentList = new ArrayList<DashBoardResultsVO>();
			
			for(Entry<String, Long> entry:voterCountMap.entrySet())
    		{
				if(partyIds.contains(Long.parseLong(entry.getKey().split(",")[1])))
				{
					DashBoardResultsVO party = new DashBoardResultsVO();
					
					party.setPercent(entry.getValue() != 0L ? roundTo2DigitsFloatValue((float) entry.getValue()
							* 100f
							/totalValidVotersCount) : "0.00");
					
					party.setName(entry.getKey().split(",")[0]);
					party.setGainedVotes(entry.getValue());
					
					percentList.add(party);
				}
    		}
			
			Double totalPercent = 0.0;
			for(DashBoardResultsVO partyVO:percentList)
			{
				totalPercent = totalPercent + Double.parseDouble(partyVO.getPercent());
			}
			
			DashBoardResultsVO other = new DashBoardResultsVO();
			other.setName("OTHERS");
			
			Double remainingPercent = 100 - totalPercent; 
			other.setPercent(remainingPercent.toString());
    			
    		
    		removeVotesFromSelectedPartyBySelectedPercent(resultList,partyId,percent);//this method minus the votes for selected party
    		setRankByGainedVotes(resultList);//this method sets the rank for parties after voters decrease
    		
    		 partyCountAfter = getPartyWiseWinningCount(resultList);
    		
    		
    		Map<String,Integer> resultMap = new HashMap<String, Integer>(); 
    		Long beforeCount = 0L;
			Long afterCount  = 0L;
    		
    		for(Entry<String, Long> entry:partyCountBefore.entrySet())
    		{
    			 beforeCount = entry.getValue() != null ?entry.getValue():0;
    			 afterCount = partyCountAfter.get(entry.getKey()) != null ? partyCountAfter.get(entry.getKey()):0;
    			
    			resultMap.put(entry.getKey(),(int)( afterCount - beforeCount ));
    		}
    		
    		if(resultList != null && resultList.size() >0)
    		{
    			resultList.get(0).setPartyWiseCountAfter(partyCountAfter);
    			resultList.get(0).setPartyWiseCountBefore(partyCountBefore);
    			resultList.get(0).setResultMap(resultMap);
    			resultList.get(0).setPartiesDetails(percentList);
    		}
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in getConstituencyResultDetailsByElectionId service method");
    	}
    	if(resultList != null && resultList.size() > 0 )
    	  return resultList.get(0);
    	return null;
    }
    
    private Map<String,Long> getPartyWiseWinningCount(List<DashBoardResultsVO> resultList)
    {
    	LOG.debug("Entered into the getPartyWiseWinningCount service method");
    	Map<String,Long> countMap = null;
    	try
    	{
    		countMap = new HashMap<String, Long>();
	    	for(DashBoardResultsVO constituency:resultList)
			{
				for(DashBoardResultsVO party:constituency.getSubList())
				{
					if(party.getRank().longValue() == 1L)
					if(countMap.get(party.getName()) == null)
					{
						countMap.put(party.getName(), 1L);
					}
					else
					{
						countMap.put(party.getName(), countMap.get(party.getName()) +1);
					}
				}
			}
	    	
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in getPartyWiseWinningCount service method");
    	}
    	
    	return countMap;
    }
	
    public void removeVotesFromSelectedPartyBySelectedPercent(List<DashBoardResultsVO> resultList,Long partyId,Long percent)
    {
    	LOG.debug("Entered into the removeVotesFromSelectedPartyBySelectedPercent service method");
    	try
    	{
    		for(DashBoardResultsVO constituency:resultList)
    		{
    			Long totalValidVotes = constituency.getVotesCount();
    			
    			Long votesToRemove = (long)percent*totalValidVotes/100;
    			
    			for(DashBoardResultsVO party:constituency.getSubList())
    			{
    				if(party.getId().equals(partyId))
    					party.setGainedVotes(party.getGainedVotes() - votesToRemove);
    			}
    			
    			Collections.sort(constituency.getSubList(),sortByValidVotes);
    		}
    		
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in removeVotesFromSelectedPartyBySelectedPercent service method");
    	}
    }
    
    public void setRankByGainedVotes(List<DashBoardResultsVO> resultList)
    {
    	LOG.debug("Entered into the setRankByGainedVotes service method");
    	try
    	{
    		for(DashBoardResultsVO constituency:resultList)
    		{
    			for(int i=0;i<constituency.getSubList().size();i++)
    			{
    				constituency.getSubList().get(i).setRank((long)i+1);
    			}
    		}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in setRankByGainedVotes service method");
    	}
    }
    
    public void caluculateValidVotesForConstituency(List<DashBoardResultsVO> resultList)
    {
    	LOG.debug("Entered into the caluculateValidVotesForConstituency service method");
    	try
    	{
    		for(DashBoardResultsVO constituency:resultList)
    		{
    			Long totalValidVotes = 0L;
    			
    			for(DashBoardResultsVO party:constituency.getSubList())
    				totalValidVotes = totalValidVotes + party.getGainedVotes();
    			
    			constituency.setVotesCount(totalValidVotes);
    		}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in caluculateValidVotesForConstituency service method");
    	}
    }
    
    public static Comparator<DashBoardResultsVO> sortByValidVotes = new Comparator<DashBoardResultsVO>()
    {
        public int compare(DashBoardResultsVO locationVO1, DashBoardResultsVO locationVO2)
        {
        	if(locationVO1.getGainedVotes() > locationVO2.getGainedVotes())
    			return -1;
    		else if(locationVO1.getGainedVotes() < locationVO2.getGainedVotes())
    			return 1;
    		
    		return 0;
        }
        
    };
   
    
    
    public List<OptionVO> getTop5CastePeopleOpnionOnParty(Long constituencyId,List<Long> surveyIds){
     List<OptionVO> surveyDataCasteWise = null;
     try{
    	List<Long> partyIds = new ArrayList<Long>();
    	partyIds.add(163l);
    	partyIds.add(872l);
    	//0 partyId,1 votesearned
    	List<Object[]> candidateResult = candidateResultDAO.getResultByPartyIds(partyIds,constituencyId,258l);
    	List<Object[]> totalValidVotesDetails = constituencyElectionResultDAO.getTotalValidVotes(258l, constituencyId);
    	Double constituencyTotalVoters = (Double)totalValidVotesDetails.get(0)[0];
    	Double totalValidVotes = (Double)totalValidVotesDetails.get(0)[1];
    	Long totalVotes = null;
    	if(candidateResult.size() == 1){
    		totalVotes = ((Double)candidateResult.get(0)[1]).longValue();
    	}else{
    		for(Object[] result:candidateResult){
    			if(((Long)result[0]).longValue() == 872l){
    				totalVotes = ((Double)result[1]).longValue();
    			}
    		}
    	}
    	// surveyDataCasteWise = webServiceClient.getTop5CastePeopleOpnionOnParty(constituencyId,surveyIds,constituencyTotalVoters.longValue());
    	OptionVO.CastePercs values = surveyDataCasteWise.get(0).getCastePercs();
    	surveyDataCasteWise.get(0).setTotal(totalVotes);
    	LinkedHashMap<Long,Double> castePercs = new LinkedHashMap<Long,Double>();
    	LinkedHashMap<Long,Long> casteVoters = new LinkedHashMap<Long,Long>();
    	for(OptionVO.CastePercs.Entry enter:values.getEntry()){
    		castePercs.put(enter.getKey(), enter.getValue());
    	}
    	for(Long key:castePercs.keySet()){
    		casteVoters.put(key, ((Double)(totalValidVotes*castePercs.get(key)/100)).longValue());
    	}
    	Double partyPerc = Double.parseDouble((new BigDecimal(totalVotes*100/totalValidVotes.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
    	surveyDataCasteWise.get(0).setTotalPercentage(partyPerc);
    	//List<Long> topCastes = surveyDataCasteWise.get(0).getAverageBoothIdsList();
      /*List<Double> avgperc = surveyDataCasteWise.get(0).getAvgPercs();
    	SelectOptionVO cumulativeVo = new SelectOptionVO();
    	cumulativeVo.setName("Cumulative Of All Surveys");
    	List<Long> cumulativeVotes = new ArrayList<Long>();
    	
    	for(int i=0;i<avgperc.size();i++){
    		if(avgperc.get(i) != null){
    			cumulativeVotes.add(((Double)(casteVoters.get(topCastes.get(i))*avgperc.get(i)/100)).longValue());
    		}else{
    			cumulativeVotes.add(null);
    		}
    	}
    	cumulativeVo.setLocationValuesList(cumulativeVotes);
    	cumulativeVo.setTotalCount(totalVotes.longValue());
    	resultList.add(cumulativeVo);*/
    	/*for(OptionVO survey:surveyDataCasteWise){
    		survey.setCastePercs(null);
    		List<OptionVO> surveyperc = survey.getPercents();
        	for(int i=0;i<surveyperc.size();i++){
        		if(surveyperc.get(i).getPercentage() != null && surveyperc.get(i).getPercentage()>0){
        			surveyperc.get(i).setGoodBoothCount(((Double)(casteVoters.get(topCastes.get(i))*surveyperc.get(i).getPercentage()/100)).longValue());
        		}else{
        			surveyperc.get(i).setGoodBoothCount(0l);
        		}
        	}
            surveyperc = survey.getCorrectionPercs();
        	for(int i=0;i<surveyperc.size();i++){
        		if(surveyperc.get(i).getPercentage() != null && surveyperc.get(i).getPercentage()>0){
        			surveyperc.get(i).setGoodBoothCount(((Double)(casteVoters.get(topCastes.get(i))*surveyperc.get(i).getPercentage()/100)).longValue());
        		}else{
        			surveyperc.get(i).setGoodBoothCount(0l);
        		}
        	}
    	}*/
     }catch(Exception e){
    	 LOG.error("Exception rised in  getTop5CastePeopleOpnionOnParty",e);
     }
    	return surveyDataCasteWise;
    }
    public List<OptionVO> getTop5CastePeopleOpnionOnPartyStateWide(Long stateType,List<Long> surveyIds){
        List<OptionVO> surveyDataCasteWise = null;
        try{
        	//surveyDataCasteWise = webServiceClient.getTop5CastePeopleOpnionOnPartyStateWide(stateType,surveyIds);
        }catch(Exception e){
       	 LOG.error("Exception rised in  getTop5CastePeopleOpnionOnPartyStateWide",e);
        }
       	return surveyDataCasteWise;
       }
    public DashBoardResultsVO getPartyWiseWinningSeatsPercentage(
			Long electionId, List<Long> locationIds, Long scopeId,
			Long percent, Long partyId)    {
    	LOG.debug("Entered into the getConstituencyResultDetailsByElectionId service method");
    	List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();
    	try
    	{
    		List<Object[]> list = nominationDAO.getConstituencyResultDetailsByElectionId(electionId, locationIds, scopeId);
    		
    		Set<Long> constituencyIds = new HashSet<Long>();
    	
    		for(Object[] obj:list)
    		{
    			if(constituencyIds.add(Long.parseLong(obj[2].toString())))
    			{
    			DashBoardResultsVO constituency = new DashBoardResultsVO();
    			constituency.setId(Long.parseLong(obj[2].toString()));
    			constituency.setSubList(getStaticParties());
    			resultList.add(constituency);
    			}
    		}
    		for(Object[] obj:list)
    		{
    			DashBoardResultsVO constituencyVO = getMacthedVO(resultList, Long.parseLong(obj[2].toString()));
    			constituencyVO.setName(obj[3].toString());
    			DashBoardResultsVO partyVo = getPartyVo(constituencyVO.getSubList(),Long.parseLong(obj[0].toString()));
    			if(partyVo != null)
    			{
    				partyVo.setRank(Long.parseLong(obj[4].toString()));
        			partyVo.setGainedVotes(partyVo.getGainedVotes() + (long)Double.parseDouble(obj[5].toString()));
        		//	constituencyVO.getPartyIds().add(Long.parseLong(obj[0].toString()));
    				
    			}
    			else
    			{
    				DashBoardResultsVO otherPartyVo = constituencyVO.getSubList().get(constituencyVO.getSubList().size() - 1);
    				Long votes = constituencyVO.getSubList().get(constituencyVO.getSubList().size() - 1).getGainedVotes();
    				otherPartyVo.setGainedVotes((long)Double.parseDouble(obj[5].toString()) + votes);
    				
    			}
    			//constituencyVO.getPartyIds().add(0l);
    		}
    		
    		/*caluculateValidVotesForConstituency(resultList);//this method calculates all the valid votes of constituencies
    		
    	
    		
    		
    		for(DashBoardResultsVO constituencyVO:resultList)
    		{
    			totalValidVotersCount  = totalValidVotersCount + constituencyVO.getVotesCount();
    		}*/
    	Map<String,Long> voterCountMap = new HashMap<String, Long>();
    	Long totalValidVotersCount = 0L;
    	List<Object[]> countList =nominationDAO.getConstituencyResultDetailsByElectionIdCount(electionId, locationIds, scopeId);
    	if(countList != null && countList.size() > 0)
    	{
    		for(Object[] params : countList)
    		{
    		Double total = new Double(params[0].toString());
    		totalValidVotersCount = total.longValue();
    		}
    		
    	}	
    		for(DashBoardResultsVO constituencyVO:resultList)
    		{
    			for(DashBoardResultsVO partyVO:constituencyVO.getSubList())
        		{
    				if(voterCountMap.get(partyVO.getName()+","+partyVO.getId()) == null)
    				{
    					voterCountMap.put(partyVO.getName()+","+partyVO.getId(), partyVO.getGainedVotes());
    				}else
    				{
    					voterCountMap.put(partyVO.getName()+","+partyVO.getId(), voterCountMap.get(partyVO.getName()+","+partyVO.getId()) + partyVO.getGainedVotes());
    				}
        		}
    		}
    		
    		
			List<DashBoardResultsVO> percentList = new ArrayList<DashBoardResultsVO>();
			for(Entry<String, Long> entry:voterCountMap.entrySet())
    		{
				
					DashBoardResultsVO party = new DashBoardResultsVO();
				    party.setPercent(entry.getValue() != 0L ? roundTo2DigitsFloatValue((float) entry.getValue()
							* 100f
							/totalValidVotersCount) : "0.00");
					party.setName(entry.getKey().split(",")[0]);
					party.setGainedVotes(entry.getValue());
					party.setPartyId(Long.parseLong(entry.getKey().split(",")[1]));
					percentList.add(party);
				
    		}
			Map<String,String> resultMap = new HashMap<String, String>(); 
			for(DashBoardResultsVO vo : percentList)
			{
				resultMap.put(vo.getName(), vo.getPercent());
			
			}
		
    		if(resultList != null && resultList.size() >0)
    		{
    			resultList.get(0).setPartiesDetails(percentList);
    			resultList.get(0).setPercentageMap(resultMap);//before
    		
    		}
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in getConstituencyResultDetailsByElectionId service method");
    	}
    	if(resultList != null && resultList.size() > 0 )
    	  return resultList.get(0);
    	return null;
    }
    
    
    public List<DashBoardResultsVO> getStaticParties()
    {
    	List<Long> partyIds = new ArrayList<Long>();
		partyIds.add(72L);
		partyIds.add(872L);
		partyIds.add(362L);
		partyIds.add(1117L);
		partyIds.add(886L);
		partyIds.add(163L);
		partyIds.add(239L);
		List<DashBoardResultsVO> parties = new ArrayList<DashBoardResultsVO>();
    	try{
    		for(Long partyId : partyIds)
    		{
    			DashBoardResultsVO party = new DashBoardResultsVO();
    			party.setId(partyId);
    			party.setName(partyDAO.getPartyShortNameById(partyId));
    			party.setGainedVotes(0l);
    			parties.add(party);
    		}
    		DashBoardResultsVO oterParty = new DashBoardResultsVO();
    		oterParty.setId(0l);
    		oterParty.setName("OTHERS");
    		oterParty.setGainedVotes(0l);
			parties.add(oterParty);
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
		return parties;
    }
    
    
    

public GenericVO getparticipatedPartiesInLocation(Long electionId,List<Long> regionIds,Long electionScopeId,Long scope){
	List<GenericVO> resultList = new ArrayList<GenericVO>();
	List<Long> constiIds = null;
	GenericVO returnVO = new GenericVO();
	try {
		 List<Long> prtyIds = new ArrayList<Long>();
			prtyIds.add(872L);
			prtyIds.add(362L);
			prtyIds.add(1117L);
			prtyIds.add(886L);
			prtyIds.add(163L);
			prtyIds.add(662L);
			prtyIds.add(9999L);
	
			
			if(electionScopeId.longValue() == 1L){
				GenericVO genercVO = new GenericVO();
				List<GenericVO> returnList = null;
				List<Object[]> regions = stateSubRegionDAO.getStateRegionsBySubRegionIds(regionIds);
				if(regions != null && regions.size()>0){
					for (Object[] regionName : regions) {
						genercVO.setId(regionName[0] != null ? (Long) regionName[0]:0L);
						genercVO.setName(regionName[1] != null ? regionName[1].toString():"");
					}
				}
				List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(regionIds);
				
				
				
				if(constiList != null && constiList.size()>0){
					constiIds = new ArrayList<Long>();
					for (Object[] constituency : constiList) {
						constiIds.add((Long)constituency[0]);
					}
				}
				
				List<Object[]> parliaments = null ;
				if(electionScopeId != null){
					if(electionScopeId.longValue() == 1L){
						parliaments = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constiIds);
						if(parliaments != null && parliaments.size()>0){
							constiIds.clear();
							for (Object[] constituency : parliaments) {
								if(!constiIds.contains((Long)constituency[0]))
									constiIds.add((Long)constituency[0]);
							}					
						}
					}
				}
				
				List<Object[]> participants  = nominationDAO.getPartiwiseParticipatedCountInAElection(electionId,constiIds);
				
				LinkedHashMap<Long,GenericVO> participantsMap = new LinkedHashMap<Long, GenericVO>();
				if(participants != null && participants.size()>0){
					
					for (Object[] party : participants) {
						if(prtyIds.contains((Long) party[0])){
							GenericVO vo = new GenericVO();
							vo.setId((Long) party[0]);
							vo.setName(party[1].toString());
							vo.setCount((Long) party[2]);						
							participantsMap.put((Long) party[0], vo);
						}
						else{
							GenericVO vo = new GenericVO();
							if(participantsMap.get(9999L) != null){
								vo = participantsMap.get(9999L) ;
							}
							
							vo.setId(9999L);
							vo.setName("OTH");
							Long partyCount = vo.getCount()!= null ?vo.getCount():0L;
							Long count = partyCount + (Long) party[2];
							vo.setCount(count);						
							participantsMap.put(9999L, vo);
							
						}					
					}
					
					if(participantsMap != null && participantsMap.size()>0){
						 returnList = new ArrayList<GenericVO>();
						for (Long partyId : prtyIds) {							
							GenericVO partyVO = participantsMap.get(partyId);
							if(partyVO != null)
								returnList.add(partyVO);
						}								
						genercVO.setGenericVOList(returnList);								
						resultList.add(genercVO);
					}
				}
				
			}
			else
			{
				List<Object[]> regions = stateSubRegionDAO.getStateRegionsBySubRegionIds(regionIds);
				
				if(regions != null && regions.size()>0){
					for (Object[] region : regions) {
						GenericVO genercVO = new GenericVO();
						List<GenericVO> returnList = null;
						genercVO.setName(region[1] != null ?region[1].toString():"");
						genercVO.setId(region[0] != null ?(Long)region[0]:0L);
						
						List<Object[]> regionsList = stateSubRegionDAO.getStateSubRegionsByRegionId((Long)region[0]);
						
						if(regionsList != null && regionsList.size()>0){
							regionIds.clear();
							for (Object[] stateSubRegions : regionsList) {
								regionIds.add((Long)stateSubRegions[0]);
							}
						}
						
						if(scope.longValue() == 4L){
							List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(regionIds);
							
							
							if(constiList != null && constiList.size()>0){
								constiIds = new ArrayList<Long>();
								for (Object[] constituency : constiList) {
									constiIds.add((Long)constituency[0]);
								}
							}
						}			
						else{
							List<Object[]> constiList = stateSubRegionDistrictDAO.getAssemblyConstituenciesBydistricts(regionIds);
							
							if(constiList != null && constiList.size()>0){
								constiIds = new ArrayList<Long>();
								for (Object[] constituency : constiList) {
									constiIds.add((Long)constituency[0]);
								}
							}
							
						}
							List<Object[]> parliaments = null ;
							if(electionScopeId != null){
								if(electionScopeId.longValue() == 1L){
									parliaments = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constiIds);
									if(parliaments != null && parliaments.size()>0){
										constiIds.clear();
										for (Object[] constituency : parliaments) {
											if(!constiIds.contains((Long)constituency[0]))
												constiIds.add((Long)constituency[0]);
										}					
									}
								}
							}
							
							List<Object[]> participants  = nominationDAO.getPartiwiseParticipatedCountInAElection(electionId,constiIds);
							
							LinkedHashMap<Long,GenericVO> participantsMap = new LinkedHashMap<Long, GenericVO>();
							if(participants != null && participants.size()>0){
								
								for (Object[] party : participants) {
									if(prtyIds.contains((Long) party[0])){
										GenericVO vo = new GenericVO();
										vo.setId((Long) party[0]);
										vo.setName(party[1].toString());
										vo.setCount((Long) party[2]);						
										participantsMap.put((Long) party[0], vo);
									}
									else{
										GenericVO vo = new GenericVO();
										if(participantsMap.get(9999L) != null){
											vo = participantsMap.get(9999L) ;
										}
										
										vo.setId(9999L);
										vo.setName("OTH");
										Long partyCount = vo.getCount()!= null ?vo.getCount():0L;
										Long count = partyCount + (Long) party[2];
										vo.setCount(count);						
										participantsMap.put(9999L, vo);
										
									}					
								}
								
								if(participantsMap != null && participantsMap.size()>0){
									 returnList = new ArrayList<GenericVO>();
									for (Long partyId : prtyIds) {							
										GenericVO partyVO = participantsMap.get(partyId);
										if(partyVO != null)
											returnList.add(partyVO);
									}								
									genercVO.setGenericVOList(returnList);								
									resultList.add(genercVO);
								}
							}
					}
				}
			}
			
			
			returnVO.setGenericVOList(resultList);
	} catch (Exception e) {
		  e.printStackTrace();
		  LOG.error(" Exception Occured in getparticipatedPartiesInLocation() method, Exception - "+e);
	}
	return returnVO;
}

	public GenericVO getMatchedGenericVOByName(List<GenericVO> resultList,String name){
		GenericVO returnVO = null;
		try {
			if(resultList != null && resultList.size()>0){
				for (GenericVO vo : resultList) {
					if(vo.getName().trim().equalsIgnoreCase(name.trim())){
						return vo;
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception Occured in getMatchedVOByName() method, Exception - ",e);
		}
		
		return returnVO;
	}
	public GenericVO getReservedConstiList(Long electionId,List<Long> regionIds,Long electionScopeId,Long scope){
		List<GenericVO> resultList =  new ArrayList<GenericVO>();		
		List<Long> constiIds = null;
		GenericVO returnVO = new GenericVO();
		try {
			List<String> castes = new ArrayList<String>();
			castes.add("SC");
			castes.add("ST");
			castes.add("");
			
			
			if(electionScopeId.longValue() == 1L){
				GenericVO genercVO = new GenericVO();
			
				List<Object[]> regions = stateSubRegionDAO.getStateRegionsBySubRegionIds(regionIds);
				if(regions != null && regions.size()>0){
					for (Object[] regionName : regions) {
						genercVO.setId(regionName[0] != null ? (Long) regionName[0]:0L);
						genercVO.setName(regionName[1] != null ? regionName[1].toString():"");
					}
				}
				List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(regionIds);
					if(constiList != null && constiList.size()>0){
						constiIds = new ArrayList<Long>();
						for (Object[] constituency : constiList) {
							constiIds.add((Long)constituency[0]);
						}
					}
				
					List<Object[]> parliaments = null ;
					if(electionScopeId != null){
						if(electionScopeId.longValue() == 1L){
							parliaments = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constiIds);
							if(parliaments != null && parliaments.size()>0){
								constiIds.clear();
								for (Object[] constituency : parliaments) {
									if(!constiIds.contains((Long)constituency[0]))
										constiIds.add((Long)constituency[0]);
								}					
							}
						}
					}
					List<GenericVO> returnList = new ArrayList<GenericVO>();
					if(castes != null && castes.size()>0){						
						for (String caste : castes) {
							GenericVO vo = new GenericVO();
							vo.setName(caste);
							vo.setCount(0L);					
							returnList.add(vo);
						}
					}
					
					List<Object[]> reservationList = constituencyElectionDAO.getLatestReservationZoneDetailsByConstuIds(electionId,constiIds);
									
					if(reservationList != null && reservationList.size()>0){
						for (Object[] category : reservationList) {
							GenericVO vo = getMatchedGenericVOByName(returnList,category[0] != null?category[0].toString():"");
							if(vo != null){
								if(category[0].toString().trim().length()==0){
									vo.setName("GENERAL");
								}
								Long prviousCount = vo.getCount();
								Long presentCount1 = category[1] != null && category[1].toString().trim().length()>0 ? (Long) category[1]:0L;
								prviousCount = prviousCount + presentCount1;
								vo.setCount(prviousCount);
							}
						}
					}
					
					genercVO.setGenericVOList(returnList);
					resultList.add(genercVO);	
				
			}
			else{
				List<Object[]> regions = stateSubRegionDAO.getStateRegionsBySubRegionIds(regionIds);
				
				if(regions != null && regions.size()>0){
					for (Object[] region : regions) {
						GenericVO genercVO = new GenericVO();
						genercVO.setName(region[1] != null ?region[1].toString():"");
						genercVO.setId(region[0] != null ?(Long)region[0]:0L);
						
						List<Object[]> regionsList = stateSubRegionDAO.getStateSubRegionsByRegionId((Long)region[0]);
						
						if(regionsList != null && regionsList.size()>0){
							regionIds.clear();
							for (Object[] stateSubRegions : regionsList) {
								regionIds.add((Long)stateSubRegions[0]);
							}
						}
						
						if(scope.longValue() == 4L){
							List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(regionIds);
							
							
							if(constiList != null && constiList.size()>0){
								constiIds = new ArrayList<Long>();
								for (Object[] constituency : constiList) {
									constiIds.add((Long)constituency[0]);
								}
							}
						}			
						else{
							List<Object[]> constiList = stateSubRegionDistrictDAO.getAssemblyConstituenciesBydistricts(regionIds);
							
							if(constiList != null && constiList.size()>0){
								constiIds = new ArrayList<Long>();
								for (Object[] constituency : constiList) {
									constiIds.add((Long)constituency[0]);
								}
							}
							
						}
							
						List<GenericVO> returnList = new ArrayList<GenericVO>();
						if(castes != null && castes.size()>0){						
							for (String caste : castes) {
								GenericVO vo = new GenericVO();
								vo.setName(caste);
								vo.setCount(0L);					
								returnList.add(vo);
							}
						}
						
							List<Object[]> reservationList = constituencyElectionDAO.getLatestReservationZoneDetailsByConstuIds(electionId,constiIds);
											
							if(reservationList != null && reservationList.size()>0){
								for (Object[] category : reservationList) {
									GenericVO vo = getMatchedGenericVOByName(returnList,category[0] != null?category[0].toString():"");
									if(vo != null){
										Long prviousCount = vo.getCount();
										Long presentCount1 = category[1] != null && category[1].toString().trim().length()>0 ? (Long) category[1]:0L;
										prviousCount = prviousCount + presentCount1;
										vo.setCount(prviousCount);
									}
								}
							}

															
							genercVO.setGenericVOList(returnList);
							resultList.add(genercVO);	
					}
				}
				
				if(resultList != null && resultList.size()>0){
					GenericVO countVO = new GenericVO();
					countVO.setName("TOTAL");
					
					List<GenericVO> returnList = new ArrayList<GenericVO>();
					if(castes != null && castes.size()>0){						
						for (String caste : castes) {
							GenericVO genercVO = new GenericVO();
							genercVO.setName(caste);
							genercVO.setCount(0L);					
							returnList.add(genercVO);
						}
					}
					
					for (GenericVO vo : resultList) {						
						
							if(vo.getGenericVOList() != null && vo.getGenericVOList().size()>0){
								for (GenericVO innerVO : vo.getGenericVOList()) {
									GenericVO genericVo = getMatchedGenericVOByName(returnList, innerVO.getName());
									if(genericVo != null){
										Long previousCont =genericVo.getCount();
										Long presentCount = innerVO.getCount();										
										previousCont = previousCont+presentCount;
										genericVo.setCount(previousCont);
									}									
								}
							}
					}
					
					countVO.setGenericVOList(returnList);
					resultList.add(countVO);
					
				}
				
			}
			
			returnVO.setGenericVOList(resultList);		
		} catch (Exception e) {
			 LOG.error(" Exception Occured in getReservedConstiList() method, Exception - ",e);
		}
		return returnVO;
	}
	

    public String getWinningCandidateInfoForAConstituency(Long constituencyId)
    {    	
    	LOG.debug("Entered into the getWinningCandidateInfoForAConstituency service method");
    	try
    	{
    		List<Object[]> list = nominationDAO.getWinningCandidateInfoForAConstituency(constituencyId,38L);
    		
    		if(list != null && list.size() > 0 )
    			return list.get(0)[0].toString()+"-"+list.get(0)[1].toString();
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in getWinningCandidateInfoForAConstituency service method");
    	}
		return null;
    }
    
    
    public DashBoardResultsVO getPartyWiseCountDetailsByConstituencyIdAndSurveyIds(Long constituencyId,List<Long> surveyIds,Long electionId)
    {
    	DashBoardResultsVO mainResult = new DashBoardResultsVO();
    	try
    	{
			
    	            
    		//ElectionComparisonVO result  =  webServiceClient.getOptionWiseCountDetailsForSelectedSurveysByConstituencyId(constituencyId,surveyIds);
            
    		ElectionComparisonVO result  =  new ElectionComparisonVO();
    		
    		List<SurveyReportVO> partiesResult = result.getPartyWiseResult();
    		
    		List<Long> partyIds = new ArrayList<Long>();
    		
    		for(SurveyReportVO partyVO:partiesResult)
    		{
    			if(partyVO.getName().equalsIgnoreCase("OTHERS"))
    				partyVO.setPartyId(0L);
    		}
    		
    		for(SurveyReportVO partyVO:partiesResult)
    		{
    			partyIds.add(partyVO.getPartyId());
    		}
    		
    		//partyIds.add(0L);
    		
    		List<DashBoardResultsVO>  constiResultDetails = getVoteShareByConstituencyIdAndElectionId(constituencyId,electionId,partyIds);
    		
    		
    		for(SurveyReportVO party:partiesResult)
    		{
    			DashBoardResultsVO partyVO = getMacthedVO(constiResultDetails, party.getPartyId());
    			
    			partyVO.setSurveyCount(party.getCount());
    			partyVO.setSurveyPercent(party.getPercent());
    		}
    		
    		mainResult.setSubList(constiResultDetails);
    		
    		Double castePercent =0.0;
    		for( com.itgrids.survey.soa.endpoints.GenericVO caste:result.getCasteResult())
    		{
    			castePercent = castePercent + Double.parseDouble(caste.getPercent());
    		}
    		
    		com.itgrids.survey.soa.endpoints.GenericVO others = new com.itgrids.survey.soa.endpoints.GenericVO();
    		
    		others.setName("OTHERS");
    		Double remainPercent = 100 - castePercent;
    		others.setPercent(remainPercent.toString());
    		
    		result.getCasteResult().add(others);
    		
    		mainResult.setCasteDetails(result.getCasteResult());
    		mainResult.setSurveyWiseResult(result.getSurveyWiseResult());
    		
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	return mainResult;
    }
    
    public List<DashBoardResultsVO> getVoteShareByConstituencyIdAndElectionId(Long constituencyId,Long electionId,List<Long> partyIds)
	{
		LOG.debug("Entered into the getVoteShareByConstituencyIdAndElectionId service method");
		List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();
		try
		{
				List<Object[]> list = nominationDAO.getVoteShareByConstituencyIdAndElectionId(constituencyId,electionId);
				
				Long totalValidVotes = 0L;
				
				for(Long partyId:partyIds)
				{
					DashBoardResultsVO party = new DashBoardResultsVO();
					party.setPartyId(partyId);
					party.setId(partyId);
					
					if(partyId == 0L)
						party.setName("OTHERS");
					
					resultList.add(party);
				}
		
				for(Object[] obj:list)
				{
					DashBoardResultsVO partyVO = null;
					if(partyIds.contains((Long)obj[1]))
					 partyVO = getMacthedVO(resultList, (Long)obj[1]);
					else
					 partyVO = getMacthedVO(resultList, 0L);
					
					if((Long)obj[1] != 0L)
						partyVO.setName(obj[2].toString());
					
					partyVO.setCount(partyVO.getCount() + (long)Double.parseDouble(obj[4].toString()));
					if((!partyIds.contains((Long)obj[1]) && (Long)obj[3] == 1L) || partyVO.getRank() !=1L)
					{
					  partyVO.setRank((Long)obj[3]);
					  partyVO.setCandidateName(obj[0].toString());
					  partyVO.setName(obj[2].toString());
					  partyVO.setVotesPercentage(obj[5].toString());
					  partyVO.setMarginPercent(obj[6].toString());
					}
					
					if(partyIds.contains((Long)obj[1]))
					{
					  partyVO.setRank((Long)obj[3]);
					  partyVO.setCandidateName(obj[0].toString());
					  partyVO.setName(obj[2].toString());
					  partyVO.setVotesPercentage(obj[5].toString());
					  partyVO.setMarginPercent(obj[6].toString());
					}
					
					totalValidVotes =totalValidVotes +  (long)Double.parseDouble(obj[4].toString());
				}
				
				for(DashBoardResultsVO partyVO:resultList)
				{
					
					 partyVO.setPercent(partyVO.getCount()!= 0 ? roundTo2DigitsFloatValue(partyVO.getCount()
							 * 100f
							 /totalValidVotes) : "0.00");
				}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in  getVoteShareByConstituencyIdAndElectionId service method");
		}
		return resultList;
	}
    
    public Map<String,String> getConstituencyDetaisByRegionid(Long regionId)
    {
		LOG.debug("Entered into the getConstituencyDetaisByRegionid service method");
		Map<String,String> constituenciesMap = new LinkedHashMap<String, String>();
    	
    	try {
        	List<Long> regionIds = new ArrayList<Long>();

			if(regionId == 1L)
			{
				regionIds.add(1L);
			}else
			{
				regionIds.add(2L);
				regionIds.add(3L);
			}
				
		List<Object[]> list = 	constituencyDAO.getConstituencyDetaisByRegionid(regionIds);
		
		for(Object[] obj:list)
			constituenciesMap.put(obj[0].toString(), obj[1].toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised in  getConstituencyDetaisByRegionid service method");
		}
    	
    	return constituenciesMap;
    	
    }
    
    public List<BasicVO> gettingUserDetails(String userNameStr){
    	List<BasicVO> list = new ArrayList<BasicVO>();
    	 List<Object[]> user=null;
    	 if(userNameStr != null && userNameStr.length() > 0)
    		 user=userDAO.getUserNameDetails(userNameStr);
    	 else
    		 user=userDAO.getUserDetails();
    	
    	 try{
    		 LOG.info("entered into gettingUserDetails()");
    	 if(user !=null && user.size()>0){
    		 
    		 for (Object[] objects : user) {
    			BasicVO vo= new BasicVO();
    			 vo.setId(Long.valueOf(objects[0].toString()));//user id
    			 vo.setName(objects[1].toString());//user name
    			 vo.setMandalName(objects[2].toString());//first name
    			 vo.setHamletName(objects[3].toString());//last name
    			 
    			 list.add(vo);
			}
    	 }
    	 }
    	 catch (Exception e) {
    		 e.printStackTrace();
			LOG.error("Exception ocured in gettingUserDetails()"+e);
		}
		return list;
    }
    
    public ResultStatus updatePassword(String id,String newPassword,Long regId){
    	ResultStatus rs = new ResultStatus();
    	try	{
           User user = userDAO.get(Long.valueOf(id));
           
    	    PBKDF2 pb1 = new PBKDF2();
			String storedPwd = pb1.generatePassword(newPassword);
			String[] parts = storedPwd.split(":");
	        //int iterations = Integer.parseInt(parts[0]);
	        String passwordSalt=parts[1];
	        String passwordHash=parts[2];
			
	        user.setPasswordHash(passwordHash);
			user.setPasswordSalt(passwordSalt);
			
			//user.setPassword(newpassword);
			user.setIsPwdChanged(IConstants.TRUE);
			user.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
			user = userDAO.save(user);
			rs.setResultCode(ResultCodeMapper.SUCCESS);
    	}
    	catch (Exception e) {
    		rs.setResultCode(ResultCodeMapper.FAILURE);
			LOG.error("Exception raised in updatePassword()");
		}
    	return rs;
    }
}
