package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventAttendeeErrorDAO;
import com.itgrids.partyanalyst.model.EventAttendeeError;

public class EventAttendeeErrorDAO extends GenericDaoHibernate<EventAttendeeError, Long> implements IEventAttendeeErrorDAO{

	public EventAttendeeErrorDAO() {
		super(EventAttendeeError.class);
		// TODO Auto-generated constructor stub
	}

}
