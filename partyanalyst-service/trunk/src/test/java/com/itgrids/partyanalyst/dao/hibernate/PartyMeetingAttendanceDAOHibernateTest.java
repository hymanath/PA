package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;

public class PartyMeetingAttendanceDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;

	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}
	
	public void test()
	{
		partyMeetingAttendanceDAO.getAll();
	}
}
