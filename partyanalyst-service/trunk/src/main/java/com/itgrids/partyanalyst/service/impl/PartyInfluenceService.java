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
import java.util.Collections;
import java.util.List;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResults;
import com.itgrids.partyanalyst.dto.ConstituencyElectionsDetailedResultVO;
import com.itgrids.partyanalyst.dto.DistrictWiseConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyInfluenceReportVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.IPartyInfluenceService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.ConstituencyElectionVotesComparator;
import com.itgrids.partyanalyst.utils.VotesPercentageComparator;


public class PartyInfluenceService implements IPartyInfluenceService {
	
	private IElectionDAO electionDAO;
	private INominationDAO nominationDAO;
	private IElectionScopeDAO electionScopeDAO;
	private IStaticDataService staticDataService;
	
	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	
	
	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}

	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
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
	public PartyInfluenceReportVO getPartyInfluenceReportResults(Long electionTypeId,Long partyId,Long newPartyId,String electionYear,Boolean includeAlliance,Long stateId){
		
		PartyInfluenceReportVO partyInfluenceReportVO = null;
		List<ConstituencyElectionResults> constituencyElectionResultForYearOne = null;
		List<ConstituencyElectionResults> constituencyElectionResultForYearTwo = null;
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO = null;
		List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO = null;
		String previousElectionYear = "";
		
		previousElectionYear = electionDAO.findPreviousElectionYear(electionYear, electionTypeId, stateId, new Long(1));
		//List<ElectionScope> electionScope = electionScopeDAO.findByPropertyElectionTypeIdandStateId(electionTypeId, stateId);
		
		if(previousElectionYear == null)
			return null;
		System.out.println("prev year -->" + previousElectionYear);
		Election election1 = getElection(new Long(2),electionYear);
		Election election2 = getElection(new Long(2),previousElectionYear);
		
		
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
			
		if(constituencyElectionResultForYearOne != null && constituencyElectionResultForYearTwo != null)
		  constituencyElectionsDetailedResultVO = getDetailedConstituencyWiseElectionResultsForTwoYears(constituencyElectionResultForYearOne,constituencyElectionResultForYearTwo);
		
		if(constituencyElectionsDetailedResultVO.size() > 0){
			districtWiseConstituencyElectionResultsVO = new ArrayList<DistrictWiseConstituencyElectionResultsVO>();
			districtWiseConstituencyElectionResultsVO = getDistrictWiseResults(constituencyElectionsDetailedResultVO);
		    if(districtWiseConstituencyElectionResultsVO.size() > 0){
		    	
		    	partyInfluenceReportVO = new PartyInfluenceReportVO();
		    	partyInfluenceReportVO = getPartyInfluenceReportFinalResults(districtWiseConstituencyElectionResultsVO,partyId,newPartyId);
		    	
		    return partyInfluenceReportVO;
		    }
		}

	return null;
	}
	
	public PartyInfluenceReportVO getPartyInfluenceReportFinalResults(List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO,Long partyId,Long newPartyId){
		
		PartyInfluenceReportVO partyInfluenceReportVO = new PartyInfluenceReportVO();
		int constituencies = 0;
		Double totalVotesEarnedForPartyInDistrictsForYearOne = new Double(0);
		Double totalValidVotesForPartyInDistrictsForYearOne = new Double(0);
		Double totalVotesEarnedForNewPartyInDistrictsForYearOne = new Double(0);
		Double totalValidVotesForNewPartyInDistrictsForYearOne = new Double(0);
		Double totalVotesEarnedForPartyInDistrictsForYearTwo = new Double(0);
		Double totalValidVotesForPartyInDistrictsForYearTwo = new Double(0);
		
		Double percentageOfVotesForPartyInDistrictsForYearOne = new Double(0);
		Double percentageOfVotesForNewPartyInDistrictsForYearOne = new Double(0);
		Double percentageOfVotesForPartyInDistrictsForYearTwo = new Double(0);
		Double percentageDiffForParty = new Double(0);
		
		for(DistrictWiseConstituencyElectionResultsVO resultVO:districtWiseConstituencyElectionResultsVO){
			
			if(resultVO.getPartyId().equals(partyId))
			 partyInfluenceReportVO.setImpactedPartyName(resultVO.getPartyName());
			if(resultVO.getNewPartyId().equals(newPartyId))
			 partyInfluenceReportVO.setNewPartyName(resultVO.getNewPartyName());
			partyInfluenceReportVO.setYear(resultVO.getYear());
			partyInfluenceReportVO.setPreviousYear(resultVO.getPreviousYear());
			partyInfluenceReportVO.setTotalDistrictsConsidered(districtWiseConstituencyElectionResultsVO.size());
			
			totalVotesEarnedForPartyInDistrictsForYearOne += resultVO.getTotalVotesEarnedInDistrictForPartyInYear1();
			totalValidVotesForPartyInDistrictsForYearOne += resultVO.getTotalValidVotesInDistrictForPartyInYear1();
			totalVotesEarnedForNewPartyInDistrictsForYearOne += resultVO.getTotalVotesEarnedInDistrictForNewPartyInYear1();
			totalValidVotesForNewPartyInDistrictsForYearOne += resultVO.getTotalValidVotesInDistrictForNewPartyInYear1();
			totalVotesEarnedForPartyInDistrictsForYearTwo += resultVO.getTotalVotesEarnedInDistrictForPartyInYear2();
			totalValidVotesForPartyInDistrictsForYearTwo += resultVO.getTotalValidVotesInDistrictForPartyInYear2();
			
			constituencies += resultVO.getConstituencyElectionsDetailedResults().size();
		}
		
		    percentageOfVotesForPartyInDistrictsForYearOne = getPercentageOfVotes(totalVotesEarnedForPartyInDistrictsForYearOne,totalValidVotesForPartyInDistrictsForYearOne);
		    percentageOfVotesForNewPartyInDistrictsForYearOne = getPercentageOfVotes(totalVotesEarnedForNewPartyInDistrictsForYearOne,totalValidVotesForNewPartyInDistrictsForYearOne);
		    percentageOfVotesForPartyInDistrictsForYearTwo = getPercentageOfVotes(totalVotesEarnedForPartyInDistrictsForYearTwo,totalValidVotesForPartyInDistrictsForYearTwo);
		    
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentForPartyForYearOne(getRoundedDoubleValue(percentageOfVotesForPartyInDistrictsForYearOne).toString());
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentForNewPartyForYearOne(getRoundedDoubleValue(percentageOfVotesForNewPartyInDistrictsForYearOne).toString());
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentForPartyForYearTwo(getRoundedDoubleValue(percentageOfVotesForPartyInDistrictsForYearTwo).toString());
		    
		    percentageDiffForParty = percentageOfVotesForPartyInDistrictsForYearOne - percentageOfVotesForPartyInDistrictsForYearTwo;
		    partyInfluenceReportVO.setTotalDistrictsVotesPercentDiffForParty(getRoundedDoubleValue(percentageDiffForParty).toString());
		    
		    partyInfluenceReportVO.setTotalConstituenciesConsidered(constituencies);
		    partyInfluenceReportVO.setDistrictWiseConstituencyElectionResultsVO(districtWiseConstituencyElectionResultsVO);
	
	return partyInfluenceReportVO;
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
					constituencyElectionResultForParty.setYear(candidateResultOne.getNomination().getConstituencyElection().getElection().getElectionYear());
					constituencyElectionResultForParty.setValidVotes(nominationsForParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes().toString());
					constituencyElectionResultForParty.setPartyId(partyOne.getPartyId());
					constituencyElectionResultForParty.setPartyName(partyOne.getShortName());
					
					constituencyElectionResultForNewParty.setCandidateId(candidateTwo.getCandidateId());
					constituencyElectionResultForNewParty.setCandidateName(candidateTwo.getLastname());
					constituencyElectionResultForNewParty.setConstituencyId(constituencyTwo.getConstituencyId());
					constituencyElectionResultForNewParty.setConstituencyName(constituencyTwo.getName());
					constituencyElectionResultForNewParty.setDistrictId(constituencyTwo.getDistrict().getDistrictId());
					constituencyElectionResultForNewParty.setDistrictName(constituencyTwo.getDistrict().getDistrictName());
					constituencyElectionResultForNewParty.setPartyName(partyTwo.getShortName());
					constituencyElectionResultForNewParty.setVotesEarned(candidateResultTwo.getVotesEarned().toString());
					constituencyElectionResultForNewParty.setPercentageOfVotes(candidateResultTwo.getVotesPercengate());
					constituencyElectionResultForNewParty.setValidVotes(nominationsForNewParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes().toString());
					constituencyElectionResultForNewParty.setPartyId(partyTwo.getPartyId());
					constituencyElectionResultForNewParty.setPartyName(partyTwo.getShortName());
					
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
			constituencyElectionResultForParty.setPartyId(partyOne.getPartyId());
			constituencyElectionResultForParty.setPartyName(partyOne.getShortName());
			constituencyElectionResultForParty.setVotesEarned(candidateResultOne.getVotesEarned().toString());
			constituencyElectionResultForParty.setPercentageOfVotes(candidateResultOne.getVotesPercengate());
			constituencyElectionResultForParty.setYear(candidateResultOne.getNomination().getConstituencyElection().getElection().getElectionYear());
			constituencyElectionResultForParty.setValidVotes(nominationsForParty.getConstituencyElection().getConstituencyElectionResult().getValidVotes().toString());
			
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
					constituencyElectionsDetailedResult.setVotesPercntDiff(difference);
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
		if(districtWiseConstituencyElectionResultsVO != null && districtWiseConstituencyElectionResultsVO.size() > 0){
			Collections.sort(districtWiseConstituencyElectionResultsVO, new VotesPercentageComparator());
			return districtWiseConstituencyElectionResultsVO;
		}
			
	
	return null;
	}
	
	public DistrictWiseConstituencyElectionResultsVO getConstituencyResultsForADistrict(List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResultVO,Long districtId){
		
		DistrictWiseConstituencyElectionResultsVO districtWiseConstituencyElectionResults = new DistrictWiseConstituencyElectionResultsVO();
		List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResults = new ArrayList<ConstituencyElectionsDetailedResultVO>();
		int count = 0;
		String districtName="";
		Long partyId = new Long(0);
		Long newPartyId = new Long(0);
		String partyName = "";
		String newPartyName = "";
		Double percentSumForPartyForYearOne = new Double(0); 
		Double percentSumForNewPartyForYearOne = new Double(0);
		Double percentSumForPartyForYearTwo = new Double(0); 
		Double votesPercentageDiffDouble = new Double(0);
		String year = "";
		String prevYear = "";
		Double totalVotesEarnedForPartyForYearOne =new Double(0);
		Double totalVotesEarnedForNewPartyForYearOne =new Double(0);
		Double totalVotesEarnedForPartyForYearTwo =new Double(0);
		Double totalValidVotesForPartyForYearOne = new Double(0);
		Double totalValidVotesForNewPartyForYearOne = new Double(0);
		Double totalValidVotesForPartyForYearTwo = new Double(0);
				
		for(ConstituencyElectionsDetailedResultVO constituencyElectionsDetailedResult:constituencyElectionsDetailedResultVO){
			if(constituencyElectionsDetailedResult.getDistrictId().equals(districtId)){
				partyId = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getPartyId();
				newPartyId = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getPartyId();
				
				partyName = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getPartyName();
				newPartyName = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getPartyName();
				
				Double votesEarnedForPartyforYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getVotesEarned());
				totalVotesEarnedForPartyForYearOne = totalVotesEarnedForPartyForYearOne + votesEarnedForPartyforYearOne;
				
				Double votesEarnedForNewPartyforYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getVotesEarned());
				totalVotesEarnedForNewPartyForYearOne = totalVotesEarnedForNewPartyForYearOne + votesEarnedForNewPartyforYearOne;
				
				Double votesEarnedForPartyforYearTwo = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearTwo().getElectionResultForParty().getVotesEarned());
				totalVotesEarnedForPartyForYearTwo = totalVotesEarnedForPartyForYearTwo + votesEarnedForPartyforYearTwo;
				
				Double validVotesForPartyForYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getValidVotes());
				totalValidVotesForPartyForYearOne = totalValidVotesForPartyForYearOne + validVotesForPartyForYearOne;
				
				Double validVotesForNewPartyForYearOne = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForNewParty().getValidVotes());
				totalValidVotesForNewPartyForYearOne  =  totalValidVotesForNewPartyForYearOne + validVotesForNewPartyForYearOne;
				
				Double validVotesForPartyForYearTwo = Double.valueOf(constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearTwo().getElectionResultForParty().getValidVotes());
				totalValidVotesForPartyForYearTwo = totalValidVotesForPartyForYearTwo + validVotesForPartyForYearTwo;
				
				year = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getYear();
				prevYear = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearTwo().getElectionResultForParty().getYear();
				
				constituencyElectionsDetailedResults.add(constituencyElectionsDetailedResult);
				districtName = constituencyElectionsDetailedResult.getConstituencyElectionResultsForYearOne().getElectionResultForParty().getDistrictName();
				count++;
			}
		}
		if(count > 0 && constituencyElectionsDetailedResults.size() > 0){
			districtWiseConstituencyElectionResults.setDistrictId(districtId);
			districtWiseConstituencyElectionResults.setDistrictName(districtName);
			
			percentSumForPartyForYearOne = getPercentageOfVotes(totalVotesEarnedForPartyForYearOne,totalValidVotesForPartyForYearOne);
			percentSumForNewPartyForYearOne = getPercentageOfVotes(totalVotesEarnedForNewPartyForYearOne,totalValidVotesForNewPartyForYearOne);
			percentSumForPartyForYearTwo = getPercentageOfVotes(totalVotesEarnedForPartyForYearTwo,totalValidVotesForPartyForYearTwo);
			votesPercentageDiffDouble = percentSumForPartyForYearOne - percentSumForPartyForYearTwo;
		
			Double percentDiff = getRoundedDoubleValue(votesPercentageDiffDouble);
			Collections.sort(constituencyElectionsDetailedResults, new ConstituencyElectionVotesComparator());
			districtWiseConstituencyElectionResults.setConstituencyElectionsDetailedResults(constituencyElectionsDetailedResults);
			districtWiseConstituencyElectionResults.setDistrictVotesPercentageDiff(percentDiff.toString());
			districtWiseConstituencyElectionResults.setDistrictWiseVotesPercntDiff(percentDiff);
			districtWiseConstituencyElectionResults.setPartyVotesPercentage(getRoundedDoubleValue(percentSumForPartyForYearOne).toString());
			districtWiseConstituencyElectionResults.setNewPartyVotesPercentage(getRoundedDoubleValue(percentSumForNewPartyForYearOne).toString());
			districtWiseConstituencyElectionResults.setPartyVotesPercentForYear2(getRoundedDoubleValue(percentSumForPartyForYearTwo).toString());
			districtWiseConstituencyElectionResults.setYear(year);
			districtWiseConstituencyElectionResults.setPreviousYear(prevYear);
			districtWiseConstituencyElectionResults.setPartyId(partyId);
			districtWiseConstituencyElectionResults.setNewPartyId(newPartyId);
			districtWiseConstituencyElectionResults.setPartyName(partyName);
			districtWiseConstituencyElectionResults.setNewPartyName(newPartyName);
			districtWiseConstituencyElectionResults.setTotalVotesEarnedInDistrictForPartyInYear1(totalVotesEarnedForPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalValidVotesInDistrictForPartyInYear1(totalValidVotesForPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalVotesEarnedInDistrictForNewPartyInYear1(totalVotesEarnedForNewPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalValidVotesInDistrictForNewPartyInYear1(totalValidVotesForNewPartyForYearOne);
			districtWiseConstituencyElectionResults.setTotalVotesEarnedInDistrictForPartyInYear2(totalVotesEarnedForPartyForYearTwo);
			districtWiseConstituencyElectionResults.setTotalValidVotesInDistrictForPartyInYear2(totalValidVotesForPartyForYearTwo);
			
		return districtWiseConstituencyElectionResults;
		}
	return null;
	}
	
	//Returns a String value for a Double value with Rounded Decimal
	public Double getRoundedDoubleValue(Double value){
			 
		try{
			BigDecimal bd = new BigDecimal(value);
			BigDecimal bd1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			return bd1.doubleValue();
		}
		catch(Exception e){
			
		}
	return null;
	}
	
	//Returns percentage Of Votes
	public Double getPercentageOfVotes(Double totalvotesEarned,Double totalValidVotes){
		
		try{
			Double percentage = (totalvotesEarned/totalValidVotes)*100;
			return percentage;
		}
		catch(Exception e){
			
		}
	return null;
	}
}
