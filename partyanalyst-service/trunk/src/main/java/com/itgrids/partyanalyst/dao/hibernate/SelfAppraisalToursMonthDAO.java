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
		 queryStr.append(" select CONCAT(Month(aDate),'-',YEAR(aDate)) as monthYear from ( " +
				 "  select @maxDate - interval (a.a + (10 * b.a) + (100 * c.a)) month as aDate from"+
				 " (select 0 as a union all select 1 union all select 2 union all select 3"+
				 " union all select 4 union all select 5 union all select 6 union all"+
				 " select 7 union all select 8 union all select 9) a,"+
				 " (select 0 as a union all select 1 union all select 2 union all select 3"+
				 " union all select 4 union all select 5 union all select 6 union all"+
				 " select 7 union all select 8 union all select 9) b,"+
				 " (select 0 as a union all select 1 union all select 2 union all select 3"+
				 " union all select 4 union all select 5 union all select 6 union all"+
				 " select 7 union all select 8 union all select 9) c,"+
				 " (select @minDate /*'*/:=/*'*/ :fromDate, @maxDate /*'*/:=/*'*/ :toDate) d) e"+
				 " where aDate between @minDate and @maxDate"+
				 " ORDER BY YEAR(aDate),Month(aDate);");
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
public List<Long> getSelfAppraisalToursMonth(String toursMonth){
		
		Query query = getSession().createQuery(" select model.selfAppraisalToursMonthId from " +
				"  SelfAppraisalToursMonth model " +
				" where model.toursMonth = :toursMonth ");
		
		query.setParameter("toursMonth", toursMonth.trim());
		
		return query.list();
	}
	
}
