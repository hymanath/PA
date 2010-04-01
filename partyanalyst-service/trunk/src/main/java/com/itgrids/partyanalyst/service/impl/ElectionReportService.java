/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31,2010
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.ElectionResultsReportVO;
import com.itgrids.partyanalyst.dto.ElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.service.IElectionReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.PartyElectionResultsComparator;

public class ElectionReportService implements IElectionReportService {
	
	private IElectionDAO electionDAO;
	private IDistrictDAO districtDAO;
	private INominationDAO nominationDAO;
	private IStaticDataService staticDataService;
	private IAllianceGroupDAO allianceGroupDAO;
	private IElectionAllianceDAO electionAllianceDAO;
	
	private final static Logger log = Logger.getLogger(ElectionReportService.class);
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	/*
	 * (non-Javadoc)
	 * @see com.itgrids.partyanalyst.service.IElectionReportService#getBasicResultsForAnElection(java.lang.String, java.lang.String, java.lang.Long)
	 * Method returns all parties election results,such as seats won,participated,lost,2nd pos,3rd pos ....
	 */
	public IElectionAllianceDAO getElectionAllianceDAO() {
		return electionAllianceDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO) {
		this.electionAllianceDAO = electionAllianceDAO;
	}

	@SuppressWarnings("unchecked")
	public ElectionResultsReportVO getBasicResultsForAnElection(
			String electionType, String electionYear, Long stateId) {
		
		if(log.isInfoEnabled())
		    log.info("Successfully Entered Into getBasicResultsForAnElection() Method ....");
		
		Long electionID = null;
		List partiesParticipated = null;
		ResultStatus resultStatus = new ResultStatus();
		List<SelectOptionVO> participatedParties = null;
		List<PartyPositionsVO> allPartiesResults = null;
		ElectionResultsReportVO electionResultsReportVO = null;
		Map<Long,PartyElectionResult> participatedPartysResults = new HashMap<Long,PartyElectionResult>();
		Map<Long,PartyElectionResult> districtWiseParticipatedPartysResults = new HashMap<Long,PartyElectionResult>();
		
		try{
			allPartiesResults = new ArrayList<PartyPositionsVO>();
			electionID = getElectionIdForAElectionTypeAndYear(electionType,electionYear,stateId);
			
			//get Participated Parties in an election
			if(electionID != null)
			partiesParticipated = nominationDAO.getPartyIdAndShortNameForThatParticipatedInAElection(electionID);
			
			if(partiesParticipated != null && partiesParticipated.size() > 0){
				participatedParties = new ArrayList<SelectOptionVO>();
				for(int i=0;i<partiesParticipated.size();i++){
				Object[] params = (Object[])partiesParticipated.get(i);
				
				SelectOptionVO parties = new SelectOptionVO();
				parties.setId((Long)params[0]);
				parties.setName((String)params[1]);
				
				  if(parties.getId() != null){
					PartyElectionResult resultForParty = null;
					resultForParty = staticDataService.getPartyElectionResultsForAParty(electionID,parties.getId());
					if(resultForParty == null)
					resultForParty = staticDataService.savePartyElectionResultForAPartyForAElection(electionID, parties.getId());
					if(resultForParty != null)
					participatedPartysResults.put(parties.getId(), resultForParty);
					participatedParties.add(parties);
				  }
				  
				 /* //districtwise results
				  if(!electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
					  List<SelectOptionVO> districtsInfo = getDistrictsInfoInAState(stateId);
					  if(districtsInfo != null && districtsInfo.size() > 0){
					  PartyElectionDistrictResult resultForParty = null;
					  resultForParty = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(electionId, partyId, stateId, districtId);
					  }
				  }*/
				}
			}
			
			//get allianc Parties Details and results
			List<AlliancePartyResultsVO> partiesInAlliancResults = new ArrayList<AlliancePartyResultsVO>();
			List alliancGroups = electionAllianceDAO.findGroupIdByElection(electionID);
			if(alliancGroups != null && alliancGroups.size() > 0){
				List<SelectOptionVO> alliancPartys = null;
				
				for(int i=0;i<alliancGroups.size();i++){
					Object[] params = (Object[])alliancGroups.get(i);
					Long groupID = (Long)params[0];
					String groupName = (String)params[1];
					
					alliancPartys = getPartiesAsSelectOptionVOForAnAlliancGroup(groupID,electionID);
					if(alliancPartys != null){
						AlliancePartyResultsVO alliancePartyResults = new AlliancePartyResultsVO();
						List<PartyPositionsVO> alliancPartyResultsForAGroup =  new ArrayList<PartyPositionsVO>();
						for(SelectOptionVO alliancParty:alliancPartys){
							if(!participatedPartysResults.isEmpty()){
								PartyElectionResult partyElectionResult = participatedPartysResults.get(alliancParty.getId());
								PartyPositionsVO partyPositions = getElectionResultsForAParty(partyElectionResult);
								alliancPartyResultsForAGroup.add(partyPositions);
								participatedPartysResults.remove(alliancParty.getId());
							}
						}
						alliancePartyResults.setYear(electionYear);
						alliancePartyResults.setAllianceGroupName(groupName);
						alliancePartyResults.setElectionType(electionType);
						alliancePartyResults.setPartiesInAlliance(alliancPartyResultsForAGroup);
						partiesInAlliancResults.add(alliancePartyResults);
						
						allPartiesResults.add(getElectionResultsGrandTotalForAlliancParty(alliancePartyResults));
					}
				}
			}
			if(!participatedPartysResults.isEmpty()){
				Set entries = participatedPartysResults.entrySet();
				Iterator iterator = entries.iterator();
				while(iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				PartyElectionResult partyElectionResult = (PartyElectionResult)entry.getValue();
				PartyPositionsVO partyPositions = getElectionResultsForAParty(partyElectionResult);
				allPartiesResults.add(partyPositions);
				}
			}
			electionResultsReportVO = new ElectionResultsReportVO();
			ElectionResultsVO electionBasicResultsVO = new ElectionResultsVO();
			
			Collections.sort(allPartiesResults, new PartyElectionResultsComparator());
			electionBasicResultsVO.setElectionType(electionType);
			electionBasicResultsVO.setElectionYear(electionYear);
			electionBasicResultsVO.setAllPartiesResults(allPartiesResults);
			electionBasicResultsVO.setAlliancePartiesList(partiesInAlliancResults);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			electionResultsReportVO.setElectionType(electionType);
			electionResultsReportVO.setElectionYear(electionYear);
			electionResultsReportVO.setResultStatus(resultStatus);
			electionResultsReportVO.setElectionBasicResultsVO(electionBasicResultsVO);
				
		}catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		
		
		return electionResultsReportVO;
	}
	
