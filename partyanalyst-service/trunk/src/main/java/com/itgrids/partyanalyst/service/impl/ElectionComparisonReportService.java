/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ComparedConstituencyElectionVO;
import com.itgrids.partyanalyst.dto.ComparedElectionResultVO;
import com.itgrids.partyanalyst.dto.ComparedReportVO;
import com.itgrids.partyanalyst.dto.CompleteResultsVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.service.IElectionComparisonReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;

public class ElectionComparisonReportService implements IElectionComparisonReportService {
	
	private IElectionDAO electionDAO;
	private IStaticDataService staticDataService;
	private IPartyDAO partyDAO;
	private INominationDAO nominationDAO;
	
	private final static Logger log = Logger.getLogger(ElectionComparisonReportService.class);
	
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

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public ElectionComparisonReportVO getDistrictWiseElectionResultsForAParty(
			                         Long electionType, Long partyId,Long stateId,
			                         String firstYear,String secondYear, Boolean hasAlliances) {
		
		ElectionComparisonReportVO electionCompReportVO = new ElectionComparisonReportVO();
		List<PartyPositionsVO> partyPosYearOne = null;
		List<PartyPositionsVO> partyPosYearTwo = null;
		ResultStatus resultStatus = new ResultStatus();
		log.debug("Entered Into getDistrictWiseElectionResultsForAParty Method .......");
		
		
		List<DistrictWisePartyResultVO> districtWiseElectionResultsForYearOne = null;
		List<DistrictWisePartyResultVO> districtWiseElectionResultsForYearTwo = null;
		PartyElectionResult partyElectionResultforSelectedParty = null;
		PartyElectionResult partyElectionResultforAllianceParty = null;
		CompleteResultsVO forYearOne = new CompleteResultsVO();
		CompleteResultsVO forYearTwo = new CompleteResultsVO();
		Double completeVotesPercentYearOne = null;
		Double completeVotesPercentYearTwo = null;
		Double votesPercentDiff = null;
		Long seatsWonByPartyInYearOne = new Long(0);
		Long seatsWonByPartyInYearTwo = new Long(0);
		
		List<Party> alliancPartys = null;
		try{
			
		electionCompReportVO.setStateId(stateId);
		electionCompReportVO.setPartyId(partyId);
		
		if(new Long(firstYear) > new Long(secondYear)){
			electionCompReportVO.setYearOne(firstYear);
			electionCompReportVO.setYearTwo(secondYear);	
		}else{
			electionCompReportVO.setYearOne(secondYear);
			electionCompReportVO.setYearTwo(firstYear);	
		}
		
		electionCompReportVO.setElectionType(electionType);
		electionCompReportVO.setHasAlliances(hasAlliances);
		partyPosYearOne = new ArrayList<PartyPositionsVO>();
		partyPosYearTwo = new ArrayList<PartyPositionsVO>();
				
		
		//For Election yearOne
		Election electionForYearOne = getElectionFromTypeAndYear(electionType,firstYear,stateId,new Long(1));
		if(electionForYearOne != null){
			Long seatsWon = new Long(0);
			electionCompReportVO.setElecIdYearOne(electionForYearOne.getElectionId());
		    if(hasAlliances)
			alliancPartys = staticDataService.getAllianceParties(firstYear,electionType,partyId);
		    if(alliancPartys == null || !hasAlliances){
		    	partyElectionResultforSelectedParty = staticDataService.getPartyElectionResultsForAParty(electionForYearOne.getElectionId(),partyId);
		    	if(partyElectionResultforSelectedParty == null)
		    	partyElectionResultforSelectedParty = staticDataService.savePartyElectionResultForAPartyForAElection(electionForYearOne.getElectionId(),partyId);
		    	if(partyElectionResultforSelectedParty != null){
		    	seatsWon = new Long(partyElectionResultforSelectedParty.getTotalSeatsWon());
		    	seatsWonByPartyInYearOne = new Long(partyElectionResultforSelectedParty.getTotalSeatsWon());
		    	PartyPositionsVO partyPositions = getPartyPositions(partyElectionResultforSelectedParty);
		    	completeVotesPercentYearOne = new Double(partyPositions.getCompleteVotesPercent());
		    	partyPosYearOne.add(partyPositions);
		       	}
		    }
		    else if(alliancPartys != null && hasAlliances){
		    	for(Party allincParty:alliancPartys){
			    partyElectionResultforAllianceParty = staticDataService.getPartyElectionResultsForAParty(electionForYearOne.getElectionId(),allincParty.getPartyId());
			    if(partyElectionResultforAllianceParty == null)
			    partyElectionResultforAllianceParty = staticDataService.savePartyElectionResultForAPartyForAElection(electionForYearOne.getElectionId(),allincParty.getPartyId());
			    if(partyElectionResultforAllianceParty != null){
			    seatsWon = seatsWon + new Long(partyElectionResultforAllianceParty.getTotalSeatsWon());
			    PartyPositionsVO partyPositions = getPartyPositions(partyElectionResultforAllianceParty);
			    if(allincParty.getPartyId().equals(partyId)){
			    seatsWonByPartyInYearOne = new Long(partyPositions.getTotalSeatsWon());
			    completeVotesPercentYearOne = new Double(partyPositions.getCompleteVotesPercent());
			    }
		    	partyPosYearOne.add(partyPositions);
			    }
			    
		    	}
		    }
		log.debug("Seats Won In " + firstYear + ":"+ seatsWon);
		
		forYearOne.setSeatsWon(seatsWon);
		electionCompReportVO.setForYearOne(forYearOne);
		districtWiseElectionResultsForYearOne = staticDataService.getDistrictWisePartyElectionResults(firstYear, electionType, electionForYearOne.getElectionId(), partyId, hasAlliances);
		}
		
		//For Election yearTwo
		Election electionForYearTwo = getElectionFromTypeAndYear(electionType,secondYear,stateId,new Long(1));
		if(electionForYearTwo != null){
			electionCompReportVO.setElecIdYearTwo(electionForYearTwo.getElectionId());
			Long seatsWon = new Long(0);
		    if(hasAlliances)
			alliancPartys = staticDataService.getAllianceParties(secondYear,electionType,partyId);
		    if(alliancPartys == null || !hasAlliances){
		    	partyElectionResultforSelectedParty = staticDataService.getPartyElectionResultsForAParty(electionForYearTwo.getElectionId(),partyId);
		    	if(partyElectionResultforSelectedParty == null)
		    	partyElectionResultforSelectedParty = staticDataService.savePartyElectionResultForAPartyForAElection(electionForYearTwo.getElectionId(),partyId);
		    	if(partyElectionResultforSelectedParty != null){
		    	seatsWon = new Long(partyElectionResultforSelectedParty.getTotalSeatsWon());
		    	seatsWonByPartyInYearTwo = new Long(partyElectionResultforSelectedParty.getTotalSeatsWon());
		    	PartyPositionsVO partyPositions = getPartyPositions(partyElectionResultforSelectedParty);
		    	completeVotesPercentYearTwo = new Double(partyPositions.getCompleteVotesPercent());
		    	partyPosYearTwo.add(partyPositions);
		    	}
		    	
		    }
		    else if(alliancPartys != null && hasAlliances){
		    	for(Party allincParty:alliancPartys){
			    partyElectionResultforAllianceParty = staticDataService.getPartyElectionResultsForAParty(electionForYearTwo.getElectionId(),allincParty.getPartyId());
			    if(partyElectionResultforAllianceParty == null)
			    partyElectionResultforAllianceParty = staticDataService.savePartyElectionResultForAPartyForAElection(electionForYearTwo.getElectionId(),allincParty.getPartyId());
			    if(partyElectionResultforAllianceParty != null){
			    seatsWon = seatsWon + new Long(partyElectionResultforAllianceParty.getTotalSeatsWon());
			    PartyPositionsVO partyPositions = getPartyPositions(partyElectionResultforAllianceParty);
			    if(allincParty.getPartyId().equals(partyId)){
			    seatsWonByPartyInYearTwo = new Long(partyPositions.getTotalSeatsWon());
			    completeVotesPercentYearTwo = new Double(partyPositions.getCompleteVotesPercent());
			    }
			    partyPosYearTwo.add(partyPositions);
			    }
			   	}
		    }
		log.debug("Seats Won In " + secondYear + ":"+ seatsWon);  
		
		forYearTwo.setSeatsWon(seatsWon);
		electionCompReportVO.setForYearTwo(forYearTwo);
		
		districtWiseElectionResultsForYearTwo = staticDataService.getDistrictWisePartyElectionResults(secondYear, electionType, electionForYearTwo.getElectionId(), partyId, hasAlliances);
		}
		
		votesPercentDiff = new BigDecimal(completeVotesPercentYearTwo - completeVotesPercentYearOne).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		Long seatsDifferenceForParty = seatsWonByPartyInYearTwo - seatsWonByPartyInYearOne;
		
		electionCompReportVO.setPositionsForYearOne(partyPosYearOne);
		electionCompReportVO.setPositionsForYearTwo(partyPosYearTwo);
		electionCompReportVO.setVotesPercentDiff(votesPercentDiff.toString());
		electionCompReportVO.setSeatsDiff(seatsDifferenceForParty);
		
		if(districtWiseElectionResultsForYearOne != null)
		electionCompReportVO.setDistrictWisePartyResultsForYearOne(districtWiseElectionResultsForYearOne);
		if(districtWiseElectionResultsForYearTwo != null)
		electionCompReportVO.setDistrictWisePartyResultsForYearTwo(districtWiseElectionResultsForYearTwo);
		
		}catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultPartial(true);
			electionCompReportVO.setResultStatus(resultStatus);
		}
		
