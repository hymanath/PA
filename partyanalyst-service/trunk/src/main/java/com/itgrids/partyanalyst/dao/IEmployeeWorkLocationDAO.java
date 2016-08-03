package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EmployeeWorkLocation;

public interface IEmployeeWorkLocationDAO extends GenericDao<EmployeeWorkLocation, Long> {
	
	public List<Object[]> getOfficeWiseTotalEmployeeList();
	public List<Object[]> getOfficeWiseTotalAttendedEmployee(Date fromDate, Date toDate);
	public List<Object[]> getTotalAttendedEmployeesCadreId(Date fromDate, Date toDate);
	public List<Object[]> getOfficeWiseTotalNonAttendedEmployeeDetails(Date fromDate, Date toDate, List<Long> cadreIdList);
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeDetails(Date fromDate, Date toDate);
	
	public List<Object[]> getOfficeWiseTotalNonAttendedEmployeeDetailsFilter(Date fromDate, Date toDate, List<Long> cadreIdList, List<Long> deptList);
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeDetailsFilter(Date fromDate, Date toDate, List<Long> deptList);
	public List<Object[]> getOfficeWiseTotalEmployeeListFilter(List<Long> deptList);
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeFilter(Date fromDate, Date toDate, List<Long> deptList);
	public List<Object[]> getOfficeWiseTotalMigratedAttendedEmployee(Date fromDate, Date toDate, List<Long> migratedCaderIds, List<Long> deptList);
	public List<Object[]> getOfficeWiseTotalMigratedAttendedEmployeeDetails(Date fromDate, Date toDate, List<Long> attendedExtraCadreidList, List<Long> deptList);     
}
