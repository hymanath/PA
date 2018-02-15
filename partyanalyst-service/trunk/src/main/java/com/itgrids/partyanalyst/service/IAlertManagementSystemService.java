package com.itgrids.partyanalyst.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.itgrids.partyanalyst.dto.AlertAssigningVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertTrackingVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertsSummeryVO;
import com.itgrids.partyanalyst.dto.AmsAppLoginVO;
import com.itgrids.partyanalyst.dto.AmsAppVO;
import com.itgrids.partyanalyst.dto.AmsDataVO;
import com.itgrids.partyanalyst.dto.AmsKeyValueVO;
import com.itgrids.partyanalyst.dto.AmsTrackingVO;
import com.itgrids.partyanalyst.dto.AmsVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.FilterSectionVO;
import com.itgrids.partyanalyst.dto.GovtDepartmentVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.JalavaniAlertsInputVO;
import com.itgrids.partyanalyst.dto.JalavaniVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LocationAlertVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface IAlertManagementSystemService {
	public List<AlertVO> getStatusWiseAlertOverviewcnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertVO> getLevelWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertVO> getDepartmentWiseAlertOverviewCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> alertStatusIds,List<Long> departmentScopeIds,String resultType,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertVO> getDepartmentStatus();
	public List<AlertVO> getDepartmentScope();
	public List<IdAndNameVO> getDeptListForUser(Long userId);
	public DistrictOfficeViewAlertVO getDistrictOfficerAlertsCountView(Long userId,String startDate,String endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public ResultStatus updateComment(Long alertId,String comment,Long userId);
	public ResultStatus updateAlertPriority(Long alertId,Long priorityId,Long userId);
	public List<IdNameVO> getDepartmentLevels(Long departmentId);
	public List<IdNameVO> getParentLevelsOfLevel(Long departmentId,Long levelId);
	public List<GovtDepartmentVO> getDesignationsByDepartment(Long departmentId,Long levelId,Long levelValue);
	public List<GovtDepartmentVO> getOfficersByDesignationAndLevel(Long levelId,Long levelValue,Long designationId);
	public String assigningAlertToOfficer(final AlertAssigningVO inputvo);
	public String assigningSubTaskToOfficer(final AlertAssigningVO inputvo);
	public IdNameVO getGovtSubLevelInfo(Long departmentId,Long LevelId,Long levelValue);
	public List<AlertCoreDashBoardVO> getTotalAlertByStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatus(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public  List<AlertVO> getDistrictLevelDeptWiseFilterView(Long scopeId,String startDateStr,String fromDateStr,String type);
	public  List<AlertVO> getDistrictLevelDeptWiseStatusOverView(Long scopeId,String startDateStr,String fromDateStr,String type,List<Long> deptIds,String sortingtype,Long levelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public  List<AlertVO> getDistrictLevelDeptWiseLocationLevelView(Long scopeId,String startDateStr,String fromDateStr,String type,List<Long> deptIds,String sortingType,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,String resultType,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertVO> getGovtDepartmentDetails(Long userId);
	public List<AlertVO> getGovtDeptScopeDetails(List<Long> departmentIds,Long userId);
	public  List<IdAndNameVO> getSubOrdinateLevels(Long designationId);
	public List<AlertCoreDashBoardVO> groupAlertsTimeWise(List<AlertCoreDashBoardVO> alertCoreDashBoardVOs);
	public List<DistrictOfficeViewAlertVO> getSubOrdinateAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
			List<Long> desigIds,Long priorityId);
	public ResultStatus updateAlertDueDate(Long alertId ,String date,Long userId);
	public ResultStatus updateAlertStatusComment(Long alertId,Long statusId,String comment,Long userId,Long proposalCategoryId,String proposalAmount,Long rejoinderActionTypeId);
	public ResultStatus uploadDocumentsForAlert(final Map<File, String> mapfiles,final Long alertId,final Long userId);
	public List<AlertTrackingVO> viewAlertHistory(Long alertId);
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCount(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortType,String order);
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseFlterClick(Long scopeId,Long deptId,Long locatonLevelId,List<Long> statusIds,
			String type,String fromDateStr,String endDateStr,Long desigIds,Long officerIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long alertCategoryId,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<IdNameVO> getDepartmentSubLevels(Long departmentId,Long parentLevelId);
	public List<IdNameVO> getChildLevelValuesForSubTask(Long departmentId,Long parentLevelId,List<Long> parentLevelValues,Long levelId);
	public List<AlertCoreDashBoardVO> getStateThenGovtDeptScopeWiseAlertCountStatusWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType);
	public List<IdNameVO> getDeptListForMultiLvl(Long userId,Set<Long> deptSet);
	public List<AlertCoreDashBoardVO> getDistrictOfficerAlertDetails(List<Long> govtDeptGovtOffrIds,List<Long> govtOffrcrIds,String countType,String alertType, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,String fromDate,String toDate,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds, List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
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
	public List<AlertVO> stateLevelDeptOfficerStatusOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverity,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertVO> stateLevelDeptOfficerLocationLevelOverview(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public DistrictOfficeViewAlertVO getIASOfficerMyAlertsCountView(Long userId,String startdateStr,String endDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public DistrictOfficeViewAlertVO getIASOfficerMyAssignedSubTasksCountView(Long userId,String startDateStr,String endDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public DistrictOfficeViewAlertVO getIASOfficerMySubTasksCountView(Long userId,String startDateStr,String endDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSubTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	
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
	public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsView(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIds,List<Long> socialMediaTypeids,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertCoreDashBoardVO> getDistrictLevelDeptWiseAlertClick(Long userId,List<Long> govtOffceIds,List<Long> statusIds,String formDateStr,String toDateStr,String clickType,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeids,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertVO> stateLevelDeptOfficerLocationLevelOverviewBySubTasks(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIdsList,List<Long> socialMediaTypeids,List<Long> subTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertVO> stateLevelDeptOfficerDepartmentWiseAlertsViewBySubTasksClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> callCenterIdsList,List<Long> socialMediaTypeids,List<Long> subTaskStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<GrievanceAlertVO> getGovtGrievanceAlertDetails(String mobileNo,String locatoinType,Long locationId,String fromDateStr,String toDateStr,Long statusId,Long deptId);
	public FilterSectionVO getFilterSectionAlertDetails(Long userId,List<Long> deptIdList);
	public String getDesignationForUser(Long userId);
	public DistrictOfficeViewAlertVO getDeptDetails(Long userId);
	public List<AlertCoreDashBoardVO> getStateLevelAlertclickView(List<Long> deptIds,List<Long> statusIds,String type,List<Long> govtDeptGovtOffrIds,List<Long> govtOffrcrIds,String searchType,String startdateStr,String endDateStr,List<Long> printIdList, List<Long> electronicIdList,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertCoreDashBoardVO> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long deptScopeId, Long statusId,List<Long> calCntrIds);
	public List<AlertTrackingVO> getSubTaskDetails(Long alertId);
	public List<IdNameVO> getStatusCompletionInfoForSubTask(Long alertId,Long subTaskId,Long userId);
	public List<AlertCoreDashBoardVO> getStateLevelDeptWiseFlterClick(Long userId,List<Long> deptIds,Long locatonLevelId,
			List<Long> statusIds,String type,String fromDateStr,String toDateStr,
			Long desigDeptOfficerId,Long officerId, List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,
			Long stateId,String levelType,String assignType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<IdNameVO> getGovtAllDepartmentDetails();
	public List<AlertCoreDashBoardVO> getWorkLocationWiseThenGovtDeptScopeWiseAlertCountForOverviewDynamic(String fromDateStr, String toDateStr, Long stateId, 
			List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
			Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
			Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, String group,List<Long> calCntrIdList,List<Long> sublevels);
	public List<IdNameVO>  getStatusCompletionInfoNew(Long alertId,Long levelValue,Long designationId,Long levelId,Long userId,List<String> entitlements);
	public String getOfficernameDesignationForUser(Long userId);
	
	 //Santosh
	 public List<IdNameVO> getLocationBasedOnDepartmentLevel(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String alertType,List<Long> calCntrIdList,List<Long> subLevelIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,String type,Long childLevelId);
	 public List<IdNameVO> getChildLocationBasedOnParentLocation(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue,Long childLevelId,String alertType ,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,String type,Long resultLevelDeptScopeId);
	 public List<AlertCoreDashBoardVO> getLocationWiseDepartmentOverviewAlertCount(String fromDateStr, String toDateStr, Long stateId,List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, 
				String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,Long filterScopeValue,String searchType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds );
	 public List<AlertCoreDashBoardVO> getAlertDetailsBasedOnLocation(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,Long deptScopeId,List<Long> alertStatusIds,List<Long> calCntrIds,Long locationValue,String alertType,Long alertCategoryId,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long govtOfficerId);
	
	 public List<IdNameVO> getDepartmentDetailsByDepartmentId(Long userId,Long departmentId,String designationType);
	 public Map<String,List<String>> getMonthWeekAndDays(String startDate,String endDate,String type);
	 public List<IdNameVO> getDepartmentDetailsOfAlert(Long alertId);
	 public List<DistrictOfficeViewAlertVO> getSubOrdinateFilterAlertsOverview(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
				List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> deptIds , Long lagStartCnt, Long lagEndCnt,String alertType,String isMoreThanYrChkd,String isLagChkd,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<IdNameVO> getLvlsForDepatmnt(Long userId,Long departmentId);
	 public List<IdNameVO> getStatusByType(String type);
	 public List<IdNameVO> getDeptListForGreivanceReport(Long userId);
	 public List<AlertCoreDashBoardVO> getTotalAlertByStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationIdList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd,String isLagChkd,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public FilterSectionVO getFilterSectionAlertNewDetails(Long userId,List<Long> deptIdList);
	 public List<AlertCoreDashBoardVO> getTotalAlertByOtherStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd,String isLagChkd,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds
			 ,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<AlertVO> getAlertSourceWiseAlert(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,String userType,List<Long> socailMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<AlertCoreDashBoardVO> getAlertDtlsByAlertSource(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,Long alertCategoryId,String userType,List<Long> alertStatusIds,List<Long> socialMediaTyepIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);	 
	 public List<DistrictOfficeViewAlertVO> getBellowDistrictOfficerAlertsCountView(Long userId,String startDate,String endDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList, String task,String sortingType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<AlertCoreDashBoardVO> getBellowDistrictOfficerAlertsDtls(String fromDateStr,String toDateStr,List<Long> printIdList,List<Long> electronicIdList,List<Long> calCntrIdList,String task,Long statusId,Long desigDeptOfficerId,Long officerId,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<List<AlertTrackingVO>> viewAlertHistoryNew(Long alertId,String task);
	 public List<AlertCoreDashBoardVO> getTotalAlertBySubTaskStatusNew(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,List<Long> statusIdList,Long userId,Long govtDeptScopeId,Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long startDay,Long endDay,Long scopeId,List<Long> locationList,List<Long> subTaskStatusIdList,String isMoreThanYrChkd,String isLagChkd,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds
			 ,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<AlertCoreDashBoardVO> getOfficerLocationWiseDepartmentOverviewAlertCount(String fromDateStr, String toDateStr, Long stateId, 
				List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
				String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,
				Long filterScopeValue,String searchType,Long source);
	 public List<AlertCoreDashBoardVO> getLocationFilterClickDetails(Long userId,String fromDateStr,String toDateStr , List<Long> govtScopeIds,List<Long> locationValues,
				List<Long> desigIds,Long priorityId,List<Long> statusIds ,List<Long> deptIds, Long lagStartCnt, 
				Long lagEndCnt,String alertType,String isMoreThanYrChkd,String isLagChkd,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,Long childLevelId,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<IdAndNameVO> getSocialMediaTypeList();
	 public Long getSearchAlertsDtls(Long userId,Long alertId);
	 public List<IdAndNameVO> getAlertCallCenterType();
	 public ResultStatus sendSMSTOAlertAssignedOfficer(Long designationId,Long govtOfficerId,String mobileNo,Long alertId,Long actionTypeId,Long userId,String status,String comment,Long mainUserId);
	 public List<AlertCoreDashBoardVO> getAlertDetailsForGrievanceReportClick(String fromDateStr, String toDateStr, Long stateId, 
				List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
				String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,
				Long filterScopeValue,String searchType,Long statusId,Long sourseId);
	 public List<IdAndNameVO> getAlertSeverity();
	 public List<IdAndNameVO> getAlertStatus();
	 public List<IdAndNameVO> getSubTaskAlertStatus();
	 public List<IdNameVO> getAllMainDepartments();
	 public String changeDepartmentStatusToAlert(final Long alertId,final Long changedDeptId,final Long userId);
	 public List<AlertsSummeryVO> getCadreGreivienceEfficiency(String fromDateStr, String toDateStr, Long stateId, 
				List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				Long parentGovtDepartmentScopeId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> sublevels,Long source,List<Long> statusIdList,int rangeValue);
	 public List<IdAndNameVO> getMondayGrievanceTypeList();
	 public List<IdAndNameVO> getJanmabhoomiTypeList();
	 public List<IdAndNameVO> getSpecialGrievanceTypeList();
	 public List<IdAndNameVO> getGeneralGrievanceTypeList();
	 public List<IdNameVO> getSubDeptsFrParentDept(Long parentDeptId);
	 public List<IdNameVO> getPresentAssignedDepartmentOfAlert(Long alertId);
	 public List<AlertVO> getFinancialAssistanceAlertCntCategoryWise(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<AlertCoreDashBoardVO> getFinancialAssistanceAlertCntDtls(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long proposalCategoryId,Long proposalStatusId);
	 public String updateProposalStatusFrAlert(final Long userId,final Long alertId,final Long proposalStatusId,final String comment,final String approvedAmount);
	 public String alertDeptmentExistInLogin(Long alertId,Long userId);
	 public List<AlertVO> getDepartmentWiseProposalAlertCnt(String fromDateStr, String toDateStr, Long stateId, List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long userId,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public List<AlertCoreDashBoardVO> getOfficerWiseAlertCntBasedOnDepartmentScopeLevel(String fromDateStr, String toDateStr, Long stateId,List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,
				List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds );
	 public String getOfficerMobilenNo(Long userId);
	 public String generatingAndSavingOTPDetails(String mobileNoStr);
	 public String getOfficerOtpStatus(Long userId,String otp);
	 
	 public List<AmsKeyValueVO> getDeptListForUserForAms(Long userId);
	 public List<AmsKeyValueVO> getSocialMediaTypeListForAms();
	 public List<AmsKeyValueVO> getAlertCallCenterTypeForAms();
	 public List<AmsKeyValueVO> getMondayGrievanceTypeListForAms();
	 public List<AmsKeyValueVO> getJanmabhoomiTypeListForAms();
	 public List<AmsKeyValueVO> getSpecialGrievanceTypeListForAms() ;
	 public List<AmsKeyValueVO> getGeneralGrievanceTypeListForAms();
	 public List<AmsKeyValueVO> getAlertSeverityForAms();
	 public List<AmsKeyValueVO> getAlertStatusForAms();
	 public List<AmsKeyValueVO> getSubTaskAlertStatusForAms();
	 public DistrictOfficeViewAlertVO getAmsAppAlertsBasicCounts(Long userId,String fromDateStr,String toDateStr,List<Long> printIdList, 
			 List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
			 List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,
			 List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,List<Long> subTaskAlertStatusIds);
	 public List<AmsDataVO> getOfficerAlertDetails(Long userId,String countType,String alertType,
				List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,String fromDateStr,String toDateStr,List<Long>
				socialMediaTypeIds,List<Long> alertSeverityIds, List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,
				List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public ResultStatus uploadDocumentsForRejoinderStatus(final StringBuilder pathBuilder,final Long alertId,final Long userId);
	 public List<AlertVO> getRejoinderDocumentsForAlert(Long alertId);
	 public AmsVO getAlertDetailsInfoForAms(AmsAppLoginVO keyVo);
	 public List<AmsTrackingVO> getSubTaskInfoForAlertForAms(AmsAppLoginVO VO);
	 public List<AmsAppVO> getStatusCompletionInfoForSubTaskForAms(AmsAppLoginVO inputVo);
	 public List<AmsKeyValueVO> getGovtAllDepartmentDetailsForAms();
	 public List<AmsKeyValueVO> getSubDeptsFrParentDeptForAms(AmsAppLoginVO keyVo);
	 public List<AmsKeyValueVO> getDepartmentLevelsForAms(AmsAppLoginVO keyVo);
	 public List<AmsKeyValueVO> getParentLevelsOfLevelForAms(AmsAppLoginVO keyVo);
	 public List<AmsKeyValueVO> getDesignationsByDepartmentForAms(AmsAppLoginVO keyVo);
	 public List<AmsKeyValueVO> getOfficersByDesignationAndLevelForAms(AmsAppLoginVO keyVo);
	 public ResultStatus updateAlertStatusCommentForAms(final AmsAppLoginVO keyVo);
	 public List<DistrictOfficeViewAlertVO> getLocationWiseDepartmentOverviewAlertCountForAms(String fromDateStr, String toDateStr, Long stateId, 
				List<Long> printIdList, List<Long> electronicIdList,Long userId, Long govtDepartmentId, 
				Long parentGovtDepartmentScopeId,String sortingType, String order,String alertType,
				Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId, 
				String group,List<Long> calCntrIdList,List<Long> sublevels,Long filterParentScopeId,
				Long filterScopeValue,String searchType,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds );
	 public ResultStatus saveDocumentsForAlertForAms(final AmsAppLoginVO keyVo);
	 public List<IdNameVO> getAlertDetailsOfCategoryByStatusWise(JalavaniVO VO);
	 public List<IdNameVO> getAlertFeedbackStatusDetails(JalavaniVO VO);
	 public List<AmsDataVO> getAlertsOfCategoryByStatusWise(JalavaniVO mainVo);
	 public List<KeyValueVO> getLocationWiseAlertStatusCounts(Long departmentId,String fromDateStr,String toDateStr,String year,Long locationTypeId,
 			 List<Long> locationValues,Long searchLevelId,List<Long> searchLevelValues);
	 public String validateOTP(String mobileNo,String otp);
	 public String updateMobileNo(final Long userId, final String otp,final String mobileNo);
	 public List<AlertVO> getHamletWiseIvrStatusCounts(String fromDate,String toDate,String year,List<Long> locationValues,Long locationTypeId,
			 Long searchLevelId,List<Long> searchLevelValues);
	 public AmsKeyValueVO getDistrictWiseInfoForAms(Long departmentId,Long LevelId,Long levelValue);
	 public List<DistrictOfficeViewAlertVO> getTotalAlertByOtherStatusForAMS(String fromDateStr, String toDateStr, Long stateId,
			 List<Long> printIdList, List<Long> electronicIdList, List<Long> deptIdList,Long statusId,Long userId,Long govtDeptScopeId,
			 Long deptId,List<Long> calCntrIdList,List<Long> impactLevelIdList,List<Long> priorityIdList,
			 List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,
			 List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,
			 List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	 public  LocationAlertVO getTotalAlertDetailsForConstituencyInfo(String fromDateStr ,String toDateStr,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year);
	 public List<AlertVO> getHamletWiseIvrStatusList(String fromDateStr,String toDateStr,String year,List<Long> locationValues,Long locationTypeId,String statusType);
	 public List<AlertVO> getDrainsIvrStatusCounts(String fromDateStr,String toDateStr,List<Long> locationValues,Long locationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues,Long entityType,List<Long> questionsList,List<String> selectedDatesStr);
	 public List<AlertVO> getOverAllIvrDetails(String fromDateStr,String toDateStr,Long entityType,List<Long> questionsList,String type);
	 public List<IdNameVO> getIvrSurveyDates(String fromDateStr,String toDateStr,Long entityType);
	 public List<IdNameVO> getIvrSurveyQuestions(String fromDateStr,String toDateStr,Long entityType);
	 public AlertVO getJalavaniDashBoardViewInfo(JalavaniAlertsInputVO inputVo);
	 public AlertVO getJalavaniCategoryWiseDetailsInfo(JalavaniAlertsInputVO inputVo);
}        
