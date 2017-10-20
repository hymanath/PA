package com.itgrids.core.api.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.core.api.service.ILocationWiseElectionInformationDetalsService;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IMarginVotesRangeDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.hibernate.DelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dto.BaseCandidateResultVO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class LocationWiseElectionInformationDetalsService implements ILocationWiseElectionInformationDetalsService {

	private final static Logger LOG = Logger.getLogger(LocationWiseElectionInformationDetalsService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private ICandidateDAO candidateDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IPartyDAO partyDAO;
    private IMarginVotesRangeDAO marginVotesRangeDAO;
	private DelimitationConstituencyDAO delimitationConstituencyDAO;

	private INominationDAO nominationDAO;

	private ICandidateBoothResultDAO candidateBoothResultDAO;

	private ILocalElectionBodyDAO localElectionBodyDAO;

	private IElectionDAO electionDAO;
	
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	
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
	
	

	public DelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	public void setDelimitationConstituencyDAO(
			DelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}


	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	
	public ICandidateBoothResultDAO getCandidateBoothResultDAO() {
		return candidateBoothResultDAO;
	}

	public void setCandidateBoothResultDAO(ICandidateBoothResultDAO candidateBoothResultDAO) {
		this.candidateBoothResultDAO = candidateBoothResultDAO;
	}

	
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IMarginVotesRangeDAO getMarginVotesRangeDAO() {
		return marginVotesRangeDAO;
	}

	public void setMarginVotesRangeDAO(IMarginVotesRangeDAO marginVotesRangeDAO) {
		this.marginVotesRangeDAO = marginVotesRangeDAO;
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
			List<Object[]> marginWiseStatusObjs = marginVotesRangeDAO.getMarginVotesAgeRangeDetails();
			if(marginWiseStatusObjs != null && marginWiseStatusObjs.size() >0){
				for(Object[] objs : marginWiseStatusObjs){
					
					statusMap.put(commonMethodsUtilService.getStringValueForObject(objs[0]),commonMethodsUtilService.getStringValueForObject(objs[1]));
					}
				}
			
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
			if(levelId != null && levelId.longValue() == 4l){
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
			setTemplateForParties(partyids);
			setPolledVotesPerElectionYear(assemlyPolledVotes,yearsPolledMap,"assembly",partyids);
			setPolledVotesPerElectionYear(parliamentPolledVoters,yearsPolledMap,"parliament",partyids);
			
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
								if(type != null &&type.equalsIgnoreCase("assembly")){
									partyVO.setAssemblyEarndVotes(commonMethodsUtilService.getLongValueForObject(param[3]));
								}else{
									partyVO.setParliamentEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[3]));
								}
								if(type != null &&type.equalsIgnoreCase("assembly")){
									partyVO.setEarnedVotersPerc(Double.valueOf(calculatePercentage(yearVO.getAssemblyValidVoters(), partyVO.getAssemblyEarndVotes())));
								}else{
									partyVO.setEarnedVotersPerc1(Double.valueOf(calculatePercentage(yearVO.getAssemblyValidVoters(), partyVO.getParliamentEarnedVotes())));
									Double parliamentPerc = partyVO.getEarnedVotersPerc1() != null ?partyVO.getEarnedVotersPerc1():0.0;
									Double assmblyPerc = partyVO.getEarnedVotersPerc() != null ?partyVO.getEarnedVotersPerc():0.0;
									Double diffPerc = assmblyPerc-parliamentPerc;
									partyVO.setPerc(diffPerc.toString());
								}
								
								
							
						}
					}
					
					
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

