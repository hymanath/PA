package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventSmsSentDAO;
import com.itgrids.partyanalyst.model.EventSmsSent;

public class EventSmsSentDAO extends GenericDaoHibernate<EventSmsSent, Long> implements IEventSmsSentDAO{

	public EventSmsSentDAO() {
		super(EventSmsSent.class);
	}

}
