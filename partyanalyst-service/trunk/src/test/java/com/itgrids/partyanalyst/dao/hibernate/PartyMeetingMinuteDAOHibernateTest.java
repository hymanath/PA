package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;

public class PartyMeetingMinuteDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingMinuteDAO partyMeetingMinuteDAO;

	public void setPartyMeetingMinuteDAO(
			IPartyMeetingMinuteDAO partyMeetingMinuteDAO) {
		this.partyMeetingMinuteDAO = partyMeetingMinuteDAO;
	}
	
	public void test(){
		List<Long> partyMeetingIds = new ArrayList<Long>();
		partyMeetingIds.add(15l);
		List<Object[]> list = partyMeetingMinuteDAO.getMinuteDetailsForMeetings(partyMeetingIds);
		System.out.println(list.size());
	}
}
