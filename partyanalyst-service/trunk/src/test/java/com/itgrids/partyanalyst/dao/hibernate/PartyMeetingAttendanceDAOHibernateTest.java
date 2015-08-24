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
	
	/*public void test(){
		List<Long> partyMeetingIds = new ArrayList<Long>();
		partyMeetingIds.add(20l);
		//partyMeetingIds.add(20l);
		//List<Object[]> list = partyMeetingAttendanceDAO.getTotalAttendentsOfMeetings(partyMeetingIds);
		List<Object[]> list1 = partyMeetingAttendanceDAO.getInviteesAttendedCountOfMeetings(partyMeetingIds);
		System.out.println(list1.size());
	}*/
	
	/*public void testGetAttendanceForMeetings()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(1l);
		list.add(2l);
		List<Object[]> list2 = partyMeetingAttendanceDAO.getAttendanceForMeetings(list);
		System.out.println(list2.size());
	}*/
	
	public void testGetCandidateAttendanceForMeetings()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(1l);
		list.add(2l);
		List<Object[]> list2 = partyMeetingAttendanceDAO.getCandidateAttendanceForMeetings(list);
		System.out.println(list2.size());
	}
	
	public void testGetCommitteeMemberAttendanceForMeetings()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(1l);
		list.add(2l);
		List<Object[]> list2 = partyMeetingAttendanceDAO.getCommitteeMemberAttendanceForMeetings(list);
		System.out.println(list2.size());
	}
}
