package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.HamletBoothElection;

public interface IHamletBoothElectionDAO extends GenericDao<HamletBoothElection, Long>{

	public List<Long> findByHamletAndBoothConstituencyElection(Long hamletId,
			Long boothConstituencyElectionId) ;
	
}
