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
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IHamletBoothElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
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
	private IPanchayatDAO panchayatDAO;
	private IConstituencyDAO constituencyDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
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

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public PartyResultsVO getElectionResultsByAttributeWise(List<Long> electionIds,List<Long> partyIds,Long userId,Long constituencyId,String type,List<Long> ids,String attributeType,List<Long> attributeIds,Long attrPerc,Long publicationId){
		    List<Object[]> dataList = new ArrayList<Object[]>();//contains location wise attributes(caste,party etc) count
		    List<Object[]> countList = new ArrayList<Object[]>();//contains location wise  total count
		    PartyResultsVO partyResults = new PartyResultsVO();
		    Map<Long,Map<Long,PartyResultsVO>> attributesMap = new HashMap<Long,Map<Long,PartyResultsVO>>();//Map<attributeId,Map<locationId,count>>
		    Map<Long,String> attributeNames = new HashMap<Long,String>();//contains Map<attributeId,attributeName(ex caste name,party name)>
		    Map<Long,Map<Long,Long>> electionCount = new HashMap<Long,Map<Long,Long>>();//Map<electionId,Map<locationId(panchayat booth),total polled votes in location>>
		    Map<String,List<Long>> votesMap = new HashMap<String,List<Long>>();//Map<party name,list<votes secure in all locations>> ex: Map<inc in assembly 2009 votes,List<2000,5000,600(votes secure in all locations)>>
		    Map<String,List<Long>> attrMap = new HashMap<String,List<Long>>();//Map<attribute name,list<total voters in all locations for this attribute>> ex: Map<Reddy voters kapu voters etc,List<2000,5000,600(voters in all locations)>>
		    Map<String,List<BigDecimal>> votesPercMap = new HashMap<String,List<BigDecimal>>();//Map<party name,list<votes secure % in all locations>> ex: Map<inc in assembly 2009 percentage,List<30.20,20.12,45.00(votes % in all locations)>>
		    Map<String,List<BigDecimal>> attrPercMap = new HashMap<String,List<BigDecimal>>();//Map<attribute name,list<total % in all locations for this attribute>> ex: Map<Reddy voters percentage kapu voters percentage etc,List<25.00,31.36,32.00(% in all locations)>>
		    Map<Long,String> partyNames = new HashMap<Long,String>();//Map<partyId,PartyName>
			Map<Long,String> electionNames = new HashMap<Long,String>();//Map<electionId,ElectionName> ex Map<38,Assembly 2009>
			Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap = new HashMap<Long,Map<Long,Map<Long,PartyResultsVO>>>();//Map<electionId,Map<partyId,Map<panchayatid,panchayatObj>>>
			List<Long> locIds = new ArrayList<Long>();
			Map<Long,String> locationNames = new HashMap<Long,String>(); //contains Map<locationId,location Name>
			Map<Long,Map<Long,Long>> totalVotersMap = new HashMap<Long,Map<Long,Long>>();//contains totalvoters locations wise Map<electionId,Map<locationId,totalVoters in that location>>
			Map<String,List<Long>> polledVotesMap = new HashMap<String, List<Long>>();
			Map<String,List<Long>> totalVotesMap = new HashMap<String, List<Long>>();
			Map<String, List<BigDecimal>> polledVotesPercentageMap = new HashMap<String, List<BigDecimal>>();
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
						List<Object[]> list = hamletBoothElectionDAO.getBoothIdsByMandalIdsElectionIds(locationIds,electionIds,constituencyId);
						Map<Long,Long> boothIds = new HashMap<Long,Long>();//Map<boothId,panchayatId>
						
						for(Object[] boothsData:list){
							boothIds.put((Long)boothsData[0],(Long)boothsData[1]);
							locationNames.put((Long)boothsData[1], boothsData[2].toString());//Map<panchayatId,panchayatName>
							Map<Long,Long> elecMap = totalVotersMap.get((Long)boothsData[4]);
							if(elecMap == null){
								elecMap = new HashMap<Long,Long>();
								totalVotersMap.put((Long)boothsData[4], elecMap);
							}
							Long total = elecMap.get((Long)boothsData[1]);
							if(total == null){
								elecMap.put((Long)boothsData[1],(Long)boothsData[3]);
							}else{
								elecMap.put((Long)boothsData[1],total+(Long)boothsData[3]);
							}
						}
						if(boothIds.size() > 0){
							List<Object[]> results =  candidateBoothResultDAO.findBoothResultsForMultipleBoothsAndElectionsForParties(boothIds.keySet(),electionIds,partyIds);
							List<Object[]> resultsCount = candidateBoothResultDAO.findBoothCountForMultipleBoothsAndElectionsForParties(boothIds.keySet(),electionIds);
							populateDataToVos(electionCount,results,resultsCount,electionResultsMap,electionNames,partyNames,boothIds,true);
							locIds.addAll(locationIds);
						}
					}
				}
				
				if(lclBodyIds.size() > 0){
					List<Long> locationIds1 = getAttributesCount(dataList,countList,userId,constituencyId,publicationId,"localElectionBody",lclBodyIds,attributeType,attributeIds,attrPerc,attributesMap,attributeNames,locationNames,null);
					if(locationIds1.size() > 0){
						List<Object[]> results1 =  candidateBoothResultDAO.getLocalbodyResultsForElectionAndByIds(constituencyId,locationIds1,electionIds,partyIds);
						List<Object[]> resultsCount1 =  candidateBoothResultDAO.getLocalbodyCountForElectionAndByIds(constituencyId,locationIds1,electionIds);
						getAllLocalBodyLocationVoters(totalVotersMap,constituencyId,lclBodyIds,electionIds,"localElectionBody",null);
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
						Map<Long,Long> elecMap = totalVotersMap.get((Long)boothsData[0]);
						if(elecMap == null){
							elecMap = new HashMap<Long,Long>();
							totalVotersMap.put((Long)boothsData[0], elecMap);
						}
						Long total = elecMap.get((Long)boothsData[1]);
						if(total == null){
							elecMap.put((Long)boothsData[1],(Long)boothsData[5]);
						}else{
							elecMap.put((Long)boothsData[1],total+(Long)boothsData[5]);
						}
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
					 getAllLocalBodyLocationVoters(totalVotersMap,constituencyId,null,electionIds,"booth",prtNos);
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
						Map<Long,Long> elecMap = totalVotersMap.get((Long)boothsData[0]);
						if(elecMap == null){
							elecMap = new HashMap<Long,Long>();
							totalVotersMap.put((Long)boothsData[0], elecMap);
						}
						Long total = elecMap.get((Long)boothsData[1]);
						if(total == null){
							elecMap.put((Long)boothsData[1],(Long)boothsData[4]);
						}else{
							elecMap.put((Long)boothsData[1],total+(Long)boothsData[4]);
						}

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
		    if("booth".equalsIgnoreCase(type)){
		    	Collections.sort(locIds);
		    }else if("ward".equalsIgnoreCase(type)){
		      try{
			    	List<SelectOptionVO> vos = new ArrayList<SelectOptionVO>();
				    for(Long id:locIds){
				    	
				    	SelectOptionVO vo = new SelectOptionVO(id,locationNames.get(id).replace("WARD-","").trim());
				    	vos.add(vo);
				    }
				    Collections.sort(vos,selectOptionVOSort);
				    List<Long> orderedLocIds = new ArrayList<Long>();
				    for(SelectOptionVO option:vos){
				    	orderedLocIds.add(option.getId());
				    }
				    locIds = orderedLocIds;
		        }catch(Exception e){
		        	
		        }
		    }else{
			    List<SelectOptionVO> vos = new ArrayList<SelectOptionVO>();
			    for(Long id:locIds){
			    	
			    	SelectOptionVO vo = new SelectOptionVO(id,locationNames.get(id));
			    	vos.add(vo);
			    }
			    Collections.sort(vos);
			    List<Long> orderedLocIds = new ArrayList<Long>();
			    for(SelectOptionVO option:vos){
			    	orderedLocIds.add(option.getId());
			    }
			    locIds = orderedLocIds;
		    }
		    //populating votes count(votesMap) and percent(votesPercMap) location wise
		    arrangeObjects(electionResultsMap,electionNames,partyNames,locIds,electionCount,votesMap,votesPercMap,totalVotersMap,polledVotesMap,totalVotesMap,polledVotesPercentageMap);
		    partyResults.setLocationResults(votesMap);
			partyResults.setLocationPercnts(votesPercMap);
			
			//populating attributes count(attrMap) and percent(attrPercMap) location wise
		    getAttributeResults(attributesMap, attributeNames, locIds,attrMap,attrPercMap);
		    partyResults.setAttributeResults(attrMap);
		    partyResults.setAttributePercnts(attrPercMap);
		    
		    partyResults.setTotalVotes(totalVotesMap);
		    partyResults.setPolledVotes(polledVotesMap);
		    partyResults.setPolledVotesPercnts(polledVotesPercentageMap);
		    
		    //starting process of removing data from maps if all the locations for a election contains null values
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
		    //ending the process of removing data from maps if all the locations for a election contains null values
		    
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
				  return getCasteWiseVotersCount(type,attributeIds,ids,constituencyId,publicationId,userId,attrPerc,attributesMap,attributeNames,locationNames);
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
	
	public void arrangeObjects(Map<Long,Map<Long,Map<Long,PartyResultsVO>>> electionResultsMap,Map<Long,String> electionNames,Map<Long,String> partyNames,List<Long> locIds,Map<Long,Map<Long,Long>> electionCount,Map<String,List<Long>> votesMap,Map<String,List<BigDecimal>> votesPercMap,Map<Long,Map<Long,Long>> totalVotersMap,Map<String,List<Long>> polledVotesMap,Map<String,List<Long>> totalVotesMap,Map<String,List<BigDecimal>> polledVotesPercntsMap){
		Map<Long,Map<Long,PartyResultsVO>> partiesMap = null;
		Map<Long,PartyResultsVO> locationResultMap = null;
		List<PartyResultsVO> returnVal = new ArrayList<PartyResultsVO>();
		PartyResultsVO partyResultsVO = null;
		Map<Long,Long> locationsCountMap = null;
		Map<Long,Long> locationsTotalVotCountMap = null;
		for(Long electionId:electionResultsMap.keySet()){
			partiesMap = electionResultsMap.get(electionId);
			locationsCountMap = electionCount.get(electionId);
			locationsTotalVotCountMap = totalVotersMap.get(electionId);
			for(Long partyId:partiesMap.keySet()){
				locationResultMap = partiesMap.get(partyId);
				partyResultsVO = new PartyResultsVO();
				partyResultsVO.setPartyName(partyNames.get(partyId)+" "+electionNames.get(electionId));
				partyResultsVO.setType(electionNames.get(electionId) );
				List<PartyResultsVO> listVos = new ArrayList<PartyResultsVO>();
				for(Long panchayatId:locationResultMap.keySet()){
					PartyResultsVO locationVO = locationResultMap.get(panchayatId);
					listVos.add(locationVO);
					if(locationVO != null){
						locationVO.setTotalPolledVotes(locationsCountMap.get(panchayatId));
						if(locationsTotalVotCountMap != null)
							locationVO.setBallotVotes(locationsTotalVotCountMap.get(panchayatId));
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
				List<Long> votesLocPolledVotesList = new ArrayList<Long>();
				List<Long> votesLocTotalVotesList = new ArrayList<Long>();
				List<BigDecimal> votesPercList = new ArrayList<BigDecimal>();
				List<BigDecimal> polledVotesPercList = new ArrayList<BigDecimal>();
				for(Long id:locIds){
					Long votes = null;
					BigDecimal perc = null;
					Long totalPolledvotes = null;
					Long totalVoters = null;
					BigDecimal polledVotesPerc = null;
				   	for(PartyResultsVO value:valuesList){
				   		if(value.getPartyId().longValue() == id.longValue()){
				   			votes = value.getVotesEarned();
				   			totalPolledvotes = value.getTotalPolledVotes();
				   			totalVoters = value.getBallotVotes();
				   			if(votes != null && value.getTotalPolledVotes() != null && value.getTotalPolledVotes() >0)
				   				perc = new BigDecimal(votes/value.getTotalPolledVotes().doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP);
				   			if(totalVoters != null && totalPolledvotes != null && totalVoters >0)
				   				polledVotesPerc = new BigDecimal(totalPolledvotes/totalVoters.doubleValue()*100).setScale(2, BigDecimal.ROUND_HALF_UP);
				   		}
				   	}
				   	votesList.add(votes);
				   	votesLocPolledVotesList.add(totalPolledvotes);
				   	votesLocTotalVotesList.add(totalVoters);
				   	votesPercList.add(perc);
				   	polledVotesPercList.add(polledVotesPerc);
				}
				votesMap.put(vo.getPartyName()+" Votes",votesList);
				//votesMap.put("Polled Votes In "+vo.getType(),votesLocPolledVotesList);
				polledVotesMap.put("Polled Votes In "+vo.getType(),votesLocPolledVotesList);
				//votesMap.put("Total Voters In "+vo.getType(),votesLocTotalVotesList);
				totalVotesMap.put("Total Voters In "+vo.getType(),votesLocTotalVotesList);
				//votesPercMap.put("Polled Votes Percentage In "+vo.getType(),polledVotesPercList);
				polledVotesPercntsMap.put("Polled Votes Percentage In "+vo.getType(),polledVotesPercList);
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
	
	public List<Long> getCasteWiseVotersCount(String locationType,List<Long> attributeIds,List<Long> levelValues,Long constituencyId,Long publicationId,Long userId,Long attrPerc,Map<Long,Map<Long,PartyResultsVO>> attributesMap,Map<Long,String> attributeNames,Map<Long,String> locationNames){
		
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
		returnIds.addAll(locIds);
		if(locIds.size() > 0){
			List<Object[]> names = null;
			if("Mandal".equalsIgnoreCase(locationType)){
				names = tehsilDAO.getTehsilNameByTehsilIdsList(returnIds);
			}else if("Panchayat".equalsIgnoreCase(locationType)){
				names = panchayatDAO.getPanchayatsByPanchayatIdsList(returnIds);
			}else if("Ward".equalsIgnoreCase(locationType)){
				names = constituencyDAO.getConstituencyNameByConstituencyIdsList(returnIds);
			}else if("localElectionBody".equalsIgnoreCase(locationType)){
				names = localElectionBodyDAO.findByLocalElecBodyIds(returnIds);
			}
			if(names != null){
				for(Object[] name:names){
					if(!"localElectionBody".equalsIgnoreCase(locationType))
					  locationNames.put((Long)name[0], name[1].toString());
					else
					  locationNames.put((Long)name[0], name[1].toString()+" "+name[2].toString());
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
		}
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
	
	public void getAllLocalBodyLocationVoters(Map<Long,Map<Long,Long>> totalVotersMap,Long constituencyId,List<Long> lclBodyIds, List<Long> electionIds,String type,List<String> partNos){
		Map<Long,Map<Long,List<Long>>> boothsInLocation = new HashMap<Long,Map<Long,List<Long>>>();
		List<Object[]> electionWiseBooths = candidateBoothResultDAO.getLocalbodyBoothIdsForElectionAndByIds(constituencyId,lclBodyIds,electionIds,type,partNos);
		
		for(Object[] booth:electionWiseBooths){
			Map<Long,List<Long>> locationBooths = boothsInLocation.get((Long)booth[0]);
			if(locationBooths == null){
				locationBooths = new HashMap<Long,List<Long>>();
				boothsInLocation.put((Long)booth[0],locationBooths);
			}
			List<Long> booths = locationBooths.get(new Long(booth[1].toString().trim()));
			if(booths == null){
				booths = new ArrayList<Long>();
				locationBooths.put(new Long(booth[1].toString().trim()),booths);
			}
			booths.add((Long)booth[2]);
		}
		
		for(Long electionId:boothsInLocation.keySet()){
			Map<Long,Long> elecMap = totalVotersMap.get(electionId);
			if(elecMap == null){
				elecMap = new HashMap<Long,Long>();
				totalVotersMap.put(electionId, elecMap);
			}
			Map<Long,List<Long>> locationMap = boothsInLocation.get(electionId);
			for(Long locationId:locationMap.keySet()){
				List<Long> boothIds = locationMap.get(locationId);
				Long totalVoters = boothDAO.getTotalVotersInBooths(boothIds);
				elecMap.put(locationId, totalVoters);
			}
		}
		
	}
	
	public static Comparator<SelectOptionVO> selectOptionVOSort = new Comparator<SelectOptionVO>()
			{
						  
					  public int compare(SelectOptionVO vo1, SelectOptionVO vo2)
						{
						   return (new Long(vo1.getName()).intValue()) - (new Long(vo2.getName()).intValue());
						}
			};
}
