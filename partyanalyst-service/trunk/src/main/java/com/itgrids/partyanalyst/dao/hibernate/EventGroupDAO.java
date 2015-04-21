package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventGroupDAO;
import com.itgrids.partyanalyst.model.EventGroup;

public class EventGroupDAO extends GenericDaoHibernate<EventGroup, Long> implements IEventGroupDAO{

	public EventGroupDAO() {
		super(EventGroup.class);
	}

}
