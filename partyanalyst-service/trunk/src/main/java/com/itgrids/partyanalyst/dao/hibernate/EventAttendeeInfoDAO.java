package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventAttendeeInfoDAO;
import com.itgrids.partyanalyst.model.EventAttendeeInfo;

public class EventAttendeeInfoDAO extends GenericDaoHibernate<EventAttendeeInfo, Long> implements IEventAttendeeInfoDAO{

	public EventAttendeeInfoDAO() {
		super(EventAttendeeInfo.class);
		
	}

}

