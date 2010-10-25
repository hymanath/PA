package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LocalElectionBodyWard;

public interface ILocalElectionBodyWardDAO extends GenericDao<LocalElectionBodyWard, Long>  {

	public List getLocalBodyElectionInfo(List<Long> constituencyIds);
	
}
