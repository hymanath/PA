package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateDetailsNewDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateDetailsNew;

public class SelfAppraisalCandidateDetailsNewDAO extends GenericDaoHibernate<SelfAppraisalCandidateDetailsNew, Long> implements ISelfAppraisalCandidateDetailsNewDAO {

	public SelfAppraisalCandidateDetailsNewDAO() {
		super(SelfAppraisalCandidateDetailsNew.class);
	}
	
	public List<String> getMonthAndYearOfBetweenDates(Date fromDate,Date toDate){
		
		Query query = getSession().createSQLQuery(" select CONCAT(Month(aDate),'-',YEAR(aDate)) as monthYear from ( "+
						"  select @maxDate - interval (a.a + (10 * b.a) + (100 * c.a)) month as aDate from "+
						 " (select 0 as a union all select 1 union all select 2 union all select 3 "+
						 "  union all select 4 union all select 5 union all select 6 union all "+
						  " select 7 union all select 8 union all select 9) a, "+
						 " (select 0 as a union all select 1 union all select 2 union all select 3 "+
						  " union all select 4 union all select 5 union all select 6 union all "+
						 "  select 7 union all select 8 union all select 9) b, "+
						 " (select 0 as a union all select 1 union all select 2 union all select 3 "+
						 "  union all select 4 union all select 5 union all select 6 union all "+
						 "  select 7 union all select 8 union all select 9) c, "+
						 " (select @minDate /*'*/:=/*'*/ :fromDate, @maxDate /*'*/:=/*'*/ :toDate) d "+
						" ) e "+
						" where aDate between @minDate and @maxDate "+
						" ORDER BY YEAR(aDate),Month(aDate); ")
						.addScalar("monthYear",Hibernate.STRING); 
						query.setDate("fromDate", fromDate);
						query.setDate("toDate", toDate);
		
						return query.list();
		
	}
	
}
