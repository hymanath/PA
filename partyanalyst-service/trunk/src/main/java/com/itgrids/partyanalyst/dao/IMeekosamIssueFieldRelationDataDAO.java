package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamIssueFieldRelationData;

public interface IMeekosamIssueFieldRelationDataDAO extends	GenericDao<MeekosamIssueFieldRelationData, Long> {

	public List<Object[]> getAllDataForRelationIds(List<Long> relationIds);
}
