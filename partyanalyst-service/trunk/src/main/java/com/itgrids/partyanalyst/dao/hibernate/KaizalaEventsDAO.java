package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaEventsDAO;
import com.itgrids.partyanalyst.model.KaizalaEvents;

public class KaizalaEventsDAO extends GenericDaoHibernate<KaizalaEvents, Long> implements IKaizalaEventsDAO{

	public KaizalaEventsDAO() {
		super(KaizalaEvents.class);
		
	}

	public Long getEventId(String eventType) {
		Query query = getSession().createQuery(" select model.kaizalaEventsId "
				+ " from KaizalaEvents model "
				+ " where model.event=:eventType ");
		query.setParameter("eventType", eventType);
		return (Long) query.uniqueResult();
	}
	
}
