package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtDepartmentDesignationHierarchy;

public interface IGovtDepartmentDesignationHierarchyDAO extends GenericDao<GovtDepartmentDesignationHierarchy,Long>{
	public List<Long> getParentDepartment(Long designationId);
}
