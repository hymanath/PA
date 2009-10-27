/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 23, 2009
 */
package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionScopeDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.ElectionComparisonResultVO;
import com.itgrids.partyanalyst.dto.ElectionsComparisonVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.service.IElectionsComparisonService;

public class ElectionsComparisonService implements IElectionsComparisonService {

	
	private IStateDAO stateDAO;
	private IPartyDAO partyDAO;
	private IElectionDAO electionDAO;
	private ICandidateResultDAO candidateResultDAO;
	private IElectionScopeDAO electionScopeDAO;
	

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
		
		 List<Election> elections = electionDAO.getAll();
			 if(elections!=null){
				 for(Election election:elections){
					 selectOption = new SelectOptionVO();
					 selectOption.setId(i);
					 selectOption.setName(election.getElectionYear());
					 selectOptionList.add(selectOption);
					 i++;
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
	
	   List<CandidateResult> candidateResults = candidateResultDAO.findCandidateResults(electionScopeId, partyId, year);
	
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
	
	ElectionsComparisonVO electionsComparisionVO = new ElectionsComparisonVO();
	
	resultForFirstYear = getPartyElectionResults(electionScopeId,partyId,firstYear);
	resultForSecondYear = getPartyElectionResults(electionScopeId,partyId,secondYear);
	
	 if(resultForFirstYear != null && resultForSecondYear != null){
		 
		 String partyName = null;
		 electionsComparisionVO.setFirstYear(firstYear);
		 electionsComparisionVO.setSecondYear(secondYear);
		 for(ElectionComparisonResultVO resultFirst:resultForFirstYear){
			 
			  for(ElectionComparisonResultVO resultSecond:resultForSecondYear){
				 if(resultSecond.getDistrictId().equals(resultFirst.getDistrictId())){
					 List<PartyElectionResultsVO> partyElectionResultsForFirst = new ArrayList<PartyElectionResultsVO>();
					 List<PartyElectionResultsVO> partyElectionResultsForSecond = new ArrayList<PartyElectionResultsVO>();
					 ElectionComparisonResultVO resultVO = new ElectionComparisonResultVO();
					 ElectionComparisonResultVO resultVOforSecond = new ElectionComparisonResultVO();
					 resultVO.setDistrictId(resultSecond.getDistrictId());
					 resultVO.setDistrictName(resultSecond.getDistrictName());
					 resultVO.setStateId(resultSecond.getStateId());
					 resultVOforSecond.setDistrictId(resultFirst.getDistrictId());
					 resultVOforSecond.setDistrictName(resultFirst.getDistrictName());
					 resultVOforSecond.setStateId(resultFirst.getStateId());
					 
					 for(PartyElectionResultsVO firstPartyResults:resultFirst.getPartyElectionResultsVO()){
						 for(PartyElectionResultsVO secondPartyResults:resultSecond.getPartyElectionResultsVO()){
							 if(firstPartyResults.getConstituencyId().equals(secondPartyResults.getConstituencyId()) && firstPartyResults.getVotesEarned() < secondPartyResults.getVotesEarned()){
								 
							    PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
							    partyResultVO = getResult(firstPartyResults,secondPartyResults);
							    Double votesDiff = partyResultVO.getVotesEarnedBySecond() - partyResultVO.getVotesEarned();
							    partyResultVO.setVotesDiff(votesDiff);
							    partyElectionResultsForFirst.add(partyResultVO);
							    break;
							 }
							 else if(firstPartyResults.getConstituencyId().equals(secondPartyResults.getConstituencyId()) && firstPartyResults.getVotesEarned() >= secondPartyResults.getVotesEarned()){
								 
								 PartyElectionResultsVO partyResultVO = new PartyElectionResultsVO();
								 partyResultVO = getResult(firstPartyResults,secondPartyResults);
								 Double votesDiff = partyResultVO.getVotesEarned() - partyResultVO.getVotesEarnedBySecond();
								 partyResultVO.setVotesDiff(votesDiff);
								 partyElectionResultsForSecond.add(partyResultVO);
								 break;
							 }
						 }
						 partyName = firstPartyResults.getPartyName();
	    				 electionsComparisionVO.setPartyName(partyName);
					 }
					 resultVO.setPartyElectionResultsVO(partyElectionResultsForFirst);
					 resultsForVotesGained.add(resultVO);
					 	 
					 resultVOforSecond.setPartyElectionResultsVO(partyElectionResultsForSecond);
					 resultsForVotesLost.add(resultVOforSecond);
				 break;
				 }
			}
		 }
		 electionsComparisionVO.setVotesGained(resultsForVotesGained);
		 electionsComparisionVO.setVotesLost(resultsForVotesLost);
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

}
