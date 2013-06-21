package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateParty;

public interface ICandidatePartyDAO extends GenericDao<CandidateParty, Long>{
	
	public List<Object[]> getCandidatesOfAParty(Long partyId);


}
