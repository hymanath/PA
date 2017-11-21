package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationWiseElectionInformationDetalsService;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICandidateBoothResultDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMarginVotesRangeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.hibernate.DelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.hibernate.TehsilDAO;
import com.itgrids.partyanalyst.dto.BaseCandidateResultVO;
import com.itgrids.partyanalyst.dto.ElectionInformationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.BoothResultVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class LocationWiseElectionInformationDetalsService implements ILocationWiseElectionInformationDetalsService {

	private final static Logger LOG = Logger.getLogger(LocationWiseElectionInformationDetalsService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	private ICandidateDAO candidateDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IPartyDAO partyDAO;
    private IMarginVotesRangeDAO marginVotesRangeDAO;
	private DelimitationConstituencyDAO delimitationConstituencyDAO;
	private IConstituencyDAO constituencyDAO;
	

	private INominationDAO nominationDAO;

	private ICandidateBoothResultDAO candidateBoothResultDAO;

	private ILocalElectionBodyDAO localElectionBodyDAO;

	private IElectionDAO electionDAO;
	private TehsilDAO tehsilDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	private IBoothDAO boothDAO;
	private IPanchayatDAO panchayatDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService = new SetterAndGetterUtilService();

	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	
	
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IParliamentAssemblyDAO getParliamentAssemblyDAO() {
		return parliamentAssemblyDAO;
	}

	public void setParliamentAssemblyDAO(IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}

	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}

	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}

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
	public TehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(TehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IElectionAllianceDAO getElectionAllianceDAO() {
		return electionAllianceDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO) {
		this.electionAllianceDAO = electionAllianceDAO;
	}
	
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	@Override
	public List<ElectionInformationVO> getElectionInformationLocationWiseStatus(Long locationTypeId,Long locationValue, 
			List<Long> partyIdList, List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType,String withAllaince) {
		try{
			
			List<ElectionInformationVO> finalPartyList = new ArrayList<ElectionInformationVO>();
			Map<Long,List<Long>> electionIdAndLocationIdListMap=new HashMap<Long, List<Long>>();
			Map<Long,List<ElectionInformationVO>> yearMap = new HashMap<Long, List<ElectionInformationVO>>();
			Map<Long,ElectionInformationVO> electionYeasrMap = new HashMap<Long, ElectionInformationVO>();
			Map<Long,Map<String,Long>> locationIdAndStatusAndCountMap = new HashMap<Long, Map<String,Long>>();
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
			Map<Long,String> orderNoAndStatusmap = new LinkedHashMap<Long,String>();
			Map<String,String> statusMap = new HashMap<String, String>();
			Map<String,ElectionInformationVO> statusVOMap = new HashMap<String, ElectionInformationVO>();
			List<Object[]> marginWiseStatusObjs = marginVotesRangeDAO.getMarginVotesAgeRangeDetails();
			if(marginWiseStatusObjs != null && marginWiseStatusObjs.size() >0){
				for(Object[] objs : marginWiseStatusObjs){
					statusMap.put(commonMethodsUtilService.getStringValueForObject(objs[2])+" - "+commonMethodsUtilService.getStringValueForObject(objs[3]),commonMethodsUtilService.getStringValueForObject(objs[1]));
					statusVOMap.put(commonMethodsUtilService.getStringValueForObject(objs[2])+" - "+commonMethodsUtilService.getStringValueForObject(objs[3]), new ElectionInformationVO(commonMethodsUtilService.getStringValueForObject(objs[1]),commonMethodsUtilService.getStringValueForObject(objs[0]),
							commonMethodsUtilService.getLongValueForObject(objs[2]),commonMethodsUtilService.getLongValueForObject(objs[3]),commonMethodsUtilService.getLongValueForObject(objs[4])));
					orderNoAndStatusmap.put(commonMethodsUtilService.getLongValueForObject(objs[4]), commonMethodsUtilService.getStringValueForObject(objs[1]));
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
			if(locationTypeId != null && locationTypeId.longValue() !=4L &&locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L && locationTypeId.longValue() !=10L){
				
				if(!searchType.equalsIgnoreCase("Parliament"))
					validVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,searchType,false,false);
				
				if(!commonMethodsUtilService.isListOrSetValid(validVoterList)){
					validVoterList = new ArrayList<Object[]>();
				}
				
				if(electionScopeIds.contains(1l) || searchType.equalsIgnoreCase("Parliament")){
					
					List<Object[]> parliamentWiseValidVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList,null,false,false);
					if(commonMethodsUtilService.isListOrSetValid(parliamentWiseValidVoterList))
						validVoterList.addAll(parliamentWiseValidVoterList);
				}
			}else{
				validVoterList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,searchType,false,false);
				if((electionScopeIds.contains(3l) ||electionScopeIds.contains(4l)) && (locationTypeId == 4l ||  locationTypeId == 5l)){
					List<Object[]> validVoterListTmp= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,searchType,false,true);
					if(commonMethodsUtilService.isListOrSetValid(validVoterListTmp))
						validVoterList.addAll(validVoterListTmp);
				}

				if(searchType.equalsIgnoreCase("mandal")){
					List<Object[]> municipalList= candidateDAO.getElectionInformationLocationWisedetailsForValidVotes(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,searchType,true,false);
					if(commonMethodsUtilService.isListOrSetValid(municipalList))
						validVoterList.addAll(municipalList);
				}
			}
			Map<Long, ElectionInformationVO> locationMap= new HashMap<Long, ElectionInformationVO>();
			for (Object[] objects : validVoterList) {	
				//if(commonMethodsUtilService.getLongValueForObject(objects[5]) != 280L)
				//	continue;
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
			if(locationTypeId != null && locationTypeId.longValue() !=4L && locationTypeId.longValue() !=5L && locationTypeId.longValue() !=7L && locationTypeId.longValue() !=6L && locationTypeId.longValue() !=10L){
				if(!searchType.equalsIgnoreCase("Parliament"))
					earnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,null,searchType,false,false);
				if(!commonMethodsUtilService.isListOrSetValid(earnedVotesList))
					earnedVotesList = new ArrayList<Object[]>();
					
				if(electionScopeIds.contains(1l) || searchType.equalsIgnoreCase("Parliament")){	
					List<Object[]> tempearnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,parliamentIdsList,null,null,false,false);
					if(commonMethodsUtilService.isListOrSetValid(tempearnedVotesList))
						earnedVotesList.addAll(tempearnedVotesList);
				}
			}else{
				earnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,null,searchType,false,false);
				if((electionScopeIds.contains(3l) ||electionScopeIds.contains(4l)) && (locationTypeId == 4l ||  locationTypeId == 5l)){
					List<Object[]> earnedVotesListTmp= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,null,subTypes,null,null,searchType,false,true);
					if(commonMethodsUtilService.isListOrSetValid(earnedVotesListTmp))
						earnedVotesList.addAll(earnedVotesListTmp);
				}
				if(searchType.equalsIgnoreCase("mandal")){
					List<Object[]> tempearnedVotesList= candidateDAO.getElectionInformationLocationWiseDetailEarnedVoterShare(electionYrs, locationTypeId, locationValue,electionScopeIds,"lowLevels",subTypes,null,null,searchType,true,false);
					
					if(commonMethodsUtilService.isListOrSetValid(tempearnedVotesList))
						earnedVotesList.addAll(tempearnedVotesList);
				}
			}
			
			Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupIdMap = null;
			if(withAllaince != null && withAllaince.trim().equalsIgnoreCase("true"))
				alliancedPartiesWithGroupIdMap = getSegregateAliancePartiesMap(subTypes,electionYrs,electionScopeIds);
			if(!commonMethodsUtilService.isMapValid(alliancedPartiesWithGroupIdMap))
				alliancedPartiesWithGroupIdMap = new HashMap<Long, Map<Long,ElectionInformationVO>>(0);
			
			for (Object[] param : earnedVotesList) {
				
				Long partyId =commonMethodsUtilService.getLongValueForObject(param[0]);
				String partyName =commonMethodsUtilService.getStringValueForObject(param[1]);
				Long electionId = commonMethodsUtilService.getLongValueForObject(param[5]);
				
				List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,electionId,partyId);
				if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
					partyId = Long.valueOf(alliancePartyIdNameList.get(0));
					partyName = alliancePartyIdNameList.get(1);
					String []alianceArr=alliancePartyIdNameList.get(3).replace("[", "").replace("]", "").split(",");
					List<Long> alisceidSList=new ArrayList<Long>();
					for(int i=0; i< alianceArr.length;i++){
						alisceidSList.add(Long.parseLong(alianceArr[i].trim()));
					}
					for(Long id :alisceidSList ){
						if(partyIdList.contains(id))
							partyIdList.add(partyId);
					}
				}
				
				List<Long> locationList=electionIdAndLocationIdListMap.get(commonMethodsUtilService.getLongValueForObject(param[5]));
				if(!commonMethodsUtilService.isListOrSetValid(locationList))
					locationList =new ArrayList<Long>();
				
				locationList.add(commonMethodsUtilService.getLongValueForObject(param[7]));
				electionIdAndLocationIdListMap.put(commonMethodsUtilService.getLongValueForObject(param[5]), locationList);
				
				//if(commonMethodsUtilService.getLongValueForObject(param[7]) != 280L)
				//	continue;
				ElectionInformationVO yearVo= locationMap.get(commonMethodsUtilService.getLongValueForObject(param[7]));
				if(yearVo == null){
					List<ElectionInformationVO> yearList = new ArrayList<ElectionInformationVO>();
					yearVo = new ElectionInformationVO();
					ElectionInformationVO innerEvo = new ElectionInformationVO();
					yearVo.setLocationId(commonMethodsUtilService.getLongValueForObject(param[7]));
					yearVo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[8]));
					
					innerEvo.setPartyId(partyId);
					innerEvo.setPartyName(partyName);
					innerEvo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
					innerEvo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[3]));
					innerEvo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
					innerEvo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[5]));
					innerEvo.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6]));
					
					
					ElectionInformationVO partyVO = getMatchedElectionInformationVOByPartyName(innerEvo.getSubList2(),partyName);
					if(partyVO == null){
						partyVO = new ElectionInformationVO();
						innerEvo.getSubList2().add(partyVO);
					}
					
					partyVO.setPartyId(partyId);
					partyVO.setPartyName(partyName);
					partyVO.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
					partyVO.setElectionType(commonMethodsUtilService.getStringValueForObject(param[3]));
					partyVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
					partyVO.setElectionId(commonMethodsUtilService.getLongValueForObject(param[5]));
					partyVO.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6]));
					
					yearList.add(innerEvo);
					yearVo.setList(yearList);
					locationMap.put(commonMethodsUtilService.getLongValueForObject(param[7]), yearVo);
				}else{
					if(commonMethodsUtilService.isListOrSetValid(yearVo.getList())){
						ElectionInformationVO innerEvo = getMatchedLocationVO(yearVo.getList(),commonMethodsUtilService.getLongValueForObject(param[5]));
						if (innerEvo != null) {
							innerEvo.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6])+innerEvo.getEarnedVotes());
							
							ElectionInformationVO partyVO = new ElectionInformationVO();
							partyVO.setPartyId(commonMethodsUtilService.getLongValueForObject(param[0]));
							partyVO.setPartyName(commonMethodsUtilService.getStringValueForObject(param[1]));
							partyVO.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
							partyVO.setElectionType(commonMethodsUtilService.getStringValueForObject(param[3]));
							partyVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
							partyVO.setElectionId(commonMethodsUtilService.getLongValueForObject(param[5]));
							partyVO.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6]));
							innerEvo.getSubList2().add(partyVO);
							
						}else{
							innerEvo = new ElectionInformationVO();
							innerEvo.setPartyId(partyId);
							innerEvo.setPartyName(partyName);
							innerEvo.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
							innerEvo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[3]));
							innerEvo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
							innerEvo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[5]));
							innerEvo.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							ElectionInformationVO partyVO = getMatchedElectionInformationVOByPartyName(innerEvo.getSubList2(),partyName);
							if(partyVO == null){
								partyVO = new ElectionInformationVO();
								innerEvo.getSubList2().add(partyVO);
							}
							partyVO.setPartyId(partyId);
							partyVO.setPartyName(partyName);
							partyVO.setElectionTypeId(commonMethodsUtilService.getLongValueForObject(param[2]));
							partyVO.setElectionType(commonMethodsUtilService.getStringValueForObject(param[3]));
							partyVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[4]));
							partyVO.setElectionId(commonMethodsUtilService.getLongValueForObject(param[5]));
							partyVO.setEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							yearVo.getList().add(innerEvo);
						}
					}
				}
			}
			List<ElectionInformationVO> partyResultList = setLocationWiseStatus(statusVOMap,locationMap,partyIdList);
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
				finalPartyList = buildSummaryForElectionResult(partyResultList,statusMap,electionIdAndLocationIdListMap,statusVOMap);
			}
			//Location LevelWise spliting the data in Constituency and Mandal and panchayat wise
			List<Long> locationIdsList = new ArrayList<Long>();
			List<Long> locationIdsListMuncipality = new ArrayList<Long>();
			Map<Long,String> locationIdadsubLocationMap=new HashMap<Long, String>();
			if(commonMethodsUtilService.isListOrSetValid(finalPartyList)){
				for(ElectionInformationVO locationVO : finalPartyList){
					if(!locationVO.getLocationName().contains("MUNCIPALITY")){
						locationIdsList.add(locationVO.getLocationId());
					}else{
						locationIdsListMuncipality.add(locationVO.getLocationId());
					}
					Map<String,Long> statusAndCountMap=locationIdAndStatusAndCountMap.get(locationVO.getLocationId());
					if(!commonMethodsUtilService.isMapValid(statusAndCountMap))
						statusAndCountMap = new HashMap<String, Long>();
					
					if(commonMethodsUtilService.isListOrSetValid(locationVO.getList())){
						for(ElectionInformationVO yearVo : locationVO.getList()){
							if(yearVo.getStatus() != null && yearVo.getStatus().trim().length() >0){
								Long count=statusAndCountMap.get(yearVo.getStatus().trim());
								if(count == null)
									count=0L;
									count=count+1L;
									statusAndCountMap.put(yearVo.getStatus().trim(), count);
							}
							
						}
					}
					locationIdAndStatusAndCountMap.put(locationVO.getLocationId(),statusAndCountMap);	
					for(Entry<Long,String> status: orderNoAndStatusmap.entrySet()){
						ElectionInformationVO statusVo = new ElectionInformationVO();
						statusVo.setLocationId(locationVO.getLocationId());
						statusVo.setStatus(status.getValue());
						statusVo.setWonSeatsCount(0L);
						locationVO.getSubList1().add(statusVo);
					}
				}
				
			List<Object[]> locationWiseObjsList = null;
			if(searchType != null && searchType.equalsIgnoreCase("constituency")){
				locationWiseObjsList = constituencyDAO.getLocationsDetailsBySearchType(locationIdsList, searchType);
				
			}else if(searchType.equalsIgnoreCase("mandal")){
				locationWiseObjsList = boothDAO.getLocationWiseMandalAndConstituency(locationIdsList, searchType,false);
				if(locationIdsListMuncipality !=null && locationIdsListMuncipality.size()>0 ){
					List<Object[]> locationWiseObjsListTmp = boothDAO.getLocationWiseMandalAndConstituency(locationIdsListMuncipality, searchType,true);
					if(!commonMethodsUtilService.isListOrSetValid(locationWiseObjsList)){
						locationWiseObjsList = new ArrayList<Object[]>();
					}if(commonMethodsUtilService.isListOrSetValid(locationWiseObjsListTmp)){
						locationWiseObjsList.addAll(locationWiseObjsListTmp);
					}
				}
			}else if(searchType.equalsIgnoreCase("panchayat")){
				locationWiseObjsList  = boothDAO.getLocationWiseMandalAndpanchayat(locationIdsList, searchType);
			}
				if(locationWiseObjsList != null && locationWiseObjsList.size() >0){
					for(Object[] objs : locationWiseObjsList){
						locationIdadsubLocationMap.put(commonMethodsUtilService.getLongValueForObject(objs[2]), commonMethodsUtilService.getStringValueForObject(objs[1]));
					
					}
				}
		}
			
			if(commonMethodsUtilService.isListOrSetValid(finalPartyList)){
				for(ElectionInformationVO locationVO : finalPartyList){
					locationVO.setName(locationIdadsubLocationMap.get(locationVO.getLocationId()));
					Map<String,Long> statusAndCountMap = locationIdAndStatusAndCountMap.get(locationVO.getLocationId());
					if(commonMethodsUtilService.isMapValid(statusAndCountMap)){
						for(ElectionInformationVO vo : locationVO.getSubList1()){
							if(statusAndCountMap.get(vo.getStatus().trim()) != null && statusAndCountMap.get(vo.getStatus().trim()) > 0)
							vo.setWonSeatsCount(statusAndCountMap.get(vo.getStatus().trim()));
						}
					
					}
					
				}
			}
		return finalPartyList;
		
		}catch(Exception e){
			Log.error("Exception raised in getElectionInformationLocationWiseVoterShare method of LocationWiseElectionInformationDetalsService"+e);
			return null;
		}
		
	}

	public ElectionInformationVO getMatchedElectionInformationVOByPartyName(List<ElectionInformationVO> partyVOList , String partyName){
		ElectionInformationVO returnVO =null;
		try {
			if(commonMethodsUtilService.isListOrSetValid(partyVOList) && partyName != null && partyName.trim().length()>0){
				for (ElectionInformationVO vo : partyVOList) {
					if(vo != null && vo.getPartyName() != null && vo.getPartyName().trim().equalsIgnoreCase(partyName.trim()))
						return vo;
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised in getElectionInformationLocationWiseVoterShare method of LocationDashboardService"+e);
		}
		return returnVO;
	}
	
	public List<ElectionInformationVO> buildSummaryForElectionResult(List<ElectionInformationVO> finalPartyList,Map<String,String> statusMap,Map<Long,List<Long>> electionIdAndLocationIdListMap,
			Map<String,ElectionInformationVO> statusVOMap){
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
												ElectionInformationVO rangeVO = new ElectionInformationVO();
												rangeVO.setStatus(statusMap.get(range));
												rangeVO.setRange(range.trim());
												rangeVO.setWonSeatsCount(statusCountMap.get(statusMap.get(range)));
												
												ElectionInformationVO rangeInfoVO = statusVOMap.get(range);
												if(rangeInfoVO != null){
													rangeVO.setMin(rangeInfoVO.getMin());
													rangeVO.setMax(rangeInfoVO.getMax());
													rangeVO.setRange(rangeInfoVO.getPartyName().trim());
													rangeVO.setRank(rangeInfoVO.getRank());
												}
												electionVO.getSubList1().add(rangeVO);
											}
											
											Collections.sort(electionVO.getSubList1(), new Comparator<ElectionInformationVO>() {
												public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
													return o1.getRank().compareTo(o2.getRank());
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
	private List<ElectionInformationVO> setLocationWiseStatus(Map<String, ElectionInformationVO> statusMap,Map<Long, ElectionInformationVO> locationMap,List<Long> partyIdsList) {
		List<ElectionInformationVO> finalList= new ArrayList<ElectionInformationVO>(0);
		try {
			for (Long locationId : locationMap.keySet()) {
				ElectionInformationVO locationVO = locationMap.get(locationId);
				if(locationVO != null && commonMethodsUtilService.isListOrSetValid(locationVO.getList())){
					
					for (ElectionInformationVO electionVO : locationVO.getList()) {
						if(electionVO != null && commonMethodsUtilService.isListOrSetValid(electionVO.getSubList2())){
							 Collections.sort( electionVO.getSubList2(),new Comparator<ElectionInformationVO>() {
								public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
									return o2.getEarnedVotes().compareTo(o1.getEarnedVotes());
								}
							});
						}
						
						if(commonMethodsUtilService.isListOrSetValid(electionVO.getSubList2())){
							 int ourPartyPositionNo = -1;
								for (int i=0;i<electionVO.getSubList2().size();) {
									ElectionInformationVO tempPartyVO = electionVO.getSubList2().get(i);
									tempPartyVO.setRank(Long.valueOf(String.valueOf(i+1)));
									if(tempPartyVO.getPartyId() != null && partyIdsList.contains(tempPartyVO.getPartyId().longValue())){
										ourPartyPositionNo =i;break;
									}
									i++;
								}
								
								if(ourPartyPositionNo == 0){// rank 1 - first position after sorting
									ElectionInformationVO rankOneVO = electionVO.getSubList2().get(ourPartyPositionNo);
									ElectionInformationVO rankTwoVO = null;
									if(electionVO.getSubList2() != null && electionVO.getSubList2().size()>1){
										rankTwoVO = electionVO.getSubList2().get(ourPartyPositionNo+1);
									}
									if(rankTwoVO != null && rankOneVO != null){
										Long marginVotes = rankOneVO.getEarnedVotes() - rankTwoVO.getEarnedVotes();
										rankOneVO.setMarginVotes(marginVotes);
										String rank1Perc= calculatePercentage(electionVO.getValidVoters(),rankOneVO.getEarnedVotes());
										String rank2Perc= calculatePercentage(electionVO.getValidVoters(),rankTwoVO.getEarnedVotes());
										
										Double marginPerc = Double.parseDouble(rank1Perc) - Double.parseDouble(rank2Perc);
										rankOneVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
										electionVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
										
										/*Long perc = Math.round(marginPerc);
										
										if(perc != null && perc.longValue()>0L){
											rankOneVO.setPerc(perc.toString());
											electionVO.setPerc(perc.toString());
										}else{
											rankOneVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
											electionVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
										}*/
										
										electionVO.setMarginVotes(marginVotes);
										
										for (String rangeValue : statusMap.keySet()) {
											ElectionInformationVO ststusVO = statusMap.get(rangeValue);
											if(ststusVO !=null){
												if(Double.parseDouble(electionVO.getPerc()) > 0.0d && 
														 Double.parseDouble(electionVO.getPerc()) >= Double.parseDouble(ststusVO.getMin().toString().trim()) &&  Double.parseDouble(electionVO.getPerc()) <= Double.parseDouble(ststusVO.getMax().toString().trim())){
													rankOneVO.setStatus(ststusVO.getPartyName().trim());
													electionVO.setStatus(ststusVO.getPartyName().trim());break;
												}else if(Double.parseDouble(electionVO.getPerc()) < 0.0d && 
														 Double.parseDouble(electionVO.getPerc()) >= Double.parseDouble(ststusVO.getMax().toString().trim()) &&  Double.parseDouble(electionVO.getPerc()) <= Double.parseDouble(ststusVO.getMin().toString().trim())){
													rankOneVO.setStatus(ststusVO.getPartyName().trim());
													electionVO.setStatus(ststusVO.getPartyName().trim());break;
												}
											}
										}
									}
								}else if(ourPartyPositionNo >0){
									// Not first position after sorting
									ElectionInformationVO rankOne1VO = electionVO.getSubList2().get(0);
									ElectionInformationVO nextRankVO = null;
									if(electionVO.getSubList2() != null && electionVO.getSubList2().size()>1){
										nextRankVO = electionVO.getSubList2().get(ourPartyPositionNo);
									}
									if(nextRankVO != null && rankOne1VO != null){
										Long marginVotes = nextRankVO.getEarnedVotes() - rankOne1VO.getEarnedVotes();
										nextRankVO.setMarginVotes(marginVotes);
										String rank1Perc= calculatePercentage(electionVO.getValidVoters(),rankOne1VO.getEarnedVotes());
										String rank2Perc= calculatePercentage(electionVO.getValidVoters(),nextRankVO.getEarnedVotes());
										
										Double marginPerc = Double.parseDouble(rank2Perc) - Double.parseDouble(rank1Perc);
										/*Long perc = Math.round(marginPerc);
										if(perc != null && perc.longValue()>0L){
											nextRankVO.setPerc(perc.toString());
											electionVO.setPerc(perc.toString());
										}else{
											nextRankVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
											electionVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
										}*/
										nextRankVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
										electionVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
										electionVO.setMarginVotes(marginVotes);
										
										for (String rangeValue : statusMap.keySet()) {
											ElectionInformationVO ststusVO = statusMap.get(rangeValue);
											if(ststusVO !=null){
												if(Double.parseDouble(electionVO.getPerc().toString()) > 0.0d && 
														 Double.parseDouble(electionVO.getPerc().toString()) >= Double.parseDouble(ststusVO.getMin().toString().trim()) &&  Double.parseDouble(electionVO.getPerc().toString()) <= Double.parseDouble(ststusVO.getMax().toString().trim())){
													nextRankVO.setStatus(ststusVO.getPartyName().trim());
													electionVO.setStatus(ststusVO.getPartyName().trim());break;
												}else if(Double.parseDouble(electionVO.getPerc().toString()) < 0.0d && 
														 Double.parseDouble(electionVO.getPerc().toString()) >= Double.parseDouble(ststusVO.getMax().toString().trim()) &&  Double.parseDouble(electionVO.getPerc().toString()) <= Double.parseDouble(ststusVO.getMin().toString().trim())){
													nextRankVO.setStatus(ststusVO.getPartyName().trim());
													electionVO.setStatus(ststusVO.getPartyName().trim());break;
												}
											}
										}
									}
								}
							if(ourPartyPositionNo >=0 && electionVO != null && commonMethodsUtilService.isListOrSetValid(electionVO.getSubList2())){
								List<ElectionInformationVO> electionPartyVOList = new ArrayList<ElectionInformationVO>(0);
								for (ElectionInformationVO partyVO : electionVO.getSubList2()) {
									if(partyVO.getPartyId() != null && partyIdsList.contains(partyVO.getPartyId().longValue()))
										electionPartyVOList.add(partyVO);break;
								}
								if(commonMethodsUtilService.isListOrSetValid(electionPartyVOList)){
									electionVO.getSubList2().clear();
									electionVO.getSubList2().addAll(electionPartyVOList);
								}
							}else{
								electionVO.setStatus("NOT PARTICIPATED");
							}
						}
					}
					finalList.add(locationVO);
				}
			}
		} catch (Exception e) {
			Log.error("Exception raised in setLocationWiseStatus method of LocationWiseElectionInformationDetalsService",e);
			return null;
		}
		/* for (Entry<Long, ElectionInformationVO> entry : locationMap.entrySet()) {
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
		 }*/
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
			List<Long> partyids,String withAlliance,Long levelId,List<Long> locationVals,List<String> subtypes,List<Long> electionScopeIds){
		
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
	
	public ElectionInformationVO getMatchedVO(List<ElectionInformationVO> list,Long partyId){
		try {
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (ElectionInformationVO electionInformationVO : list) {
					if(electionInformationVO.getPartyId().longValue() == partyId.longValue()){
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
	public List<PartyBoothPerformanceVO> getBoothWiseElectionResults(List<Long> partyIds, Long constituencyId, Long electionYears,Long electionScopeId,Long locationTypeId,Long locationValue) {
		
		try{
			List<PartyBoothPerformanceVO> boothResultsForParties = new ArrayList<PartyBoothPerformanceVO>();
			List<Long> constituencyIds = new ArrayList<Long>();
			String level = null;
			if(locationTypeId==5l || locationTypeId==6l || locationTypeId == 7l){
				level ="lowLevel";
				if(locationTypeId== 5l){
					List<Long> constiList = tehsilDAO.getAllConstituenciesByTehilId(locationValue);
					constituencyIds.addAll(constiList);
					constituencyId=locationValue;
				}if(locationTypeId== 6l){
					List<Long> constiList = tehsilDAO.getConstituencyByPanchayt(locationValue);
					constituencyIds.addAll(constiList);
					constituencyId=locationValue;
				}if(locationTypeId== 7l){
					List<Long> constiList = tehsilDAO.getConstituencyByLocalBody(locationValue);
					constituencyIds.addAll(constiList);
					constituencyId=locationValue;
				}
				
			}else{
				
				constituencyIds.add(constituencyId);
				
			}
				
			List<Long> electionIds = electionDAO.getElectionDetailsByYearAndElectionTypeForLocation(String.valueOf(electionYears), electionScopeId,1L);
			
			List<Object[]> wincandidatesList =null;
			if(locationTypeId !=6l && locationTypeId !=7l)
			wincandidatesList = nominationDAO.locationWisefindWonCandidateInConstituency(constituencyIds,String.valueOf(electionYears),electionScopeId);
			
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

			List<Object[]> boothWiseWonPartyList = candidateBoothResultDAO.locationWisefindboothWiseResultsForNominators(constituencyId,electionYears,locationTypeId);
			
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
						
						List<Object[]> candidateboothResults = candidateBoothResultDAO.LocatioWisefindboothWiseResultsForCandidate(constituencyId,param[13] != null ? Long.valueOf(param[13].toString()):0L, locationTypeId);
						
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
									Object [] partiesArr={"TDP","BJP","TDP+BJP","TDP/BJP","TDP & BJP","YSRC","INC","CPM","CPI","OTHERS"};
									/*List<BoothResultVO> returnList = (List<BoothResultVO>) commonMethodsUtilService.sortElectionInformationVOsList(boothResultVOs,"message",partiesArr);
									if(commonMethodsUtilService.isListOrSetValid(returnList)){							
										boothResultVOs.clear();
										boothResultVOs.addAll(returnList);
									}*/
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
					Object [] partiesArr={"TDP","BJP","TDP+BJP","TDP/BJP","TDP & BJP","YSRC","INC","CPM","CPI","OTHERS"};
					/*List<PartyBoothPerformanceVO> returnList = (List<PartyBoothPerformanceVO>) commonMethodsUtilService.sortElectionInformationVOsList(candidatesList,"partyName",partiesArr);
					if(commonMethodsUtilService.isListOrSetValid(returnList)){							
						candidatesList.clear();
						candidatesList.addAll(returnList);
					}*/		
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
	public List<ElectionInformationVO> getLocationWiseVotingDetails(List<Long> electionYrs,Long levelId,List<Long> locationVals,List<String> subtypes,String searchLevel,String clickType,List<Long> partyIds){
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
				List<Object[]> totalVoters = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel,clickType,partyIds);
				setLocationWiseVotersDetails( totalVoters, locationMap,"totalVoters",levelId,searchLevel,partyIds);
				
				
			}
			List<Object[]> polledVotes = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel,"",partyIds);
			setLocationWiseVotersDetails( polledVotes, locationMap,"polled",levelId,searchLevel,partyIds);
			List<Object[]> assemblyEarnedVotes = null;
			
				 assemblyEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel, 2l,partyIds);
			setLocationWiseVotersDetails( assemblyEarnedVotes, locationMap,"assembly",levelId,searchLevel,partyIds);
			
			List<Object[]> parliamentEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, searchLevel, 1l,partyIds);
			setLocationWiseVotersDetails( parliamentEarnedVotes, locationMap,"parliament",levelId,searchLevel,partyIds);
			
			if(levelId != null && levelId.longValue() == 4l){
				List<Object[]> muncipalTotalVotes = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality",clickType,partyIds);
				setLocationWiseVotersDetails( muncipalTotalVotes, locationMap,"totalVoters",levelId,"muncipality",partyIds);
				List<Object[]> muncipalPolledVotes = boothConstituencyElectionDAO.getLocationWisePolledVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality","",partyIds);
				setLocationWiseVotersDetails( muncipalPolledVotes, locationMap,"polled",levelId,"muncipality",partyIds);
				
				List<Object[]> muncipalAssemblyEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality", 2l,partyIds);
				setLocationWiseVotersDetails( muncipalAssemblyEarnedVotes, locationMap,"assembly",levelId,"muncipality",partyIds);
				
				List<Object[]> muncipalParliamentEarnedVotes = boothConstituencyElectionDAO.getLocationWiseErnedVotesForVotingDetails(electionYrs, levelId, locAssmblyVals, subtypes, "muncipality", 1l,partyIds);
				setLocationWiseVotersDetails( muncipalParliamentEarnedVotes, locationMap,"parliament",levelId,"muncipality",partyIds);
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
	public void setLocationWiseVotersDetails(List<Object[]> polledVotes,Map<Long,ElectionInformationVO> yearsPolledMap,String type,Long locationTypeId,String searchLevel,List<Long> partyids){
		try{
			Long locationLevelId = 0l;
			if(locationTypeId.longValue() == 2l ){
				locationLevelId  = 3l;
			}
			if( locationTypeId.longValue() == 10l || locationTypeId.longValue() == 3l){
				locationLevelId  = 4l;
		    }else if(locationTypeId.longValue() == 4l ){
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
					ElectionInformationVO yearVO = getMatchedLocationVO(locationVO.getList(), commonMethodsUtilService.getLongValueForObject(param[3]));
					if(yearVO == null){
						yearVO = new ElectionInformationVO();
						yearVO.setElectionId(commonMethodsUtilService.getLongValueForObject(param[3]));
						yearVO.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[3]));
						yearVO.setList(setTemplateForParties(partyids));//parties List
						locationVO.getList().add(yearVO);
					}
					ElectionInformationVO partyVO = getMatchedLocationVO(yearVO.getList(), commonMethodsUtilService.getLongValueForObject(param[4]));
					if(partyVO != null){
						if(type != null &&type.equalsIgnoreCase("totalVoters")){
							partyVO.setTotalVoters(partyVO.getTotalVoters()+commonMethodsUtilService.getLongValueForObject(param[2]));
						}else if(type != null &&type.equalsIgnoreCase("polled")){
							partyVO.setValidVoters(partyVO.getValidVoters()+commonMethodsUtilService.getLongValueForObject(param[2]));
						}else if(type != null &&type.equalsIgnoreCase("assembly")){
							partyVO.setAssemblyEarndVotes(partyVO.getAssemblyEarndVotes()+commonMethodsUtilService.getLongValueForObject(param[2]));
							partyVO.setEarnedVotersPerc(Double.valueOf(calculatePercentage(partyVO.getValidVoters(), partyVO.getAssemblyEarndVotes())));
							//yearVO.setParliamentValidVoters(commonMethodsUtilService.getLongValueForObject(param[1]));
						}else if(type != null &&type.equalsIgnoreCase("parliament")){
							partyVO.setParliamentEarnedVotes(commonMethodsUtilService.getLongValueForObject(param[2]));
							partyVO.setEarnedVotersPerc1(Double.valueOf(calculatePercentage(partyVO.getValidVoters(), partyVO.getParliamentEarnedVotes())));
							Double parliamentPerc = partyVO.getEarnedVotersPerc1() != null ?partyVO.getEarnedVotersPerc1():0.0;
							Double assmblyPerc = partyVO.getEarnedVotersPerc() != null ?partyVO.getEarnedVotersPerc():0.0;
							Double diffPerc = assmblyPerc-parliamentPerc;
							partyVO.setPerc(diffPerc.toString());
						}
					}
					if(type != null &&type.equalsIgnoreCase("totalVoters")){
						locationVO.setTotalVoters(locationVO.getTotalVoters()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(type != null &&type.equalsIgnoreCase("polled")){
						locationVO.setValidVoters(locationVO.getValidVoters()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}else if(type != null &&type.equalsIgnoreCase("assembly")){
						locationVO.setAssemblyEarndVotes(locationVO.getAssemblyEarndVotes()+commonMethodsUtilService.getLongValueForObject(param[2]));
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
	List<Long> electionYrs,List<Long> electionScopeIds, List<String> subTypes,String searchType,String statusType,String year,List<Long> locationIds,String withAllaince){
	
	List<ElectionInformationVO> finalList=new ArrayList<ElectionInformationVO>(0);
	Map<String,Map<String,Long>>  yearAndStatusCountMap=new HashMap<String,Map<String,Long>>();
	Map<String,Map<String,List<Long>>> YearAndstatusAndLocationIdsMap=new HashMap<String,Map<String,List<Long>>>();
	try{
		List<ElectionInformationVO> electionInformationVOList=getElectionInformationLocationWiseStatus(locationTypeId,locationValue,partyIdList,electionYrs,electionScopeIds,subTypes,searchType,withAllaince);
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
						if(subvo.getElectionYear() !=null && subvo.getElectionYear().length() >0 && year != null && year.trim().length() > 0&& !subvo.getElectionYear().equalsIgnoreCase(year) )
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


public ElectionInformationVO getMatchedBylocationIdLocationVO(List<ElectionInformationVO> list,Long locationId){
	try {
		if(commonMethodsUtilService.isListOrSetValid(list)){
			for (ElectionInformationVO electionInformationVO : list) {
				if(electionInformationVO.getLocationId().longValue() == locationId.longValue()){
					return electionInformationVO;
				}
			}
		}
	} catch (Exception e) {
		Log.error("Exception raised at getMatchedVO service"+e);
	}
	return null;
}

	public List<ElectionInformationVO> getLocationBasedCrossVotingReult(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIdsList,List<Long> electionScopeIds,String withAlliance){
		List<ElectionInformationVO> finalList = new ArrayList<ElectionInformationVO>();
		try{
			Map<Long, ElectionInformationVO> parliamentMap= new HashMap<Long, ElectionInformationVO>();
			Map<Long,Long> assemblyParliamentMap = new HashMap<Long, Long>(0);
			Map<Long,ElectionInformationVO> parliamentDtlsMap = new HashMap<Long, ElectionInformationVO>(0);
			Set<Long> partyIdsList1 = new HashSet<Long>(0);
			List<Long> availableAssemblyIdsList = new ArrayList<Long>(0);
			List<ElectionInformationVO> tehsilREsults = new ArrayList<ElectionInformationVO>(0);
			Long selectedPartyId = partyIdsList.get(0);
			Long glAssemblyId=0L;
			Long glParliamentId=0L;
			String locationTypeStr="Mandal";
			if(!commonMethodsUtilService.isListOrSetValid(locationValues)){
				String[] parliamentIdsArr = IConstants.AP_PARLIAMENT_IDS_LIST;
				if(parliamentIdsArr != null && parliamentIdsArr.length>0){
					for (int i = 0; i < parliamentIdsArr.length; i++) {
						locationValues.add(Long.valueOf(parliamentIdsArr[i].trim()));
					}
				}
				
				List<Object[]> assemblyIdsList = constituencyDAO.getConstituenciesByStateId(1L);
				if(commonMethodsUtilService.isListOrSetValid(assemblyIdsList)){
					for (Object[] param : assemblyIdsList) {
						availableAssemblyIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
				
			}else if(locationTypeId.longValue()==3L){
				Long districtId =locationValues.get(0);
				List<Object[]> assemblyIdsList = constituencyDAO.getAssemblyConstituencyDetlsByDistrictIds(locationValues);
				if(commonMethodsUtilService.isListOrSetValid(assemblyIdsList)){
					for (Object[] param : assemblyIdsList) {
						availableAssemblyIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
				List<Object[]> parliamentIdsList = delimitationConstituencyAssemblyDetailsDAO.findParliamentConstituenciesByDistrictId(districtId,2009L);
				if(commonMethodsUtilService.isListOrSetValid(parliamentIdsList)){
					locationValues.clear();
					for (Object[] param : parliamentIdsList) {
						locationValues.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						
						List<Object[]> assemblyIdList = delimitationConstituencyAssemblyDetailsDAO.getAllAssembliesOfParliament(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(commonMethodsUtilService.isListOrSetValid(assemblyIdList)){
							for (Object[] params : assemblyIdList) {
								availableAssemblyIdsList.add(commonMethodsUtilService.getLongValueForObject(params[0]));
							}
						}
					}
				}
			}else if(locationTypeId.longValue()==10L){
				Long pId =locationValues.get(0);
				List<Object[]> assemblyIdsList = delimitationConstituencyAssemblyDetailsDAO.getAllAssembliesOfParliament(pId);
				if(commonMethodsUtilService.isListOrSetValid(assemblyIdsList)){
					for (Object[] param : assemblyIdsList) {
						availableAssemblyIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
				}
			}else if(locationTypeId.longValue()==4L){
				List<Long> tehsilIdsList  = boothDAO.getTehsildByConstituency(locationValues.get(0), IConstants.VOTER_DATA_PUBLICATION_ID);
				if(commonMethodsUtilService.isListOrSetValid(tehsilIdsList))					
					return getLocationBasedCrossVotingReult(electionYears,5L,tehsilIdsList,electionId,subTypes,partyIdsList,electionScopeIds,withAlliance);
			}else if(locationTypeId.longValue()==5L){
				if(locationValues.size()==1L){
					List<Long> panchayatList  = boothDAO.getPanchayatsIdsListByTehsilId(locationValues, IConstants.VOTER_DATA_PUBLICATION_ID);
					if(commonMethodsUtilService.isListOrSetValid(panchayatList))					
						return getLocationBasedCrossVotingReult(electionYears,6L,panchayatList,electionId,subTypes,partyIdsList,electionScopeIds,withAlliance);
				}
				if(locationValues.size()>1){
					locationTypeStr="Mandal";
					List<Long> assemblyIdsList = boothDAO.getConstituencyDetailsByTehsilId(locationValues.get(0));
					if(commonMethodsUtilService.isListOrSetValid(assemblyIdsList)){
						glAssemblyId=assemblyIdsList.get(0);
						availableAssemblyIdsList.addAll(locationValues);
						locationValues.clear();
						for (Long id : availableAssemblyIdsList) {
							parliamentDtlsMap.put(id, new ElectionInformationVO(id,tehsilDAO.get(id).getTehsilName()));
						}
					}
				}
			}else if(locationTypeId.longValue()==6L){
					locationTypeStr="panchayat";
					List<Long> assemblyIdsList = boothDAO.getConstituencyForPanchayat(locationValues.get(0));
					List<Object[]> list = boothDAO.getBoothsByPanchayatIDsListConstiId(locationValues,assemblyIdsList.get(0),IConstants.VOTER_DATA_PUBLICATION_ID);
					if(commonMethodsUtilService.isListOrSetValid(assemblyIdsList)){
						glAssemblyId=assemblyIdsList.get(0);
						availableAssemblyIdsList.addAll(locationValues);
						locationValues.clear();
						if(commonMethodsUtilService.isListOrSetValid(list)){
							for (Object[] param : list) {
								parliamentDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), new ElectionInformationVO(commonMethodsUtilService.getLongValueForObject(param[2]),commonMethodsUtilService.getStringValueForObject(param[3])));
							}
						}
					}
			}else if(locationTypeId.longValue()==7L){
				locationTypeStr="Munci/Corp/Greater City";
				List<Long> assemblyIdsList = boothDAO.getConstituencyIdsByLebId(locationValues.get(0).longValue());
				if(commonMethodsUtilService.isListOrSetValid(assemblyIdsList)){
					glAssemblyId=assemblyIdsList.get(0);
					List<Object[]> parliamentList = parliamentAssemblyDAO.getParliamentByAssemblyId(assemblyIdsList.get(0));
					if(commonMethodsUtilService.isListOrSetValid(parliamentList)){
						availableAssemblyIdsList.add(locationValues.get(0));
						locationValues.clear();
						for (Object[] param : parliamentList) {
							locationValues.add(commonMethodsUtilService.getLongValueForObject(param[0]));
							parliamentDtlsMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), new ElectionInformationVO(commonMethodsUtilService.getLongValueForObject(param[0]),commonMethodsUtilService.getStringValueForObject(param[1])));
						}
					}
				}
			}
			 
			List<Object[]> assemblyList =parliamentAssemblyDAO.getConsIdsByParliamntId(locationValues);
			 for (Object[] objects : assemblyList) {
				 Long parliamentId = commonMethodsUtilService.getLongValueForObject(objects[0]);
				ElectionInformationVO parliamentVO =parliamentMap.get(parliamentId);
				if(parliamentVO == null){
					parliamentVO= new ElectionInformationVO();
					parliamentVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[0]));//parliamentId
					parliamentVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[1]));
					
				}
					ElectionInformationVO assemblyVO = new ElectionInformationVO();
					if(locationTypeId.longValue()==2L || locationTypeId.longValue()==3L || locationTypeId.longValue()==4L || locationTypeId.longValue()==10L){
						assemblyVO.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[2]));//assemblyid
						assemblyVO.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[3]));
						if((locationTypeId.longValue()==3L || locationTypeId.longValue() == 4L) && availableAssemblyIdsList.contains(assemblyVO.getLocationId())){
							parliamentVO.getList().add(assemblyVO);
							assemblyParliamentMap.put(assemblyVO.getLocationId(), parliamentId);
							parliamentMap.put(parliamentVO.getLocationId(), parliamentVO);
						}else if(locationTypeId.longValue()!=3L && locationTypeId.longValue() != 4L){
							parliamentVO.getList().add(assemblyVO);
							assemblyParliamentMap.put(assemblyVO.getLocationId(), parliamentId);
							parliamentMap.put(parliamentVO.getLocationId(), parliamentVO);
							availableAssemblyIdsList.add(assemblyVO.getLocationId());
						}
					}else if (locationTypeId.longValue()==5L || locationTypeId.longValue()==6L  || locationTypeId.longValue()==7L){
						
						if(commonMethodsUtilService.isListOrSetValid(availableAssemblyIdsList)){
							for (Long tehsilId :availableAssemblyIdsList) {
								
								 parliamentVO = new ElectionInformationVO();
								 if(parliamentDtlsMap.get(tehsilId) != null){
									 parliamentVO.setLocationId(tehsilId);
									 parliamentVO.setLocationName(parliamentDtlsMap.get(parliamentVO.getLocationId()).getLocationName());
									 
									 assemblyVO = new ElectionInformationVO ();
									 assemblyVO.setLocationId(tehsilId);
									 assemblyVO.setLocationName(parliamentDtlsMap.get(parliamentVO.getLocationId()).getLocationName());
									 
									 if(parliamentVO.getLocationId().longValue() == tehsilId.longValue())
											parliamentVO.getList().add(assemblyVO);
								 }
								
								parliamentMap.put(parliamentVO.getLocationId(), parliamentVO);
								assemblyParliamentMap.put(assemblyVO.getLocationId(), parliamentId);
								glParliamentId = parliamentId;
							}
						}
						break;
					}else{
						
						assemblyVO.setLocationId(availableAssemblyIdsList.get(0));
						if(parliamentDtlsMap.get(parliamentVO.getLocationId()) != null){
							assemblyVO.setLocationName(parliamentDtlsMap.get(parliamentVO.getLocationId()).getPartyName());
							//assemblyVO.setLocationName( commonMethodsUtilService.getStringValueForObject(objects[3]) + " "+locationTypeStr+" ("+assemblyVO.getLocationName()+" Parliament)");
							assemblyVO.setLocationName(assemblyVO.getLocationName()+" Parliament");
						}
						parliamentVO.setLocationId(availableAssemblyIdsList.get(0));//mandal/muncipality/panchayat
						parliamentVO.getList().add(assemblyVO);
						parliamentMap.put(parliamentVO.getLocationId(), parliamentVO);
						assemblyParliamentMap.put(assemblyVO.getLocationId(), parliamentId);
						break;
					}
					
			 }
			 
			 List<Long> scopeIdsList = new ArrayList<Long>();
			 scopeIdsList.add(1L);
			 
			 if(locationTypeId != null && (  locationTypeId.longValue() == 5L || locationTypeId.longValue() == 6L || locationTypeId.longValue() == 7L) 
					  && commonMethodsUtilService.isListOrSetValid(availableAssemblyIdsList)){
					locationValues.clear();
					locationValues.addAll(availableAssemblyIdsList);
			 }
			 
			 List<Object[]> parliamentResultList = electionDAO.getElectionDetailsForCrossVoting(electionYears,locationTypeId,locationValues, electionId, subTypes, null, scopeIdsList,"parliament");
			 
			 if(locationTypeId != null && (locationTypeId.longValue() == 10L || locationTypeId.longValue() == 4L || locationTypeId.longValue() == 3L) && commonMethodsUtilService.isListOrSetValid(availableAssemblyIdsList)){
					locationValues.clear();
					locationValues.addAll(availableAssemblyIdsList);
			 }
			 List<Object[]> assemblyWsieParliamentResultList = electionDAO.getElectionDetailsForCrossVoting(electionYears,locationTypeId,locationValues, electionId, subTypes, null, scopeIdsList,"assembly");
			 
			 scopeIdsList.clear();
			 scopeIdsList.add(2L);
			 if(commonMethodsUtilService.isListOrSetValid(availableAssemblyIdsList)){
					locationValues.clear();
					locationValues.addAll(availableAssemblyIdsList);
			}
			 List<Object[]> assemblyResultList = electionDAO.getElectionDetailsForCrossVoting(electionYears,locationTypeId,locationValues, electionId, subTypes, null, scopeIdsList,"assembly");
			 
			 if (locationTypeId.longValue()==5L){
				 List<Long> lebIdsList =  boothDAO.getLocalElectionsBodyList(locationValues);
				 List<Object[]> lebPAResultsList = electionDAO.getElectionDetailsForCrossVoting(electionYears,7L,lebIdsList, electionId, subTypes, null, scopeIdsList,"parliament");
				 List<Object[]> assemblyWiseLEBResultList = electionDAO.getElectionDetailsForCrossVoting(electionYears,7L,lebIdsList, electionId, subTypes, null, scopeIdsList,"assembly");
				 
				 scopeIdsList.clear();
				 scopeIdsList.add(1L);
				 
				 List<Object[]> assemblyWiselebResultsList = electionDAO.getElectionDetailsForCrossVoting(electionYears,7L,lebIdsList, electionId, subTypes, null, scopeIdsList,"parliament");

				 if(commonMethodsUtilService.isListOrSetValid(assemblyWiseLEBResultList)){
					 if(!commonMethodsUtilService.isListOrSetValid(assemblyResultList))
						 assemblyResultList = new ArrayList<Object[]>(0);
						 
					 assemblyResultList.addAll(assemblyWiseLEBResultList);
				 }
				 if(commonMethodsUtilService.isListOrSetValid(assemblyWiselebResultsList)){
					 if(!commonMethodsUtilService.isListOrSetValid(assemblyWsieParliamentResultList))
						 assemblyWsieParliamentResultList = new ArrayList<Object[]>(0);
						 
					 assemblyWsieParliamentResultList.addAll(assemblyWiselebResultsList);
				 }if(commonMethodsUtilService.isListOrSetValid(lebPAResultsList)){
					 if(!commonMethodsUtilService.isListOrSetValid(parliamentResultList))
						 parliamentResultList = new ArrayList<Object[]>(0);
						 
					 parliamentResultList.addAll(lebPAResultsList);
				 }
				 
				 if(commonMethodsUtilService.isListOrSetValid(lebIdsList)){
					 for (Long lebId : lebIdsList) {
						 ElectionInformationVO parliamentVO = new ElectionInformationVO();
						 ElectionInformationVO  assemblyVO = new ElectionInformationVO ();
						LocalElectionBody leb =  localElectionBodyDAO.get(lebId);
						 parliamentVO.setLocationId(lebId);
						 parliamentVO.setLocationName(leb.getName()+" "+leb.getElectionType().getElectionType());
						
						 assemblyVO.setLocationId(lebId);
						 assemblyVO.setLocationName(leb.getName()+" "+leb.getElectionType().getElectionType());
						 
						parliamentVO.getList().add(assemblyVO);
						
						parliamentMap.put(parliamentVO.getLocationId(), parliamentVO);
						assemblyParliamentMap.put(assemblyVO.getLocationId(), glParliamentId);
					}
				 }
			 }
			 
			 Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupIdMap = null;
				if(withAlliance != null && withAlliance.trim().equalsIgnoreCase("true"))
					alliancedPartiesWithGroupIdMap = getSegregateAliancePartiesMap(subTypes,electionYears,electionScopeIds);
				if(!commonMethodsUtilService.isMapValid(alliancedPartiesWithGroupIdMap))
					alliancedPartiesWithGroupIdMap = new HashMap<Long, Map<Long,ElectionInformationVO>>(0);
				
			Map<Long,String> partyIdNamesListMap = new HashMap<Long,String>();
			Set<Long> retrievedPartyIds = new HashSet<Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(parliamentResultList)){
				for (Object[] param : parliamentResultList) {
					retrievedPartyIds.add(commonMethodsUtilService.getLongValueForObject(param[6]));
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(assemblyResultList)){
				for (Object[] param : assemblyResultList) {
					retrievedPartyIds.add(commonMethodsUtilService.getLongValueForObject(param[6]));
				}
			}
			if (retrievedPartyIds != null && retrievedPartyIds.size() > 0) {
				List<Object[]> partyList = partyDAO.getPartyShortNameByIds(new ArrayList<Long>(retrievedPartyIds));

				if (partyList != null && partyList.size() > 0) {
					for (Object[] param : partyList) {
						partyIdNamesListMap.put(commonMethodsUtilService.getLongValueForObject(param[0]),param[1] != null ? param[1].toString().trim() : "");
					}
				}
			}
				
			String partyNameStr = "";
			Map<Long,Long> partyAssemblyWiseParliamentEarnedVotesMap = new HashMap<Long, Long>(0);
			
			if(commonMethodsUtilService.isListOrSetValid(assemblyWsieParliamentResultList)){
				for (Object[] param : assemblyWsieParliamentResultList) {
					Long assembliId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long partyId =commonMethodsUtilService.getLongValueForObject(param[6]);
					String partyName = commonMethodsUtilService.getStringValueForObject(param[7]);
					Long tempElectionId = commonMethodsUtilService.getLongValueForObject(param[3]);
					
					if(assembliId != null && assembliId.longValue()>0L){
						Long earnedCount = partyAssemblyWiseParliamentEarnedVotesMap.get(assembliId);
						if(earnedCount == null)
							earnedCount=0L; 
						if(commonMethodsUtilService.isMapValid(alliancedPartiesWithGroupIdMap)){
							
							List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyId);
							if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
								
								partyId = Long.valueOf(alliancePartyIdNameList.get(0));
								partyName = alliancePartyIdNameList.get(1);
								
								List<String> selectedAlliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyIdsList.get(0));
								if(commonMethodsUtilService.isListOrSetValid(selectedAlliancePartyIdNameList)){
									Long tempPartyId = Long.valueOf(selectedAlliancePartyIdNameList.get(0));
									if(tempPartyId.longValue() == partyId.longValue()){
										partyIdsList1.add(partyId);
										partyNameStr = alliancePartyIdNameList.get(2);
									}
								}
							}
							
							if(partyName != null && partyName.trim().length()>0 && partyNameStr.contains(partyName)){
								partyAssemblyWiseParliamentEarnedVotesMap.put(assembliId, earnedCount.longValue()+commonMethodsUtilService.getLongValueForObject(param[9]));
							}
							else if(partyIdsList1.contains(partyId)){
								partyAssemblyWiseParliamentEarnedVotesMap.put(assembliId, earnedCount.longValue()+commonMethodsUtilService.getLongValueForObject(param[9]));
							}
							else if(partyIdsList.contains(partyId)){
								partyAssemblyWiseParliamentEarnedVotesMap.put(assembliId, earnedCount.longValue()+commonMethodsUtilService.getLongValueForObject(param[9]));
							}
						}else if(partyIdsList.contains(partyId)){
							partyAssemblyWiseParliamentEarnedVotesMap.put(assembliId, earnedCount.longValue()+commonMethodsUtilService.getLongValueForObject(param[9]));
						}
					}
				}
			}
				
			if(commonMethodsUtilService.isListOrSetValid(parliamentResultList)){
				for (Object[] param : parliamentResultList) {
					//Long electionTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long parliamntId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long assembliId = 0L;
					if(locationTypeId.longValue() == 2L && commonMethodsUtilService.isListOrSetValid(locationValues) && locationValues.size()==1){
						Long assemblyId = commonMethodsUtilService.getLongValueForObject(param[1]);
						assembliId = assemblyId;
						parliamntId = assemblyParliamentMap.get(assemblyId);
					}else if(locationTypeId.longValue() == 10L){//parliamntId nothing but an assembly id here
						parliamntId = commonMethodsUtilService.getLongValueForObject(param[1]);							
					}else if(locationTypeId.longValue() == 3L){						
						parliamntId = commonMethodsUtilService.getLongValueForObject(param[1]);
					}
					
					if(parliamntId != null && parliamntId.longValue()>0L){
						Long partyId =commonMethodsUtilService.getLongValueForObject(param[6]);
						String partyName = commonMethodsUtilService.getStringValueForObject(param[7]);
						if(partyName != null && partyName.trim().length()==0)
							partyName = partyIdNamesListMap.get(partyId);
						
						Long tempElectionId = commonMethodsUtilService.getLongValueForObject(param[3]);
						//if(selectedPartyId.longValue() == partyId.longValue()){
							List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyId);
							if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
								
								partyId = Long.valueOf(alliancePartyIdNameList.get(0));
								partyName = alliancePartyIdNameList.get(1);
								
								List<String> selectedAlliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyIdsList.get(0));
								if(commonMethodsUtilService.isListOrSetValid(selectedAlliancePartyIdNameList)){
									Long tempPartyId = Long.valueOf(selectedAlliancePartyIdNameList.get(0));
									if(tempPartyId.longValue() == partyId.longValue()){
										partyIdsList1.add(partyId);
										partyNameStr = alliancePartyIdNameList.get(2);
									}
								}
							}
						//}
						
						
						ElectionInformationVO parliamentVO =parliamentMap.get(parliamntId);
						if(parliamentVO != null){
							ElectionInformationVO parliamentPartyVO =  getMatchedVO(parliamentVO.getSubList2(), partyId);
							if(locationTypeId.longValue() == 5L || locationTypeId.longValue() == 6L || locationTypeId.longValue() == 7L){
								if(parliamentDtlsMap.get(parliamntId) != null)
									parliamentVO.setLocationName(parliamentDtlsMap.get(parliamntId).getPartyName());
								else if(locationTypeId.longValue() == 5L)
									parliamentVO.setLocationName(tehsilDAO.get(parliamntId).getTehsilName());
								else if(locationTypeId.longValue() == 6L && panchayatDAO.get(parliamntId) != null )
									parliamentVO.setLocationName(panchayatDAO.get(parliamntId).getPanchayatName());
									
							}
							if(parliamentPartyVO == null){
								parliamentPartyVO = new ElectionInformationVO();
								parliamentVO.getSubList2().add(parliamentPartyVO);
							}
							parliamentPartyVO.setPartyId(partyId);
							parliamentPartyVO.setPartyName(partyName);
							parliamentPartyVO.setMpCandidateEarnedVotes(parliamentPartyVO.getMpCandidateEarnedVotes()+commonMethodsUtilService.getLongValueForObject(param[9]));
							parliamentVO.setMpCandidateEarnedVotes(parliamentVO.getMpCandidateEarnedVotes()+commonMethodsUtilService.getLongValueForObject(param[9]));
						}
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(assemblyResultList)){
				for (Object[] param : assemblyResultList) {
					//Long electionTypeId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long assemblyId = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long parliamntId = assemblyParliamentMap.get(assemblyId);
					if(locationTypeId.longValue()==5L || locationTypeId.longValue()==6L || locationTypeId.longValue()==7L)
						 parliamntId = assemblyId;// mandal/muncipality/panchayat
					ElectionInformationVO parliamentVO =parliamentMap.get(parliamntId);
					if(parliamentVO != null){
						Long partyId =commonMethodsUtilService.getLongValueForObject(param[6]);
						String partyName = commonMethodsUtilService.getStringValueForObject(param[7]);
						if(partyName != null && partyName.trim().length()==0)
							partyName = partyIdNamesListMap.get(partyId);
						//if(selectedPartyId.longValue() == partyId.longValue()){
							if(!commonMethodsUtilService.isListOrSetValid(locationValues) && locationValues.size()==1){
								Long tempElectionId = commonMethodsUtilService.getLongValueForObject(param[3]);
								List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyId);
								if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
									partyId = Long.valueOf(alliancePartyIdNameList.get(0));
									partyName = alliancePartyIdNameList.get(1);
									
									if(commonMethodsUtilService.isListOrSetValid(partyIdsList1)){
										for (Long  id : partyIdsList1) {
											partyId = id;
										}
									}
									
									List<String> selectedAlliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyIdsList.get(0));
									if(commonMethodsUtilService.isListOrSetValid(selectedAlliancePartyIdNameList)){
										Long tempPartyId = Long.valueOf(selectedAlliancePartyIdNameList.get(0));
										if(tempPartyId.longValue() == partyId.longValue()){
											if(partyIdsList1.isEmpty())
												partyIdsList1.add(partyId);
											partyNameStr = alliancePartyIdNameList.get(2);
										}
									}
								}
							}else{
								
								Long tempElectionId = commonMethodsUtilService.getLongValueForObject(param[3]);
								List<String> alliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyId);
								if(commonMethodsUtilService.isListOrSetValid(alliancePartyIdNameList)){
										partyId = Long.valueOf(alliancePartyIdNameList.get(0));
										partyName = alliancePartyIdNameList.get(1);
										
										if(commonMethodsUtilService.isListOrSetValid(partyIdsList1)){
											for (Long  id : partyIdsList1) {
												partyId = id;
											}
										}
										
									List<String> selectedAlliancePartyIdNameList  = findMatchedPartyId(alliancedPartiesWithGroupIdMap,tempElectionId,partyIdsList.get(0));
									if(commonMethodsUtilService.isListOrSetValid(selectedAlliancePartyIdNameList)){
										Long tempPartyId = Long.valueOf(selectedAlliancePartyIdNameList.get(0));
										if(tempPartyId.longValue() == partyId.longValue()){
											if(partyIdsList1.isEmpty())
												partyIdsList1.add(partyId);
											partyNameStr = alliancePartyIdNameList.get(2);
										}
									}
								}
							}
						//}
						
						if(commonMethodsUtilService.isListOrSetValid(partyIdsList1)){
							partyIdsList.clear();
							partyIdsList.addAll(partyIdsList1);
						}
					
						for (ElectionInformationVO assemblyVO : parliamentVO.getList()) {
							if(locationTypeId.longValue() == 5L || locationTypeId.longValue() == 6L || locationTypeId.longValue() == 7L){
								if(parliamentDtlsMap.get(parliamntId) != null)
									assemblyVO.setLocationName(parliamentDtlsMap.get(parliamntId).getPartyName());
								else if(locationTypeId.longValue() == 5L)
									assemblyVO.setLocationName(tehsilDAO.get(parliamntId).getTehsilName());
								else if(locationTypeId.longValue() == 6L && panchayatDAO.get(parliamntId) != null )
									assemblyVO.setLocationName(panchayatDAO.get(parliamntId).getPanchayatName());
							}
							if(assemblyVO.getLocationId().longValue() == assemblyId.longValue()){
								ElectionInformationVO partyVO =  getMatchedVO(assemblyVO.getList(), partyId);
								if(partyVO == null){
									partyVO = new ElectionInformationVO();
									assemblyVO.getList().add(partyVO);
								}
								
								partyVO.setPartyId(partyId);
								partyVO.setPartyName(partyName);
								partyVO.setMlaCandidateEarnedVotes(partyVO.getMlaCandidateEarnedVotes()+commonMethodsUtilService.getLongValueForObject(param[9]));
								assemblyVO.setMlaCandidateEarnedVotes(assemblyVO.getMlaCandidateEarnedVotes()+partyVO.getMlaCandidateEarnedVotes());
								if(commonMethodsUtilService.isMapValid(alliancedPartiesWithGroupIdMap)){
									if(partyNameStr.contains(partyName)){
										if(commonMethodsUtilService.isListOrSetValid(locationValues) && locationValues.size()==1){
											partyVO.setPartyId(partyIdsList.get(0));
											parliamentVO.setMlaCandidateEarnedVotes(parliamentVO.getMlaCandidateEarnedVotes()+commonMethodsUtilService.getLongValueForObject(param[9]));
										}
									}else if(partyIdsList.contains(partyId))
										parliamentVO.setMlaCandidateEarnedVotes(parliamentVO.getMlaCandidateEarnedVotes()+commonMethodsUtilService.getLongValueForObject(param[9]));
								}else if(partyIdsList.contains(partyId)){
										parliamentVO.setMlaCandidateEarnedVotes(parliamentVO.getMlaCandidateEarnedVotes()+commonMethodsUtilService.getLongValueForObject(param[9]));
								}
								break;
							}
						}
					}
				}
			}
			if(commonMethodsUtilService.isListOrSetValid(partyIdsList1)){
				partyIdsList.clear();
				partyIdsList.addAll(partyIdsList1);
			}
			if(commonMethodsUtilService.isMapValid(parliamentMap)){
				for (Long parliamentId : parliamentMap.keySet()) {
					ElectionInformationVO parliamentVO = parliamentMap.get(parliamentId);
				//	if(locationTypeId!=2l){
						if(parliamentVO != null && commonMethodsUtilService.isListOrSetValid(parliamentVO.getList())){
							for (ElectionInformationVO assemblyVO : parliamentVO.getList()) {
								if(assemblyVO != null && commonMethodsUtilService.isListOrSetValid(assemblyVO.getList())){
									Collections.sort(assemblyVO.getList(), new Comparator<ElectionInformationVO>() {
										public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
											Long o2Count = o2.getMlaCandidateEarnedVotes() != null?o2.getMlaCandidateEarnedVotes():0L;
											Long o1Count = o1.getMlaCandidateEarnedVotes() != null?o1.getMlaCandidateEarnedVotes():0L;
											return o2Count.compareTo(o1Count);
										}
									});
									
									for (Long partyId : partyIdsList) {
										ElectionInformationVO returnPartyVO = new ElectionInformationVO();
										
										int ourPartyPositionNo=-1;
										for (int i = 0; i < assemblyVO.getList().size();) {
											ElectionInformationVO partyVO =  assemblyVO.getList().get(i);
											
											Long parliaentVotesCount = partyVO.getMpCandidateEarnedVotes();
											
											if(commonMethodsUtilService.isMapValid(partyAssemblyWiseParliamentEarnedVotesMap)){
												parliaentVotesCount = partyAssemblyWiseParliamentEarnedVotesMap.get(assemblyVO.getLocationId());
												if(parliaentVotesCount==null){
													if(locationTypeId.longValue() == 10L)
														parliaentVotesCount=parliamentVO.getMpCandidateEarnedVotes();
													else
														parliaentVotesCount = 0L;
												}
												partyVO.setMpCandidateEarnedVotes(parliaentVotesCount);
											}
											returnPartyVO.setMpCandidateEarnedVotes(parliaentVotesCount);
											
											if(partyVO.getPartyId().longValue() == partyId.longValue()){
												ourPartyPositionNo = i;
												
												Long validVotesCount = assemblyVO.getMlaCandidateEarnedVotes();
												Long assemblyVotesCount = partyVO.getMlaCandidateEarnedVotes();
												
												Long crossVotesCount =  parliaentVotesCount-assemblyVotesCount;
												String assemblyPerc = calculatePercentage(validVotesCount,assemblyVotesCount);;
												String parliamentPerc = calculatePercentage(validVotesCount,parliaentVotesCount);;
												
												Double crossVotingPerc = Double.parseDouble(parliamentPerc)-Double.parseDouble(assemblyPerc);
												String perc = String.valueOf(crossVotingPerc);
												returnPartyVO.setCrossVotingPerc(String.valueOf(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc))));
												returnPartyVO.setCrossVotingCount(crossVotesCount);
												
												returnPartyVO.setMlaCandidateEarnedVotes(assemblyVotesCount);
												break;
											}
											
											i=i+1;
										}
										
										if(ourPartyPositionNo == 0){// rank 1 - first position after sorting
											ElectionInformationVO rankOneVO = assemblyVO.getList().get(ourPartyPositionNo);
											ElectionInformationVO rankTwoVO = null;
											if(assemblyVO.getList() != null && assemblyVO.getList().size()>1){
												rankTwoVO = assemblyVO.getList().get(ourPartyPositionNo+1);
											}
											if(rankTwoVO != null && rankOneVO != null){
												Long marginVotes = rankOneVO.getMlaCandidateEarnedVotes() - rankTwoVO.getMlaCandidateEarnedVotes();
												rankOneVO.setMarginVotes(marginVotes);
												String rank1Perc= calculatePercentage(assemblyVO.getMlaCandidateEarnedVotes(),rankOneVO.getMlaCandidateEarnedVotes());
												String rank2Perc= calculatePercentage(assemblyVO.getMlaCandidateEarnedVotes(),rankTwoVO.getMlaCandidateEarnedVotes());
												
												Double marginPerc = Double.parseDouble(rank1Perc) - Double.parseDouble(rank2Perc);
												rankOneVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
												
												if(locationTypeId.longValue() ==2L || locationTypeId.longValue() ==3L || locationTypeId.longValue() ==4L || 
														 locationTypeId.longValue() ==10L )
													returnPartyVO.setRank(1L);
												
												returnPartyVO.setPartyId(partyId);
												returnPartyVO.setPartyName(rankOneVO.getPartyName());
												returnPartyVO.setMarginVotes(marginVotes);
												returnPartyVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
											}
										}else if(ourPartyPositionNo >0){
											// Not first position after sorting
											ElectionInformationVO rankOneVO = assemblyVO.getList().get(0);
											ElectionInformationVO nextRankVO = null;
											if(assemblyVO.getList() != null && assemblyVO.getList().size()>1){
												nextRankVO = assemblyVO.getList().get(ourPartyPositionNo);
											}
											if(nextRankVO != null && rankOneVO != null){
												Long marginVotes = nextRankVO.getMlaCandidateEarnedVotes() - rankOneVO.getMlaCandidateEarnedVotes();
												rankOneVO.setMarginVotes(marginVotes);
												String rank1Perc= calculatePercentage(assemblyVO.getMlaCandidateEarnedVotes(),rankOneVO.getMlaCandidateEarnedVotes());
												String rank2Perc= calculatePercentage(assemblyVO.getMlaCandidateEarnedVotes(),nextRankVO.getMlaCandidateEarnedVotes());
												
												Double marginPerc = Double.parseDouble(rank1Perc) - Double.parseDouble(rank2Perc);
												nextRankVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
												
												returnPartyVO.setPartyId(partyId);
												returnPartyVO.setPartyName(nextRankVO.getPartyName());
												returnPartyVO.setMarginVotes(marginVotes);
												returnPartyVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
												
												if(locationTypeId.longValue() ==2L || locationTypeId.longValue() ==3L || locationTypeId.longValue() ==4L || 
														 locationTypeId.longValue() ==10L )
													returnPartyVO.setRank(Long.valueOf(String.valueOf(ourPartyPositionNo+1)));
											}
										}
										if(ourPartyPositionNo>=0)
											assemblyVO.getSubList1().add(returnPartyVO);
									}
								}
							}
						}
						
						if((locationTypeId.longValue() == 10L || commonMethodsUtilService.isMapValid(partyAssemblyWiseParliamentEarnedVotesMap) || (
								locationTypeId.longValue() != 5L && locationTypeId.longValue() != 6L && locationTypeId.longValue() != 7L))){
							if(parliamentVO != null && commonMethodsUtilService.isListOrSetValid(parliamentVO.getSubList2())){
								if(parliamentVO != null && commonMethodsUtilService.isListOrSetValid(parliamentVO.getSubList2())){
									Collections.sort(parliamentVO.getSubList2(), new Comparator<ElectionInformationVO>() {
										public int compare(ElectionInformationVO o1,ElectionInformationVO o2) {
											
											Long o2Count = o2.getMpCandidateEarnedVotes() != null?o2.getMpCandidateEarnedVotes():0L;
											Long o1Count = o1.getMpCandidateEarnedVotes() != null?o1.getMpCandidateEarnedVotes():0L;
											return o2Count.compareTo(o1Count);
										}
									});
									
									for (Long partyId : partyIdsList) {
										ElectionInformationVO returnPartyVO = new ElectionInformationVO();
										
										int ourPartyPositionNo=-1;
										for (int i = 0; i < parliamentVO.getSubList2().size();) {
											ElectionInformationVO partyVO =  parliamentVO.getSubList2().get(i);
											Long validVotesCount = parliamentVO.getMpCandidateEarnedVotes();
											Long assemblyVotesCount = parliamentVO.getMlaCandidateEarnedVotes();
											Long parliaentVotesCount = partyVO.getMpCandidateEarnedVotes();
											returnPartyVO.setMlaCandidateEarnedVotes(assemblyVotesCount);
											if(partyVO.getPartyId().longValue() == partyId.longValue()){
												ourPartyPositionNo = i;
												
												//Long crossVotesCount = assemblyVotesCount - parliaentVotesCount ;
												Long crossVotesCount = parliaentVotesCount - assemblyVotesCount  ;
												String assemblyPerc = calculatePercentage(validVotesCount,assemblyVotesCount);;
												String parliamentPerc = calculatePercentage(validVotesCount,parliaentVotesCount);;
												
												//Double crossVotingPerc = Double.parseDouble(assemblyPerc) - Double.parseDouble(parliamentPerc);
												Double crossVotingPerc = Double.parseDouble(parliamentPerc) -  Double.parseDouble(assemblyPerc);
												String perc = String.valueOf(crossVotingPerc);
												returnPartyVO.setCrossVotingPerc(String.valueOf(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(Float.valueOf(perc))));
												returnPartyVO.setCrossVotingCount(crossVotesCount);

												returnPartyVO.setMpCandidateEarnedVotes(parliaentVotesCount);
												break;
											}
											i=i+1;
										}
										
										if(ourPartyPositionNo == 0){// rank 1 - first position after sorting
											ElectionInformationVO rankOneVO = parliamentVO.getSubList2().get(ourPartyPositionNo);
											ElectionInformationVO rankTwoVO = null;
											if(parliamentVO.getSubList2() != null && parliamentVO.getSubList2().size()>1){
												rankTwoVO = parliamentVO.getSubList2().get(ourPartyPositionNo+1);
											}
											if(rankTwoVO != null && rankOneVO != null){
												Long marginVotes = rankOneVO.getMpCandidateEarnedVotes() - rankTwoVO.getMpCandidateEarnedVotes();
												rankOneVO.setMarginVotes(marginVotes);
												String rank1Perc= calculatePercentage(parliamentVO.getMpCandidateEarnedVotes(),rankOneVO.getMpCandidateEarnedVotes());
												String rank2Perc= calculatePercentage(parliamentVO.getMpCandidateEarnedVotes(),rankTwoVO.getMpCandidateEarnedVotes());
												
												Double marginPerc = Double.parseDouble(rank1Perc) - Double.parseDouble(rank2Perc);
												rankOneVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
												
												if(locationTypeId.longValue() ==2L || locationTypeId.longValue() ==3L || locationTypeId.longValue() ==4L || 
														 locationTypeId.longValue() ==10L )
													returnPartyVO.setRank(1L);
												
												returnPartyVO.setPartyId(partyId);
												returnPartyVO.setPartyName(rankOneVO.getPartyName());
												returnPartyVO.setMarginVotes(marginVotes);
												returnPartyVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
											}
										}else if(ourPartyPositionNo >0){
											// Not first position after sorting
											ElectionInformationVO rankOneVO = parliamentVO.getSubList2().get(0);
											ElectionInformationVO nextRankVO = null;
											if(parliamentVO.getSubList2() != null &&parliamentVO.getSubList2().size()>1){
												nextRankVO = parliamentVO.getSubList2().get(ourPartyPositionNo);
											}
											if(nextRankVO != null && rankOneVO != null){
												Long marginVotes = nextRankVO.getMpCandidateEarnedVotes()-rankOneVO.getMpCandidateEarnedVotes();
												rankOneVO.setMarginVotes(marginVotes);
												String rank1Perc= calculatePercentage(parliamentVO.getMpCandidateEarnedVotes(),rankOneVO.getMpCandidateEarnedVotes());
												String rank2Perc= calculatePercentage(parliamentVO.getMpCandidateEarnedVotes(),nextRankVO.getMpCandidateEarnedVotes());
												
												Double marginPerc = Double.parseDouble(rank1Perc) - Double.parseDouble(rank2Perc);
												nextRankVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
												
												returnPartyVO.setPartyId(partyId);
												returnPartyVO.setPartyName(nextRankVO.getPartyName());
												returnPartyVO.setMarginVotes(marginVotes);
												returnPartyVO.setPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(marginPerc));
												
												if(locationTypeId.longValue() ==2L || locationTypeId.longValue() ==3L || locationTypeId.longValue() ==4L || 
														 locationTypeId.longValue() ==10L )
													returnPartyVO.setRank(Long.valueOf(String.valueOf(ourPartyPositionNo+1)));
											}
										}
										if(ourPartyPositionNo>=0)
											parliamentVO.getSubList1().add(returnPartyVO);
									}
								}
							}
						}
				}
			}
			
			
			if(commonMethodsUtilService.isMapValid(parliamentMap)){
				finalList.addAll(parliamentMap.values());
				if(commonMethodsUtilService.isListOrSetValid(finalList)){
					for (ElectionInformationVO vo : finalList) {
						if(commonMethodsUtilService.isListOrSetValid(vo.getSubList2()))
							vo.getSubList2().clear();
						if(commonMethodsUtilService.isListOrSetValid(vo.getList())){
							for (ElectionInformationVO assemblyVO : vo.getList()) {
								if(commonMethodsUtilService.isListOrSetValid(assemblyVO.getList()))
									assemblyVO.getList().clear();
							}
						}
					}
				}
			}
			
		}catch(Exception e){
				e.printStackTrace();
				Log.error("Exception raised at getLocationBasedCrossVotingReult service"+e);
		}
	
			return finalList;
	}

