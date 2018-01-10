package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MeesevaKpiCenters;

public interface IMeesevaKpiCentersDAO extends GenericDao<MeesevaKpiCenters, Long>{
	
	public int deleteRecrdsFrmTable(String districtIdStr);
	public Long getStateWiseTotalMeesevaCentersCunts();
	public List<Date> getEstablisedDatesFrAllRecords();
	public List<Object[]> getMeesevaKPIDetails();
	public Long getMeesevaKPICentresEstFrom2014();
	public Long getMeesevaKPICentresEstLast(Date fromDate,Date endDate);
	public List<Object[]> getMeesevaKPICentresForDoistrict();
	public List<Object[]> getMeesevaKPICentresFrom2014ForDistricts();
	public List<Object[]> getMeesevaKPICentresLastYearForDistricts(Date startDate,Date endDate);
	public List<Object[]> getMeesevaDetailsByDistrictId(String districtId);
	public Long getMeesevaKPICentresForFromAndToDate(Date startDate,Date endDate);
}
