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
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.LocalBodyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.service.ILocalBodyElectionService;

public class LocalBodyElectionService implements ILocalBodyElectionService {

	private final static Logger log = Logger.getLogger(LocalBodyElectionService.class);
	
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IConstituencyElectionDAO constituencyElectionDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	
	
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
	 
	 try{
		 //initialize the outputVO
		 localBodyElectionResultVO = new LocalBodyElectionResultsVO();
		 
		 if(localBodyId != null){
			 
			 //Local Body Elections High Level Info
			 getHighLevelInformationOfLocalBodyElection(localBodyElectionResultVO,localBodyId);
			 
			 //High Level Local Election Body Results
			 getAllPartyElectionResulsInLocalBodyElections(localBodyElectionResultVO,localBodyId,localBodyElectionResultVO.getLocalBodyElectionYear());
			 
			 //Low Level ward wise results in Local Body Election
			 
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
	 * Method to get High Level Information for a Local Body Election
	 */
	public void getHighLevelInformationOfLocalBodyElection(LocalBodyElectionResultsVO localBodyElectionResultVO,Long localBodyId) throws Exception{
		if(log.isDebugEnabled())
			log.debug(" Inside getHighLevelInformationOfLocalBodyElection Method ..");
		
		List<SelectOptionVO> electionsList = null;
		LocalElectionBody localElectionBody = localElectionBodyDAO.get(localBodyId);
		if(localElectionBody != null){
			
			localElectionBody.setNoOfWards(localElectionBody.getNoOfWards());
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
			electionsList = getLocalBodyElectionsList(localBodyId,state.getStateId());
			if(electionsList != null && electionsList.size() > 0){
				localBodyElectionResultVO.setLocalBodyElectionId(electionsList.get(0).getId());
				localBodyElectionResultVO.setLocalBodyElectionYear(electionsList.get(0).getName());
				localBodyElectionResultVO.setOtherElectionYears(electionsList);
			}
				
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getLocalBodyElectionsList(Long localBodyId,Long stateId) throws Exception{
		List<SelectOptionVO> elections = null;
		if(localBodyId != null && stateId != null){
			List electionsList = constituencyElectionDAO.getLocalBodyElectionsInAState(localBodyId, stateId);
			if(electionsList != null && electionsList.size() > 0){
				
				elections = new ArrayList<SelectOptionVO>();
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
    			}
				
				//2nd pos info
				if(partySecPosSeatsInfo.containsKey(partyId)){
				TeshilPartyInfoVO secndPosSeats = partySecPosSeatsInfo.get(partyId);
				partyResultVO.setPartySecndPos(secndPosSeats.getSeatsWonByParty());
				}
				
				//3rd pos info
				if(partyThirdPosSeatsInfo.containsKey(partyId)){
				TeshilPartyInfoVO thirdPosSeats = partyThirdPosSeatsInfo.get(partyId);
				partyResultVO.setPartyThirdPos(thirdPosSeats.getSeatsWonByParty());	
				}
				
				//Nth pos info
				if(partyNthPosSeatsInfo.containsKey(partyId)){
				TeshilPartyInfoVO nthPosSeats = partyNthPosSeatsInfo.get(partyId);
				partyResultVO.setPartyNthPos(nthPosSeats.getSeatsWonByParty());			
				}
				
				localBodyElecResList.add(partyResultVO);
			}
			localBodyElectionResultVO.setMuncipalityVO(localBodyElecResList);
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
				resultsList = nominationDAO.getResultsOfAllPartiesInLocalBodyELectionsBasedOnNthRank(localBodyId, electionYear, rank);
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

}
