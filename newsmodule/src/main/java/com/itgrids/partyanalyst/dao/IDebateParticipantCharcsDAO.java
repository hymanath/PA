package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DebateParticipantCharcs;

public interface IDebateParticipantCharcsDAO extends GenericDao<DebateParticipantCharcs, Long>{

	public List<Object[]> getDebateCharcsDetails(Long debateId);
	public List<Object[]> getDebatePerformanceCount();
	public List<Object[]> getDebatePerformanceCountCharcs();
	public List<Object[]> getPartyWiseTotalDebatesAndScales();
	public List<Object[]> getPartyWiseDebatePartiCharsCount();
	public List<Object[]> getPartyWiseEachDebateCharsCount();
	public List<Object[]> getPartyCandidateDetailsTopicWise();
	public List<Object[]> getTopicWiseStrongOrWeakCandidates(String sortOrder);
}
