package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
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
	
	public void test(){
		List<Long> partyMeetingIds = new ArrayList<Long>();
		partyMeetingIds.add(1l);
		partyMeetingIds.add(3l);
		partyMeetingIds.add(4l);
		List<Object[]> list = partyMeetingInviteeDAO.getPartyMeetingInviteesForMeetings(partyMeetingIds);
		
		System.out.println(list.size());
	}
}
