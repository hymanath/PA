package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEmployeeDepartmentDAO;
import com.itgrids.partyanalyst.model.EmployeeDepartment;

public class EmployeeDepartmentDAO extends GenericDaoHibernate<EmployeeDepartment, Long> implements IEmployeeDepartmentDAO {
	public EmployeeDepartmentDAO(){
		super(EmployeeDepartment.class);
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getDepartmentWiseTotalEmployeeList(){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, count(ED.department.departmentId) " +
						" from EmployeeDepartment ED, EmployeeWorkLocation EWL, TdpCadre TC " +
						" where " +
						" ED.employee.employeeId = EWL.employee.employeeId and " +
						" ED.employee.tdpCadreId = TC.tdpCadreId and " +
						//" EWL.partyOffice.event.parentEventId = 44 and " +
						" ED.isDeleted = 'N' and " +
						" ED.employee.isDeleted = 'N' and " +
						" ED.employee.isActive = 'Y' " +
						" group by " +
						" ED.department.departmentId " +
						" order by " +
						" ED.department.departmentId ");
		Query query = getSession().createQuery(sqlQuery.toString());
		return query.list();
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getDepartmentWiseTotalAttendedEmployee(Date fromDate, Date toDate){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, count(distinct ED.employee.tdpCadreId)" +
						" from EmployeeDepartment ED, EmployeeWorkLocation EWL, EventAttendee EA " +
						" where " +
						" ED.employee.employeeId = EWL.employee.employeeId and " +
						" EWL.partyOffice.event.eventId = EA.event.eventId and " +
						//" EWL.partyOffice.event.parentEventId = 44 and " +
						" ED.employee.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
						" date(EA.attendedTime) between :fromDate and :toDate and " +
						" ED.isDeleted = 'N' and " +
						" ED.employee.isDeleted = 'N' and " +
						" ED.employee.isActive = 'Y' " +
						" group by " +
						" ED.department.departmentId " +
						" order by " +
						" ED.department.departmentId ");
		Query query = getSession().createQuery(sqlQuery.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	//PO.event_id = E.event_id and EA.event_id = E.event_id
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalAttendedEmployee(Date fromDate, Date toDate){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EA.tdpCadre.tdpCadreId) " +
						" from EmployeeDepartment ED, EmployeeWorkLocation EWL, EventAttendee EA " +
						" where " +
						" ED.employee.employeeId = EWL.employee.employeeId and " +
						" ED.employee.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
						//" EWL.partyOffice.event.parentEventId = 44 and " +
						" EWL.partyOffice.event.eventId = EA.event.eventId and " +
						" date(EA.attendedTime) between :fromDate and :toDate and " +
						" ED.isDeleted = 'N' and " +
						" ED.employee.isDeleted = 'N' and " +
						" ED.employee.isActive = 'Y' and " +
						" EWL.partyOffice.isDeleted = 'N' " +
						" group by " +
						" ED.department.departmentId, EWL.partyOffice.partyOfficeId " +
						" order by " +
						" ED.department.departmentId, EWL.partyOffice.partyOfficeId ");
		Query query = getSession().createQuery(sqlQuery.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getEmployeeDetails(Long cadreId){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(" select distinct ED.employee.tdpCadreId, ED.department.departmentName from EmployeeDepartment ED " +
						" where ED.employee.tdpCadre.tdpCadreId = :cadreId");
		Query query = getSession().createQuery(sqlQuery.toString());
		query.setParameter("cadreId", cadreId);
		return  query.list();
	}
}