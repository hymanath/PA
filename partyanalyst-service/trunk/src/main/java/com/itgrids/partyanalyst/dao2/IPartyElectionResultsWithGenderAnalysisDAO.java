package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyElectionResultsWithGenderAnalysis;

public interface IPartyElectionResultsWithGenderAnalysisDAO extends GenericDao<PartyElectionResultsWithGenderAnalysis,Long>{

	public Object getCountOfPartiesInAElection(Long electionId,List<Long>partiesList);
	
	public List<Object[]> getPartyWiseGenderDetails(Long electionId,List<Long>partiesList);
}
