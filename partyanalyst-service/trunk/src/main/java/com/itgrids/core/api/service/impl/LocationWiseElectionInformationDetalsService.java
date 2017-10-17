package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationWiseElectionInformationDetalsService;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocationWiseElectionInformationDetalsService implements ILocationWiseElectionInformationDetalsService {

	private final static Logger LOG = Logger.getLogger(LocationWiseElectionInformationDetalsService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private ICandidateDAO candidateDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IPartyDAO partyDAO;

	
	
	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}

	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	@Override
	public List<ElectionInformationVO> getElectionInformationLocationWiseStatus(Long locationTypeId,Long locationValue, 
			List<Long> partyIdList, List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType) {
		try{
			
			List<ElectionInformationVO> finalPartyList = new ArrayList<ElectionInformationVO>();
			Map<Long,List<Long>> electionIdAndLocationIdListMap=new HashMap<Long, List<Long>>();
			Map<Long,List<ElectionInformationVO>> yearMap = new HashMap<Long, List<ElectionInformationVO>>();
			Map<Long,ElectionInformationVO> electionYeasrMap = new HashMap<Long, ElectionInformationVO>();
			if(electionYrs !=null && electionYrs.size()>0l){
				for (Long year : electionYrs) {
					List<ElectionInformationVO> listEvo =yearMap.get(year);
					if(listEvo == null){
						listEvo = new ArrayList<ElectionInformationVO>();
					}
					ElectionInformationVO evo= new ElectionInformationVO();
					evo.setElectionYear(year.toString());
					listEvo.add(evo);
					yearMap.put(year, listEvo);
				}
			}
			
			Map<String,String> statusMap = new HashMap<String, String>();
			statusMap.put("0-30","WORST");
			statusMap.put("31-50","VERY POOR");
			statusMap.put("51-60","POOR");
			statusMap.put("61-80","OK");
			statusMap.put("81-90","STRONG");
			statusMap.put("91-100","VERY STRONG");

			List<Long> parliamentIdsList = new ArrayList<Long>(0);
			if(locationTypeId != null && locationTypeId.longValue()==10L)
				parliamentIdsList.add(locationValue);
			else{
				String[] parliamentIdsArr = IConstants.AP_PARLIAMENT_IDS_LIST;
				if(parliamentIdsArr != null && parliamentIdsArr.length>0){
					for (int i = 0; i < parliamentIdsArr.length; i++) {
						parliamentIdsList.add(Long.valueOf(parliamentIdsArr[i].trim()));
					}
				}
			}
			List<Object[]> validVoterList= null;
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L && locationTypeId.longValue() !=10L){
				if(!searchType.equalsIgnoreCase("Parliament"))
				validVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,searchType);
				if(!commonMethodsUtilService.isListOrSetValid(validVoterList)){
					validVoterList = new ArrayList<Object[]>();
				}if(electionScopeIds.contains(1l) || searchType.equalsIgnoreCase("Parliament")){
					List<Object[]> parliamentWiseValidVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList,null);
					if(commonMethodsUtilService.isListOrSetValid(parliamentWiseValidVoterList))
						validVoterList.addAll(parliamentWiseValidVoterList);
				}
			}else{
				validVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,searchType);
			}
			Map<Long, ElectionInformationVO> locationMap= new HashMap<Long, ElectionInformationVO>();
			for (Object[] objects : validVoterList) {	
				ElectionInformationVO yearVo =	locationMap.get(commonMethodsUtilService.getLongValueForObject(objects[5]));
				if(yearVo == null){
					List<ElectionInformationVO> yearList = new ArrayList<ElectionInformationVO>();
					yearVo = new ElectionInformationVO();
					ElectionInformationVO innerEvo = new ElectionInformationVO();
					yearVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[5]));
					yearVo.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[6]));
					
					innerEvo.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[0]));
					innerEvo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(objects[1]));
					innerEvo.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[2]));
					innerEvo.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					innerEvo.setValidVoters(commonMethodsUtilService.getLongValueForObject(objects[4]));
					yearList.add(innerEvo);
					yearVo.setList(yearList);
					locationMap.put(commonMethodsUtilService.getLongValueForObject(objects[5]), yearVo);
				} else {
					if (commonMethodsUtilService.isListOrSetValid(yearVo.getList())) {
						ElectionInformationVO innerEvo = getMatchedLocationVO(yearVo.getList(),commonMethodsUtilService.getLongValueForObject(objects[5]));
						if (innerEvo != null) {
							innerEvo.setValidVoters(commonMethodsUtilService.getLongValueForObject(objects[4])+ innerEvo.getValidVoters());
						} else {
							innerEvo = new ElectionInformationVO();
							innerEvo.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[0]));
							innerEvo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(objects[1]));
							innerEvo.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[2]));
							innerEvo.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
							innerEvo.setValidVoters(commonMethodsUtilService.getLongValueForObject(objects[4]));
							yearVo.getList().add(innerEvo);
						}
					}
				}
				
				ElectionInformationVO tempYearVO=  electionYeasrMap.get(commonMethodsUtilService.getLongValueForObject(objects[3]));
				if(tempYearVO==null){
					tempYearVO = new ElectionInformationVO();
					tempYearVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(objects[0]));
					tempYearVO.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(objects[1]));
					tempYearVO.setElectionType(commonMethodsUtilService.getStringValueForObject(objects[2]));
					tempYearVO.setElectionId(commonMethodsUtilService.getLongValueForObject(objects[3]));
					tempYearVO.setValidVoters(commonMethodsUtilService.getLongValueForObject(objects[4]));
					tempYearVO.setEarnedVotes(0L);
					electionYeasrMap.put(tempYearVO.getElectionId(), tempYearVO);
				}
			}
			List<Object[]> earnedVotesList = null;
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L && locationTypeId.longValue() !=10L){
				if(!searchType.equalsIgnoreCase("Parliament"))
				earnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,partyIdList,searchType);
				if(!commonMethodsUtilService.isListOrSetValid(earnedVotesList))
					earnedVotesList = new ArrayList<Object[]>();
				if(electionScopeIds.contains(1l) || searchType.equalsIgnoreCase("Parliament")){	
					List<Object[]> tempearnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList,partyIdList,null);
					if(commonMethodsUtilService.isListOrSetValid(tempearnedVotesList))
						earnedVotesList.addAll(tempearnedVotesList);
				}
			}else{
				earnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,partyIdList,searchType);
			}
			for (Object[] param : earnedVotesList) {
				
				List<Long> locationList=electionIdAndLocationIdListMap.get(commonMethodsUtilService.getLongValueForObject(param[5]));
				if(!commonMethodsUtilService.isListOrSetValid(locationList))
					locationList =new ArrayList<Long>();
				
				locationList.add(commonMethodsUtilService.getLongValueForObject(param[7]));
				electionIdAndLocationIdListMap.put(commonMethodsUtilService.getLongValueForObject(param[5]), locationList);
				
				ElectionInformationVO yearVo= locationMap.get(commonMethodsUtilService.getLongValueForObject(param[7]));
				if(yearVo == null){
					List<ElectionInformationVO> yearList = new ArrayList<ElectionInformationVO>();
					yearVo = new ElectionInformationVO();
					ElectionInformationVO innerEvo = new ElectionInformationVO();
					yearVo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[7]));
					yearVo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[8]));
					
					innerEvo.setPartyId(commonMethodsUtilService.getLongValueForObject(param[0]));
					innerEvo.setPartyName(commonMethodsUtilService.getStringValueForObject(param[1]));
					innerEvo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
					innerEvo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[3]));
					innerEvo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
					innerEvo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[5]));
					innerEvo.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6]));
					yearList.add(innerEvo);
					yearVo.setList(yearList);
					locationMap.put(commonMethodsUtilService.getLongValueForObject(param[7]), yearVo);
				}else{
					if(commonMethodsUtilService.isListOrSetValid(yearVo.getList())){
							ElectionInformationVO innerEvo = getMatchedLocationVO(yearVo.getList(),commonMethodsUtilService.getLongValueForObject(param[5]));
							if (innerEvo != null) {
								innerEvo.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6])+innerEvo.getEarnedVotes());
							}else{
								innerEvo = new ElectionInformationVO();
								innerEvo.setPartyId(commonMethodsUtilService.getLongValueForObject(param[0]));
								innerEvo.setPartyName(commonMethodsUtilService.getStringValueForObject(param[1]));
								innerEvo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
								innerEvo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[3]));
								innerEvo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
								innerEvo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[5]));
								innerEvo.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6]));
								yearVo.getList().add(innerEvo);
							}
							
						
					}
					
				}
				
			}
			List<ElectionInformationVO> partyResultList = setLocationWiseStatus(statusMap,locationMap);
			if(commonMethodsUtilService.isListOrSetValid(partyResultList)){
				for (ElectionInformationVO locationVO : partyResultList) {
					List<Long> availableElectionIdsList = new ArrayList<Long>(0);
					if(commonMethodsUtilService.isListOrSetValid(locationVO.getList())){
						for (ElectionInformationVO yearVO : locationVO.getList()) {
							availableElectionIdsList.add(yearVO.getElectionId());
						}
					}
					if(commonMethodsUtilService.isMapValid(electionYeasrMap)){
						for (Long electionId : electionYeasrMap.keySet()) {
							if(!availableElectionIdsList.contains(electionId))
								locationVO.getList().add(electionYeasrMap.get(electionId));
						}
					}
					
					if(commonMethodsUtilService.isListOrSetValid(locationVO.getList())){
						Collections.sort(locationVO.getList(), new Comparator<ElectionInformationVO>() {
							public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
								return Long.valueOf(o2.getElectionYear()).compareTo(Long.valueOf(o1.getElectionYear()));
							}
						});
					}
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(partyResultList)){
				finalPartyList = buildSummaryForElectionResult(partyResultList,statusMap,electionIdAndLocationIdListMap);
			}
		return finalPartyList;
			
		}catch(Exception e){
			Log.error("Exception raised in getElectionInformationLocationWiseVoterShare method of LocationWiseElectionInformationDetalsService"+e);
			return null;
		}
		
	}

	public List<ElectionInformationVO> buildSummaryForElectionResult(List<ElectionInformationVO> finalPartyList,Map<String,String> statusMap,Map<Long,List<Long>> electionIdAndLocationIdListMap){
		List<ElectionInformationVO> resultList = new ArrayList<ElectionInformationVO>(0);
		try {
	
			if(commonMethodsUtilService.isListOrSetValid(finalPartyList)){
				Map<Long,Map<String,Long>> yearWiseStatusCountMap = new HashMap<Long,Map<String, Long>>(0);
				for (ElectionInformationVO locationVO : finalPartyList) {
					
					if(commonMethodsUtilService.isMapValid(statusMap)){
						Map<String,Long>  statusCountMap= new HashMap<String, Long>(0);
						
						if(commonMethodsUtilService.isListOrSetValid(locationVO.getList())){
							for (ElectionInformationVO electionVO : locationVO.getList()) {
								
								if(electionVO.getStatus() != null && electionVO.getStatus().length()>0){
									List<Long> lcoationIdsList = electionIdAndLocationIdListMap.get(electionVO.getElectionId());
									if(yearWiseStatusCountMap.get(electionVO.getElectionId()) == null){
										for (String range : statusMap.keySet()) {
											statusCountMap = yearWiseStatusCountMap.get(electionVO.getElectionId());
											if(!commonMethodsUtilService.isMapValid(statusCountMap))
												statusCountMap = new HashMap<String, Long>(0);
											if(statusCountMap.get(electionVO.getStatus()) == null)
												statusCountMap.put(statusMap.get(range.trim()), 0L);
											
											yearWiseStatusCountMap.put(electionVO.getElectionId(), statusCountMap);
										}
									}
									
									statusCountMap = yearWiseStatusCountMap.get(electionVO.getElectionId());
									if(commonMethodsUtilService.isMapValid(statusCountMap)){
										Long count = statusCountMap.get(electionVO.getStatus().trim());
										if(count == null)
											count=0L;
										// in 2004 we have 226 assembly, but in 2014 175 . so am not adding  the counts and
										// status details which are not available in this locations,
										if(lcoationIdsList !=null && lcoationIdsList.size() >0 && lcoationIdsList.contains(locationVO.getLocationId()))
											count = count+1L;

										statusCountMap.put(electionVO.getStatus().trim(),count);
										
									}
								}
							}
						}
					}
					resultList.add(locationVO);
				}
				
				if(commonMethodsUtilService.isListOrSetValid(resultList)){
					for (ElectionInformationVO locatinVO : resultList) {
						if(commonMethodsUtilService.isListOrSetValid(locatinVO.getList())){
								for (ElectionInformationVO electionVO : locatinVO.getList()) {
								if(electionVO != null){
									Map<String,Long>  statusCountMap= yearWiseStatusCountMap.get(electionVO.getElectionId());
									if(commonMethodsUtilService.isMapValid(statusCountMap)){
										if(!commonMethodsUtilService.isListOrSetValid(electionVO.getSubList1())){
											for (String range : statusMap.keySet()) {
												electionVO.getSubList1().add(new ElectionInformationVO(statusMap.get(range),range.trim(),statusCountMap.get(statusMap.get(range))));
											}
											
											Collections.sort(electionVO.getSubList1(), new Comparator<ElectionInformationVO>() {
												public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
													String[] o1Arr = o1.getRange().split("-");
													String[] o2Arr = o2.getRange().split("-");
													return Long.valueOf(o1Arr[0].trim()).compareTo(Long.valueOf(o2Arr[0].trim()));
												}
											});											
										}
									}
								}
							}
						}
					}
				}
			}
			
			return resultList;
		} catch (Exception e) {
			Log.error("Exception raised in buildSummaryForelectionResult method of LocationWiseElectionInformationDetalsService",e);
			return null;
		}
	}
	private List<ElectionInformationVO> setLocationWiseStatus(Map<String, String> statusMap,Map<Long, ElectionInformationVO> locationMap) {
		List<ElectionInformationVO> finalList= new ArrayList<ElectionInformationVO>(0);
		DecimalFormat formatter = new DecimalFormat("0");
		 for (Entry<Long, ElectionInformationVO> entry : locationMap.entrySet()) {
			 if(commonMethodsUtilService.isListOrSetValid(entry.getValue().getList())){
				 for (ElectionInformationVO element : entry.getValue().getList()) {
					String percentage= calculatePercentage(element.getValidVoters(),element.getEarnedVotes());
					element.setPerc(percentage);
					if(element.getEarnedVotes() != null && element.getEarnedVotes().longValue()>0){
						for (Entry<String, String> innerentry : statusMap.entrySet()) {
							String val = innerentry.getKey();
							String[] valueArr = val.split("-");
							Long perc = Math.round(Double.parseDouble(percentage));
							if(Double.parseDouble(perc.toString()) >= Double.parseDouble(valueArr[0].trim()) &&  Double.parseDouble(perc.toString()) <= Double.parseDouble(valueArr[1].trim())){
								element.setStatus(innerentry.getValue().trim());break;
							}
						}
					}else{
						element.setStatus("NOT PARTICIPATED".trim());
					}
				}
			 }
			 finalList.add(entry.getValue());
		 }
		return finalList;
	}

	public String calculatePercentage(Long totalVoters,Long count)
	{
		try{
			if(totalVoters != null && totalVoters.longValue() > 0l && count != null && count.longValue()>0L)
			  return (new BigDecimal((count * 100.0)/totalVoters.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
			else{
				return "0";
			}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in calculatePercentage() method, Exception - ",e);
		}
		return null;
	}
	
	public ElectionInformationVO getMatchedLocationVO(List<ElectionInformationVO> list,Long id){
		try {
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (ElectionInformationVO electionInformationVO : list) {
					if(electionInformationVO.getElectionId().longValue() == id.longValue()){
						return electionInformationVO;
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised at getMatchedVO service"+e);
		}
		return null;
	}
	
	/**
	* @author Hymavathi G 
	* @Description :Cross Voting Details For Location
	*  @since 14-oct-2017
	*  @return :void
	*/
	public ElectionInformationVO getLocationWiseCrossVotingDetails(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,
			List<Long> partyids,String withAlliance,Long levelId,List<Long> locationVals,List<String> subtypes){
		
		ElectionInformationVO returnVO = new ElectionInformationVO();
		try {
			Map<Long,ElectionInformationVO> yearsPolledMap = new HashMap<Long,ElectionInformationVO>();
			if(parliamentIds != null && parliamentIds.size() > 0 && assemlyIds.size() == 0){
				List<Object[]> findAssembliesConstituencies = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliaments(parliamentIds);
				if(commonMethodsUtilService.isListOrSetValid(findAssembliesConstituencies)){
					for (Object[] param : findAssembliesConstituencies) {
						assemlyIds.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
			List<Long> locAssmblyVals = new ArrayList<Long> ();
			if(levelId != null && levelId.longValue() == 10l){
				List<Object[]> findAssembliesConstituencies = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliaments(locationVals);
				//locationVals.clear();
				if(commonMethodsUtilService.isListOrSetValid(findAssembliesConstituencies)){
					for (Object[] param : findAssembliesConstituencies) {
						locAssmblyVals.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
			List<Long> locParliamntVals = new ArrayList<Long> ();
			if(levelId != null && levelId.longValue() == 10l){
				List<Object[]> findAssembliesConstituencies = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.getAllParliamentConstituencyByAllLevels(null,locationVals,levelId,null);
				//locationVals.clear();
				if(commonMethodsUtilService.isListOrSetValid(findAssembliesConstituencies)){
					for (Object[] param : findAssembliesConstituencies) {
						locParliamntVals.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
			List<Object[]> assemlyPolledVotes = null;
			List<Object[]> assemblyEarnedVotes = null;
			if(levelId != null && levelId.longValue() == 10l){
				assemlyPolledVotes = boothConstituencyElectionDAO.getLocationWiseAssemblyElectionPolledVotes(electionYrs, parliamentIds, assemlyIds, levelId, locAssmblyVals,subtypes,2l);
				assemblyEarnedVotes = boothConstituencyElectionDAO.getLocationwiseAssemblyEarnedVotes(electionYrs, parliamentIds, assemlyIds, partyids, levelId, locAssmblyVals, subtypes, 2l);
			}else{
				assemlyPolledVotes = boothConstituencyElectionDAO.getLocationWiseAssemblyElectionPolledVotes(electionYrs, parliamentIds, assemlyIds, levelId, locationVals,subtypes,2l);
				assemblyEarnedVotes = boothConstituencyElectionDAO.getLocationwiseAssemblyEarnedVotes(electionYrs, parliamentIds, assemlyIds, partyids, levelId, locationVals, subtypes, 2l);
			}
			List<Object[]> parliamentPolledVoters = null;
			List<Object[]> parlearnedVotes = null;
			if(levelId != null && levelId.longValue() == 4l){
				parliamentPolledVoters = boothConstituencyElectionDAO.getLocationWiseAssemblyElectionPolledVotes(electionYrs, parliamentIds, assemlyIds, levelId, locParliamntVals,subtypes,1l);
				parlearnedVotes = boothConstituencyElectionDAO.getLocationwiseAssemblyEarnedVotes(electionYrs, parliamentIds, assemlyIds, partyids, levelId, locParliamntVals, subtypes, 1l);
			}else{
				 parliamentPolledVoters = boothConstituencyElectionDAO.getLocationWiseAssemblyElectionPolledVotes(electionYrs, parliamentIds, assemlyIds, levelId, locationVals,subtypes,1l);
				  parlearnedVotes = boothConstituencyElectionDAO.getLocationwiseAssemblyEarnedVotes(electionYrs, parliamentIds, assemlyIds, partyids, levelId, locationVals, subtypes, 1l);
			}
			//List<Object[]> assemlyPolledVotes = boothConstituencyElectionDAO.getLocationWiseAssemblyElectionPolledVotes(electionYrs, parliamentIds, assemlyIds, levelId, locationVals,subtypes,2l);
			//List<Object[]> parliamentPolledVoters = boothConstituencyElectionDAO.getLocationWiseAssemblyElectionPolledVotes(electionYrs, parliamentIds, assemlyIds, levelId, locParliamntVals,subtypes,1l);
			
			setTemplateForParties(partyids);
			setPolledVotesPerElectionYear(assemlyPolledVotes,yearsPolledMap,"assembly",partyids);
			setPolledVotesPerElectionYear(parliamentPolledVoters,yearsPolledMap,"parliament",partyids);
			//List<Object[]> assemblyEarnedVotes = boothConstituencyElectionDAO.getLocationwiseAssemblyEarnedVotes(electionYrs, parliamentIds, assemlyIds, partyids, levelId, locationVals, subtypes, 2l);
			//List<Object[]> parlearnedVotes = boothConstituencyElectionDAO.getLocationwiseAssemblyEarnedVotes(electionYrs, parliamentIds, assemlyIds, partyids, levelId, locationVals, subtypes, 1l);
			
			setEarnedVotesPercentagePerPartyInYear(assemblyEarnedVotes,yearsPolledMap,"assembly");
			setEarnedVotesPercentagePerPartyInYear(parlearnedVotes,yearsPolledMap,"parliament");
			if(commonMethodsUtilService.isMapValid(yearsPolledMap)){
				
				returnVO.getSubList1().addAll(yearsPolledMap.values());
			}
			
			if(commonMethodsUtilService.isListOrSetValid(returnVO.getSubList1())){
				Collections.sort(returnVO.getSubList1(), new Comparator<ElectionInformationVO>() {
					public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
						return Long.valueOf(o2.getElectionYear()).compareTo(Long.valueOf(o1.getElectionYear()));
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at getLocationWiseCrossVotingDetails service"+e);
		}
		return returnVO;
	}
	public List<ElectionInformationVO> setTemplateForParties(List<Long> partyids){
		List<ElectionInformationVO> returnList = new ArrayList<ElectionInformationVO>();
		try{
			List<Object[]> parties = partyDAO.getPartyShortNameByIds(partyids);
			if(commonMethodsUtilService.isListOrSetValid(parties)){
				for (Object[] param : parties) {
					//ElectionInformationVO partyVO = getMatchedVO(yearVO.getList(),partyId.longValue());
					ElectionInformationVO partyVO = new ElectionInformationVO();
						partyVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[0]));
						partyVO.setPartyFlag(commonMethodsUtilService.getStringValueForObject(param[2]));
						partyVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[1]));
						returnList.add(partyVO);
					}
				}
			
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at setTemplateForParties service"+e);
		}
		
		return returnList;
	}
	public void setPolledVotesPerElectionYear(List<Object[]> polledVotes,Map<Long,ElectionInformationVO> yearsPolledMap,String type,List<Long> partyids){
		try{
			
			if(commonMethodsUtilService.isListOrSetValid(polledVotes)){
				for (Object[] param : polledVotes) {
					ElectionInformationVO yearVO = yearsPolledMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(yearVO == null){
						yearVO = new ElectionInformationVO();
						yearVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[0]));
						yearsPolledMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), yearVO);
						yearVO.getList().addAll(setTemplateForParties(partyids));
					}
					if(type != null &&type.equalsIgnoreCase("assembly")){
						yearVO.setAssemblyValidVoters(commonMethodsUtilService.getLongValueForObject(param[1]));
					}else{
						yearVO.setParliamentValidVoters(commonMethodsUtilService.getLongValueForObject(param[1]));
					}
					//if(type != null &&type.equalsIgnoreCase("assembly")){
					
					/*if(commonMethodsUtilService.isListOrSetValid(partyids)){
						for (Long partyId : partyids) {
							ElectionInformationVO partyVO = getMatchedVO(yearVO.getList(),partyId.longValue());
							if(partyVO == null){
								partyVO = new ElectionInformationVO();
								partyVO.setPartyId(partyId);
								yearVO.getList().add(partyVO);
							}
						}
					}*/
				//	}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at setPolledVotesPerElectionYear service"+e);
		}
	}
	public void setEarnedVotesPercentagePerPartyInYear(List<Object[]> earnedVoters,Map<Long,ElectionInformationVO> yearsPolledMap,String type){
		try{
			
			if(commonMethodsUtilService.isListOrSetValid(earnedVoters)){
				for (Object[] param : earnedVoters) {
					ElectionInformationVO yearVO = yearsPolledMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(yearVO != null){
						ElectionInformationVO partyVO = getMatchedVO(yearVO.getList(),commonMethodsUtilService.getLongValueForObject(param[2]));
						
							if(partyVO != null){
								//partyVO.setPartyFlag(commonMethodsUtilService.getStringValueForObject(param[4]));
								//partyVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[5]));
								//partyVO.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[3]));
								if(type != null &&type.equalsIgnoreCase("assembly")){
									partyVO.setAssemblyEarndVotes(commonMethodsUtilService.getLongValueForObject(param[3]));
								}else{
									partyVO.setParliamentEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[3]));
								}
								if(type != null &&type.equalsIgnoreCase("assembly")){
									partyVO.setEarnedVotersPerc(Double.valueOf(calculatePercentage(yearVO.getAssemblyValidVoters(), partyVO.getAssemblyEarndVotes())));
								}else{
									partyVO.setEarnedVotersPerc1(Double.valueOf(calculatePercentage(yearVO.getParliamentValidVoters(), partyVO.getParliamentEarnedVotes())));
									Double parliamentPerc = partyVO.getEarnedVotersPerc1() != null ?partyVO.getEarnedVotersPerc1():0.0;
									Double assmblyPerc = partyVO.getEarnedVotersPerc() != null ?partyVO.getEarnedVotersPerc():0.0;
									Double diffPerc = parliamentPerc-assmblyPerc;
									partyVO.setPerc(diffPerc.toString());
								}
								
								
							
						}
					}
					/*if(type != null &&type.equalsIgnoreCase("assembly")){
						yearVO.setAssemblyValidVoters(commonMethodsUtilService.getLongValueForObject(param[1]));
					}else{
						yearVO.setParliamentValidVoters(commonMethodsUtilService.getLongValueForObject(param[1]));
					}*/
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at setPolledVotesPerElectionYear service"+e);
		}
	}
	
	public ElectionInformationVO getMatchedVO(List<ElectionInformationVO> list,Long id){
		try {
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (ElectionInformationVO electionInformationVO : list) {
					if(electionInformationVO.getPartyId().longValue() == id.longValue()){
						return electionInformationVO;
					}
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised at getMatchedVO service"+e);
		}
		return null;
	}
}
