package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoring;



public interface ILightMonitoringDAO extends GenericDao<LightMonitoring, Long > {
	
	
	public List<Object[]> getTotalVillagesDetails(Date startDate,Date endDate);
	public List<Object[]> getTotalSurveyDetails(Date startDate,Date endDate);
	public List<Object[]> getAllDitrictWiseSurveyDetails(Date fromDate,Date toDate,String year,List<Long> locationValues,Long locationTypeId,Long searchlevelId,List<Long> searchLevelValues);
	public List<Object[]> getConstituencyLevelWiseSurveyDetails();
	public List<Object[]> getMandalLevelWiseSurveyDetails();
    public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date);
    public List<Object[]> getParlaimentWiseSurveyDetails();
 	public List<Object[]> getDistrictLevelWise(Date fromDate, Date toDate,  List<Long> locationValues,	Long locationTypeId); 
 	
 	public List<Object[]> getLocationsForLEDDashboard(String locationType,String filterType,Long locationId);
 	public List<Object[]> getLocationWiseDataForLEDDashboard(String locationType,String filterType,Long locationId,Date formDate,Date toDate);

}
