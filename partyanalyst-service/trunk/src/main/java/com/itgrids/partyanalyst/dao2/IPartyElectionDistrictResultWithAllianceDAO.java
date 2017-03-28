package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyElectionDistrictResultWithAlliance;
import com.itgrids.partyanalyst.model.PartyElectionStateResultWithAlliance;

public interface IPartyElectionDistrictResultWithAllianceDAO extends
		GenericDao<PartyElectionDistrictResultWithAlliance, Long> {

	public List<PartyElectionStateResultWithAlliance> getPartyResultsForADistrictByPartyIdAndElectionId(Long electionId,Long partyId,Long districtId);
	
	public List findDistrictWiseElectionResultsForStatePartyAndElection(Long partyId, Long stateId, Long electionId);
	
}
