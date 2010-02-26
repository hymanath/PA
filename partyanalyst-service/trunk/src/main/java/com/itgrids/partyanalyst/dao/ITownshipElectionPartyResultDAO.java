package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TownshipElectionPartyResult;

public interface ITownshipElectionPartyResultDAO extends GenericDao<TownshipElectionPartyResult, Long>{
	public List<TownshipElectionPartyResult> findByTownshipID(Long townshipID);
}
