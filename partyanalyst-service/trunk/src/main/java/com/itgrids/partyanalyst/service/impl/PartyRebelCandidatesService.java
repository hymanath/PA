package com.itgrids.partyanalyst.service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelDAO;
import com.itgrids.partyanalyst.dao.columns.enums.ElectionColumnNames;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyRebel;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;
import com.itgrids.partyanalyst.service.IPartyRebelCandidatesService;

public class PartyRebelCandidatesService implements IPartyRebelCandidatesService {

	IPartyRebelCandidateDAO partyRebelCandidateDAO;
	IPartyRebelDAO rebelPartyDAO;
	IElectionDAO electionDAO;
	IPartyDAO partyDAO;
	

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}

	public void setRebelPartyDAO(IPartyRebelDAO rebelPartyDAO) {
		this.rebelPartyDAO = rebelPartyDAO;
	}

	public void setPartyRebelCandidateDAO(IPartyRebelCandidateDAO rebelDAO) {
		this.partyRebelCandidateDAO = rebelDAO;
	}


	public void savePartyRebelCandidates(Long stateId, Long partyId,
			Long electionId, List<Long> rebelsList) {
		
		Long partyRebelId = null;
		Set<PartyRebelCandidate> rebelsCandList = null;
		
		Election election =  electionDAO.findByProperty(ElectionColumnNames.ELECTION_ID, electionId).get(0);
		Party party = partyDAO.findByPartyId(partyId).get(0);
		//Create PartyRebel Entity

		PartyRebel rebelParty = new PartyRebel();
		rebelParty.setElection(election);
		rebelParty.setParty(party);

		//Set PartyRebelCandidate entity list
		Set<PartyRebelCandidate> candList = new HashSet<PartyRebelCandidate>();
		for(Long rebelCandId : rebelsList) {
			PartyRebelCandidate rebelCandidate = new PartyRebelCandidate();
			rebelCandidate.setPartyRebel(rebelParty);
			rebelCandidate.setCandidate(new Candidate(rebelCandId));
			candList.add(rebelCandidate);
		}

		//Find rebel party exist already or not
		List<PartyRebel> rebelParties = rebelPartyDAO.findByPartyIdAndElectionId(partyId, electionId);

		for(PartyRebel partyRebel : rebelParties) {
			if(partyRebel.getParty().getPartyId().equals(party.getPartyId()) &&
					partyRebel.getElection().getElectionId().equals(election.getElectionId())) {
				partyRebelId = partyRebel.getPartyRebelId();
				rebelsCandList = partyRebel.getRebelCandidates();
			}
		}
		
		//Insert candidates if not exist
		if(partyRebelId != null) {
			PartyRebel rParty = new PartyRebel(partyRebelId, election, party);
			for(Long rebelCandId : rebelsList) {
				if(!isRebelCandidateExist(rebelCandId, rebelsCandList)) {
					PartyRebelCandidate rebelCandidate = new PartyRebelCandidate();
					rebelCandidate.setPartyRebel(rParty);
					rebelCandidate.setCandidate(new Candidate(rebelCandId));
					partyRebelCandidateDAO.save(rebelCandidate);
				}
			}
		} else { // Insert rebel parties if not exist
			rebelParty.setRebelCandidates(candList);
			rebelPartyDAO.save(rebelParty);

		}

	}

	private Boolean isRebelCandidateExist(Long rebelCandId, Set<PartyRebelCandidate> rebelsCandList) {
		for(PartyRebelCandidate partyRebelCandidate : rebelsCandList) {
			if(rebelCandId.equals(partyRebelCandidate.getCandidate().getCandidateId())) 
				return true;
		}
		return false;
	}

	public List<SelectOptionVO> findByPartyIdAndElectionId(Long partyId, Long electionId) {
		List<PartyRebelCandidate> rebelsList =  partyRebelCandidateDAO.findByPartyIdAndElectionId(partyId, electionId);
		List<SelectOptionVO> rebelsVO = new ArrayList<SelectOptionVO>();
		for(PartyRebelCandidate rebelCandidate : rebelsList) {
			rebelsVO.add(new SelectOptionVO(rebelCandidate.getCandidate().getCandidateId(), rebelCandidate.getCandidate().getLastname()));
		}
		return rebelsVO;
	}


}
