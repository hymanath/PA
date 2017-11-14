package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.BiometricEmployeeAttendence;

public interface IBiometricEmployeeAttendenceDAO extends GenericDao<BiometricEmployeeAttendence, Long> {

	public Long getTotalPresentEmployee(Date fromDate,Date toDate);
	public List<Object[]> getTotalBioMetricDeviceIds(Date fromDate,Date toDate);
	public Long getEmployeeAttendenceDetails(Date fromDate, Date toDate,String type,Date fromTime,Date toTime);
	public List<Object[]> getDateWiseEmployeeAttendenceDetails(Date fromDate,Date toDate);
	public List<Object[]> getEmployeeWiseAttendenceCount(Date fromDate,Date toDate);
	public Long getIndividualEmployeeAttendenceDetails(Date fromDate, Date toDate,String type,Date fromTime,Date toTime,String empId);
	public List<Date> getDateWiseEmployeeAttendence(Date fromDate,Date toDate,String empId);
}
