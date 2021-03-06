package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IConstiCasteGroupPercDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.IStateSubRegionDistrictDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CasteWiseResultVO;
import com.itgrids.partyanalyst.dto.GenericVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAcPcWiseElectionResultService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.webservice.client.WebServiceClient;

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
	
	
	private WebServiceClient webServiceClient;
	
	public WebServiceClient getWebServiceClient() {
		return webServiceClient;
	}

	public void setWebServiceClient(WebServiceClient webServiceClient) {
		this.webServiceClient = webServiceClient;
	}
	@Autowired 	private IVoterAgeInfoDAO voterAgeInfoDAO;
    
	@Autowired 	private IPartyTrendsDAO partyTrendsDAO;

	
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
	
	public List<GenericVO> cbnEffectCalucation()
	{
		List<GenericVO> returnList = null;
		
		try
		{
			
			//Map<Long,GenericVO> mapFor2009 = null;
			Map<Long,GenericVO> mapFor2009Win = null;
			Map<Long,GenericVO> mapFor2009Loss = null;
			
			//Map<Long,GenericVO> mapFor2012 = null;
			Map<Long,GenericVO> mapFor2014Win = null;
			Map<Long,GenericVO> mapFor2014Loss = null;
			
			List<Object[]> resultFor2009Election = candidateResultDAO.getElectionResultForCbnEffect(38l, 872l);
			System.out.println(resultFor2009Election.size());
			if(resultFor2009Election != null && resultFor2009Election.size() > 0 )
			{
				 //mapFor2009 = new HashMap<Long, GenericVO>();
				 mapFor2009Win = new HashMap<Long, GenericVO>();
				 mapFor2009Loss = new HashMap<Long, GenericVO>();
				for (Object[] objects : resultFor2009Election)
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId((Long)objects[0]);
					genericVO.setPerc(Double.valueOf(objects[1].toString()));
					genericVO.setRank((Long)objects[2]);
					if((Long)objects[2] == 1l)
					{
						mapFor2009Win.put((Long)objects[0], genericVO);
					}
					else
					{
						mapFor2009Loss.put((Long)objects[0], genericVO);
					}
					//mapFor2009.put((Long)objects[0], genericVO);
				}
			}
			List<Object[]> resultFor2012Election = candidateResultDAO.getElectionResultForCbnEffect(258l, 872l);
			System.out.println(resultFor2012Election.size());
			if(resultFor2012Election != null && resultFor2012Election.size() > 0 )
			{
				 //mapFor2012 = new HashMap<Long, GenericVO>();
				 mapFor2014Win = new HashMap<Long, GenericVO>();
				 mapFor2014Loss = new HashMap<Long, GenericVO>();
				for (Object[] objects : resultFor2012Election)
				{
					GenericVO genericVO = new GenericVO();
					genericVO.setId((Long)objects[0]);
					genericVO.setPerc(Double.valueOf(objects[1].toString()));
					genericVO.setRank((Long)objects[2]);
					if((Long)objects[2] == 1l)
					{
						mapFor2014Win.put((Long)objects[0], genericVO);
					}
					else
					{
						mapFor2014Loss.put((Long)objects[0], genericVO);
					}
					//mapFor2012.put((Long)objects[0], genericVO);
				}
			}
			Map<Long,String> constiMap = new HashMap<Long, String>();
		
			
			//1) 2009 LOST 			2014 LOST
			
			List<Long> firstRuleConstituencyes = new ArrayList<Long>(mapFor2009Loss.keySet());
			firstRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2014Loss.keySet()));
			
			Set<Long> firstIds = findDuplicates(firstRuleConstituencyes);
			if(firstIds != null && firstIds.size() > 0)
			{
				for (Long constituencyId : firstIds)
				{
					Double avilPerc = mapFor2014Loss.get(constituencyId).getPerc().doubleValue() - mapFor2009Loss.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "CBN EFFECT");
					}
					else if (avilPerc == 0)
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					else
					{
						constiMap.put(constituencyId, "NO CBN EFFECT");
					}
				}
			}
			
			// 2) 2009 LOST 				2014 WON
			List<Long> secondRuleConstituencyes = new ArrayList<Long>(mapFor2009Loss.keySet());
			secondRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2014Win.keySet()));
			
			Set<Long> secondIds = findDuplicates(secondRuleConstituencyes);
			if(secondIds != null && secondIds.size() > 0)
			{
				for (Long constituencyId : secondIds)
				{
					Double avilPerc = mapFor2014Win.get(constituencyId).getPerc().doubleValue() - mapFor2009Loss.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "CBN EFFECT");
					}
					else
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
				}
			}
			
			//3)2009 WON 				2014 LOST
			List<Long> thirdRuleConstituencyes = new ArrayList<Long>(mapFor2009Win.keySet());
			thirdRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2014Loss.keySet()));
			Set<Long> thirdIds = findDuplicates(thirdRuleConstituencyes);
			if(thirdIds != null && thirdIds.size() > 0)
			{
				for (Long constituencyId : thirdIds)
				{
					Double avilPerc = mapFor2014Loss.get(constituencyId).getPerc().doubleValue() - mapFor2009Win.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					else if (avilPerc == 0)
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					else
					{
						constiMap.put(constituencyId, "NO CBN EFFECT");
					}
				}
			}
			
			//2009 WON 				2014 WON
			List<Long> fourthRuleConstituencyes = new ArrayList<Long>(mapFor2009Win.keySet());
			fourthRuleConstituencyes.addAll(new ArrayList<Long>(mapFor2014Win.keySet()));
			Set<Long> fourthIds = findDuplicates(fourthRuleConstituencyes);	
			if(fourthIds != null && fourthIds.size() > 0)
			{
				for (Long constituencyId : fourthIds)
				{
					Double avilPerc = mapFor2014Win.get(constituencyId).getPerc().doubleValue() - mapFor2009Win.get(constituencyId).getPerc().doubleValue();
					if(avilPerc > 0.0)
					{
						constiMap.put(constituencyId, "CBN EFFECT");
					}
					else 
					{
						constiMap.put(constituencyId, "NEUTRAL");
					}
					
				}
			}
			
			List<Long> constituencyes = new ArrayList<Long>(constiMap.keySet());
			List<Object[]> constituencyDetails = delimitationConstituencyDAO.getConstituencyNoByState(1l,2009l,2l,"ac");
			Map<Long,Long> constituencyNosMap = null;
			Map<Long,String> constituencyNameMap = null;
			if(constituencyDetails != null && constituencyDetails.size() > 0)
			{
				constituencyNosMap = new HashMap<Long, Long>();
				constituencyNameMap = new HashMap<Long, String>();
				for (Object[] objects : constituencyDetails)
				{
					constituencyNosMap.put((Long)objects[0], (Long)objects[1]);
					constituencyNameMap.put((Long)objects[0], objects[2].toString());
				}
			}
			if(constituencyes != null && constituencyes.size() > 0)
			{
				returnList = new ArrayList<GenericVO>();
				for (Long constituency : constituencyes)
				{
					GenericVO VO = new GenericVO();
					VO.setId(constituency);
					VO.setName(constituencyNameMap.get(constituency));
					VO.setRank(constituencyNosMap.get(constituency));
					VO.setDesc(constiMap.get(constituency));
					returnList.add(VO);
				}
			}
			
		}
		
		
		catch (Exception e)
		{
			LOG.error("Exception Raised In cbnEffectCalucation", e);
		}
		return returnList;
	}
	
	
	public Set<Long> findDuplicates(List<Long> listContainingDuplicates)
	{ 
	  final Set<Long> setToReturn = new HashSet<Long>(); 
	  final Set<Long> set1 = new HashSet<Long>(); 

	  for (Long yourInt : listContainingDuplicates)
	  {
	   if (!set1.add(yourInt))
	   {
	    setToReturn.add(yourInt);
	   }
	  }
	  return setToReturn;
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
	
	public List<com.itgrids.survey.soa.endpoints.GenericVO> getGenderWiseSurveyReport(Long partyId,Long constituencyId,List<Long> surveyIds)
	{
		List<com.itgrids.survey.soa.endpoints.GenericVO> returnList = null;
		try 
		{
			returnList = webServiceClient.buildGenderWiseDetails(partyId, constituencyId, surveyIds);
			try {
			setGendersTables(returnList, constituencyId);
			}catch (Exception e) {
				 LOG.error(" Exception Occured in getGenderWiseSurveyReport() method setting table dta, Exception - ",e);
			}
		} 
		catch (Exception e)
		{
			 LOG.error(" Exception Occured in getGenderWiseSurveyReport() method, Exception - ",e);
		}
		
		return returnList;	//List<Object[]> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoListByconstituency(181L, 10l);

		
	}
	
	// get total votes ,votes pollled , male count,femalecount,young voter count and elder voters count 
	
	//
	public void setGendersTables(List<com.itgrids.survey.soa.endpoints.GenericVO> vo,Long constId)
	{
		
			  Long totalVoters=0L;     
			  Long youngVOters=0L;
			  Long yeldervoters=0L;
			  Long middleageMaleVoters=0L;
			  Long middleAgeFemaleVoters=0L;
			 
		       Long totalVotersCumm=0L;     
			  Long youngVOtersCumm=0L;
			  Long yeldervotersCumm=0L;
			  Long middleageMaleVotersCumm=0L;
			  Long middleAgeFemaleVotersCumm=0L;
			  
			  
			//constitituency voter details
				
				
				
				//get totalvotes and votes polled
				List<Object[]> objs=partyTrendsDAO.getVotesPolledAndTotalVotesForConst(258L, 1L, constId);
				
				List<Object[]> voterAgeInfoList = voterAgeInfoDAO.getVoterAgeInfoListByconstituency(constId, 10l);
				List<Object[]>  middleagegenders =partyTrendsDAO.getGendercountBetweenAgeGroup(constId, 10l, 23, 60);
				
				
				for (Object[] objects : voterAgeInfoList) {
					 long ageId=Long.valueOf(objects[0].toString());
				     long longAgeValue=Long.valueOf(objects[2].toString());
				/*	
				     if(ageId==1L ||ageId==6L)
					total=total+longAgeValue;*/
					
					if(ageId==1L)
						youngVOters=longAgeValue;
					if(ageId==6L)
						yeldervoters=longAgeValue;
				}
		      for (Object[] objects : middleagegenders) {
		    	  
		    	  System.out.println(objects[1]+"==="+objects[0]);
		    	  if(objects[0].toString().equalsIgnoreCase("M"))
		    		  middleageMaleVoters=Long.valueOf(objects[1].toString());
		    	  else
		    		  middleAgeFemaleVoters=Long.valueOf(objects[1].toString());
				
			}
				//System.out.println(total);
				System.out.println(objs.get(0)[2]+"==="+objs.get(0)[1]);
				System.out.println(middleageMaleVoters+"=="+middleAgeFemaleVoters+"="+youngVOters+"=="+yeldervoters);
				long total=middleageMaleVoters+middleAgeFemaleVoters+youngVOters+yeldervoters;
				
/*				//124","138","150","158"
				ArrayList<Long> al= new ArrayList<Long>();
				al.add(124L);
				al.add(138L);
				al.add(150L);
				al.add(158L);
				//set these data to genricvo
				List<GenericVO>  vo = new WebServiceClient().buildGenderWiseDetails(872L, 232L, al);
				System.out.println(vo.size());*/
				
				    for (com.itgrids.survey.soa.endpoints.GenericVO genericVO : vo) {
				    	
					     System.out.println(genericVO);
					  
					    int older= genericVO.getOlderCount().intValue();
					    int younger= genericVO.getYoungerCount().intValue();
					    int female= genericVO.getFemaleCount().intValue();
					    int male= genericVO.getMaleCount().intValue();
					    
					    int totalcount=older+younger+female+male;
					    
					    // Count of Male/Female Younger/Elder
					    
					    int genMaleCount = 0;
					    int genFemaleCount = 0;
					    int yngerCount = 0;
					    int eldersCount = 0;
					    
					    if(genericVO.getActualmaleCountPercentage()!=null){
					    	genMaleCount = genericVO.getActualmaleCountPercentage().intValue();
					    }
					    if(genericVO.getActualFemaleCountPercentage()!=null){
					    	genFemaleCount = genericVO.getActualFemaleCountPercentage().intValue();
					    }
					    if(genericVO.getActualYoungVotersPercentage()!=null){
					    	yngerCount = genericVO.getActualYoungVotersPercentage().intValue();
					    }
					    if(genericVO.getActualYelderVotersPercentage()!=null){
					    	eldersCount = genericVO.getActualYelderVotersPercentage().intValue();
					    }
					    
					    int ttlCount = genMaleCount + genFemaleCount + yngerCount + eldersCount;
					    
					    float genMalePercent = Float.valueOf(roundTo2DigitsFloatValue(((float)genMaleCount*100)/(float)ttlCount));
					    float genFemalePercent = Float.valueOf(roundTo2DigitsFloatValue(((float)genFemaleCount*100)/(float)ttlCount));
					    float yngerPercent = Float.valueOf(roundTo2DigitsFloatValue(((float)yngerCount*100)/(float)ttlCount));
					    float elderPercent = Float.valueOf(roundTo2DigitsFloatValue(((float)eldersCount*100)/(float)ttlCount));
					    
					    genericVO.setSurveyMalePercent(genMalePercent);
					    genericVO.setSurveyFemalePercent(genFemalePercent);
					    genericVO.setSurveyYoungerPercent(yngerPercent);
					    genericVO.setSurveyElderPercent(elderPercent);
					    
					    //calculate percentages 
					   /* float olderPer=Float.valueOf(roundTo2DigitsFloatValue(((float)older*100)/(float)totalcount));
					    float youngerPer= Float.valueOf(roundTo2DigitsFloatValue(((float)younger*100)/(float)totalcount));
					    float femalePer=Float.valueOf(roundTo2DigitsFloatValue(((float)female*100)/(float)totalcount));
					    float malePer=Float.valueOf(roundTo2DigitsFloatValue(((float)male*100)/(float)totalcount));*/
					    float olderPer=genericVO.getElderPercent();
					    float youngerPer= genericVO.getYoungerPercent();
					    float femalePer=genericVO.getFemalePercent();
					    float malePer=genericVO.getMalePercent();
					    
					    
					    //calculate count from percentages middleageMaleVoters+middleAgeFemaleVoters+youngVOters+yeldervoters;
					  
					    int olderFromTotal= (int)(olderPer*yeldervoters)/100;
					    int youngerFromTotal=  (int)(youngerPer*youngVOters)/100;
					    int femaleFromTotal=  (int)(femalePer*middleAgeFemaleVoters)/100;
					    int maleFromTotal= (int)(malePer*middleageMaleVoters)/100;
					    
					    //survey based total 
					    
					    genericVO.setOlderFromTotal(olderFromTotal);
					    genericVO.setYoungerFromTotal(youngerFromTotal);
					    genericVO.setFemaleFromTotal(femaleFromTotal);
					    genericVO.setMaleFromTotal(maleFromTotal);
					    genericVO.setTotalFromTotal(olderFromTotal+youngerFromTotal+femaleFromTotal+maleFromTotal);
					    
					    //actual counts from booth publication voter
					    genericVO.setActualTotal(total);
					    genericVO.setActualYelderVoters(yeldervoters);
					    genericVO.setActualYoungVoters(youngVOters);
					    genericVO.setActualFemaleCount(middleAgeFemaleVoters);
					    genericVO.setActualmaleCount(middleageMaleVoters);
					    
					    float actlMalePercent = Float.valueOf(roundTo2DigitsFloatValue(((float)genericVO.getActualmaleCount()*100)/(float)genericVO.getActualTotal()));
					    float actlFemalePercent = Float.valueOf(roundTo2DigitsFloatValue(((float)genericVO.getActualFemaleCount()*100)/(float)genericVO.getActualTotal()));
					    float actlYngerPercent = Float.valueOf(roundTo2DigitsFloatValue(((float)genericVO.getActualYoungVoters()*100)/(float)genericVO.getActualTotal()));
					    float actlElderPercent = Float.valueOf(roundTo2DigitsFloatValue(((float)genericVO.getActualYelderVoters()*100)/(float)genericVO.getActualTotal()));
					    
					    
					    
					    if( genMalePercent < actlMalePercent){
					    	Double per = calculateErrorPerc(genericVO.getActualmaleCount(),(long)male,(double)((float)male*100/(float)genMaleCount));
					    	float prevMaleper=genericVO.getMalePercent();
					    	genericVO.setMalePercent(Float.valueOf(roundTo2DigitsFloatValue(prevMaleper+per.floatValue())));
					    	
					    	
						    
						    
						    
					    }
					    
					    if( genFemalePercent < actlFemalePercent){
					    	Double per = calculateErrorPerc(genericVO.getActualFemaleCount(),(long)female,(double)((float)female*100/(float)genFemaleCount));
					    	float prevFemalePer=genericVO.getFemalePercent();
					    	genericVO.setFemalePercent(Float.valueOf(roundTo2DigitsFloatValue(prevFemalePer+per.floatValue())));
					    	
					    }
					    
					    if( yngerPercent < actlYngerPercent){
					    	Double per = calculateErrorPerc(genericVO.getActualYoungVoters(),(long)younger,(double)((float)younger*100/(float)yngerCount));
					    	float prevYoungerPer= genericVO.getYoungerPercent();
					    	genericVO.setYoungerPercent(Float.valueOf(roundTo2DigitsFloatValue(prevYoungerPer+per.floatValue())));
					    }
					    
					    if( elderPercent < actlElderPercent){
					    	Double per = calculateErrorPerc(genericVO.getActualYelderVoters(),(long)older,(double)((float)older*100/(float)eldersCount));
					    	float prevElderPer=genericVO.getElderPercent();
					    	genericVO.setElderPercent(Float.valueOf(roundTo2DigitsFloatValue(prevElderPer+per.floatValue())));
					    }
					    
					    youngVOtersCumm=youngVOtersCumm+youngerFromTotal;
					    yeldervotersCumm=yeldervotersCumm+olderFromTotal;
					    middleageMaleVotersCumm=middleageMaleVotersCumm+maleFromTotal;
					    middleAgeFemaleVotersCumm=middleAgeFemaleVotersCumm+femaleFromTotal;
					    
					    //set to vo
					    
				          }
				    int avg=vo.size();
				    if(avg==0)
				    	return;
				    youngVOtersCumm=youngVOtersCumm/avg;
				    yeldervotersCumm=yeldervotersCumm/avg;
				    middleageMaleVotersCumm=middleageMaleVotersCumm/avg;
				    middleAgeFemaleVotersCumm=middleAgeFemaleVotersCumm/avg;
				    for (com.itgrids.survey.soa.endpoints.GenericVO genericVO : vo) {
				  
				    genericVO.setYeldervotersCumm(yeldervotersCumm);
				    genericVO.setYoungVOtersCumm(youngVOtersCumm);
				    genericVO.setMiddleAgeFemaleVotersCumm(middleAgeFemaleVotersCumm);
				    genericVO.setMiddleageMaleVotersCumm(middleageMaleVotersCumm);
				    genericVO.setTotalVotersCumm(yeldervotersCumm+youngVOtersCumm+middleAgeFemaleVotersCumm+middleageMaleVotersCumm);
				    }
				    
					System.out.println(vo.size());
	}
	
	
	// Example : NN -- 80000( Males )  nn -- 5000 ( in 10000 Samples Males Supporting to TDP ) pp -- (5000*100/10000) =50% 
	public Double calculateErrorPerc(Long NN,Long nn,Double pp){
		try{
			Double moe=(196000*Math.sqrt((NN-nn)/(NN-1))*Math.sqrt(pp*(1-pp)/nn))/1000;
			return moe;
		}catch(Exception e){
			LOG.error("Exception rised in calculateErrorPerc",e);
		}
			return 0d;
		}
	
	public static String roundTo2DigitsFloatValue(Float number){
		  
		  Log.debug("Entered into the roundTo2DigitsFloatValue service method");
		  
		  String result = "";
		  try
		  {
			  
			
			NumberFormat f = NumberFormat.getInstance(Locale.ENGLISH);  
			f.setMaximumFractionDigits(2);  
			f.setMinimumFractionDigits(2);
			
			result =  f.format(number);
		  }catch(Exception e)
		  {
			  Log.error("Exception raised in roundTo2DigitsFloatValue service method");
			  e.printStackTrace();
		  }
		  return result;
	}


}

