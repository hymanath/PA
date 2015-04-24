package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventInfoDAO;
import com.itgrids.partyanalyst.model.EventInfo;

public class EventInfoDAO extends GenericDaoHibernate<EventInfo, Long> implements IEventInfoDAO{

	public EventInfoDAO() {
		super(EventInfo.class);
		// TODO Auto-generated constructor stub
	}

}
