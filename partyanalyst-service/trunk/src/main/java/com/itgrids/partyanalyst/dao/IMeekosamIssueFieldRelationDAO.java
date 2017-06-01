package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamIssueFieldRelation;

public interface IMeekosamIssueFieldRelationDAO extends	GenericDao<MeekosamIssueFieldRelation, Long> {

	public List<Object[]> getDynamicFieldsForIssueType(Long issueTypeId);
}
