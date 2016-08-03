package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEmployeeWorkLocationDAO;
import com.itgrids.partyanalyst.model.EmployeeWorkLocation;

public class EmployeeWorkLocationDAO extends GenericDaoHibernate<EmployeeWorkLocation, Long> implements IEmployeeWorkLocationDAO {
	public EmployeeWorkLocationDAO(){
		super(EmployeeWorkLocation.class);
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalEmployeeList(){
		Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(EWL.employeeWorkLocationId) " +
											   " from EmployeeWorkLocation EWL "+
											   " where " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											  // " EWL.partyOffice.event.parentEventId = 44 " +
											   " group by " +
											   " EWL.partyOffice.partyOfficeId " +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		return query.list();
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalAttendedEmployee(Date fromDate, Date toDate){
		Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EWL.employee.tdpCadreId) " +
											   " from EmployeeWorkLocation EWL, EventAttendee EA " +
											   " where " +
											  // " EWL.partyOffice.event.parentEventId = 44 " +
											   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
											   " EA.event.eventId = EWL.partyOffice.event.eventId " +
											   " and date(EA.attendedTime) between :fromDate and :toDate and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " group by " +
											   " EWL.partyOffice.partyOfficeId " +  
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}  
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalAttendedEmployeesCadreId(Date fromDate, Date toDate){
		Query query = getSession().createQuery(" select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.tdpCadreId " +
											   " from EmployeeWorkLocation EWL, EventAttendee EA " +
											   " where " +
											   //" EWL.partyOffice.event.parentEventId = 44 " +
											   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
											   " EA.event.eventId = EWL.partyOffice.event.eventId " +
											   " and date(EA.attendedTime) between :fromDate and :toDate and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalNonAttendedEmployeeDetails(Date fromDate, Date toDate, List<Long> cadreIdList){
		Query query = getSession().createQuery(" select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, ED.department.departmentName  " +
											   " from EmployeeWorkLocation EWL, EmployeeDepartment ED " +
											   " where " +
											   " EWL.employee.tdpCadre.tdpCadreId not in (:cadreIdList) and " +
											   " ED.employee.employeeId = EWL.employee.employeeId and" +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId, ED.department.departmentId ");
		query.setParameterList("cadreIdList", cadreIdList);
		return query.list();
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeDetails(Date fromDate, Date toDate){
		Query query = getSession().createQuery("  select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, min(EA.attendedTime), ED.department.departmentName " +
											   " from EmployeeWorkLocation EWL, EventAttendee EA, EmployeeDepartment ED " +
											   " where " +
											   //" EWL.partyOffice.event.parentEventId = 44 and " +
											   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
											   " ED.employee.employeeId = EWL.employee.employeeId and " +
											   " EA.event.eventId = EWL.partyOffice.event.eventId and " +
											   " date(EA.attendedTime) between :fromDate and :toDate and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " group by" +
											   " EWL.employee.tdpCadre.tdpCadreId" +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId, ED.department.departmentId, EA.attendedTime");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalNonAttendedEmployeeDetailsFilter(Date fromDate, Date toDate, List<Long> cadreIdList, List<Long> deptList){
		Query query = getSession().createQuery(" select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, ED.department.departmentName, EWL.employee.tdpCadre.tdpCadreId  " +
											   " from EmployeeWorkLocation EWL, EmployeeDepartment ED " +
											   " where " +
											   " EWL.employee.tdpCadre.tdpCadreId not in (:cadreIdList) and " +
											   " ED.employee.employeeId = EWL.employee.employeeId and" +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' and " +
											   " ED.department.departmentId in (:deptList) " +
											   " order by " + 
											   " EWL.partyOffice.partyOfficeId, ED.department.departmentId ");
		query.setParameterList("cadreIdList", cadreIdList);
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeDetailsFilter(Date fromDate, Date toDate, List<Long> deptList){
		Query query = getSession().createQuery("  select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, min(EA.attendedTime), ED.department.departmentName " +
											   " from EmployeeWorkLocation EWL, EventAttendee EA, EmployeeDepartment ED " +
											   " where " +
											   //" EWL.partyOffice.event.parentEventId = 44 and " +
											   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
											   " ED.employee.employeeId = EWL.employee.employeeId and " +
											   " EA.event.eventId = EWL.partyOffice.event.eventId and " +
											   " date(EA.attendedTime) between :fromDate and :toDate and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' and " +
											   " ED.department.departmentId in (:deptList) " +
											   " group by" +
											   " EWL.employee.tdpCadre.tdpCadreId" +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId, ED.department.departmentId, EA.attendedTime");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalEmployeeListFilter(List<Long> deptList){
		Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(EWL.employeeWorkLocationId) " +
											   " from EmployeeWorkLocation EWL, EmployeeDepartment ED "+
											   " where " +
											   " ED.employee.employeeId = EWL.employee.employeeId and " +
											   " ED.department.departmentId in (:deptList) and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											  // " EWL.partyOffice.event.parentEventId = 44 " +
											   " group by " +
											   " EWL.partyOffice.partyOfficeId " +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeFilter(Date fromDate, Date toDate, List<Long> deptList){
		Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EWL.employee.tdpCadreId) " +
											   " from EmployeeWorkLocation EWL, EventAttendee EA,  EmployeeDepartment ED  " +
											   " where " +
											   " ED.employee.employeeId = EWL.employee.employeeId and " +
											   " ED.department.departmentId in (:deptList) and " +
											  // " EWL.partyOffice.event.parentEventId = 44 " +
											   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
											   " EA.event.eventId = EWL.partyOffice.event.eventId " +
											   " and date(EA.attendedTime) between :fromDate and :toDate and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " group by " +
											   " EWL.partyOffice.partyOfficeId " +  
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameterList("deptList", deptList);
		return query.list();
	} 
	//query for migrated emplyees started....
	public List<Object[]> getOfficeWiseTotalMigratedAttendedEmployee(Date fromDate, Date toDate, List<Long> migratedCaderIds, List<Long> deptList){
		Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EWL.employee.tdpCadre.tdpCadreId) " +
											   " from EmployeeWorkLocation EWL, EventAttendee EA, EmployeeDepartment ED " +
											   " where " +
											   " EWL.employee.employeeId = ED.employee.employeeId and " +
											   " ED.department.departmentId in (:deptList) and " +
											   " EA.event.eventId = EWL.partyOffice.event.eventId and " +
											   " EWL.employee.tdpCadre.tdpCadreId in (:migratedCaderIds) and " +
											   " date(EA.attendedTime) between :fromDate and :toDate and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " group by " +
											   " EWL.partyOffice.partyOfficeId " +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameterList("migratedCaderIds", migratedCaderIds);
		query.setParameterList("deptList", deptList);
		return query.list();
		
	}
	public List<Object[]> getOfficeWiseTotalMigratedAttendedEmployeeDetails(Date fromDate, Date toDate, List<Long> attendedExtraCadreidList, List<Long> deptList){
		Query query = getSession().createQuery("  select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, min(EA.attendedTime), ED.department.departmentName " +
				   " from EmployeeWorkLocation EWL, EventAttendee EA, EmployeeDepartment ED " +
				   " where " +
				   //" EWL.partyOffice.event.parentEventId = 44 and " +
				   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
				   " EWL.employee.tdpCadre.tdpCadreId in (:attendedExtraCadreidList) and " +
				   " EWL.employee.employeeId = ED.employee.employeeId and " +
				   " ED.department.departmentId in (:deptList) and " +   
				   " ED.employee.employeeId = EWL.employee.employeeId and " +
				   //" EA.event.eventId = EWL.partyOffice.event.eventId and " +
				   " date(EA.attendedTime) between :fromDate and :toDate and " +
				   " EWL.employee.isDeleted = 'N' and " +
				   " EWL.employee.isActive = 'Y' and " +
				   " EWL.partyOffice.isDeleted = 'N' " +
				   " group by" +
				   " EWL.employee.tdpCadre.tdpCadreId" +
				   " order by " +
				   " EWL.partyOffice.partyOfficeId, ED.department.departmentId, EA.attendedTime");
query.setDate("fromDate", fromDate);
query.setDate("toDate", toDate);
query.setParameterList("attendedExtraCadreidList", attendedExtraCadreidList);
query.setParameterList("deptList", deptList);  
return query.list();
	}
}