package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertCallerRelation;

public interface IAlertCallerRelationDAO extends GenericDao<AlertCallerRelation, Long>{

	public List<Object[]> getAlertCallerDetailsByAlert(Long alertId);
	public List<Object[]> getAlertCallerDetailsForAlerts(List<Long> alertIds);
	public Long getMaxCallerOrderForAlert(Long alertId);
	public Long totalCallCenterCallForRwsDept(Date fromDate,Date toDate);
}
