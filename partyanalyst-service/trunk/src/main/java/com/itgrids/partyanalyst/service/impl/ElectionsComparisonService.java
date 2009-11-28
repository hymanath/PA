/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 23, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.velocity.util.StringUtils;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonResultVO;
import com.itgrids.partyanalyst.dto.ElectionsComparisonVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultsPercentageVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElectionResult;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IElectionsComparisonService;
import common.Logger;

public class ElectionsComparisonService implements IElectionsComparisonService {

	
	private IStateDAO stateDAO;
	private IPartyDAO partyDAO;
	private IElectionDAO electionDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IElectionScopeDAO electionScopeDAO;
	
	private static Logger logger = Logger.getLogger(ElectionsComparisonService.class);
	

	public IElectionScopeDAO getElectionScopeDAO() {
		return electionScopeDAO;
	}


	public void setElectionScopeDAO(IElectionScopeDAO electionScopeDAO) {
		this.electionScopeDAO = electionScopeDAO;
	}


	public void setCandidateResultDAO(ICandidateResultDAO candidateResultDAO) {
		this.candidateResultDAO = candidateResultDAO;
	}
	
	
	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}
	

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}
	
	 public Long getElectionScopeId(Long electionTypeId,Long stateId){
		 
		 List<ElectionScope> electionScope = electionScopeDAO.findByPropertyElectionTypeIdandStateId(electionTypeId, stateId);
		 if(electionScope != null){
			 
		 return electionScope.get(0).getElectionScopeId(); 
		 }
		 
	 return null;
	 }

	public List<SelectOptionVO> getStatesList() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOption = null;
		List<State> states = null;
		 states = stateDAO.getAll();
		 if(states != null){
			 for(State state:states){
				 selectOption = new SelectOptionVO();
				 selectOption.setId(state.getStateId());
				 selectOption.setName(state.getStateName());
				 selectOptionList.add(selectOption);
			 }
		 return selectOptionList;
		 }
		 
	return null;
	}

	public List<SelectOptionVO> getPartiesList() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOption = null;
		List<Party> parties = null;
		 parties = partyDAO.getAll();
		 if(parties != null){
			 for(Party party:parties){
				 selectOption = new SelectOptionVO();
				 selectOption.setId(party.getPartyId());
				 selectOption.setName(party.getLongName());
				 selectOptionList.add(selectOption);
			 }
		 return selectOptionList;
		 }
	return null;
	}
	
	public List<SelectOptionVO> getYearsList(){
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		SelectOptionVO selectOption = null;
		Long i = new Long(1);
		List<String> years = new ArrayList<String>();
		years.add("");
		
		 List<Election> elections = electionDAO.getAll();
			 if(elections!=null){
				 for(Election election:elections){
					Boolean flag = false;
					if(years.contains(election.getElectionYear().trim()))
						flag = true;
					if(flag == false){
					 years.add(election.getElectionYear().trim());
					 selectOption = new SelectOptionVO();
					 selectOption.setId(i);
					 selectOption.setName(election.getElectionYear());
					 selectOptionList.add(selectOption);
					 i++;
					}
				 }
			 return selectOptionList;
			 }
		 
		 
	return null;
	}
	
	
	public boolean IsPartyParticipated(Long electionScopeId, Long partyId,String year) {
		
	    List<Long> status  = candidateResultDAO.findCandidateResultsCount(electionScopeId, partyId, year);
	    Long count = null;
	    if(status != null && status.size() >= 1){
	    	for(Long c:status){
	    		count = c;
	    	}
	    	if(count.equals(new Long(0)))
	    		return false;
	    }
	return true;
    }


    public List<ElectionComparisonResultVO> getPartyElectionResults(Long electionScopeId,Long partyId,String year){
	
	   List<ElectionComparisonResultVO> electionComparisonResultVO = new ArrayList<ElectionComparisonResultVO>();
	
	   List<CandidateResult> candidateResults = getCandidateElectionResults(electionScopeId, partyId, year);
	
	   if(candidateResults != null){
		   List<Long> districtIds = getDistrictIds(candidateResults);
		    for(Long districtId:districtIds){
		    	System.out.println(" inside -->" + districtId);
		     ElectionComparisonResultVO resultVO = getElectionResults(candidateResults,districtId);
		     electionComparisonResultVO.add(resultVO);
		    }
	        return electionComparisonResultVO;	   
	   }
	
   return null;	
   }
    
    public List<CandidateResult> getCandidateElectionResults(Long electionScopeId,Long partyId,String year){
    	
    	List<CandidateResult> candidateResults = candidateResultDAO.findCandidateResults(electionScopeId, partyId, year);
    	if(candidateResults != null)
    		return candidateResults;
    	else 
    		return null;
    }

   public List<Long> getDistrictIds(List<CandidateResult> candidateResults){
	
	List<Long> districtIds = new ArrayList<Long>();
	District district = null;
	int count=0;
	
	if(candidateResults!=null){
		
		    for(CandidateResult result:candidateResults){
			  district = result.getNomination().getConstituencyElection().getConstituency().getDistrict();
			  if(districtIds.contains(district.getDistrictId()))
			   count++;
			  if(count == 0)
			   districtIds.add(district.getDistrictId());
		      count = 0;
		    }
	        return districtIds;
	}
	  
   return null;
   }

   public ElectionComparisonResultVO getElectionResults(List<CandidateResult> candidateResults,Long districtId){
	
	ElectionComparisonResultVO electionComparisonResultVO = new ElectionComparisonResultVO();
	List<PartyElectionResultsVO> partyElectionResultsVO = new ArrayList<PartyElectionResultsVO>();
	List<CandidateOppositionVO> candidateOppositionVO = new ArrayList<CandidateOppositionVO>();
	District district = null;
	Candidate candidate = null;
	Constituency constituency = null;
	Party party = null;
	
	if(candidateResults != null){
		
		 
		for(CandidateResult candidateResult:candidateResults){
			
			district = candidateResult.getNomination().getConstituencyElection().getConstituency().getDistrict();
					
			if(district.getDistrictId().equals(districtId)){
				
				electionComparisonResultVO.setDistrictId(district.getDistrictId());
				electionComparisonResultVO.setDistrictName(district.getDistrictName());
				electionComparisonResultVO.setStateId(district.getState().getStateId());
				candidate = candidateResult.getNomination().getCandidate();
				constituency = candidateResult.getNomination().getConstituencyElection().getConstituency();
				party = candidateResult.getNomination().getParty();
				
				PartyElectionResultsVO  partyResults = new PartyElectionResultsVO();
				partyResults.setCandidateId(candidate.getCandidateId());
				partyResults.setCandidateName(candidate.getLastname());
				partyResults.setConstituencyId(constituency.getConstituencyId());
				partyResults.setConstituencyName(constituency.getName());
				partyResults.setPartyId(party.getPartyId());
				partyResults.setPartyName(party.getLongName());
				partyResults.setRank(candidateResult.getRank());
				partyResults.setVotesEarned(candidateResult.getVotesEarned());
				partyResults.setVotesPercentage(candidateResult.getVotesPercengate());
			partyElectionResultsVO.add(partyResults);
			}
		}
		electionComparisonResultVO.setPartyElectionResultsVO(partyElectionResultsVO);
	return electionComparisonResultVO;
	}
   return null;
   }

   public ElectionsComparisonVO getPartyElectionComparedResults(Long electionScopeId,Long partyId,String firstYear,String secondYear){
	
	List<ElectionComparisonResultVO> resultForFirstYear = null;
	List<ElectionComparisonResultVO> resultForSecondYear = null;
	List<ElectionComparisonResultVO> resultsForVotesGained = new ArrayList<ElectionComparisonResultVO>();
	List<ElectionComparisonResultVO> resultsForVotesLost = new ArrayList<ElectionComparisonResultVO>();
	List<ElectionComparisonResultVO> constituenciesNotConsidered = new ArrayList<ElectionComparisonResultVO>();
	
	ElectionsComparisonVO electionsComparisionVO = new ElectionsComparisonVO();
	
	resultForFirstYear = getPartyElectionResults(electionScopeId,partyId,firstYear);
	resultForSecondYear = getPartyElectionResults(electionScopeId,partyId,secondYear);
	
	 if(resultForFirstYear != null && resultForSecondYear != null){
		 
		 int votesGainedCount = 0;
		 int votesLostCount = 0;
		 int count = 0;
		 String partyName = null;
		 electionsComparisionVO.setFirstYear(firstYear);
		 electionsComparisionVO.setSecondYear(secondYear);
		 
		 for(ElectionComparisonResultVO resultFirst:resultForFirstYear){
			 
			  for(ElectionComparisonResultVO resultSecond:resultForSecondYear){
				 if(resultSecond.getDistrictId().equals(resultFirst.getDistrictId())){
					 
					 List<PartyElectionResultsVO> partyElectionResultsForFirst = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsForSecond = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsNotConsidered = new ArrayList<PartyElectionResultsVO>();
					 int statusForGained = 0;
					 int statusForLost = 0;
					 int statusForNotConsidered = 0;
					 
					 for(PartyElectionResultsVO firstPartyResults:resultFirst.getPartyElectionResultsVO()){
						 Boolean flag = false;
						 for(PartyElectionResultsVO secondPartyResults:resultSecond.getPartyElectionResultsVO()){
							 if(firstPartyResults.getConstituencyId().equals(secondPartyResults.getConstituencyId()) && Double.parseDouble(firstPartyResults.getVotesPercentage()) < Double.parseDouble(secondPartyResults.getVotesPercentage())){
								 
							    PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
							    partyResultVO = getResult(firstPartyResults,secondPartyResults);
							    Double votesDiff = Double.parseDouble(partyResultVO.getVotesPercentageBySecond()) - Double.parseDouble(partyResultVO.getVotesPercentage());
							    partyResultVO.setVotesDiff(getRoundedDoubleValue(votesDiff).toString());
							    partyElectionResultsForFirst.add(partyResultVO);
							    flag = true;
							    statusForGained = 1;
							    votesGainedCount++;
							    break;
							 }
							 else if(firstPartyResults.getConstituencyId().equals(secondPartyResults.getConstituencyId()) && Double.parseDouble(firstPartyResults.getVotesPercentage()) >= Double.parseDouble(secondPartyResults.getVotesPercentage())){
								 
								 PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
								 partyResultVO = getResult(firstPartyResults,secondPartyResults);
								 Double votesDiff = Double.parseDouble(partyResultVO.getVotesPercentage()) - Double.parseDouble(partyResultVO.getVotesPercentageBySecond());
								 partyResultVO.setVotesDiff(getRoundedDoubleValue(votesDiff).toString());
								 partyElectionResultsForSecond.add(partyResultVO);
								 flag = true;
								 statusForLost = 1;
								 votesLostCount++;
								 break;
							 }
												 
						 }
						 if(flag == false){
							 PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
							 partyResultVO = getResults(firstPartyResults);
							 partyElectionResultsNotConsidered.add(partyResultVO);
							 flag = true;
							 statusForNotConsidered = 1;
							 count++;
						 }
						 partyName = firstPartyResults.getPartyName();
	    				 electionsComparisionVO.setPartyName(partyName);
					 }
					 
					 if(statusForGained == 1){
						 ElectionComparisonResultVO resultVO = new ElectionComparisonResultVO();
						 ElectionComparisonResultVO resultVOforSecond = new ElectionComparisonResultVO();
						 resultVO.setDistrictId(resultSecond.getDistrictId());
						 resultVO.setDistrictName(resultSecond.getDistrictName());
						 resultVO.setStateId(resultSecond.getStateId());
						 resultVOforSecond.setDistrictId(resultFirst.getDistrictId());
						 resultVOforSecond.setDistrictName(resultFirst.getDistrictName());
						 resultVOforSecond.setStateId(resultFirst.getStateId());
						 
						 resultVO.setPartyElectionResultsVO(partyElectionResultsForFirst);
						 resultsForVotesGained.add(resultVO);
					 }
					 if(statusForLost == 1){
						 ElectionComparisonResultVO resultVO = new ElectionComparisonResultVO();
						 ElectionComparisonResultVO resultVOforSecond = new ElectionComparisonResultVO();
						 resultVO.setDistrictId(resultSecond.getDistrictId());
						 resultVO.setDistrictName(resultSecond.getDistrictName());
						 resultVO.setStateId(resultSecond.getStateId());
						 resultVOforSecond.setDistrictId(resultFirst.getDistrictId());
						 resultVOforSecond.setDistrictName(resultFirst.getDistrictName());
						 resultVOforSecond.setStateId(resultFirst.getStateId());
									 	 
						 resultVOforSecond.setPartyElectionResultsVO(partyElectionResultsForSecond);
						 resultsForVotesLost.add(resultVOforSecond);
					 }
					 if(statusForNotConsidered == 1){
						 ElectionComparisonResultVO resultVOforConstituenciesNotConsi = new ElectionComparisonResultVO();
						 resultVOforConstituenciesNotConsi.setDistrictId(resultFirst.getDistrictId());
						 resultVOforConstituenciesNotConsi.setDistrictName(resultFirst.getDistrictName());
						 resultVOforConstituenciesNotConsi.setStateId(resultFirst.getStateId());
						 resultVOforConstituenciesNotConsi.setPartyElectionResultsVO(partyElectionResultsNotConsidered);
						 constituenciesNotConsidered.add(resultVOforConstituenciesNotConsi);
					 }
				 break;
				 }
			}
		 }
		 electionsComparisionVO.setVotesGainedCount(votesGainedCount);
		 electionsComparisionVO.setVotesLostCount(votesLostCount);
		 electionsComparisionVO.setCount(count);
		 electionsComparisionVO.setVotesGained(resultsForVotesGained);
		 electionsComparisionVO.setVotesLost(resultsForVotesLost);
		 electionsComparisionVO.setConstituenciesNotConsidered(constituenciesNotConsidered);
		 
	 return electionsComparisionVO;
	 }
	
   return null;
   }

   public PartyElectionResultsVO getResult(PartyElectionResultsVO firstPartyResults,PartyElectionResultsVO secondPartyResults){

	PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
	partyResultVO.setCandidateId(firstPartyResults.getCandidateId());
	partyResultVO.setCandidateName(firstPartyResults.getCandidateName());
	partyResultVO.setConstituencyId(firstPartyResults.getConstituencyId());
	partyResultVO.setConstituencyName(firstPartyResults.getConstituencyName());
	partyResultVO.setRank(firstPartyResults.getRank());
	partyResultVO.setVotesEarned(firstPartyResults.getVotesEarned());
	partyResultVO.setVotesPercentage(firstPartyResults.getVotesPercentage());
	partyResultVO.setSecondCandidateName(secondPartyResults.getCandidateName());
	partyResultVO.setRankBySecond(secondPartyResults.getRank());
	partyResultVO.setVotesEarnedBySecond(secondPartyResults.getVotesEarned());
	partyResultVO.setVotesPercentageBySecond(secondPartyResults.getVotesPercentage());
	
   return partyResultVO;
   }
   
   public PartyElectionResultsVO getResults(PartyElectionResultsVO firstPartyResults){

		PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
		partyResultVO.setCandidateId(firstPartyResults.getCandidateId());
		partyResultVO.setCandidateName(firstPartyResults.getCandidateName());
		partyResultVO.setConstituencyId(firstPartyResults.getConstituencyId());
		partyResultVO.setConstituencyName(firstPartyResults.getConstituencyName());
		partyResultVO.setRank(firstPartyResults.getRank());
		partyResultVO.setVotesEarned(firstPartyResults.getVotesEarned());
		partyResultVO.setVotesPercentage(firstPartyResults.getVotesPercentage());
		
	return partyResultVO;
	}
   
   
   public PartyResultsPercentageVO getPartyResultsPercentage(Long electionScopeId,Long partyId,String year){

	   List<CandidateResult> candidateResults = getCandidateElectionResults(electionScopeId,partyId,year);
	   
	   if(candidateResults != null){
		   PartyResultsPercentageVO resultsPercentage = new PartyResultsPercentageVO();
		   Double validVotes = new Double(0);
		   Double votesEarned = new Double(0);
		   Long totalSeatsWon = new Long(0);
		   
		   for(CandidateResult result:candidateResults){
			   ConstituencyElectionResult constituencyElectionResult = result.getNomination().getConstituencyElection().getConstituencyElectionResult();
			   if(result.getRank().equals(new Long(1))){
				   totalSeatsWon++;
			   }
			   
			   votesEarned = votesEarned + result.getVotesEarned();
			   validVotes = validVotes + constituencyElectionResult.getValidVotes();
		   }
		   
		   System.out.println("votesEarned -->" + votesEarned);
		   System.out.println("validVotes -->" + validVotes);
		   String percentage = getPercentage(validVotes,votesEarned);
		   resultsPercentage.setPercentage(percentage);
		   resultsPercentage.setYear(year);
		   resultsPercentage.setSeatsWOn(totalSeatsWon);
		   
	   return resultsPercentage;
	   }
	   
   return null;
   }
   
   public String getPercentage(Double validVotes,Double votesEarned){
	   
	   Double percentage = getPercentageOfVotes(votesEarned.doubleValue(),validVotes.doubleValue());
	   percentage = getRoundedDoubleValue(percentage);
	    
   return percentage.toString();
   }
   
 //Returns a Double value for a Double value with Rounded Decimal
	public Double getRoundedDoubleValue(Double value){
			 
		try{
			BigDecimal bd = new BigDecimal(value);
			BigDecimal bd1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			return bd1.doubleValue();
		}
		catch(Exception e){
			logger.debug("exception -->" + e);
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
			logger.debug("exception -->" + e);
		}
	return null;
	}

}
