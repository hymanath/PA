package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventRfidDetailsDAO;
import com.itgrids.partyanalyst.model.EventRfidDetails;

public class EventRfidDetailsDAO extends GenericDaoHibernate<EventRfidDetails,Long> implements IEventRfidDetailsDAO{

	public EventRfidDetailsDAO()
	{
		super(EventRfidDetails.class);
	}
}
