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
	public List<Object[]> getDepartmentWiseTotalAttendedEmployeeFilter(List<Long> deptList, List<Long> presentedCaderIdList);
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilter(List<Long> deptList, List<Long> presentedCaderIdList);
	public List<Object[]> getDepartmenWiseTotalMigratedAttendedEmployee(Date fromDate, Date toDate, List<Long> attendedExtraCadreidList, List<Long> deptList);
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalMigratedAttendedEmployee(List<Long> attendedExtraCadreidList, List<Long> deptList);
	public List<Object[]> getDepartmentWiseTotalEmployeeListFilterForOffice(List<Long> deptList, Long officeId );
	public List<Object[]> getDepartmentWiseTotalAttendedEmployeeFilterForOffice(List<Long> deptList, List<Long> presentedCaderIdList, Long officeId);
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilterForOffice(List<Long> deptList, List<Long> presentedCaderIdList, Long officeId);
	public List<Object[]> getAbsentCadreDtls(List<Long> cadreIdList);  
}
