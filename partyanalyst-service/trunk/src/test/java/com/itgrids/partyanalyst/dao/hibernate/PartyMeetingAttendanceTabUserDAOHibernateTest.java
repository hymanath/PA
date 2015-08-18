package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceTabUserDAO;
import com.itgrids.partyanalyst.model.PartyMeeting;

public class PartyMeetingAttendanceTabUserDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO;

	public void setPartyMeetingAttendanceTabUserDAO(
			IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO) {
		this.partyMeetingAttendanceTabUserDAO = partyMeetingAttendanceTabUserDAO;
	}
	
	/*public void test()
	{
		partyMeetingAttendanceTabUserDAO.getAll();
	}*/
	
	public void testGetPartyMeetingsOfAttendanceTabUser()
	{
		List<PartyMeeting> list = partyMeetingAttendanceTabUserDAO.getPartyMeetingsOfAttendanceTabUser(1l);
		System.out.println(list.size());
		
		for(PartyMeeting partyMeeting : list)
			System.out.println(partyMeeting.getPartyMeetingId());
	}
}