		//Code for votes % Diff, Seats Diff - Mohan
		if(new Long(electionCompReportVO.getYearOne()) > new Long(electionCompReportVO.getYearTwo())){
			for(DistrictWisePartyResultVO districtVO1:districtWiseElectionResultsForYearOne){
				for(DistrictWisePartyResultVO districtVO2:districtWiseElectionResultsForYearTwo){
					if(districtVO2.getDistrictName().equalsIgnoreCase(districtVO1.getDistrictName())){
						
						districtVO1.setVotesPercentDiff(new BigDecimal(districtVO2.getVotesPercent() - districtVO1.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						districtVO1.setSeatsWonDiff(districtVO2.getSeatsWon() - districtVO1.getSeatsWon());
						
						districtVO2.setVotesPercentDiff(new BigDecimal(districtVO2.getVotesPercent() - districtVO1.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						districtVO2.setSeatsWonDiff(districtVO2.getSeatsWon() - districtVO1.getSeatsWon());
						break;
					}
				}
			}
		}else{
			for(DistrictWisePartyResultVO districtVO1:districtWiseElectionResultsForYearOne){
				for(DistrictWisePartyResultVO districtVO2:districtWiseElectionResultsForYearTwo){
					if(districtVO2.getDistrictName().equalsIgnoreCase(districtVO1.getDistrictName())){
						
						districtVO1.setVotesPercentDiff(new BigDecimal(districtVO1.getVotesPercent() - districtVO2.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						districtVO1.setSeatsWonDiff(districtVO1.getSeatsWon() - districtVO2.getSeatsWon());
						
						districtVO2.setVotesPercentDiff(new BigDecimal(districtVO1.getVotesPercent() - districtVO2.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
						districtVO2.setSeatsWonDiff(districtVO1.getSeatsWon() - districtVO2.getSeatsWon());
						break;
					}
				}
			}
		}
		
		if(new Long(firstYear) > new Long(secondYear)){
			electionCompReportVO.setDistrictWisePartyResultsForYearTwo(districtWiseElectionResultsForYearTwo);
			electionCompReportVO.setDistrictWisePartyResultsForYearOne(districtWiseElectionResultsForYearOne);
		}else{
			electionCompReportVO.setDistrictWisePartyResultsForYearOne(districtWiseElectionResultsForYearTwo);
			electionCompReportVO.setDistrictWisePartyResultsForYearTwo(districtWiseElectionResultsForYearOne);
		}
		
		return electionCompReportVO;
	}
		
	public PartyPositionsVO getPartyPositions(PartyElectionResult partyElecResults){
		PartyPositionsVO partyPositions = new PartyPositionsVO();
		partyPositions.setTotalSeatsWon(new Long(partyElecResults.getTotalSeatsWon()));
    	partyPositions.setSecondPosWon(new Long(partyElecResults.getSecondPosWon()));
    	partyPositions.setThirdPosWon(new Long(partyElecResults.getThirdPosWon()));
    	partyPositions.setFourthPosWon(new Long(partyElecResults.getFourthPosWon()));
    	partyPositions.setNthPosWon(new Long(partyElecResults.getNthPosWon()));
    	partyPositions.setTotalConstiParticipated(new Long(partyElecResults.getTotalConstiParticipated()));
    	partyPositions.setPartyId(partyElecResults.getParty().getPartyId());
    	partyPositions.setPartyName(partyElecResults.getParty().getShortName());
    	partyPositions.setVotesPercentage(partyElecResults.getVotesPercentage());
    	partyPositions.setCompleteVotesPercent(partyElecResults.getCompleteVotesPercent());
    	
		return partyPositions;
	}
	
	public Election getElectionFromTypeAndYear(Long electionType,String elecYear,Long stateId,Long countryId){
		
		log.debug("Inside getElectionFromTypeAndYear Method .......");
		
		Election election = null;
		List<Election> elections = electionDAO.findByElectionTypeYearAndState(electionType,elecYear,stateId,countryId);
		if(elections != null && elections.size() > 0)
		election = elections.get(0);
	
	 return election;
	}
	
	public ComparedReportVO getComparedElectionResults(Long electionType,Long stateId,Long partyId,String yearOne,String yearTwo,Long districtId,Boolean hasAlliances){
		
		log.debug("Inside getComparedElectionResults Method .......");
		
		ComparedReportVO comparedResultVO = null;
		Election elecYearOne = null;
		Election elecYearTwo = null;
		List<Party> alliancPartiesForYearOne = null;
		List<Party> alliancPartiesForYearTwo = null;
		List<Long> partyIdsYearOne = null;
		List<Long> partyIdsYearTwo = null;
		List<PartyPositionsVO> posDetailsYearOne;
		List<PartyPositionsVO> posDetailsYearTwo;
		List<ConstituencyElection> constiElectionsForYearOne = null;
		List<ConstituencyElection> constiElectionsForYearTwo = null;
		
		try{
		if(electionType != null && yearOne != null && yearTwo != null && stateId != null){
			
		partyIdsYearOne = new ArrayList<Long>();
		partyIdsYearTwo = new ArrayList<Long>();
		
		posDetailsYearOne = new ArrayList<PartyPositionsVO>();
		posDetailsYearTwo = new ArrayList<PartyPositionsVO>();
		
		elecYearOne = getElectionFromTypeAndYear(electionType,yearOne,stateId,new Long(1));
		elecYearTwo = getElectionFromTypeAndYear(electionType,yearTwo,stateId,new Long(1));
		}
		    if(elecYearOne != null && elecYearTwo != null){
			comparedResultVO = new ComparedReportVO();
			Party party = partyDAO.get(partyId);
			
			if(hasAlliances){
				alliancPartiesForYearOne = staticDataService.getAllianceParties(yearOne,electionType,partyId);
				alliancPartiesForYearTwo = staticDataService.getAllianceParties(yearTwo,electionType,partyId);
				if(alliancPartiesForYearOne == null || alliancPartiesForYearOne.size() == 0){
				alliancPartiesForYearOne = new ArrayList<Party>();
				alliancPartiesForYearOne.add(party);
				}
				if(alliancPartiesForYearTwo == null || alliancPartiesForYearTwo.size() == 0){
				alliancPartiesForYearTwo =  new ArrayList<Party>();
				alliancPartiesForYearTwo.add(party);
				}
			}
			else if(!hasAlliances){
				alliancPartiesForYearOne = new ArrayList<Party>();
				alliancPartiesForYearTwo =  new ArrayList<Party>();
				alliancPartiesForYearOne.add(party);
				alliancPartiesForYearTwo.add(party);
			}
			partyIdsYearOne = getAlliancPartyIds(alliancPartiesForYearOne);
			partyIdsYearTwo = getAlliancPartyIds(alliancPartiesForYearTwo);
			
			posDetailsYearOne = getPartyPositionsForADistrict(elecYearOne.getElectionId(),stateId,districtId,partyIdsYearOne);
			posDetailsYearTwo = getPartyPositionsForADistrict(elecYearTwo.getElectionId(),stateId,districtId,partyIdsYearTwo);
			
			constiElectionsForYearOne = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(elecYearOne.getElectionId(), districtId, partyIdsYearOne);
			constiElectionsForYearTwo = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(elecYearTwo.getElectionId(), districtId, partyIdsYearTwo);
			
			log.debug("Total Consti In " + yearOne + " :" + constiElectionsForYearOne.size());
			log.debug("Total Consti In " + yearTwo + " :" + constiElectionsForYearTwo.size());
			
			if(constiElectionsForYearOne != null && constiElectionsForYearTwo != null)
			comparedResultVO = 	getDetailedComparedResults(yearOne,constiElectionsForYearOne,yearTwo,constiElectionsForYearTwo,partyId,partyIdsYearOne,partyIdsYearTwo,hasAlliances);
			comparedResultVO.setPositionsYearOne(posDetailsYearOne);
			comparedResultVO.setPositionsYearTwo(posDetailsYearTwo);
		    }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		return comparedResultVO;
	}
	
	public List<Long> getAlliancPartyIds(List<Party> alliancPartys) throws Exception{
		List<Long> partyIds = new ArrayList<Long>();
		for(Party party:alliancPartys){
		partyIds.add(party.getPartyId());
		}
	  return partyIds;
	}
	
	public List<PartyPositionsVO> getPartyPositionsForADistrict(Long electionId,Long stateId,Long districtId,List<Long> partyIds){
		List<PartyPositionsVO> partyPositionsList = null;
		
		PartyElectionDistrictResult partyElectionResultParty = null;
				
		if(electionId != null && stateId != null && districtId != null){
		partyPositionsList = new ArrayList<PartyPositionsVO>();
			
		  for(Long partyId:partyIds){
			
			  partyElectionResultParty = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(electionId, partyId, stateId, districtId);
		      if(partyElectionResultParty == null)
		      partyElectionResultParty = staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(electionId, partyId, stateId, districtId);
		      if(partyElectionResultParty != null){
		      PartyPositionsVO partyPositions = getPartyPositionsForADistrict(partyElectionResultParty);
		      partyPositionsList.add(partyPositions);
		      }
	      }
		
    	}
		return partyPositionsList;
	}
		
		public PartyPositionsVO getPartyPositionsForADistrict(PartyElectionDistrictResult partyElecResults){
			PartyPositionsVO partyPositions = new PartyPositionsVO();
			partyPositions.setTotalSeatsWon(new Long(partyElecResults.getTotalSeatsWon()));
	    	partyPositions.setSecondPosWon(new Long(partyElecResults.getSecondPosWon()));
	    	partyPositions.setThirdPosWon(new Long(partyElecResults.getThirdPosWon()));
	    	partyPositions.setFourthPosWon(new Long(partyElecResults.getFourthPosWon()));
	    	partyPositions.setNthPosWon(new Long(partyElecResults.getNthPosWon()));
	    	partyPositions.setTotalConstiParticipated(new Long(partyElecResults.getTotalConstiParticipated()));
	    	partyPositions.setPartyId(partyElecResults.getParty().getPartyId());
	    	partyPositions.setPartyName(partyElecResults.getParty().getShortName());
	    	partyPositions.setVotesPercentage(partyElecResults.getVotesPercentage());
	    	partyPositions.setCompleteVotesPercent(partyElecResults.getCompleteVotesPercent());
	    	
			return partyPositions;
		}
	
	@SuppressWarnings("unchecked")
	public ComparedReportVO getDetailedComparedResults(String yearOne,List<ConstituencyElection> constiElectionsForYearOne,String yearTwo,List<ConstituencyElection> constiElectionsForYearTwo,Long partyId,List<Long> alliancPartyIdsYearOne,List<Long> alliancPartyIdsYearTwo,Boolean hasAlliances){
		
		log.debug("Inside getDetailedComparedResults Method .......");
		
		ComparedReportVO comparedReportVO = null;
		ComparedReportVO comparedReportNewVO = null;
		List<PartyResultVO> notConsideredYearOneResults = null;
		List<PartyResultVO> notConsideredYearTwoResults = null;
		ComparedConstituencyElectionVO comparedConstituencyElectionVO = null;
		
		if(constiElectionsForYearOne != null && constiElectionsForYearTwo != null){
			comparedReportVO    = new ComparedReportVO();
			comparedReportNewVO = new ComparedReportVO();
						
			Map<Long,ComparedConstituencyElectionVO> comparedConstiElecMap = new HashMap<Long,ComparedConstituencyElectionVO>();
			Map<Long,Nomination> rebelsMap = new HashMap<Long,Nomination>();
						
			List<Long> constituencyIds = new ArrayList<Long>();
			for(ConstituencyElection constiElecOne:constiElectionsForYearOne){
				for(ConstituencyElection constiElecTwo:constiElectionsForYearTwo){
					if(constiElecTwo.getConstituency().getConstituencyId().equals(constiElecOne.getConstituency().getConstituencyId())){
					comparedConstituencyElectionVO = new ComparedConstituencyElectionVO();
					comparedConstituencyElectionVO.setConstiElecForYearOne(constiElecOne);
					comparedConstituencyElectionVO.setConstiElecForYearTwo(constiElecTwo);
					comparedConstituencyElectionVO.setYearOne(yearOne);
					comparedConstituencyElectionVO.setYearTwo(yearTwo);
					
					if(comparedConstiElecMap.isEmpty() || !comparedConstiElecMap.containsKey(constiElecOne.getConstituency().getConstituencyId()))
					comparedConstiElecMap.put(constiElecOne.getConstituency().getConstituencyId(), comparedConstituencyElectionVO);
								
					log.debug("Constituency ::" + constiElecOne.getConstituency().getName());
					constituencyIds.add(constiElecOne.getConstituency().getConstituencyId());
					break;
					}
				}
			}
			if(!comparedConstiElecMap.isEmpty()){
				List<ComparedConstituencyElectionVO> compConstiElecList = new ArrayList<ComparedConstituencyElectionVO>();
				Set entries = comparedConstiElecMap.entrySet();
				Iterator iterator = entries.iterator();
				while (iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				ComparedConstituencyElectionVO compConstiElec= (ComparedConstituencyElectionVO)entry.getValue();
				compConstiElecList.add(compConstiElec);
			    }
				comparedReportNewVO = getResultsForVotesGainedOrLost(compConstiElecList,yearOne,yearTwo,partyId,alliancPartyIdsYearOne,alliancPartyIdsYearTwo,hasAlliances);
				
			}
				
			notConsideredYearOneResults = getNotconsideredConstituencies(constiElectionsForYearOne,yearOne,constituencyIds,partyId,alliancPartyIdsYearOne,hasAlliances);
			notConsideredYearTwoResults = getNotconsideredConstituencies(constiElectionsForYearTwo,yearTwo,constituencyIds,partyId,alliancPartyIdsYearTwo,hasAlliances);
			
			comparedReportVO.setVotesPercentGainedResults(comparedReportNewVO.getVotesPercentGainedResults());
			comparedReportVO.setVotesPercentLostResults(comparedReportNewVO.getVotesPercentLostResults());
			comparedReportVO.setNotConsideredYearOneResults(notConsideredYearOneResults);
			comparedReportVO.setNotConsideredYearTwoResults(notConsideredYearTwoResults);
			comparedReportVO.setYearOne(yearOne);
			comparedReportVO.setYearTwo(yearTwo);
		}
		
		return comparedReportVO;
	}
	
	public ComparedReportVO getResultsForVotesGainedOrLost(List<ComparedConstituencyElectionVO> compConstiElecList ,String elecYearOne,String elecYearTwo,Long partyId,List<Long> alliancPartyIdsYearOne,List<Long> alliancPartyIdsYearTwo,Boolean hasAlliances){
		ComparedReportVO comparedReportVO = null;
		
		log.debug("Inside getResultsForVotesGainedOrLost Method .......");
		
		if(compConstiElecList != null){
			Set<Nomination> nominationsForYearOne = null;
			Set<Nomination> nominationsForYearTwo = null;
			List<ComparedElectionResultVO> votesPercentGainedResultsList = new ArrayList<ComparedElectionResultVO>();
		    List<ComparedElectionResultVO> votesPercentLostResultsList = new ArrayList<ComparedElectionResultVO>();
			comparedReportVO = new ComparedReportVO();
			for(ComparedConstituencyElectionVO compConstiResults:compConstiElecList){
				nominationsForYearOne = compConstiResults.getConstiElecForYearOne().getNominations();
				nominationsForYearTwo = compConstiResults.getConstiElecForYearTwo().getNominations();
				Nomination selectedNominationYearOne = null;
				Nomination selectedNominationYearTwo = null;
				
				if(!hasAlliances){
					selectedNominationYearOne = getSelectedNominationWithoutAllianc(nominationsForYearOne,partyId);
					selectedNominationYearTwo = getSelectedNominationWithoutAllianc(nominationsForYearTwo,partyId);
				}
				else if(hasAlliances){
				    selectedNominationYearOne = getSelectedNominationWithAllianc(nominationsForYearOne,partyId,alliancPartyIdsYearOne,hasAlliances);
					selectedNominationYearTwo = getSelectedNominationWithAllianc(nominationsForYearTwo,partyId,alliancPartyIdsYearOne,hasAlliances);
				}	
					    if(selectedNominationYearOne != null && selectedNominationYearTwo != null){
						Double votesEarnedYearOne = selectedNominationYearOne.getCandidateResult().getVotesEarned();
						Double votesEarnedYearTwo = selectedNominationYearTwo.getCandidateResult().getVotesEarned();
						Double validVotesYearOne = selectedNominationYearOne.getConstituencyElection().getConstituencyElectionResult().getValidVotes();
						Double validVotesYearTwo = selectedNominationYearTwo.getConstituencyElection().getConstituencyElectionResult().getValidVotes();
						
						Double votesPercentYearOne = new BigDecimal((votesEarnedYearOne/validVotesYearOne)*100).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
						Double votesPercentYearTwo = new BigDecimal((votesEarnedYearTwo/validVotesYearTwo)*100).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
						Double votesPercentDiff = new BigDecimal(votesPercentYearTwo - votesPercentYearOne).setScale (2,BigDecimal.ROUND_HALF_UP).doubleValue();
						
						log.debug("Votes% One" + votesPercentYearOne);
						log.debug("Votes% Two" + votesPercentYearTwo);
						
						if(votesPercentDiff > 0){
						ComparedElectionResultVO votesPercentGainedResults = getResultDetailsIntoVO(selectedNominationYearOne,selectedNominationYearTwo,votesPercentYearOne,votesPercentYearTwo,votesPercentDiff);
						votesPercentGainedResultsList.add(votesPercentGainedResults);
						}
						else if(votesPercentDiff <= 0){
						ComparedElectionResultVO votesPercentLostResults = 	getResultDetailsIntoVO(selectedNominationYearOne,selectedNominationYearTwo,votesPercentYearOne,votesPercentYearTwo,votesPercentDiff);
						votesPercentLostResultsList.add(votesPercentLostResults);
						}
					    }
			}
			comparedReportVO.setVotesPercentGainedResults(votesPercentGainedResultsList);
			comparedReportVO.setVotesPercentLostResults(votesPercentLostResultsList);
		}
		
		return comparedReportVO;
	}
	
	public ComparedElectionResultVO getResultDetailsIntoVO(Nomination selectedNominationYearOne,Nomination selectedNominationYearTwo,Double votesPercentYearOne,Double votesPercentYearTwo,Double votesPercentDiff){
		ComparedElectionResultVO votesPercentResults = new ComparedElectionResultVO();
		
		log.debug("Inside getResultDetailsIntoVO Method .......");
		
		votesPercentResults.setCandName(selectedNominationYearOne.getCandidate().getLastname());
		votesPercentResults.setConstiName(selectedNominationYearOne.getConstituencyElection().getConstituency().getName());
		votesPercentResults.setVotesEarned(selectedNominationYearOne.getCandidateResult().getVotesEarned().longValue());
		votesPercentResults.setVotesPercent(votesPercentYearOne.toString());
		votesPercentResults.setPartyName(selectedNominationYearOne.getParty().getShortName());
		votesPercentResults.setRank(selectedNominationYearOne.getCandidateResult().getRank());
		votesPercentResults.setSecndCandName(selectedNominationYearTwo.getCandidate().getLastname());
		votesPercentResults.setSecndConstiName(selectedNominationYearTwo.getConstituencyElection().getConstituency().getName());
		votesPercentResults.setVotesEarnedBySecnd(selectedNominationYearTwo.getCandidateResult().getVotesEarned().longValue());
		votesPercentResults.setSecndVotesPercent(votesPercentYearTwo.toString());
		votesPercentResults.setSecndCandPartyName(selectedNominationYearTwo.getParty().getShortName());
		votesPercentResults.setSecndCandRank(selectedNominationYearTwo.getCandidateResult().getRank());
		votesPercentResults.setVotesPercentDiff(votesPercentDiff.toString());
		
		return votesPercentResults;
	}
	
	public Nomination getSelectedNominationWithoutAllianc(Set<Nomination> nominations,Long partyId){
		Nomination nomination = null;
		for(Nomination nominatn:nominations){
		if(nominatn.getParty().getPartyId().equals(partyId))
		return nominatn;
		}
	return nomination;
	}
	
	public Nomination getSelectedNominationWithAllianc(Set<Nomination> nominations,Long partyId,List<Long> alliancPartyIds,Boolean hasAlliances){
		Nomination nomination = null;
		
		for(Nomination nominatn:nominations){
			if(nominatn.getParty().getPartyId().equals(partyId))
			return nominatn;
			
			if(alliancPartyIds.contains(nominatn.getParty().getPartyId())){
			if(nomination == null)
			nomination = nominatn;
			else if(nomination != null && nomination.getCandidateResult().getRank() > nominatn.getCandidateResult().getRank())
			nomination = nominatn;
			}
		}
		
		return nomination;
	}
	
	public List<PartyResultVO> getNotconsideredConstituencies(List<ConstituencyElection> constiElections,String elecYear,List<Long> comparedConstiIds,Long partyId,List<Long> alliancPartyIds,Boolean hasAllianc){
		List<PartyResultVO> notConsideredResults = null;
		
		log.debug("Inside getNotconsideredConstituencies Method (for" + elecYear +" ).......");
		
		if(constiElections != null){
			notConsideredResults = new ArrayList<PartyResultVO>();
		for(ConstituencyElection constiElecResults:constiElections){
			if(!comparedConstiIds.contains(constiElecResults.getConstituency().getConstituencyId())){
			log.debug("Constituency ::" + constiElecResults.getConstituency().getName());
				
			PartyResultVO partyResult = getPartyResultsForNotConsideredConsti(constiElecResults,partyId,alliancPartyIds,hasAllianc);
			notConsideredResults.add(partyResult);
			}
		}
		
		}
		
	return notConsideredResults;
	}
	
	public PartyResultVO getPartyResultsForNotConsideredConsti(ConstituencyElection constiElection,Long partyId,List<Long> alliancPartyIds,Boolean hasAllianc){
		PartyResultVO partyResultVO = null;
		if(constiElection != null){
		Nomination selectdNomination = null;
		partyResultVO = new PartyResultVO();
		Set<Nomination> selectedNominations = constiElection.getNominations();
		if(hasAllianc)
		selectdNomination = getSelectedNominationWithAllianc(selectedNominations,partyId,alliancPartyIds,hasAllianc);
		else if(!hasAllianc)
		selectdNomination = getSelectedNominationWithoutAllianc(selectedNominations,partyId);
		
		if(selectdNomination != null){
		partyResultVO.setCandidateName(selectdNomination.getCandidate().getLastname());
		partyResultVO.setConstituencyName(selectdNomination.getConstituencyElection().getConstituency().getName());
		partyResultVO.setVotesEarned(selectdNomination.getCandidateResult().getVotesEarned().longValue());
		partyResultVO.setVotesPercent(selectdNomination.getCandidateResult().getVotesPercengate());
		partyResultVO.setPartyName(selectdNomination.getParty().getShortName());
		partyResultVO.setRank(selectdNomination.getCandidateResult().getRank());
		}
		}
		
		return partyResultVO;
	}
	

}
