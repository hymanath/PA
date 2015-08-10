package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;

public class PartyMeetingLevelDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingLevelDAO partyMeetingLevelDAO;

	public void setPartyMeetingLevelDAO(IPartyMeetingLevelDAO partyMeetingLevelDAO) {
		this.partyMeetingLevelDAO = partyMeetingLevelDAO;
	}
	
	public void test()
	{
		partyMeetingLevelDAO.getAll();
	}
}
