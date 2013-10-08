package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothResult;

public interface IBoothResultDAO extends GenericDao<BoothResult, Long>{

	public List<BoothResult> findByBoothConstituencyElection(Long boothConstituencyElectionId);

	public List<BoothResult> findByConstituencyAndElection(String constituencyName, String electionYear, Long electionScopeId);
	
	public List getAllPolledVotesByElectionsInDistrict(Long districtId, String electionType);
	
	public List getParliamentResultHappenedInAssembly(String ac, Long districtId, Long electionScopeId, String electionYear);
	
	public List getAllPolledVotesForMandalsInAnElection(String mandalIds, String electionYear, String electionType);
	
	public List getMandalwiseValidVotesForAMappedConstituency(Long constituencyId, String elecYear, String elecType);
	
	public List<Object[]> getAfterDelimitationEffectBasedOnVoters(Long electionId,Long constituencyid);
	
	public List<Object[]> getBeforeDelimitationEffectBasedOnVoters(Long electionId,List<Long> boothIds);
}
