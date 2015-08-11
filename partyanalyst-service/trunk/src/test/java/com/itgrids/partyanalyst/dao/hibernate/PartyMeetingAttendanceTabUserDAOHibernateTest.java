package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceTabUserDAO;

public class PartyMeetingAttendanceTabUserDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO;

	public void setPartyMeetingAttendanceTabUserDAO(
			IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO) {
		this.partyMeetingAttendanceTabUserDAO = partyMeetingAttendanceTabUserDAO;
	}
	
	public void test()
	{
		partyMeetingAttendanceTabUserDAO.getAll();
	}
}
