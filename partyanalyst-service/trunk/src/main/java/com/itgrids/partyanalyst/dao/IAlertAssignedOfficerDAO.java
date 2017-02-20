package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficer;

public interface IAlertAssignedOfficerDAO extends GenericDao<AlertAssignedOfficer, Long>{
	
	public List<Object[]> getAssignedOfficersForAlert(Long alertId);
	public List<Object[]> getTotalAlertGroupByStatusForGovtOneDept(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList);
	public List<Object[]> getLocationWiseThenStatusWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,Long deptId,Long levelValue);
	
	public List<Object[]> getStatusWiseAlertDetails(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long levelId,List<Long> levelValues,Long statusId);
	public List<Object[]> getSubOrdinatesAlertDetails(Long designationId,Long levelId,Date fromDate,Date toDate);
	public List<Long> getAlertAssignedOfficerIdsForAlertByUser(Long alertId,Long userId);
	public List<Object[]> getTotalAlertGroupByStatusForAlertList(Set<Long> alertIdSet);   
	public List<Object[]> getTotalAlertGroupByDeptForAlertList(Set<Long> alertIdSet);
	
	public List<Object[]> getSubOrdinateLevelWiseAlertsDetails(Long designationId,Long levelId,Long levelValue,Date fromDate,Date toDate);
	public List<Object[]> getAlertIdAndDeptDesigOfficerIdAndGovtOfficerIdList(Date fromDate,Date toDate, List<Long> deptIdList, Long locValue, List<Long> locIdList,Long statusId);
	public List<Object[]> getDepartmentWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long locValue, List<Long> locIdList);
	public List<Object[]> getTotalAlertGroupByDepartmentThenStatusForGovt(Date fromDate,Date toDate,Long stateId,List<Long> printIdList,List<Long> electronicIdList,List<Long> deptIdList,Long locValue, List<Long> locIdList);
	
	public List<Object[]> getDesigAndStatusWiseAlertsCounts(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList);
	public List<Object[]> getDepartmentAndDistrictWiseAlertsCounts(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList);
	public List<Object[]> getStatusWiseTotalCountsForAlert(List<Long> departmentIds,Long levelId,List<Long> levelValues,Long stateId,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList);
}
  