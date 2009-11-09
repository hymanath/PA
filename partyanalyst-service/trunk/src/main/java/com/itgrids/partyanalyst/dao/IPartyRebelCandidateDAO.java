package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.PartyRebel;
import com.itgrids.partyanalyst.model.PartyRebelCandidate;

public interface IPartyRebelCandidateDAO extends GenericDao<PartyRebelCandidate, Long> {
	public List<PartyRebelCandidate> findByPartyIdAndElectionId(final Long partyId, final Long electionId);
}
