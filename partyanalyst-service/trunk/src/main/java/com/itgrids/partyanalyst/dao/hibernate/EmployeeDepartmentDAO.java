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
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getDepartmentWiseTotalEmployeeListFilter(List<Long> deptList ){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, count(distinct ED.employee.tdpCadre.tdpCadreId) " +
						" from EmployeeDepartment ED " +
						" where " +
						" ED.isDeleted = 'N' and " +
						" ED.employee.isDeleted = 'N' and " +
						" ED.employee.isActive = 'Y' and " +
						" ED.department.departmentId in (:deptList)" +
						" group by " +
						" ED.department.departmentId " +
						" order by " +
						" ED.department.departmentId ");
		Query query = getSession().createQuery(sqlQuery.toString());
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	
	public List<Object[]> getDepartmentWiseTotalAttendedEmployeeFilter(List<Long> deptList, List<Long> presentedCaderIdList){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, count(distinct ED.employee.tdpCadre.tdpCadreId)" +
						" from EmployeeDepartment ED " +
						" where " +
						" ED.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) and " +
						" ED.department.departmentId in (:deptList) and " +
						" ED.isDeleted = 'N' and " +
						" ED.employee.isDeleted = 'N' and " +
						" ED.employee.isActive = 'Y' " +
						" group by " +
						" ED.department.departmentId " +
						" order by " +
						" ED.department.departmentId ");
		Query query = getSession().createQuery(sqlQuery.toString());
		
		query.setParameterList("deptList", deptList);  
		query.setParameterList("presentedCaderIdList", presentedCaderIdList);
		return query.list();
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilter(List<Long> deptList, List<Long> presentedCaderIdList){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct ED.employee.tdpCadre.tdpCadreId) " +
						" from EmployeeDepartment ED, EmployeeWorkLocation EWL " +
						" where " +
						" ED.employee.employeeId = EWL.employee.employeeId and " +
						" ED.employee.tdpCadre.tdpCadreId =  EWL.employee.tdpCadre.tdpCadreId and " +
						" EWL.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) and " +
						" ED.isDeleted = 'N' and " +
						" ED.employee.isDeleted = 'N' and " +  
						" ED.employee.isActive = 'Y' and " +
						" EWL.partyOffice.isDeleted = 'N' and" +
						" ED.department.departmentId in (:deptList)" +
						" group by " +
						" ED.department.departmentId, EWL.partyOffice.partyOfficeId " +
						" order by " +
						" ED.department.departmentId, EWL.partyOffice.partyOfficeId ");
		Query query = getSession().createQuery(sqlQuery.toString());
		query.setParameterList("presentedCaderIdList", presentedCaderIdList);  
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	
	public List<Object[]> getDepartmenWiseTotalMigratedAttendedEmployee(Date fromDate, Date toDate, List<Long> attendedExtraCadreidList, List<Long> deptList){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(" select model.department.departmentId, model.department.departmentName, count(distinct model.employee.tdpCadre.tdpCadreId) " +
						" from EmployeeDepartment model " +
						" where " +
						" model.employee.tdpCadre.tdpCadreId in (:attendedExtraCadreidList) and " +
						" model.department.departmentId in (:deptList) " +
						" group by " +
						" model.department.departmentId " +
						" order by " +
						" model.department.departmentId");
		Query query = getSession().createQuery(sqlQuery.toString());
		query.setParameterList("attendedExtraCadreidList", attendedExtraCadreidList);
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	public List<Object[]> getDepartmentWiseThenOfficeWiseTotalMigratedAttendedEmployee(List<Long> attendedExtraCadreidList, List<Long> deptList){
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EA.tdpCadre.tdpCadreId) " +
						" from EmployeeDepartment ED, EmployeeWorkLocation EWL, EventAttendee EA " +
						" where " +  
						" ED.employee.employeeId = EWL.employee.employeeId and " +
						" ED.employee.tdpCadreId = EA.tdpCadre.tdpCadreId and " +  
						" ED.employee.tdpCadre.tdpCadreId in (:attendedExtraCadreidList) and " +
						" ED.department.departmentId in (:deptList) and " +  
						//" EWL.partyOffice.event.parentEventId = 44 and " +
						" EWL.partyOffice.event.eventId = EA.event.eventId and " +  
						" ED.isDeleted = 'N' and " +  
						" ED.employee.isDeleted = 'N' and " +
						" ED.employee.isActive = 'Y' and " +
						" EWL.partyOffice.isDeleted = 'N' " +
						" group by " +
						" ED.department.departmentId, EWL.partyOffice.partyOfficeId " +
						" order by " +
						" ED.department.departmentId, EWL.partyOffice.partyOfficeId ");
		Query query = getSession().createQuery(sqlQuery.toString());
		
		query.setParameterList("attendedExtraCadreidList", attendedExtraCadreidList);
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	//Swadhin Lenka[ItGrids]
		@SuppressWarnings("unchecked")
		public List<Object[]> getDepartmentWiseTotalEmployeeListFilterForOffice(List<Long> deptList, Long officeId ){
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, count(distinct ED.employee.tdpCadre.tdpCadreId) " +
							" from EmployeeDepartment ED, EmployeeWorkLocation EWL " +
							" where " +
							" ED.isDeleted = 'N' and " +
							" ED.employee.isDeleted = 'N' and " +
							" ED.employee.isActive = 'Y' and " +
							" ED.department.departmentId in (:deptList) and " +
							" ED.employee.employeeId = EWL.employee.employeeId and " +
							" EWL.partyOffice.partyOfficeId = :officeId " +
							" group by " +
							" ED.department.departmentId " +
							" order by " +
							" ED.department.departmentId ");
			Query query = getSession().createQuery(sqlQuery.toString());
			query.setParameterList("deptList", deptList);
			query.setParameter("officeId", officeId);
			return query.list();
		}
		public List<Object[]> getDepartmentWiseTotalAttendedEmployeeFilterForOffice(List<Long> deptList, List<Long> presentedCaderIdList, Long officeId){
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, count(distinct ED.employee.tdpCadre.tdpCadreId)" +
							" from EmployeeDepartment ED, EmployeeWorkLocation EWL " +
							" where " +
							" ED.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) and " +
							" ED.department.departmentId in (:deptList) and " +
							" ED.employee.employeeId = EWL.employee.employeeId and " +
							" EWL.partyOffice.partyOfficeId = :officeId and " +
							" ED.isDeleted = 'N' and " +
							" ED.employee.isDeleted = 'N' and " +
							" ED.employee.isActive = 'Y' " +
							" group by " +
							" ED.department.departmentId " +
							" order by " +
							" ED.department.departmentId ");  
			Query query = getSession().createQuery(sqlQuery.toString());
			query.setParameter("officeId", officeId);
			query.setParameterList("deptList", deptList);  
			query.setParameterList("presentedCaderIdList", presentedCaderIdList);
			return query.list();
		}
		//Swadhin Lenka[ItGrids]
		@SuppressWarnings("unchecked")
		public List<Object[]> getDepartmentWiseThenOfficeWiseTotalAttendedEmployeeFilterForOffice(List<Long> deptList, List<Long> presentedCaderIdList, Long officeId){
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select ED.department.departmentId, ED.department.departmentName, EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct ED.employee.tdpCadre.tdpCadreId) " +
							" from EmployeeDepartment ED, EmployeeWorkLocation EWL " +
							" where " +
							" ED.employee.employeeId = EWL.employee.employeeId and " +
							" ED.employee.tdpCadre.tdpCadreId =  EWL.employee.tdpCadre.tdpCadreId and " +
							" EWL.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) and " +
							" EWL.partyOffice.partyOfficeId = :officeId and " +
							" ED.isDeleted = 'N' and " +
							" ED.employee.isDeleted = 'N' and " +  
							" ED.employee.isActive = 'Y' and " +
							" EWL.partyOffice.isDeleted = 'N' and" +
							" ED.department.departmentId in (:deptList)" + 
							" group by " +
							" ED.department.departmentId, EWL.partyOffice.partyOfficeId " +
							" order by " +
							" ED.department.departmentId, EWL.partyOffice.partyOfficeId ");
			Query query = getSession().createQuery(sqlQuery.toString());
			query.setParameterList("presentedCaderIdList", presentedCaderIdList);  
			query.setParameterList("deptList", deptList);
			query.setParameter("officeId", officeId);
			return query.list();
		}
		public List<Object[]> getAbsentCadreDtls(List<Long> cadreIdList){
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(" select distinct " +
							" ED.employee.tdpCadre.tdpCadreId, " +//0
							" ED.department.departmentId, " +//1
							" ED.department.departmentName, " +//2
							" ED.employee.tdpCadre.firstname, " +//3
							" ED.employee.tdpCadre.mobileNo " +//4
							" from " +
							" EmployeeDepartment ED " +
							" where " +
							" ED.employee.tdpCadre.tdpCadreId in (:cadreIdList) " +
							" order by " +
							" ED.department.departmentId");
			Query query = getSession().createQuery(sqlQuery.toString());
			query.setParameterList("cadreIdList", cadreIdList);
			return query.list();
		}
		
}
/*select 
distinct TC.tdp_cadre_id, D.department_id, D.department_name,TC.first_name,TC.mobile_no from employee_department ED, employee EMP, department D, tdp_cadre TC
where
ED.department_id = D.department_id and 
ED.employee_id = EMP.employee_id and 
EMP.tdp_cadre_id = TC.tdp_cadre_id and
TC.tdp_cadre_id in (5169162,5185976,5190202,5194619,5298953,5305302,5356421,5356475) 
order by 
D.department_id;*/