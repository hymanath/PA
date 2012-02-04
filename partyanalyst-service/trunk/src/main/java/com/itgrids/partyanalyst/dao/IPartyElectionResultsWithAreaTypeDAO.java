package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyElectionResultsWithAreaType;

public interface IPartyElectionResultsWithAreaTypeDAO extends GenericDao<PartyElectionResultsWithAreaType, Long>
{
	public Object getCountOfPartiesInAElection(Long electionId,List<Long>partiesList);
	
	public List<Object[]> getPartiesElectionResultInConstituencyAreaTypes(Long electionId,List<Long>partiesList);
}
