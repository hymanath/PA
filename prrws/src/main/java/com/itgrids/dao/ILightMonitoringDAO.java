package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoring;



public interface ILightMonitoringDAO extends GenericDao<LightMonitoring, Long > {
	public List<LightMonitoring> getLiveDateForCurrentDateSelection(Date date);
	
	
	

}
