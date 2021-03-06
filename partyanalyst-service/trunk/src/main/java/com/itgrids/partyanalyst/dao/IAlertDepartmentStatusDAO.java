package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertDepartmentStatus;

public interface IAlertDepartmentStatusDAO extends GenericDao<AlertDepartmentStatus, Long>{
	public List<Object[]> getAllStatus();
	public List<Object[]> getAllStatusForDepartment(Long govtDepartmentId);
	public List<Object[]> getAlertStatusByAlertType(Long alertTypeId);
	public List<Object[]> getStatusForDepartments(List<Long> deptIds);
	public List<Object[]> getStatusWithoutPending();
	public List<Object[]> getAlertStatusByAlertTypeId(Long alertTypeId);
	 public List<Object[]> getAlertStatusByAlertStatusId(List<Long> alertStatusIds,Long alertTypeId);
	 public List<Object[]> getAllStatuses(List<Long> alertStatusIds);
	 public List<Object[]> getAlertGovtDepartmentStatus(Long deptId);
	 public List<Object[]> getAlertStatusByDepartmentId(Long deptId);
	 public List<Object[]> getRequiredAlertGovtDepartmentStatus();
}
