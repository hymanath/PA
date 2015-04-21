package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventSmsDAO;
import com.itgrids.partyanalyst.model.EventSms;

public class EventSmsDAO extends GenericDaoHibernate<EventSms, Long> implements IEventSmsDAO{

	public EventSmsDAO( ) {
		super(EventSms.class);
	}

}
