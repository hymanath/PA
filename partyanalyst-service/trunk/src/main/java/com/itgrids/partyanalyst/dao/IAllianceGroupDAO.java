package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.Party;

public interface IAllianceGroupDAO extends GenericDao<AllianceGroup, Long>  {
	
	public List<AllianceGroup> findByGroupId(Long groupId);
	
	@SuppressWarnings("unchecked")
	public List findPartysByGroupId(Long groupId);
	
	public List findAlliancePartiesByElectionAndParty(Long electionId, Long partyId);
	
	@SuppressWarnings("unchecked")
	public List findPartiesByGroup(Long groupId);
	
	public List findAlliancePartiesByElectionStateAndPartyExcludeParty(Long electionId, Long partyId, Long stateId);

	public List findAlliancePartiesByElectionAndPartyExcludeParty(Long electionId, Long partyId);
	
	public List<Party> findAlliancePartiesByElectionAndPartyForState(Long electionId, Long partyId, Long stateId);
	
	public List findAlliancePartiesByElectionStateAndPartyExcludeParty(List<Long> electionIds, Long partyId, Long stateId);
}
