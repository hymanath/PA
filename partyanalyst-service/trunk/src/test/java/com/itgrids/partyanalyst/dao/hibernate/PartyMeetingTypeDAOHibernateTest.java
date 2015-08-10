package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;

public class PartyMeetingTypeDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingTypeDAO partyMeetingTypeDAO;

	public void setPartyMeetingTypeDAO(IPartyMeetingTypeDAO partyMeetingTypeDAO) {
		this.partyMeetingTypeDAO = partyMeetingTypeDAO;
	}
	
	public void test()
	{
		partyMeetingTypeDAO.getAll();
	}
}
