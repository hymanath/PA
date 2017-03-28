package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IAnnouncementDAO;
import com.itgrids.partyanalyst.model.Announcement;

public class AnnouncementDAO extends GenericDaoHibernate<Announcement, Long> implements IAnnouncementDAO {

	public AnnouncementDAO() {
		super(Announcement.class);
	}
	
}
