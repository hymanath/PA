package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightMonitoringDetails;

public interface ILightMonitoringDetailsDAO extends GenericDao<LightMonitoringDetails, Long>{
	
	public List<Object[]> getLightsFittedCountBetweenDates(Date fromDate,Date toDate,Long vendorId);
	public List<Object[]> getTodayVendorDetails(Date toDate,Long vendorId);
	public List<Object[]> getLightVendorDetailsByLevelType(String levelType,Long vendorId,Date fromDate,Date toDate);
	public List<Object[]> getVendorDetails(Date fromDate,Date toDate);
	public List<Object[]> getAllVendorDetailsByLevelType(String levelType,Date fromDate,Date toDate);
	public List<Object[]> getVendorDetailsForLocation(String levelType,Date fromDate,Date toDate,Long locationValue,Long vendorId);

}
