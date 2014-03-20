package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStrategyMergPancListDAO;
import com.itgrids.partyanalyst.dao.ISuggestiveRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.PanchayatVO;
import com.itgrids.partyanalyst.dto.PartyEffectVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.StrategyVO;
import com.itgrids.partyanalyst.dto.SuggestedLocationsVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.SuggestiveRange;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.utils.IConstants;

public class StrategyModelTargetingService implements
		IStrategyModelTargetingService {
	
	private static final Logger LOG = Logger.getLogger(StrategyModelTargetingService.class);
	
	private IRegionServiceData regionServiceDataImp;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO ;
	private ISuggestiveRangeDAO suggestiveRangeDAO;
	private IElectionDAO electionDAO;
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothDAO boothDAO;
	private IStaticDataService staticDataService;
	private IPanchayatDAO panchayatDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IStrategyMergPancListDAO strategyMergPancListDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IPartialBoothPanchayatDAO partialBoothPanchayatDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IConstituencyDAO constituencyDAO;
	

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public void setSuggestiveRangeDAO(ISuggestiveRangeDAO suggestiveRangeDAO) {
		this.suggestiveRangeDAO = suggestiveRangeDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setStrategyMergPancListDAO(
			IStrategyMergPancListDAO strategyMergPancListDAO) {
		this.strategyMergPancListDAO = strategyMergPancListDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public void setPartialBoothPanchayatDAO(
			IPartialBoothPanchayatDAO partialBoothPanchayatDAO) {
		this.partialBoothPanchayatDAO = partialBoothPanchayatDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public void setTotalCasteSort(Comparator<PanchayatVO> totalCasteSort) {
		this.totalCasteSort = totalCasteSort;
	}

	public void setYoungOldSort(Comparator<PanchayatVO> youngOldSort) {
		this.youngOldSort = youngOldSort;
	}

	
	public void getCriticalPanchayatsData(){
		
	}
	
	public List<PartyPositionVO> getPartyPreviousTrends(Long constituencyId,Long partyId,List<Long> electionIds,Map<Long,PartyEffectVO> partyEffect,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult)
	{
		List<PartyPositionVO> resultList = null;
		try{

			List<Long> assemblyEleIdsList = new ArrayList<Long>(0);
			List<Long> mandalIds = new ArrayList<Long>();
			List<Long> localbodyIds = new ArrayList<Long>();
			List<Long> panchayatIds = null;
			List<Object[]> boothIdsList = null;
	        //getting mandals and municipalities in that consti
			List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
			
			if(mandalsList != null && mandalsList.size() > 0){
			   for(SelectOptionVO vo : mandalsList){
			      if(vo.getId().toString().substring(0,1).equalsIgnoreCase("2")){
			         mandalIds.add(new Long(vo.getId().toString().substring(1)));
			      }else{
				     localbodyIds.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(vo.getId().toString().substring(1))).get(0));
			      }
			   }
			}
	
			 for(Long eleId : electionIds)
			  {
				 assemblyEleIdsList.add(eleId); 
			  } 	
			
			if(mandalIds != null && mandalIds.size() > 0){
				 //getting all panchayats in mandals
				 panchayatIds =hamletBoothElectionDAO.getPanchayatIdsByEleIdMandalIdConstituencyId(mandalIds,assemblyEleIdsList.get(0),constituencyId);
			}
				 
			if(assemblyEleIdsList != null && assemblyEleIdsList.size() > 0)
			{
				resultList = new ArrayList<PartyPositionVO>(0);
				  List<SuggestiveRange> suggestiveRangeList = suggestiveRangeDAO.getSuggestiveRangeList();
					  
				  PartyPositionVO partyPositionVO = null;
				  int count = 0;
				  for(Long eleId :assemblyEleIdsList){
					  count = count+1;
					Election election = electionDAO.get(eleId);
					partyPositionVO = new PartyPositionVO();
					List<PartyPositionVO> rangeList = new ArrayList<PartyPositionVO>();
					
					PartyPositionVO range = null;
					for(SuggestiveRange suggestiveRange:suggestiveRangeList)
					  {
						range = new PartyPositionVO();
						range.setName(suggestiveRange.getName());
						range.setMinValue(suggestiveRange.getMinValue());
						range.setMaxValue(suggestiveRange.getMaxValue());
						range.setTempVar(suggestiveRange.getColor());							
						rangeList.add(range);
						
					  }
					
					partyPositionVO.setPartyPositionVOList(rangeList);
					partyPositionVO.setName(election.getElectionYear() != null?election.getElectionYear():" ");
					partyPositionVO.setId(eleId);
					partyPositionVO.setConstituencyId(constituencyId);
					
										
					if(localbodyIds != null && localbodyIds.size() > 0){
						//getting all booths in municipality
					  boothIdsList = hamletBoothElectionDAO.getBoothsByLocalBodyNElectionId(localbodyIds,eleId);
					}
					if(count == 1){
					    getMandalWisePartyPerformanceReport(constituencyId,eleId, partyPositionVO, partyId,panchayatIds,boothIdsList,partyEffect,effectPartyId,effectElectionId,currentResult);
					}else{
						getMandalWisePartyPerformanceReport(constituencyId,eleId, partyPositionVO, partyId,panchayatIds,boothIdsList,partyEffect,effectPartyId,effectElectionId,null);
					}
					 resultList.add(partyPositionVO);
				  }
			}
			
			if(resultList != null && resultList.size() == 2){
			  List<PartyPositionVO> suggestedLocations = getSuggestiveLocationsForAParty(resultList);
			  resultList.get(0).setSuggestedLocations(suggestedLocations);
			}	
	
			 return resultList;
		}catch (Exception e) {
		 LOG.error(" Exception Occured in getPartyPreviousTrends() method, Exception-",e);
		 return resultList;
		}
	}
	
	public void getMandalWisePartyPerformanceReport(Long constituencyId,Long electionId,PartyPositionVO partyPositionVO,Long partyId,List<Long> panchaytIdsList,List<Object[]> localbodybooths,Map<Long,PartyEffectVO> partyEffect,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult)
	{
		try{
		Map<Long,List<Long>> boothIds = new HashMap<Long, List<Long>>();//Map<boothId,List<panchayatId>>
		Map<Long,Map<Long,Long>> resultMap = new HashMap<Long, Map<Long,Long>>(0);//Map<panchayatId,Map<partyId,totalvoters>>
		Map<Long,Map<Long,Long>> resultMap1 = new HashMap<Long,Map<Long,Long>>(0);//Map<localbodyname,Map<partyId,totalvoters>>
		Map<Long,List<Long>> boothIdMap = new HashMap<Long, List<Long>>(0);//<localBodyName,<boothIds>
		
		List<Long> booths = new ArrayList<Long>();
		if(localbodybooths != null && localbodybooths.size() > 0)
		{
		for(Object[] params: localbodybooths)
		{
			List<Long> boothIds1 = boothIdMap.get(params[2]);
				if(boothIds1 == null)
				{
				boothIds1 = new ArrayList<Long>(0);	
				boothIdMap.put((Long)params[2], boothIds1);
				}
				if(!boothIds1.contains((Long)params[0]))
				boothIds1.add((Long)params[0]);
		}
		
		for(Long localbodyId : boothIdMap.keySet())
		{
			booths = boothIdMap.get(localbodyId);
			//getting all results boothwise for municipality
			List<Object[]> partyResult = candidateBoothResultDAO.findBoothResultsForBoothsAndElectionAndAllParties( boothIdMap.get(localbodyId),electionId,null);
			Map<Long,Long> partyMap1 = null;
			for(Object[] params : partyResult)
			{
				partyMap1 = resultMap1.get(localbodyId);
				if(partyMap1 == null)
				{
					partyMap1 = new HashMap<Long, Long>(0);
					resultMap1.put(localbodyId, partyMap1);
				}
				Long votesEarned = partyMap1.get((Long)params[0]);
				if(votesEarned == null)
					partyMap1.put((Long)params[0], (Long)params[2]);	
				else
					partyMap1.put((Long)params[0], votesEarned+(Long)params[2]);	
			}
			
		}
		}
		if(panchaytIdsList != null && panchaytIdsList.size() > 0)
		{
		  //getting what are the booths present in a panchayat
		  List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchaytIdsList, electionId); 
		  if(list != null && list.size() > 0)
		  {
			  for(Object[] params:list)
			  {
				  List<Long> panchayatIds = boothIds.get((Long)params[1]);
				  if(panchayatIds == null)
				  {
				   panchayatIds = new ArrayList<Long>(0);
				   boothIds.put((Long)params[1], panchayatIds);
				  }
				  if(!panchayatIds.contains((Long)params[0]))
				   panchayatIds.add((Long)params[0]);
			  }
			 //getting all results boothwise for mandals
			  List<Object[]> resultList = candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionIdForSelElection(boothIds.keySet(), electionId);
			  if(resultList != null && resultList.size() > 0)
			  {
				 for(Object[] params:resultList)
				 {
				   List<Long> panchayatIdsList = boothIds.get((Long)params[0]);
				   if(panchayatIdsList != null && panchayatIdsList.size() > 0)
				   {
					 Map<Long,Long> partyMap = null;//Map<PartyId,totalvotes>
					 for(Long panchayatId :panchayatIdsList)
					 {
						 partyMap = resultMap.get(panchayatId);
						 if(partyMap == null)
						 {
							 partyMap = new HashMap<Long, Long>(0);
							 resultMap.put(panchayatId, partyMap);
						 }
						 Long votesEarned = partyMap.get((Long)params[1]);
						 if(votesEarned == null)
						  partyMap.put((Long)params[1],(Long)params[2]);
						 else
						  partyMap.put((Long)params[1], votesEarned+(Long)params[2]);
					 }
				   }
				   
				 }
			  }
			  
		  }
		}
		//getting alliance parties for selected party
		//used to consider alliance party votes if selected party is not participated
		AlliancePartyResultsVO alliancePartiesVO = staticDataService.getAlliancePartiesByElectionAndParty(electionId,partyId);
		if(resultMap != null && resultMap.size() > 0){
			//getting results for panchayats
			getPartyPerformanceForPanchayat(resultMap,partyPositionVO, partyId,electionId,partyEffect,alliancePartiesVO,effectPartyId,effectElectionId,currentResult); 
		}
		if(resultMap1 != null && resultMap1.size() > 0){
			//getting results for municipalities
		  getPartyPerformanceForLocalBody(partyPositionVO, partyId,resultMap1,booths,partyEffect,alliancePartiesVO,effectPartyId,effectElectionId,currentResult);
		}
		
		}catch (Exception e) {
			LOG.error(" Exception Occured in getMandalWisePartyPerformanceReport() method, Exception - ",e);
		  }
	}
	
	 public void getPartyPerformanceForPanchayat(Map<Long,Map<Long,Long>> resultMap,PartyPositionVO partyPositionVO, Long selectedpartyId, Long electionId,Map<Long,PartyEffectVO> partyEffect,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult)
		{
			try{
				
				//resultMap -- Map<panchayatId,Map<partyId,totalvoters>>
				//resultMap1 -- Map<localbodyName,Map<partyId,totalvoters>>
				
				Map<Long,List<Long>> boothIdsMap = new HashMap<Long, List<Long>>(0);//<panchayatId,List<boothIds>>
				Map<Long,Long> panchayatTotalVotersMap = new HashMap<Long, Long>(0);//<locationId,totalVoters>
									
				  List<Long> panchayatIdsList = new ArrayList<Long>(resultMap.keySet());
				  //getting booths in a panchayat
				  List<Object[]> boothList = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayatIdsList(panchayatIdsList, partyPositionVO.getId());
				  if(boothList != null && boothList.size() > 0)
				  {
					  for(Object[] params:boothList)
					  {
						  List<Long> boothIdsList = boothIdsMap.get((Long)params[0]);
						  if(boothIdsList == null)
						  {
							boothIdsList = new ArrayList<Long>(0);
							boothIdsMap.put((Long)params[0], boothIdsList);  
						  }
						  if(!boothIdsList.contains((Long)params[1]))
							  boothIdsList.add((Long)params[1]);  
					  }
					  
				  }				
				
			if(boothIdsMap != null && boothIdsMap.size() > 0)
			{
			  for(Long id : boothIdsMap.keySet()){
				 //setting total voters in each panchayat
				panchayatTotalVotersMap.put(id, boothDAO.getTotalVotesByBoothIdsList(boothIdsMap.get(id)));
			  }
			}
			Set<Long> mergePanchayatIds = new HashSet<Long>();
			Map<Long,Set<Long>> mergePanchayatMap = new HashMap<Long,Set<Long>>();
			List<Object[]> mergePanchayatsList = strategyMergPancListDAO.getPanchayatsToMerge(partyPositionVO.getConstituencyId(),"E", electionId);
			for(Object[] mergePanchayat:mergePanchayatsList){
			   if(mergePanchayat[0] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[0]);
			   }
			   if(mergePanchayat[1] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[1]);
			   }
			   
			   Set<Long> panchayats = mergePanchayatMap.get((Long)mergePanchayat[1]);
			   if(panchayats == null){
				   panchayats = new HashSet<Long>();
				   mergePanchayatMap.put((Long)mergePanchayat[1],panchayats);
				   panchayats.add((Long)mergePanchayat[1]);
			   }
			   panchayats.add((Long)mergePanchayat[0]);
			}
			//iterating all panchayats
			 for(Long id:resultMap.keySet())
			 {
				 if(!mergePanchayatIds.contains(id)){
				   calculateDifference(panchayatTotalVotersMap,resultMap,id,selectedpartyId,alliancePartiesVO,effectPartyId,effectElectionId,electionId,partyEffect,partyPositionVO,currentResult);
				 }
			 } 
			if(mergePanchayatMap.size() > 0){
				calculateDifferenceForMergePanchayats(panchayatTotalVotersMap,resultMap,selectedpartyId,alliancePartiesVO,effectPartyId,effectElectionId,electionId,partyEffect,partyPositionVO,mergePanchayatMap,currentResult);
			}
			}catch (Exception e) {
			 LOG.error(" Exception Occured in getPartyPerformanceForPanchayat() method, Exception - ",e);
			}
		}
	 
	 public void calculateDifference(Map<Long,Long> panchayatTotalVotersMap,Map<Long,Map<Long,Long>> resultMap,Long id,Long selectedpartyId,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Long electionId,Map<Long,PartyEffectVO> partyEffect,PartyPositionVO partyPositionVO,Map<Long,Double> currentResult){
		 

			Map<Long,Long> partyMap = resultMap.get(id);
			Long totalVotes = 0L;
				 
			for(Long partysId:partyMap.keySet())
			  totalVotes += partyMap.get(partysId); 
				 
			Long selectedPartyTotal = partyMap.get(selectedpartyId);
			
			//if party not partispated considering alliance
			if(selectedPartyTotal == null){
				 
				  if(alliancePartiesVO.getAllianceParties() == null)
					  selectedPartyTotal = 0L;
				  else
					  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
						  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
						  selectedPartyTotal = partyMap.get(alianceParty.getId());
			  }
		   
		  if(selectedPartyTotal == null)
			  selectedPartyTotal = 0L;
		  

		  double difference = 0d;
		  if(totalVotes != null && totalVotes > 0){
			  difference =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		  }
		  
		  //getting other party effect
		  Double effectPartyperc = 0d;
		  if(effectElectionId != null && effectPartyId != null){
			  Long prp = null;
			if(electionId.longValue() == effectElectionId.longValue())
				prp = partyMap.get(effectPartyId);
			
		  
		     if(prp != null && totalVotes != null && totalVotes > 0){
		    	 effectPartyperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		     }
	    
		  }	
		  String locationName = panchayatDAO.getPanchayatNameById(id); 
		  PartyEffectVO effect = partyEffect.get(id);
	    	 if(effect == null){
	    	  effect = new PartyEffectVO();
	    	  effect.setName(locationName);
	    	  partyEffect.put(id, effect);
	    	 }
	    	 if(effectElectionId != null && effectPartyId != null && electionId.longValue() == effectElectionId.longValue()){
	    		 effect.setPrpCurrentPerc(effectPartyperc);
	    		 effect.setTdpCurrentPerc(difference);
	    		 effect.setId(id);
	    		 effect.setReportLvl(3l);
	    	 }else{
	    		 effect.setTdpPrevPerc(difference); 
	    	 }
	    	 
	      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	      for(PartyPositionVO positionVO :partyPositionVOList)
	      {
	    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
	    	{
	    	 PartyPositionVO locationVO = null;
	    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
	    	 if(locationList == null || locationList.size() == 0)
	    		locationList = new ArrayList<PartyPositionVO>(0);
	    	 
	    	 locationVO = checkPartyPositionVOExist(id,locationList);
	    	 if(locationVO == null)
	    	 {
	    		 locationVO = new PartyPositionVO();
	    		 locationVO.setId(id);
	    		 locationVO.setName(locationName != null?locationName:" ");
	    		 locationVO.setPartyPercentage(difference);
	    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
	    		 locationVO.setTotalValidVotes(totalVotes);
	    		 locationVO.setTotalVoters(panchayatTotalVotersMap.get(id));
	    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVotersMap.get(id))).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    		 locationVO.setMargin(difference);
	    		 if(currentResult != null)
	    		   currentResult.put(id, difference);
	    		 locationList.add(locationVO);
	    		 positionVO.setPartyPositionVOList(locationList);
	    		 
	    	 }
	    	 
	    	}
	      }
	    	
		
		 
	 }
	 
	 public void calculateDifferenceForMergePanchayats(Map<Long,Long> panchayatTotalVotersMap,Map<Long,Map<Long,Long>> resultMap,Long selectedpartyId,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Long electionId,Map<Long,PartyEffectVO> partyEffect,PartyPositionVO partyPositionVO,Map<Long,Set<Long>> mergePanchayatMap,Map<Long,Double> currentResult){
		 
        for(Long key:mergePanchayatMap.keySet()){
        	Set<Long> panchayatIds = mergePanchayatMap.get(key);
			
		  Long totalVotes = 0L;
		  Long selectedPartyTotal = 0l;
		  Long prp = 0l;
		  Long panchayatTotalVoters = 0l;
		  for(Long id:panchayatIds){	
			 Map<Long,Long> partyMap = resultMap.get(id);	 
			 for(Long partysId:partyMap.keySet()){
			  totalVotes += partyMap.get(partysId); 
		     }	
		  
		     Long partyTotal = partyMap.get(selectedpartyId);
			
			 //if party not partispated considering alliance
			 if(partyTotal == null){
				  if(alliancePartiesVO.getAllianceParties() == null){
					  partyTotal = 0L;
				  }else{
					  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties()){
						  if(partyTotal == null || partyTotal.longValue() == 0l){
							  partyTotal = partyMap.get(alianceParty.getId());
						  }
					  }
				  }
			  }
		   
		    if(partyTotal == null){
		 	  partyTotal = 0L;
		    }
		    selectedPartyTotal = selectedPartyTotal+partyTotal;
		    
		    if(effectElectionId != null && effectPartyId != null){
				if(electionId.longValue() == effectElectionId.longValue() && partyMap.get(effectPartyId) != null){
					prp = prp+partyMap.get(effectPartyId);
				}
		    }
		    
		    Long totVoters = panchayatTotalVotersMap.get(id);
		    if(totVoters != null){
		    	panchayatTotalVoters = panchayatTotalVoters+totVoters;
		    }
         }
         
			
		  double difference = 0d;
		  if(totalVotes != null && totalVotes > 0){
			  difference =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		  }
		  
		  //getting other party effect
		  Double effectPartyperc = 0d;
		  
		  
		     if(prp != null && totalVotes != null && totalVotes > 0){
		    	 effectPartyperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		     }
	    
		  
		  String locationName = panchayatDAO.getPanchayatNameById(key); 
		  PartyEffectVO effect = partyEffect.get(key);
	    	 if(effect == null){
	    	  effect = new PartyEffectVO();
	    	  effect.setName(locationName);
	    	  partyEffect.put(key, effect);
	    	 }
	    	 if(effectElectionId != null && effectPartyId != null && electionId.longValue() == effectElectionId.longValue()){
	    		 effect.setPrpCurrentPerc(effectPartyperc);
	    		 effect.setTdpCurrentPerc(difference);
	    		 effect.setId(key);
	    		 effect.setReportLvl(3l);
	    	 }else{
	    		 effect.setTdpPrevPerc(difference); 
	    	 }
	    	 
	      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
	      for(PartyPositionVO positionVO :partyPositionVOList)
	      {
	    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
	    	{
	    	 PartyPositionVO locationVO = null;
	    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
	    	 if(locationList == null || locationList.size() == 0)
	    		locationList = new ArrayList<PartyPositionVO>(0);
	    	 
	    	 locationVO = checkPartyPositionVOExist(key,locationList);
	    	 if(locationVO == null)
	    	 {
	    		 locationVO = new PartyPositionVO();
	    		 locationVO.setId(key);
	    		 locationVO.setName(locationName != null?locationName:" ");
	    		 locationVO.setPartyPercentage(difference);
	    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
	    		 locationVO.setTotalValidVotes(totalVotes);
	    		 locationVO.setTotalVoters(panchayatTotalVoters);
	    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/panchayatTotalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
	    		 locationVO.setMargin(difference);
	    		 if(currentResult != null)
	    		   currentResult.put(key, difference);
	    		 locationList.add(locationVO);
	    		 positionVO.setPartyPositionVOList(locationList);
	    		 
	    	 }
	    	 
	    	}
	      }
	    	
	    }
		 
	 }
	 
	 public void getPartyPerformanceForLocalBody(PartyPositionVO partyPositionVO, Long selectedpartyId,Map<Long,Map<Long,Long>> resultMap1,List<Long> localbodyboothIds,Map<Long,PartyEffectVO> partyEffect,AlliancePartyResultsVO alliancePartiesVO,Long effectPartyId,Long effectElectionId,Map<Long,Double> currentResult)
	 {
		 try{
			 Long localbodytotalVoters = 0l;
			 if(localbodyboothIds != null && localbodyboothIds.size() > 0)
		     localbodytotalVoters = boothDAO.getTotalaVotesByBoothIds(localbodyboothIds).get(0);	
			 if(resultMap1 != null)
			 {
			 for(Long localbodyId : resultMap1.keySet())
			 {
				 String localbodyName = localElectionBodyDAO.getLocalElectionBodyName1(localbodyId);
					Map<Long,Long> partyMap1 = resultMap1.get(localbodyId);
					Long totalVotes = 0L;
						 
					for(Long partysId:partyMap1.keySet())
					  totalVotes += partyMap1.get(partysId); 
						 
					Long selectedPartyTotal = partyMap1.get(selectedpartyId);
					
					if(selectedPartyTotal == null){
						 
						  if(alliancePartiesVO.getAllianceParties() == null)
							  selectedPartyTotal = 0L;
						  else
							  for(SelectOptionVO alianceParty:alliancePartiesVO.getAllianceParties())
								  if(selectedPartyTotal == null || selectedPartyTotal.longValue() == 0l)
								  selectedPartyTotal = partyMap1.get(alianceParty.getId());
					  }
				  if(selectedPartyTotal == null)
					  selectedPartyTotal = 0L;
				  
				  double selectedPartyTotalPercent = 0d;
				   if(totalVotes != null)
				     selectedPartyTotalPercent =  new BigDecimal((selectedPartyTotal*100.0/totalVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			     
			     
			      double difference = selectedPartyTotalPercent;
			      
			      Double effectPartyperc = 0d;
				  if(effectElectionId != null && effectPartyId != null){
					  Long prp = null;
					if(partyPositionVO.getId().longValue() == effectElectionId.longValue())
						prp = partyMap1.get(effectPartyId);
					
				  
				     if(prp != null && totalVotes != null && totalVotes > 0){
				    	 effectPartyperc =  new BigDecimal((prp*100.0/totalVotes)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
				     }
			    
				  }	
				 
				  PartyEffectVO effect = partyEffect.get(localbodyId);
			    	 if(effect == null){
			    	  effect = new PartyEffectVO();
			    	  effect.setName(localbodyName);
			    	  partyEffect.put(localbodyId, effect);
			    	 }
			    	 if(effectElectionId != null && effectPartyId != null && partyPositionVO.getId().longValue() == effectElectionId.longValue()){
			    		 effect.setPrpCurrentPerc(effectPartyperc);
			    		 effect.setTdpCurrentPerc(difference);
			    		 effect.setId(localbodyId);
			    		 effect.setReportLvl(5l);
			    	 }else{
			    		 effect.setTdpPrevPerc(difference); 
			    	 } 
			    	
			      List<PartyPositionVO> partyPositionVOList = partyPositionVO.getPartyPositionVOList();
			      for(PartyPositionVO positionVO :partyPositionVOList)
			      {
			    	if(difference >= positionVO.getMinValue() && difference <= positionVO.getMaxValue())
			    	{
			    	 PartyPositionVO locationVO = null;
			    	 List<PartyPositionVO> locationList = positionVO.getPartyPositionVOList();
			    	 if(locationList == null || locationList.size() == 0)
			    		locationList = new ArrayList<PartyPositionVO>(0);
			    	 
			    	 locationVO = checkPartyPositionVOExist(localbodyId,locationList);
			    	 if(locationVO == null)
			    	 {
			    		 locationVO = new PartyPositionVO();
			    		 locationVO.setId(localbodyId);
			    		 locationVO.setName(localbodyName != null?localbodyName:" ");
			    		 locationVO.setPartyPercentage(selectedPartyTotalPercent);
			    		 locationVO.setSelectedPartyTotalVoters(selectedPartyTotal);
			    		 locationVO.setTotalValidVotes(totalVotes);
			    		 locationVO.setTotalVoters(localbodytotalVoters);
			    		 locationVO.setPercentage(new BigDecimal((totalVotes*100.0/localbodytotalVoters)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			    		 locationVO.setMargin(difference);
			    		 locationList.add(locationVO);
			    		 if(currentResult != null)
			    		  currentResult.put(localbodyId, difference);
			    		 positionVO.setPartyPositionVOList(locationList);
			    		 
			    	 }
			    	 
			    	}
			      }
			    	
				
			 }
			 } 
		 }
		 catch(Exception e)
		 {
			 LOG.error(" Exception Occured in getPartyPerformanceForLocalBody() method, Exception - ",e);
		 }
	 }

	 public PartyPositionVO checkPartyPositionVOExist(Long locationId,List<PartyPositionVO> list)
		{
			try{
			if(list == null)
			 return null;
			for(PartyPositionVO positionVO:list)
			 if(positionVO.equals(locationId))
			  return positionVO;
				
			 return null;
			}catch (Exception e) {
			 LOG.error(" ExceptionOccured in checkPartyPositionVOExist() method, Exception - ",e);
			 return null;
			}
		}
	 public PartyPositionVO checkPartyPositionVOExists(String name,List<PartyPositionVO> localbodyList)
		{
			try{
				if(localbodyList == null || localbodyList.size() == 0)
					return null;
				for(PartyPositionVO vo : localbodyList)
					if(vo.getName().equalsIgnoreCase(name))
						return vo;
				return null;
			}
			catch(Exception e)
			{
				 LOG.error(" ExceptionOccured in checkPartyPositionVOExists() method, Exception - ",e);
				return null;	
			}
		}
	 public List<PartyPositionVO> getSuggestiveLocationsForAParty(List<PartyPositionVO> partyPositions){
		 List<PartyPositionVO> returnValues = new ArrayList<PartyPositionVO>(); 
	    try{
		 if(partyPositions.size() == 2){
			 List<PartyPositionVO> latestElec =  partyPositions.get(0).getPartyPositionVOList();
			 List<PartyPositionVO> prevElec =  partyPositions.get(1).getPartyPositionVOList();
			 Collections.reverse(latestElec);
			 Collections.reverse(prevElec);
			 int z = 1;
			 for(z = 1;z<latestElec.size();z++){
				  for(int j = 0;j<z;j++){//2009
					  List<PartyPositionVO> panchayaties = latestElec.get(j).getPartyPositionVOList();
					  if(panchayaties != null && panchayaties.size() >0){	
						for(int i = prevElec.size()-z+j;i>=prevElec.size()-z+j;i--)
						{//2004
							List<PartyPositionVO> prevPanchayaties = prevElec.get(i).getPartyPositionVOList();
							if(prevPanchayaties != null && prevPanchayaties.size() >0){	
								populateMachedValues(panchayaties,prevPanchayaties,returnValues,z);
							}
						}
					  }
					}
				  }
			 z = z-1;
			    for(int y = 2;y<=latestElec.size()-1;y++){
				  for(int j = y-2;j<y;j++){
					  z++;
					  List<PartyPositionVO> latestPanchayaties = latestElec.get(y-1).getPartyPositionVOList();
					  List<PartyPositionVO> prevPanchayaties = prevElec.get(j).getPartyPositionVOList();
					  populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						for(int i = j,l=y;i>0&&l<latestElec.size()-1;i--,l++)
						{   latestPanchayaties = latestElec.get(l).getPartyPositionVOList();
						    prevPanchayaties = prevElec.get(i-1).getPartyPositionVOList();
						    populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
						}
					}
				  } 
			    z++;
			    for(int last=0;last < latestElec.size() ; last++ ){
			    	List<PartyPositionVO> latestPanchayaties = latestElec.get(latestElec.size()-1).getPartyPositionVOList();
			    	List<PartyPositionVO> prevPanchayaties = prevElec.get(last).getPartyPositionVOList();
				    populateMachedValues(latestPanchayaties,prevPanchayaties,returnValues,z);
			    }
			    populateMachedValues(latestElec.get(0).getPartyPositionVOList(),prevElec.get(0).getPartyPositionVOList(),returnValues,z);
			     Collections.reverse(latestElec);
				 Collections.reverse(prevElec);
		 }
	    }catch(Exception e){
	    	LOG.error(" Exception Occured in getSuggestiveLocationsForAParty() method, Exception - ",e);
	    }
	    return returnValues;
	 }
	 
	 
	 public void populateMachedValues(List<PartyPositionVO> panchayaties,List<PartyPositionVO> prevPanchayaties,List<PartyPositionVO> returnValues,int priorityOrder){
		if(prevPanchayaties != null && prevPanchayaties.size() > 0){
			 for(PartyPositionVO prev:prevPanchayaties){
				  if(panchayaties != null && panchayaties.size() > 0){ 
					    for(PartyPositionVO current:panchayaties){
						   if(current.getId().longValue() == prev.getId().longValue()){
							   current.setPriorityOrder(priorityOrder);
							  returnValues.add(current);
							}
						}
				  }
			 }
	    }
	 }
	 public List<Object> getYoungOldVotersEffectWithOutCaste(Long publicationId,Long constituencyId){
		 List<Object> priorityList = new ArrayList<Object>();
		   
		    List<PanchayatVO> youngCastesList = null;
		    List<PanchayatVO> agedCastesList = null;
		    
		    List<Object[]> totalVoters = new ArrayList<Object[]>();
			List<Object[]> youngTotal = new ArrayList<Object[]>();
			List<Object[]> agedTotal = new ArrayList<Object[]>();
			Map<Long,String> panchayatNames = new HashMap<Long,String>();
			
            Constituency constituency = constituencyDAO.get(constituencyId);
            
            Map<Long,Long> totalVotersMap = new HashMap<Long,Long>();
            Map<Long,Long> ageVotersMap = new HashMap<Long,Long>();
		    
		    Set<Long> mergePanchayatIds = new HashSet<Long>();
			Map<Long,Set<Long>> mergePanchayatMap = new HashMap<Long,Set<Long>>();
			getPanchayatsToMerge(mergePanchayatIds,mergePanchayatMap,constituencyId,"P",publicationId);
			
			 //getting partial panchayats
			   List<Object[]> partialPanchayatsList = partialBoothPanchayatDAO.getPartialPanchayatsList(constituencyId, publicationId);
			   List<Long> partialIds = new ArrayList<Long>();
			   for(Object[] partial:partialPanchayatsList){
					if(partial[0] !=null && !partialIds.contains((Long)partial[0])){
						partialIds.add((Long)partial[0]);
					}
					if(partial[1] !=null && !partialIds.contains((Long)partial[1])){
						partialIds.add((Long)partial[1]);
					}
			   }
			   
			   if(partialIds.size() > 0){
				   
				   //calculating total voters starts
				   List<Object[]> totalVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, null, null);
				   List<Object[]> totalVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, null, null);
		
				   if(totalVotersCount != null && totalVotersCount.size() > 0){
					   totalVoters.addAll(totalVotersCount);
				   }
				   if(totalVotersPartialCount != null && totalVotersPartialCount.size() > 0){
					   totalVoters.addAll(totalVotersPartialCount);
				   }
				   //calculating total voters ends
				   
				   //young voters calculation starts
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, 18l, 22l);
		
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				   if(youngVotersPartialCount != null && youngVotersPartialCount.size() > 0){
					   youngTotal.addAll(youngVotersPartialCount);
				   }
				    
				   //young voters calculation ends
				  
                   //aged voters calculation starts
				   
				   List<Object[]> agedVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 61l, 200l);
				   List<Object[]> agedVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, 61l, 200l);
				   
				   if(agedVotersCount != null && agedVotersCount.size() > 0){
					   agedTotal.addAll(agedVotersCount);
				   }
				   if(agedVotersPartialCount != null && agedVotersPartialCount.size() > 0){
					   agedTotal.addAll(agedVotersPartialCount);
				   }
				   
				   //aged voters calculation ends
			   }else{
				   List<Object[]> totalVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, null, null, null);

				   if(totalVotersCount != null && totalVotersCount.size() > 0){
					   totalVoters.addAll(totalVotersCount);
				   }
				   
				   //young voters calculation starts
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, null, 18l, 22l);
				   
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				  			    
				   //young voters calculation ends
				   
                   //aged voters calculation starts
				    List<Object[]> agedVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, null, 61l, 200l);   
				    
				    if(agedVotersCount != null && agedVotersCount.size() > 0){
						 agedTotal.addAll(agedVotersCount);
					 }
				    
				   //aged voters calculation ends
			   
			   }
			   if(constituency.getAreaType().equalsIgnoreCase("RURAL-URBAN")){
				   
				   List<Object[]> totalVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId,null, null);
				       if(totalVoterForMunic != null && totalVoterForMunic.size() > 0){
						   totalVoters.addAll(totalVoterForMunic);
					   }
					   
				       List<Object[]> totalYoungVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId, 18l, 22l);
					  
					   if(totalYoungVoterForMunic != null && totalYoungVoterForMunic.size() > 0){
						   youngTotal.addAll(totalYoungVoterForMunic);
					   }
					   List<Object[]> totalAgeVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId, 61l, 200l);
						  
					   if(totalAgeVoterForMunic != null && totalAgeVoterForMunic.size() > 0){
						  agedTotal.addAll(totalAgeVoterForMunic);
					   }
			   }
			   
				 
				 for(Object[] count:totalVoters){
					 totalVotersMap.put((Long)count[0],(Long)count[1]);
					 panchayatNames.put((Long)count[0],count[2].toString());
				 }
				 mergeTotalVoters(totalVotersMap,mergePanchayatMap);
				 
				 for(Object[] count:youngTotal){
					 ageVotersMap.put((Long)count[0],(Long)count[1]);
				 }
				 mergeTotalVoters(ageVotersMap, mergePanchayatMap);
				 
			     youngCastesList = getOrderOfPriorWithOutCaste(totalVotersMap,ageVotersMap,panchayatNames);
			     
			     ageVotersMap = new HashMap<Long,Long>();
			     for(Object[] count:agedTotal){
					 ageVotersMap.put((Long)count[0],(Long)count[1]);
				 }
			     mergeTotalVoters(ageVotersMap, mergePanchayatMap);
			     
			     agedCastesList = getOrderOfPriorWithOutCaste(totalVotersMap,ageVotersMap,panchayatNames);
			   
		   
		    priorityList.add(youngCastesList);
		    priorityList.add(agedCastesList);
			return priorityList;
		 
	 }

	 public List<PanchayatVO> getOrderOfPriorWithOutCaste(Map<Long,Long> totalVotersMap,Map<Long,Long> youngOldVotersList,Map<Long,String> panchayatNames){
		 
		List<PanchayatVO> totalPriorityList = new ArrayList<PanchayatVO>();
				 
			 PanchayatVO panchayatVo = null;
			 
			
			 for(Long panchayatId:youngOldVotersList.keySet()){
				 panchayatVo = new PanchayatVO();
				 panchayatVo.setPanchayatId(panchayatId);
				 panchayatVo.setCount(totalVotersMap.get(panchayatId));
				 panchayatVo.setPanchayatName(panchayatNames.get(panchayatId));
				 panchayatVo.setTotalTargetCount(youngOldVotersList.get(panchayatId));
				 panchayatVo.setTargetPerc(new BigDecimal((panchayatVo.getTotalTargetCount()*100)/panchayatVo.getCount()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
				 totalPriorityList.add(panchayatVo);
			 }
				
			 Collections.sort(totalPriorityList,youngOldSort);	
			 
			 Double maxPerc = totalPriorityList.get(0).getTargetPerc();
			 
			 for(PanchayatVO orderVo:totalPriorityList){
				 orderVo.setVoterPoints(new BigDecimal((orderVo.getTargetPerc()*100)/maxPerc).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 }
			 
			 return totalPriorityList;
	 }
	 
	 public List<Object> getTotalYoungOldVotersEffectWithCaste(Map<Long,Float> castePercents,Long publicationId,Long constituencyId,Map<Long,Set<Long>> mergeCasteMap,Map<Long,Double> currentResult){
		    List<Object> priorityList = new ArrayList<Object>();
		    
		    List<PanchayatVO> totalCastesList = null;
		    List<PanchayatVO> youngCastesList = null;
		    List<PanchayatVO> agedCastesList = null;
		    List<Long> panchayatIds = new ArrayList<Long>();
		    List<Long> lebIds = new ArrayList<Long>();
		    
		    Map<Long,String> panchayatNames = new HashMap<Long,String>();
		    Map<Long,Long> totalVotersMap = new HashMap<Long,Long>();
		    
		    List<Object[]> youngCaste = new ArrayList<Object[]>();
			List<Object[]> youngTotal = new ArrayList<Object[]>();
			List<Object[]> ageCaste = new ArrayList<Object[]>();
			List<Object[]> ageTotal = new ArrayList<Object[]>();
			
			
			
			List<Long> locationLvs = new ArrayList<Long>();
			 locationLvs.add(3l);
			 locationLvs.add(5l);
			 
		    Constituency constituency = constituencyDAO.get(constituencyId);
		    
		    Set<Long> mergePanchayatIds = new HashSet<Long>();
			Map<Long,Set<Long>> mergePanchayatMap = new HashMap<Long,Set<Long>>();
			getPanchayatsToMerge(mergePanchayatIds,mergePanchayatMap,constituencyId,"P",publicationId);
			
			   
			//total voters calculation starts
			   List<Object[]> totalVotersList = voterInfoDAO.getVoterCountByLevels(constituencyId, publicationId, locationLvs);
			   for(Object[] total:totalVotersList){
				   if(((Long)total[2]).longValue() == 3l){
					   panchayatIds.add((Long)total[0]);
					   totalVotersMap.put((Long)total[0],(Long)total[1]);
				   }else{
					   lebIds.add((Long)total[0]); 
					   totalVotersMap.put((Long)total[0],(Long)total[1]);
				   }
			   }
			   
			   populateNames(panchayatIds,lebIds,panchayatNames);
			   List<Object[]> casteList = voterCastInfoDAO.getVotersCastInfoByCasteIds(locationLvs, constituencyId, publicationId, 1l, new ArrayList<Long>(castePercents.keySet()));
			   
			    totalCastesList = getOrderOfPriorUsingCaste(castePercents,casteList,totalVotersList,mergePanchayatMap,mergeCasteMap,currentResult,"totalCaste",panchayatNames,totalVotersMap);
			 //total voters calculation ends  
			    
			    //getting partial panchayats
			   List<Object[]> partialPanchayatsList = partialBoothPanchayatDAO.getPartialPanchayatsList(constituencyId, publicationId);
			   List<Long> partialIds = new ArrayList<Long>();
			   for(Object[] partial:partialPanchayatsList){
					if(partial[0] !=null && !partialIds.contains((Long)partial[0])){
						partialIds.add((Long)partial[0]);
					}
					if(partial[1] !=null && !partialIds.contains((Long)partial[1])){
						partialIds.add((Long)partial[1]);
					}
			   }
			  
			  
			   if(partialIds.size() > 0){
				   //young voters calculation starts
				   List<Object[]> youngVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersPartialCaste = boothPublicationVoterDAO.getCasteCountForPartial(castePercents.keySet(), publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 18l, 22l);
				   List<Object[]> youngVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, 18l, 22l);
				   if(youngVotersCaste != null && youngVotersCaste.size() > 0){
					   youngCaste.addAll(youngVotersCaste);
				   }
				   if(youngVotersPartialCaste != null && youngVotersPartialCaste.size() > 0){
					   youngCaste.addAll(youngVotersPartialCaste);
				   }
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				   if(youngVotersPartialCount != null && youngVotersPartialCount.size() > 0){
					   youngTotal.addAll(youngVotersPartialCount);
				   }
				    
				   //young voters calculation ends
				  
                   //aged voters calculation starts
				   List<Object[]> agedVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, partialIds,  61l, 200l);
				   List<Object[]> agedVotersPartialCaste = boothPublicationVoterDAO.getCasteCountForPartial(castePercents.keySet(), publicationId, constituencyId, partialIds,  61l, 200l);
				   List<Object[]> agedVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 61l, 200l);
				   List<Object[]> agedVotersPartialCount = boothPublicationVoterDAO.getTotalVotersCountForPartial(publicationId, constituencyId, partialIds, 61l, 200l);
				   if(agedVotersCaste != null && agedVotersCaste.size() > 0){
					   ageCaste.addAll(agedVotersCaste);
				   }
				   if(agedVotersPartialCaste != null && agedVotersPartialCaste.size() > 0){
					   ageCaste.addAll(agedVotersPartialCaste);
				   }
				   if(agedVotersCount != null && agedVotersCount.size() > 0){
					   ageTotal.addAll(agedVotersCount);
				   }
				   if(agedVotersPartialCount != null && agedVotersPartialCount.size() > 0){
					   ageTotal.addAll(agedVotersPartialCount);
				   }
				   
				   //aged voters calculation ends
			   }else{

				   //young voters calculation starts
				   List<Object[]> youngVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, null, 18l, 22l);
				   List<Object[]> youngVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, null, 18l, 22l);
				   if(youngVotersCaste != null && youngVotersCaste.size() > 0){
					   youngCaste.addAll(youngVotersCaste);
				   } 
				   if(youngVotersCount != null && youngVotersCount.size() > 0){
					   youngTotal.addAll(youngVotersCount);
				   }
				  
				    
				   //young voters calculation ends
				   
                   //aged voters calculation starts
				    List<Object[]> agedVotersCaste = boothPublicationVoterDAO.getCasteCount(castePercents.keySet(), publicationId, constituencyId, partialIds, 61l, 200l);  
				    List<Object[]> agedVotersCount = boothPublicationVoterDAO.getTotalVotersByAge(publicationId, constituencyId, partialIds, 61l, 200l);   
				    if(agedVotersCaste != null && agedVotersCaste.size() > 0){
						 ageCaste.addAll(agedVotersCaste);
					 }
				    if(agedVotersCount != null && agedVotersCount.size() > 0){
						 ageTotal.addAll(agedVotersCount);
					 }
				    
				   //aged voters calculation ends
			   
			   }
			   if(constituency.getAreaType().equalsIgnoreCase("RURAL-URBAN")){
				   
				       List<Object[]> totalYoungVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId, 18l, 22l);
					  
					   List<Object[]> casteYoungVoterForMunic = boothPublicationVoterDAO.getCasteCountForMunicipality(castePercents.keySet(),publicationId,constituencyId, 18l, 22l);
					   if(casteYoungVoterForMunic != null && casteYoungVoterForMunic.size() > 0){
						   youngCaste.addAll(casteYoungVoterForMunic);
					   } 
					   if(totalYoungVoterForMunic != null && totalYoungVoterForMunic.size() > 0){
						   youngTotal.addAll(totalYoungVoterForMunic);
					   }
					   List<Object[]> totalAgeVoterForMunic = boothPublicationVoterDAO.getTotalVotersByAgeForMunicipality(publicationId,constituencyId, 18l, 22l);
						  
					   List<Object[]> casteAgeVoterForMunic = boothPublicationVoterDAO.getCasteCountForMunicipality(castePercents.keySet(),publicationId,constituencyId, 18l, 22l);
					   if(casteAgeVoterForMunic != null && casteAgeVoterForMunic.size() > 0){
							 ageCaste.addAll(casteAgeVoterForMunic);
						 }
					    if(totalAgeVoterForMunic != null && totalAgeVoterForMunic.size() > 0){
							 ageTotal.addAll(totalAgeVoterForMunic);
						 }
			   }
			   youngCastesList = getOrderOfPriorUsingCaste(castePercents,youngCaste,youngTotal,mergePanchayatMap,mergeCasteMap,currentResult,"youngCaste",panchayatNames,totalVotersMap);
			   agedCastesList = getOrderOfPriorUsingCaste(castePercents,ageCaste,ageTotal,mergePanchayatMap,mergeCasteMap,currentResult,"agedCaste",panchayatNames,totalVotersMap);
			
			
			priorityList.add(totalCastesList);
		    priorityList.add(youngCastesList);
		    priorityList.add(agedCastesList);
			return priorityList;
	  }
	 
	 public void getPanchayatsToMerge(Set<Long> mergePanchayatIds,Map<Long,Set<Long>> mergePanchayatMap,Long constituencyId,String type,Long typeId){
		 List<Object[]> mergePanchayatsList = strategyMergPancListDAO.getPanchayatsToMerge(constituencyId,type, typeId);
			for(Object[] mergePanchayat:mergePanchayatsList){
			   if(mergePanchayat[0] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[0]);
			   }
			   if(mergePanchayat[1] != null){
				 mergePanchayatIds.add((Long)mergePanchayat[1]);
			   }
			   
			   Set<Long> panchayats = mergePanchayatMap.get((Long)mergePanchayat[1]);
			   if(panchayats == null){
				   panchayats = new HashSet<Long>();
				   mergePanchayatMap.put((Long)mergePanchayat[1],panchayats);
				   panchayats.add((Long)mergePanchayat[1]);
			   }
			   panchayats.add((Long)mergePanchayat[0]);
			}
	 }
	 public List<PanchayatVO> getOrderOfPriorUsingCaste(Map<Long,Float> castePercents, List<Object[]> casteList,List<Object[]> totalVotersList,Map<Long,Set<Long>> mergePanchayatMap,Map<Long,Set<Long>> mergeCasteMap,Map<Long,Double> currentResult,String type,Map<Long,String> panchayatNames,Map<Long,Long> pancTotalVotersList){
		 Map<Long,PanchayatVO> totalCastePriorityMap = new HashMap<Long,PanchayatVO>();
		 
		 PanchayatVO panchayatVo = null;
		 Map<Long,Long> totalVotersMap = new HashMap<Long,Long>();
		 if(type.equalsIgnoreCase("totalCaste")){	
			 totalVotersMap = pancTotalVotersList;
		 }else{
			 for(Object[] count:totalVotersList){
				 totalVotersMap.put((Long)count[0],(Long)count[1]);
			 }
		 }
		 for(Object[] caste:casteList){
			 panchayatVo = totalCastePriorityMap.get((Long)caste[0]);
			 if(panchayatVo == null){
				 panchayatVo = new PanchayatVO();
				 panchayatVo.setPanchayatId((Long)caste[0]);
				 panchayatVo.setPanchayatName(panchayatNames.get((Long)caste[0]));
				 panchayatVo.setTotalPanchayatVoters(pancTotalVotersList.get((Long)caste[0]).intValue());
				 Map<Long,Long> casteMap = new HashMap<Long,Long>();
				 panchayatVo.setCasteMap(casteMap);
				 casteMap.put(0l, totalVotersMap.get((Long)caste[0]));
				 for(Long casteStateId:castePercents.keySet()){
					 if(casteStateId.longValue() != 0l){
					  casteMap.put(casteStateId, 0l);
					 }
				 }
				 totalCastePriorityMap.put((Long)caste[0],panchayatVo);
			 }
			 Map<Long,Long> casteMap = panchayatVo.getCasteMap();
			 casteMap.put((Long)caste[1],(Long)caste[2]);
			 casteMap.put(0l,casteMap.get(0l)-(Long)caste[2]);
			
		 }
		 if(mergePanchayatMap != null && mergePanchayatMap.size() > 0){
		     mergePanchayats(totalCastePriorityMap,mergePanchayatMap);
		     mergeTotalVoters(totalVotersMap,mergePanchayatMap);
		 }
		 if(mergeCasteMap != null && mergeCasteMap.size() > 0){
		     mergeCastes(totalCastePriorityMap,mergeCasteMap);
		 }
		 
		 for(Long panchayatId:totalCastePriorityMap.keySet()){
			 PanchayatVO panchayat = totalCastePriorityMap.get(panchayatId);
			 Map<Long,Long> casteMap = panchayat.getCasteMap();
			 for(Long casteStateId:casteMap.keySet()){
				 Long count = casteMap.get(casteStateId);
				 panchayat.setOthrExpctdVotes(panchayat.getOthrExpctdVotes()+Math.round(count*castePercents.get(casteStateId)));
			 }
			 panchayat.setCasteMap(null);
			 
			 
			 Long totalVoters = totalVotersMap.get(panchayatId);
			 if(totalVoters != null)
			   panchayat.setTotalVoters(totalVoters.intValue());
			 if(totalVoters > 0){
			   panchayat.setTargetPerc(new BigDecimal((panchayat.getOthrExpctdVotes()*100.0/totalVoters)).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 }else{
				 panchayat.setTargetPerc(0d);
			 }
			 
		   if(type.equalsIgnoreCase("totalCaste")){	 
			 Double partyPerc = currentResult.get(panchayatId);
			 if(partyPerc != null){
			    panchayat.setPartyPerc(partyPerc);
			 }else{
				 panchayat.setPartyPerc(0d);
			 }
			 panchayat.setDifferencePerc(panchayat.getTargetPerc()-panchayat.getPartyPerc());
			 panchayat.setOpportunity(panchayat.getPartyPerc()-panchayat.getTargetPerc());
		   } 
		 }
		 List<PanchayatVO> returnList = new ArrayList<PanchayatVO>(totalCastePriorityMap.values());
		 if(type.equalsIgnoreCase("totalCaste")){	
		    calculateForTotalCaste(returnList);
		 }else{
			 calculateForYoungOld(returnList); 
		 }
		 return returnList;
	 }
	 
	 public void calculateForTotalCaste(List<PanchayatVO> returnList){
		 Collections.sort(returnList,totalCasteSort);
		 if(returnList.get(returnList.size()-1).getDifferencePerc() < 0){
			 Double addValue = returnList.get(returnList.size()-1).getDifferencePerc()*(-1);
			 for(PanchayatVO vo:returnList){
				 vo.setDifferencePerc( vo.getDifferencePerc()+addValue);
				 vo.setVoterPoints(new BigDecimal((vo.getDifferencePerc()*100/returnList.get(0).getDifferencePerc())).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			 }
			 
		 }
	 }
	 
	 public void calculateForYoungOld(List<PanchayatVO> returnList){
		 Collections.sort(returnList,youngOldSort);
		 for(PanchayatVO vo:returnList){
			 vo.setVoterPoints(new BigDecimal((vo.getTargetPerc()*100/returnList.get(0).getTargetPerc())).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
	     }
	 }
	 
	 public void mergePanchayats(Map<Long,PanchayatVO> totalCastePriorityMap,Map<Long,Set<Long>> mergePanchayatMap){
		 for(Long key:mergePanchayatMap.keySet()){
		  if(totalCastePriorityMap.get(key) != null){
			 Map<Long,Long> casteMap = totalCastePriorityMap.get(key).getCasteMap();
			 for(Long panchayatId:mergePanchayatMap.get(key)){
				 if(totalCastePriorityMap.get(panchayatId) != null){
					 Map<Long,Long> mergeMap = totalCastePriorityMap.get(panchayatId).getCasteMap();
					 for(Long casteStateId:casteMap.keySet()){
						 casteMap.put(casteStateId,casteMap.get(casteStateId)+mergeMap.get(casteStateId));
					 }
				 }
				 totalCastePriorityMap.remove(panchayatId);
			 }
		  }
		 }
	 }
	 
     public void mergeCastes(Map<Long,PanchayatVO> totalCastePriorityMap,Map<Long,Set<Long>> mergeCasteMap){
    	for(Long panchayatId:totalCastePriorityMap.keySet()){
    		 Map<Long,Long> casteMap = totalCastePriorityMap.get(panchayatId).getCasteMap();
    		 for(Long casteStateId:mergeCasteMap.keySet()){
    			 Long mainCount = casteMap.get(casteStateId);
    			 if(mainCount != null){
	    			 for(Long mergeCasteStateId:mergeCasteMap.get(casteStateId)){
	    				if(casteMap.get(mergeCasteStateId) != null){
	    					casteMap.put(casteStateId,casteMap.get(casteStateId)+casteMap.get(mergeCasteStateId));
	    				}
	    			 }
    			 }
    		 }
    	}
	 }
     
     public void mergeTotalVoters(Map<Long,Long> totalVotersMap,Map<Long,Set<Long>> mergePanchayatMap){
    	 for(Long key:mergePanchayatMap.keySet()){
			 for(Long panchayatId:mergePanchayatMap.get(key)){
				 totalVotersMap.put(key,totalVotersMap.get(key)+totalVotersMap.get(panchayatId));
				 totalVotersMap.remove(panchayatId);
			 }
		 }
 	 }
     
       public  Comparator<PanchayatVO> totalCasteSort = new Comparator<PanchayatVO>()
	   {
			  
		  public int compare(PanchayatVO loc1, PanchayatVO loc2)
			{
			   return (loc2.getDifferencePerc().compareTo(loc1.getDifferencePerc()));
			}
	   };
     		  
	   public  Comparator<PanchayatVO> youngOldSort = new Comparator<PanchayatVO>()
	   {
			  
		  public int compare(PanchayatVO loc1, PanchayatVO loc2)
			{
			   return (loc2.getTargetPerc().compareTo(loc1.getTargetPerc()));
			}
	   };
     	
	   public  Comparator<PartyEffectVO> prpSort = new Comparator<PartyEffectVO>()
			   {
					  
				  public int compare(PartyEffectVO loc1, PartyEffectVO loc2)
					{
					   return (loc2.getDifference().compareTo(loc1.getDifference()));
					}
			   };
			   
       public void populateNames(List<Long> panchayatIds,List<Long> lebIds,Map<Long,String> panchayatNames){
    	   if(panchayatIds.size() > 0){
    		   List<Object[]> panchayatsList = panchayatDAO.getPanchayatsByPanchayatIdsList(panchayatIds);
    		   for(Object[] panchayat:panchayatsList){
    			   panchayatNames.put((Long)panchayat[0], panchayat[1].toString());
    		   }
    	   }
    	   if(lebIds.size() > 0){
    		   List<Object[]> panchayatsList = localElectionBodyDAO.getLocalElectionBodyNames(lebIds);
    		   for(Object[] panchayat:panchayatsList){
    			   panchayatNames.put((Long)panchayat[0], panchayat[1].toString());
    		   }
    	   }
       }
       
       public List<PartyEffectVO> calculateWeightsForPrpImpact(Map<Long,PartyEffectVO> prpEffect,Long publicationId){
    	   List<PartyEffectVO> prpList =  new ArrayList<PartyEffectVO>(prpEffect.values());
   		for(Long location:prpEffect.keySet()){
   			PartyEffectVO locationVo = prpEffect.get(location);
   			if(locationVo.getPrpCurrentPerc() != null && locationVo.getTdpCurrentPerc() != null && locationVo.getTdpPrevPerc() != null){
   				Double prpPerc = locationVo.getPrpCurrentPerc();
   				Double partyDiff = null;
   			
   					
   					Double partyPrev = locationVo.getTdpPrevPerc();
   					Double partyCurrent = locationVo.getTdpCurrentPerc();
   				    partyDiff = partyCurrent-partyPrev;
   				   if(partyDiff < 0){
   					partyDiff = partyDiff*(-1);
   					if(prpPerc > partyDiff){
   						locationVo.setDifference(partyDiff);
   					}else{
   						locationVo.setDifference(prpPerc);
   					}
   				}
   			}
   		}
   		for(Long location:prpEffect.keySet()){
   			PartyEffectVO locationVo = prpEffect.get(location);
   			if(locationVo.getDifference() == null){
   				locationVo.setDifference(0d);
   			}
   		}
   		
   		Collections.sort(prpList,prpSort);
   		
   			for(PartyEffectVO partyEffectVO:prpList){
   				if(prpList.get(0).getDifference() != null && prpList.get(0).getDifference() > 0){
   					partyEffectVO.setPoints(new BigDecimal(partyEffectVO.getDifference()*100/prpList.get(0).getDifference()).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
   					List<Object[]> panchayatVoterCount = voterCastInfoDAO.getTopThreeCasteForPanchayat(partyEffectVO.getId(),partyEffectVO.getReportLvl(),publicationId,1l);//Long panchayatId,Long reportId,Long publicationId,Long userId
					int i = 0;
					StringBuilder cast = new StringBuilder("");
					 for(Object[] panchayat:panchayatVoterCount){
						 if(i==3){
						 break;
						 }
						 cast.append("("+panchayat[0].toString());
						 cast.append(","+panchayat[2].toString());
						 cast.append(","+panchayat[1].toString()+")");
						 i++;
					 }
					 partyEffectVO.setCastes(cast.toString());
   				}
   		   }
   		return prpList;
   	}
	   public void getPrioritiesToTarget(StrategyVO strategyVO){
		   Map<Long,PartyEffectVO> partyEffect = new HashMap<Long,PartyEffectVO>();
		   Map<Long,Double> currentResult = new HashMap<Long,Double>();
		   
		   List<PanchayatVO> totalCastesList = null;
		   List<PanchayatVO> youngCastesList = null;
		   List<PanchayatVO> agedCastesList = null;
		   
		   boolean castePresent = false;
		   
		   List<PartyPositionVO>  previousTrends = getPartyPreviousTrends(strategyVO.getConstituencyId(),strategyVO.getPartyId(),strategyVO.getElectionIds(),partyEffect,strategyVO.getEffectPartyId(),strategyVO.getEffectElectionId(),currentResult);
		   List<PartyEffectVO> otherPartyEffect = calculateWeightsForPrpImpact(partyEffect,strategyVO.getPublicationId());
		   if(strategyVO.getCastePercents() != null){
			   castePresent = true;
			   List<Object> castePriorities = getTotalYoungOldVotersEffectWithCaste(strategyVO.getCastePercents(),strategyVO.getPublicationId(),strategyVO.getConstituencyId(),strategyVO.getMergeCasteMap(),currentResult);
			   totalCastesList = (List<PanchayatVO>)castePriorities.get(0);
			   youngCastesList = (List<PanchayatVO>)castePriorities.get(1);
			   agedCastesList  = (List<PanchayatVO>)castePriorities.get(2);
		   }else{
			   List<Object> voterPriorities = getYoungOldVotersEffectWithOutCaste(strategyVO.getPublicationId(),strategyVO.getConstituencyId());
			   youngCastesList = (List<PanchayatVO>)voterPriorities.get(0);
			   agedCastesList  = (List<PanchayatVO>)voterPriorities.get(1);
		   }
	   }
}
