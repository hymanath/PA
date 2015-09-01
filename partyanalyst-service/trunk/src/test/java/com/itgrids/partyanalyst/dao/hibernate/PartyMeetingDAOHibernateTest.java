package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;

public class PartyMeetingDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingDAO partyMeetingDAO;

	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
	
	/*public void test()
	{
		partyMeetingDAO.getAll();
	}*/
	
	/*public void testGetPartyMeetingIdsByLevelAndLocation()
	{
		DateUtilService dateUtilService = new DateUtilService();
		System.out.println(dateUtilService.getDateAndTime("2015-08-22 00:00:00"));
		List<Long> list = partyMeetingDAO.getPartyMeetingIdsByLevelAndLocation(2l,dateUtilService.getDateAndTime("2015-08-22 00:00:00"),dateUtilService.getDateAndTime("2015-08-26 00:00:00"),2l,2l,12l);
		System.out.println("-->"+ list.size());
		for(Long l : list)
			System.out.println(l);
		
	}*/
}
