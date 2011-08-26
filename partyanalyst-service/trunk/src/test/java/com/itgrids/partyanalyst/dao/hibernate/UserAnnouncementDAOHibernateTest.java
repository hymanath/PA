package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserAnnouncementDAO;
import com.itgrids.partyanalyst.model.UserAnnouncement;

public class UserAnnouncementDAOHibernateTest extends BaseDaoTestCase{
	private IUserAnnouncementDAO userAnnouncementDAO;

	public void setUserAnnouncementDAO(IUserAnnouncementDAO userAnnouncementDAO) {
		this.userAnnouncementDAO = userAnnouncementDAO;
	}
	
	/*@Test
	public void testAnnouncementResultsByAUser(){
    List s = userAnnouncementDAO.findAnnouncementDetailsByUserId(1L);
    System.out.println(s.size());
	Iterator i =s.iterator();
	while(i.hasNext()){
		Object[] o =(Object[])i.next();
		System.out.println(o[0]);
		System.out.println(o[1]);
		System.out.println(o[2]);
		System.out.println(o[3]);
		System.out.println(o[4]);
	}
	}*/
	
	public void testGetUserAnnouncementByAnnouncementId()
	{
		List<UserAnnouncement> list = userAnnouncementDAO.getUserAnnouncementByAnnouncementId(1l);
		System.out.println(list.size());
		System.out.println(list.get(0).getAnnouncement().getDiscription());
	}

}
