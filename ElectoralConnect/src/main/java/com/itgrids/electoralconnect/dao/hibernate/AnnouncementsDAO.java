package com.itgrids.electoralconnect.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IAnnouncementsDAO;
import com.itgrids.electoralconnect.model.Announcements;





public class AnnouncementsDAO extends GenericDaoHibernate<Announcements, Long> implements IAnnouncementsDAO{

	public AnnouncementsDAO() {
		super(Announcements.class);
	}

}
