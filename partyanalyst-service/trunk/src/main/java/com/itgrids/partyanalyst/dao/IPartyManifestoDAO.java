package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.PartyManifesto;

public interface IPartyManifestoDAO extends GenericDao<PartyManifesto, Long> {
	public List<Object[]> getPartyManifestoInfo(Long partyId);
	
	public List<Object[]> getSelectedState(Long partyId);

	public List<Object[]> getElectionTypes(Long partyId, Long stateId);
	
	public List<Object[]> getElectionYearsBasedOnElectionTypeId(Long electionTypeId,Long partyId,Long stateId);

	public List<File> getPartyManifestoBasedOnElectionYear(Long electionId,
			Long partyId, Long stateId);

	public List<Object[]> getPartyManifestoInfo(Long partyId, String queryStr);
}
