package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MeesevaKpiCenters;

public interface IMeesevaKpiCentersDAO extends GenericDao<MeesevaKpiCenters, Long>{
	
	public int deleteRecrdsFrmTable(String districtIdStr);
	public List<Object[]> getStateWiseTotalMeesevaCentersCunts();
	public List<Date> getEstablisedDatesFrAllRecords();
	public List<Object[]> getMeesevaKPIDetails();
	public List<Object[]> getMeesevaKPICentresEstFrom2014();
	public  List<Object[]> getMeesevaKPICentresEstLast(Date fromDate,Date endDate);
	public List<Object[]> getMeesevaKPICentresByType(String levelType);
	public List<Object[]> getMeesevaKPICentresFrom2014ByType(String levelType);
	public List<Object[]> getMeesevaKPICentresLastYearByType(Date startDate,Date endDate,String levelType);
	public List<Object[]> getMeesevaDetailsByDistrictId(String districtId);
	public List<Object[]> getMeesevaKPICentresForFromAndToDate(Date startDate,Date endDate);
}
