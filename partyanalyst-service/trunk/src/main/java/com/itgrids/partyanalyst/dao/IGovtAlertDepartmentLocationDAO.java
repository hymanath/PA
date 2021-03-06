package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtAlertDepartmentLocation;

public interface IGovtAlertDepartmentLocationDAO extends GenericDao<GovtAlertDepartmentLocation, Long>{
	
	public List<Object[]> getDepartmentsForUser(Long userId);
	public List<Object[]> getDeptListForUser(Long userId);  
	public List<Object[]> getGovtDeptLevelForDeptAndUser(Long departmentId,Long userId);
	public List<Object[]> getDeptIdAndNameListForUser(Long userId);
	
	public List<Object[]> getUserAccessLevelsForUser(Long userId);
	public List<Object[]> getUserAccessLevels(Long userId);
	public List<Object[]> getAccessDeptsAndLvlsForUserAndDept(Long userId,Long departmentId);
	public List<Object[]> getDeptIdAndNameForUser(Long userId);
}
