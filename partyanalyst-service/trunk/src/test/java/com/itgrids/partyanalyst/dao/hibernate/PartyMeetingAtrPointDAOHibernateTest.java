package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;

public class PartyMeetingAtrPointDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO;

	public void setPartyMeetingAtrPointDAO(
			IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO) {
		this.partyMeetingAtrPointDAO = partyMeetingAtrPointDAO;
	}
	
	public void test()
	{
		partyMeetingAtrPointDAO.getAll();
	}
}
