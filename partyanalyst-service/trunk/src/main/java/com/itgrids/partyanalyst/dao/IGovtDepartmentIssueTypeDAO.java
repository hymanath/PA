package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentIssueType;

public interface IGovtDepartmentIssueTypeDAO extends GenericDao<GovtDepartmentIssueType, Long>{

	public List<Object[]> getRelatedDepartmentsForIssueType(Long issueType);
}
