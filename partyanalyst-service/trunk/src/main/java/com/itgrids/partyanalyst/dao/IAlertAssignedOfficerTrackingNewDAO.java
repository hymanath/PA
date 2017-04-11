package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficerTrackingNew;

public interface IAlertAssignedOfficerTrackingNewDAO extends GenericDao<AlertAssignedOfficerTrackingNew, Long> {
	public List<AlertAssignedOfficerTrackingNew> getAlertHistory(Long alertId);
	public List<Object[]> getAlertStatusHistory(Long alertId);
	public List<Object[]> getCommentsForAlert(Long alertId);
	public List<Object[]> getDocumentsForAlert(Long alertId);
	public List<String> getAlertDueDate(Long alertId);
}
