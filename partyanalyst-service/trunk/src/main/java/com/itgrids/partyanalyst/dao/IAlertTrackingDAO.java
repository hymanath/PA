package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.AlertUserType;

public interface IAlertTrackingDAO extends GenericDao<AlertTracking, Long> {
	public List<Object[]> getAlertTrackingDetails(Long alertId);
	public List<Object[]> getAlertTrackingDetailsList(Long alertId,boolean hasTrue);
	public List<Long> lastUpdatedstatus(Long alertId);
	

}
