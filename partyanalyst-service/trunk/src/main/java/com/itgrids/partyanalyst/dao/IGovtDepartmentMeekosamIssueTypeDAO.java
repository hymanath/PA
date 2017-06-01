package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentMeekosamIssueType;

public interface IGovtDepartmentMeekosamIssueTypeDAO extends GenericDao<GovtDepartmentMeekosamIssueType, Long> {

	public List<Object[]> getMeekosamIssueTypeListByDept(Long deptId);
}
