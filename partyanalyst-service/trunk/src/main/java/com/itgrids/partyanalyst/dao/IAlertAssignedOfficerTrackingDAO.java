package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertAssignedOfficerTracking;

public interface IAlertAssignedOfficerTrackingDAO extends GenericDao<AlertAssignedOfficerTracking, Long>{

	public List<Object[]> getStatusWiseTrackingComments(Long alertId);
	public List<Object[]> getStatusWiseTrackingCommentsNew(Long alertId);
	public List<String> getLatestStatusForAlertTracking(Long alertId);
}
