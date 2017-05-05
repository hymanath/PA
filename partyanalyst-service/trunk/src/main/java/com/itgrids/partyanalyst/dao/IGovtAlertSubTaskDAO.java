package com.itgrids.partyanalyst.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtAlertSubTask;

public interface IGovtAlertSubTaskDAO extends GenericDao<GovtAlertSubTask, Long> {
	
	public List<Object[]> getDistrictOfficerAlertsSubTasksCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<Object[]> getSubTaskCount(List<Long> alertIds);
 	public List<Object[]> getSubTaskAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate);
 	public List<Object[]> getDistrictLevelDeptWiseStatusOverViewForSubTask(Date fromDate, Date toDate,Long scopeId,List<Long> deptIds,Long levelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> levelValues);
 	public List<Object[]> getDistrictLevelDeptWiseLocationLevelViewForSubtask(Date fromDate, Date toDate,Long deptId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long levelId,List<Long> levelValues);
 	public List<Object[]> getSubOrdinateTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,String type);
 	 public List<Long> getDistrictOfficerSubTasksAlertIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,Date formDate,Date toDate);
  	public List<Object[]> getDistrictOfficerSubTaskAlerts(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
 			Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,String type);
	 public Integer updateSubTaskPriority(Long subTaskId,Long id,Long userId,Date date);
 	public Integer updateSubTaskDueDate(Long subTaskId,Date dueDate,Long userId,Date date);
 	public List<Object[]> getSubTaskInfoForAlert(Long alertId);
 	//public List<Long> getDistrictOfficerAlertsDetails(Date fromDate,Date toDate,Long stateId,Long levelId,List<Long> levelValues,
		        //Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long govtDeptWorkLocId,Long statusId,Long childGovtScopeId);
 	public List<Object[]> getDistrictOfficerMyAssignedSubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
 	public List<Object[]> getDistrictOfficerMySubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
 	public List<Object[]> getDistrictOfficerMyAssignedSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
 	public List<Object[]> getDistrictOfficerAlertsSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
 	public List<Object[]> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
 		   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
 		   Long districtWorkLocationId, String group,String searchType,List<Long> calCntrIds);
 	public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseSubTaskCountForOverview(Date fromDate,Date toDate,Long stateId,
 		   List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
 		   Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,
 		   Long subDivisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long lvlTwo,Long lvlThree);
 	public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseSubTaskForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
 		   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
 		   Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long justUpperLvl);
 	public List<Long> getStateAndDistrictWorkLocationGovtDeptScopeWiseSubTaskCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
 		   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
 		   Long districtWorkLocationId, String group,Long statusId,Long govtDeptScopeId,List<Long> calCntrIds);
 	public List<Long> getDivisionWorkLocationGovtDeptScopeWiseSubTaskDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
 		   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
 		   Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long justUpperLvl);
 	 public List<Long> getSubDivisionWorkLocationDeptScopeWiseSubTaskCountDetails(Date fromDate,Date toDate,Long stateId,
 			   List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
 			   Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,
 			   Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long lvlTwo,Long lvlThree);
 	 public List<Long> getDistrictOffcrSubTasksAlertIds(Long govtDeptDesigOffceId,Long govtOffceId,String type,Date fromDate,Date toDate,Long statusId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	 public List<Long> getDistrictOffcerSubTsksAlertIds(Long govtDeptDesigOffceId,Long govtOffceId,String type,Date fromDate,Date toDate,Long statusId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	 public List<Object[]> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIdsList);
 	 public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(Date fromDate, Date toDate, Long stateId, 
 			List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,
 			String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIdsList);
 	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,Long statusId,Date startDate,Date endDate,
 			Long desigIds,Long officerIds,Long levelId,List<Long> levelValues,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
 	public List<Long> getStateLevelAlertclickViewAlertIds(List<Long> govtDepDesigOffcrIds,
			List<Long> govtOffcrIds,String type,List<Long> deptIds,Long statusId,Date startDate,Date endDate);
 	public List<Long> getSubTasksIdsList(List<Long> alertIds);
 	public Long getGovtDeptDesigOfficerIdBySubTaskId(Long subTaskId);
 	public List<Long> getStateLevelAssignedAlertClickViewAlertIds(List<Long> govtDepDesigOffcrIds,
			List<Long> govtOffcrIds,String type,List<Long> deptIds,Long statusId,Date fromDate,Date endDate);
 	
 	public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,Long statusId,
			Date fromDate,Date toDate,Long levelId,List<Long> levelValues,List<Long> printIdList,List<Long> electronicIdList,List<Long> calCntrIdList);
 	public List<Object[]> getGovtDeptDesigOfficerIdsListBySubTaskId(List<Long> subTaskIdsList);
 	public List<Long> getAssignedSubTaskOfficerIdsDtls(Long govtAlertSubTaskId);
 	public Object[] getSubTaskDetailsForSMS(Long govtAlertSubTaskId);
 	//Santosh
 	public List<Object[]> getSubTaskAlertCntBasedOnDepartmentLevel(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
			   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
			    String group,String searchType,List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue);
 	public List<Object[]> getSubTaskAlertLocationLevelWise(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
			   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
			   List<Long> calCntrIds);
 	public List<Object[]> getSubTaskChildLocationByParentLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
			   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue, Long childLevelId,List<Long> deptScopeIdList,
			   List<Long> calCntrIds);
 	public List<Long> getSubTaskAlertIdsBasedOnLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
			   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long locationValue,Long deptLevelId
			   ,Long statusId,List<Long> calCntrIds,Long alertCategoryId);
 	public List<Object[]> getSubOrdinateFilterSubTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId);
 	 public List<Object[]> getSubTaskStatusIds(Set<Long> alertSet);
}
