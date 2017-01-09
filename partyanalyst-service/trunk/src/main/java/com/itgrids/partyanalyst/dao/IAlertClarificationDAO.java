package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityLocationAttendance;
import com.itgrids.partyanalyst.model.AlertClarification;

public interface IAlertClarificationDAO extends GenericDao<AlertClarification, Long>{

	public List<Object[]> getAlertClarificationStatus(Long alertId);
	public Long getAlertClarfStatusRecord(Long alertId);
	 public Integer updateAlertStatus(Long alertId,Long statusId);
}
