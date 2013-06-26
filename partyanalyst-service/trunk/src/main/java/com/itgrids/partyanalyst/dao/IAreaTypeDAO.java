package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AreaType;

public interface IAreaTypeDAO extends GenericDao<AreaType,Long>{
	public List<Object[]> getElectionYears(Long stateId,Long electionType);
	public List<Object[]> getPartiesForElection(Long electionId,Long constituencyId);
}
