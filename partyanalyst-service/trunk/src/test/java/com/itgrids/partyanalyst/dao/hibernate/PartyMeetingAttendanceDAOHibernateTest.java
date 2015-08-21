package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;

public class PartyMeetingAttendanceDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;

	public void setPartyMeetingAttendanceDAO(IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}
	
	public void test(){
		List<Long> partyMeetingIds = new ArrayList<Long>();
		partyMeetingIds.add(32l);
		List<Object[]> list = partyMeetingAttendanceDAO.getTotalAttendentsOfMeetings(partyMeetingIds);
		List<Object[]> list1 = partyMeetingAttendanceDAO.getInviteesAttendedCountOfMeetings(partyMeetingIds);
		System.out.println(list.size());
	}
}
