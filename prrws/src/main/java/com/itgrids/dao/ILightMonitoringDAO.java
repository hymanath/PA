package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoring;



public interface ILightMonitoringDAO extends GenericDao<LightMonitoring, Long > {
	public List<Object[]> getTotalVillagesDetails(Date startDate,Date endDate,String locationType,List<Long> locationValues);
	public List<Object[]> getTotalSurveyDetails(Date startDate,Date endDate,String locationType,List<Long> locationValues);
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date);
  	public List<Object[]> getLocationsForLEDDashboard(String locationType,String filterType,List<Long> locationIds,String subLocationType);
 	public List<Object[]> getLocationWiseDataForLEDDashboard(String locationType,String filterType,List<Long> locationIds,Date formDate,Date toDate);
 	public List<Long> getLightMonitroingIds(Date date);
 	public Integer updateLightMoitoringData(Date date);
 	public Integer updateLightWattageMoitoringData(List<Long> lightMonitroingIds);

}
