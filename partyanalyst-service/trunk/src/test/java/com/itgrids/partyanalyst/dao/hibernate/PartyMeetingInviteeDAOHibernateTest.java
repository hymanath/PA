package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;

public class PartyMeetingInviteeDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}
	
	/*public void test()
	{
		partyMeetingInviteeDAO.getAll();
	}*/
	
	public void testGetPartyMeetingInvittees()
	{
		List<String> list = partyMeetingInviteeDAO.getPartyMeetingInvittees(1l);
		System.out.println(list.size());
		for(String str : list)
			System.out.println(str);
	}
}