public  Map<Long,Map<Long,ElectionInformationVO>> getSegregateAliancePartiesMap(List<String> subTypes,List<Long> electionYear,List<Long> electionScopeIds){
	
	Map<Long,Map<Long,ElectionInformationVO>> electionIdAndgroptIdVomap = new HashMap<Long,Map<Long,ElectionInformationVO>>();
	try{
		//0-election_id 1-election_type 2-election_year 3-group_id 4-group_name 5-party_id 6-partyIdAndName
				List<Object[]> segregateObjects=electionAllianceDAO.getSegregateAlianceParties(subTypes, electionYear, electionScopeIds);
				if( segregateObjects !=null && segregateObjects.size() >0 ){
					for(Object[] param :segregateObjects){
						Map<Long,ElectionInformationVO> groupIdVOsMap=electionIdAndgroptIdVomap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
						if(!commonMethodsUtilService.isMapValid(groupIdVOsMap))
							groupIdVOsMap = new HashMap<Long,ElectionInformationVO>() ;
						
						ElectionInformationVO vo=groupIdVOsMap.get(commonMethodsUtilService.getLongValueForObject(param[3]));
						if(vo==null){
								vo = new ElectionInformationVO();
							vo.setElectionId(commonMethodsUtilService.getLongValueForObject(param[0]));
							vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[1]));
							vo.setElectionYear(commonMethodsUtilService.getStringValueForObject(param[2]));
							vo.setGroupId(commonMethodsUtilService.getLongValueForObject(param[3]));
							vo.setPartyName(commonMethodsUtilService.getStringValueForObject(param[4]));
						}
						vo.getIdsList().add(commonMethodsUtilService.getLongValueForObject(param[5]));
						vo.getPartyNamesList().add(commonMethodsUtilService.getStringValueForObject(param[6]));
						Collections.sort(vo.getIdsList());
						
						groupIdVOsMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), vo);
						electionIdAndgroptIdVomap.put(commonMethodsUtilService.getLongValueForObject(param[0]), groupIdVOsMap);
					}
				}
	}catch(Exception e){
		Log.error("Exception raised at getSegregateAliancePartiesDetails service"+e);

	}
	return electionIdAndgroptIdVomap;
}
@SuppressWarnings("unused")
public List<Object[]> segregateAlianceParties(List<Object[]> inputObjList,List<String> subTypes,List<Long> electionYear,List<Long> electionScopeIds,Map<Long, Map<Long, ElectionInformationVO>> alliancedPartiesWithGroupId) {
	List<Object[]> finalObjList = new ArrayList<Object[]>();
	List<Object[]> aliancedPartiesObj = new ArrayList<Object[]>();
	List<Object[]> notAliancedPartiesObj = new ArrayList<Object[]>();;
	//Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupId = getSegregateAliancePartiesMap(subTypes,electionYear,electionScopeIds);
	try{
	List<Object[]> withOutSegragateObjList = inputObjList.subList(0, inputObjList.size()-1);
	if(withOutSegragateObjList != null && withOutSegragateObjList.size()>0){
		for(Object[] param : withOutSegragateObjList){
			Long electionId = commonMethodsUtilService.getLongValueForObject(param[6]);
			Long partyId = commonMethodsUtilService.getLongValueForObject(param[0]);
			String partyName = commonMethodsUtilService.getStringValueForObject(param[1]);
			List<String> groupIdAndName = findMatchedPartyId(alliancedPartiesWithGroupId,electionId,partyId);
	        if(groupIdAndName == null){
	        	notAliancedPartiesObj.add(param);
	        }else{
	        	addAliancePartyToList(aliancedPartiesObj, param,groupIdAndName,electionId);
	         }
			}
		 }
	finalObjList.addAll(aliancedPartiesObj);
	finalObjList.addAll(notAliancedPartiesObj);
	}catch(Exception e){
		Log.error("Exception raised at segregateAlianceParties service",e);

	}
	return finalObjList;
}


