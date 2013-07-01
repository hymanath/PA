package com.itgrids.electoralconnect.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.IAnnouncementFiles;
import com.itgrids.electoralconnect.model.AnnouncementFiles;



public class AnnouncementFilesDAO extends GenericDaoHibernate<AnnouncementFiles, Long> implements IAnnouncementFiles{

	public AnnouncementFilesDAO() {
		super(AnnouncementFiles.class);
	}

}