	/*
	 * Returns ElectionID for a Particular Election Of ElectionType and ElectionYear
	 */
	@SuppressWarnings("unchecked")
	public Long getElectionIdForAElectionTypeAndYear(String electionType,String electionYear,Long stateId){
		
		if(log.isInfoEnabled())
	        log.info("Inside getElectionIdForAElectionTypeAndYear() Method ..");
		
		List elec = null;
		Long electionID = null;
		if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE))
		elec = electionDAO.findParliamentElectionIdByElectionTypeAndYear(electionType, electionYear);
		else elec = electionDAO.findElectionIdByElectionTypeAndYear(electionType, electionYear, stateId);
		
		if(elec != null && elec.size() > 0){
			Object params = (Object)elec.get(0);
			electionID = (Long)params;
		}
		return electionID;
	}
	
	/*
	 * Returns Set Of Parties As SelectOptionVO In A Particular Allianc Group And For A Particular Election
	 */
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getPartiesAsSelectOptionVOForAnAlliancGroup(Long groupId,Long electionId){
		
		if(log.isInfoEnabled())
		    log.info("Inside getPartiesAsSelectOptionVOForAnAlliancGroup() Method ..");
		
		List<SelectOptionVO> allaincParties = null;
		if(groupId != null && electionId != null){
			List partys = allianceGroupDAO.findPartysByGroupId(groupId);
			if(partys != null && partys.size() > 0){
				allaincParties = new ArrayList<SelectOptionVO>();
				for(int i=0;i<partys.size();i++){
					Object[] params = (Object[])partys.get(i);
					SelectOptionVO partySelectOptn = new SelectOptionVO();
					partySelectOptn.setId((Long)params[0]);
					partySelectOptn.setName((String)params[1]);
					
					allaincParties.add(partySelectOptn);
				}
			}
		}
		return allaincParties;
	}
	
	/*
	 * Returns Basic Election Results For A Party
	 */
	public PartyPositionsVO getElectionResultsForAParty(PartyElectionResult partyElectionResult){
		PartyPositionsVO partyResults = null;
		if(partyElectionResult != null){
			partyResults = new PartyPositionsVO();
			Party party = partyElectionResult.getParty();
			
			partyResults.setPartyId(party.getPartyId());
			if(party.getShortName() != null)
			partyResults.setPartyName(party.getShortName());
			else
			partyResults.setPartyName(party.getLongName());
			partyResults.setNthPosWon(new Long(partyElectionResult.getNthPosWon()));
			partyResults.setVotesPercentage(partyElectionResult.getVotesPercentage());
			partyResults.setThirdPosWon(new Long(partyElectionResult.getThirdPosWon()));
			partyResults.setFourthPosWon(new Long(partyElectionResult.getFourthPosWon()));
			partyResults.setSecondPosWon(new Long(partyElectionResult.getSecondPosWon()));
			partyResults.setTotalSeatsWon(new Long(partyElectionResult.getTotalSeatsWon()));
			partyResults.setCompleteVotesPercent(partyElectionResult.getCompleteVotesPercent());
			partyResults.setTotalConstiParticipated(new Long(partyElectionResult.getTotalConstiParticipated()));
		}
	 return partyResults;
	}
	
	/*
	 * Returns Aggregate Sum Of Allianc Party Results ...
	 */
	public PartyPositionsVO getElectionResultsGrandTotalForAlliancParty(AlliancePartyResultsVO alliancePartyResultsVO){
		PartyPositionsVO partyResults = null;
		
		Long seatsWonAgg = new Long(0);
		Long secndPosAgg = new Long(0);
		Long thirdPosAgg = new Long(0);
		Long fourthPosAgg = new Long(0);
		Long nthPosAgg = new Long(0);
		Double votesPercent = new Double(0);
		Double overallVotesPercent = new Double(0);
					
		if(alliancePartyResultsVO != null){
			partyResults = new PartyPositionsVO();
			int alliancPartiesSize = alliancePartyResultsVO.getPartiesInAlliance().size();
			
			for(PartyPositionsVO partyPos:alliancePartyResultsVO.getPartiesInAlliance()){
				seatsWonAgg+=partyPos.getTotalSeatsWon();
				secndPosAgg+=partyPos.getSecondPosWon();
				thirdPosAgg+=partyPos.getThirdPosWon();
				fourthPosAgg+=partyPos.getFourthPosWon();
				nthPosAgg+=partyPos.getNthPosWon();
				votesPercent+=new Double(partyPos.getVotesPercentage());
				overallVotesPercent+=new Double(partyPos.getCompleteVotesPercent());
			}
			partyResults.setPartyName(alliancePartyResultsVO.getAllianceGroupName());
			partyResults.setTotalSeatsWon(seatsWonAgg);
			partyResults.setSecondPosWon(secndPosAgg);
			partyResults.setThirdPosWon(thirdPosAgg);
			partyResults.setFourthPosWon(fourthPosAgg);
			partyResults.setNthPosWon(nthPosAgg);
			
			Double votesPercnt = new BigDecimal(votesPercent/alliancPartiesSize).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			Double completVotesPercnt = new BigDecimal(overallVotesPercent/alliancPartiesSize).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			partyResults.setVotesPercentage(votesPercnt.toString());
			partyResults.setCompleteVotesPercent(completVotesPercnt.toString());
		}
	 return partyResults;
	}
	
	@SuppressWarnings("unchecked")
	public List<SelectOptionVO> getDistrictsInfoInAState(Long stateId){
		List<SelectOptionVO> districts = null;
		if(stateId != null){
			List dists = districtDAO.getDistrictIdAndNameByState(stateId);
			if(dists != null && dists.size() > 0){
				districts = new ArrayList<SelectOptionVO>();
				for(int i=0;i<dists.size();i++){
				Object[] params = (Object[])dists.get(i);
				SelectOptionVO districtDetails = new SelectOptionVO();
				districtDetails.setId((Long)params[0]);
				districtDetails.setName((String)params[1]);
				districts.add(districtDetails);
				}
			}
		}
	 return districts;
	}
}
