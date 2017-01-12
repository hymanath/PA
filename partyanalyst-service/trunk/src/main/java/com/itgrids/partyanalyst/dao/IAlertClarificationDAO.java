package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertClarification;

public interface IAlertClarificationDAO extends GenericDao<AlertClarification, Long>{

	public List<Object[]> getAlertClarificationStatus(Long alertId);
	public Long getAlertClarfStatusRecord(Long alertId);
	public Integer updateAlertStatus(Long alertId,Long statusId);
	public Integer updateStatusForOld(Long userId,Long alertId,Date date); 
}
