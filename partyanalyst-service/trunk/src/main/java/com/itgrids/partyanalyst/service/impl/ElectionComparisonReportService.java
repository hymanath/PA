/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IElectionAllianceDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionDistrictResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionStateResultWithAllianceDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.ComparedConstituencyElectionVO;
import com.itgrids.partyanalyst.dto.ComparedElectionResultVO;
import com.itgrids.partyanalyst.dto.ComparedReportVO;
import com.itgrids.partyanalyst.dto.CompleteResultsVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonReportVO;
import com.itgrids.partyanalyst.dto.PartyPositionsVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.Group;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResultWithAlliance;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IElectionComparisonReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.NominatResultsComparator;

public class ElectionComparisonReportService implements IElectionComparisonReportService {
	
	private IElectionDAO electionDAO;
	private IStaticDataService staticDataService;
	private IPartyDAO partyDAO;
	private INominationDAO nominationDAO;
	private IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO;
	private IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO;
	private TransactionTemplate transactionTemplate;
	private IStateDAO stateDAO;
	private IElectionAllianceDAO  electionAllianceDAO; 
	
	
	public IPartyElectionStateResultWithAllianceDAO getPartyElectionStateResultWithAllianceDAO() {
		return partyElectionStateResultWithAllianceDAO;
	}

	public void setPartyElectionStateResultWithAllianceDAO(
			IPartyElectionStateResultWithAllianceDAO partyElectionStateResultWithAllianceDAO) {
		this.partyElectionStateResultWithAllianceDAO = partyElectionStateResultWithAllianceDAO;
	}

	public IPartyElectionDistrictResultWithAllianceDAO getPartyElectionDistrictResultWithAllianceDAO() {
		return partyElectionDistrictResultWithAllianceDAO;
	}

