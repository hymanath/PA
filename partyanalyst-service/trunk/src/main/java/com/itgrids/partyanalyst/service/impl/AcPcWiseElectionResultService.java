package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstiCasteGroupPercDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateSubRegionDistrictDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CasteWiseResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAcPcWiseElectionResultService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	@Autowired IAllianceGroupDAO allianceGroupDAO;
	@Autowired IConstiCasteGroupPercDAO constiCasteGroupPercDAO;
	
	public List<BasicVO> getPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope)
	{
		List<BasicVO> returnList = null;
		try 
		{
			List<Object[]> alliancesList = allianceGroupDAO.getAlliancesAndPartiesForAnElection(IConstants.PRES_PARLIAMENT_ELECTION_ID);
			
			Map<String,List<Long>> alliancesMap = new HashMap<String, List<Long>>();
			if(alliancesList!=null && alliancesList.size()>0){
			for(Object[] obj:alliancesList){
				List<Long> alliances = alliancesMap.get(obj[1].toString());
				if(alliances == null){
					alliances = new ArrayList<Long>();
				}
				
				alliances.add(Long.valueOf(obj[2].toString()));
				
				alliancesMap.put(obj[1].toString(), alliances);
			}
			}
			
			List<Long> upaAlliances = null;
			List<Long> ndaAlliances = null;
			
			if(alliancesMap.get("UPA") == null){
				upaAlliances = new ArrayList<Long>();
			}else{
				upaAlliances = alliancesMap.get("UPA");
			}
			
			if(alliancesMap.get("NDA") == null){
				ndaAlliances = new ArrayList<Long>();
			}else{
				ndaAlliances = alliancesMap.get("NDA");
			}
			
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
					
					if(upaAlliances.contains((Long)objects[5])){
						basicVO.setAliancedWith("UPA");
					}else if(ndaAlliances.contains((Long)objects[5])){
						basicVO.setAliancedWith("NDA");
					}else{
						basicVO.setAliancedWith("OTHERS");
					}
					
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
									if(subVO.getAliancedWith()!=null){
										partyVO.setAliancedWith(subVO.getAliancedWith());
									}
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
	
	public List<SelectOptionVO> cbnOrModiEffect(Long electionId,Long stateid,List<Long> partyId,Long electionScopeId)
	{
		List<SelectOptionVO> returnList = null;
		Long effect = 0l;
		try
		{
			List<Object[]> result = candidateResultDAO.getElectionResultsForCBNORMODIEffect(electionId,stateid,partyId,electionScopeId);
			if(result != null && result.size() > 0)
			{
				returnList = new ArrayList<SelectOptionVO>();
				for (Object[] objects : result) 
				{
					SelectOptionVO genericVO = new SelectOptionVO();
					genericVO.setId((Long)objects[0]);
					genericVO.setName(objects[1].toString());
					returnList.add(genericVO);
					if(electionScopeId.longValue() == 2l && objects[2] !=null && ((Long)objects[2]).longValue()  > 10){
						effect=effect+1;
					}
				}
			}
			if(returnList != null && returnList.size() > 0){
				returnList.get(0).setTotalCount(effect);
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
		List<BasicVO> returnList = new ArrayList<BasicVO>();
		BasicVO returnVO = new BasicVO();
		
		try 
		{
			List<BasicVO> consituencyList = new ArrayList<BasicVO>();
			List<BasicVO> returnList1 = null;
			
			List<Object[]> constiList =  stateSubRegionDistrictDAO.getAssemblyConstituenciesBySubRegionIds(subRegionId);
			List<Long> constiIds = null;	 
			if(constiList != null && constiList.size()>0){
				constiIds = new ArrayList<Long>();
				for (Object[] constituency : constiList) {
					constiIds.add((Long)constituency[0]);
					
					BasicVO vo = new BasicVO();
					vo.setId((Long)constituency[0]);
					vo.setName(constituency[1].toString());
					consituencyList.add(vo);
				}
				
			}
			List<Object[]> parliaments = null ;
			if(electionScopeId.longValue() == 1L){
				parliaments = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(constiIds);
				if(parliaments != null && parliaments.size()>0){
					
					constiIds.clear();
					consituencyList.clear();
					
					for (Object[] constituency : parliaments) {
						if(!constiIds.contains((Long)constituency[0])){
						constiIds.add((Long)constituency[0]);
						
						BasicVO vo = new BasicVO();
						vo.setId((Long)constituency[0]);
						vo.setName(constituency[2].toString());
						consituencyList.add(vo);
						}
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
					returnList1 = new ArrayList<BasicVO>();
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
						returnList1.add(VO);
					}
				}					
				
			}
			Collections.sort(returnList1, new Comparator<BasicVO>() {
				public int compare(BasicVO o1, BasicVO o2) {									
					return o1.getName().compareTo(o2.getName());
				}
			});
			
			returnVO.setHamletCasteInfo(consituencyList);
			returnVO.setHamletVoterInfo(returnList1);
			
			returnList.add(returnVO);
			
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

public List<CasteWiseResultVO> getCasteWiseDataForElection(Long electionId)
{

List<CasteWiseResultVO>  resultList = new ArrayList<CasteWiseResultVO>();
try{
	
	List<Object[]> list = nominationDAO.getCandidateCasteResult(electionId);
	Set<Long> constituencyIds = new java.util.HashSet<Long>();
	if(list != null && list.size() > 0)
	{
	
		for(Object[] params : list)
		{
			CasteWiseResultVO mainVo = new CasteWiseResultVO();
			if(constituencyIds.add((Long)params[0]))
			{
			mainVo.setId((Long)params[0]);
			mainVo.setName(params[1].toString());
			resultList.add(mainVo);
			}
		}
		//List<CasteWiseResultVO> candidatesList = new ArrayList<CasteWiseResultVO>();
		for(Object[] params : list)
		{
			CasteWiseResultVO constituency = getConstituencyVo(resultList,(Long)params[0]);
			if(constituency != null && constituency.getCandidateList().size() < 3)
			{
				CasteWiseResultVO candidateVo = new CasteWiseResultVO();
				candidateVo.setId((Long)params[2]);
				candidateVo.setName(params[3] != null ? params[3].toString() : "" +" "+ params[4] != null ? params[4].toString() : "");
				
				candidateVo.setPartyId((Long)params[5]);
				candidateVo.setParty(params[6].toString());
				if((Long)params[7] == 1)
				{
				candidateVo.setStatus("WINNER");
				}
				else if((Long)params[7] != 1)
				{
					candidateVo.setStatus("RUNNER - "+((Long)params[7] - 1));	
				}
				if(params[8] != null)
				{
				Double votes = new Double(params[8].toString());
				candidateVo.setVotes(votes.longValue());
				}
				constituency.getCandidateList().add(candidateVo);
			}
		}
		
	}
	
	List<Object[]> list1 = constiCasteGroupPercDAO.getConstituencyCastePer();
	if(list1 != null && list1.size() > 0)
	{
		for(Object[] params : list1)
		{
			CasteWiseResultVO constituency = getConstituencyVo(resultList,(Long)params[0]);
			if(constituency != null && constituency.getCasteList().size() < 5)
			{
				SelectOptionVO castVo = new SelectOptionVO();
				castVo.setId((Long)params[1]);
				castVo.setName(params[2] != null ? params[2].toString() : "");
				Double votesPerc = new Double(params[3].toString());
				String percentage = new BigDecimal(votesPerc)
					.setScale(2,BigDecimal.ROUND_HALF_UP).toString();	
				castVo.setPercentage(percentage !=null ? percentage :"0.0");
			
				constituency.getCasteList().add(castVo);
			}
		}
	}
	
}
catch (Exception e) {
	e.printStackTrace();
}
return resultList;
}

public CasteWiseResultVO getConstituencyVo(List<CasteWiseResultVO> resultList,Long constituencyId)
{

try{
	if(resultList == null || resultList.size() == 0)
	 return null;
	for(CasteWiseResultVO vo : resultList)
	 if(vo.getId().longValue() == constituencyId)
	  return vo;
	 
   return null;
 }catch (Exception e) {
  e.printStackTrace();
  LOG.error(" Exception Occured in checkPartyPositionVOExist() method, Exception - "+e);
  return null;
 }

}

	public SelectOptionVO getTopCastesInALocation(Long electionId,Long constituencyId){
		SelectOptionVO returnVo = new SelectOptionVO();
		
		List<SelectOptionVO> castesList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> resultList = new ArrayList<SelectOptionVO>();
		
		returnVo.setSelectOptionsList(castesList);
		returnVo.setSelectOptionsList1(resultList);
		
		SelectOptionVO vo = null;
		
		//0 casteGroupId, 1 casteGroupName,2 groupPerc,3 rank
		List<Object[]> castesResults = constiCasteGroupPercDAO.getConstituencyCastePerByConstiId(constituencyId);
		for(Object[] caste:castesResults){
			vo = new SelectOptionVO();
			vo.setName(caste[1].toString());
			vo.setPerc((Double)caste[2]);
			vo.setOrderId((Long)caste[3]);
			castesList.add(vo);
		}
		List<Long> constituencyIds = new ArrayList<Long>(); 
		constituencyIds.add(constituencyId);
		nominationDAO.getConstituencyWiseResults1(electionId, constituencyIds);
		return returnVo;
	}

}

