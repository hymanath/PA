package com.itgrids.partyanalyst.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtAlertSubTask;

public interface IGovtAlertSubTaskDAO extends GenericDao<GovtAlertSubTask, Long> {
	
	public List<Object[]> getDistrictOfficerAlertsSubTasksCount(Long govtDepDesigOffcrId,Long govtOffcrId,String countType,String type);
	public List<Object[]> getSubTaskCount(List<Long> alertIds);
 	public List<Object[]> getSubTaskAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate);
 	public List<Object[]> getDistrictLevelDeptWiseStatusOverViewForSubTask(Date fromDate, Date toDate,Long scopeId,Long deptId,Long levelId);
 	public List<Object[]> getDistrictLevelDeptWiseLocationLevelViewForSubtask(Date fromDate, Date toDate,Long deptId);
 	public List<Object[]> getSubOrdinateTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,String type);
 	 public List<Long> getDistrictOfficerSubTasksAlertIds(Long govtDepDesigOffcrId,Long govtOffcrId,String countType,String type);
 	public List<Object[]> getDistrictOfficerSubTaskAlerts(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
 			Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,String type);
 	 public Integer updateSubTaskPriority(Long subTaskId,Long id,Long userId,Date date);
 	public Integer updateSubTaskDueDate(Long subTaskId,Date dueDate,Long userId,Date date);
 	public List<Object[]> getSubTaskInfoForAlert(Long alertId);
}
