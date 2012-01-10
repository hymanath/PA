package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HamletBoothElection;

public interface IHamletBoothElectionDAO extends GenericDao<HamletBoothElection, Long>{

	public List<Long> findByHamletAndBoothConstituencyElection(Long hamletId,
			Long boothConstituencyElectionId) ;
	
	public List findPanchayathsWiseBoothsAndHamletsDataInTehsilForElection(Long tehsilId, Long electionId);
	
	public List findPanchayathBoothIdsInTehsilForElection(Long tehsilId, Long electionId);
	
	public List<Object[]> getPanchayatWiseBoothDetails(Long tehsilId,Long electionId);
	
	public List<Object[]> findPanchayatWiseVotingTrendsForATehsil(Long tehsilId, String electionIds);
	
}
