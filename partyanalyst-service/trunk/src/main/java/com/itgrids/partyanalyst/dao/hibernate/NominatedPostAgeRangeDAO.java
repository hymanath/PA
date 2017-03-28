package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INominatedPostAgeRangeDAO;
import com.itgrids.partyanalyst.model.NominatedPostAgeRange;

public class NominatedPostAgeRangeDAO extends GenericDaoHibernate<NominatedPostAgeRange, Long> implements INominatedPostAgeRangeDAO{

	public NominatedPostAgeRangeDAO() {
		super(NominatedPostAgeRange.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getAllAgeRanges(){
		Query query = getSession().createQuery("select model.nominatedPostAgeRangeId,model.ageRange" +
									" from NominatedPostAgeRange model");
		return query.list();
	}
	
	public List<Object[]> getAllAgeRangesByOrder(){
		Query query = getSession().createQuery("select model.nominatedPostAgeRangeId,model.ageRange" +
									" from NominatedPostAgeRange model order by model.orderId");
		return query.list();
	}
}
