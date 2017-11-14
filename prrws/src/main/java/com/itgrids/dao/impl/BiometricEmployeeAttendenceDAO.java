package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IBiometricEmployeeAttendenceDAO;
import com.itgrids.model.BiometricEmployeeAttendence;

@Repository
public class BiometricEmployeeAttendenceDAO extends GenericDaoHibernate<BiometricEmployeeAttendence, Long> implements IBiometricEmployeeAttendenceDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	BiometricEmployeeAttendenceDAO(){
		super(BiometricEmployeeAttendence.class);
	}
	
	public Long getTotalPresentEmployee(Date fromDate,Date toDate) {
	    Query query  = getSession().createQuery(" select count(distinct model.empId) from BiometricEmployeeAttendence model where model.isDeleted='N' and date(model.inTime) between :fromDate and :toDate ");
	    query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return (Long) query.uniqueResult();
	}
	public List<Object[]>  getTotalBioMetricDeviceIds(Date fromDate,Date toDate) {
	     Query query  = getSession().createQuery(" select distinct model.inDeviceId,model.outDeviceId from BiometricEmployeeAttendence model where model.isDeleted='N' and date(model.inTime) between :fromDate and :toDate  ");
		 query.setDate("fromDate", fromDate);
		 query.setDate("toDate", toDate);
		 return query.list();
    }

	public Long getEmployeeAttendenceDetails(Date fromDate, Date toDate,String type,Date fromTime,Date toTime) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct model.empId) from BiometricEmployeeAttendence model where model.isDeleted='N' "
						+ " and date(model.inTime) between :fromDate and :toDate ");

		if (type.equalsIgnoreCase("before - 9:00")) {
			queryStr.append(" and time(model.inTime) < :fromTime");
		}
		if (type.equalsIgnoreCase("9:00 - 9:30") || type.equalsIgnoreCase("9:30 - 10:00") || type.equalsIgnoreCase("10:00 - 11:00")) {
			queryStr.append(" and time(model.inTime) between :fromTime and :toTime ");
		}
		if (type.equalsIgnoreCase("After - 11:00")) {
			queryStr.append(" and time(model.inTime) > :fromTime");
		}

		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if (type.equalsIgnoreCase("before - 9:00") || type.equalsIgnoreCase("After - 11:00")) {
			query.setTime("fromTime", fromTime);
		}
		if (type.equalsIgnoreCase("9:00 - 9:30") || type.equalsIgnoreCase("9:30 - 10:00") || type.equalsIgnoreCase("10:00 - 11:00")) {
			query.setTime("fromTime", fromTime);
			query.setTime("toTime", toTime);
		}

		return (Long) query.uniqueResult();
	}
	public List<Object[]> getDateWiseEmployeeAttendenceDetails(Date fromDate,Date toDate) {
	    Query query  = getSession().createQuery(" select date(model.inTime),count(distinct model.empId) from BiometricEmployeeAttendence model where model.isDeleted='N' and date(model.inTime) between :fromDate and :toDate  group by date(model.inTime)");
	    query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	public List<Object[]> getEmployeeWiseAttendenceCount(Date fromDate,Date toDate) {
		Query query = getSession().createSQLQuery(" select model.emp_id as empId,count(distinct date(model.in_time)) as count from biometric_employee_attendence model  where model.is_deleted = 'N' and date(model.in_time) between  :fromDate and :toDate group by model.emp_id")
				    .addScalar("empId", StandardBasicTypes.STRING)
				    .addScalar("count", StandardBasicTypes.LONG);
				    query.setDate("fromDate", fromDate);
					query.setDate("toDate", toDate);
		return query.list();
	}
	public Long getIndividualEmployeeAttendenceDetails(Date fromDate, Date toDate,String type,Date fromTime,Date toTime,String empId) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct date(model.in_time)) as count from biometric_employee_attendence model  where model.is_deleted = 'N' and  date(model.in_time) between  :fromDate and :toDate ");
		
		if (type.equalsIgnoreCase("before - 9:00")) {
			queryStr.append(" and time(model.in_time) < :fromTime");
		}
		if (type.equalsIgnoreCase("9:00 - 9:30") || type.equalsIgnoreCase("9:30 - 10:00") || type.equalsIgnoreCase("10:00 - 11:00")) {
			queryStr.append(" and time(model.in_time) between :fromTime and :toTime ");
		}
		if (type.equalsIgnoreCase("After - 11:00")) {
			queryStr.append(" and time(model.in_time) > :fromTime");
		}
		
		if (empId != null && empId.length() > 0) {
			queryStr.append(" and model.emp_id =:empId");
		}

		Query query = getSession().createSQLQuery(queryStr.toString())
				      .addScalar("count", StandardBasicTypes.LONG);
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if (type.equalsIgnoreCase("before - 9:00") || type.equalsIgnoreCase("After - 11:00")) {
			query.setTime("fromTime", fromTime);
		}
		if (type.equalsIgnoreCase("9:00 - 9:30") || type.equalsIgnoreCase("9:30 - 10:00") || type.equalsIgnoreCase("10:00 - 11:00")) {
			query.setTime("fromTime", fromTime);
			query.setTime("toTime", toTime);
		}
		if (empId != null && empId.length() > 0) {
			query.setParameter("empId", empId);
		}

		return (Long) query.uniqueResult();
	}
	public List<Date> getDateWiseEmployeeAttendence(Date fromDate,Date toDate,String empId) {
	    Query query  = getSession().createQuery(" select date(model.inTime) from BiometricEmployeeAttendence model" +
	    		        " where model.isDeleted='N' and date(model.inTime) between :fromDate and :toDate and  model.empId =:empId group by date(model.inTime) ");
	    query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameter("empId", empId);
		return query.list();
	}
}
