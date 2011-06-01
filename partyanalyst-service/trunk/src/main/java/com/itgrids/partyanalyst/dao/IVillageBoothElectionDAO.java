package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VillageBoothElection;

public interface IVillageBoothElectionDAO extends GenericDao<VillageBoothElection, Long>{

	@SuppressWarnings("unchecked")
	public List findTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId);

	public List<Long> findByTownshipAndBoothConstituencyElection(Long townshipId,
			Long boothConstituencyElectionId);
	
	@SuppressWarnings("unchecked")
	public List findElectionsForElectionType(Long electionTypeId);
	
	@SuppressWarnings("unchecked")
	public List findTownsBoothIdsInTehsilForElection(String townshipIds, Long electionId);
	
	@SuppressWarnings("unchecked")
	public List findTownshipAndBoothConstiElecIds(String townshipIds, Long electionId);
	
	@SuppressWarnings("unchecked")
	public List findTownshipWiseVotingTrendsForATehsil(Long tehsilId, String electionIds);
	
	public List findPolledVotesInAllElectionsOfMandalByRevenueVillages(Long tehsilId);
	
	public List findVillageAndBoothInfoForBoothConstituencyElection(Long boothConstituencyElectionId);

	public void flushAndclearSession();
	
	public List<Object> findLatestElectionYearInARevenueVillageForElectionType(Long townshipId,String electionType);
}
