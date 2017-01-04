package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalToursMonthDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalToursMonth;


public class SelfAppraisalToursMonthDAO extends GenericDaoHibernate<SelfAppraisalToursMonth, Long> implements ISelfAppraisalToursMonthDAO {

	public SelfAppraisalToursMonthDAO() {
		super(SelfAppraisalToursMonth.class);
	}
	
	public List<Long> getSelfAppraisalToursMonth(String toursMonth){
		
		Query query = getSession().createQuery(" select model.selfAppraisalToursMonthId from " +
				"  SelfAppraisalToursMonth model " +
				" where model.toursMonth = :toursMonth ");
		
		query.setParameter("toursMonth", toursMonth.trim());
		
		return query.list();
	}
	
}
