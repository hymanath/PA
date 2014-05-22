package com.itgrids.partyanalyst.service.impl;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.service.IDashBoardElectionResultsService;

public class DashBoardElectionResultsService implements
		IDashBoardElectionResultsService {
	
	private static final Logger LOG = Logger.getLogger(DashBoardElectionResultsService.class);

	
	
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
	private IConstituencyElectionDAO constituencyElectionDAO;
	
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
	 
	 
	 public  List<DashBoardResultsVO> getWonAndLeadCountPartyWise(Long electionId,List<Long> locationIds,Long scopeId)
	 {
		 List<DashBoardResultsVO> resultList = new ArrayList<DashBoardResultsVO>();
		 try
		 {
			 List<Object[]> list = nominationDAO.getWonAndLeadCountPartyWise(electionId, locationIds, scopeId);
			 
			 
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
				partyMap.put(1117L,"YCP");
				partyMap.put(886L, "TRS");
				partyMap.put(163L, "BJP");
				partyMap.put(662L, "PRP");
				partyMap.put(9999L,"OTH"); //for others we considered id as 9999
				
				
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
					DashBoardResultsVO locationVO =  getMacthedVO(resultList,Long.parseLong(obj[1].toString()));
					locationVO.setName(obj[2].toString());
					
					DashBoardResultsVO partyVO = null;
					if(partyIds.contains(Long.parseLong(obj[3].toString())))
					 partyVO =  getMacthedVO(locationVO.getPartiesDetails(),Long.parseLong(obj[3].toString()));
					
					
					 if(partyVO == null)
					 {
						 partyVO = getMacthedVO(locationVO.getPartiesDetails(),9999L);
						 partyVO.setName("OTH");
						 
						 if(Long.parseLong(obj[5].toString()) == 1L)
								partyVO.setWinCount(partyVO.getWinCount() + Long.parseLong(obj[0].toString()));
						else 
								partyVO.setLeadCount(partyVO.getLeadCount() + Long.parseLong(obj[0].toString()));
						 
					 }else
					 {
					    partyVO.setName(obj[4].toString());

						if(Long.parseLong(obj[5].toString()) == 1L)
							partyVO.setWinCount(Long.parseLong(obj[0].toString()));
						else
							partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
					 }

					/*if(Long.parseLong(obj[5].toString()) == 1L)
						partyVO.setWinCount(Long.parseLong(obj[0].toString()));
					else
						partyVO.setLeadCount(Long.parseLong(obj[0].toString()));*/
					
				}
				
				
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 
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
    
	public List<DashBoardResultsVO> getPartyWiseWinningSeatsCount(
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
    			
    			constituencyVO.getSubList().add(partyVO);
    		}
    		
    		 partyCountBefore = getPartyWiseWinningCount(resultList);
    		
    		caluculateValidVotesForConstituency(resultList);//this method calculates all the valid votes of a constituency
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
    		}
    		
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    		LOG.error("Exception raised in getConstituencyResultDetailsByElectionId service method");
    	}
    	return resultList;
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
}
