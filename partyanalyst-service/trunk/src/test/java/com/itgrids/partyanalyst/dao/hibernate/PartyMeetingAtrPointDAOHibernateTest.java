package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;

public class PartyMeetingAtrPointDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO;

	public void setPartyMeetingAtrPointDAO(
			IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO) {
		this.partyMeetingAtrPointDAO = partyMeetingAtrPointDAO;
	}
	
	public void test(){
		List<Long> partyMeetingIds = new ArrayList<Long>();
		partyMeetingIds.add(15l);
		List<Object[]> list = partyMeetingAtrPointDAO.getAtrPointsOfMeetings(partyMeetingIds);
		System.out.println(list.size());
	}
}
