package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.model.Event;

public class EventDAO extends GenericDaoHibernate<Event, Long> implements IEventDAO{

	public EventDAO( ) {
		super(Event.class);
	}

}
