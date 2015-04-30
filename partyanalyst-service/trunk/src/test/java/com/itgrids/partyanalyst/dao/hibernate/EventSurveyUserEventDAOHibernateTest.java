package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IEventSurveyUserEventDAO;

public class EventSurveyUserEventDAOHibernateTest extends BaseDaoTestCase{
	
	private IEventSurveyUserEventDAO eventSurveyUserEventDAO;

	public void setEventSurveyUserEventDAO(
			IEventSurveyUserEventDAO eventSurveyUserEventDAO) {
		this.eventSurveyUserEventDAO = eventSurveyUserEventDAO;
	}
	
	public void test()
	{
		eventSurveyUserEventDAO.getAll();
	}

}
