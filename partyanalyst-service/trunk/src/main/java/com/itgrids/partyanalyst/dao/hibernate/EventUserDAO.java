package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.model.EventUser;

public class EventUserDAO extends GenericDaoHibernate<EventUser, Long> implements IEventUserDAO{

	public EventUserDAO() {
		super(EventUser.class);
	}

}
