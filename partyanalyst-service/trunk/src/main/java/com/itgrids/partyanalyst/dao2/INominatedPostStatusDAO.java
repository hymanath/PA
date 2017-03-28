package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NominatedPostStatus;

public interface INominatedPostStatusDAO extends GenericDao<NominatedPostStatus, Long>{
	public List<Long> getStatusIdsList();
	public List<Object[]> getAllNominatedStatusList();
}
