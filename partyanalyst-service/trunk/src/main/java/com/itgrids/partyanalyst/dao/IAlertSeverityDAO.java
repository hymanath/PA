package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Alert;
import com.itgrids.partyanalyst.model.AlertSeverity;

public interface IAlertSeverityDAO extends GenericDao<AlertSeverity, Long> {
	public List<Object[]> getFilterSectionDetailsOnSeverity();
	public List<Object[]> getAlertSeverity();
	public List<Long> getIdOfName(String seviority);
}
