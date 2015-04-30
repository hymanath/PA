package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEventSurveyUserEventDAO;
import com.itgrids.partyanalyst.model.EventSurveyUserEvent;

public class EventSurveyUserEventDAO extends GenericDaoHibernate<EventSurveyUserEvent,Long> implements IEventSurveyUserEventDAO{

	public EventSurveyUserEventDAO()
	{
		super(EventSurveyUserEvent.class);
	}
}
