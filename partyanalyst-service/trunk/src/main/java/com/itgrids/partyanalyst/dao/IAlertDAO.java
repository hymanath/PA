package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AlertInputVO;
import com.itgrids.partyanalyst.dto.LocationVO;
import com.itgrids.partyanalyst.model.Alert;

public interface IAlertDAO extends GenericDao<Alert, Long> {
	public List<Object[]> getLocationLevelWiseAlerts(List<Long> userTypeIds,Date startDate,Date endDate);
	public List<Object[]> getLocationLevelWiseAlertsData(List<Long> userTypeIds,AlertInputVO inputVO,Date fromDate,Date toDate);
	public List<Object[]> getAlertsData(Long alertId);

	public List<Object[]> getLocationWiseFilterAlertData(List<Long> sourceIds,Date fromDate,Date toDate,LocationVO inputVO,Long assignedCadreId);
	public List<Object[]> getTotalAlertGroupByStatus(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByStatusThenCategory(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByImpactLevel(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByImpactLevelThenStatus(Date fromDate, Date toDate, Long stateId,Long alertTypeId);
	public List<Alert> getAlertDetailsOfNewstype(Long alertCategoryType);
	public int updateAlertStatusOfNews(Long alertCategoryType,Long alertStatusId);
	public List<Object[]> getLocationIdList(Date fromDate, Date toDate, Long stateId, String Location,Long alertTypeId);
	public List<Object[]> getAlertCountGrpBylocationIdAndStatusIdAndCategoryId(Date fromDate, Date toDate, Long stateId, List<Long> locaionIds, String Location,Long alertTypeId);
	public List<Object[]> getTotalAlertGroupByLocation(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues);
	public List<Object[]> getTotalAlertGroupByLocationThenStatus(Date fromDate, Date toDate, Long stateId, List<Long> scopeIdList, String step, Long userAccessLevelId, List<Long> userAccessLevelValues);
	//CoreDashBoard Alerts
	public List<Object[]> getOverAllAlertDetailsForCoreDashBoard(Date startDate,Date endDate,Long locationLevelId,List<Long> levelValues
				,List<Long> impactScopeIds);
}