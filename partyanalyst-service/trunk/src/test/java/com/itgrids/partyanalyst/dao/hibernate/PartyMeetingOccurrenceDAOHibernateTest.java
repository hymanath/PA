package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingOccurrenceDAO;

public class PartyMeetingOccurrenceDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingOccurrenceDAO partyMeetingOccurrenceDAO;

	public void setPartyMeetingOccurrenceDAO(
			IPartyMeetingOccurrenceDAO partyMeetingOccurrenceDAO) {
		this.partyMeetingOccurrenceDAO = partyMeetingOccurrenceDAO;
	}
	
	public void test()
	{
		partyMeetingOccurrenceDAO.getAll();
	}
}
