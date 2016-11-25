package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.model.Alert;

public interface IAlertDAO extends GenericDao<Alert, Long> {
	public List<Object[]> getLocationLevelWiseAlerts(List<Long> userTypeIds,Date startDate,Date endDate);
	public List<Object[]> getLocationLevelWiseAlertsData(List<Long> userTypeIds,Date fromDate,Date toDate,Long levelId,Long statusId);
	public List<Object[]> getAlertsData(Long alertId);

	public List<Object[]> getLocationWiseFilterAlertData(List<Long> sourceIds,Date fromDate,Date toDate,LocationVO inputVO,Long assignedCadreId);
	public List<Object[]> getTotalAlertGroupByStatus(Date fromDate, Date toDate, Long stateId);
	public List<Object[]> getTotalAlertGroupByStatusThenCategory(Date fromDate, Date toDate, Long stateId);
	public List<Object[]> getTotalAlertGroupByImpactLevel(Date fromDate, Date toDate, Long stateId);
	public List<Object[]> getTotalAlertGroupByImpactLevelThenStatus(Date fromDate, Date toDate, Long stateId);
	public List<Alert> getAlertDetailsOfNewstype(Long alertCategoryType);
	public int updateAlertStatusOfNews(Long alertCategoryType,Long alertStatusId);
}
