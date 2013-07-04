package com.itgrids.electoralconnect.dao.hibernatetest;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;


import com.itgrids.electoralconnect.dao.hibernate.AnnouncementFilesDAO;


public class AnnouncementFilesDAOHibernateTest extends BaseDaoTestCase{
	
	private AnnouncementFilesDAO announcementFilesDAO;
	
	public void setAnnouncementFilesDAO(AnnouncementFilesDAO announcementFilesDAO) {
		this.announcementFilesDAO = announcementFilesDAO;
	}


	/*public void test(){
		announcementFilesDAO.getAll();
	}
*/
	
	public void testgetAnnoncementById()
	{
		List<Object[]> values = announcementFilesDAO.getAnnoncementById(4l);
		System.out.println(values.size());
	}
}
