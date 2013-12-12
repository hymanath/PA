package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Flag;

public interface IFlagDAO extends GenericDao<Flag, Long>{

	public List<Flag> getAllFlags();
	
	public Integer deleteFlag(Long flagId);
	
	public List<Object> checkFlagName(String flagName);
}
