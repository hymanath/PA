package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationWiseElectionInformationDetalsService;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.dto.GrivenceStatusVO;
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
			List<Long> partyIdList, List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes) {
		try{
			
			List<ElectionInformationVO> finalPartyList = new ArrayList<ElectionInformationVO>();
			
			Map<Long,List<ElectionInformationVO>> yearMap = new HashMap<Long, List<ElectionInformationVO>>();
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
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L ){
				validVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null);
				if(!commonMethodsUtilService.isListOrSetValid(validVoterList)){
					validVoterList = new ArrayList<Object[]>();
				}
				List<Object[]> parliamentWiseValidVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList);
				if(commonMethodsUtilService.isListOrSetValid(parliamentWiseValidVoterList))
					validVoterList.addAll(parliamentWiseValidVoterList);
			}else{
				validVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null);
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
			}
			List<Object[]> earnedVotesList = null;
			if(locationTypeId != null && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L ){
				earnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,partyIdList);
				if(!commonMethodsUtilService.isListOrSetValid(earnedVotesList))
					earnedVotesList = new ArrayList<Object[]>();
					
				List<Object[]> tempearnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList,partyIdList);
				if(commonMethodsUtilService.isListOrSetValid(tempearnedVotesList))
					earnedVotesList.addAll(tempearnedVotesList);
			}else{
				earnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,partyIdList);
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
		finalPartyList = setLocationWiseStatus(statusMap,locationMap);
		return finalPartyList;
			
		}catch(Exception e){
			Log.error("Exception raised in getElectionInformationLocationWiseVoterShare method of LocationDashboardService"+e);
			return null;
		}
		
	}

	private List<ElectionInformationVO> setLocationWiseStatus(Map<String, String> statusMap,Map<Long, ElectionInformationVO> locationMap) {
		List<ElectionInformationVO> finalList= new ArrayList<ElectionInformationVO>(0);
		 for (Entry<Long, ElectionInformationVO> entry : locationMap.entrySet()) {
			 if(commonMethodsUtilService.isListOrSetValid(entry.getValue().getList())){
				 for (ElectionInformationVO element : entry.getValue().getList()) {
					String percentage= calculatePercentage(element.getValidVoters(),element.getEarnedVotes());
					element.setPerc(percentage);
					for (Entry<String, String> innerentry : statusMap.entrySet()) {
						String val = innerentry.getKey();
						String[] valueArr = val.split("-");
						double d1 = Double.parseDouble(percentage);
						if(d1 >= Double.parseDouble(valueArr[0]) &&  d1 <= Double.parseDouble(valueArr[1])){
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
