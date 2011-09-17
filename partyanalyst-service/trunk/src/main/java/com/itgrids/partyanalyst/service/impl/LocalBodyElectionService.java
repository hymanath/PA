/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 21, 2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.hibernate.LocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.hibernate.PartyDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsInConstituencyVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.ILocalBodyElectionService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyElectionResultVOByRankComparator;
import com.itgrids.partyanalyst.utils.TehsilPartyInfoVOBySeatsWonComparator;

public class LocalBodyElectionService implements ILocalBodyElectionService {

	private final static Logger log = Logger.getLogger(LocalBodyElectionService.class);
	
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IStaticDataService staticDataService;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	public ILocalElectionBodyWardDAO localElectionBodyWardDAO;
	public IPartyDAO partyDAO;

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public ILocalElectionBodyWardDAO getLocalElectionBodyWardDAO() {
		return localElectionBodyWardDAO;
	}

	public void setLocalElectionBodyWardDAO(
			ILocalElectionBodyWardDAO localElectionBodyWardDAO) {
		this.localElectionBodyWardDAO = localElectionBodyWardDAO;
	}
	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IConstituencyElectionDAO getConstituencyElectionDAO() {
		return constituencyElectionDAO;
	}

	public void setConstituencyElectionDAO(
			IConstituencyElectionDAO constituencyElectionDAO) {
		this.constituencyElectionDAO = constituencyElectionDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ILocalBodyElectionService#getLocalBodyElectionResultsByLocalBodyTypeAndYear(java.lang.Long, java.lang.String)
	 * Method to get All Party Election Results for a Local Body Election In a Year.
	 */
	public LocalBodyElectionResultsVO getLocalBodyElectionResultsByLocalBodyTypeAndYear(
			Long localBodyId, Long stateId) {
	 if(log.isDebugEnabled())
		 log.debug(" Inside getLocalBodyElectionResultsByLocalBodyTypeAndYear Method ...");
	 LocalBodyElectionResultsVO localBodyElectionResultVO = null;
	 ResultStatus resultStatus = new ResultStatus();
	 Long electionId = 0L;
	 
	 try{
		 //initialize the outputVO
		 localBodyElectionResultVO = new LocalBodyElectionResultsVO();
		 
		 if(localBodyId != null){
			 
			 //Local Body Elections High Level Info
			 getHighLevelInformationOfLocalBodyElection(localBodyElectionResultVO,localBodyId,electionId);
			 
			 //High Level Local Election Body Results
			 getAllPartyElectionResulsInLocalBodyElections(localBodyElectionResultVO,localBodyId,localBodyElectionResultVO.getLocalBodyElectionYear());
			 
			 //Get All Wards Participated In Election
			 getAllWardDetailsParticipatedInLocalBodyElection(localBodyElectionResultVO,localBodyId,localBodyElectionResultVO.getLocalBodyElectionId());
			 
		 }
		 
		 
	 }catch(Exception ex){
		 ex.printStackTrace();
		 resultStatus.setExceptionEncountered(ex);
		 resultStatus.setExceptionClass(ex.getClass().toString());
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 
		 localBodyElectionResultVO.setResultStatus(resultStatus);
	 }
		
	 return localBodyElectionResultVO;
	}
	
	/*
	 * Method to set PArticipated wards list to VO
	 */
	@SuppressWarnings("unchecked")
	public void getAllWardDetailsParticipatedInLocalBodyElection(LocalBodyElectionResultsVO localBodyElectionResultVO,Long localBodyId,Long electionId) throws Exception{
		
		if(log.isDebugEnabled())
			log.debug(" Inside getAllWardDetailsParticipatedInLocalBodyElection Method ..");
		
		List<SelectOptionVO> wardsList = new ArrayList<SelectOptionVO>(); 
		
		if(localBodyId != null && electionId != null){
			
			List resultsList = constituencyElectionDAO.getAllWardsDetailsParticipatedInALocalBodyElection(localBodyId, electionId);
			if(resultsList != null && resultsList.size() > 0){
				for(int i=0;i<resultsList.size();i++){
					Object[] values = (Object[])resultsList.get(i);
					Long wardId = (Long)values[0];
					String wardName = (String)values[1];
					
					SelectOptionVO option = new SelectOptionVO(wardId,wardName);
					wardsList.add(option);
				}
				localBodyElectionResultVO.setWardsList(wardsList);
			}
		}
	}
	
	/*
	 * Method to get High Level Information for a Local Body Election
	 */
	public void getHighLevelInformationOfLocalBodyElection(LocalBodyElectionResultsVO localBodyElectionResultVO,Long localBodyId,Long electionId) throws Exception{
		if(log.isDebugEnabled())
			log.debug(" Inside getHighLevelInformationOfLocalBodyElection Method ..");
		
		List<SelectOptionVO> electionsList = new ArrayList<SelectOptionVO>();
		LocalElectionBody localElectionBody = localElectionBodyDAO.get(localBodyId);
		if(localElectionBody != null){
			
			localBodyElectionResultVO.setTotalWards(localElectionBody.getNoOfWards());
			ElectionType electionType = localElectionBody.getElectionType();
						
			//Local Body Tehsil Info
			Tehsil tehsil = localElectionBody.getTehsil();
			localBodyElectionResultVO.setTehsilId(tehsil.getTehsilId());
			localBodyElectionResultVO.setTehsil(tehsil.getTehsilName());
			
			//Local Body District Info
			District district = localElectionBody.getDistrict();
			localBodyElectionResultVO.setDistrictId(district.getDistrictId());
			localBodyElectionResultVO.setDistrict(district.getDistrictName());
			
			//Local Body State Info
			State state = district.getState();
			localBodyElectionResultVO.setStateId(state.getStateId());
			localBodyElectionResultVO.setState(state.getStateName());
			
			//Local Body Type Info	
			localBodyElectionResultVO.setLocalBodyId(localBodyId);
			localBodyElectionResultVO.setLocalBodyElectionTypeId(electionType.getElectionTypeId());
			localBodyElectionResultVO.setLocalBodyElectionType(electionType.getElectionType());
			localBodyElectionResultVO.setLocalBodyRegion(localElectionBody.getName());
			
			//Local Body Election Info
			if(electionId != null && !electionId.equals(0L)){
				Election election = electionDAO.get(electionId);
				localBodyElectionResultVO.setLocalBodyElectionId(electionId);
				localBodyElectionResultVO.setLocalBodyElectionYear(election.getElectionYear());
				localBodyElectionResultVO.setOtherElectionYears(electionsList);
			}else{
				electionsList = getLocalBodyElectionsList(localBodyId,state.getStateId());
				if(electionsList != null && electionsList.size() > 0){
					localBodyElectionResultVO.setLocalBodyElectionId(electionsList.get(0).getId());
					localBodyElectionResultVO.setLocalBodyElectionYear(electionsList.get(0).getName());
					localBodyElectionResultVO.setOtherElectionYears(electionsList);
				}
			}
				
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getLocalBodyElectionsList(Object localBody,Long stateId) throws Exception{
		List<SelectOptionVO> elections = new ArrayList<SelectOptionVO>();
		List electionsList = null;
		if(localBody != null && stateId != null){
			if(localBody instanceof Long)
				electionsList = constituencyElectionDAO.getLocalBodyElectionsInAState((Long)localBody, stateId);
			else
				electionsList = electionDAO.getLocalBodyElectionsInAState(localBody.toString(), stateId);
			
			if(electionsList != null && electionsList.size() > 0){
				
				for(int i=0;i<electionsList.size();i++){
					Object[] params = (Object[])electionsList.get(i);
					SelectOptionVO selectOption = new SelectOptionVO();
					selectOption.setId((Long)params[0]);
					selectOption.setName((String)params[1]);
					
					elections.add(selectOption);
				}
			}
		}
		return elections;
	}
	
	/*
	 * Method to get All Party Election results from DB and set to VO
	 */
	@SuppressWarnings("unchecked")
	public void getAllPartyElectionResulsInLocalBodyElections(LocalBodyElectionResultsVO localBodyElectionResultVO,Long localBodyId,String electionYear) 
	                                                                                                                                   throws Exception{
		if(log.isDebugEnabled())
		log.debug(" Inside getAllPartyElectionResulsInLocalBodyElections Method ..");
		List<TeshilPartyInfoVO> localBodyElecResList = new ArrayList<TeshilPartyInfoVO>();
		List<SelectOptionVO> participatedParties = new ArrayList<SelectOptionVO>();
		
		//Get LocalBodyElection Voters & Votes Info
		Double constiValidVotes = null;
		Double totalVotes = null;
		Double totalPolledVotes = null;
		List totalConstiValidVotes = constituencyElectionDAO.getConstituencyValidVotesForLocalBodyElection(localBodyId, electionYear);
		if(totalConstiValidVotes != null){
			Object[] params = (Object[])totalConstiValidVotes.get(0);
			constiValidVotes = (Double)params[0];
			totalPolledVotes = (Double)params[1];
			totalVotes = (Double)params[2];
			if(totalVotes != null)
			    localBodyElectionResultVO.setTotalVotes(totalVotes.longValue());
			else
				localBodyElectionResultVO.setTotalVotes(1L);
			if(totalPolledVotes != null)
			    localBodyElectionResultVO.setTotPolledVotes(totalPolledVotes.longValue());
			else
				localBodyElectionResultVO.setTotPolledVotes(0L);
			if(constiValidVotes != null)
				localBodyElectionResultVO.setTotalValidVotes(constiValidVotes.longValue());
		}
		
		//Map<PartyId,results> is a Hashmap that holds party results 
		Map<Long,TeshilPartyInfoVO> partyVotesInfo = getPartyVotesStatusDetailsAsMap(localBodyId,electionYear,constiValidVotes.longValue());
		Map<Long,TeshilPartyInfoVO> partyWonSeatsInfo = getPartySeatsStatusDetailsAsMap(localBodyId,electionYear,1L);
		Map<Long,TeshilPartyInfoVO> partySecPosSeatsInfo = getPartySeatsStatusDetailsAsMap(localBodyId,electionYear,2L);
		Map<Long,TeshilPartyInfoVO> partyThirdPosSeatsInfo = getPartySeatsStatusDetailsAsMap(localBodyId,electionYear,3L);
		Map<Long,TeshilPartyInfoVO> partyNthPosSeatsInfo = getPartySeatsStatusDetailsAsMap(localBodyId,electionYear,4L);
		
		if(partyVotesInfo != null && !partyVotesInfo.isEmpty()){
			
			Set<Long> partyIds = partyVotesInfo.keySet();
			Long validVotes = 0L;
			
			for(Long partyId:partyIds){
				TeshilPartyInfoVO partyResultVO = new TeshilPartyInfoVO();
				
				//Party Votes Info
				if(partyVotesInfo.containsKey(partyId)){
				TeshilPartyInfoVO votesObtained = partyVotesInfo.get(partyId);
				partyResultVO.setPartyId(partyId);
				partyResultVO.setPartyName(votesObtained.getPartyName());
				
				//for Participated Parties list
				SelectOptionVO selectOption = new SelectOptionVO(partyId,votesObtained.getPartyName());
				participatedParties.add(selectOption);				
				
				partyResultVO.setParticipatedSeats(votesObtained.getParticipatedSeats());
				partyResultVO.setVotesGained(votesObtained.getVotesGained());
				partyResultVO.setPartyParticipatedValidVotes(votesObtained.getPartyParticipatedValidVotes());
				partyResultVO.setPartiPartiVotesPercent(votesObtained.getPartiPartiVotesPercent());
				partyResultVO.setTotConstiVotesPercent(votesObtained.getTotConstiVotesPercent());
				
				validVotes+=votesObtained.getVotesGained();
				}
				
				//won seats info
				if(partyWonSeatsInfo.containsKey(partyId)){
				TeshilPartyInfoVO wonSeats = partyWonSeatsInfo.get(partyId);
				partyResultVO.setPartyWonSeats(wonSeats.getSeatsWonByParty());
				}else if(!partyWonSeatsInfo.containsKey(partyId))
    				partyResultVO.setPartyWonSeats(0L);	
				
				//2nd pos info
				if(partySecPosSeatsInfo.containsKey(partyId)){
				TeshilPartyInfoVO secndPosSeats = partySecPosSeatsInfo.get(partyId);
				partyResultVO.setPartySecndPos(secndPosSeats.getSeatsWonByParty());
				}else if(!partySecPosSeatsInfo.containsKey(partyId))
					partyResultVO.setPartySecndPos(0L);
				
				//3rd pos info
				if(partyThirdPosSeatsInfo.containsKey(partyId)){
				TeshilPartyInfoVO thirdPosSeats = partyThirdPosSeatsInfo.get(partyId);
				partyResultVO.setPartyThirdPos(thirdPosSeats.getSeatsWonByParty());	
				}else if(!partyThirdPosSeatsInfo.containsKey(partyId))
					partyResultVO.setPartyThirdPos(0L);	
				
				//Nth pos info
				if(partyNthPosSeatsInfo.containsKey(partyId)){
				TeshilPartyInfoVO nthPosSeats = partyNthPosSeatsInfo.get(partyId);
				partyResultVO.setPartyNthPos(nthPosSeats.getSeatsWonByParty());		
				}else if(!partyNthPosSeatsInfo.containsKey(partyId))
					partyResultVO.setPartyNthPos(0L);
				
				localBodyElecResList.add(partyResultVO);
			}
			Collections.sort(localBodyElecResList,new TehsilPartyInfoVOBySeatsWonComparator());
			localBodyElectionResultVO.setMuncipalityVO(localBodyElecResList);
			Collections.sort(participatedParties);
			localBodyElectionResultVO.setParticipatedParties(participatedParties);
		}
		
	}
	/*
	 * Seats Details Of Party in participated local election
	 */
	@SuppressWarnings("unchecked")
	public Map<Long,TeshilPartyInfoVO> getPartySeatsStatusDetailsAsMap(Long localBodyId,String electionYear,Long rank) throws Exception{
		
		Map<Long,TeshilPartyInfoVO> partySeatsInfo = null;
		
		if(localBodyId != null && electionYear != null && rank != null){
			partySeatsInfo = new HashMap<Long,TeshilPartyInfoVO>();
			List resultsList = null;
			
			//DAO method to get Results from DB
			if(rank > 3L)
				resultsList = nominationDAO.getResultsOfAllPartiesInLocalBodyELectionsBasedOnNthRank(localBodyId, electionYear, 3L);
			else
			    resultsList = nominationDAO.getResultsOfAllPartiesInLocalBodyELectionsBasedOnRank(localBodyId, electionYear, rank);
			
			if(resultsList != null){
				ListIterator li = resultsList.listIterator();
				while(li.hasNext()){
					Object[] values = (Object[])li.next();
					
					TeshilPartyInfoVO infoVO = new TeshilPartyInfoVO();
					infoVO.setPartyId((Long)values[0]);
					infoVO.setPartyName((String)values[1]);
					infoVO.setSeatsWonByParty((Long)values[2]);
					
					partySeatsInfo.put(infoVO.getPartyId(), infoVO);
				}
			}
		}
		
	 return partySeatsInfo;
	}
	
	/*
	 * Votes Details Of Party in participated local election
	 */
	@SuppressWarnings("unchecked")
	public Map<Long,TeshilPartyInfoVO> getPartyVotesStatusDetailsAsMap(Long localBodyId,String electionYear,Long constiValidVotes) throws Exception{
		
		Map<Long,TeshilPartyInfoVO> partyVotesInfo = null;
		
		if(localBodyId != null && electionYear != null){
			partyVotesInfo = new HashMap<Long,TeshilPartyInfoVO>();
			
			List resultsList = nominationDAO.getAllParticipatedPartyResultsInALocalBodyElection(localBodyId, electionYear);
			
			if(resultsList != null){
								
				ListIterator li = resultsList.listIterator();
				while(li.hasNext()){
					Object[] values = (Object[])li.next();
					
					TeshilPartyInfoVO infoVO = new TeshilPartyInfoVO();
					infoVO.setPartyId((Long)values[0]);
					infoVO.setPartyName((String)values[1]);
					infoVO.setParticipatedSeats((Long)values[2]);
					Double votesGained = (Double)values[3];
					infoVO.setVotesGained(votesGained.longValue());
					
					List partiPartiValidVotes = nominationDAO.getPartyParticipatedValidVotesForLocalBodyElection(localBodyId, infoVO.getPartyId(), electionYear);
					if(partiPartiValidVotes != null){
						Object params = (Object)partiPartiValidVotes.get(0);
						Double partiValidVotes = (Double)params;
						infoVO.setPartyParticipatedValidVotes(partiValidVotes.longValue());
						Double partyPartiPercent = calculatePercentage(votesGained,new Double(partiValidVotes));
						Double totConstiPercent = calculatePercentage(votesGained,new Double(constiValidVotes));
						infoVO.setPartiPartiVotesPercent(partyPartiPercent.toString());
						infoVO.setTotConstiVotesPercent(totConstiPercent.toString());
					}
					
					partyVotesInfo.put(infoVO.getPartyId(), infoVO);
				}
			}
			
		}
		
	 return partyVotesInfo;
	}
	
	public Double calculatePercentage(Double votesEarned,Double validVotes) throws Exception{
		
		return new BigDecimal(votesEarned/validVotes*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ILocalBodyElectionService#getLocalBodyElectionResultsForAPartyInAnElection(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsForAPartyInAnElection(
			Long localBodyId, Long stateId, Long electionId, Long partyId) {
		
		if(log.isDebugEnabled())
		  log.debug(" Inside getLocalBodyElectionResultsForAPartyInAnElection Method ..");	
		
		List<PartyElectionResultsInConstituencyVO> partyResultsList = null;
		
        try{
        	Party party = partyDAO.get(partyId);
        	List highLevelResults = null;
        	
        	List resultsList = nominationDAO.getWardWiseResultsForAPartyInALocalBodyElection(localBodyId, electionId, partyId);
        	        	
        	if(party.getShortName().equalsIgnoreCase(IConstants.IND))
        		highLevelResults = nominationDAO.getConstituencyLevelPartyParticipatedLocalBodyElectionVotesInfoForIND(localBodyId, partyId, electionId);
        	else
        		highLevelResults = nominationDAO.getConstituencyLevelPartyParticipatedLocalBodyElectionVotesInfo(localBodyId, partyId, electionId);
        	
        	if(resultsList != null && resultsList.size() > 0)
				partyResultsList = getLocalBodyELectionWardWiseResults(resultsList,highLevelResults);
        	
		}catch(Exception ex){
			ex.printStackTrace();
			log.error(" Exception Raised :" + ex);
			
		}
		
	 return partyResultsList;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ILocalBodyElectionService#getLocalBodyElectionResultsInAnElection(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsInAnElection(
			Long localBodyId, Long stateId, Long electionId) {
		
		if(log.isDebugEnabled())
			  log.debug(" Inside getLocalBodyElectionResultsInAnElection Method ..");	
		
		List<PartyElectionResultsInConstituencyVO> partyResultsList = null;
		try{
			List resultsList = nominationDAO.getWardWiseResultsOfAllPartiesInLocalElectionBodies(localBodyId, electionId);
			List highLevelResults = constituencyElectionDAO.getConstituencyVotesInfoForLocalBodyElection(localBodyId, electionId);
			
			if(resultsList != null && resultsList.size() > 0)
				partyResultsList = getLocalBodyELectionWardWiseResults(resultsList,highLevelResults);
		
						
		}catch(Exception ex){
			ex.printStackTrace();
			log.error(" Exception Raised :" + ex);
		}
		
	 return partyResultsList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyELectionWardWiseResults(List partyResultsList,List highLevelVotesInfo){
		
		if(log.isDebugEnabled())
			  log.debug(" Inside getLocalBodyELectionWardWiseResults Method ..");	
		
		List<PartyElectionResultsInConstituencyVO> partyElecResultsList = null;
		if(partyResultsList != null && partyResultsList.size() > 0){
			//Map<wardId,resultsList>
			Map<Long,List<PartyElectionResultsVO>> wardWisePartyResults = getWardLevelPartyResultsMap(partyResultsList);
			Map<Long,ConstituencyElectionResultVO> wardVotesInfoMap = getWardWiseVotesInfoMap(highLevelVotesInfo);
			
			if(wardVotesInfoMap != null && !wardVotesInfoMap.isEmpty()){
				
				partyElecResultsList = new ArrayList<PartyElectionResultsInConstituencyVO>();
				
				Set<Long> keys = wardVotesInfoMap.keySet();
				for(Long ward:keys){
					
				   if(wardVotesInfoMap.containsKey(ward) && wardWisePartyResults.containsKey(ward))
				   {
					ConstituencyElectionResultVO votesInfo = wardVotesInfoMap.get(ward);
					List<PartyElectionResultsVO> partyResults = wardWisePartyResults.get(ward);
					//collections sort
					Collections.sort(partyResults, new PartyElectionResultVOByRankComparator());
					PartyElectionResultsVO wonCandResult = partyResults.get(0);
					
					PartyElectionResultsInConstituencyVO partyElectionResultObject = new PartyElectionResultsInConstituencyVO();
					
					partyElectionResultObject.setConstituencyId(votesInfo.getConstituencyId());
					partyElectionResultObject.setConstituencyName(votesInfo.getConstituencyName());
					partyElectionResultObject.setTotalPolledVotes(votesInfo.getTotPolledVotes());
					partyElectionResultObject.setTotalVoters(votesInfo.getTotalVotes());
					partyElectionResultObject.setWonPartyId(wonCandResult.getPartyId());
					partyElectionResultObject.setWonPartyName(wonCandResult.getPartyName());
					partyElectionResultObject.setWonCandidate(wonCandResult.getCandidateName());
					partyElectionResultObject.setWonCandDesignation("Counsellor");
					
					partyElectionResultObject.setPartyElectionResultsVO(partyResults);
					
					partyElecResultsList.add(partyElectionResultObject);
				   }
				}
				
			}
		}
		
	 return partyElecResultsList;
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Map<Long,List<PartyElectionResultsVO>> getWardLevelPartyResultsMap(List partyResultsList){
		
		Map<Long,List<PartyElectionResultsVO>> partyResultsMap = null;
		if(partyResultsList != null && partyResultsList.size() > 0){
			partyResultsMap = new HashMap<Long,List<PartyElectionResultsVO>>();
			for(int i=0;i<partyResultsList.size();i++){
				Object[] values = (Object[])partyResultsList.get(i);
				
				Long wardId = (Long)values[4];
				List<PartyElectionResultsVO> partyResultsInMap = null;
				
				if(partyResultsMap.isEmpty() || !partyResultsMap.containsKey(wardId)){
					partyResultsInMap = new ArrayList<PartyElectionResultsVO>();
				}else if(partyResultsMap.containsKey(wardId)){
					partyResultsInMap = partyResultsMap.get(wardId);
				}
				
				PartyElectionResultsVO result = new PartyElectionResultsVO();
				result.setPartyId((Long)values[0]);
				result.setPartyName((String)values[1]);
				result.setCandidateId((Long)values[2]);
				result.setCandidateName((String)values[3]);
				result.setConstituencyId((Long)values[4]);
				result.setConstituencyName((String)values[5]);
				Double votesGained = (Double)values[6];
				if(votesGained != null)
				result.setVotesEarned(votesGained.longValue());
				else
				result.setVotesEarned(0L);
				result.setVotesPercentage((String)values[7]);
				result.setRank((Long)values[8]);
				
				partyResultsInMap.add(result);	
				
				partyResultsMap.put(wardId, partyResultsInMap);
			}
		}
		
	 return partyResultsMap;
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Map<Long,ConstituencyElectionResultVO> getWardWiseVotesInfoMap(List votesInfo){
	
		Map<Long,ConstituencyElectionResultVO> votesInfoMap = null;
		List<Long> constituencyIds = new ArrayList<Long>(0);
		if(votesInfo != null && votesInfo.size() > 0){
			votesInfoMap = new HashMap<Long,ConstituencyElectionResultVO>();
			
			for(int i=0;i<votesInfo.size();i++){
				Object[] values = (Object[])votesInfo.get(i);
				
				Long wardId = (Long)values[0];
				 				
				if(votesInfoMap.isEmpty() || !votesInfoMap.containsKey(wardId)){
				 ConstituencyElectionResultVO votesInfoObject = new ConstituencyElectionResultVO();
				 votesInfoObject.setConstituencyId(wardId);
				 votesInfoObject.setConstituencyName((String)values[1]);
				 Double totalVotes = (Double)values[4];
				 if(totalVotes != null)
				 votesInfoObject.setTotalVotes(totalVotes.longValue());
				 Double totalPolledVotes = (Double)values[3];
				 if(totalPolledVotes != null)
				 votesInfoObject.setTotPolledVotes(totalPolledVotes.longValue());
				 Double validVotes = (Double)values[2];
				 if(validVotes != null)
				 votesInfoObject.setValidVotes(validVotes.longValue());
				 constituencyIds.add(wardId);
				 votesInfoMap.put(wardId, votesInfoObject);
				}
			}
			
			List wardsInfo= localElectionBodyWardDAO.getLocalBodyElectionInfo(constituencyIds);
			for(int i=0;i<wardsInfo.size();i++){
				Object[] values = (Object[])wardsInfo.get(i);
				if(votesInfoMap.get(values[0])!=null){
					if(values[1]!= null && values[1].toString()!= null){
						String name = votesInfoMap.get(values[0]).getConstituencyName();
						name = values[1].toString()+" ("+name+")";
						votesInfoMap.get(values[0]).setConstituencyName(name);						
					}
					if(values[2]!= null && values[2].toString()!= null){
						 votesInfoMap.get(values[0]).setCircleName(values[2].toString());												
					}
					if(values[3]!= null && values[3].toString()!= null){
						 votesInfoMap.get(values[0]).setCircleZone(values[3].toString());												
					}
					
				}
			}
			
		}
	 return votesInfoMap;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.ILocalBodyElectionService#getLocalBodyElectionResultsForAWardInAnElection(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<PartyElectionResultsInConstituencyVO> getLocalBodyElectionResultsForAWardInAnElection(
			Long localBodyId, Long stateId, Long electionId, Long wardId) {
		if(log.isDebugEnabled())
			  log.debug(" Inside getLocalBodyElectionResultsForAWardInAnElection Method ..");	
			
			List<PartyElectionResultsInConstituencyVO> partyResultsList = null;
			
	        try{
	        	List resultsList = nominationDAO.getAllPartyResultsInAWardInALocalBodyElection(localBodyId, electionId, wardId);
	        	List highLevelResults = constituencyElectionDAO.getConstituencyVotesInfoForLocalBodyElection(localBodyId, electionId,wardId);
	        	
	        	if(resultsList != null && resultsList.size() > 0)
					partyResultsList = getLocalBodyELectionWardWiseResults(resultsList,highLevelResults);
	        	
			}catch(Exception ex){
				ex.printStackTrace();
				log.error(" Exception Raised :" + ex);
			}
			
			return partyResultsList;
	}
	
	public TeshilPartyInfoVO getMuncipalOrCorporationElectionsResultsForAnAssembly(Long electionId, Long constituencyId){
		TeshilPartyInfoVO teshilPartyInfoVO = new TeshilPartyInfoVO();
		Election election = electionDAO.get(electionId);
		StringBuilder lebIds = new StringBuilder();
		List result = null;
		try {
			List lebs = assemblyLocalElectionBodyDAO.findByConstituencyId(constituencyId);
			
			for(Object[] values:(List<Object[]>)lebs){
				if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(values[2].toString()))
					lebIds.append(",").append(values[4]);
			}
			
			if(lebIds.length() > 0){
				result = nominationDAO.getLocalBodiesElecConstituenciesDetailsForAnElection(electionId, lebIds.toString().substring(1));
				List<TeshilPartyInfoVO> allMuncipalities = staticDataService.getLocalElectionPartyDetails(result, election.getElectionYear(), 
						election.getElectionScope().getElectionType().getElectionType());
				teshilPartyInfoVO.setMuncipalityVO(allMuncipalities);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teshilPartyInfoVO;
	}
	
	public ConstituencyVO findConstituencywiseGreaterElectionResults(Long electionId, Long constituencyId,Long partyId,Long wardId){
		List wardsInfo = assemblyLocalElectionBodyWardDAO.findByConstituencyIdAndYear(constituencyId, IConstants.GREATER_ELECTION_TYPE);
		ConstituencyVO constituencyVO = new ConstituencyVO();
		List<MandalVO> localElecBodies = new ArrayList<MandalVO>();
		MandalVO mandalVO = null;
		List<SelectOptionVO> listOfWards = new ArrayList<SelectOptionVO>(0);
		List<SelectOptionVO> listOfParties = new ArrayList<SelectOptionVO>(0);
		Map<Long, List<Object[]>> constituencyIdResultsMap = new LinkedHashMap<Long, List<Object[]>>();
		StringBuilder wardIds = new StringBuilder(); 
		Map<Long,String> parties = new HashMap<Long,String>();
		for(Object[] values:(List<Object[]>)wardsInfo){
			wardIds.append(",").append(Long.parseLong(values[0].toString()));
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(Long.parseLong(values[0].toString()));
			selectOptionVO.setName(values[1].toString());
			listOfWards.add(selectOptionVO);
			Collections.sort(listOfWards);
		}
		constituencyVO.setListOfWards(listOfWards);
		
		
		if(wardIds.toString().trim().length() == 0){
			constituencyVO.setIsDataExists(false);
			return constituencyVO;
		}
		
		
		List partyIds = nominationDAO.getALLPartiesByElectionId(electionId, wardIds.substring(1));
		for(Object[] values:(List<Object[]>)partyIds){		
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId(Long.parseLong(values[0].toString()));
			selectOptionVO.setName(values[1].toString());
			listOfParties.add(selectOptionVO);
		}
		constituencyVO.setListOfParties(listOfParties);
		List allElectionsInfo = null;
		if(wardId==0l){
			 allElectionsInfo = nominationDAO.findAllElectionResultsForConstituencies(electionId, wardIds.substring(1),partyId);	
		}else{
			 allElectionsInfo = nominationDAO.findAllElectionResultsForConstituencies(electionId, wardId.toString(),0l);
		}
		
		getAllElectionsInfo(constituencyIdResultsMap, allElectionsInfo);
		for(Map.Entry<Long, List<Object[]>> entry:constituencyIdResultsMap.entrySet()){
			mandalVO = new MandalVO();
			mandalVO.setId(entry.getKey());
			mandalVO.setName((entry.getValue().get(0)[4]).toString());
			mandalVO.setElectionType((entry.getValue().get(0)[2]).toString());
			mandalVO.setElectionYear((entry.getValue().get(0)[1]).toString());
			mandalVO.setElectionTypeId((Long)entry.getValue().get(0)[15]);
			mandalVO.setStateId((Long)entry.getValue().get(0)[14]);
			mandalVO.setWardwiseResultsForParty(getSubDivisionswiseResults(entry.getValue()));
			localElecBodies.add(mandalVO);			
		}
		
		constituencyVO.setLocalElectionsInfo(localElecBodies);
		return constituencyVO;
	}
	
	private List<PartyElectionResultsVO> getSubDivisionswiseResults(
			List<Object[]> results) {
		List<PartyElectionResultsVO> wardswiseResults = new ArrayList<PartyElectionResultsVO>();
		PartyElectionResultsVO wardAndPartyInfo = null;
		try{
		for(Object[] wardResult:results){
			wardAndPartyInfo = new PartyElectionResultsVO();
			wardAndPartyInfo.setElectionId((Long)wardResult[0]);
			wardAndPartyInfo.setElectionYear(wardResult[1].toString());
			wardAndPartyInfo.setElectionType(wardResult[2].toString());
			wardAndPartyInfo.setLebId((Long)wardResult[3]);
			wardAndPartyInfo.setLebName(wardResult[4].toString());
			wardAndPartyInfo.setConstituencyId((Long)wardResult[5]);
			wardAndPartyInfo.setConstituencyName(wardResult[6].toString());
			wardAndPartyInfo.setPartyId((Long)wardResult[7]);
			wardAndPartyInfo.setPartyName(wardResult[8].toString());
			wardAndPartyInfo.setVotesEarned(((Double)wardResult[9]).longValue());
			wardAndPartyInfo.setVotesPercentage(wardResult[10].toString());
			wardAndPartyInfo.setRank((Long)wardResult[11]);
			wardAndPartyInfo.setCandidateName(wardResult[12].toString());
			wardAndPartyInfo.setTotalVotes(((Double)wardResult[13]).longValue());
			wardswiseResults.add(wardAndPartyInfo);
		}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return wardswiseResults;
	}

	private void getAllElectionsInfo(
			Map<Long, List<Object[]>> constituencyIdResultsMap,
			List allElectionsInfo) {
		Long lebId;
		List<Object[]> results = null;
		for(Object[] values:(List<Object[]>)allElectionsInfo){
			lebId = (Long)values[3];
			results = constituencyIdResultsMap.get(lebId);
			if(results == null)
				results = new ArrayList<Object[]>();
			results.add(values);
			constituencyIdResultsMap.put(lebId, results);
		}
	}
	
	/**
	 * This method is used to get all local election bodies that are present in a constituency.
	 * @author Ravi Kiran.Y
	 * @version 1.0,20-10-10
	 * @param constituencyId
	 * @param electionType
	 * @return
	 */
	public NavigationVO getLatestGHMCElectionIdAndLatestElectionYear(String electionType){
		NavigationVO navigationVO = new NavigationVO();
		List<SelectOptionVO> selList = new ArrayList<SelectOptionVO>(0);
		ResultStatus resultStatus = new ResultStatus();
		try{
			List result = electionDAO.findLatestElectionYearForGHMC(electionType);			
			for(int i = 0;i<result.size();i++)
			{
				Object[] obj = (Object[])result.get(i);
				SelectOptionVO selectOptionVO = new SelectOptionVO();				
				selectOptionVO.setId(Long.parseLong(obj[0].toString()));
				selectOptionVO.setName(obj[1].toString());	
				selList.add(selectOptionVO);
			}
			navigationVO.setMessageTypes(selList);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
			navigationVO.setResultStatus(resultStatus);
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			navigationVO.setResultStatus(resultStatus);	
		}
		return navigationVO;
	}
	
	
	/**
	 * This method is used to get all local election bodies that are present in a constituency.
	 * @author Ravi Kiran.Y
	 * @version 1.0,20-10-10
	 * @param constituencyId
	 * @param electionType
	 * @return
	 */
	public NavigationVO getLocalBodyElectionIdsForAConstituency(Long constituencyId,String electionType){
		NavigationVO navigationVO = new NavigationVO();
		List<SelectOptionVO> selList = new ArrayList<SelectOptionVO>(0);
		ResultStatus resultStatus = new ResultStatus();
		try{
			List result = assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByConstituencyId(constituencyId,IConstants.GREATER_ELECTION_TYPE);			
			for(int i = 0;i<result.size();i++)
			{
				Object[] obj = (Object[])result.get(i);
				SelectOptionVO selectOptionVO = new SelectOptionVO();				
				selectOptionVO.setId(Long.parseLong(obj[0].toString()));
				selectOptionVO.setName(obj[1].toString());	
				selList.add(selectOptionVO);
			}
			navigationVO.setMessageTypes(selList);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			resultStatus.setResultPartial(false);
			navigationVO.setResultStatus(resultStatus);
		}catch(Exception e){
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			resultStatus.setResultPartial(true);
			navigationVO.setResultStatus(resultStatus);	
		}
		return navigationVO;
	}

}