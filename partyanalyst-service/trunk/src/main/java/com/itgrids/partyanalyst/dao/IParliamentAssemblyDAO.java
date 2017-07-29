package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ParliamentAssembly;

public interface IParliamentAssemblyDAO extends GenericDao<ParliamentAssembly, Long> {
	public List<Long> getAssemblyConstituencyforParliament(List<Long> userAccessLevelValues);
	public List<Object[]> getAssemblyConstituencyforByPaliament(List<Long> userAccessLevelValues);
	public List<Object[]> getParliamentDetailsOfAssembly(Long stateId);
}
