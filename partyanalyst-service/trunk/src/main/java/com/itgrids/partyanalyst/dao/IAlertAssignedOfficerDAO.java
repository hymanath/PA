package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficer;

public interface IAlertAssignedOfficerDAO extends GenericDao<AlertAssignedOfficer, Long>{
	
	public List<Object[]> getAssignedOfficersForAlert(Long alertId);
	public List<Object[]> getTotalAlertGroupByStatusForGovtOneDept(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList);
	public List<Object[]> getDepartmentWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList);
	public List<Object[]> getTotalAlertGroupByDepartmentThenStatusForGovt(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList);
	public List<Object[]> getLocationWiseThenStatusWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,Long deptId,Long levelValue);
	
	public List<Object[]> getStatusWiseAlertDetails(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList);
}
