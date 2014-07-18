package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CommitteeLevel;

public interface ICommitteeLevelDAO extends GenericDao<CommitteeLevel, Long>{
	public List<Object[]> getCommitteeLevels();
}
