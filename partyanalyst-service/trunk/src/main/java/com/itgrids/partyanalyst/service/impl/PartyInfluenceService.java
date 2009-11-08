/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 3, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ConstituencyElectionsDetailedResultVO;
import com.itgrids.partyanalyst.dto.DistrictWiseConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.IPartyInfluenceService;
import com.itgrids.partyanalyst.service.IStaticDataService;


public class PartyInfluenceService implements IPartyInfluenceService {
	
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IStaticDataService staticDataService;
	
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

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	
	//Returns DistrictWiseConstituencyElectionResults VO
	public List<DistrictWiseConstituencyElectionResultsVO> getPartyInfluenceReportResults(Long electionTypeId,Long partyId,Long newPartyId,String electionYear,Boolean includeAlliance){
		
		
		List<ConstituencyElectionResults> constituencyElectionResultForYearOne = null;
		List<ConstituencyElectionResults> constituencyElectionResultForYearTwo = null;
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO = null;
		List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO = null;
		String previousElectionYear ="2004";
		Election election1 = getElection(new Long(2),electionYear);
		Election election2 = getElection(new Long(2),previousElectionYear);
	
		if(electionYear.equals("2009"))
		  previousElectionYear = "2004";
		else if(electionYear.equals("2004"))
			previousElectionYear = "1999";
		
		//year 1
		List<Nomination> nominationsForPartyForYearOne = getNominationsForAPartyForAnElection(electionTypeId,partyId,electionYear,election1.getElectionId(),includeAlliance);
		List<Nomination> nominationsForNewPartyForYearOne = getNominationsForAPartyForAnElection(electionTypeId,newPartyId,electionYear,election1.getElectionId(),includeAlliance);
		
		if(nominationsForPartyForYearOne != null && nominationsForNewPartyForYearOne != null){
			constituencyElectionResultForYearOne = getConstituencyElectionResults(nominationsForPartyForYearOne,nominationsForNewPartyForYearOne);
		}
		else if(nominationsForPartyForYearOne != null && nominationsForNewPartyForYearOne == null){
			constituencyElectionResultForYearOne = getConstituencyElectionResults(nominationsForPartyForYearOne);
		}
		else if(nominationsForPartyForYearOne == null && nominationsForNewPartyForYearOne != null){
			constituencyElectionResultForYearOne = getConstituencyElectionResults(nominationsForNewPartyForYearOne);
		}
		
		//year 2
		List<Nomination> nominationsForPartyForYearTwo = getNominationsForAPartyForAnElection(electionTypeId,partyId,previousElectionYear,election2.getElectionId(),includeAlliance);
		List<Nomination> nominationsForNewPartyForYearTwo = getNominationsForAPartyForAnElection(electionTypeId,newPartyId,previousElectionYear,election2.getElectionId(),includeAlliance);
	
		if(nominationsForPartyForYearTwo != null && nominationsForNewPartyForYearTwo != null){
			constituencyElectionResultForYearTwo = getConstituencyElectionResults(nominationsForPartyForYearTwo,nominationsForNewPartyForYearTwo);
		}
		else if(nominationsForPartyForYearTwo != null && nominationsForNewPartyForYearTwo == null){
			constituencyElectionResultForYearTwo = getConstituencyElectionResults(nominationsForPartyForYearTwo);
		}
		else if(nominationsForPartyForYearTwo == null && nominationsForNewPartyForYearTwo != null){
			constituencyElectionResultForYearTwo = getConstituencyElectionResults(nominationsForNewPartyForYearTwo);
		}
			
		constituencyElectionsDetailedResultVO = getDetailedConstituencyWiseElectionResultsForTwoYears(constituencyElectionResultForYearOne,constituencyElectionResultForYearTwo);
		
		if(constituencyElectionsDetailedResultVO.size() > 0){
			districtWiseConstituencyElectionResultsVO = new ArrayList<DistrictWiseConstituencyElectionResultsVO>();
			districtWiseConstituencyElectionResultsVO = getDistrictWiseResults(constituencyElectionsDetailedResultVO);
		    if(districtWiseConstituencyElectionResultsVO.size() > 0)
		    	return districtWiseConstituencyElectionResultsVO;
		}

	return null;
	}
	
	
	public List<Nomination> getNominationsForAPartyForAnElection(Long electionTypeId,Long partyId,String electionYear,Long electionId,Boolean includeAlliance){
		
        List<Long> partyIds = null;
		
		if(includeAlliance.equals(true)){
			partyIds = getHasAllianceParties(electionYear,electionTypeId,partyId);
		}
		else if(includeAlliance.equals(false)){
			partyIds = new ArrayList<Long>();
			partyIds.add(partyId);
		}
		
		List<Nomination> nominations = getNominations(electionId,partyIds);
		if(nominations != null && nominations.size() > 0)
			return nominations;
		
	return null;
	}
	
