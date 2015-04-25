package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.model.Event;

public class EventDAO extends GenericDaoHibernate<Event, Long> implements IEventDAO{

	public EventDAO( ) {
		super(Event.class);
	}

	public List<Object[]> getEventsForUser(Long userId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.eventId, model.name from Event model where model.parentEventId is null ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		return query.list();
	}
}
