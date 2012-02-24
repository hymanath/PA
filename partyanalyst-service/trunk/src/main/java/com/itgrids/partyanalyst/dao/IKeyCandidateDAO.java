package com.itgrids.partyanalyst.dao;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.KeyCandidate;


public interface IKeyCandidateDAO extends GenericDao<KeyCandidate,Long>{
	
	List<Object[]> getCandidatesBasedOnPartyId(String candidateName,Long stateId , Long PartyId);
	
	public Object getCountCandidate(Long candidateId);
	
	public List<KeyCandidate> getCandidateById(Long candidateId);
}
