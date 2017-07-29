package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;

public interface IAlertAssignedOfficerNewDAO extends GenericDao<AlertAssignedOfficerNew, Long> {
	public List<Object[]> getAssignedStatuses();
	public List<Object[]> getDepartmentScope();
	public List<Object[]> getAlertCntByRequiredType(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<AlertAssignedOfficerNew> getModelForAlert(Long alertId);
	public List<Object[]> getDistrictOfficerAlertsCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatuIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Long> getAlertAssignedOfficerId(Long alertId);
	public List<Long> getTotalAlertByOtherStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, Long statusId,Long levelId,List<Long> levelValues,Long govtDeptScopeId,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate);
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelView(Date fromDate, Date toDate,List<Long> deptIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long accessLevelId,List<Long> levelValues,String type,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long scopeId,List<Long> deptIds,Long levelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> levelValues,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	//public List<Object[]> getAlertDetailsForDistrictOfficer(Date fromDate, Date toDate, Long stateId, List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,String dateType);
	public List<Object[]> getSubOrdinateAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
	List<Long> desigIds,Long priorityId,String type);
	public List<Long> getDistrictOfficerAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,Date formDate,Date todDate,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getLocationThenGovtDeptScopeWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdsList,List<Long> printIdsList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList);
	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,List<Long> statusIds,Date fromDate,
			Date toDate,Long desigDeptOfficerId,Long officerId,Long levelId,List<Long> levelValues,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long alertCategoryId,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getLocationThenGovtDeptScopeWiseAlertCountForStatus(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList);
	public List<Object[]> getDeptList(Long userId);
	public List<Object[]> getDistrictOfficerScopesWiseAlerts(Date fromDate,Date toDate,Long stateId,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList);
	public List<Object[]> getAssignedOfficersDetails(Long alertId);
	public List<Long> getLocationThenGovtDeptScopeWiseAlertCountOnClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,Long parentGovtDepartmentScopeId,Long locationId,Long childLocationId);
	public List<Long> getLocationThenGovtDeptScopeWiseAlertCountForStatusForClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,Long parentGovtDepartmentScopeId,Long locationId,Long statusId);
	public List<Object[]> getDesignationOfficerDetails(Long alertId);
	public List<Object[]> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,String group,String searchType,List<Long> calCntrIds);
	public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long justUpperLvl);
	public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds,Long lvlTwo,Long lvlThree);
	public List<Object[]> getDistrictOfficerMyAlertsCountView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getDistrictOfficerMyAlertsStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Long> getDistrictOfficerAlertsDetails(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId, String group,Long statusId,Long govtdeptScopeId,List<Long> calCntrIdList);
	public List<Long> getDivisionWorkLocationGovtDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
    		List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,
    		List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long justUpperLvl);
	public List<Long> getSubDivisionWorkLocationDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
    		Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
    		Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long lvlTwo,Long lvlThree);
	
	public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsView(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Long> getDistrictOffrAlertsIds(List<Long> govtDeptDesigOffceIds,List<Long> govtOffceIds,Date formDateStr,Date toDateStr,List<Long> alertStatusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Long> getGovtDepartmentDesignationOfficer(Long alertId);
	public List<Long> getStateLevelAlertclickViewAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,
			 String type,List<Long> deptIds,List<Long> statusIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> callCenterIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public Long getGovtDeptScopeIdForAlert(Long alertId);
	public List<Long> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,Long deptScopeId, Long statusId,List<Long> calCntrIds);  
	public Long getGovtDeptDesigOfficerIdListByUserId(Long alertId);
	public Long getGovtDeptDesigIdListByUserId(Long alertId);
	public Integer deleteAssignment(Long alertId);
	public List<Long> getAssignedDtls(Long alertId);
	public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,List<Long> statusIds,
			Date fromDate,Date toDate,Long levelId,List<Long> levelValues, List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
	public List<Object[]> getAlertAssignedLevelDetails(Long alertId);
	//santosh
	public List<Object[]> getLocationBasedOnDepartmentLevelId(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long childLevelId);
    public List<Object[]> getChildLocationBasedOnParentLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
     		Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,
     		Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue, Long childLevelId,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long resultLevelDeptScopeId);
    public List<Object[]> getAlertDetailsLocationWiseBasedOnDepartmentLevel(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,Long source,String pendingType,List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    public List<Long> getAlertIdsBasedOnRequiredParameter(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     	 Long parentGovtDepartmentScopeId,Long locationValue,List<Long> calCntrIds,Long deptLevelId,
     	 List<Long> alertStatusIds,Long alertCategoryId,List<Long> deptScopeIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long govtOfficerId);
    public List<Object[]> getDepartmentDetaislByDeptIds(Long departmentId);
    public List<Object[]> getDepartmentDetailsOfAlert(Long alertId);
    public List<Object[]> getAllDepartmentHasData(List<Long> deptIds,Long levelId,List<Long> levelValues);
    public List<Object[]> getSubOrdinateFilterAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> deptIds,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    public List<Object[]> getSubOrdinateFilterAlertsDetailsForUser(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,List<Long> govtDepartmentIds,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds,List<Long> filterLocationValues,List<Long> desigIds);
    public List<Long> getTotalAlertByAllAlertStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, List<Long> statusIdList,Long levelId,List<Long> levelValues, Long govtDepartmentScopeId, Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationList,List<Long> filterSocialMediaIds,List<Long> filterCallCenterIdList,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    //public List<Object[]> getSubTaskStatusIds(Set<Long> alertSet);
    public List<Object[]> getBellowLvlDistrictOfficerAlertsCount(List<Long> govtDepDesigOffcrIds,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> alertStatuIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    public List<Long> getAlertCntByAlertCategory(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,List<Long> calCntrIds,Long alertCategoryId,List<Long> alertStatusIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    public List<Long> getBellowDistrictOfficerAlertsDtls(Date fromDate,Date toDate,List<Long> printIdList,List<Long> electronicIdList,List<Long> calCntrIdList,Long statusId,Long desigDeptOfficerId,Long officerId,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    public List<Object[]> getAlertFeedBackDetailsLocationWiseBasedOnDepartmentLevel(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,String queryStatus,Long source,String filterType);
    public List<Object[]> getAlertDetailsLocationWiseBasedOnDepartmentLevelForOfficer(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue);
    public List<Long> getAllReOpenAlerts();
    public List<Long> getLocationFilterClickAlertIds(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
 			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId,List<Long> socialMediaTypeIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    public Long getAlertdetails(Long alertId,List<Long> deptsList,List<Long> levelValues,Long levelId,Set<Long> govtScopeIds);
    public List<AlertAssignedOfficerNew> getModelForApprovedAlert(Long alertId);
    public List<Long> getAlertDetailsForGrievanceReportClick(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,Long statusId,Long sourseId,String feedbackType);
    public List<Long> getAlertFeedBackDetailsForGrievanceReportClick(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue, String reopen,Long source,Long statusId);
    public List<Object[]> getDataAvailableAlertStatus();
    public List<Object[]> getDifferenceTimeList(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds,List<Long> socialMediaTypeIds,Long source,List<Long> alertStatusIds);
    public List<Object[]> getMainDeptAndItsSubDepartment();
    public List<Object[]> getPresentAssignedDepartmentOfAlert(Long alertId);
    public List<Object[]> getAlertProposalCategoryWiseAlertCnt(Date fromDate, Date toDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,
     		List<Long> alertStatusIds,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
     		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,String type);
    public List<Long> getAlertProposalCategoryWiseAlertDtls(Date fromDate, Date toDate, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,
     		List<Long> alertStatusIds,List<Long> calCntrIds,List<Long> socialMediaTypeIds,List<Long> alertSeverityIds,
     		List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,
     		List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds,Long proposalCategoryId,Long proposalStatusId);
    public List<Object[]> getOfficerWiseAlertCountBasedOnDepartmentScopeLevel(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue,List<Long> socialMediaTypeIds,
     		List<Long> alertSeverityIds,List<Long> alertStatusIds,List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds);
    public List<Object[]> getLocationWiseAlertStatusCounts(Long departmentId,Date fromDate,Date toDate,String year,Long groupByValue,List<Long> locationValues);
    public List<Object[]> getHamletWiseIvrStatusCounts(Date fromDate,Date toDate,String year,List<Long> locationValues,Long LocationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues);
    public List<Object[]> getAssignedMemberAlertDetails(Date fromDate,Date toDate,List<Long> locationValues,List<Long> alertTypeIds,Long locationTypeId,String year);
    public List<Object[]> getHamletWiseIvrStatusList(Date fromDate,Date toDate,String year,List<Long> locationValues,Long locationTypeId);
    public List<Object[]> getDrainsIvrStatusCounts(Date fromDate,Date toDate,List<Long> locationValues,Long locationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues,Long entityType,List<Long> questionsList,List<Date> selectedDates);
    public List<Object[]> getOverAllIvrDetails(Date fromDate,Date toDate,Long entityType,List<Long> questionsList,String type);
    public List<Date> getIvrSurveyDates(Date fromDate,Date toDate,Long entityType);
    public List<Object[]> getIvrSurveyQuestions(Date fromDate,Date toDate,Long entityType);
}
