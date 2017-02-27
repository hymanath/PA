package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.model.NominatedPostGovtOrder;

public class NominatedPostGovtOrderDAO extends GenericDaoHibernate<NominatedPostGovtOrder, Long> implements INominatedPostGovtOrderDAO{

	public NominatedPostGovtOrderDAO() {
		super(NominatedPostGovtOrder.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> gettingGoPassedDates(Long nominatedPostId ){
		Query query = getSession().createQuery("select distinct model.nominatedPost.nominatedPostId,date(model.govtOrder.fromDate)," +
				                               " date(model.govtOrder.toDate),model.govtOrder.govtOrderId " +
				   							   " from NominatedPostGovtOrder model " +
				   							   " where  model.nominatedPost.nominatedPostId =:nominatedPostId and " +
				   							   " model.isDeleted = 'N' and model.govtOrder.isDeleted = 'N' ");
		query.setParameter("nominatedPostId", nominatedPostId);
		return query.list();
	}

}
