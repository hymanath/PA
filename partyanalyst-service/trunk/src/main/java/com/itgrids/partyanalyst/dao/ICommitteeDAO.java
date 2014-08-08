package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Committee;

public interface ICommitteeDAO extends GenericDao<Committee, Long>{
	public List<Long> getAllCommitteeLevelValuesByCommitteeLevel(Long committeeLevelId);
	public List<Object[]> getAllCommitteesForCommitteeLevelValues(Long committeeLevelValueId,Long scopeId);
}
