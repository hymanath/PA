package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamIssueType;

public interface IMeekosamIssueTypeDAO extends GenericDao<MeekosamIssueType, Long> {

	public List<Object[]> getMeekosamSubIssueTypeListForParentIssueType(Long parentIssueTypeId);
	public Long getParentIssueType(Long issueTypeId);
}
