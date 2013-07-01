package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.dao.hibernate.AnnouncementsDAO;

public class AnnouncementsDAOHibernateTest extends BaseDaoTestCase{
	
	private AnnouncementsDAO announcementsDAO;
	
	public void setAnnouncementsDAO(AnnouncementsDAO announcementsDAO) {
		this.announcementsDAO = announcementsDAO;
	}


	public void test(){
		announcementsDAO.getAll();
	}

}