	//Returns List Of Nominations for An Election For A Single or Alliance Party's.
	public List<Nomination> getNominations(Long electionId,List<Long> partyIds){
		
		List<Nomination> nominations = null;
		if(electionId != null && partyIds != null && partyIds.size() > 0){
			nominations = nominationDAO.findByElectionIdAndPartys(electionId, partyIds);
		}
	return 	nominations;
	}
	
	//Returns Election Object for A Election Year And ElectionType
	public Election getElection(Long electionScopeId,String electionYear){
		
        Election election = electionDAO.findByElectionScopeIdElectionYear(electionScopeId, electionYear);
		
		if(election.getElectionId() != null)
		    return election;
		
	return null;
	}
	
	//Returns list Of Party Id's If a Party Has Alliance Party's
	public List<Long> getHasAllianceParties(String electionYear,Long electionType,Long partyId){
		
		List<SelectOptionVO> allianceParties = staticDataService.getAlliancePartiesAsVO(electionYear, electionType, partyId);
		List<Long> partyIds = null;
		
		if(allianceParties != null && allianceParties.size() > 0){
			partyIds = new ArrayList<Long>();
			
			for(SelectOptionVO partys:allianceParties){
				partyIds.add(partys.getId());
				System.out.println("PartyIds alliances -->"+ partys.getId());
			}
		}
		else{
			partyIds = new ArrayList<Long>();
			partyIds.add(partyId);	
		}
	return partyIds;
	}
	
	//Returns Two Parties  Participated ConstituencyElection Result VO's
	public List<ConstituencyElectionResults> getConstituencyElectionResults(List<Nomination> nominationsForPartyForYearOne,List<Nomination> nominationsForNewPartyForYearOne){
		
		List<ConstituencyElectionResults> constituencyElectionResults = new ArrayList<ConstituencyElectionResults>();
		
		for(Nomination nominationsForParty:nominationsForPartyForYearOne){
			Constituency constituencyOne = nominationsForParty.getConstituencyElection().getConstituency();
			Party partyOne = nominationsForParty.getParty();
			CandidateResult candidateResultOne = nominationsForParty.getCandidateResult();
			Candidate candidateOne = nominationsForParty.getCandidate();
			
			for(Nomination nominationsForNewParty:nominationsForNewPartyForYearOne){
				Constituency constituencyTwo =	nominationsForNewParty.getConstituencyElection().getConstituency();
				Party partyTwo = nominationsForNewParty.getParty();
				CandidateResult candidateResultTwo = nominationsForNewParty.getCandidateResult();
				Candidate candidateTwo = nominationsForNewParty.getCandidate();
				
				if(constituencyTwo.getConstituencyId().equals(constituencyOne.getConstituencyId())){
					ConstituencyElectionResultVO constituencyElectionResultForParty = new ConstituencyElectionResultVO();
					ConstituencyElectionResultVO constituencyElectionResultForNewParty = new ConstituencyElectionResultVO();
					ConstituencyElectionResults constituencyElectionResult = new ConstituencyElectionResults();
					
					constituencyElectionResultForParty.setCandidateId(candidateOne.getCandidateId());
					constituencyElectionResultForParty.setCandidateName(candidateOne.getLastname());
					constituencyElectionResultForParty.setConstituencyId(constituencyOne.getConstituencyId());
					constituencyElectionResultForParty.setConstituencyName(constituencyOne.getName());
					constituencyElectionResultForParty.setDistrictId(constituencyOne.getDistrict().getDistrictId());
					constituencyElectionResultForParty.setDistrictName(constituencyOne.getDistrict().getDistrictName());
					constituencyElectionResultForParty.setPartyName(partyOne.getShortName());
					constituencyElectionResultForParty.setVotesEarned(candidateResultOne.getVotesEarned().toString());
					constituencyElectionResultForParty.setPercentageOfVotes(candidateResultOne.getVotesPercengate());
					constituencyElectionResultForParty.setYear(candidateResultTwo.getNomination().getConstituencyElection().getElection().getElectionYear());
					
					constituencyElectionResultForNewParty.setCandidateId(candidateTwo.getCandidateId());
					constituencyElectionResultForNewParty.setCandidateName(candidateTwo.getLastname());
					constituencyElectionResultForNewParty.setConstituencyId(constituencyTwo.getConstituencyId());
					constituencyElectionResultForNewParty.setConstituencyName(constituencyTwo.getName());
					constituencyElectionResultForNewParty.setDistrictId(constituencyTwo.getDistrict().getDistrictId());
					constituencyElectionResultForNewParty.setDistrictName(constituencyTwo.getDistrict().getDistrictName());
					constituencyElectionResultForNewParty.setPartyName(partyTwo.getShortName());
					constituencyElectionResultForNewParty.setVotesEarned(candidateResultTwo.getVotesEarned().toString());
					constituencyElectionResultForNewParty.setPercentageOfVotes(candidateResultTwo.getVotesPercengate());
				
					constituencyElectionResult.setElectionResultForParty(constituencyElectionResultForParty);
					constituencyElectionResult.setElectionResultForNewParty(constituencyElectionResultForNewParty);
					constituencyElectionResults.add(constituencyElectionResult);
					break;	
				}
				
			}
		}
		
		if(constituencyElectionResults.size() > 0 && constituencyElectionResults != null)
			return constituencyElectionResults;
		
	return null;
	}
	
