package com.itgrids.electoralconnect.dao.hibernatetest;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.hibernate.AnnouncementsDAO;
import com.itgrids.electoralconnect.model.Announcements;

public class AnnouncementsDAOHibernateTest extends BaseDaoTestCase{
	
	private AnnouncementsDAO announcementsDAO;
	
	public void setAnnouncementsDAO(AnnouncementsDAO announcementsDAO) {
		this.announcementsDAO = announcementsDAO;
	}


	/*public void test(){
		announcementsDAO.getAll();
	}*/
	
	public void testgetTopAnnouncements()
	{
		List<Announcements> values = announcementsDAO.getTopAnnouncements(2l,0,5);
		for (Announcements announcements : values) {
			System.out.println(announcements.getAnnouncementId());
		}
	}

}
