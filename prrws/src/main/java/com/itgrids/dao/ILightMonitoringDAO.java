package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoring;



public interface ILightMonitoringDAO extends GenericDao<LightMonitoring, Long > {
	public List<Object[]> getTotalVillagesDetails(Date startDate,Date endDate,String locationType,List<Long> locationValues,List<Long> lightsVendorIds,String isGroupRequired);
	public List<Object[]> getTotalSurveyDetails(Date startDate,Date endDate,String locationType,List<Long> locationValues,List<Long> lightsVendorIds,String isGroupRequired);
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date);
  	public List<Object[]> getLocationsForLEDDashboard(String locationType,String filterType,List<Long> locationIds,String subLocationType,Date fromDate,Date toDate,List<Long> lightsVendorIds);
 	public List<Object[]> getLocationWiseDataForLEDDashboard(String locationType,String filterType,List<Long> locationIds,Date formDate,Date toDate,List<Long> lightsVendorIds);
 	public List<Long> getLightMonitroingIds(Date date,Long lightVendorId);
 	public Integer updateLightMoitoringData(Date date,Long lightVendorId);
 	public Integer updateLightWattageMoitoringData(List<Long> lightMonitroingIds);
 	public List<Long> isDataExist(Date fromDate, Date toDate);
 	public List<String> getMonthAndYear(Date fromDate,Date toDate);
 	public List<Object[]> getDateWiseLightMonitoringDtls(Date fromDate,Date toDate,String locationType,List<Long> locationValues);

}
