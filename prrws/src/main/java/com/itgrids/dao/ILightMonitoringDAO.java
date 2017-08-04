package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoring;



public interface ILightMonitoringDAO extends GenericDao<LightMonitoring, Long > {
	
	
	public List<Object[]> getTotalVillagesDetails();
	public List<Object[]> getTotalSurveyDetails();
	public List<Object[]> getAllDitrictWiseSurveyDetails(Date startDate,Date endDate);
	public List<Object[]> getConstituencyLevelWiseSurveyDetails();
	public List<Object[]> getMandalLevelWiseSurveyDetails();
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date);
	public List<Object[]> getParlaimentWiseSurveyDetails();


}
