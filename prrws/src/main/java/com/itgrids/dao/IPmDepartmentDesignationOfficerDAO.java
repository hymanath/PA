package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PmDepartmentDesignationOfficer;

public interface IPmDepartmentDesignationOfficerDAO extends GenericDao<PmDepartmentDesignationOfficer, Long> {
	public List<Object[]> getDeptDesignationOfficerDetailsByDeptDesignation(List<Long> deptDesignationIdsList,List<Long> deptIdsList);
	public List<Object[]> getDeptDesignationOfficerDetailsByDeptAndOffId(Long officerDesignationId,Long pmDepartmentDesignationOfficerId);
	public Long geOfficerIdByDeptDesigIds(List<Long> deptdesigIds);
}
