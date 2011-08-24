package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import com.itgrids.partyanalyst.dao.IAnnouncementsDao;

public class AnnouncementDAOHibernateTest extends BaseDaoTestCase{
	private IAnnouncementsDao announcementsDao;
	

	public void setAnnouncementsDao(IAnnouncementsDao announcementsDao) {
		this.announcementsDao = announcementsDao;
	}

//	@Test
//	public void testAnnouncement(){
//		announcementsDao.deleteAnnouncement(62L);
//}
	

}
