package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.INewsFlagDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.FileGallary;

public class NewsFlagDAOHibernateTestCase extends BaseDaoTestCase{
	
	INewsFlagDAO newsFlagDAO;

	public INewsFlagDAO getNewsFlagDAO() {
		return newsFlagDAO;
	}

	public void setNewsFlagDAO(INewsFlagDAO newsFlagDAO) {
		this.newsFlagDAO = newsFlagDAO;
	}
	
		/*public void testGetFlagedMews()
		{
			List<Long> contentIds = new ArrayList<Long>();
			contentIds.add(25737l);
			contentIds.add(25736l);
			contentIds.add(25540l); 
			List<FileGallary> newsList= newsFlagDAO.getFlagedMews(contentIds);
			System.out.println(newsList);
        }*/
	public void testGetFlaggedNewsCount()
	{
		List<Long> contentIds = new ArrayList<Long>();
		FileVO inputs = new FileVO();
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 03, 01);
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2013, 03, 04);
		//String fromDate = "2013-04-01";
		//String toDate = "2013-04-03";
		 //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");    
		inputs.setExistingFrom(cal.getTime());
		inputs.setIdentifiedOn(cal1.getTime());
		contentIds.add(30206l);
		contentIds.add(29844l);
		contentIds.add(24396l); 
		contentIds.add(22717l);
		contentIds.add(14824l);
		Long newsList= newsFlagDAO.getFlaggedNewsCount(inputs,contentIds);
		System.out.println(newsList);
    }

}