public List<String> findMatchedPartyId(Map<Long,Map<Long,ElectionInformationVO>> alliancedPartiesWithGroupId,Long electionId,Long partyId){
	List<String> groupIdAndName = null;
	   if(alliancedPartiesWithGroupId != null && alliancedPartiesWithGroupId.size() >0){
	    for (Entry<Long,Map<Long, ElectionInformationVO>> electionEntry : alliancedPartiesWithGroupId.entrySet()) {
	    	Long innerElectionId = electionEntry.getKey();
	    	if(innerElectionId.equals(electionId)){
	    		Map<Long, ElectionInformationVO> groupIdMap = electionEntry.getValue();
	    		 if(groupIdMap != null && groupIdMap.size() >0){
	    		    for (Entry<Long, ElectionInformationVO>  groupEntry : groupIdMap.entrySet()) {
	    		    	//Long innerGroupId = groupEntry.getKey();
	    		    	ElectionInformationVO electionInformationVO = groupEntry.getValue();
	    		    	List<Long> partyIdsList = electionInformationVO.getIdsList();
	    		    	if(partyIdsList.contains(partyId)){
	    		    		groupIdAndName = new ArrayList<String>();
	    		    		//String prefixedGroupId ="999999"+innerGroupId.toString();
	    		    		String prefixedGroupId =partyIdsList.get(0).toString();
	    		    		groupIdAndName.add(prefixedGroupId);
	    		    		groupIdAndName.add(electionInformationVO.getPartyName());
	    		    		List<String> partNamesList=electionInformationVO.getPartyNamesList();
	    		    		String commaSeparatedNames="";
	    		    		for(String partyName:partNamesList){
	    		    			commaSeparatedNames+=partyName+", ";
	    		    		}
	    		    		groupIdAndName.add(commaSeparatedNames.substring(0, commaSeparatedNames.length()-2));
	    		    		groupIdAndName.add(partyIdsList.toString());
	    		    		return groupIdAndName;
	    		    	}
	    		     }
	    		   }
	    	}
	    }
	   }
	return groupIdAndName;
}

