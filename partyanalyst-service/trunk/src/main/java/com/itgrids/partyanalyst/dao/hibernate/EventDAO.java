package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.model.Event;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventDAO extends GenericDaoHibernate<Event, Long> implements IEventDAO{

	public EventDAO( ) {
		super(Event.class);
	}

	public List<Object[]> getEventsForUser(Long userId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.eventId, model.name from Event model where model.parentEventId is null and model.isActive =:isActive order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public Event checkIsExistEvent(String eventName)
	{
		Query query = getSession().createQuery(" select model from Event model where model.namel like '"+eventName.trim()+"' and model.isActive =:isActive  ");
		query.setParameter("isActive", IConstants.TRUE);
		return (Event) query.uniqueResult();
	}
	
	public List<Object[]> getEventNames(List<Long> eventIds)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct  model.eventId, model.name from Event model where  model.eventId in(:eventIds)  and model.isActive =:isActive order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("eventIds", eventIds);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
	
	public List<Object[]> getSubEventsByParentEvent(Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.eventId, model.name from Event model where  model.parentEventId = :eventId  and model.isActive =:isActive order by model.orderId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("isActive", IConstants.TRUE);
		return query.list();
	}
}
