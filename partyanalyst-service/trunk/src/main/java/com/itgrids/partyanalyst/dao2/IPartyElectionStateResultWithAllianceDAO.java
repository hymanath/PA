package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyElectionStateResultWithAlliance;

public interface IPartyElectionStateResultWithAllianceDAO extends
		GenericDao<PartyElectionStateResultWithAlliance, Long> {

	public List<PartyElectionStateResultWithAlliance> getPartyResultsByStateIdAndElectionId(Long electionId,Long partyId,Long stateId);

	public List findStatewiseResultsForPartyElectionAndCountry(Long partyId,
			Long countryId, Long electionId);
}
