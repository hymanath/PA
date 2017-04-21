package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

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
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelView(Date fromDate, Date toDate,Long deptId);
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long scopeId,Long deptId,Long levelId);
	//public List<Object[]> getAlertDetailsForDistrictOfficer(Date fromDate, Date toDate, Long stateId, List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,String dateType);
	public List<Object[]> getSubOrdinateAlertsDetails(Long userId,Date fromDate,Date endDate , List<Long> govtScopeIds,List<Long> locationValues,Long levelId,List<Long> levelValues,
	List<Long> desigIds,Long priorityId,String type);
	public List<Long> getDistrictOfficerAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<Object[]> getLocationThenGovtDeptScopeWiseAlertCount(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdsList,List<Long> printIdsList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList);
	public List<Long> getAlertIdsForDeptAndLevelId(Long deptId,Long locationLevelId,Long statusId,Date fromDate,
			Date toDate,Long desigDeptOfficerId,Long officerId,Long scopeId);
	public List<Object[]> getLocationThenGovtDeptScopeWiseAlertCountForStatus(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList);
	public List<Object[]> getDeptList(Long userId);
	public List<Object[]> getDistrictOfficerScopesWiseAlerts(Date fromDate,Date toDate,Long stateId,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList);
	public List<Object[]> getAssignedOfficersDetails(Long alertId);
	public List<Long> getLocationThenGovtDeptScopeWiseAlertCountOnClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,Long parentGovtDepartmentScopeId,Long locationId,Long childLocationId);
	public List<Long> getLocationThenGovtDeptScopeWiseAlertCountForStatusForClick(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,List<Long> deptScopeIdList,Long parentGovtDepartmentScopeId,Long locationId,Long statusId);
	public List<Object[]> getDesignationOfficerDetails(Long alertId);
	public List<Object[]> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,String group,String searchType,List<Long> calCntrIds);
	public List<Object[]> getDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds);
	public List<Object[]> getSubDivisionWorkLocationThenGovtDeptScopeWiseAlertCountForOverview(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,String searchType,List<Long> calCntrIds);
	public List<Object[]> getDistrictOfficerMyAlertsCountView(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,String type);
	public List<Object[]> getDistrictOfficerMyAlertsStatusWiseDetails(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds);
	public List<Long> getDistrictOfficerAlertsDetails(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,Long districtWorkLocationId, String group,Long statusId,Long govtdeptScopeId,List<Long> calCntrIdList);
	public List<Long> getDivisionWorkLocationGovtDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,
    		List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,
    		List<Long> deptScopeIdList,Long districtWorkLocationId,Long divisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds);
	public List<Long> getSubDivisionWorkLocationDeptScopeWiseAlertCountDetails(Date fromDate,Date toDate,Long stateId,List<Long> electronicIdList,List<Long> printIdList,
    		Long levelId,List<Long> levelValues,Long govtDepartmentId,Long parentGovtDepartmentScopeId,List<Long> deptScopeIdList,
    		Long districtWorkLocationId,Long divisionWorkLocationId,Long subDivisionWorkLocationId,String filter,String group,Long statusId,Long govtDeprtMentScopeId,List<Long> calCntrIds);
	
	public List<Object[]> stateLevelDeptOfficerDepartmentWiseAlertsView(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds);
	public List<Long> getDistrictOffrAlertsIds(Long govtDeptDesigOffceId,Long govtOffceId,Date formDateStr,Date toDateStr,Long statusId,List<Long> printIdsList,List<Long> electronicIdsList,List<Long> calCntrIdList);
	public List<Long> getGovtDepartmentDesignationOfficer(Long alertId);
	public List<Long> getStateLevelAlertclickViewAlertsIds(List<Long> govtDepDesigOffcrIds,List<Long> govtOffcrIds,
			 String type,Long deptId,Long statusId);
	public Long getGovtDeptScopeIdForAlert(Long alertId);
	public List<Long> getStateAndDistrictWorkLocationThenGovtDeptScopeWiseAlertCountForOverviewForClick(Date fromDate,Date toDate,
    		Long stateId,List<Long> electronicIdList,List<Long> printIdList,Long levelId,List<Long> levelValues,Long govtDepartmentId,
    		Long parentGovtDepartmentScopeId,Long deptScopeId, Long statusId,List<Long> calCntrIds);  
	public Long getGovtDeptDesigOfficerIdListByUserId(Long alertId);
	public Long getGovtDeptDesigIdListByUserId(Long alertId);
	public Integer deleteAssignment(Long alertId);
	public Long getAssignedDtls(Long alertId);
	public List<Long> getStateLevelDeptWiseFlterClick(List<Long> deptIds,Long locationLevelId,Long statusId,
			Date fromDate,Date toDate,Long desigDeptOfficerId,Long officerId,Long scopeId, List<Long> printIdList, List<Long> electronicIdList,List<Long> calCntrIdList);
}
