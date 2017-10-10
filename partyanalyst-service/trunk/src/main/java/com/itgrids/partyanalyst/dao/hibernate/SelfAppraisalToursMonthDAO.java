package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.ISelfAppraisalToursMonthDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalToursMonth;


public class SelfAppraisalToursMonthDAO extends GenericDaoHibernate<SelfAppraisalToursMonth, Long> implements ISelfAppraisalToursMonthDAO {

	public SelfAppraisalToursMonthDAO() {
		super(SelfAppraisalToursMonth.class);
	}
	
	public List<String> getMonthAndYear(Date fromDate,Date toDate){
		 StringBuilder queryStr = new StringBuilder();
		 
		 queryStr.append(" select CONCAT(DATE_FORMAT(m1, '%m'),'-',year(m1)) as monthYear " +
							"	from ( " +
								"	select " +
								"	(:fromDate - INTERVAL DAYOFMONTH(:fromDate)-1 DAY)  " +
								"		+INTERVAL m MONTH as m1 " +
								"	from ( " +
									"	select @rownum /*'*/:=/*'*/ @rownum+1 as m from " +
									"	(select 1 union select 2 union select 3 union select 4 union select 5) t1, " +
									" 	(select 1 union select 2 union select 3 union select 4 union select 5) t2, " +
									" 	(select 1 union select 2 union select 3 union select 4 union select 5) t3,  " +
									" 	(select 1 union select 2 union select 3 union select 4 union select 5) t4, " +
									" 	(select @rownum /*'*/:=/*'*/ -1) t0  " +
									"  	) d1 " +
								" ) d2 " +
								"  where m1<=:toDate " +
								" order by m1");
		 
		   Session session = getSession();
	       SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	       sqlQuery.addScalar("monthYear",Hibernate.STRING);
	       sqlQuery.setDate("fromDate", fromDate);
	       sqlQuery.setDate("toDate", toDate);
	       return sqlQuery.list();
	}
	public List<Long> getMonthYearByTourMonths(List<String> monthYearList){
		StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.selfAppraisalToursMonthId from SelfAppraisalToursMonth model");
		 if(monthYearList != null && monthYearList.size() > 0){
			 queryStr.append(" where model.toursMonth in(:monthYearList) ");
		 }
		 Query query = getSession().createQuery(queryStr.toString());
		 if(monthYearList != null && monthYearList.size() > 0){
			 query.setParameterList("monthYearList", monthYearList);
		 }
		  return query.list();
	}
	public List<Object[]> getMonthDtls(List<String> monthYearList){
		StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.selfAppraisalToursMonthId,model.monthName,model.year from SelfAppraisalToursMonth model");
		 if(monthYearList != null && monthYearList.size() > 0){
			 queryStr.append(" where model.toursMonth in(:monthYearList) ");
		 }
		 Query query = getSession().createQuery(queryStr.toString());
		 if(monthYearList != null && monthYearList.size() > 0){
			 query.setParameterList("monthYearList", monthYearList);
		 }
		  return query.list();
	}
public List<Long> getSelfAppraisalToursMonth(String toursMonth){
		
		Query query = getSession().createQuery(" select model.selfAppraisalToursMonthId from " +
				"  SelfAppraisalToursMonth model " +
				" where model.toursMonth = :toursMonth ");
		
		query.setParameter("toursMonth", toursMonth.trim());
		
		return query.list();
	}

	public List<String> getSelfAppraisalMonthById(Long selfAppraisalToursMonthId){
		
		Query query = getSession().createQuery(" select model.toursMonth from " +
				"  SelfAppraisalToursMonth model " +
				" where model.selfAppraisalToursMonthId = :selfAppraisalToursMonthId ");
		
		query.setParameter("selfAppraisalToursMonthId", selfAppraisalToursMonthId);
		
		return query.list();
	}

public List<Long> getLatestMonthYearId(){
	Query query = getSession().createQuery(" select distinct model.selfAppraisalToursMonth.selfAppraisalToursMonthId from SelfAppraisalDesignationTarget model where model.isActive='Y' order by model.selfAppraisalToursMonth.year desc,model.selfAppraisalToursMonth.monthNo desc ");
	query.setMaxResults(2);
	return query.list();
}
public List<Object[]> getMonthAndYearDtls(List<String> monthYearList){
	StringBuilder queryStr = new StringBuilder();
	 queryStr.append(" select model.selfAppraisalToursMonthId,model.monthName,model.year from SelfAppraisalToursMonth model ");
	 if(monthYearList != null && monthYearList.size() > 0){
		 queryStr.append(" where model.toursMonth in(:monthYearList) ");
	 }
	 queryStr.append(" order by model.monthNo desc ");
	 Query query = getSession().createQuery(queryStr.toString());
	 if(monthYearList != null && monthYearList.size() > 0){
		 query.setParameterList("monthYearList", monthYearList);
	 }
	  return query.list();
}
}
