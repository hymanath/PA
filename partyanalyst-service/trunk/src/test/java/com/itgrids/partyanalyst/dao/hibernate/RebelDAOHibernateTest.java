package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelCandidateDAO;
import com.itgrids.partyanalyst.dao.IPartyRebelDAO;
import com.itgrids.partyanalyst.dao.columns.enums.CandidateColumnNames;
import com.itgrids.partyanalyst.dao.columns.enums.ElectionColumnNames;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyRebel;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;

public class RebelDAOHibernateTest extends BaseDaoTestCase {

	IPartyRebelCandidateDAO partyRebelCandidateDAO;
		
	IPartyRebelDAO rebelPartyDAO;
	IElectionDAO electionDAO;
	IPartyDAO partyDAO;
	ICandidateDAO candidateDAO;
	
	

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

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
	
	//@Test
	public void testSavePartyRebelCandidates() {
		
		List<Long> rebelsList = new ArrayList<Long>();
		rebelsList.add(new Long(185));
		rebelsList.add(new Long(187));
		PartyRebel rebelParty = new PartyRebel();
		
		Election election =  electionDAO.findByProperty(ElectionColumnNames.ELECTION_ID, new Long(27)).get(0);
		Party party = partyDAO.findByPartyId(new Long(1)).get(0);
		rebelParty.setElection(election);
		rebelParty.setParty(party);
		//rebelDAO.save(rebelParty);
		
		Set<PartyRebelCandidate> candList = new HashSet<PartyRebelCandidate>();
		for(Long rebelCandId : rebelsList) {
			PartyRebelCandidate rebelCandidate = new PartyRebelCandidate();
			rebelCandidate.setPartyRebel(rebelParty);
			rebelCandidate.setCandidate(new Candidate(rebelCandId));
			candList.add(rebelCandidate);
		}
		
		List<PartyRebel> rebelParties = rebelPartyDAO.findByPartyIdAndElectionId(new Long(1),new Long(27));
		Long partyRebelId = null;
		Set<PartyRebelCandidate> rebelsCandList = null;
		for(PartyRebel partyRebel : rebelParties) {
			if(partyRebel.getParty().getPartyId().intValue() == 1 &&
					partyRebel.getElection().getElectionId().intValue() == 27) {
				partyRebelId = partyRebel.getPartyRebelId();
				rebelsCandList = partyRebel.getRebelCandidates();
			}
		}
		if(partyRebelId != null) {
			
			PartyRebel rParty = new PartyRebel(partyRebelId, election, party);
			for(Long rebelCandId : rebelsList) {
				if(!isRebelCandidateExist(rebelCandId, rebelsCandList)) {
					PartyRebelCandidate rebelCandidate = new PartyRebelCandidate();
					rebelCandidate.setPartyRebel(rParty);
					Candidate candidate = candidateDAO.findByProperty(CandidateColumnNames.CANDIDATE_ID, rebelCandId).get(0);
					rebelCandidate.setCandidate(candidate);
					partyRebelCandidateDAO.save(rebelCandidate);
				}
			}
		} else {
			rebelParty.setRebelCandidates(candList);
			rebelPartyDAO.save(rebelParty);
		
		}
		setComplete();
		
	}
	
	private Boolean isRebelCandidateExist(Long rebelCandId, Set<PartyRebelCandidate> rebelsCandList) {
		for(PartyRebelCandidate partyRebelCandidate : rebelsCandList) {
			if(rebelCandId.equals(partyRebelCandidate.getCandidate().getCandidateId())) 
				return true;
		}
		return false;
	}

	public void testFindByPartyIdAndElectionId() {
		List<PartyRebel> list = rebelPartyDAO.findByPartyIdAndElectionId(new Long(1),new Long(27));
		System.out.println(list.size());
	}

}
