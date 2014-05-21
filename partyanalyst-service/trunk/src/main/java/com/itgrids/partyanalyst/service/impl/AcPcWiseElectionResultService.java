package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateSubRegionDistrictDAO;
import com.itgrids.partyanalyst.dao.hibernate.NominationDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.service.IAcPcWiseElectionResultService;

public class AcPcWiseElectionResultService implements IAcPcWiseElectionResultService{

	
	private final static Logger LOG = Logger.getLogger(AcPcWiseElectionResultService.class);
	
	@Autowired
	
	ICandidateResultDAO candidateResultDAO;
	
	@Autowired
	
	IDelimitationConstituencyDAO delimitationConstituencyDAO;
	
	@Autowired
	
	IPartyDAO partyDAO;
	
	@Autowired
	IStateSubRegionDistrictDAO stateSubRegionDistrictDAO;
	
	@Autowired
	IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	
	@Autowired
	INominationDAO nominationDAO;
	
	public List<BasicVO> getPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope)
	{
		List<BasicVO> returnList = null;
		try 
		{
			List<Object[]> result = candidateResultDAO.getElectionResultsForSelection(electionId,stateId,partyIds,electionScopeId);
			if(result != null && result.size() > 0)
			{
				
				Map<Long,List<BasicVO>> constituencyWiseMap = new HashMap<Long, List<BasicVO>>();
				Map<Long,Long> constituencyNosMap = new HashMap<Long, Long>();
				Map<Long,String> constituencyNameMap = new HashMap<Long, String>();
				for (Object[] objects : result)
				{
					List<BasicVO> constituencyWiseList = constituencyWiseMap.get((Long)objects[0]);
					if(constituencyWiseList == null)
					{
						constituencyWiseList = new ArrayList<BasicVO>();
						constituencyWiseMap.put((Long)objects[0], constituencyWiseList);
					}
					BasicVO basicVO = new BasicVO();
					basicVO.setId((Long)objects[0]);//constituency Id
					basicVO.setName(objects[1] != null ? objects[1].toString() : "");//constituency Name
					basicVO.setMandalName(objects[2] != null ? objects[2].toString() : "");//District Name
					basicVO.setCount(objects[3] != null ?Double.valueOf(objects[3].toString()).longValue() : 0l);//gained Votes
					basicVO.setPersent(objects[4] != null ? objects[4].toString() : "0");//votes percentage
					basicVO.setLevelId((Long)objects[5]);//party Id
					basicVO.setDescription(objects[6] != null ? objects[6].toString() : "");//party Name
					basicVO.setCasteName(objects[7] != null ? objects[7].toString() : "");//candidate
					basicVO.setLevelValue((Long)objects[8]);//rank
					constituencyWiseList.add(basicVO);
				}
				List<Object[]> constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(stateId,2009l,electionScopeId,scope);
				if(constituencyDetails != null && constituencyDetails.size() > 0)
				{
					for (Object[] objects : constituencyDetails)
					{
						constituencyNosMap.put((Long)objects[0], (Long)objects[1]);
						constituencyNameMap.put((Long)objects[0], objects[2].toString());
					}
				}
				List<Object[]> parties = partyDAO.getPartyShortNameByIds(partyIds);
				
				List<Long> constituenctyIds = new ArrayList<Long>(constituencyNosMap.keySet());
				if(constituenctyIds != null && constituenctyIds.size() > 0)
				{
					returnList = new ArrayList<BasicVO>();
					for (Long constituencyId : constituenctyIds)
					{
						BasicVO VO = new BasicVO();
						VO.setId(constituencyId);
						VO.setHamletId(constituencyNosMap.get(constituencyId));
						VO.setName(constituencyNameMap.get(constituencyId));
						List<BasicVO> constiwiseList = constituencyWiseMap.get(constituencyId);
						if(constiwiseList != null && constiwiseList.size() > 0)
						{
							List<BasicVO> partiesList = new ArrayList<BasicVO>();
							/*if(parties != null && parties.size() > 0)
							{
								for (Object[] objects : parties)
								{
									BasicVO partyVO = new BasicVO();
									partyVO.setId((Long)objects[0]);
									partyVO.setName(objects[1].toString());
									partyVO.setCount(0l);
									partyVO.setPerc(0.0);
									partiesList.add(partyVO);
								}
							}*/
							for (BasicVO subVO : constiwiseList)
							{
								VO.setMandalName(subVO.getMandalName());
								try {
									BasicVO partyVO = new BasicVO();
									partyVO.setId(subVO.getLevelId());
									partyVO.setName(subVO.getDescription());
									partyVO.setCount(subVO.getCount());
									partyVO.setPersent(subVO.getPersent());
									partyVO.setCasteName(subVO.getCasteName());
									
									partiesList.add(partyVO);
								} catch (Exception e) {}
								
								
								/*for(BasicVO partyVO : partiesList)
								{
									if(subVO.getLevelValue().longValue() == 1l)
									{
										partyVO.setLevelValue(1l);
									}
									if(partyVO.getId().longValue() == subVO.getLevelId().longValue())
									{
										partyVO.setCount(subVO.getCount());
										partyVO.setPersent(subVO.getPersent());
										partyVO.setCasteName(subVO.getCasteName());
									}
								}*/
							}
							Collections.sort(partiesList, new Comparator<BasicVO>() {

								public int compare(BasicVO o1, BasicVO o2) {									
									return o2.getCount().compareTo(o1.getCount());
								}
							});
							
							VO.setSelectedCasteDetails(partiesList);
						}
						returnList.add(VO);
					}
				}
						
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<GenericVO> cbnOrModiEffect(Long electionId,Long stateid,List<Long> partyId,Long electionScopeId)
	{
		List<GenericVO> returnList = null;
		try
		{
			List<Object[]> result = candidateResultDAO.getElectionResultsForCBNORMODIEffect(electionId,stateid,partyId,electionScopeId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Object[] objects : result) 
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId((Long)objects[0]);
					genericVO.setName(objects[1].toString());
					returnList.add(genericVO);
				}
			}
		} 
		catch (Exception e)
		{
			LOG.error("Exception Raised In modiEffect", e);
		}
		return returnList;
	}
	
	
	public List<BasicVO> filterToGetPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope,List<Long> subRegionId)
	{
		List<BasicVO> returnList = null;
		try 
		{
			
			List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(subRegionId);
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
					constiIds.clear();
					for (Object[] constituency : parliaments) {
						if(!constiIds.contains((Long)constituency[0]))
						constiIds.add((Long)constituency[0]);
					}					
				}
			}
			
			//List<Object[]> result = candidateResultDAO.filterToGetElectionResultsForSelection(electionId,partyIds,electionScopeId,constiIds);
			
			List<Object[]> result = candidateResultDAO.filterToGetElectionResultsForSelection(electionId,partyIds,electionScopeId,constiIds);
			if(result != null && result.size() > 0)
			{
				
				Map<Long,List<BasicVO>> constituencyWiseMap = new HashMap<Long, List<BasicVO>>();
				Map<Long,Long> constituencyNosMap = new HashMap<Long, Long>();
				Map<Long,String> constituencyNameMap = new HashMap<Long, String>();
				for (Object[] objects : result)
				{
					List<BasicVO> constituencyWiseList = constituencyWiseMap.get((Long)objects[0]);
					if(constituencyWiseList == null)
					{
						constituencyWiseList = new ArrayList<BasicVO>();
						constituencyWiseMap.put((Long)objects[0], constituencyWiseList);
					}
					BasicVO basicVO = new BasicVO();
					basicVO.setId((Long)objects[0]);//constituency Id
					basicVO.setName(objects[1] != null ? objects[1].toString() : "");//constituency Name
					basicVO.setMandalName(objects[2] != null ? objects[2].toString() : "");//District Name
					basicVO.setCount(objects[3] != null ?Double.valueOf(objects[3].toString()).longValue() : 0l);//gained Votes
					basicVO.setPersent(objects[4] != null ? objects[4].toString() : "0");//votes percentage
					basicVO.setLevelId((Long)objects[5]);//party Id
					basicVO.setDescription(objects[6] != null ? objects[6].toString() : "");//party Name
					basicVO.setCasteName(objects[7] != null ? objects[7].toString() : "");//candidate
					basicVO.setLevelValue((Long)objects[8]);//rank
					constituencyWiseList.add(basicVO);
				}
				//List<Object[]> constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(stateId,2009l,electionScopeId,scope);
				List<Object[]> constituencyDetails  = null;
				
				if(electionScopeId.longValue() == 1L){
					constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(stateId,2009l,electionScopeId,scope);
				}
				else {
					constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByConstituency(constiIds,2009l,electionScopeId,scope);
				}
				
				
				if(constituencyDetails != null && constituencyDetails.size() > 0)
				{
					for (Object[] objects : constituencyDetails)
					{
						constituencyNosMap.put((Long)objects[0], (Long)objects[1]);
						constituencyNameMap.put((Long)objects[0], objects[2].toString());
					}
				}
				List<Object[]> parties = partyDAO.getPartyShortNameByIds(partyIds);
				
				List<Long> constituenctyIds = new ArrayList<Long>(constituencyNosMap.keySet());
				if(constituenctyIds != null && constituenctyIds.size() > 0)
				{
					returnList = new ArrayList<BasicVO>();
					for (Long constituencyId : constituenctyIds)
					{
						BasicVO VO = new BasicVO();
						VO.setId(constituencyId);
						VO.setHamletId(constituencyNosMap.get(constituencyId));
						VO.setName(constituencyNameMap.get(constituencyId));
						List<BasicVO> constiwiseList = constituencyWiseMap.get(constituencyId);
						if(constiwiseList != null && constiwiseList.size() > 0)
						{
							List<BasicVO> partiesList = new ArrayList<BasicVO>();
							/*if(parties != null && parties.size() > 0)
							{
								for (Object[] objects : parties)
								{
									BasicVO partyVO = new BasicVO();
									partyVO.setId((Long)objects[0]);
									partyVO.setName(objects[1].toString());
									partyVO.setCount(0l);
									partyVO.setPerc(0.0);
									partiesList.add(partyVO);
								}
							}*/
							for (BasicVO subVO : constiwiseList)
							{
								VO.setMandalName(subVO.getMandalName());
								try {
									BasicVO partyVO = new BasicVO();
									partyVO.setId(subVO.getLevelId());
									partyVO.setName(subVO.getDescription());
									partyVO.setCount(subVO.getCount());
									partyVO.setPersent(subVO.getPersent());
									partyVO.setCasteName(subVO.getCasteName());
									
									partiesList.add(partyVO);
								} catch (Exception e) {}
								
								
								/*for(BasicVO partyVO : partiesList)
								{
									if(subVO.getLevelValue().longValue() == 1l)
									{
										partyVO.setLevelValue(1l);
									}
									if(partyVO.getId().longValue() == subVO.getLevelId().longValue())
									{
										partyVO.setCount(subVO.getCount());
										partyVO.setPersent(subVO.getPersent());
										partyVO.setCasteName(subVO.getCasteName());
									}
								}*/
							}
							Collections.sort(partiesList, new Comparator<BasicVO>() {

								public int compare(BasicVO o1, BasicVO o2) {									
									return o2.getCount().compareTo(o1.getCount());
								}
							});
							
							VO.setSelectedCasteDetails(partiesList);
						}
						returnList.add(VO);
					}
				}					
				
			}
			Collections.sort(returnList, new Comparator<BasicVO>() {
				public int compare(BasicVO o1, BasicVO o2) {									
					return o1.getName().compareTo(o2.getName());
				}
			});
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
	
	public List<BasicVO> searchPartyWiseComparissionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope,List<Long> subRegionId,String searchName)
	{
		List<BasicVO> returnList = null;
		try 
		{
			
			List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(subRegionId);
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
					constiIds.clear();
					for (Object[] constituency : parliaments) {
						if(!constiIds.contains((Long)constituency[0]))
						constiIds.add((Long)constituency[0]);
					}					
				}
			}
			
			List<Object[]> result = candidateResultDAO.searchElectionResultsByConstituencyName(electionId,partyIds,electionScopeId,constiIds,searchName);
			if(result != null && result.size() > 0)
			{
				
				Map<Long,List<BasicVO>> constituencyWiseMap = new HashMap<Long, List<BasicVO>>();
				Map<Long,Long> constituencyNosMap = new HashMap<Long, Long>();
				Map<Long,String> constituencyNameMap = new HashMap<Long, String>();
				for (Object[] objects : result)
				{
					List<BasicVO> constituencyWiseList = constituencyWiseMap.get((Long)objects[0]);
					if(constituencyWiseList == null)
					{
						constituencyWiseList = new ArrayList<BasicVO>();
						constituencyWiseMap.put((Long)objects[0], constituencyWiseList);
					}
					BasicVO basicVO = new BasicVO();
					basicVO.setId((Long)objects[0]);//constituency Id
					basicVO.setName(objects[1] != null ? objects[1].toString() : "");//constituency Name
					basicVO.setMandalName(objects[2] != null ? objects[2].toString() : "");//District Name
					basicVO.setCount(objects[3] != null ?Double.valueOf(objects[3].toString()).longValue() : 0l);//gained Votes
					basicVO.setPersent(objects[4] != null ? objects[4].toString() : "0");//votes percentage
					basicVO.setLevelId((Long)objects[5]);//party Id
					basicVO.setDescription(objects[6] != null ? objects[6].toString() : "");//party Name
					basicVO.setCasteName(objects[7] != null ? objects[7].toString() : "");//candidate
					basicVO.setLevelValue((Long)objects[8]);//rank
					constituencyWiseList.add(basicVO);
				}
				//List<Object[]> constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(stateId,2009l,electionScopeId,scope);
				List<Object[]> constituencyDetails  = null;
				if(electionScopeId.longValue() == 1L){
					constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(stateId,2009l,electionScopeId,scope);
				}
				else {
					constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByConstituency(constiIds,2009l,electionScopeId,scope);
				}
				
				
				if(constituencyDetails != null && constituencyDetails.size() > 0)
				{
					for (Object[] objects : constituencyDetails)
					{
						constituencyNosMap.put((Long)objects[0], (Long)objects[1]);
						constituencyNameMap.put((Long)objects[0], objects[2].toString());
					}
				}
				List<Object[]> parties = partyDAO.getPartyShortNameByIds(partyIds);
				
				List<Long> constituenctyIds = new ArrayList<Long>(constituencyNosMap.keySet());
				if(constituenctyIds != null && constituenctyIds.size() > 0)
				{
					returnList = new ArrayList<BasicVO>();
					for (Long constituencyId : constituenctyIds)
					{
						BasicVO VO = new BasicVO();
						VO.setId(constituencyId);
						VO.setHamletId(constituencyNosMap.get(constituencyId));
						VO.setName(constituencyNameMap.get(constituencyId));
						List<BasicVO> constiwiseList = constituencyWiseMap.get(constituencyId);
						if(constiwiseList != null && constiwiseList.size() > 0)
						{
							List<BasicVO> partiesList = new ArrayList<BasicVO>();
							/*if(parties != null && parties.size() > 0)
							{
								for (Object[] objects : parties)
								{
									BasicVO partyVO = new BasicVO();
									partyVO.setId((Long)objects[0]);
									partyVO.setName(objects[1].toString());
									partyVO.setCount(0l);
									partyVO.setPerc(0.0);
									partiesList.add(partyVO);
								}
							}*/
							for (BasicVO subVO : constiwiseList)
							{
								VO.setMandalName(subVO.getMandalName());
								try {
									BasicVO partyVO = new BasicVO();
									partyVO.setId(subVO.getLevelId());
									partyVO.setName(subVO.getDescription());
									partyVO.setCount(subVO.getCount());
									partyVO.setPersent(subVO.getPersent());
									partyVO.setCasteName(subVO.getCasteName());
									
									partiesList.add(partyVO);
								} catch (Exception e) {}
								
								
								/*for(BasicVO partyVO : partiesList)
								{
									if(subVO.getLevelValue().longValue() == 1l)
									{
										partyVO.setLevelValue(1l);
									}
									if(partyVO.getId().longValue() == subVO.getLevelId().longValue())
									{
										partyVO.setCount(subVO.getCount());
										partyVO.setPersent(subVO.getPersent());
										partyVO.setCasteName(subVO.getCasteName());
									}
								}*/
							}
							Collections.sort(partiesList, new Comparator<BasicVO>() {

								public int compare(BasicVO o1, BasicVO o2) {									
									return o2.getCount().compareTo(o1.getCount());
								}
							});
							
							VO.setSelectedCasteDetails(partiesList);
						}
						returnList.add(VO);
					}
				}
						
				if(returnList != null && returnList.size()>0){
					Collections.sort(returnList, new Comparator<BasicVO>() {
						public int compare(BasicVO o1, BasicVO o2) {									
							return o1.getName().compareTo(o2.getName());
						}
					});
				}
				
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return returnList;
	}
}

