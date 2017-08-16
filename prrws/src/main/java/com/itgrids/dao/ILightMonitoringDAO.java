package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoring;



public interface ILightMonitoringDAO extends GenericDao<LightMonitoring, Long > {
	public List<Object[]> getTotalVillagesDetails(Date startDate,Date endDate);
	public List<Object[]> getTotalSurveyDetails(Date startDate,Date endDate);
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date);
  	public List<Object[]> getLocationsForLEDDashboard(String locationType,String filterType,Long locationId);
 	public List<Object[]> getLocationWiseDataForLEDDashboard(String locationType,String filterType,Long locationId,Date formDate,Date toDate);

}
