package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IEventTypeDAO;
import com.itgrids.partyanalyst.model.EventType;

public class EventTypeDAO extends GenericDaoHibernate<EventType, Long> implements IEventTypeDAO{

	public EventTypeDAO() {
		super(EventType.class);
	}
	
	public List<Object[]> getEventTypeDetails()
	{
		Query query =  getSession().createQuery(" select distinct model.eventTypeId, model.type from EventType model ");
		return query.list();
	}
}
