package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.DashBoardResultsVO;
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
				
				locationVO.getReservationDetails().add(rural);
				locationVO.getReservationDetails().add(urban);
				locationVO.getReservationDetails().add(ruralUrban);
				
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
			partyMap.put(9999L, "OTHERS");
			
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
			 
			 
			 DashBoardResultsVO constnTypeVO =  getMacthedVoByName(locationVO.getReservationDetails(),obj[9].toString());
			 
			 
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
	
	
	public List<Object[]> getConstituenciesDetailsForSubReport(String type,Long partyId)
	{
		LOG.debug("Entered into the getConstituenciesDetailsForSubReport service method");
		List<Object[]> list = null;

		try
		{
			
			if(type.equalsIgnoreCase("RURAL") || type.equalsIgnoreCase("URBAN") ||type.equalsIgnoreCase("RURAL-URBAN"))
			{
				list = nominationDAO.getConstituencyDetailsByConstituencyType(type,partyId,38L);
			}else
			{
				list = nominationDAO.getConstituencyDetailsByReservationType(type,partyId,38L);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			LOG.error("Exception raised in getConstituenciesDetailsForSubReport service method");

		}
		return list;
	}
}
