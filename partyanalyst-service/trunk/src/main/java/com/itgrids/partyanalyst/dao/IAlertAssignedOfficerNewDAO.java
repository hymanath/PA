package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficerNew;

public interface IAlertAssignedOfficerNewDAO extends GenericDao<AlertAssignedOfficerNew, Long> {
	public List<Object[]> getAssignedStatuses();
	public List<Object[]> getDepartmentScope();
	public List<Object[]> getAlertCntByRequiredType(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds,Long levelId,List<Long> levelValues,String type,List<Long> alertStatusIds,List<Long> departmentScopeIds);
	public List<AlertAssignedOfficerNew> getModelForAlert(Long alertId);
	public List<Object[]> getDistrictOfficerAlertsCount(Long govtDepDesigOffcrId,Long govtOffcrId,String type);
	public List<Long> getTotalAlertByOtherStatus(Date fromDate, Date toDate, Long stateId, List<Long> printIdsList, List<Long> electronicIdsList,List<Long> departmentIds, Long statusId,Long levelId,List<Long> levelValues,Long govtDeptScopeId,Long deptId);
	public List<Object[]> getAlertAssignCountsForDeptWiseDetails(Date fromDate, Date toDate);
	public List<Object[]> getDistrictLevelDeptWiseLocationLevelView(Date fromDate, Date toDate,Long deptId);
	public List<Object[]> getDistrictLevelDeptWiseStatusOverView(Date fromDate, Date toDate,Long scopeId,Long deptId);
}
