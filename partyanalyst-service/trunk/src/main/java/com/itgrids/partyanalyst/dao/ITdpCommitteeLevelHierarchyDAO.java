package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCommitteeLevelHierarchy;

public interface ITdpCommitteeLevelHierarchyDAO extends GenericDao<TdpCommitteeLevelHierarchy, Long> {

	public List<Long> getUpperLevelTdpCommitteeId(Long tdpCommiteeLevelId);
}
