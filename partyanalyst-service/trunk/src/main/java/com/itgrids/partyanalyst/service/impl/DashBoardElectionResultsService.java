package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
import com.itgrids.partyanalyst.model.ElectionAlliance;
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
			
			
		List<Object[]> winningCandidatesList = nominationDAO.getWinningCandidatesDetailsForConstituenciesByElectionId(38L);
			
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
					 
				 }else
				 {
				    partyVO.setName(obj[4].toString());

					if(Long.parseLong(obj[5].toString()) == 1L)
						partyVO.setWinCount(Long.parseLong(obj[0].toString()));
					else
						partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
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
			
			if(resultList != null && resultList.size() >0)
				resultList.get(0).setSummaryDetails(summaryDetails);
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return resultList;
	}
	
	
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
			
			if(!types.contains(obj[6].toString()))
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
				 
			 }else
			 {
			  //  partyVO.setName(obj[4].toString());

				if(Long.parseLong(obj[5].toString()) == 1L)
					partyVO.setWinCount(Long.parseLong(obj[0].toString()));
				else
					partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
			 }

		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
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
				 
			 }else
			 {
			   // partyVO.setName(obj[4].toString());

				if(Long.parseLong(obj[5].toString()) == 1L)
					partyVO.setWinCount(Long.parseLong(obj[0].toString()));
				else
					partyVO.setLeadCount(Long.parseLong(obj[0].toString()));
			 }

		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return resultList;
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
	 
}
