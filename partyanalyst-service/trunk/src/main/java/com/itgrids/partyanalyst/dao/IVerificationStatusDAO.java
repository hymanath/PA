package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.model.VerificationStatus;

public interface IVerificationStatusDAO extends GenericDao<VerificationStatus, Long> {
	public Integer updateStatusForOldAlert(Long userId,Long alertId,Date date);
	public Object[] getAertStausIdAndName(Long alertId);
	public Long getAlertStatusId(Long alertId);
	public List<Object[]> getStatusWiseAlertCount(Long stateId,Date fromDate,Date toDate,Long alertTypeId,Long assignedUserId,String verificationUserType);
	public List<Object[]> getAllAlerts(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,Date fromDate2,Date toDate2,Long assignedUserId,String verificationUserType);
	public List<Object[]> getAlertCountStatusWiseBasedOnActionType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes,List<Long> scopeIds,List<Long> alertStatusIds);
	public List<Object[]> getActionTypeAlertDetails(Date fromDate, Date toDate, Long stateId, Long alertTypeId, List<Long> alertStatusIds, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList,Long actionTypeId,List<Long> impactScopeids,Long alertStatusId);
	public List<Object[]> getTotalStatus();
	
	public List<Object[]> getAllAlertsForCentralMembers(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,Date fromDate2,Date toDate2);
}
