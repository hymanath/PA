package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmDepartmentDesignationHierarchy;

public interface IPmDepartmentDesignationHierarchyDAO extends GenericDao<PmDepartmentDesignationHierarchy, Long> {
	public List<Object[]> getSubDesignationDetailsForParentDeptDesignations(List<Long> deptDesignationIdsList,List<Long> deptIdsList);
}
