/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 2, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.util.StringUtils;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.ICandidateResultObjectsDAO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateOppositionVO;
import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateResult;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;

public class CandidateDetailsService implements ICandidateDetailsService {

	
	/*
	 * (doc)
	 * 
	 */
	
	private ICandidateResultObjectsDAO candidateResultObjectsDAO;
	private ICandidateDAO candidateDAO;
	
	public void setCandidateResultObjectsDAO(
			ICandidateResultObjectsDAO candidateResultObjectsDAO) {
		this.candidateResultObjectsDAO = candidateResultObjectsDAO;
	}
	
	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public CandidateVO getCandidateDetails(Long candidateId){
		CandidateVO candidateVO = new CandidateVO();
		List<Candidate> candidate = candidateDAO.findCandidateDetails(candidateId);
		
		if(candidate != null){
			for(Candidate candidateDetails:candidate){
				candidateVO.setId(candidateDetails.getCandidateId());
				String name = null;
				  if(candidateDetails.getFirstname()!= null && candidateDetails.getLastname()!= null){
				    name = candidateDetails.getFirstname() + " " + candidateDetails.getLastname();
				  }
				  else if(candidateDetails.getFirstname() == null && candidateDetails.getLastname() != null)
					name = candidateDetails.getLastname();
				  else
					name = candidateDetails.getFirstname();
				candidateVO.setCandidateName(name);
				
				candidateVO.setImage(candidateDetails.getImage());
			}
		return candidateVO;
		}
		return null;
	}
	
	public List<CandidateDetailsVO> getCandidateElectionDetails(Long candidateId) {
		
		List<CandidateDetailsVO> candidateElectionDetails = new ArrayList<CandidateDetailsVO>(0);
		
		List<CandidateResult> candidateResults = candidateResultObjectsDAO.findCandidateResults(candidateId);
		 
		 if(candidateResults != null){
			//Nomination nomination = null;
			Candidate candidate = null;
			Party party = null;
			Constituency constituency = null;
			Election election = null;
			
			for(CandidateResult result:candidateResults){
				//nomination = result.getNomination();
				candidate = result.getNomination().getCandidate();
				party = result.getNomination().getParty();
				constituency = result.getNomination().getConstituencyElection().getConstituency();
				election = result.getNomination().getConstituencyElection().getElection();
				
				CandidateDetailsVO candidateDetails = new CandidateDetailsVO();
				
				candidateDetails.setCandidateId(candidate.getCandidateId());
				String name = null;
				  if(candidate.getFirstname()!= null && candidate.getLastname()!= null){
				    name = candidate.getFirstname() + " " + candidate.getLastname();
				  }
				  else if(candidate.getFirstname() == null &&candidate.getLastname() != null)
					name = candidate.getLastname();
				  else
					name = candidate.getFirstname();
				candidateDetails.setCandidateName(name);
				candidateDetails.setConstituencyName(constituency.getName());
				candidateDetails.setElectionId(election.getElectionId());
				candidateDetails.setElectionType(election.getElectionScope().getElectionType().getElectionType());
				candidateDetails.setElectionYear(election.getElectionYear());
				candidateDetails.setPartyName(party.getLongName());
				candidateDetails.setRank(result.getRank());
				String votes = result.getVotesEarned().toString();
				String votesEarn[] = StringUtils.split(votes, ".");
				candidateDetails.setVotesEarned(votesEarn[0]);
				candidateDetails.setVotesPercentage(result.getVotesPercengate());
				candidateDetails.setDistrictName(constituency.getDistrict().getDistrictName());
				candidateDetails.setStateName(constituency.getState().getStateName());
				candidateDetails.setImage(candidate.getImage());
				
				if(result.getRank().equals(new Long(1)))
					candidateDetails.setStatus(true);
				else
					candidateDetails.setStatus(false);
				
				List<CandidateOppositionVO> oppositionCandidates = getCandidatesOppositionList(candidate.getCandidateId(),election.getElectionId(),constituency.getConstituencyId());
				candidateDetails.setOppositionCandidates(oppositionCandidates);
				
				
				candidateElectionDetails.add(candidateDetails);
			}  
			
		 return candidateElectionDetails;
		 }
	return null; 
	}
	
	public List<CandidateOppositionVO> getCandidatesOppositionList(Long candidateId,Long electionId,Long constituencyId){
		
		List<CandidateOppositionVO> oppositionCandidatesList =  new ArrayList<CandidateOppositionVO>(0);
		List<CandidateResult> candidateResultsList = candidateResultObjectsDAO.findCandidateResults(candidateId, electionId, constituencyId);
		 
		  if(candidateResultsList != null){
			  
			  Party party = null;
			  Candidate candidate = null;
			  
			  for(CandidateResult result:candidateResultsList){
				  
				  party = result.getNomination().getParty();
				  candidate  = result.getNomination().getCandidate();
				  
				  CandidateOppositionVO oppositionCandidate = new CandidateOppositionVO();
				  
				  oppositionCandidate.setCandidateId(candidate.getCandidateId());
				  String name = null;
				  if(candidate.getFirstname()!= null && candidate.getLastname()!= null){
				    name = candidate.getFirstname() + " " + candidate.getLastname();
				  }
				  else if(candidate.getFirstname() == null &&candidate.getLastname() != null)
					name = candidate.getLastname();
				  else
					name = candidate.getFirstname();
				  oppositionCandidate.setCandidateName(name);
				  oppositionCandidate.setPartyId(party.getPartyId());
				  oppositionCandidate.setPartyName(party.getLongName());
				  oppositionCandidate.setRank(result.getRank());
				  String votes = result.getVotesEarned().toString();
				  String votesEarn[] = StringUtils.split(votes, ".");
				  oppositionCandidate.setVotesEarned(votesEarn[0]);
				  oppositionCandidate.setVotesPercentage(result.getVotesPercengate());
				  
				  if(result.getRank().equals(new Long(1)))
					  oppositionCandidate.setStatus(true);
					else
						oppositionCandidate.setStatus(false);
				  oppositionCandidatesList.add(oppositionCandidate);
				  
			  }
		  return oppositionCandidatesList;	  
		  }
		
		return null;
	}
	

}
