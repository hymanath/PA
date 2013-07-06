package com.itgrids.electoralconnect.dao.hibernatetest;


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
	
	public void testAll(){
		List<AnnouncementFiles> list=announcementFilesDAO.getAllAnnouncements(1, 15);
	}*/
	public void testAlla(){
		int list=announcementFilesDAO.getAllAnnouncementsCountOfUser();
	}

	
	/*public void testgetAnnoncementById()
	{
		List<Object[]> values = announcementFilesDAO.getAllAnnoncement(1l);
		System.out.println(values.size());
	}*/
}
