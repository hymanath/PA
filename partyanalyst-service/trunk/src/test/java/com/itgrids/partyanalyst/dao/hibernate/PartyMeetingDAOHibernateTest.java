package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;

public class PartyMeetingDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingDAO partyMeetingDAO;

	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
	
	public void test(){
		partyMeetingDAO.getAll();
	}
}