/***
 * 
 * 
 * Booth wise Results
 * 
 * 
 */
	@Override
	public List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId, Long electionYears,Long electionScopeId) {
		
		try{
			List<PartyBoothPerformanceVO> boothResultsForParties = new ArrayList<PartyBoothPerformanceVO>();
			Long locationTypeId=2l, locationValue=1l; 
			String level = null;
			if(locationTypeId==5l || locationTypeId==6l || locationTypeId == 7l){
				level ="lowLevel";
			}
			List<Long> constituencyIds = new ArrayList<Long>();
			constituencyIds.add(constituencyId);
			
			List<Long> electionIds = electionDAO.getElectionDetailsByYearAndElectionTypeForLocation(String.valueOf(electionYears), electionScopeId,1L);
			List<Object[]> wincandidatesList = nominationDAO.findWonCandidateInConstituency(constituencyIds,String.valueOf(electionYears),null);
			
			List<BaseCandidateResultVO> basicWonCandidateVOList = new ArrayList<BaseCandidateResultVO>(0);
			
			if(wincandidatesList != null && wincandidatesList.size()>0)
			{
				for (Object[] param : wincandidatesList) 
				{
					BaseCandidateResultVO vo = new BaseCandidateResultVO();
					
					vo.setCandidateId(param[0] != null ?(Long) param[0]:0L);
					vo.setCandidateName(param[1] != null ? param[1].toString():"");
					vo.setPartyId(param[2] != null ?(Long) param[2]:0L);
					vo.setPartyName(param[3] != null ? param[3].toString():"");
					vo.setReservation(param[4] != null ? param[4].toString():"");
					vo.setVotesEarned(param[5] != null ? Double.valueOf(param[5].toString()):Double.valueOf("0"));
					vo.setMarginVotes(param[6] != null ? Double.valueOf(param[6].toString()):Double.valueOf("0"));
					vo.setVotesPercengate(param[7] != null ? param[7].toString():"0");
					vo.setRank(1L);
					vo.setWonStatus("won");
					
					basicWonCandidateVOList.add(vo);
				}
			}
			
			//	List<Object[]> boothResult = candidateBoothResultDAO.findLocationWiseBoothWisePollingPercentage(partyIds,locationTypeId,locationValue,electionYears,level,electionScopeId);
			
			List<Object[]> boothWiseWonPartyList = candidateBoothResultDAO.locationWisefindboothWiseResultsForNominators(constituencyId,electionYears);
			
			Map<Long,String> boothwiseWonPartyMap = new HashMap<Long, String>();
			
			if(boothWiseWonPartyList != null && boothWiseWonPartyList.size()>0)
			{
				for (int i=0; i<boothWiseWonPartyList.size();i++ ) 
				{
					Object[] param = boothWiseWonPartyList.get(i);
					if(boothwiseWonPartyMap.get(param[1] != null ? Long.valueOf(param[1].toString().trim()):0L) == null)
					{
						boothwiseWonPartyMap.put(param[1] != null ? Long.valueOf(param[1].toString().trim()):0L, param[2] != null ? param[2].toString():"");
					}
				
				}
			}
			List<Object[]> nominations  = null;
			nominations  = nominationDAO.findByLocationBasedConstituencyPartyInfoAndElectionsYears(partyIds, constituencyIds, String.valueOf(electionYears),electionScopeId);
		//	nominations  = nominationDAO.findByLocationBasedConstituencyPartyInfoAndElectionsYears(partyIds, constituencyIds, String.valueOf(electionYears),electionScopeId,2l);
			BoothResultVO boothResultVO = null;
			if(nominations != null && nominations.size()>0)
			{
				for(Object[] param:nominations)
				{

						List<BoothResultVO> boothResultVOs = new ArrayList<BoothResultVO>();
						PartyBoothPerformanceVO partyBoothPerformanceVO = new PartyBoothPerformanceVO();
						
						partyBoothPerformanceVO.setPartyName(param[1] != null ? param[1].toString():"");
						partyBoothPerformanceVO.setCandidateName(param[2] != null ? param[2].toString():"");
						partyBoothPerformanceVO.setConstituencyName(param[3] != null ? param[3].toString():"");
						partyBoothPerformanceVO.setElectionType(param[4] != null ? param[4].toString():"");
						partyBoothPerformanceVO.setElectionYear(param[5] != null ? param[5].toString():"");
						partyBoothPerformanceVO.setTotalVotes(param[14] != null ? Double.valueOf(param[14].toString()).longValue():0L);
						partyBoothPerformanceVO.setTotalValidVotes(param[7] != null ? Double.valueOf(param[7].toString()).intValue():0);
						partyBoothPerformanceVO.setVotingPercentage(param[8] != null ? param[8].toString():"");
						partyBoothPerformanceVO.setVotesGained(param[9] != null ? Double.valueOf(param[9].toString()).intValue():0);
						partyBoothPerformanceVO.setPercentage(param[10] != null ? param[10].toString():"");
						partyBoothPerformanceVO.setRank(param[11] != null ? Long.valueOf(param[11].toString()):0L);
						partyBoothPerformanceVO.setWonCandidate(basicWonCandidateVOList);
						partyBoothPerformanceVO.setMarginVotes(param[12] != null ? Double.valueOf(param[12].toString()).longValue():0L);
					
						
						//List<CandidateBoothResult> candidateboothResults = new ArrayList<CandidateBoothResult>(nomination.getCandidateBoothResults() != null ? nomination.getCandidateBoothResults():null);
						
						List<Object[]> candidateboothResults = candidateBoothResultDAO.LocatioWisefindboothWiseResultsForCandidate(constituencyId,param[13] != null ? Long.valueOf(param[13].toString()):0L);
						System.out.println("In getBoothWiseResultsForParty::"+candidateboothResults.size());
						
						if(candidateboothResults != null && candidateboothResults.size()>0)
						{
							for(Object[] param1:candidateboothResults)
							{
									int totalValidVotes = param1[4] != null ? Integer.valueOf(param1[4].toString()) :0;
									int votesEarned =  param1[8] != null ? Integer.valueOf(param1[8].toString()) :0;
									int totalVoters = param1[3] != null ? Integer.valueOf(param1[3].toString()) :0;
									
									String percentage  = calculatePercentage(Long.valueOf(totalValidVotes), Long.valueOf(votesEarned));	
									String pollPercent = calculatePercentage(Long.valueOf(totalVoters),Long.valueOf(totalValidVotes));
									
								
									if(param1[6] != null)
									{
										LocalElectionBody localElectionBody = localElectionBodyDAO.get(param1[6] != null ? (Long)param1[6] :0L);
										boothResultVO = new BoothResultVO(param1[1] != null ? param1[1].toString() :"",
												param1[2] != null ? param1[2].toString() :"", param1[5] != null ? param1[5].toString().replace(",", ", ") :"", 
												votesEarned, totalValidVotes, percentage, localElectionBody.getName()+" "+
														localElectionBody.getElectionType().getElectionType(), false,pollPercent);
									}
											
									else if(param1[7] != null)
									{
										Tehsil tehsil = (Tehsil) param1[7];
										boothResultVO = new BoothResultVO(param1[1] != null ? param1[1].toString() :"",
												param1[2] != null ? param1[2].toString() :"", param1[5] != null ? param1[5].toString().replace(",", ", ") :"", 
												votesEarned, totalValidVotes, percentage, tehsil.getTehsilName(), false,pollPercent);
									}
										
									else
									{
										boothResultVO = new BoothResultVO(param1[1] != null ? param1[1].toString() :"",
												param1[2] != null ? param1[2].toString() :"", param1[5] != null ? param1[5].toString().replace(",", ", ") :"", 
												votesEarned, totalValidVotes, percentage, "", true,pollPercent);
									}
										
									boothResultVO.setTotalBoothVoters(totalVoters);
									boothResultVO.setBoothId(param1[0] != null ? (Long)param1[0] :0L);
									boothResultVO.setOppPartyId(param[0] != null ? Long.valueOf(param[0].toString().trim()) :0L);
									boothResultVO.setMessage(param[1] != null ? param[1].toString():"");
									String partyName = boothwiseWonPartyMap.get(param1[1] != null ? Long.valueOf(param1[1].toString().trim()):0L) != null? boothwiseWonPartyMap.get(param1[1] != null ? Long.valueOf(param1[1].toString().trim()):0L):"";
									
									boothResultVO.setWonParty(partyName);
									boothResultVOs.add(boothResultVO);
									partyBoothPerformanceVO.setBoothResults(boothResultVOs);
								
							}
						}
						
						
						boothResultsForParties.add(partyBoothPerformanceVO);
					
				}
			}
			return boothResultsForParties;
		}catch(Exception e){
			Log.error("Exception raised at getBoothWiseElectionResults service"+e);
			return null;
		}
		
		
	}

	@Override
	public PartyBoothPerformanceVO getVotingPercentageWiseBoothResultForParties(PartyBoothPerformanceVO performanceVO, boolean isPollingPercentage,
			String path, List<Long> partyIdsList) {
		try {
			List<String> partyIdNamesList = new ArrayList<String>();

			if (partyIdsList != null && partyIdsList.size() > 0) {
				List<Object[]> partyList = partyDAO.getPartyShortNameByIds(partyIdsList);

				if (partyList != null && partyList.size() > 0) {
					for (Object[] param : partyList) {
						partyIdNamesList.add(param[1] != null ? param[1].toString().trim() : "");
					}
				}
			}

			if (performanceVO != null) {
				Map<String, List<BoothResultVO>> resultMap = new LinkedHashMap<String, List<BoothResultVO>>();
				// Map<String,Long> partyCountMap = new HashMap<String,
				// Long>();
				Map<String, Map<String, List<BoothResultVO>>> partyWiseCountInRangeMap = new HashMap<String, Map<String, List<BoothResultVO>>>();
				Map<String, List<BoothResultVO>> partyCountMap = new HashMap<String, List<BoothResultVO>>();
				resultMap.put("0-25", new ArrayList<BoothResultVO>(0));
				resultMap.put("25-50", new ArrayList<BoothResultVO>(0));
				resultMap.put("50-75", new ArrayList<BoothResultVO>(0));
				resultMap.put("75-100", new ArrayList<BoothResultVO>(0));

				for (BoothResultVO boothResultVO : performanceVO.getBoothResults()) {
					Double percentage = null;
					if (isPollingPercentage) {
						if (boothResultVO != null && boothResultVO.getPollingPercentage() != null)
							percentage = Double.parseDouble(boothResultVO.getPollingPercentage());
					} else
						percentage = Double.parseDouble(boothResultVO.getPercentage());

					List<BoothResultVO> partyCount = new ArrayList<BoothResultVO>(0);

					if (percentage >= 0 && percentage < 25) {
						List<BoothResultVO> boothList = resultMap.get("Below-5");

						if (boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("0-25", boothList);

						if (partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null) {
							partyCountMap = new HashMap<String, List<BoothResultVO>>();
							partyCount.add(boothResultVO);
							partyCountMap.put("0-25", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						} else {
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());

							if (partyCountMap.get("0-25") == null) {
								partyCount.add(boothResultVO);
								partyCountMap.put("0-25", partyCount);
							} else {
								partyCount = partyCountMap.get("0-25");
								partyCount.add(boothResultVO);
								partyCountMap.put("0-25", partyCount);
							}

							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						}

					} else if (percentage >= 25 && percentage < 50) {
						List<BoothResultVO> boothList = resultMap.get("25-50");

						if (boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("25-50", boothList);

						if (partyWiseCountInRangeMap.get(boothResultVO
								.getWonParty()) == null) {
							partyCountMap = new HashMap<String, List<BoothResultVO>>();
							partyCount.add(boothResultVO);
							partyCountMap.put("25-50", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						} else {
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());

							if (partyCountMap.get("25-50") == null) {
								partyCount.add(boothResultVO);
								partyCountMap.put("25510", partyCount);
							} else {
								partyCount = partyCountMap.get("25-50");
								partyCount.add(boothResultVO);
								partyCountMap.put("25-50", partyCount);
							}

							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						}

					} else if (percentage >= 50 && percentage < 75) {
						List<BoothResultVO> boothList = resultMap.get("50-75");

						if (boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("50-75", boothList);

						if (partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null) {
							partyCountMap = new HashMap<String, List<BoothResultVO>>();
							partyCount.add(boothResultVO);
							partyCountMap.put("50-75", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						} else {
							partyCountMap = partyWiseCountInRangeMap
									.get(boothResultVO.getWonParty());

							if (partyCountMap.get("50-75") == null) {
								partyCount.add(boothResultVO);
								partyCountMap.put("50-75", partyCount);
							} else {
								partyCount = partyCountMap.get("50-75");
								partyCount.add(boothResultVO);
								partyCountMap.put("50-75", partyCount);
							}

							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						}

					} else if (percentage >= 75 && percentage < 100) {
						List<BoothResultVO> boothList = resultMap.get("75-100");

						if (boothList == null)
							boothList = new ArrayList<BoothResultVO>();
						boothList.add(boothResultVO);
						resultMap.put("75-100", boothList);

						if (partyWiseCountInRangeMap.get(boothResultVO.getWonParty()) == null) {
							partyCountMap = new HashMap<String, List<BoothResultVO>>();
							partyCount.add(boothResultVO);
							partyCountMap.put("75-100", partyCount);
							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						} else {
							partyCountMap = partyWiseCountInRangeMap.get(boothResultVO.getWonParty());

							if (partyCountMap.get("75-100") == null) {
								partyCount.add(boothResultVO);
								partyCountMap.put("75-100", partyCount);
							} else {
								partyCount = partyCountMap.get("75-100");
								partyCount.add(boothResultVO);
								partyCountMap.put("75-100", partyCount);
							}

							partyWiseCountInRangeMap.put(boothResultVO.getWonParty(),partyCountMap);
						}

					}

				}

				List<BoothResultVO> perWiseboothResults = new ArrayList<BoothResultVO>(0);
				BoothResultVO resultVO = null;
				Map<String, List<SelectOptionVO>> boothsMap = new HashMap<String, List<SelectOptionVO>>();

				for (Map.Entry<String, List<BoothResultVO>> entry : resultMap.entrySet()) {
					resultVO = new BoothResultVO();
					resultVO.setLocation(entry.getKey());
					resultVO.setVotesEarned(entry.getValue().size());
					resultVO.setMessage(performanceVO.getPartyName());
					List<BoothResultVO> list = entry.getValue();

					List<BoothResultVO> boothResultVOList1 = null;
					if (partyWiseCountInRangeMap != null && partyWiseCountInRangeMap.size() > 0) {
						boothResultVOList1 = new ArrayList<BoothResultVO>();
						for (String partyName : partyWiseCountInRangeMap
								.keySet()) {
							Map<String, List<BoothResultVO>> tempMap = partyWiseCountInRangeMap.get(partyName);
							if (tempMap.get(entry.getKey()) != null) {
								List<BoothResultVO> countBoothsList = tempMap.get(entry.getKey());

								if (countBoothsList != null&& countBoothsList.size() > 0) {
									List<BoothResultVO> wonPartyWiseList = new ArrayList<BoothResultVO>();
									List<BoothResultVO> wonPartyWiseList1 = new ArrayList<BoothResultVO>();

									for (BoothResultVO boothResultVO2 : countBoothsList) {
										if (partyIdNamesList.contains(boothResultVO2.getWonParty().trim())) {
											wonPartyWiseList.add(boothResultVO2);
										} else {
											wonPartyWiseList1.add(boothResultVO2);
										}
									}

									BoothResultVO vo = new BoothResultVO();
									vo.setBoothResultVOList(wonPartyWiseList);

									if (wonPartyWiseList1 != null
											&& wonPartyWiseList1.size() > 0) {
										vo.setBoothResultVOList1(wonPartyWiseList1);
									} else
										vo.setBoothResultVOList1(null);

									vo.setWonParty(partyName);
									vo.setResultState(Long.valueOf(String.valueOf(wonPartyWiseList
													.size())));
									boothResultVOList1.add(vo);
								}
							}

						}

						resultVO.setBoothResultVOList(boothResultVOList1);
					}

					double total = 0.0d;
					double earned = 0.0d;

					List<SelectOptionVO> booths = new ArrayList<SelectOptionVO>();
					if (isPollingPercentage)
						for (BoothResultVO brVO : list) {
							SelectOptionVO boothsdtllist = new SelectOptionVO();
							total += brVO.getTotalVoters();
							earned += brVO.getVotesEarned();
							boothsdtllist.setPartno(brVO.getPartNo());
							boothsdtllist.setId(brVO.getBoothId());
							String loc = brVO.getLocation();
							boothsdtllist.setLocation(loc.replace("'", " ").replace("\"", " ").replace("\r", ""));
							String vill_co = brVO.getVillagesCovered();
							String villages_co = vill_co.replace("'", " ").replace("\"", " ").replace("\r", " ");
							boothsdtllist.setVillageCovered(villages_co);
							booths.add(boothsdtllist);
						}
					else
						for (BoothResultVO brVO : list) {
							SelectOptionVO boothsdtllist = new SelectOptionVO();
							total += brVO.getTotalBoothVoters();
							earned += brVO.getTotalVoters();
							boothsdtllist.setPartno(brVO.getPartNo());
							boothsdtllist.setId(brVO.getBoothId());
							String loc = brVO.getLocation();
							boothsdtllist.setLocation(loc.replace("'", " ").replace("\"", " ").replace("\r", ""));
							String vill_co = brVO.getVillagesCovered();
							String villages_co = vill_co.replace("'", " ").replace("\"", " ").replace("\r", "");
							boothsdtllist.setVillageCovered(villages_co);
							booths.add(boothsdtllist);
						}

					if (list.size() > 0) {
						if (total > 0)
							resultVO.setPercentage((new BigDecimal((earned * 100) / total).setScale(2,BigDecimal.ROUND_HALF_UP)).toString());
						boothsMap.put(entry.getKey(), booths);
					} else
						resultVO.setPercentage("--");

					perWiseboothResults.add(resultVO);
				}
				if (isPollingPercentage) {
					performanceVO.setPerWiseboothResults(perWiseboothResults);
					performanceVO.setBoothsMap(boothsMap);
				} else {
					performanceVO.setPartyPerWiseboothResults(perWiseboothResults);
					performanceVO.setBoothsMap1(boothsMap);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured at " + e);
		}
		return performanceVO;
		
	}

	@Override
	public PartyBoothPerformanceVO segrigateBoothWiseResults(List<PartyBoothPerformanceVO> partyBoothPerformanceVOList) {
		PartyBoothPerformanceVO returnVO = new PartyBoothPerformanceVO();
		try {
			

			List<BoothResultVO> percentageResultList = null;
			List<BoothResultVO> partypercentageResultList = null;
			Map<String,List<BoothResultVO>> percentageRangeWiseMap = new LinkedHashMap<String, List<BoothResultVO>>();
			Map<String,List<BoothResultVO>> partyPercentageRangeWiseMap = new LinkedHashMap<String, List<BoothResultVO>>();
			
			if(partyBoothPerformanceVOList != null && partyBoothPerformanceVOList.size()>0)
			{
				List<PartyBoothPerformanceVO> candidatesList = new ArrayList<PartyBoothPerformanceVO>();
				Map<Long,List<BoothResultVO>> boothResultsMap = new TreeMap<Long, List<BoothResultVO>>();
				List<BoothResultVO> boothWiseResults = new ArrayList<BoothResultVO>();
				
				for (PartyBoothPerformanceVO vo: partyBoothPerformanceVOList) 
				{					
					PartyBoothPerformanceVO candidateVO = new PartyBoothPerformanceVO();
					
					candidateVO.setCandidateName(vo.getCandidateName());
					candidateVO.setTotalVotes(vo.getTotalVotes());
					candidateVO.setTotalValidVotes(vo.getTotalValidVotes());
					candidateVO.setVotingPercentage(vo.getVotingPercentage());
					candidateVO.setVotesGained(vo.getVotesGained());
					candidateVO.setPercentage(vo.getPercentage());
					candidateVO.setPartyName(vo.getPartyName());
					candidateVO.setRank(vo.getRank());
					candidateVO.setMarginVotes(vo.getMarginVotes());
					candidateVO.setWonCandidate(vo.getWonCandidate());
					candidatesList.add(candidateVO);
					
					
					
					
					if(vo.getBoothResults() != null && vo.getBoothResults().size()>0)
					{
						List<BoothResultVO> boothwiseResultVOList = null;
						 
						for (BoothResultVO boothResultVO1 : vo.getBoothResults())
						{
							boothwiseResultVOList = new ArrayList<BoothResultVO>();
							
							if(boothResultsMap.get(Long.valueOf(boothResultVO1.getPartNo())) == null)									
							{
								boothResultsMap.put(Long.valueOf(boothResultVO1.getPartNo()), boothwiseResultVOList);
							}
							else
							{								
								boothwiseResultVOList = boothResultsMap.get(Long.valueOf(boothResultVO1.getPartNo()));
							}
							
							boothwiseResultVOList.add(boothResultVO1);
						}
					}
					
				
					if(vo.getPartyPerWiseboothResults() != null && vo.getPartyPerWiseboothResults().size()>0)
					{
						for (BoothResultVO vo1  : vo.getPartyPerWiseboothResults()) 
						{
							
									BoothResultVO vo2 = new BoothResultVO();
									
									List<BoothResultVO> voList = new ArrayList<BoothResultVO>();
									
									if(percentageRangeWiseMap.get(vo1.getLocation()) == null)
									{
										percentageRangeWiseMap.put(vo1.getLocation(), voList);
									}
									else
									{
										voList = percentageRangeWiseMap.get(vo1.getLocation());
									}
									
									vo2.setPercentage(vo1.getPercentage());
									vo2.setVotesEarned(vo1.getVotesEarned());
									vo2.setLocation(vo1.getLocation());
									vo2.setMessage(vo1.getMessage());
									
									vo2.setBoothResultVOList(vo1.getBoothResultVOList());	
									
									BoothResultVO partyVO = getMatchedVOByPartyName(vo1.getBoothResultVOList(),vo1.getMessage());
									if(partyVO != null)
									{
										vo2.setWonParty(partyVO.getWonParty());
										vo2.setResultState(partyVO.getResultState());
										vo2.setBoothResultVOList(partyVO.getBoothResultVOList());
									}
									
									if(vo1.getBoothResultVOList() != null && vo1.getBoothResultVOList().size()>0)
									{
										for (BoothResultVO resultVO : vo1.getBoothResultVOList()) 
										{
											if(resultVO.getBoothResultVOList1() != null && resultVO.getBoothResultVOList1().size()>0)
												vo2.setBoothResultVOList1(resultVO.getBoothResultVOList1());
										}
									}
									
									voList.add(vo2);
							
						}
					}
					
					if(vo.getPerWiseboothResults() != null && vo.getPerWiseboothResults().size()>0)
					{
						for (BoothResultVO vo1  : vo.getPerWiseboothResults()) 
						{							
							BoothResultVO vo2 = new BoothResultVO();
							
							List<BoothResultVO> voList = new ArrayList<BoothResultVO>();
							
							if(partyPercentageRangeWiseMap.get(vo1.getLocation()) == null)
							{
								partyPercentageRangeWiseMap.put(vo1.getLocation(), voList);
							}
							else
							{
								voList = partyPercentageRangeWiseMap.get(vo1.getLocation());
							}
							
							vo2.setPercentage(vo1.getPercentage());
							vo2.setVotesEarned(vo1.getVotesEarned());
							vo2.setLocation(vo1.getLocation());
							vo2.setMessage(vo1.getMessage());
							
							vo2.setBoothResultVOList(vo1.getBoothResultVOList());	
							
							BoothResultVO partyVO = getMatchedVOByPartyName(vo1.getBoothResultVOList(),vo1.getMessage());
							if(partyVO != null)
							{
								vo2.setWonParty(partyVO.getWonParty());
								vo2.setResultState(partyVO.getResultState());
							}

							if(vo1.getBoothResultVOList() != null && vo1.getBoothResultVOList().size()>0)
							{
								for (BoothResultVO resultVO : vo1.getBoothResultVOList()) 
								{
									if(resultVO.getBoothResultVOList1() != null && resultVO.getBoothResultVOList1().size()>0)
										vo2.setBoothResultVOList1(resultVO.getBoothResultVOList1());
								}
							}
							
							voList.add(vo2);
								
						}
					}
				
					if(partyPercentageRangeWiseMap != null && partyPercentageRangeWiseMap.size()>0)
					{
						partypercentageResultList = new ArrayList<BoothResultVO>();
						for (String locationName : partyPercentageRangeWiseMap.keySet()) 
						{
							BoothResultVO vo3 = new BoothResultVO();
							vo3.setLocation(locationName);
							vo3.setBoothResultVOList(partyPercentageRangeWiseMap.get(locationName));
							
							partypercentageResultList.add(vo3);
						}
					}

						
					if(percentageRangeWiseMap != null && percentageRangeWiseMap.size()>0)
					{
						 percentageResultList = new ArrayList<BoothResultVO>();
						for (String locationName : percentageRangeWiseMap.keySet()) 
						{
							BoothResultVO vo3 = new BoothResultVO();
							vo3.setLocation(locationName);
							vo3.setBoothResultVOList(percentageRangeWiseMap.get(locationName));
							
							percentageResultList.add(vo3);
						}
					}
					
					
					returnVO.setPartyBoothPerformanceVOList(candidatesList);					
					returnVO.setPartyPerWiseboothResults(partypercentageResultList);
					returnVO.setPerWiseboothResults(percentageResultList);
			}
				if(boothResultsMap != null && boothResultsMap.size()>0)
				{
					for (Long boothNo : boothResultsMap.keySet()) 
					{
						BoothResultVO BoothwisePartyResultVO = new BoothResultVO();
						List<BoothResultVO> BoothwisePartyResultList = boothResultsMap.get(boothNo);
						BoothwisePartyResultVO.setPartNo(boothNo.toString());
						BoothwisePartyResultVO.setBoothResultVOList(BoothwisePartyResultList);
						
						boothWiseResults.add(BoothwisePartyResultVO);
					}
				}
						
				returnVO.setBoothResults(boothWiseResults);
			}
			
		} catch (Exception e) {
			LOG.error(" exception occured in segrigateBoothWiseResults() in PartyBoothWiseResultsService class. ",e);
		}
		
		return returnVO;
	}
	public BoothResultVO getMatchedVOByPartyName(List<BoothResultVO> list, String PartyName)
	{
		BoothResultVO returnVO = null;
		try {
			
			if(list !=null && list.size()>0)
			{
				for (BoothResultVO boothResultVO : list) {
					if(boothResultVO.getMessage().equalsIgnoreCase(PartyName))
					{
						return boothResultVO;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return returnVO;
	}
	
	/**
	* @author Hymavathi G 
	* @Description :Voting Details For Location
	*  @since 17-oct-2017
	*  @return :List<ElectionInformationVO>
	*/
	public List<ElectionInformationVO> getLocationWiseVotingDetails(List<Long> electionYrs,Long levelId,List<Long> locationVals,List<String> subtypes,String searchLevel,String clickType){
		List<ElectionInformationVO> returnList = new ArrayList<ElectionInformationVO>();
		try{
			
			List<Long> locAssmblyVals = new ArrayList<Long>();
			locAssmblyVals.addAll(locationVals);
			if(levelId != null && levelId.longValue() == 10l){
				List<Object[]> findAssembliesConstituencies = (List<Object[]>) delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesByParliaments(locationVals);
				//locationVals.clear();
				if(commonMethodsUtilService.isListOrSetValid(findAssembliesConstituencies)){
					locAssmblyVals.clear();
					for (Object[] param : findAssembliesConstituencies) {
						locAssmblyVals.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}
			
			Map<Long,ElectionInformationVO> locationMap  = new HashMap<Long,ElectionInformationVO>();
			if(clickType != null && clickType.equalsIgnoreCase("clickFunction")){
				List<Object[]> totalVoters = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel,clickType);
				setLocationWiseVotersDetails( totalVoters, locationMap,"totalVoters",levelId,searchLevel);
				
				
			}
			List<Object[]> polledVotes = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel,"");
			setLocationWiseVotersDetails( polledVotes, locationMap,"polled",levelId,searchLevel);
			List<Object[]> assemblyEarnedVotes = null;
			
				 assemblyEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel, 2l);
			setLocationWiseVotersDetails( assemblyEarnedVotes, locationMap,"assembly",levelId,searchLevel);
			
			List<Object[]> parliamentEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel, 1l);
			setLocationWiseVotersDetails( parliamentEarnedVotes, locationMap,"parliament",levelId,searchLevel);
			
			if(levelId != null && levelId.longValue() == 4l){
				List<Object[]> muncipalTotalVotes = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality",clickType);
				setLocationWiseVotersDetails( muncipalTotalVotes, locationMap,"totalVoters",levelId,"muncipality");
				List<Object[]> muncipalPolledVotes = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality","");
				setLocationWiseVotersDetails( muncipalPolledVotes, locationMap,"polled",levelId,"muncipality");
				
				List<Object[]> muncipalAssemblyEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality", 2l);
				setLocationWiseVotersDetails( muncipalAssemblyEarnedVotes, locationMap,"assembly",levelId,"muncipality");
				
				List<Object[]> muncipalParliamentEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality", 1l);
				setLocationWiseVotersDetails( muncipalParliamentEarnedVotes, locationMap,"parliament",levelId,"muncipality");
			}
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList.addAll(locationMap.values());
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at getLocationWiseVotingDetails service"+e);
		}
		return returnList;
	}
	
	/**
	* @author Hymavathi G 
	* @Description :service build function for Voting Details
	*  @since 17-oct-2017
	*  @return :void
	*/
	public void setLocationWiseVotersDetails(List<Object[]> polledVotes,Map<Long,ElectionInformationVO> yearsPolledMap,String type,Long locationTypeId,String searchLevel){
		try{
			Long locationLevelId = 0l;
			if(locationTypeId.longValue() == 2l  || locationTypeId.longValue() == 10l || locationTypeId.longValue() == 3l){
				locationLevelId  = 4l;
		        }
		        else if(locationTypeId.longValue() == 4l ){
		        	if(searchLevel != null && searchLevel.equalsIgnoreCase("panchayat")){
		        		locationLevelId  = 6l;
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("tehsil")){
		        		locationLevelId  = 5l;
		        	}else if(searchLevel != null && searchLevel.equalsIgnoreCase("muncipality")){
		        		locationLevelId  = 7l;
		        	}
	            }else if(locationTypeId.longValue() == 5l){
	            	locationLevelId  = 6l;
	            }else if(locationTypeId.longValue() == 6l){
	            	locationLevelId  = 0l;
	            }else if(locationTypeId == 8l || locationTypeId == 7l){
	            	locationLevelId  = 8l;
	             }
			if(commonMethodsUtilService.isListOrSetValid(polledVotes)){
				for (Object[] param : polledVotes) {
					ElectionInformationVO locationVO = yearsPolledMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationVO == null){
						locationVO = new ElectionInformationVO();
						locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.setLocationId(locationLevelId);
						yearsPolledMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locationVO);
					}
					if(type != null &&type.equalsIgnoreCase("totalVoters")){
						locationVO.setTotalVoters(commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(type != null &&type.equalsIgnoreCase("polled")){
						locationVO.setValidVoters(commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(type != null &&type.equalsIgnoreCase("assembly")){
						locationVO.setAssemblyEarndVotes(commonMethodsUtilService.getLongValueForObject(param[2]));
						locationVO.setEarnedVotersPerc(Double.valueOf(calculatePercentage(locationVO.getValidVoters(), locationVO.getAssemblyEarndVotes())));
						//yearVO.setParliamentValidVoters(commonMethodsUtilService.getLongValueForObject(param[1]));
					}else if(type != null &&type.equalsIgnoreCase("parliament")){
						locationVO.setParliamentEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[2]));
						locationVO.setEarnedVotersPerc1(Double.valueOf(calculatePercentage(locationVO.getValidVoters(), locationVO.getParliamentEarnedVotes())));
						Double parliamentPerc = locationVO.getEarnedVotersPerc1() != null ?locationVO.getEarnedVotersPerc1():0.0;
						Double assmblyPerc = locationVO.getEarnedVotersPerc() != null ?locationVO.getEarnedVotersPerc():0.0;
						Double diffPerc = assmblyPerc-parliamentPerc;
						locationVO.setPerc(diffPerc.toString());
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised at setPolledVotesPerElectionYear service"+e);
		}
	}
	

	 /**
	 * @author : Babu kurakula <href:kondababu.kurakula@itgrids.com >
	 * @Date   :17th OCT,2017
	 * @description : get location wise status wise counts and deatiles 
	 * @param : locationTypeId, locationValue ,partyIdList,electionYrs,
	 * 		 electionScopeIds,subTypes,searchType,statusType,year
	 * @return :List of ElectionInformationVO 
*/
public List<ElectionInformationVO> getElectionInformationLocationWiseStatusAndYearWise(Long locationTypeId,Long locationValue,List<Long> partyIdList,
	List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType,String statusType,String year,List<Long> locationIds){
	
	List<ElectionInformationVO> finalList=new ArrayList<ElectionInformationVO>(0);
	Map<String,Map<String,Long>>  yearAndStatusCountMap=new HashMap<String,Map<String,Long>>();
	Map<String,Map<String,List<Long>>> YearAndstatusAndLocationIdsMap=new HashMap<String,Map<String,List<Long>>>();
	try{
		List<ElectionInformationVO> electionInformationVOList=getElectionInformationLocationWiseStatus(locationTypeId,locationValue,partyIdList,electionYrs,electionScopeIds,subTypes,searchType);
		if(commonMethodsUtilService.isListOrSetValid(electionInformationVOList)){
			for(ElectionInformationVO locationVo:electionInformationVOList){
				boolean flage=false;
				if(commonMethodsUtilService.isListOrSetValid(locationVo.getList())){
					for(ElectionInformationVO listVo:locationVo.getList()){
						if(listVo.getStatus() !=null && listVo.getStatus().trim().equalsIgnoreCase(statusType.trim()) && listVo.getElectionYear().trim().equalsIgnoreCase(year.trim())){
							flage=true;
						}
					}
				}
				if(flage){
					if(locationIds !=null && locationIds.size() >0 && locationIds.contains(locationVo.getLocationId())){
						finalList.add(locationVo);
					}else if(locationIds ==null || locationIds.size()==0){
						finalList.add(locationVo);
					}
				}
			}
			for(ElectionInformationVO vo :finalList){
				if(commonMethodsUtilService.isListOrSetValid(vo.getList())){
					for(ElectionInformationVO subVo: vo.getList()){//YearAndstatusAndLocationIdsMap
						Map<String,Long> statusCountsMap=yearAndStatusCountMap.get(subVo.getElectionYear().trim());
						if(!commonMethodsUtilService.isMapValid(statusCountsMap))
							statusCountsMap=new HashMap<String,Long>();
						if(subVo.getStatus() !=null && subVo.getStatus().trim().length() >0){
							Long count=statusCountsMap.get(subVo.getStatus().trim());
							if(count ==null)
								count=0L;
							count=count+1L;
							statusCountsMap.put(subVo.getStatus().trim(), count);
						}		
						yearAndStatusCountMap.put(subVo.getElectionYear().trim(),statusCountsMap);
						
						Map<String,List<Long>> statusLocationIdssMap=YearAndstatusAndLocationIdsMap.get(subVo.getElectionYear().trim());
						if(!commonMethodsUtilService.isMapValid(statusLocationIdssMap))
							statusLocationIdssMap=new HashMap<String,List<Long>>();
						
						if(subVo.getStatus() !=null && subVo.getStatus().trim().length() >0){
							List<Long> locationIdsList=statusLocationIdssMap.get(subVo.getStatus().trim());
							if(!commonMethodsUtilService.isListOrSetValid(locationIdsList))
								locationIdsList=new ArrayList<Long>();
							locationIdsList.add(vo.getLocationId());
							statusLocationIdssMap.put(subVo.getStatus().trim(), locationIdsList);
						}
						
						YearAndstatusAndLocationIdsMap.put(subVo.getElectionYear().trim(), statusLocationIdssMap);
					}
				}
			}
		}
		if(commonMethodsUtilService.isListOrSetValid(finalList.get(0).getList())){
			for(ElectionInformationVO subvo: finalList.get(0).getList()){
				Map<String,Long> statusCountsMap=yearAndStatusCountMap.get(subvo.getElectionYear().trim());
				Map<String,List<Long>> statusLocationIdsMap=YearAndstatusAndLocationIdsMap.get(subvo.getElectionYear().trim());
				if(commonMethodsUtilService.isListOrSetValid(subvo.getSubList1())){
					for(ElectionInformationVO vo: subvo.getSubList1()){
						vo.setWonSeatsCount(statusCountsMap.get(vo.getStatus().trim()));
						if(vo.getStatus() != null && vo.getStatus().trim().length()>0 && statusLocationIdsMap.get(vo.getStatus().trim()) !=null && statusLocationIdsMap.get(vo.getStatus().trim()).size() >0)	
							vo.getIdsList().addAll(statusLocationIdsMap.get(vo.getStatus().trim()));
				
					}
				}
			}
		}
	}catch(Exception e){
		Log.error("Exception raised at getElectionInformationLocationWiseStatusAndYearWise service"+e);

	}
	return finalList;
}
}
