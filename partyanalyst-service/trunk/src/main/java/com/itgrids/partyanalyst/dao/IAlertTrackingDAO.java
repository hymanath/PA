package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertTracking;
import com.itgrids.partyanalyst.model.AlertUserType;

public interface IAlertTrackingDAO extends GenericDao<AlertTracking, Long> {
	public List<Object[]> getAlertTrackingDetails(Long alertId);
	public List<Object[]> getAlertTrackingDetailsList(Long alertId,boolean hasTrue);
	public List<Long> lastUpdatedstatus(Long alertId);
	public List<Object[]> getStatuswiseAlertsDetails(String mobileNo,Long userId, Date startDate, Date endDate,Long departmentId);
	public List<Object[]> getAlertFeedbackStatuswiseAlertsDetails(String mobileNo,Long userId, Date startDate, Date endDate,Long departmentId);
}
