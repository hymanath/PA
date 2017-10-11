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
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocationWiseElectionInformationDetalsService implements ILocationWiseElectionInformationDetalsService {

	private final static Logger LOG = Logger.getLogger(LocationWiseElectionInformationDetalsService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private ICandidateDAO candidateDAO;

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
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L  ){
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
					tempYearVO.setValidVoters(0L);
					tempYearVO.setEarnedVotes(0L);
					tempYearVO.setStatus("WORST");
					electionYeasrMap.put(tempYearVO.getElectionId(), tempYearVO);
				}
			}
			List<Object[]> earnedVotesList = null;
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L ){
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
								return o2.getElectionId().compareTo(o1.getElectionId());
							}
						});
					}
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(partyResultList)){
				finalPartyList = buildSummaryForElectionResult(partyResultList,statusMap);
			}
			
		/*	List<ElectionInformationVO> partyResultList = setLocationWiseStatus(statusMap,locationMap);
		*/
		return finalPartyList;
			
		}catch(Exception e){
			Log.error("Exception raised in getElectionInformationLocationWiseVoterShare method of LocationWiseElectionInformationDetalsService"+e);
			return null;
		}
		
	}

	public List<ElectionInformationVO> buildSummaryForElectionResult(List<ElectionInformationVO> finalPartyList,Map<String,String> statusMap){
		List<ElectionInformationVO> resultList = new ArrayList<ElectionInformationVO>(0);
		try {
			
			if(commonMethodsUtilService.isListOrSetValid(finalPartyList)){
				Map<Long,Map<String,Long>> yearWiseStatusCountMap = new HashMap<Long,Map<String, Long>>(0);
				for (ElectionInformationVO locationVO : finalPartyList) {
					if(commonMethodsUtilService.isMapValid(statusMap)){
						Map<String,Long>  statusCountMap= new HashMap<String, Long>(0);
						
						if(commonMethodsUtilService.isListOrSetValid(locationVO.getList())){
							for (ElectionInformationVO electionVO : locationVO.getList()) {
								for (String range : statusMap.keySet()) {
									statusCountMap = yearWiseStatusCountMap.get(electionVO.getElectionId());
									if(!commonMethodsUtilService.isMapValid(statusCountMap))
										statusCountMap = new HashMap<String, Long>(0);
									if(statusCountMap.get(electionVO.getStatus()) == null)
										statusCountMap.put(statusMap.get(range), 0L);
									
									yearWiseStatusCountMap.put(electionVO.getElectionId(), statusCountMap);
								}
								
								statusCountMap = yearWiseStatusCountMap.get(electionVO.getElectionId());
								if(commonMethodsUtilService.isMapValid(statusCountMap)){
									Long count = statusCountMap.get(electionVO.getStatus());
									if(count == null)
										count=0L;
									count = count+1L;
									statusCountMap.put(electionVO.getStatus(),count);
								}
							}
						}
					}
					resultList.add(locationVO);
				}
				
				if(commonMethodsUtilService.isListOrSetValid(resultList)){
					for (ElectionInformationVO locatinVO : resultList) {
						if(commonMethodsUtilService.isListOrSetValid(locatinVO.getList())){
							ElectionInformationVO electionVO = locatinVO.getList().get(0);
							if(electionVO != null){
								Map<String,Long>  statusCountMap= yearWiseStatusCountMap.get(electionVO.getElectionId());
								if(commonMethodsUtilService.isMapValid(statusCountMap)){
									if(!commonMethodsUtilService.isListOrSetValid(electionVO.getSubList1())){
										for (String range : statusMap.keySet()) {
											electionVO.getSubList1().add(new ElectionInformationVO(statusMap.get(range),range.trim(),statusCountMap.get(statusMap.get(range))));
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
			Log.error("Exception raised in buildSummaryForelectionResult method of LocationWiseElectionInformationDetalsService"+e);
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
					for (Entry<String, String> innerentry : statusMap.entrySet()) {
						String val = innerentry.getKey();
						String[] valueArr = val.split("-");
						String d1 = formatter.format(Double.parseDouble(percentage));
						if(Double.parseDouble(d1) >= Double.parseDouble(valueArr[0]) &&  Double.parseDouble(d1) <= Double.parseDouble(valueArr[1])){
							element.setStatus(innerentry.getValue());
						}
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
	
}
