package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightWattage;

public interface ILightWattageDAO  extends GenericDao<LightWattage, Long > {

	public List<Object[]> getTotalWattege(Date fromDate,Date toDate,String locationType,List<Long> locationValues,List<Long> lightMonitoringIds);
	public int deleteAllLightWattageDetails(Date surveyDate);
	public List<Object[]> getLocationWiseLightWattageDtls(String locationType,String filterType,List<Long> filterValues,Date fromDate,Date toDate,List<Long> lightMonitoringIds);

}
