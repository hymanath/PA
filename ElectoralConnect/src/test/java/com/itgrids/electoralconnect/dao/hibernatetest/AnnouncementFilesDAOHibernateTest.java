package com.itgrids.electoralconnect.dao.hibernatetest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormat;
import org.appfuse.dao.BaseDaoTestCase;


import com.itgrids.electoralconnect.dao.hibernate.AnnouncementFilesDAO;
import com.itgrids.electoralconnect.dto.AnnouncementVO;
import com.itgrids.electoralconnect.model.AnnouncementFiles;



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
	/*public void testAlla(){
		int list=announcementFilesDAO.getAllAnnouncementsCountOfUser();
	}
*/
	
	/*public void testgetAnnoncementById()
	{
		List<Object[]> values = announcementFilesDAO.getAllAnnoncement(1l);
		System.out.println(values.size());
	}*/
	
/*	public void testAnnouncemetsBtSelDates() throws ParseException
	{
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-mm-dd");
		List<AnnouncementFiles> valuse = 	announcementFilesDAO.getAnnouncemetsBtSelDates(formate.parse("2013-07-01"), formate.parse("2013-07-05"), 0, 5);
		System.out.println(valuse.size());
	}*/
	
	/*public void testgetSelBtDatesAnnouncementsCountOfUser() throws ParseException
	{
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-mm-dd");
		int count = announcementFilesDAO.getSelBtDatesAnnouncementsCountOfUser(formate.parse("2013-07-01"), formate.parse("2013-07-05"));
		System.out.println(count);
	}*/
	
	/*public void testgetAllAnnouncemetsForSelectedType ()
	{
		List<AnnouncementFiles> valuse =  announcementFilesDAO.getAllAnnouncemetsForSelectedType(1l,0,5);
		System.out.println(valuse.size());
	}*/
	
	/*public void testgetCountForSelAnnouncemetType()
	{
		Long count = announcementFilesDAO.getCountForSelAnnouncemetType(1l);
		System.out.println(count);
	}*/
	
	public void testtop50announcements()
	{
		List<Object[]> an=announcementFilesDAO.getLatest50Annoncements(2l);
		System.out.println(an.size());
	}
}
