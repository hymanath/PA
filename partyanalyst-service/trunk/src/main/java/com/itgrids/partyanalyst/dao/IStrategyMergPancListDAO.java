package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StrategyMergPancList;

public interface IStrategyMergPancListDAO  extends GenericDao<StrategyMergPancList,Long> {
	public List<Object[]> getPanchayatsToMerge(Long constituencyId,String type,Long typeValue);
}
