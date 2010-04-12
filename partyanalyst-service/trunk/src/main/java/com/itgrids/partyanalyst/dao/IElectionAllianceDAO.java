package com.itgrids.partyanalyst.dao;

import java.util.List;

import com.itgrids.partyanalyst.model.ElectionAlliance;

public interface IElectionAllianceDAO {
	
	public List<ElectionAlliance> findByElectionYearAndType(String year, Long electionType);
	
	@SuppressWarnings("unchecked")
	public List findGroupIdByElection(Long electionId);
	
	public List findAllianceGroupsInElections(String electionIds);
	
}
