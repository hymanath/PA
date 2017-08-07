package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoring;



public interface ILightMonitoringDAO extends GenericDao<LightMonitoring, Long > {
	
	
	public List<Object[]> getTotalVillagesDetails(Date startDate,Date endDate);
	public List<Object[]> getTotalSurveyDetails(Date startDate,Date endDate);
	public List<Object[]> getAllDitrictWiseSurveyDetails(Date fromDate,Date toDate,String year,List<Long> locationValues,Long locationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues);
	public List<Object[]> getConstituencyLevelWiseSurveyDetails();
	public List<Object[]> getMandalLevelWiseSurveyDetails();
     public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date);
     public List<Object[]> getParlaimentWiseSurveyDetails();
	

}
