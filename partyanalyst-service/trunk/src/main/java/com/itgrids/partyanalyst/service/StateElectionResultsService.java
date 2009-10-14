/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.dao.ICandidateResultObjectsDAO;
import com.itgrids.partyanalyst.dto.PartyResultsVO;
import com.itgrids.partyanalyst.dto.StateElectionResultsVO;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;

public class StateElectionResultsService implements
		IStateElectionResultsService {

	private ICandidateResultObjectsDAO candidateResultObjectsDAO;
	
	public ICandidateResultObjectsDAO getCandidateResultObjectsDAO() {
		return candidateResultObjectsDAO;
	}
	public void setCandidateResultObjectsDAO(
			ICandidateResultObjectsDAO candidateResultObjectsDAO) {
		this.candidateResultObjectsDAO = candidateResultObjectsDAO;
	}
	
   public StateElectionResultsVO getStateElectionResults(Long electionId){
		
		List<PartyResultsVO> partyResultsVO = new ArrayList<PartyResultsVO>(0);
		StateElectionResultsVO stateElectionResults = new StateElectionResultsVO();
		Long partyId;
		String partyName;
		int count=0;
		
		List<CandidateResult> candidateResults = candidateResultObjectsDAO.findCandidateResultObjects(electionId);
		
		  if(candidateResults != null){
		     Election election = null;
		     election = candidateResults.get(0).getNomination().getConstituencyElection().getElection();
			   
			   stateElectionResults.setElectionId(election.getElectionId());
			   stateElectionResults.setElectionType(election.getElectionScope().getElectionType().getElectionType());
			   stateElectionResults.setElectionYear(election.getElectionYear());
			   
				   for(CandidateResult result:candidateResults){
					   
					   	if(result.getRank().equals(new Long(1))){
							Party party= result.getNomination().getParty();
							partyId = party.getPartyId();
							partyName=party.getLongName();
					    
						     
							if(partyResultsVO.size()>0){
								for(PartyResultsVO resultsVO:partyResultsVO){
									if(resultsVO.getPartyId().equals(partyId)){
										resultsVO.setPartyId(partyId);
										resultsVO.setPartyName(partyName);
										resultsVO.setTotalSeatsWon(resultsVO.getTotalSeatsWon() + 1);
										count++;
										
									}
								}
							}
							if(partyResultsVO.size() == 0 || count == 0 ){
							  PartyResultsVO partyResult = new PartyResultsVO();
							  partyResult.setPartyId(partyId);
							  partyResult.setPartyName(partyName);
							  partyResult.setTotalSeatsWon(new Long(1));
							  
							  partyResultsVO.add(partyResult);
							  
							}
						   			
						count = 0;
					   }
				  }
				  stateElectionResults.setPartyResultsVO(partyResultsVO);
				   
		  return stateElectionResults;					 
		  }
		  
	return null;
	}

}