public List<Object[]> addAliancePartyToList(List<Object[]> aliancedPartiesObj,Object[] param,List<String> groupIdAndName,Long electionId){
	
	  if(aliancedPartiesObj != null && aliancedPartiesObj.size() == 0){
		  Object [] newObj = new Object[param.length];
			newObj[0] =(Object)groupIdAndName.get(0);
			newObj[1] = (Object)groupIdAndName.get(1);//group name
			newObj[2]=param[2];
			newObj[3]=param[3];
			newObj[4]=param[4];
			newObj[5]=param[5];
			newObj[6] = (Object)electionId;
			aliancedPartiesObj.add(newObj);
			 return aliancedPartiesObj;
	  }
	  boolean addNewObjstatus = false;
	  if(aliancedPartiesObj != null && aliancedPartiesObj.size() > 0){
		  for(Object[] aliancedPartiesParam:aliancedPartiesObj){
			  String aliancedGroupId = commonMethodsUtilService.getStringValueForObject(aliancedPartiesParam[0]);
			  if(aliancedGroupId.equals(groupIdAndName.get(0).trim())){
				  aliancedPartiesParam[2] = commonMethodsUtilService.getLongValueForObject(aliancedPartiesParam[2])+commonMethodsUtilService.getLongValueForObject(param[2]);
				  addNewObjstatus = true;
				  return aliancedPartiesObj;
			  }
		  }
	  }
	  if(addNewObjstatus == false){
		  Object [] newObj = new Object[param.length];
			newObj[0] =(Object)groupIdAndName.get(0);
			newObj[1] =(Object)groupIdAndName.get(1);//group name
			newObj[2]=param[2];
			newObj[3]=param[3];
			newObj[4]=param[4];
			newObj[5]=param[5];
			newObj[6] = (Object)electionId;
			aliancedPartiesObj.add(newObj);
		    return aliancedPartiesObj;
	  }
	return null;
}
public PartyBoothPerformanceVO getBoothWiseElectionResultsForAssamblyAndParlaiment(PartyBoothPerformanceVO assemblyBoothResultVO,PartyBoothPerformanceVO parliamentBoothResultVO) {
	PartyBoothPerformanceVO finalVo = new PartyBoothPerformanceVO();
	try{
		Map<Long,BoothResultVO> assemblyMap= new HashMap<Long,BoothResultVO>();
		if(assemblyBoothResultVO != null){
			List<BoothResultVO> boothResultVOs = assemblyBoothResultVO.getBoothResults();
			if(boothResultVOs != null && boothResultVOs.size()>0){
				for(BoothResultVO resultVo: boothResultVOs){
					List<BoothResultVO> boothResults = resultVo.getBoothResultVOList();
					if(boothResults != null && boothResults.size()>0){
						for(BoothResultVO param : boothResults){
							if(param.getBoothId() != null){
								assemblyMap.put(param.getBoothId(), resultVo);break;
							}
						}
					}
				}
			}
		}
		if(parliamentBoothResultVO != null ){
			List<BoothResultVO> boothResultVOs = parliamentBoothResultVO.getBoothResults();
			if(boothResultVOs != null && boothResultVOs.size()>0){
				for(BoothResultVO resultVo: boothResultVOs){
					List<BoothResultVO> partyResults = resultVo.getBoothResultVOList();
					if(partyResults != null && partyResults.size()>0){
						for(BoothResultVO partyVO : partyResults){
							if(partyVO.getBoothId() != null){
								BoothResultVO boothVO = assemblyMap.get(partyVO.getBoothId());
								if(boothVO != null && commonMethodsUtilService.isListOrSetValid(boothVO.getBoothResultVOList())){
									for (BoothResultVO assemblyPartyVO : boothVO.getBoothResultVOList()) {
										if(partyVO.getOppPartyId().longValue() == assemblyPartyVO.getOppPartyId().longValue() ){
											assemblyPartyVO.setParlaimentCount(partyVO.getVotesEarned());
											assemblyPartyVO.setParlaimentPerc(partyVO.getPercentage());
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		finalVo.getPartyBoothPerformanceVOList().addAll(assemblyBoothResultVO.getPartyBoothPerformanceVOList());
		finalVo.getBoothResults().addAll(assemblyMap.values());
		return finalVo;
	}catch(Exception e){
		Log.error("Exception raised at getBoothWiseElectionResultsForAssamblyAndParlaiment service"+e);
		return null;
	}
	
	
}
}
