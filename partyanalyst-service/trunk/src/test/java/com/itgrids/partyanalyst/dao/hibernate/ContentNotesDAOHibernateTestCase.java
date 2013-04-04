package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IContentNotesDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.FileGallary;

public class ContentNotesDAOHibernateTestCase extends BaseDaoTestCase{
	
	IContentNotesDAO contentNotesDAO;

	public IContentNotesDAO getContentNotesDAO() {
		return contentNotesDAO;
	}

	public void setContentNotesDAO(IContentNotesDAO contentNotesDAO) {
		this.contentNotesDAO = contentNotesDAO;
	}
	
	/*public void testGetNotedNews()
	{
		List<Long> contentIds = new ArrayList<Long>();
		contentIds.add(25737l);
		contentIds.add(25736l);
		contentIds.add(25540l); 
		List<FileGallary> newsList= contentNotesDAO.getNotedNews(contentIds);
		System.out.println(newsList.size());
    }*/
	public void testGetFlaggedNewsCount()
	{
		List<Long> contentIds = new ArrayList<Long>();
		FileVO inputs = new FileVO();
		//Date date = new Date();
		//String fromDate = "2013-01-01";
		//String toDate = "2013-04-01";
		 //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2013, 03, 01);
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2013, 03, 04);
		inputs.setExistingFrom(cal1.getTime());
		inputs.setIdentifiedOn(cal2.getTime());
		contentIds.add(30206l);
		contentIds.add(29844l);
		contentIds.add(24396l); 
		contentIds.add(22717l);
		contentIds.add(14824l); 
		Long newsList= contentNotesDAO.getNotedNewsCount(inputs,contentIds);
		System.out.println(newsList);
    }

}
