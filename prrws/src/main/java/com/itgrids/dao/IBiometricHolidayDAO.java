package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.BiometricHoliday;

public interface IBiometricHolidayDAO extends GenericDao<BiometricHoliday, Long> {

	public List<Object[]> getBioMetricHolidays(Date fromDate,Date toDate);
}
