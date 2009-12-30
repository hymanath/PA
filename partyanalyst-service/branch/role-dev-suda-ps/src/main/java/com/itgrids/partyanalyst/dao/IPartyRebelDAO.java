package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyRebel;

public interface IPartyRebelDAO extends GenericDao<PartyRebel, Long> {
	
	public List<PartyRebel> findByPartyIdAndElectionId(Long partyId, Long electionId);
}
