package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EmployeeDepartment;

public interface IEmployeeDepartmentDAO extends GenericDao<EmployeeDepartment, Long> {
	public List<Object[]> getDepartmentWiseTotalEmployeeList();
	public List<Object[]> getDepartmentWiseTotalAttendedEmployee(Date fromDate, Date toDate);
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalAttendedEmployee(Date fromDate, Date toDate);
	public List<Object[]> getEmployeeDetails(Long cadreId);
	
	public List<Object[]> getDepartmentWiseTotalEmployeeListFilter(List<Long> deptList);
	public List<Object[]> getDepartmentWiseTotalAttendedEmployeeFilter(Date fromDate, Date toDate, List<Long> deptList);
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilter(Date fromDate, Date toDate, List<Long> deptList);
}
