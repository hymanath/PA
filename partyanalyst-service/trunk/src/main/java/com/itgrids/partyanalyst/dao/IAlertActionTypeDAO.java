package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertActionType;

public interface IAlertActionTypeDAO extends GenericDao<AlertActionType, Long> {
	public List<Object[]> getAlertCountStatusWiseBasedOnActionType(Long userAccessLevelId,Set<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> alertType,List<Long> editionTypes);
	public List<Object[]> getActionTypeAlertDetails(Date fromDate, Date toDate, Long stateId, Long alertTypeId, Long alertActionStatusId, Long userAccessLevelId, List<Long> userAccessLevelValues,List<Long> editionList);
	
}
