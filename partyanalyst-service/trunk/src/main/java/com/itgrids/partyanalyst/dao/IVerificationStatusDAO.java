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
	public List<Object[]> getStatusWiseAlertCount(Long stateId,Date fromDate,Date toDate,Long alertTypeId);
	public List<Object[]> getAllAlerts(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate,Date fromDate2,Date toDate2);
	public List<Object[]> getAlertCountStatusWiseBasedOnActionType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes);
	public List<Object[]> getActionTypeAlertDetails(Date fromDate, Date toDate, Long stateId, Long alertTypeId, Long alertActionStatusId, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList,Long actionTypeId);
	public List<Object[]> getTotalStatus();
}
