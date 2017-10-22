package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMarginVotesRangeDAO;
import com.itgrids.partyanalyst.model.MarginVotesRange;

public class MarginVotesRangeDAO extends GenericDaoHibernate<MarginVotesRange, Long> implements IMarginVotesRangeDAO {

	public MarginVotesRangeDAO() {
		super(MarginVotesRange.class);
		
	}

	public List<Object[]> getMarginVotesAgeRangeDetails(){
		
		Query query = getSession().createQuery(" select model.rangeValue,model.description,model.minValue,model.maxValue,model.orderNo from MarginVotesRange model where model.isDeleted = 'N'  order by model.orderNo " );
		return query.list();
	}
}
