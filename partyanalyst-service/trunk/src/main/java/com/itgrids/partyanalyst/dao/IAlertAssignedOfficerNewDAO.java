package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;

public interface IAlertAssignedOfficerNewDAO extends GenericDao<AlertAssignedOfficerNew, Long> {
	public List<Object[]> getAssignedStatuses();
	public List<Object[]> getDepartmentScope();
	public List<Object[]> getAlertCntByRequiredType(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> calCntrIds);
	public List<AlertAssignedOfficerNew> getModelForAlert(Long alertId);
	public List<Object[]> getDistrictOfficerAlertsCount(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date fromDate,Date toDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<Long> getAlertAssignedOfficerId(Long alertId);
	public List<Long> getTotalAlertByOtherStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, Long statusId,Long levelId,List<Long> levelValues,Long govtDeptScopeId,Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList);
	public List<Object[]> getAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate);
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelView(Date fromDate, Date toDate,List<Long> deptIds,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long accessLevelId,List<Long> levelValues,String type,List<Long> deptScopeIds);
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long scopeId,List<Long> deptIds,Long levelId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,List<Long> levelValues);
	//public List<Object[]> getAlertDetailsForDistrictOfficer(Date fromDate, Date toDate, Long stateId, List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,String dateType);
	public List<Object[]> getSubOrdinateAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
	List<Long> desigIds,Long priorityId,String type);
	public List<Long> getDistrictOfficerAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,Date formDate,Date todDate);
	public List<Object[]> getLocationThenGovtDeptScopeWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdsList,List<Long> printIdsList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList);
	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,Long statusId,Date fromDate,
			Date toDate,Long desigDeptOfficerId,Long officerId,Long levelId,List<Long> levelValues,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList,Long alertCategoryId);
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
	public List<Object[]> getDistrictOfficerMyAlertsCountView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<Object[]> getDistrictOfficerMyAlertsStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,Date startDate,Date endDate,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<Long> getDistrictOfficerAlertsDetails(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId, String group,Long statusId,Long govtdeptScopeId,List<Long> calCntrIdList);
	public List<Long> getDivisionWorkLocationGovtDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
    		List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,
    		List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long justUpperLvl);
	public List<Long> getSubDivisionWorkLocationDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
    		Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
    		Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds,Long lvlTwo,Long lvlThree);
	
	public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsView(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds,List<Long> callCenterIds);
	public List<Long> getDistrictOffrAlertsIds(List<Long> govtDeptDesigOffceIds,List<Long> govtOffceIds,Date formDateStr,Date toDateStr,Long statusId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<Long> getGovtDepartmentDesignationOfficer(Long alertId);
	public List<Long> getStateLevelAlertclickViewAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,
			 String type,List<Long> deptIds,Long statusId,Date startDate,Date endDate);
	public Long getGovtDeptScopeIdForAlert(Long alertId);
	public List<Long> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,Long deptScopeId, Long statusId,List<Long> calCntrIds);  
	public Long getGovtDeptDesigOfficerIdListByUserId(Long alertId);
	public Long getGovtDeptDesigIdListByUserId(Long alertId);
	public Integer deleteAssignment(Long alertId);
	public List<Long> getAssignedDtls(Long alertId);
	public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,Long statusId,
			Date fromDate,Date toDate,Long levelId,List<Long> levelValues, List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList);
	public List<Object[]> getAlertAssignedLevelDetails(Long alertId);
	//santosh
	public List<Object[]> getLocationBasedOnDepartmentLevelId(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds);
    public List<Object[]> getChildLocationBasedOnParentLocation(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
     		Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,
     		Long parentGovtDepartmentScopeId,Long parentGovtDepartmentScopeValue, Long childLevelId,List<Long> calCntrIds);
    public List<Object[]> getAlertDetailsLocationWiseBasedOnDepartmentLevel(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList, String group,String searchType,
     		List<Long> calCntrIds,Long filterParentScopeId,Long filterScopeValue);
    public List<Long> getAlertIdsBasedOnRequiredParameter(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
     	 Long parentGovtDepartmentScopeId,Long locationValue,List<Long> calCntrIds,Long deptLevelId,
     	 Long alertStatusId,Long alertCategoryId,List<Long> deptScopeIds);
    public List<Object[]> getDepartmentDetaislByDeptIds(Long departmentId);
    public List<Object[]> getDepartmentDetailsOfAlert(Long alertId);
    public List<Object[]> getAllDepartmentHasData(List<Long> deptIds,Long levelId,List<Long> levelValues);
    public List<Object[]> getSubOrdinateFilterAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
			List<Long> desigIds,Long priorityId,List<Long> statusIds,List<Long> paperIdList,List<Long> chanelIdList,List<Long> calCntrIdList,List<Long> childLevelVals,Long childLevelId);
    public List<Object[]> getSubOrdinateFilterAlertsDetailsForUser(Date fromDate,Date toDate,
     		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,List<Long> govtDepartmentIds,
     		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,List<Long> calCntrIds,List<Long> filterLocationValues,List<Long> desigIds);
    public List<Long> getTotalAlertByOtherStatus1(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, List<Long> statusIdList,Long levelId,List<Long> levelValues, Long govtDepartmentScopeId, Long deptId,List<Long> calCntrIds,List<Long> impactLevelIdList,List<Long> priorityIdList,List<Long> alertSourceIdList,List<Long> printMediaIdList,List<Long> electronicMediaIdList,Long scopeId,List<Long> locationList);
    //public List<Object[]> getSubTaskStatusIds(Set<Long> alertSet);
    public List<Long> getAlertCntByAlertCategory(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,List<Long> calCntrIds,Long alertCategoryId,Long alertStatusId);
}
