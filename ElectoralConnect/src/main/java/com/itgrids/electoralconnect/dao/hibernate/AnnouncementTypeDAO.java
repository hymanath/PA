package com.itgrids.electoralconnect.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IAnnouncementTypeDAO;
import com.itgrids.electoralconnect.model.AnnouncementType;


public class AnnouncementTypeDAO extends GenericDaoHibernate<AnnouncementType, Long> implements IAnnouncementTypeDAO{

	public AnnouncementTypeDAO() {
		super(AnnouncementType.class);
	}

}
