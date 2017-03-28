package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StrategyAssumptions;

public interface IStrategyAssumptionsDAO extends GenericDao<StrategyAssumptions,Long> {

	public List<StrategyAssumptions> getDataByConstituencyId(Long constituencyId);
}