	public List<ConstituencyElectionResults> getConstituencyElectionResults(List<Nomination> nominationsForPartyForYearOne){
		
		List<ConstituencyElectionResults> constituencyElectionResults = new ArrayList<ConstituencyElectionResults>();
		ConstituencyElectionResultVO constituencyElectionResultForParty = null;
		ConstituencyElectionResults constituencyElectionResult = null;
		
		for(Nomination nominationsForParty:nominationsForPartyForYearOne){
			Constituency constituencyOne = nominationsForParty.getConstituencyElection().getConstituency();
			Party partyOne = nominationsForParty.getParty();
			CandidateResult candidateResultOne = nominationsForParty.getCandidateResult();
			Candidate candidateOne = nominationsForParty.getCandidate();
			
			constituencyElectionResultForParty = new ConstituencyElectionResultVO();
			constituencyElectionResult = new ConstituencyElectionResults();
			
			constituencyElectionResultForParty.setCandidateId(candidateOne.getCandidateId());
			constituencyElectionResultForParty.setCandidateName(candidateOne.getLastname());
			constituencyElectionResultForParty.setConstituencyId(constituencyOne.getConstituencyId());
			constituencyElectionResultForParty.setConstituencyName(constituencyOne.getName());
			constituencyElectionResultForParty.setDistrictId(constituencyOne.getDistrict().getDistrictId());
			constituencyElectionResultForParty.setDistrictName(constituencyOne.getDistrict().getDistrictName());
			constituencyElectionResultForParty.setPartyName(partyOne.getShortName());
			constituencyElectionResultForParty.setVotesEarned(candidateResultOne.getVotesEarned().toString());
			constituencyElectionResultForParty.setPercentageOfVotes(candidateResultOne.getVotesPercengate());
			constituencyElectionResultForParty.setYear(candidateResultOne.getNomination().getConstituencyElection().getElection().getElectionYear());
			
			constituencyElectionResult.setElectionResultForParty(constituencyElectionResultForParty);
			constituencyElectionResults.add(constituencyElectionResult);
		}
		if(constituencyElectionResults.size() > 0 && constituencyElectionResults != null)
			return constituencyElectionResults;
		
	return null;
	}
	
