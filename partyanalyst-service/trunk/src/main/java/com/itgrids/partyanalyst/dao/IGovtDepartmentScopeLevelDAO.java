package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentScopeLevel;

public interface IGovtDepartmentScopeLevelDAO extends GenericDao<GovtDepartmentScopeLevel, Long> {
	public List<Object[]> getDepartmentLevels(Long deptId);
	public List<Object[]> getParentLevelsOfLevel(Long deptId,Long levelId);
	public List<Object[]> govtDepartmentScopeLevelDetails(Long scopeId);
	public List<Object[]> getChildDeptScopeIdList(Long govtDepartmentId,Long parentGovtDepartmentScopeId);
	public List<Object[]> getDepartmentSubLevels(Long departmentId,Long parentlevelId);
	public List<Object[]> getAllScopesInAscOrder(Long govtDepartmentId);
	public List<Object[]> getAllScopesOfAllDeptInAscOrder(List<Long> deptIds);
	//Santosh
	public List<Object[]> getChildGovtScopesLevelByParentScopeLevel(Long parentScopeId,Long deptId);
	public List<Object[]> getRequiredDeptScopeByScopeIds(List<Long> deptIds,Set<Long> scopeIds);
	public List<Object[]> getChildGovtScopeByParentScope(Set<Long> parentScopeIds,Long deptId);
}
