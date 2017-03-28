package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.model.ClarificationRequired;

public interface IClarificationRequiredDAO extends GenericDao<ClarificationRequired,Long>{
	
	public List<Object[]> getDetails(Long alertId);
	public Integer updateStatusForOld(Long userId,Long alertId,Date date,String userType);
	public List<Object[]> getStatusAndCategoryWiseAlertsCount(Long stateId,Date fromDate,Date toDate,Long alertTypeId);
	public List<Object[]> getLocationLevelAlertClarificationData(List<Long> sourceIds,AlertInputVO inputVO,Date fromDate,Date toDate);
}
