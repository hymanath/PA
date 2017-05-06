package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.FilterSectionVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IAlertManagementSystemService {
	public List<AlertVO> getStatusWiseAlertOverviewcnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList);
	public List<AlertVO> getLevelWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList);
	public List<AlertVO> getDepartmentWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> alertStatusIds,List<Long> departmentScopeIds,String resultType,List<Long> calCntrIds);
	public List<AlertVO> getDepartmentStatus();
	public List<AlertVO> getDepartmentScope();
	public List<IdAndNameVO> getDeptListForUser(Long userId);
	public DistrictOfficeViewAlertVO getDistrictOfficerAlertsCountView(Long userId,String startDate,String endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public ResultStatus updateComment(Long alertId,String comment,Long userId);
	public ResultStatus updateAlertPriority(Long alertId,Long priorityId,Long userId);
	public List<IdNameVO> getDepartmentLevels(Long departmentId);
	public List<IdNameVO> getParentLevelsOfLevel(Long departmentId,Long levelId);
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue);
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public String assigningAlertToOfficer(final AlertAssigningVO inputvo);
	public String assigningSubTaskToOfficer(final AlertAssigningVO inputvo);
	public IdNameVO getGovtSubLevelInfo(Long departmentId,Long LevelId,Long levelValue);
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList);
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList);
	public  List<AlertVO> getDistrictLevelDeptWiseFilterView(Long scopeId,String startDateStr,String fromDateStr,String type);
	public  List<AlertVO> getDistrictLevelDeptWiseStatusOverView(Long scopeId,String startDateStr,String fromDateStr,String type,List<Long> deptIds,String sortingtype,Long levelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public  List<AlertVO> getDistrictLevelDeptWiseLocationLevelView(Long scopeId,String startDateStr,String fromDateStr,String type,Long deptId,String sortingType,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList);
	public List<AlertVO> getGovtDepartmentDetails(Long userId);
	public List<AlertVO> getGovtDeptScopeDetails(Long departmentId,Long userId);
	public  List<IdAndNameVO> getSubOrdinateLevels(Long designationId);
	public List<AlertCoreDashBoardVO> groupAlertsTimeWise(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs);
	public List<DistrictOfficeViewAlertVO> getSubOrdinateAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
			List<Long> desigIds,Long priorityId);
	public ResultStatus updateAlertDueDate(Long alertId ,String date,Long userId);
	public ResultStatus updateAlertStatusComment(Long alertId,Long statusId,String comment,Long userId);
	public ResultStatus uploadDocumentsForAlert(final Map<File, String> mapfiles,final Long alertId,final Long userId);
	public List<AlertTrackingVO> viewAlertHistory(Long alertId);
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortType,String order);
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseFlterClick(Long scopeId,Long deptId,Long locatonLevelId,Long statusId,
			String type,String fromDateStr,String endDateStr,Long desigIds,Long officerIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<IdNameVO> getDepartmentSubLevels(Long departmentId,Long parentLevelId);
	public List<IdNameVO> getChildLevelValuesForSubTask(Long departmentId,Long parentLevelId,List<Long> parentLevelValues,Long levelId);
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType);
	public List<IdNameVO> getDeptListForMultiLvl(Long userId);
	public List<AlertCoreDashBoardVO> getDistrictOfficerAlertDetails(List<Long> govtDeptGovtOffrIds,List<Long> govtOffrcrIds,String countType,String alertType, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,String fromDate,String toDate);
	public List<AlertTrackingVO> getAlertStatusHistory(Long alertId);
	public List<GovtDepartmentVO> getAssignedOfficersDetails(Long alertId);
	public List<AlertCoreDashBoardVO> getDistrictOfficerScopesWiseAlerts(String fromDateStr, String toDateStr, Long stateId, Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType);
	public List<AlertVO> getAllDivisionDetails(Long districtId);  	
	public List<AlertVO> getAllSubDivisionDetails(Long divisionId);  
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountOnClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long locationId, Long childLocationId,String category);
	public ResultStatus updateSubTaskComment(final Long subTaskId,final String comment,final Long userId);
	public ResultStatus updateSubTaskStatusComment(Long subTaskId,final Long statusId,final String comment,final Long userId);
	public ResultStatus updateSubTaskPriority(final Long subTaskId,final Long priorityId,final Long userId);
	public ResultStatus updateSubTaskDueDate(final Long subTaskId,final String dueDate,final Long userId);
	public ResultStatus uploadDocumentsForSubTask(final Map<File, String> mapfiles,final Long subTaskId,final Long userId);
	public List<AlertTrackingVO> viewSubTaskHistory(Long subTaskId);
	public List<AlertTrackingVO> getSubTaskStatusHistory(Long subTaskId);
	public List<AlertTrackingVO> getSubTaskInfoForAlert(Long alertId,Long userId);
	public List<AlertTrackingVO> getCommentsForAlert(Long alertId);
	public List<IdNameVO>  getStatusCompletionInfo(Long alertId,Long levelValue,Long designationId,Long levelId,Long userId);
	public AlertVO getSubTaskFullDetails(Long subTaskId);
	public List<AlertVO> stateLevelDeptOfficerStatusOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList);
	public List<AlertVO> stateLevelDeptOfficerLocationLevelOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList);
	public DistrictOfficeViewAlertVO getIASOfficerMyAlertsCountView(Long userId,String startdateStr,String endDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList);
	public DistrictOfficeViewAlertVO getIASOfficerMyAssignedSubTasksCountView(Long userId,String startDateStr,String endDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList);
	public DistrictOfficeViewAlertVO getIASOfficerMySubTasksCountView(Long userId,String startDateStr,String endDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList);
	
	public List<IdNameVO> getDistIdListForDistFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList);
	public List<IdNameVO> getDistIdListForDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList);
	public List<IdNameVO> getDivisionIdListForDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList);
	public List<IdNameVO> getDistrictIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String group,String alertType,String searchType,List<Long> calCntrIdList);
	public List<IdNameVO> getDivisionIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList);
	public List<IdNameVO> getSubDivisionIdListForSubDivisionFilter(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long districtWorkLocationId,Long divisionWorkLocationId,String group,String alertType,String searchType,List<Long> calCntrIdList);
	public List<AlertVO> getAllDistrictDetails(Long departmentId);
	public List<KeyValueVO> getDocumentsForAlert(Long alertId);
	public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, 
			List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
			Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,String searchType,List<Long> sublevels,List<Long> calCntrIdList);
	public List<AlertCoreDashBoardVO>  getDistrictLevelWiseClick(String fromDateStr, String toDateStr, Long stateId, 
	           List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
	           Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
	           Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String group,String searchType,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIdList,List<Long> sublevels); 
	public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsView(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIds);
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseAlertClick(List<Long> govtDeptDesigOffceIds,List<Long> govtOffceIds,Long statusId,String formDateStr,String toDateStr,String clickType,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<AlertVO> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIdsList);
	public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIdsList);
	public List<GrievanceAlertVO> getGovtGrievanceAlertDetails(String mobileNo,String locatoinType,Long locationId,String fromDateStr,String toDateStr,Long statusId);
	public FilterSectionVO getFilterSectionAlertDetails(Long userId,List<Long> deptIdList);
	public String getDesignationForUser(Long userId);
	public DistrictOfficeViewAlertVO getDeptDetails(Long userId);
	public List<AlertCoreDashBoardVO> getStateLevelAlertclickView(List<Long> deptIds,Long statusId,String type,List<Long> govtDeptGovtOffrIds,List<Long> govtOffrcrIds,String searchType,String startdateStr,String endDateStr);
	public List<AlertCoreDashBoardVO> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long deptScopeId, Long statusId,List<Long> calCntrIds);
	public List<AlertTrackingVO> getSubTaskDetails(Long alertId);
	public List<IdNameVO> getStatusCompletionInfoForSubTask(Long alertId,Long subTaskId,Long userId);
	public List<AlertCoreDashBoardVO> getStateLevelDeptWiseFlterClick(Long userId,List<Long> deptIds,Long locatonLevelId,
			Long statusId,String type,String fromDateStr,String toDateStr,
			Long desigDeptOfficerId,Long officerId, List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,
			Long stateId,String levelType,String assignType);
	public List<IdNameVO> getGovtAllDepartmentDetails();
	public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamic(String fromDateStr, String toDateStr, Long stateId, 
			List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
			Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,List<Long> calCntrIdList,List<Long> sublevels);
	public List<IdNameVO>  getStatusCompletionInfoNew(Long alertId,Long levelValue,Long designationId,Long levelId,Long userId);
	public String getOfficernameDesignationForUser(Long userId);
	
	 //Santosh
	 public List<IdNameVO> getLocationBasedOnDepartmentLevel(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String alertType,List<Long> calCntrIdList);
	 public List<IdNameVO> getChildLocationBasedOnParentLocation(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue,Long childLevelId,String alertType ,List<Long> calCntrIdList);
	 public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamicNew(String fromDateStr, String toDateStr, Long stateId,List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, 
				String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,Long filterScopeValue,String searchType);
	 public List<AlertCoreDashBoardVO> getAlertDetailsBasedOnLocation(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long deptScopeId, Long alertStatusId,List<Long> calCntrIds,Long locationValue,String alertType,Long alertCategoryId);
	 public List<IdNameVO> getDepartmentDetailsByDepartmentId(Long userId,Long departmentId,String designationType);
	 public Map<String,List<String>> getMonthWeekAndDays(String startDate,String endDate,String type);
	 public List<IdNameVO> getDepartmentDetailsOfAlert(Long alertId);
	 public List<DistrictOfficeViewAlertVO> getSubOrdinateFilterAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
				List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> deptIds , Long lagStartCnt, Long lagEndCnt,String alertType,String isMoreThanYrChkd,String isLagChkd,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId);
	 public List<IdNameVO> getLvlsForDepatmnt(Long userId,Long departmentId);
	 public List<IdNameVO> getStatusByType(String type);
	 public List<IdNameVO> getDeptListForGreivanceReport(Long userId);
	 public List<AlertCoreDashBoardVO> getTotalAlertByStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd);
	 public FilterSectionVO getFilterSectionAlertNewDetails(Long userId,List<Long> deptIdList);
	 public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd);
    public List<AlertVO> getAlertSourceWiseAlert(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,String userType);
	 public List<AlertCoreDashBoardVO> getAlertDtlsByAlertSource(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,Long alertCategoryId,String userType);
}        
