package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IAnnouncementDAO;

public class AnnouncementDAOHibernateTest extends BaseDaoTestCase{
	private IAnnouncementDAO announcementDAO;
	
	public void setAnnouncementDAO(IAnnouncementDAO announcementDAO) {
		this.announcementDAO = announcementDAO;
	}
	
	public void test()
	{
		announcementDAO.getAll();
	}

}