	public void setPartyElectionDistrictResultWithAllianceDAO(
			IPartyElectionDistrictResultWithAllianceDAO partyElectionDistrictResultWithAllianceDAO) {
		this.partyElectionDistrictResultWithAllianceDAO = partyElectionDistrictResultWithAllianceDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IElectionAllianceDAO getElectionAllianceDAO() {
		return electionAllianceDAO;
	}

	public void setElectionAllianceDAO(IElectionAllianceDAO electionAllianceDAO) {
		this.electionAllianceDAO = electionAllianceDAO;
	}
	private final static Logger log = Logger.getLogger(ElectionComparisonReportService.class);
	
	
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
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

	public ElectionComparisonReportVO getDistrictWiseElectionResultsForAParty(Long partyId,Long firstElectionId,
			Long secondElectionId, Boolean hasAlliances) {
		
		Election electionForYearOne = electionDAO.get(firstElectionId);
		Election electionForYearTwo = electionDAO.get(secondElectionId);
		String firstYear = electionForYearOne.getElectionYear();
		String secondYear = electionForYearTwo.getElectionYear();
		Long electionTypeId = electionForYearTwo.getElectionScope().getElectionType().getElectionTypeId();
		String electionType = electionForYearTwo.getElectionScope().getElectionType().getElectionType(); 
		
		if(new Long(firstYear) < new Long(secondYear)){
			electionForYearOne = electionDAO.get(secondElectionId);
			electionForYearTwo = electionDAO.get(firstElectionId);
			firstYear = electionForYearOne.getElectionYear();
			secondYear = electionForYearTwo.getElectionYear();
		}
		
		ElectionComparisonReportVO electionCompReportVO = new ElectionComparisonReportVO();
		List<PartyPositionsVO> partyPosYearOne = null;
		List<PartyPositionsVO> partyPosYearTwo = null;
		ResultStatus resultStatus = new ResultStatus();
		log.debug("Entered Into getDistrictWiseElectionResultsForAParty Method .......");
		String partyIds = "";
		
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
		List<DistrictWisePartyResultVO> districtWisePartyResultVOListForYearOne = new ArrayList<DistrictWisePartyResultVO>();
		List<DistrictWisePartyResultVO> districtWisePartyResultVOListForYearTwo = new ArrayList<DistrictWisePartyResultVO>();
		
		List<Party> alliancPartys = null;
		try{
			
		electionCompReportVO.setPartyId(partyId);
		electionCompReportVO.setYearOne(firstYear);
		electionCompReportVO.setYearTwo(secondYear);	
		electionCompReportVO.setHasAlliances(hasAlliances);
		electionCompReportVO.setElectionTypeId(electionTypeId);
		electionCompReportVO.setElectionType(electionType);
		partyPosYearOne = new ArrayList<PartyPositionsVO>();
		partyPosYearTwo = new ArrayList<PartyPositionsVO>();
				
		
		//For Election yearOne		
		if(electionForYearOne != null){
			Long seatsWon = new Long(0);
			electionCompReportVO.setElecIdYearOne(electionForYearOne.getElectionId());
		    if(hasAlliances)
		    	alliancPartys = staticDataService.getAllianceParties(firstYear,electionTypeId,partyId);
		    if(alliancPartys == null || !hasAlliances){
		    	partyIds = partyId.toString();
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
		    	if(alliancPartys.size() > 1)
		    		partyIds = getCommaSeperatedPartyIds(alliancPartys);
		    	else
		    		partyIds = partyId.toString();
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
		districtWiseElectionResultsForYearOne = staticDataService.getDistrictWisePartyElectionResults(electionType,
				electionForYearOne.getElectionId(), partyIds,hasAlliances);
		}
		
		//For Election yearTwo
		
		if(electionForYearTwo != null){
			electionCompReportVO.setElecIdYearTwo(electionForYearTwo.getElectionId());
			Long seatsWon = new Long(0);
		    if(hasAlliances)
		    	alliancPartys = staticDataService.getAllianceParties(secondYear,electionTypeId,partyId);
		    if(alliancPartys == null || !hasAlliances){
		    	partyIds = partyId.toString();
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
		    	if(alliancPartys.size() > 1)
		    		partyIds = getCommaSeperatedPartyIds(alliancPartys);
		    	else
		    		partyIds = partyId.toString();
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
		log.debug("Seats Won In :" + secondYear + ":"+ seatsWon);  
		log.debug("PartyIds:" + partyIds);  
		
		forYearTwo.setSeatsWon(seatsWon);
		electionCompReportVO.setForYearTwo(forYearTwo);
		
		districtWiseElectionResultsForYearTwo = staticDataService.getDistrictWisePartyElectionResults(electionType, 
				electionForYearTwo.getElectionId(), partyIds,hasAlliances);
		}
		
		votesPercentDiff = new BigDecimal(completeVotesPercentYearOne - completeVotesPercentYearTwo).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		Long seatsDifferenceForParty = seatsWonByPartyInYearOne - seatsWonByPartyInYearTwo;
		
		electionCompReportVO.setPositionsForYearOne(partyPosYearOne);
		electionCompReportVO.setPositionsForYearTwo(partyPosYearTwo);
		electionCompReportVO.setVotesPercentDiff(votesPercentDiff.toString());
		electionCompReportVO.setSeatsDiff(seatsDifferenceForParty);
		
		
		for(DistrictWisePartyResultVO resultForYearOne : districtWiseElectionResultsForYearOne){
			if(resultForYearOne.getSeatsWon()==0 && resultForYearOne.getTotalConstituencies()==0){
				for(DistrictWisePartyResultVO resultresultForYearTwo : districtWiseElectionResultsForYearTwo){
					if(resultForYearOne.getDistrictId().equals(resultresultForYearTwo.getDistrictId())){
						if(resultresultForYearTwo.getSeatsWon()!=0 || resultresultForYearTwo.getTotalConstituencies()!=0){
							districtWisePartyResultVOListForYearOne.add(setDataIntoDistrictWisePartyResultVO(resultForYearOne));
							districtWisePartyResultVOListForYearTwo.add(setDataIntoDistrictWisePartyResultVO(resultresultForYearTwo));							
						}						
					}
				}
			}else{				
				districtWisePartyResultVOListForYearOne.add(setDataIntoDistrictWisePartyResultVO(resultForYearOne));
				for(DistrictWisePartyResultVO resultresultForYearTwo : districtWiseElectionResultsForYearTwo){
					if(resultForYearOne.getDistrictId().equals(resultresultForYearTwo.getDistrictId())){
						districtWisePartyResultVOListForYearTwo.add(setDataIntoDistrictWisePartyResultVO(resultresultForYearTwo));
					}					
				}				
			}
		}
		
		if(districtWisePartyResultVOListForYearOne != null)
		electionCompReportVO.setDistrictWisePartyResultsForYearOne(districtWisePartyResultVOListForYearOne);
		if(districtWiseElectionResultsForYearTwo != null)
		electionCompReportVO.setDistrictWisePartyResultsForYearTwo(districtWisePartyResultVOListForYearTwo);
		
		}catch(Exception ex){
			ex.printStackTrace();
			resultStatus.setExceptionEncountered(ex);
			resultStatus.setResultPartial(true);
			electionCompReportVO.setResultStatus(resultStatus);
		}
		
		//Code for votes % Diff, Seats Diff - Mohan
		for(DistrictWisePartyResultVO districtVO1:districtWisePartyResultVOListForYearOne){
			for(DistrictWisePartyResultVO districtVO2:districtWisePartyResultVOListForYearTwo){
				if(districtVO2.getDistrictName().equalsIgnoreCase(districtVO1.getDistrictName())){
					
					districtVO1.setVotesPercentDiff(new BigDecimal(districtVO1.getVotesPercent() - districtVO2.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					districtVO1.setSeatsWonDiff(districtVO1.getSeatsWon() - districtVO2.getSeatsWon());
					
					districtVO2.setVotesPercentDiff(new BigDecimal(districtVO1.getVotesPercent() - districtVO2.getVotesPercent()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					districtVO2.setSeatsWonDiff(districtVO1.getSeatsWon() - districtVO2.getSeatsWon());
					break;
				}
			}
		}
		
		return electionCompReportVO;
	}
	
	
	private DistrictWisePartyResultVO setDataIntoDistrictWisePartyResultVO(DistrictWisePartyResultVO resultForYearOne){
		DistrictWisePartyResultVO districtWisePartyResultVO = null;
		districtWisePartyResultVO = new DistrictWisePartyResultVO();
		districtWisePartyResultVO.setDistrictId(resultForYearOne.getDistrictId());
		districtWisePartyResultVO.setDistrictName(resultForYearOne.getDistrictName());
		districtWisePartyResultVO.setTotalConstituencies(resultForYearOne.getTotalConstituencies());
		districtWisePartyResultVO.setConstiCount(resultForYearOne.getConstiCount());
		districtWisePartyResultVO.setConstiParticipated(resultForYearOne.getConstiParticipated());
		districtWisePartyResultVO.setSeatsWon(resultForYearOne.getSeatsWon());
		districtWisePartyResultVO.setVotesPercent(resultForYearOne.getVotesPercent());
		districtWisePartyResultVO.setTotalPercentage(resultForYearOne.getTotalPercentage());
		return districtWisePartyResultVO;
	}	
	
	private String getCommaSeperatedPartyIds(List<Party> alliancPartys) {
		StringBuilder str = new StringBuilder();
		for(Party party:alliancPartys)
			str.append(IConstants.COMMA).append(party.getPartyId());
		return str.toString().substring(1);
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
	
	public ComparedReportVO getComparedElectionResults(Long partyId,Long elecIdOne,
			Long elecIdTwo,Long stateOrDistrictId,Boolean hasAlliances){
		
		log.debug("Inside getComparedElectionResults Method .......");
		
		ComparedReportVO comparedResultVO = null;
		Election elecYearOne = electionDAO.get(elecIdOne);
		Election elecYearTwo = electionDAO.get(elecIdTwo);
		String yearOne = elecYearOne.getElectionYear();
		String yearTwo = elecYearTwo.getElectionYear();
		Long electionTypeId = elecYearTwo.getElectionScope().getElectionType().getElectionTypeId();
		String electionType = elecYearTwo.getElectionScope().getElectionType().getElectionType();
		List<Party> alliancPartiesForYearOne = null;
		List<Party> alliancPartiesForYearTwo = null;
		List<Long> partyIdsYearOne = null;
		List<Long> partyIdsYearTwo = null;
		List<PartyPositionsVO> posDetailsYearOne;
		List<PartyPositionsVO> posDetailsYearTwo;
		List<ConstituencyElection> constiElectionsForYearOne = null;
		List<ConstituencyElection> constiElectionsForYearTwo = null;
		
		try{
			partyIdsYearOne = new ArrayList<Long>();
			partyIdsYearTwo = new ArrayList<Long>();
			
			posDetailsYearOne = new ArrayList<PartyPositionsVO>();
			posDetailsYearTwo = new ArrayList<PartyPositionsVO>();
			
		    if(elecYearOne != null && elecYearTwo != null){
			comparedResultVO = new ComparedReportVO();
			Party party = partyDAO.get(partyId);
			
			if(hasAlliances){
				alliancPartiesForYearOne = staticDataService.getAllianceParties(yearOne,electionTypeId,partyId);
				alliancPartiesForYearTwo = staticDataService.getAllianceParties(yearTwo,electionTypeId,partyId);
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
			
			posDetailsYearOne = getPartyPositionsForADistrict(electionType, elecYearOne.getElectionId(),stateOrDistrictId,partyIdsYearOne);
			posDetailsYearTwo = getPartyPositionsForADistrict(electionType, elecYearTwo.getElectionId(),stateOrDistrictId,partyIdsYearTwo);
			
			if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){				
				constiElectionsForYearOne = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(
						elecYearOne.getElectionId(), stateOrDistrictId, partyIdsYearOne);
				constiElectionsForYearTwo = nominationDAO.findConstituencyElectionByElectionIdAndDistrictIdAndPartys(
						elecYearTwo.getElectionId(), stateOrDistrictId, partyIdsYearTwo);
			}else if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
				constiElectionsForYearOne = nominationDAO.findConstituencyElectionByElectionIdAndStateAndPartys(
						elecYearOne.getElectionId(), stateOrDistrictId, partyIdsYearOne);
				constiElectionsForYearTwo = nominationDAO.findConstituencyElectionByElectionIdAndStateAndPartys(
						elecYearTwo.getElectionId(), stateOrDistrictId, partyIdsYearTwo);
			}
			
			
			log.debug("Total Consti In " + yearOne + " :" + constiElectionsForYearOne.size());
			log.debug("Total Consti In " + yearTwo + " :" + constiElectionsForYearTwo.size());
			
			if(constiElectionsForYearOne != null && constiElectionsForYearTwo != null)
			comparedResultVO = 	getDetailedComparedResults(yearOne,constiElectionsForYearOne,yearTwo,constiElectionsForYearTwo,
					partyId,partyIdsYearOne,partyIdsYearTwo,hasAlliances);
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
	
	public List<PartyPositionsVO> getPartyPositionsForADistrict(String electionType, Long electionId,Long stateOrDistrictId,List<Long> partyIds){
		List<PartyPositionsVO> partyPositionsList = null;
		
		PartyElectionDistrictResult partyElectionResultParty = null;
		PartyElectionStateResult partyElectionStateResult = null;
				
		if(electionId != null && stateOrDistrictId != null){
			partyPositionsList = new ArrayList<PartyPositionsVO>();
			if(IConstants.ASSEMBLY_ELECTION_TYPE.equalsIgnoreCase(electionType)){				
				for(Long partyId:partyIds){				
					partyElectionResultParty = staticDataService.getPartyElectionResultsForAPartyDistrictLevel(electionId, partyId, stateOrDistrictId);
				    if(partyElectionResultParty == null)
				    partyElectionResultParty = staticDataService.savePartyElectionResultForAPartyForAElectionDistrictLevel(electionId, partyId, stateOrDistrictId);
				    if(partyElectionResultParty != null){
					    PartyPositionsVO partyPositions = getPartyPositionsForADistrict(partyElectionResultParty);
					    partyPositionsList.add(partyPositions);
				    }
			     }
			}else if(IConstants.PARLIAMENT_ELECTION_TYPE.equalsIgnoreCase(electionType)){
				for(Long partyId:partyIds){				
					partyElectionStateResult = staticDataService.getPartyElectionResultsForAPartyStateLevelInParliamentElection(electionId, partyId, stateOrDistrictId);
				    if(partyElectionStateResult == null)
				    	partyElectionStateResult = staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(electionId, partyId, stateOrDistrictId);
				    if(partyElectionStateResult != null){
					    PartyPositionsVO partyPositions = getPartyPositionsForAState(partyElectionStateResult);
					    partyPositionsList.add(partyPositions);
				    }
			     }
			}
			
		}
    	
		return partyPositionsList;
	}
		
	private PartyPositionsVO getPartyPositionsForAState(PartyElectionStateResult partyElectionStateResult) {
		PartyPositionsVO partyPositions = new PartyPositionsVO();
		partyPositions.setTotalSeatsWon(new Long(partyElectionStateResult.getTotalSeatsWon()));
    	partyPositions.setSecondPosWon(new Long(partyElectionStateResult.getSecondPosWon()));
    	partyPositions.setThirdPosWon(new Long(partyElectionStateResult.getThirdPosWon()));
    	partyPositions.setFourthPosWon(new Long(partyElectionStateResult.getFourthPosWon()));
    	partyPositions.setNthPosWon(new Long(partyElectionStateResult.getNthPosWon()));
    	partyPositions.setTotalConstiParticipated(new Long(partyElectionStateResult.getTotalConstiParticipated()));
    	partyPositions.setPartyId(partyElectionStateResult.getParty().getPartyId());
    	partyPositions.setPartyName(partyElectionStateResult.getParty().getShortName());
    	partyPositions.setVotesPercentage(partyElectionStateResult.getVotesPercentage());
    	partyPositions.setCompleteVotesPercent(partyElectionStateResult.getCompleteVotesPercent());
    	
		return partyPositions;
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
	public ComparedReportVO getDetailedComparedResults(String yearOne,List<ConstituencyElection> constiElectionsForYearOne,
			String yearTwo,List<ConstituencyElection> constiElectionsForYearTwo,Long partyId,
			List<Long> alliancPartyIdsYearOne,List<Long> alliancPartyIdsYearTwo,Boolean hasAlliances){
		
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
						
						if(comparedConstiElecMap.isEmpty() || !comparedConstiElecMap.containsKey(constiElecOne.getConstituency().
								getConstituencyId()))
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
				
			notConsideredYearOneResults = getNotconsideredConstituencies(constiElectionsForYearOne,yearOne,constituencyIds,
					partyId,alliancPartyIdsYearOne,hasAlliances);
			notConsideredYearTwoResults = getNotconsideredConstituencies(constiElectionsForYearTwo,yearTwo,constituencyIds,
					partyId,alliancPartyIdsYearTwo,hasAlliances);
			
			comparedReportVO.setVotesPercentGainedResults(comparedReportNewVO.getVotesPercentGainedResults());
			comparedReportVO.setVotesPercentLostResults(comparedReportNewVO.getVotesPercentLostResults());
			comparedReportVO.setNotConsideredYearOneResults(notConsideredYearOneResults);
			comparedReportVO.setNotConsideredYearTwoResults(notConsideredYearTwoResults);
			comparedReportVO.setYearOne(yearOne);
			comparedReportVO.setYearTwo(yearTwo);
		}
		
		return comparedReportVO;
	}
	
	public ComparedReportVO getResultsForVotesGainedOrLost(List<ComparedConstituencyElectionVO> compConstiElecList ,
			String elecYearOne,String elecYearTwo,Long partyId,List<Long> alliancPartyIdsYearOne,
			List<Long> alliancPartyIdsYearTwo,Boolean hasAlliances){
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
				    selectedNominationYearOne = getSelectedNominationWithAllianc(nominationsForYearOne,partyId,
				    		alliancPartyIdsYearOne,hasAlliances);
					selectedNominationYearTwo = getSelectedNominationWithAllianc(nominationsForYearTwo,partyId,
							alliancPartyIdsYearTwo,hasAlliances);
				}	
			    if(selectedNominationYearOne != null && selectedNominationYearTwo != null){
					Double votesEarnedYearOne = selectedNominationYearOne.getCandidateResult().getVotesEarned();
					Double votesEarnedYearTwo = selectedNominationYearTwo.getCandidateResult().getVotesEarned();
					Double validVotesYearOne = selectedNominationYearOne.getConstituencyElection()
						.getConstituencyElectionResult().getValidVotes();
					Double validVotesYearTwo = selectedNominationYearTwo.getConstituencyElection()
						.getConstituencyElectionResult().getValidVotes();
					
					Double votesPercentYearOne = new BigDecimal((votesEarnedYearOne/validVotesYearOne)*100).setScale(
							2,BigDecimal.ROUND_HALF_UP).doubleValue();
					Double votesPercentYearTwo = new BigDecimal((votesEarnedYearTwo/validVotesYearTwo)*100).setScale(
							2,BigDecimal.ROUND_HALF_UP).doubleValue();
					Double votesPercentDiff = new BigDecimal(votesPercentYearOne - votesPercentYearTwo).setScale(
							2,BigDecimal.ROUND_HALF_UP).doubleValue();

					log.debug("Votes% One" + votesPercentYearOne);
					log.debug("Votes% Two" + votesPercentYearTwo);
					
					if(votesPercentDiff > 0){
						ComparedElectionResultVO votesPercentGainedResults = getResultDetailsIntoVO(selectedNominationYearOne,
								selectedNominationYearTwo,votesPercentYearOne,votesPercentYearTwo,votesPercentDiff);
						votesPercentGainedResultsList.add(votesPercentGainedResults);
					}
					else if(votesPercentDiff <= 0){
						ComparedElectionResultVO votesPercentLostResults = 	getResultDetailsIntoVO(selectedNominationYearOne,
								selectedNominationYearTwo,votesPercentYearOne,votesPercentYearTwo,votesPercentDiff);
						votesPercentLostResultsList.add(votesPercentLostResults);
					}
			    }
			}
			comparedReportVO.setVotesPercentGainedResults(votesPercentGainedResultsList);
			comparedReportVO.setVotesPercentLostResults(votesPercentLostResultsList);
		}
		
		return comparedReportVO;
	}
	
	public ComparedElectionResultVO getResultDetailsIntoVO(Nomination selectedNominationYearOne,
			Nomination selectedNominationYearTwo,Double votesPercentYearOne,Double votesPercentYearTwo,Double votesPercentDiff){
		ComparedElectionResultVO votesPercentResults = new ComparedElectionResultVO();
		
		log.debug("Inside getResultDetailsIntoVO Method .......");
		
		votesPercentResults.setCandName(selectedNominationYearOne.getCandidate().getLastname());
		votesPercentResults.setConstiName(selectedNominationYearOne.getConstituencyElection().getConstituency().getName());
		votesPercentResults.setConstituencyId(selectedNominationYearOne.getConstituencyElection().getConstituency().getConstituencyId());
		votesPercentResults.setElectionType(selectedNominationYearOne.getConstituencyElection().getConstituency().
				getElectionScope().getElectionType().getElectionType());
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
	
	public Nomination getSelectedNominationWithAllianc(Set<Nomination> nominations,Long partyId,List<Long> alliancPartyIds,
			Boolean hasAlliances){
		Nomination nomination = null;

		for(Nomination nominatn:nominations){
			if(nominatn.getParty().getPartyId().equals(partyId))
				return nominatn;
			
			if(alliancPartyIds.contains(nominatn.getParty().getPartyId())){
				System.out.println(nomination+"::"+nominatn.getConstituencyElection().getConstituency().getName());
				if(nomination == null){
					nomination = nominatn;	
				}
				else 
				if(nomination != null && nomination.getCandidateResult().getRank() > nominatn.getCandidateResult().getRank()){
					nomination = nominatn;	
				}
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
	
	public void getAllAllianceCandidatesForAPartyInAState(Long electionId,String allianceParties,String selectedParty,Long stateId){
			List<ConstituencyElection> allianceConstituencies = nominationDAO.getAllAllianceConstituenciesForAPartyInAnElection(electionId,allianceParties,selectedParty);		
			Double totalVotesEarned=0d;
			Double totalValidVotes=0d;
			Long firstPosition=0l;
			Long secondPosition=0l;
			Long thirdPosition=0l;
			Long fourthPosition=0l;
			Long nthPosition=0l;
			Long totalConstituenciesParticipated=0l;
			PartyElectionStateResult partyElectionStateResult = null;
			List<ElectionAlliance> alliances = electionAllianceDAO.getAllianceElectionByElectionIdAndStateId(electionId,stateId);
			partyElectionStateResult = staticDataService.getPartyElectionResultsForAPartyStateLevelInParliamentElection(electionId, new Long(selectedParty), stateId);
			if(partyElectionStateResult==null){
				partyElectionStateResult = staticDataService.savePartyElectionResultForAPartyForAParliamentElectionStateLevel(electionId,new Long(selectedParty),stateId);
			}
			
			for(ConstituencyElection consti : allianceConstituencies){			
				Set<Nomination> list = consti.getNominations();				
				
				Nomination highestRankParty =  getNominationForAlliancePartyThatGotHighestRank(list,allianceParties);
				
				totalConstituenciesParticipated++;
				
				totalVotesEarned+=highestRankParty.getCandidateResult().getVotesEarned();
				totalValidVotes+=consti.getConstituencyElectionResult().getValidVotes();
				
				
				switch(highestRankParty.getCandidateResult().getRank().intValue()){
						 
						case 1 		: 	firstPosition++;
						  		 		break;
						  		 	
						case 2 		: 	secondPosition++;
			  		 					break;
			  		 				
						case 3 		: 	thirdPosition++;
										break;
		
						case 4 		: 	fourthPosition++;
			  		 					break;
			  		 					
			  		 	default 	:	nthPosition++;
			  		 					break;
				}			
			}
			firstPosition+=new Long(partyElectionStateResult.getTotalSeatsWon());
			secondPosition+=new Long(partyElectionStateResult.getSecondPosWon());
			thirdPosition+=new Long(partyElectionStateResult.getThirdPosWon());
			fourthPosition+=new Long(partyElectionStateResult.getFourthPosWon());
			nthPosition+=new Long(partyElectionStateResult.getNthPosWon());
			totalConstituenciesParticipated+=new Long(partyElectionStateResult.getTotalConstiParticipated());
			totalVotesEarned+=partyElectionStateResult.getTotalVotesGained();
			totalValidVotes+=partyElectionStateResult.getTotalValidVotes();
			
			savePartyElectionStateResult(electionDAO.get(electionId),partyDAO.get(new Long(selectedParty)),
					stateDAO.get(stateId),firstPosition,secondPosition,thirdPosition,fourthPosition,nthPosition,
					totalConstituenciesParticipated,(totalVotesEarned/totalValidVotes),null,totalVotesEarned,totalValidVotes,null,alliances.get(0).getGroup());
			//Election election
			//Party party
			//State state
			//Long totalSeatsWon
			//Long secPos
			//Long thirdPos
			//Long fourthPos
			//Long nthPos
			//Long totConstiParticipated
			
			//Double totalVotesPercentage
			
			//Double completeVotesPercent
			
			//Double totalVotesGained
			//Double totalValidVotes			
			//Double completeConstiValidVotes
			
			//Group group
	}	
	

	public Nomination getNominationForAlliancePartyThatGotHighestRank(Set<Nomination> list,String allianceParties){
		List<Nomination> selectedList = new ArrayList<Nomination>(0);
		Long rank=0l;
		for(Nomination nomination : list){				
			if(nomination.getParty().getShortName().contains(allianceParties)){
				selectedList.add(nomination);	
			}
		}		
		Collections.sort(selectedList, new NominatResultsComparator());
		
		return selectedList.get(0);		
	}
public PartyElectionStateResultWithAlliance savePartyElectionStateResult(final Election election,final Party party,final State state,final Long totalSeatsWon,final Long secPos,final Long thirdPos,final Long fourthPos,final Long nthPos,final Long totConstiParticipated,final Double totalVotesPercentage,final Double completeVotesPercent,final Double totalVotesGained,final Double totalValidVotes,final Double completeConstiValidVotes,final Group group){
		
	PartyElectionStateResultWithAlliance partyResultWithAlliance = (PartyElectionStateResultWithAlliance)transactionTemplate.execute(new TransactionCallback() {

			public Object doInTransaction(TransactionStatus status) {
				PartyElectionStateResultWithAlliance partyElectionStateResult = null;
				try{
					java.util.Date updatedDate = new java.util.Date();
					String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String strDateNew = sdf.format(updatedDate) ;
					updatedDate = sdf.parse(strDateNew);
					
					partyElectionStateResult = new PartyElectionStateResultWithAlliance();
					partyElectionStateResult.setParty(party);
					partyElectionStateResult.setElection(election);
					partyElectionStateResult.setState(state);					
					partyElectionStateResult.setGroup(group);					
					partyElectionStateResult.setTotalSeatsWon(totalSeatsWon);
					partyElectionStateResult.setSecondPosWon(secPos);
					partyElectionStateResult.setThirdPosWon(thirdPos);
					partyElectionStateResult.setFourthPosWon(fourthPos);
					partyElectionStateResult.setNthPosWon(nthPos);
					partyElectionStateResult.setTotalVotesGained(totalVotesGained);
					partyElectionStateResult.setTotalValidVotes(totalValidVotes);
					partyElectionStateResult.setCompleteConstiValidVotes(completeConstiValidVotes);
					partyElectionStateResult.setCompleteVotesPercent(completeVotesPercent.toString());
					partyElectionStateResult.setVotesPercentage(totalVotesPercentage.toString());
					partyElectionStateResult.setTotalConstiParticipated(totConstiParticipated);
					partyElectionStateResult.setLastUpdated(updatedDate);
					
					partyElectionStateResult = partyElectionStateResultWithAllianceDAO.save(partyElectionStateResult);
					
				}catch(Exception ex){
					ex.printStackTrace();
		        	log.debug("Exception Raised : " + ex);
		        	status.setRollbackOnly();
				}
			 return partyElectionStateResult;
			}
			
		});
	  return partyResultWithAlliance;
	}

}
