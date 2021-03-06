package com.itgrids.partyanalyst.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GovtAlertSubTask;

public interface IGovtAlertSubTaskDAO extends GenericDao<GovtAlertSubTask, Long> {
	
	public List<Object[]> getDistrictOfficerAlertsSubTasksCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> subTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getSubTaskCount(List<Long> alertIds);
 	public List<Object[]> getSubTaskAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate);
 	public List<Object[]> getDistrictLevelDeptWiseStatusOverViewForSubTask(Date fromDate, Date toDate,Long scopeId,List<Long> deptIds,Long levelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> levelValues,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getDistrictLevelDeptWiseLocationLevelViewForSubtask(Date fromDate, Date toDate,List<Long> deptIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long levelId,List<Long> levelValues,String type,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getSubOrdinateTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,String type);
 	 public List<Long> getDistrictOfficerSubTasksAlertIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String countType,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,Date formDate,Date toDate,List<Long> socialMediaTypeIds,List<Long> alertSubtaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
  	public List<Object[]> getDistrictOfficerSubTaskAlerts(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
 			Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,String type);
	 public Integer updateSubTaskPriority(Long subTaskId,Long id,Long userId,Date date);
 	public Integer updateSubTaskDueDate(Long subTaskId,Date dueDate,Long userId,Date date);
 	public List<Object[]> getSubTaskInfoForAlert(Long alertId);
 	//public List<Long> getDistrictOfficerAlertsDetails(Date fromDate,Date toDate,Long stateId,Long levelId,List<Long> levelValues,
		        //Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long govtDeptWorkLocId,Long statusId,Long childGovtScopeId);
 	public List<Object[]> getDistrictOfficerMyAssignedSubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getDistrictOfficerMySubTasksStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getDistrictOfficerMyAssignedSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getDistrictOfficerAlertsSubTasksCountsView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
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
 	 public List<Long> getDistrictOffcrSubTasksAlertIds(List<Long> govtDeptDesigOffceIds,List<Long> govtOffceIds,String type,Date fromDate,Date toDate,List<Long> alertSubstatusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<Long> getDistrictOffcerSubTsksAlertIds(List<Long> govtDeptDesigOffceIds,List<Long> govtOffceIds,String type,Date fromDate,Date toDate,List<Long> alertSubTaskStatusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<Object[]> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIdsList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	 public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(Date fromDate, Date toDate, Long stateId, 
 			List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,
 			String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIdsList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,List<Long> statusIds,Date startDate,Date endDate,
 			Long desigIds,Long officerIds,Long levelId,List<Long> levelValues,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long alertCategoryId,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Long> getStateLevelAlertclickViewAlertIds(List<Long> govtDepDesigOffcrIds,
			List<Long> govtOffcrIds,String type,List<Long> deptIds,List<Long> statusIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Long> getSubTasksIdsList(List<Long> alertIds);
 	public Long getGovtDeptDesigOfficerIdBySubTaskId(Long subTaskId);
 	public List<Long> getStateLevelAssignedAlertClickViewAlertIds(List<Long> govtDepDesigOffcrIds,
			List<Long> govtOffcrIds,String type,List<Long> deptIds,List<Long> statusIds,Date fromDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	
 	public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,List<Long> statusIds,
			Date fromDate,Date toDate,Long levelId,List<Long> levelValues,List<Long> printIdList,List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getGovtDeptDesigOfficerIdsListBySubTaskId(List<Long> subTaskIdsList);
 	public List<Long> getAssignedSubTaskOfficerIdsDtls(Long govtAlertSubTaskId);
 	public Object[] getSubTaskDetailsForSMS(Long govtAlertSubTaskId);
 	//Santosh
 	public List<Object[]> getSubTaskAlertCntBasedOnDepartmentLevel(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
			   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
			    String group,String searchType,List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getSubTaskAlertLocationLevelWise(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
			   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
			   List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long childLevelId);
 	public List<Object[]> getSubTaskChildLocationByParentLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
			   List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue, Long childLevelId,List<Long> deptScopeIdList,
			   List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long resultlevelDeptScopeId);
 	public List<Long> getSubTaskAlertIdsBasedOnLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
			   Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,Long locationValue,Long deptLevelId
			   ,List<Long> alertSubTaskStatusIds,List<Long> calCntrIds,Long alertCategoryId,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long govtOfficerId);
 	public List<Object[]> getSubOrdinateFilterSubTasksDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> deptIds,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	 public List<Object[]> getSubTaskStatusIds(Set<Long> alertSet);
 	public List<Object[]> getBellowDistrictOfficerAlertsSubTasksCount(List<Long> govtDepDesigOffcrIds,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Long> getBellowDistrictOfficerAlertsDtls(Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long statusId,Long govtDepDesigOffcrId,Long officerId,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Long> getLocationFilterClickAlertIds(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
 			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public Long getAlertSubTaskdetails(Long alertId,List<Long> deptsList,List<Long> levelValuesList,Long levelId,Set<Long> govtScopeIds);
 	public List<Long> getTotalAlertBySubTaskStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, List<Long> statusIdList,Long levelId,List<Long> levelValues, Long govtDepartmentScopeId, Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> subTaskStatusIdList
 			,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 	public List<Object[]> getDataAvailableSubTaskStatus();
 	public List<Object[]> getOfficerWiseSubTaskCountBasedOnDepartmentScopeLevel(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,
     		List<Long> alertSeverityIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
 
}
