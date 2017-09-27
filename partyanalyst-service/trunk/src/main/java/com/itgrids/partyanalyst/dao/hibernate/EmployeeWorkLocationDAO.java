package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

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
	public List<Object[]> getOfficeWiseTotalNonAttendedEmployeeDetailsFilter(List<Long> deptList, List<Long> presentedCaderIdList){
		Query query = getSession().createQuery(" select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, ED.department.departmentName, EWL.employee.tdpCadre.tdpCadreId  " +
											   " from EmployeeWorkLocation EWL, EmployeeDepartment ED " +    
											   " where " +
											   " EWL.employee.tdpCadre.tdpCadreId not in (:presentedCaderIdList) and " +
											   " ED.employee.employeeId = EWL.employee.employeeId and" +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' and " +
											   " ED.department.departmentId in (:deptList) " +  
											   " order by " + 
											   " EWL.partyOffice.partyOfficeId, ED.department.departmentId ");
		query.setParameterList("presentedCaderIdList", presentedCaderIdList);
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeDetailsFilter(Date fromDate, Date toDate, List<Long> deptList, List<Long> presentedCaderIdList){
		Query query = getSession().createQuery(" select " +
											   " EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, " +
											   " EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, " +
											   " min(EA.attendedTime), ED.department.departmentName, EWL.employee.tdpCadre.tdpCadreId " +
											   " from EmployeeWorkLocation EWL, EventAttendee EA, EmployeeDepartment ED " +
											   " where " +
											   //" EWL.partyOffice.event.parentEventId = 44 and " +
											   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
											   " ED.employee.employeeId = EWL.employee.employeeId and " +
											   " ED.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and" +
											   " EA.event.eventId in (14,42,25) and " +  
											   " date(EA.attendedTime) between :fromDate and :toDate and " +  
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' and " +
											   " ED.department.departmentId in (:deptList) and " +
											   " ED.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) " +  
											   " group by" +
											   " EWL.employee.tdpCadre.tdpCadreId" +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId, ED.department.departmentId, EA.attendedTime");
		
		query.setParameterList("presentedCaderIdList", presentedCaderIdList);
		query.setParameterList("deptList", deptList);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalEmployeeListFilter(List<Long> deptList){
		Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EWL.employeeWorkLocationId) " +
											   " from EmployeeWorkLocation EWL, EmployeeDepartment ED "+
											   " where " +
											   " ED.employee.employeeId = EWL.employee.employeeId and " +
											   " ED.department.departmentId in (:deptList) and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " group by " +
											   " EWL.partyOffice.partyOfficeId " +
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		query.setParameterList("deptList", deptList);
		return query.list();
	}
	//Swadhin Lenka[ItGrids]
	@SuppressWarnings("unchecked")
	public List<Object[]> getOfficeWiseTotalAttendedEmployeeFilter(List<Long> deptList, List<Long> presentedCaderIdList){
		Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EWL.employee.tdpCadre.tdpCadreId) " +
											   " from EmployeeWorkLocation EWL, EmployeeDepartment ED " +
											   " where " +
											   " ED.department.departmentId in (:deptList) and " +  
											   " EWL.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) and "+  
											   " EWL.employee.tdpCadre.tdpCadreId = ED.employee.tdpCadre.tdpCadreId and " +
											   " EWL.employee.isDeleted = 'N' and " +
											   " EWL.employee.isActive = 'Y' and " +
											   " EWL.partyOffice.isDeleted = 'N' " +
											   " group by " +  
											   " EWL.partyOffice.partyOfficeId " +  
											   " order by " +
											   " EWL.partyOffice.partyOfficeId ");
		
		query.setParameterList("deptList", deptList);
		query.setParameterList("presentedCaderIdList", presentedCaderIdList);  
		return query.list();
	} 
	
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
	//Swadhin Lenka[ItGrids]
		@SuppressWarnings("unchecked")
		public List<Object[]> getSpecificOfficeTotalEmployeeListFilter(List<Long> deptList, Long officeId){
			Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EWL.employeeWorkLocationId) " +
												   " from EmployeeWorkLocation EWL, EmployeeDepartment ED "+
												   " where " +
												   " ED.employee.employeeId = EWL.employee.employeeId and " +
												   " ED.department.departmentId in (:deptList) and " +
												   " EWL.partyOffice.partyOfficeId = :officeId and " +
												   " EWL.employee.isDeleted = 'N' and " +
												   " EWL.employee.isActive = 'Y' and " +
												   " EWL.partyOffice.isDeleted = 'N' " +
												   " group by " +
												   " EWL.partyOffice.partyOfficeId " +
												   " order by " +
												   " EWL.partyOffice.partyOfficeId ");
			query.setParameterList("deptList", deptList);
			query.setParameter("officeId", officeId);
			return query.list();
		}
		//Swadhin Lenka[ItGrids]
		@SuppressWarnings("unchecked")
		public List<Object[]> getSpecificOfficeTotalAttendedEmployeeFilter(List<Long> deptList, List<Long> presentedCaderIdList, Long officeId){
			Query query = getSession().createQuery(" select EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, count(distinct EWL.employee.tdpCadre.tdpCadreId) " +
												   " from EmployeeWorkLocation EWL, EmployeeDepartment ED " +
												   " where " +
												   " ED.department.departmentId in (:deptList) and " +  
									 			   " EWL.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) and " +
												   " EWL.partyOffice.partyOfficeId = :officeId and "+  
												   " EWL.employee.tdpCadre.tdpCadreId = ED.employee.tdpCadre.tdpCadreId and " +
												   " EWL.employee.isDeleted = 'N' and " +
												   " EWL.employee.isActive = 'Y' and " +
												   " EWL.partyOffice.isDeleted = 'N' " +
												   " group by " +  
												   " EWL.partyOffice.partyOfficeId " +  
												   " order by " +
												   " EWL.partyOffice.partyOfficeId ");
			
			query.setParameterList("deptList", deptList);
			query.setParameter("officeId", officeId); 
			query.setParameterList("presentedCaderIdList", presentedCaderIdList);  
			return query.list();
		} 
		//Swadhin Lenka[ItGrids]
		@SuppressWarnings("unchecked")
		public List<Object[]> getspecificOfficeTotalNonAttendedEmployeeDetailsFilter(List<Long> deptList, List<Long> presentedCaderIdList, Long officeId){
			if(presentedCaderIdList != null && presentedCaderIdList.size()>0 && presentedCaderIdList != null && deptList.size()>0 && officeId != null && officeId.longValue()>0L){
				Query query = getSession().createQuery(" select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, ED.department.departmentName, EWL.employee.tdpCadre.tdpCadreId  " +
						   " from EmployeeWorkLocation EWL, EmployeeDepartment ED " +    
						   " where " +
						   " EWL.employee.tdpCadre.tdpCadreId not in (:presentedCaderIdList) and " +
						   " ED.employee.employeeId = EWL.employee.employeeId and" +
						   " EWL.employee.isDeleted = 'N' and " +
						   " EWL.employee.isActive = 'Y' and " +
						   " EWL.partyOffice.isDeleted = 'N' and " +
						   " ED.department.departmentId in (:deptList) and " +
						   " EWL.partyOffice.partyOfficeId = :officeId " +  
						   " order by " + 
						   " EWL.partyOffice.partyOfficeId, ED.department.departmentId ");
				query.setParameterList("presentedCaderIdList", presentedCaderIdList);
				query.setParameterList("deptList", deptList);
				query.setParameter("officeId", officeId);
				return query.list();
			}
			return null;
		}
		
		//Swadhin Lenka[ItGrids]
		@SuppressWarnings("unchecked")
		public List<Object[]> getSpecificOfficeTotalAttendedEmployeeDetailsFilter(Date fromDate, Date toDate, List<Long> deptList, List<Long> presentedCaderIdList, Long officeId){
			Query query = getSession().createQuery(" select distinct EWL.partyOffice.partyOfficeId, EWL.partyOffice.officeName, EWL.employee.tdpCadre.firstname, EWL.employee.tdpCadre.mobileNo, min(EA.attendedTime), ED.department.departmentName, EWL.employee.tdpCadre.tdpCadreId " +
												   " from EmployeeWorkLocation EWL, EventAttendee EA, EmployeeDepartment ED " +
												   " where " +
												   //" EWL.partyOffice.event.parentEventId = 44 and " +
												   " EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
												   " ED.employee.employeeId = EWL.employee.employeeId and " +
												   " ED.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and" +
												   " EA.event.eventId in (14,42,25) and " +  
												   " date(EA.attendedTime) between :fromDate and :toDate and " +  
												   " EWL.employee.isDeleted = 'N' and " +
												   " EWL.employee.isActive = 'Y' and " +
												   " EWL.partyOffice.isDeleted = 'N' and " +
												   " ED.department.departmentId in (:deptList) and " +
												   " ED.employee.tdpCadre.tdpCadreId in (:presentedCaderIdList) and " +
												   " EWL.partyOffice.partyOfficeId = :officeId " +  
												   " group by" +
												   " EWL.employee.tdpCadre.tdpCadreId" +
												   " order by " +
												   " EWL.partyOffice.partyOfficeId, ED.department.departmentId, EA.attendedTime");
			
			query.setParameterList("presentedCaderIdList", presentedCaderIdList);
			query.setParameterList("deptList", deptList);
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			query.setParameter("officeId", officeId);
			return query.list();
		}
		public List<Object[]> getAttendanceCountBetweenDatesOfficeWise(Date fromDate, Date toDate, List<Long> officeIdList, List<Long> deptIdList){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +
							" D.department_id as deptId, " +//0
							" D.department_name as deptName, " +//1
							" TC.tdp_cadre_id as cadreId , " +//2
							" TC.first_name as name , " +//3
							" TC.mobile_no as mobile , " +//4
							" count(distinct date(EA.attended_time)) as count , " +//5
							" min(time(EA.attended_time)) as time " +//6
							" from "+
							" employee_work_location EWL, " +
							" employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC "+
							" where "+
							" EWL.party_office_id = PO.party_office_id and "+
							" PO.party_office_id in (:officeIdList) and "+
							" EWL.employee_id = EMP.employee_id and "+
							" EWL.employee_id = ED.employee_id and "+
							" ED.department_id = D.department_id and "+
							" D.department_id in (:deptIdList) and "+
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and "+
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and "+
							" (date(EA.attended_time) between :fromDate and :toDate) and "+
							" EWL.is_deleted = 'N' and "+
							" PO.is_deleted = 'N' and "+
							" EMP.is_delete = 'N' and " +
							" EMP.is_active = 'Y' and "+
							" TC.is_deleted = 'N' and "+
							" EA.event_id in (14,42,25) "+  
							" group by " +
							" TC.tdp_cadre_id " +
							" order by " +
							" D.department_id ");  
			/*queryStr.append(" select " +
							" ED.department.departmentId, " +//0
							" ED.department.departmentName, " +//1
							" EWL.employee.tdpCadre.tdpCadreId, " +//2
							" EWL.employee.tdpCadre.firstname, " +//3
							" EWL.employee.tdpCadre.mobileNo, " +//4
							" count(distinct date(EA.attendedTime)), " +//5  
							" min(time(EA.attendedTime)) " +  //6  
							" from " +
							" EmployeeWorkLocation EWL, " +
							" EventAttendee EA, " +
							" EmployeeDepartment ED " +
							" where " +
							" EWL.partyOffice.partyOfficeId in (:officeIdList) and " +
							" EWL.employee.tdpCadre.tdpCadreId = EA.tdpCadre.tdpCadreId and " +
							" ED.employee.employeeId = EWL.employee.employeeId and " +
							" ED.department.departmentId in (:deptIdList) and" +
							" date(EA.attendedTime) between :fromDate and :toDate and " +
							" EWL.isDeleted = 'N' and " +
							" EWL.partyOffice.isDeleted = 'N' and " +
							" EWL.employee.isDeleted = 'N' and " +
							" EWL.employee.tdpCadre.isDeleted = 'N' and " +  
							" ED.isDeleted = 'N' and " +
							" EA.eventId in (14,25,42) " +  
							" group by " +
							" EWL.employee.tdpCadre.tdpCadreId " +
							" order by " +
							" ED.department.departmentId ");*/
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("deptId", Hibernate.LONG)
					.addScalar("deptName", Hibernate.STRING)
					.addScalar("cadreId", Hibernate.LONG)
					.addScalar("name", Hibernate.STRING)
					.addScalar("mobile", Hibernate.STRING)
					.addScalar("count", Hibernate.LONG)
					.addScalar("time", Hibernate.STRING);
			
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			query.setParameterList("officeIdList", officeIdList);
			query.setParameterList("deptIdList",deptIdList);
			return query.list();      
			
		}
		public List<Long> getEmployeeIdListOfficeWise(List<Long> officeIdList, List<Long> deptIdList){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select EWL.employee.tdpCadre.tdpCadreId " +
							" from " +
							" EmployeeWorkLocation EWL, EmployeeDepartment ED " +
							" where " +
							" EWL.partyOffice.partyOfficeId in (:officeIdList) and " +
							" EWL.employee.employeeId = ED.employee.employeeId and " +
							" ED.department.departmentId in (:deptIdList) and " +
							" EWL.employee.isDeleted = 'N' and " +  
							" EWL.employee.isActive = 'Y' and " +
							" ED.isDeleted = 'N' ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("officeIdList",officeIdList);
			query.setParameterList("deptIdList",deptIdList);       
			return (List<Long>)query.list();   
		}
		public List<Object[]> getEmployeeLateComingsCount(Date fromDate, Date toDate, List<Long> officeIdList, List<Long> deptIdList){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select tdp_cadre_id as id ,count(time)  as count from ( " +
							" select min(EA.attended_time) time,TC.tdp_cadre_id from " +
							" employee_work_location EWL, employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC  " +
							" where  " +
							" EWL.party_office_id = PO.party_office_id and   " +
							" PO.party_office_id in (:officeIdList) and  " +
							" EWL.employee_id = EMP.employee_id and  " +
							" EWL.employee_id = ED.employee_id and  " +
							" ED.department_id = D.department_id and  " +
							" D.department_id in (:deptIdList) and  " +
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and  " +
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and  " +
							" (date(EA.attended_time) between :fromDate and :toDate ) and  " +
							" EWL.is_deleted = 'N' and  " +
							" PO.is_deleted = 'N' and  " +  
							" EMP.is_delete = 'N' and  " +
							" TC.is_deleted = 'N' and  " +
							" EA.event_id in (14,42,25)   " +
							" group by date(EA.attended_time),TC.tdp_cadre_id order by date(EA.attended_time)) sa where  " +
							" time(time) > '10:30:00' " +
							" group by tdp_cadre_id; " );
							
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("id", Hibernate.LONG)
					.addScalar("count", Hibernate.LONG);
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			
			query.setParameterList("officeIdList", officeIdList);
			query.setParameterList("deptIdList",deptIdList);
			return query.list();      
		}
		public List<Object[]> getDayWisePresentCount(List<Long> officeIdList, List<Long> deptIdList, Date fromDate, Date toDate){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select date(EA.attended_time) as time,count(distinct TC.tdp_cadre_id) as id" +
							" from "+
							" employee_work_location EWL, employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC "+
							" where "+
							" EWL.party_office_id = PO.party_office_id and  "+
							" PO.party_office_id in (:officeIdList) and "+
							" EWL.employee_id = EMP.employee_id and "+
							" EWL.employee_id = ED.employee_id and "+
							" ED.department_id = D.department_id and "+
							" D.department_id in (:deptIdList) and  "+
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and "+
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and "+
							" (date(EA.attended_time) between :fromDate and :toDate) and "+
							" EWL.is_deleted = 'N' and "+
							" PO.is_deleted = 'N' and "+
							" EMP.is_delete = 'N' and "+
							"TC.is_deleted = 'N' and "+
							" EA.event_id in (14,42,25) "+
							" group by date(EA.attended_time) order by date(EA.attended_time); ");
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("time", Hibernate.STRING)
					.addScalar("id", Hibernate.LONG);
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			query.setParameterList("officeIdList", officeIdList);
			query.setParameterList("deptIdList",deptIdList);
			return query.list();      
			
		}
		public List<Object[]> getTimeWisePresentCount(Long officeIdList, Long deptIdList, Date fromDate, Date toDate, Date fromTime, Date toTime){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select date(time) as everyDay ,count(distinct tdp_cadre_id)  as count from ( " +
							" select min(EA.attended_time) time,TC.tdp_cadre_id from " +
							" employee_work_location EWL, employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC  " +
							" where  " +
							" EWL.party_office_id = PO.party_office_id and   " +
							" PO.party_office_id = :officeIdList and  " +
							" EWL.employee_id = EMP.employee_id and  " +
							" EWL.employee_id = ED.employee_id and  " +
							" ED.department_id = D.department_id and  " +
							" D.department_id = :deptIdList and  " +
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and  " +
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and  " +
							" (date(EA.attended_time) between :fromDate and :toDate ) and  " +
							" EWL.is_deleted = 'N' and  " +
							" PO.is_deleted = 'N' and  " +
							" EMP.is_delete = 'N' and  " +
							" TC.is_deleted = 'N' and  " +
							" EA.event_id in (14,42,25)   " +
							" group by date(EA.attended_time),TC.tdp_cadre_id order by date(EA.attended_time)) sa where  " );
							if(fromTime != null && toTime != null){
								queryStr.append(" time(time) between :fromTime and :toTime ");
							}else if(fromTime != null && toTime == null){
								queryStr.append(" time(time) < :fromTime ");
							}else if(fromTime == null && toTime != null){
								queryStr.append(" time(time) > :toTime ");  
							} 
			queryStr.append(" group by date(time); ");
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("everyDay", Hibernate.STRING)
					.addScalar("count", Hibernate.LONG);
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			if(fromTime != null && toTime != null){
				query.setTime("fromTime", fromTime);
				query.setTime("toTime", toTime);  
			}else if(fromTime != null && toTime == null){
				query.setTime("fromTime", fromTime);
			}else if(fromTime == null && toTime != null){
				query.setTime("toTime", toTime);  
			} 
			
			query.setParameter("officeIdList", officeIdList);
			query.setParameter("deptIdList",deptIdList);
			return query.list();      
			
		}
		public List<Object[]> getAttendanceReportTimeToTime(Long officeIdList, Long deptIdList, Date fromDate, Date toDate, Date fromTime, Date toTime){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select tdp_cadre_id as id ,count(time)  as count from ( " +
							" select min(EA.attended_time) time,TC.tdp_cadre_id from " +
							" employee_work_location EWL, employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC  " +
							" where  " +
							" EWL.party_office_id = PO.party_office_id and   " +
							" PO.party_office_id = :officeIdList and  " +
							" EWL.employee_id = EMP.employee_id and  " +
							" EWL.employee_id = ED.employee_id and  " +
							" ED.department_id = D.department_id and  " +
							" D.department_id = :deptIdList and  " +
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and  " +
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and  " +
							" (date(EA.attended_time) between :fromDate and :toDate ) and  " +
							" EWL.is_deleted = 'N' and  " +
							" PO.is_deleted = 'N' and  " +  
							" EMP.is_delete = 'N' and  " +
							" TC.is_deleted = 'N' and  " +
							" EA.event_id in (14,42,25)   " +
							" group by date(EA.attended_time),TC.tdp_cadre_id order by date(EA.attended_time)) sa where  " );
							if(fromTime != null && toTime != null){
								queryStr.append(" time(time) between :fromTime and :toTime ");
							}else if(fromTime != null && toTime == null){
								queryStr.append(" time(time) <= :fromTime ");
							}else if(fromTime == null && toTime != null){
								queryStr.append(" time(time) >= :toTime ");  
							} 
			queryStr.append(" group by tdp_cadre_id;");
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("id", Hibernate.LONG)
					.addScalar("count", Hibernate.LONG);
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			if(fromTime != null && toTime != null){
				query.setTime("fromTime", fromTime);
				query.setTime("toTime", toTime);  
			}else if(fromTime != null && toTime == null){
				query.setTime("fromTime", fromTime);
			}else if(fromTime == null && toTime != null){
				query.setTime("toTime", toTime);  
			} 
			
			query.setParameter("officeIdList", officeIdList);
			query.setParameter("deptIdList",deptIdList);
			return query.list();      
			
			
		}
		public List<Object[]> getDayWisePresentCountForEmp(List<Long> officeIdList, List<Long> deptIdList, Date fromDate, Date toDate, Long cadreId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select date(EA.attended_time) as time,count(distinct TC.tdp_cadre_id) as id" +
							" from "+
							" employee_work_location EWL, employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC "+
							" where "+
							" EWL.party_office_id = PO.party_office_id and  "+
							" PO.party_office_id in (:officeIdList) and "+
							" EWL.employee_id = EMP.employee_id and "+
							" EWL.employee_id = ED.employee_id and "+
							" ED.department_id = D.department_id and "+
							" D.department_id in (:deptIdList) and  "+
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and "+
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and " +
							" TC.tdp_cadre_id = :cadreId and "+
							" (date(EA.attended_time) between :fromDate and :toDate) and "+
							" EWL.is_deleted = 'N' and "+
							" PO.is_deleted = 'N' and "+
							" EMP.is_delete = 'N' and "+
							"TC.is_deleted = 'N' and "+
							" EA.event_id in (14,42,25) "+
							" group by date(EA.attended_time) order by date(EA.attended_time); ");
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("time", Hibernate.STRING)
					.addScalar("id", Hibernate.LONG);
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			query.setParameterList("officeIdList", officeIdList);
			query.setParameterList("deptIdList",deptIdList);
			query.setParameter("cadreId",cadreId);
			return query.list();      
			
		}
		public List<Object[]> getTimeWisePresentCountForEmp(Long officeIdList, Long deptIdList, Date fromDate, Date toDate, Date fromTime, Date toTime,Long cadreId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select date(time) as everyDay ,count(distinct tdp_cadre_id)  as count from ( " +
							" select min(EA.attended_time) time,TC.tdp_cadre_id from " +
							" employee_work_location EWL, employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC  " +
							" where  " +
							" EWL.party_office_id = PO.party_office_id and   " +
							" PO.party_office_id = :officeIdList and  " +
							" EWL.employee_id = EMP.employee_id and  " +
							" EWL.employee_id = ED.employee_id and  " +
							" ED.department_id = D.department_id and  " +
							" D.department_id = :deptIdList and  " +
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and  " +
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and  " +
							" TC.tdp_cadre_id = :cadreId and " +
							" (date(EA.attended_time) between :fromDate and :toDate ) and  " +
							" EWL.is_deleted = 'N' and  " +
							" PO.is_deleted = 'N' and  " +
							" EMP.is_delete = 'N' and  " +
							" TC.is_deleted = 'N' and  " +
							" EA.event_id in (14,42,25)   " +
							" group by date(EA.attended_time),TC.tdp_cadre_id order by date(EA.attended_time)) sa where  " );
							if(fromTime != null && toTime != null){
								queryStr.append(" time(time) between :fromTime and :toTime ");
							}else if(fromTime != null && toTime == null){
								queryStr.append(" time(time) <= :fromTime ");
							}else if(fromTime == null && toTime != null){
								queryStr.append(" time(time) >= :toTime ");  
							} 
			queryStr.append(" group by date(time); ");
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("everyDay", Hibernate.STRING)
					.addScalar("count", Hibernate.LONG);
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
			if(fromTime != null && toTime != null){
				query.setTime("fromTime", fromTime);
				query.setTime("toTime", toTime);  
			}else if(fromTime != null && toTime == null){
				query.setTime("fromTime", fromTime);
			}else if(fromTime == null && toTime != null){
				query.setTime("toTime", toTime);  
			} 
			
			query.setParameter("officeIdList", officeIdList);
			query.setParameter("deptIdList",deptIdList);
			query.setParameter("cadreId",cadreId);  
			return query.list();      
			
		}
		public List<Object[]> getAttendanceCountInDatesOfficeWise(List<String> datesList, List<Long> officeIdList, List<Long> deptIdList){
			StringBuilder queryStr = new StringBuilder();  
			queryStr.append(" select " +
							" D.department_id as deptId, " +//0
							" D.department_name as deptName, " +//1
							" TC.tdp_cadre_id as cadreId , " +//2
							" TC.first_name as name , " +//3
							" TC.mobile_no as mobile , " +//4
							" count(distinct date(EA.attended_time)) as count , " +//5
							" min(time(EA.attended_time)) as time " +//6
							" from "+
							" employee_work_location EWL, " +
							" employee_department ED, department D, party_office PO, employee EMP, event_attendee EA, tdp_cadre TC "+
							" where "+
							" EWL.party_office_id = PO.party_office_id and "+
							" PO.party_office_id in (:officeIdList) and "+
							" EWL.employee_id = EMP.employee_id and "+
							" EWL.employee_id = ED.employee_id and "+
							" ED.department_id = D.department_id and "+
							" D.department_id in (:deptIdList) and "+
							" EMP.tdp_cadre_id = EA.tdp_cadre_id and "+
							" EMP.tdp_cadre_id = TC.tdp_cadre_id and "+
							" (date(EA.attended_time) in (:datesList)) and "+
							" EWL.is_deleted = 'N' and "+
							" PO.is_deleted = 'N' and "+
							" EMP.is_delete = 'N' and " +
							" EMP.is_active = 'Y' and "+
							" TC.is_deleted = 'N' and "+
							" EA.event_id in (14,42,25) "+  
							" group by " +
							" TC.tdp_cadre_id " +
							" order by " +
							" D.department_id ");    
			
			SQLQuery query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("deptId", Hibernate.LONG)
					.addScalar("deptName", Hibernate.STRING)
					.addScalar("cadreId", Hibernate.LONG)
					.addScalar("name", Hibernate.STRING)
					.addScalar("mobile", Hibernate.STRING)
					.addScalar("count", Hibernate.LONG)
					.addScalar("time", Hibernate.STRING);  
			
			query.setParameterList("datesList", datesList);
			
			query.setParameterList("officeIdList", officeIdList);
			query.setParameterList("deptIdList",deptIdList);
			return query.list();      
			
		}
		
}

