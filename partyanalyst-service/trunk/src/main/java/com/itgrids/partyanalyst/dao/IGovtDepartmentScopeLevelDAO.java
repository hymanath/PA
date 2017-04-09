package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentScopeLevel;

public interface IGovtDepartmentScopeLevelDAO extends GenericDao<GovtDepartmentScopeLevel, Long> {
	public List<Object[]> getDepartmentLevels(Long deptId);
	public List<Object[]> getParentLevelsOfLevel(Long deptId,Long levelId);
	public List<Object[]> govtDepartmentScopeLevelDetails(Long scopeId);
	public List<Object[]> getChildDeptScopeIdList(Long govtDepartmentId,Long parentGovtDepartmentScopeId);

}