	public List<ConstituencyElectionsDetailedResultVO> getDetailedConstituencyWiseElectionResultsForTwoYears(List<ConstituencyElectionResults> constituencyElectionResultForYearOne,List<ConstituencyElectionResults> constituencyElectionResultForYearTwo){
		
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO = new ArrayList<ConstituencyElectionsDetailedResultVO>();
		
		for(ConstituencyElectionResults constituencyElectionResultsforYearOne:constituencyElectionResultForYearOne){
			ConstituencyElectionResultVO partyYearOne = constituencyElectionResultsforYearOne.getElectionResultForParty();
						
			for(ConstituencyElectionResults constituencyElectionResultsforYearTwo:constituencyElectionResultForYearTwo){
				ConstituencyElectionResultVO partyYearTwo = constituencyElectionResultsforYearTwo.getElectionResultForParty();
						
				if(partyYearTwo.getConstituencyId().equals(partyYearOne.getConstituencyId())){
					ConstituencyElectionsDetailedResultVO constituencyElectionsDetailedResult = new ConstituencyElectionsDetailedResultVO();
					constituencyElectionsDetailedResult.setConstituencyId(partyYearOne.getConstituencyId());
					constituencyElectionsDetailedResult.setConstituencyName(partyYearOne.getConstituencyName());
					constituencyElectionsDetailedResult.setDistrictId(partyYearOne.getDistrictId());
					Double value1 = Double.valueOf(partyYearOne.getPercentageOfVotes());
					Double value2 = Double.valueOf(partyYearTwo.getPercentageOfVotes());
					Double diff = value1 - value2;
					BigDecimal bd = new BigDecimal(diff);
					BigDecimal bd1 =bd.setScale(2, BigDecimal.ROUND_HALF_UP);
					Double difference = bd1.doubleValue();
					constituencyElectionsDetailedResult.setVotesPercentageDiff(difference.toString());
					constituencyElectionsDetailedResult.setConstituencyElectionResultsForYearOne(constituencyElectionResultsforYearOne);
					constituencyElectionsDetailedResult.setConstituencyElectionResultsForYearTwo(constituencyElectionResultsforYearTwo);
					
					constituencyElectionsDetailedResultVO.add(constituencyElectionsDetailedResult);
				break;
				}
			}
		}
		
		if(constituencyElectionsDetailedResultVO.size() > 0)
			return constituencyElectionsDetailedResultVO;
		
	return null;
	}
	
	public List<DistrictWiseConstituencyElectionResultsVO> getDistrictWiseResults(List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO){
		
		List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO = new ArrayList<DistrictWiseConstituencyElectionResultsVO>();
		DistrictWiseConstituencyElectionResultsVO districtWiseConstituencyElectionResults = null;
		List<Long> districtIds = new ArrayList<Long>();
		districtIds.add(new Long(0));
		int districtsCount = 0;
		
		for(ConstituencyElectionsDetailedResultVO constituencyElectionsDetailedResult:constituencyElectionsDetailedResultVO){
			if(districtIds.contains(constituencyElectionsDetailedResult.getDistrictId())){
				districtsCount++;
			}
			else{
				districtWiseConstituencyElectionResults = new DistrictWiseConstituencyElectionResultsVO();
				districtIds.add(constituencyElectionsDetailedResult.getDistrictId());
				districtWiseConstituencyElectionResults = getConstituencyResultsForADistrict(constituencyElectionsDetailedResultVO,constituencyElectionsDetailedResult.getDistrictId());
				
				districtWiseConstituencyElectionResultsVO.add(districtWiseConstituencyElectionResults);
			}
		}
		if(districtWiseConstituencyElectionResultsVO != null && districtWiseConstituencyElectionResultsVO.size() > 0)
			return districtWiseConstituencyElectionResultsVO;
	
	return null;
	}
	
	public DistrictWiseConstituencyElectionResultsVO getConstituencyResultsForADistrict(List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO,Long districtId){
		
		DistrictWiseConstituencyElectionResultsVO districtWiseConstituencyElectionResults = new DistrictWiseConstituencyElectionResultsVO();
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResults = new ArrayList<ConstituencyElectionsDetailedResultVO>();
		int count = 0;
		String districtName="";
		for(ConstituencyElectionsDetailedResultVO constituencyElectionsDetailedResult:constituencyElectionsDetailedResultVO){
			if(constituencyElectionsDetailedResult.getDistrictId().equals(districtId)){
				constituencyElectionsDetailedResults.add(constituencyElectionsDetailedResult);
				districtName = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getDistrictName();
				count++;
			}
		}
		if(count > 0 && constituencyElectionsDetailedResults.size() > 0){
			districtWiseConstituencyElectionResults.setDistrictId(districtId);
			districtWiseConstituencyElectionResults.setDistrictName(districtName);
			districtWiseConstituencyElectionResults.setConstituencyElectionsDetailedResults(constituencyElectionsDetailedResults);
			return districtWiseConstituencyElectionResults;
		}
	return null;
	}
}
