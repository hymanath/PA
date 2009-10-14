package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothConstituencyElection;

public interface IBoothConstituencyElectionDAO extends GenericDao<BoothConstituencyElection, Long>{

	public BoothConstituencyElection findByBoothAndConstiuencyElection(Long partNo, Long constituencyElectionId);
}
