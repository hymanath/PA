package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.PartyElectionResult;

public interface IElectionAllianceDAO extends GenericDao<ElectionAlliance, Long> {
	
	public List<ElectionAlliance> findByElectionYearAndType(String year, Long electionType);
	
	@SuppressWarnings("unchecked")
	public List findGroupIdByElection(Long electionId);
	
	public List findAllianceGroupsInElections(String electionIds);
	
	@SuppressWarnings("unchecked")
	public List findAllGroupsForAnElection(String electionType,String electionYear);

	public List<ElectionAlliance> getAllianceElectionByElectionIdAndStateId(Long electionId,Long stateId);
	
	public List getAllAllianceElectionYearsForAParty(Long partyId,String electionSubType,Long stateId);
	
	public List<ElectionAlliance> findByElectionId(Long electionId);
	
	public List<ElectionAlliance> findByElectionYearAndType(final String electionYear, final Long electionType, Long stateId);
}
