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
	
	/*public void test(){
		List<Long> partyMeetingIds = new ArrayList<Long>();
		partyMeetingIds.add(1l);
		partyMeetingIds.add(3l);
		partyMeetingIds.add(4l);
		List<Object[]> list = partyMeetingInviteeDAO.getPartyMeetingInviteesForMeetings(partyMeetingIds);
		
		System.out.println(list.size());
	}*/
	
	/*public void testGetPartyMeetingInvittees()
	{
		List<String> list = partyMeetingInviteeDAO.getPartyMeetingInvittees(1l);
		System.out.println(list.size());
		for(String str : list)
			System.out.println(str);
	}*/
	
	/*public void testGetInviteesForPartyMeetings()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(1l);
		list.add(2l);
		List<Object[]> list2 = partyMeetingInviteeDAO.getInviteesForPartyMeetings(list);
		System.out.println(list2.size());
		for(Object[] params : list2)
			System.out.println(params[0].toString()+"\t"+params[1]);
	}*/
	
	/*public void testGetPublicRepresentativeInviteesForPartyMeetings()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(1l);
		list.add(2l);
		List<Object[]> list2 = partyMeetingInviteeDAO.getPublicRepresentativeInviteesForPartyMeetings(list);
		System.out.println(list2.size());
		for(Object[] params : list2)
			System.out.println(params[0].toString()+"\t"+params[1]);
	}*/
	public void testGetPublicRepresentativeInviteesForPartyMeetings()
	{
		List<Long> list = new ArrayList<Long>(0);
		list.add(1l);
		list.add(2l);
		List<Object[]> list2 = partyMeetingInviteeDAO.getCommitteeMemberInviteesForPartyMeetings(list);
		System.out.println(list2.size());
		for(Object[] params : list2)
			System.out.println(params[0].toString()+"\t"+params[1]);
	}
}
