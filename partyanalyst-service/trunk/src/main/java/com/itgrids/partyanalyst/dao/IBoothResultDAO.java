package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothResult;

public interface IBoothResultDAO extends GenericDao<BoothResult, Long>{

	public List<BoothResult> findByProperty(Object value);
	
	public BoothResult findByNominationAndBoothConstituencyElection(Long nominationId, Long boothConstituencyElectionId);
}
