package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterPartyInfo;
import com.itgrids.partyanalyst.service.IAttributeWiseElectionResultComparisonService;
import com.itgrids.partyanalyst.utils.IConstants;

public class AttributeWiseElectionResultComparisonService implements
		IAttributeWiseElectionResultComparisonService {
	private static final Logger LOG = Logger.getLogger(AttributeWiseElectionResultComparisonService.class);
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IHamletBoothElectionDAO hamletBoothElectionDAO;
	private ICandidateBoothResultDAO candidateBoothResultDAO;
	private IBoothDAO boothDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IPublicationDateDAO publicationDateDAO;
	private ITehsilDAO tehsilDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IVoterReportLevelDAO voterReportLevelDAO;
	private IVoterPartyInfoDAO voterPartyInfoDAO;
	
	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IHamletBoothElectionDAO getHamletBoothElectionDAO() {
		return hamletBoothElectionDAO;
	}

	public void setHamletBoothElectionDAO(
			IHamletBoothElectionDAO hamletBoothElectionDAO) {
		this.hamletBoothElectionDAO = hamletBoothElectionDAO;
	}

	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(
			ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public IVoterReportLevelDAO getVoterReportLevelDAO() {
		return voterReportLevelDAO;
	}

	public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
		this.voterReportLevelDAO = voterReportLevelDAO;
	}

	public IVoterPartyInfoDAO getVoterPartyInfoDAO() {
		return voterPartyInfoDAO;
	}

	public void setVoterPartyInfoDAO(IVoterPartyInfoDAO voterPartyInfoDAO) {
		this.voterPartyInfoDAO = voterPartyInfoDAO;
	}

	public PartyResultsVO getElectionResultsByAttributeWise(List<Long> electionIds,List<Long> partyIds,Long userId,Long constituencyId,String type,List<Long> ids,String attributeType,List<Long> attributeIds,Long attrPerc,Long publicationId){
		    List<Object[]> dataList = new ArrayList<Object[]>();//contains location wise attributes(caste,party etc) count
		    List<Object[]> countList = new ArrayList<Object[]>();//contains location wise  total count
		    PartyResultsVO partyResults = new PartyResultsVO();
		    Map<Long,Map<Long,PartyResultsVO>> attributesMap = new HashMap<Long,Map<Long,PartyResultsVO>>();//Map<attributeId,Map<locationId,count>>
		    Map<Long,String> attributeNames = new HashMap<Long,String>();//contains Map<attributeId,attributeName(ex caste name,party name)>
		    Map<Long,Map<Long,Long>> electionCount = new HashMap<Long,Map<Long,Long>>();
		    Map<String,List<Long>> votesMap = new HashMap<String,List<Long>>();
		    Map<String,List<Long>> attrMap = new HashMap<String,List<Long>>();
		    Map<String,List<BigDecimal>> votesPercMap = new HashMap<String,List<BigDecimal>>();
		    Map<String,List<BigDecimal>> attrPercMap = new HashMap<String,List<BigDecimal>>();
		    Map<Long,String> partyNames = new HashMap<Long,String>();
			Map<Long,String> electionNames = new HashMap<Long,String>();
			Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap = new HashMap<Long,Map<Long,Map<Long,PartyResultsVO>>>();//Map<electionId,Map<partyId,Map<panchayatid,panchayatObj>>>
			List<Long> locIds = new ArrayList<Long>();
			Map<Long,String> locationNames = new HashMap<Long,String>();
			
			//Long publicationId = publicationDateDAO.getLatestPublicationId();
			
		    if("mandal".equalsIgnoreCase(type)){
				List<Long> tehsilIds = new ArrayList<Long>();
				List<Long> lclBodyIds = new ArrayList<Long>();
				
				for(Long id:ids){
					if(id.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						//lclBodyIds.add(new Long(id.toString().substring(1)));
						 List<Object[]> assemblyLocalElectionBodyName = assemblyLocalElectionBodyDAO.getLocalElecBodyName(id.toString().substring(1));
						  Object[] reqName = assemblyLocalElectionBodyName.get(0);
						  String name = reqName[0].toString()+" "+reqName[1].toString();
						  locationNames.put((Long)reqName[2], name);
						  lclBodyIds.add((Long)reqName[2]);
					}else{
						tehsilIds.add(new Long(id.toString().substring(1)));
					}
				}
				
				
				//Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap = new HashMap<Long,Map<Long,Map<Long,PartyResultsVO>>>();//Map<electionId,Map<partyId,Map<mandalid,mandalObj>>>
				
				if(tehsilIds.size() > 0){
					List<Long> locationIds = getAttributesCount(dataList,countList,userId,constituencyId,publicationId,"mandal",tehsilIds,attributeType,attributeIds,attrPerc,attributesMap,attributeNames,locationNames,null);
					if(locationIds.size() > 0){
						List<Object[]> results =  candidateBoothResultDAO.getMandalResultsForElectionAndMandals(constituencyId,locationIds,electionIds,partyIds);
						List<Object[]> resultsCount =  candidateBoothResultDAO.getMandalCountForElectionAndMandals(constituencyId,locationIds,electionIds);
						populateDataToVos(electionCount,results,resultsCount,electionResultsMap,electionNames,partyNames,null,false);
						locIds.addAll(locationIds);
						for(Long locId:locationIds){
							locationNames.put(locId, tehsilDAO.getTehsilNameByTehsilId(locId));
						}
					}
				}
				
				if(lclBodyIds.size() > 0){
					List<Long> locationIds1 = getAttributesCount(dataList,countList,userId,constituencyId,publicationId,"localElectionBody",lclBodyIds,attributeType,attributeIds,attrPerc,attributesMap,attributeNames,locationNames,null);
					if(locationIds1.size() > 0){
						List<Object[]> results1 =  candidateBoothResultDAO.getLocalbodyResultsForElectionAndByIds(constituencyId,locationIds1,electionIds,partyIds);
						List<Object[]> resultsCount1 =  candidateBoothResultDAO.getLocalbodyCountForElectionAndByIds(constituencyId,locationIds1,electionIds);
						populateDataToVos(electionCount,results1,resultsCount1,electionResultsMap,electionNames,partyNames,null,false);
						locIds.addAll(locationIds1);
					}
				}
				
			}
			else if("panchayat".equalsIgnoreCase(type)){
				List<Long> locationIds = getAttributesCount(dataList,countList,userId,constituencyId,publicationId,type,ids,attributeType,attributeIds,attrPerc,attributesMap,attributeNames,locationNames,null);
				if(locationIds.size() > 0){
				    List<Object[]> list = hamletBoothElectionDAO.getPanchayatBoothDetailsByPanchayat(locationIds,electionIds);//Object[electionId,panchayatId,panchayatName,boothId,partNo]
				
					Map<Long,Long> boothIds = new HashMap<Long,Long>();//Map<boothId,panchayatId>
					
					for(Object[] boothsData:list){
						boothIds.put((Long)boothsData[3],(Long)boothsData[1]);
						locationNames.put((Long)boothsData[1], boothsData[2].toString());//Map<panchayatId,panchayatName>
					}
					
					locIds.addAll(locationIds);
					if(boothIds.size() > 0){
						List<Object[]> results =  candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionsForParties(boothIds.keySet(),electionIds,partyIds);
						 List<Object[]> resultsCount = candidateBoothResultDAO.findBoothCountForMultipleBoothsAndElectionsForParties(boothIds.keySet(),electionIds);
						populateDataToVos(electionCount,results,resultsCount,electionResultsMap,electionNames,partyNames,boothIds,true);
					}
				}
			}
			else if("booth".equalsIgnoreCase(type)){
				List<String> partNos = boothDAO.getPartNosForBooths(ids);
				List<Long> locationIds = getAttributesCount(dataList,countList,userId,constituencyId,publicationId,type,ids,attributeType,attributeIds,attrPerc,attributesMap,attributeNames,locationNames,partNos);
				 List<String> prtNos = new ArrayList<String>();
				if(locationIds.size() > 0){
					 for(Long locId:locationIds){
						 locationNames.put(locId, "Booth-"+locId);
						 prtNos.add(locId.toString());
					 }
					 List<Object[]> results = candidateBoothResultDAO.findBoothResultsForMultipleBoothsInElections(constituencyId,prtNos,electionIds,partyIds);
					 List<Object[]> resultsCount = candidateBoothResultDAO.findBoothCountForMultipleBoothsInElections(constituencyId,prtNos,electionIds);
					 //Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap = new HashMap<Long,Map<Long,Map<Long,PartyResultsVO>>>();//Map<electionId,Map<partyId,Map<boothId,boothObj>>>

					 populateDataToVos(electionCount,results,resultsCount,electionResultsMap,electionNames,partyNames,null,false);
					 locIds.addAll(locationIds);
				}
		    }
			else if("ward".equalsIgnoreCase(type)){
				List<Long> locationIds = getAttributesCount(dataList,countList,userId,constituencyId,publicationId,type,ids,attributeType,attributeIds,attrPerc,attributesMap,attributeNames,locationNames,null);
				if(locationIds.size() > 0){
					List<Object[]> list = boothConstituencyElectionDAO.getBoothIdsByWardIds(locationIds,electionIds,constituencyId);
					Map<Long,Long> boothIds = new HashMap<Long,Long>();
					
	
					for(Object[] boothsData:list){
						boothIds.put((Long)boothsData[2],(Long)boothsData[1]);
						locationNames.put((Long)boothsData[1],boothsData[3].toString());
					}
					//Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap = new HashMap<Long,Map<Long,Map<Long,PartyResultsVO>>>();//Map<electionId,Map<partyId,Map<panchayatid,panchayatObj>>>
					if(boothIds.size() > 0){
						List<Object[]> results =  candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionsForParties(boothIds.keySet(),electionIds,partyIds);
						List<Object[]> resultsCount = candidateBoothResultDAO.findBoothCountForMultipleBoothsAndElectionsForParties(boothIds.keySet(),electionIds);
						populateDataToVos(electionCount,results,resultsCount,electionResultsMap,electionNames,partyNames,boothIds,true);
						locIds.addAll(locationIds);
					}
				}
			}
		    //populating votes count(votesMap) and percent(votesPercMap) location wise
		    arrangeObjects(electionResultsMap,electionNames,partyNames,locIds,electionCount,votesMap,votesPercMap);
			partyResults.setLocationResults(votesMap);
			partyResults.setLocationPercnts(votesPercMap);
			//populating attributes count(attrMap) and percent(attrPercMap) location wise
		    getAttributeResults(attributesMap, attributeNames, locIds,attrMap,attrPercMap);
		    partyResults.setAttributeResults(attrMap);
		    partyResults.setAttributePercnts(attrPercMap);
		    Set<String> attrMapRemoveKeys = new HashSet<String>();
		    Set<String> attrPercMapRemoveKeys = new HashSet<String>();
		    for(String key:attrMap.keySet()){
		    	boolean noDataPresent = true;
		    	for(Long value:attrMap.get(key)){
		    		if(value != null){
		    			noDataPresent = false;
		    		}
		    	}
		    	if(noDataPresent){
		    		attrMapRemoveKeys.add(key);
		    	}
		    }
		    for(String key:attrPercMap.keySet()){
		    	boolean noDataPresent = true;
		    	for(BigDecimal value:attrPercMap.get(key)){
		    		if(value != null){
		    			noDataPresent = false;
		    		}
		    	}
		    	if(noDataPresent){
		    		attrPercMapRemoveKeys.add(key);
		    	}
		    }
		    
		    for(String key:attrMapRemoveKeys)
		    	attrMap.remove(key);
		    
		    for(String key:attrPercMapRemoveKeys)
		    	attrPercMap.remove(key);
		    
		    List<String> names = new ArrayList<String>();
		    for(Long locId:locIds){
		    	names.add(locationNames.get(locId));
		    }
		    partyResults.setLocationNames(names);
			return partyResults;
	}
	
	public List<Long> getAttributesCount(List<Object[]> dataList,List<Object[]> countList,Long userId,Long constituencyId,Long publicationId,String type,List<Long> ids,String attributeType,List<Long> attributeIds,Long attrPerc,Map<Long,Map<Long,PartyResultsVO>> attributesMap,Map<Long,String> attributeNames,Map<Long,String> locationNames,List<String> partNos){
		if("caste".equalsIgnoreCase(attributeType) || "party".equalsIgnoreCase(attributeType)){
			if("booth".equalsIgnoreCase(type)){
				dataList  = boothPublicationVoterDAO.getVoterCastAndPartyCountForDifferentLocations(userId,attributeIds,type,ids,constituencyId,publicationId,attributeType,partNos);
				countList = boothPublicationVoterDAO.getVoterCastAndPartyCount(userId,attributeIds,type,ids,constituencyId,publicationId,attributeType,partNos);
			}else{
				//getting data from intermediate table
				if("caste".equalsIgnoreCase(attributeType))
				  return getCasteWiseVotersCount(type,attributeIds,ids,constituencyId,publicationId,userId,attrPerc,attributesMap,attributeNames);
				else
				  return getPartyWiseVotersCount(type,attributeIds,ids,constituencyId,publicationId,userId,attrPerc,attributesMap,attributeNames);
			}
		}else{
			dataList  = boothPublicationVoterDAO.getVoterAttributeCountForDifferentLocations(userId,attributeIds,type,ids,constituencyId,publicationId,partNos);
			countList = boothPublicationVoterDAO.getVoterAttributeCount(userId,attributeIds,type,ids,constituencyId,publicationId,partNos);
		}
		List<Long> locationIds = compareDataWithPerc(dataList,countList,attrPerc);
		//Map<Long,Map<Long,Long>> attributesMap = new HashMap<Long,Map<Long,Long>>();//Map<attributeId,Map<locationId,count>>
		Map<Long,PartyResultsVO> locationMap = null;
		PartyResultsVO obj = null;
		Map<Long,Long> locationCountMap = new HashMap<Long,Long>();
		//Map<Long,String> attributeNames = new HashMap<Long,String>();
		for(Object[] totalVoters:countList){
			locationCountMap.put(new Long(totalVoters[1].toString().trim()),(Long)totalVoters[0]);
		}
		for(Object[] count:dataList){
			locationMap = attributesMap.get((Long)count[3]);
			if(locationMap == null){
				locationMap = new HashMap<Long,PartyResultsVO>();
				attributeNames.put((Long)count[3], count[4].toString());
				attributesMap.put((Long)count[3], locationMap);
				locationNames.put(new Long(count[1].toString().trim()), count[2].toString());
			}
			if(locationIds.contains(new Long(count[1].toString().trim()))){
				obj = locationMap.get(new Long(count[1].toString().trim()));
				if(obj == null){
					obj = new PartyResultsVO();
					obj.setValidVotes((Long)count[0]);
					if(locationCountMap.get(new Long(count[1].toString().trim())) != null && locationCountMap.get(new Long(count[1].toString().trim())) > 0 ){
						obj.setVotesPercent(new BigDecimal((Long)count[0]/locationCountMap.get(new Long(count[1].toString().trim())).doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					if(obj.getVotesPercent() == null || obj.getVotesPercent().longValue() < attrPerc){
						obj.setValidVotes(null);
						obj.setVotesPercent(null);
					}
					locationMap.put(new Long(count[1].toString().trim()), obj);
				}
			}
		}
		return locationIds;
	}
	
	public List<Long> compareDataWithPerc(List<Object[]> dataList,List<Object[]> countList,Long attrPerc){
		
		Map<Long,Long> countMap = new HashMap<Long,Long>();
		Set<Long> idsSet = new HashSet<Long>();
		List<Long> returnIds = new ArrayList<Long>();
		
		for(Object[] count : countList){
			  countMap.put(new Long(count[1].toString().trim()),(Long)count[0]);
		}
		
		for(Object[] data : dataList){
			Long id = new Long(data[1].toString().trim());
		  if(countMap.get(id) != null && countMap.get(id) > 0){
			if(new Float((Long)data[0]/countMap.get(id).doubleValue()*100).doubleValue() >= attrPerc){
				idsSet.add(id);
			}
		  }
		}
		returnIds.addAll(idsSet);
		return returnIds;
	}
	
	public void populateDataToVos(Map<Long,Map<Long,Long>> electionCount,List<Object[]> results,List<Object[]> resultsCount,Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap,Map<Long,String> electionNames,Map<Long,String> partyNames,Map<Long,Long> locationIds,boolean fromLocationIdsReq){
		Map<Long,Map<Long,PartyResultsVO>> partiesMap = null;
		Map<Long,PartyResultsVO> locationResultMap = null;
		Map<Long,Long> locationCount = null;
		for(Object[] result:results){
			
			partiesMap = electionResultsMap.get((Long)result[0]);
			
			if(partiesMap == null){
				electionNames.put((Long)result[0], result[6].toString()+" "+result[5].toString());
				partiesMap = new HashMap<Long,Map<Long,PartyResultsVO>>();
				electionResultsMap.put((Long)result[0], partiesMap);
			}
			locationResultMap = partiesMap.get((Long)result[2]);
			
			if(locationResultMap == null){
				locationResultMap = new HashMap<Long,PartyResultsVO>();
				partiesMap.put((Long)result[2], locationResultMap);
				partyNames.put((Long)result[2], result[3].toString());
			}
			PartyResultsVO location = null;
			if(fromLocationIdsReq)
				location = locationResultMap.get(locationIds.get(new Long(result[1].toString().trim())));
			else
				location = locationResultMap.get(new Long(result[1].toString().trim()));
			
			if(location == null){
				location = new PartyResultsVO();
				if(fromLocationIdsReq){
				 locationResultMap.put(locationIds.get(new Long(result[1].toString().trim())), location);
				 location.setPartyId(locationIds.get(new Long(result[1].toString().trim())));
				}else{
				 locationResultMap.put(new Long(result[1].toString().trim()), location);
				 location.setPartyId(new Long(result[1].toString().trim()));
				}
			}
			
			if(location.getVotesEarned() != null){
				location.setVotesEarned(location.getVotesEarned()+(Long)result[4]);
			}else{
				location.setVotesEarned((Long)result[4]);
			}
		}
		
		for(Object[] resultCount:resultsCount){
			locationCount = electionCount.get((Long)resultCount[0]);
			if(locationCount == null){
				locationCount = new HashMap<Long,Long>();
				electionCount.put((Long)resultCount[0], locationCount);
			}
			Long id = null;
			if(fromLocationIdsReq)
				id = locationIds.get(new Long(resultCount[1].toString().trim()));
			else
				id = new Long(resultCount[1].toString().trim());
				
			Long count = locationCount.get(id);
			if(count == null){
				locationCount.put(id, (Long)resultCount[2]);
			}else{
				locationCount.put(id, count+(Long)resultCount[2]);
			}
		}
	}
	
	public void populateTotalVotersToVo(List<Object[]> resultsCount,Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap){
		
	}
	
	public void arrangeObjects(Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap,Map<Long,String> electionNames,Map<Long,String> partyNames,List<Long> locIds,Map<Long,Map<Long,Long>> electionCount,Map<String,List<Long>> votesMap,Map<String,List<BigDecimal>> votesPercMap){
		Map<Long,Map<Long,PartyResultsVO>> partiesMap = null;
		Map<Long,PartyResultsVO> locationResultMap = null;
		List<PartyResultsVO> returnVal = new ArrayList<PartyResultsVO>();
		PartyResultsVO partyResultsVO = null;
		Map<Long,Long> locationsCountMap = null;
		for(Long electionId:electionResultsMap.keySet()){
			partiesMap = electionResultsMap.get(electionId);
			locationsCountMap = electionCount.get(electionId);
			for(Long partyId:partiesMap.keySet()){
				locationResultMap = partiesMap.get(partyId);
				partyResultsVO = new PartyResultsVO();
				partyResultsVO.setPartyName(partyNames.get(partyId)+" "+electionNames.get(electionId));
				List<PartyResultsVO> listVos = new ArrayList<PartyResultsVO>();
				for(Long panchayatId:locationResultMap.keySet()){
					PartyResultsVO locationVO = locationResultMap.get(panchayatId);
					listVos.add(locationVO);
					if(locationVO != null){
						locationVO.setTotalPolledVotes(locationsCountMap.get(panchayatId));
					}
				}
				partyResultsVO.setPartyResultsVOList(listVos);
				returnVal.add(partyResultsVO);
			}
			
		}
		for(PartyResultsVO vo:returnVal){
			List<PartyResultsVO> valuesList = vo.getPartyResultsVOList();
			if(valuesList != null && valuesList.size() > 0){
				List<Long> votesList = new ArrayList<Long>();
				List<BigDecimal> votesPercList = new ArrayList<BigDecimal>();
				for(Long id:locIds){
					Long votes = null;
					BigDecimal perc = null;
				   	for(PartyResultsVO value:valuesList){
				   		if(value.getPartyId().longValue() == id.longValue()){
				   			votes = value.getVotesEarned();
				   			if(votes != null && value.getTotalPolledVotes() != null && value.getTotalPolledVotes() >0)
				   				perc = new BigDecimal(votes/value.getTotalPolledVotes().doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP);
				   		}
				   	}
				   	votesList.add(votes);
				   	votesPercList.add(perc);
				}
				votesMap.put(vo.getPartyName()+" Votes",votesList);
				votesPercMap.put(vo.getPartyName()+" Percentage",votesPercList);
			}
		}
		
	}
	
	public void getAttributeResults(Map<Long,Map<Long,PartyResultsVO>> attributesMap,Map<Long,String> attributeNames,List<Long> locIds,Map<String,List<Long>> votesMap,Map<String,List<BigDecimal>> votesPercMap){
		
		Map<Long,PartyResultsVO> locationMap = null;
		for(Long key:attributesMap.keySet()){
			locationMap = attributesMap.get(key);
			if(locationMap != null && locationMap.size() > 0){
				List<Long> votesList = new ArrayList<Long>();
				List<BigDecimal> votesPercList = new ArrayList<BigDecimal>();
				for(Long id:locIds){
					PartyResultsVO partyResultsVO = locationMap.get(id);
					if(partyResultsVO != null){
					votesList.add(partyResultsVO.getValidVotes());
					votesPercList.add(partyResultsVO.getVotesPercent());
					}else{
						votesList.add(null);
						votesPercList.add(null);
					}
				}
				votesMap.put(attributeNames.get(key)+" Votes",votesList);
				votesPercMap.put(attributeNames.get(key)+" Percentage",votesPercList);
			}
		}
	}
	
	public List<Long> getCasteWiseVotersCount(String locationType,List<Long> attributeIds,List<Long> levelValues,Long constituencyId,Long publicationId,Long userId,Long attrPerc,Map<Long,Map<Long,PartyResultsVO>> attributesMap,Map<Long,String> attributeNames){
		
		Long levelId = getReportLevelId(locationType);
		Set<Long> locIds = new HashSet<Long>();
		Set<Long> ids = new HashSet<Long>();
		List<Long> returnIds = new ArrayList<Long>();
		ids.addAll(levelValues);
		List<VoterCastInfo> castInfoList = voterCastInfoDAO.getVotersCastInfoByMultipleLevelValuesAndCastIds(levelId,ids,attributeIds,constituencyId,publicationId,userId);
		for(VoterCastInfo castInfo:castInfoList){
			if(castInfo.getCastePercentage() >= attrPerc  ){
				locIds.add(castInfo.getReportLevelValue());
			}
		}
		Map<Long,PartyResultsVO> locationMap = null;
		PartyResultsVO obj = null;
		for(VoterCastInfo castInfo:castInfoList){
			locationMap = attributesMap.get(castInfo.getCasteState().getCasteStateId());
			if(locationMap == null){
				locationMap = new HashMap<Long,PartyResultsVO>();
				attributeNames.put(castInfo.getCasteState().getCasteStateId(),castInfo.getCasteState().getCaste().getCasteName());
				attributesMap.put(castInfo.getCasteState().getCasteStateId(), locationMap);
			}
			if(locIds.contains(castInfo.getReportLevelValue())){
				obj = locationMap.get(castInfo.getReportLevelValue());
				if(obj == null){
					obj = new PartyResultsVO();
					if(castInfo.getCastePercentage() != null && castInfo.getCastePercentage()  >= attrPerc){
					  obj.setValidVotes(castInfo.getCasteVoters());
					  obj.setVotesPercent(castInfo.getCastePercentage()!=null?new BigDecimal(castInfo.getCastePercentage()):null);
					}
					 locationMap.put(castInfo.getReportLevelValue(), obj);
				}
			}
		}
		returnIds.addAll(locIds);
	 return returnIds;
	}
	
    public List<Long> getPartyWiseVotersCount(String locationType,List<Long> attributeIds,List<Long> levelValues,Long constituencyId,Long publicationId,Long userId,Long attrPerc,Map<Long,Map<Long,PartyResultsVO>> attributesMap,Map<Long,String> attributeNames){
		
		Long levelId = getReportLevelId(locationType);
		Set<Long> locIds = new HashSet<Long>();
		List<Long> returnIds = new ArrayList<Long>();
		
		List<VoterPartyInfo> partyInfoList = voterPartyInfoDAO.getVotersPartyInfoByMultipleLevelValuesAndPartyIds(levelId,levelValues,attributeIds,constituencyId,publicationId,userId);
		for(VoterPartyInfo partyInfo:partyInfoList){
			if(attrPerc >= partyInfo.getPartyPercentage()){
				locIds.add(partyInfo.getReportLevelValue());
			}
		}
		Map<Long,PartyResultsVO> locationMap = null;
		PartyResultsVO obj = null;
		for(VoterPartyInfo partyInfo:partyInfoList){
			locationMap = attributesMap.get(partyInfo.getParty().getPartyId());
			if(locationMap == null){
				locationMap = new HashMap<Long,PartyResultsVO>();
				attributeNames.put(partyInfo.getParty().getPartyId(),partyInfo.getParty().getShortName());
				attributesMap.put(partyInfo.getParty().getPartyId(), locationMap);
			}
			if(locIds.contains(partyInfo.getReportLevelValue())){
				obj = locationMap.get(partyInfo.getReportLevelValue());
				if(obj == null){
					obj = new PartyResultsVO();
					obj.setValidVotes(partyInfo.getPartyVoters());
					obj.setVotesPercent(partyInfo.getPartyPercentage()!=null?new BigDecimal(partyInfo.getPartyPercentage()):null);
					locationMap.put(partyInfo.getReportLevelValue(), obj);
				}
			}
		}
		returnIds.addAll(locIds);
	 return returnIds;
	}
    
	public Long getReportLevelId(String type)
	 {
		 Long reportLevelId = 0l;
		 try{
			 if(type.equalsIgnoreCase("localElectionBody"))
				 type = IConstants.LOCALELECTIONBODY;
			 
			 reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
			 return reportLevelId;
		 }catch (Exception e) {
			LOG.error("Exception Occured in getReportLevelId() Method, Exception - "+e);
			return reportLevelId;
		}
	 }
}
