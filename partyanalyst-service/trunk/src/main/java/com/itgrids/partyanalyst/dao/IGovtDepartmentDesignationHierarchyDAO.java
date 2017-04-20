package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationHierarchy;

public interface IGovtDepartmentDesignationHierarchyDAO extends GenericDao<GovtDepartmentDesignationHierarchy,Long>{
	public List<Long> getParentDepartment(Long designationId);
	public List<Long> getOldParentDepartment(Long designationId);
	public List<Long> getChildLocationDesignations(Long designationId);
	public List<Object[]> getChildDesigData(List<Long> parentGovtDeptDesigIdList, Long childGovtDeptDesigId2);
	public List<Object[]> getChildDesigDataNew(List<Long> parentGovtDeptDesigIdList, Long childGovtDeptDesigId2);
}
