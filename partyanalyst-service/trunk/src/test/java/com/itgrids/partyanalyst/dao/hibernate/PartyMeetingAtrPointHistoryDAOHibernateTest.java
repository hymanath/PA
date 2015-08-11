package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointHistoryDAO;

public class PartyMeetingAtrPointHistoryDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingAtrPointHistoryDAO partyMeetingAtrPointHistoryDAO;

	public void setPartyMeetingAtrPointHistoryDAO(
			IPartyMeetingAtrPointHistoryDAO partyMeetingAtrPointHistoryDAO) {
		this.partyMeetingAtrPointHistoryDAO = partyMeetingAtrPointHistoryDAO;
	}
	
	public void test()
	{
		partyMeetingAtrPointHistoryDAO.getAll();
	}
}
