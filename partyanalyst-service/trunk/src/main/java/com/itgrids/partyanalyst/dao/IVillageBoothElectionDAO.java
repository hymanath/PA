package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VillageBoothElection;

public interface IVillageBoothElectionDAO extends GenericDao<VillageBoothElection, Long>{

	public List findTownshipWiseBoothDetailsForTehsil(Long tehsilId, Long electionId);

	public List<Long> findByTownshipAndBoothConstituencyElection(Long townshipId,
			Long boothConstituencyElectionId);
	
}
