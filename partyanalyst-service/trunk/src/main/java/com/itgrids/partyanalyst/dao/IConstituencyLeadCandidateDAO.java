package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyLeadCandidate;

public interface IConstituencyLeadCandidateDAO  extends GenericDao<ConstituencyLeadCandidate, Long>{
	
	@SuppressWarnings("unchecked")
	public List getCountOfOldConstituenciesInAElection(Long electionId);
	
	@SuppressWarnings("unchecked")
	public List getCountOfDelimitedConstituenciesInAElection(Long electionId);
	
	public List getLeadingConstituenciesCount(Long electionId);

	public List<Object[]> getPartyLeadingOrWinningConstituencies(Long electionId);
	
	
	public List<Object> getCandidateResultStatus(Long candidateId,Long constiElecId);
	
	public List<Object> checkCandidateResultExist(Long constiElecId);
	
	public List getElectionIds(Long electionId);
	
	public List<Object[]> getPartiesLeadingInfo(Long electionId);
}
