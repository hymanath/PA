package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserEventsDAO;
import com.itgrids.partyanalyst.dao.IUserImpDatesDAO;
import com.itgrids.partyanalyst.model.UserEvents;
import com.itgrids.partyanalyst.model.UserImpDate;

import common.Logger;


public class UserEventsDAOHibernateTest  extends BaseDaoTestCase {
	private IUserEventsDAO userEventsDAO;
	private IUserImpDatesDAO userImpDatesDAO;
	
	public final static Logger log = Logger.getLogger(UserEventsDAOHibernateTest.class);

	public void setUserEventsDAO(IUserEventsDAO userEventsDAO) {
		this.userEventsDAO = userEventsDAO;
	}
	
	public void setUserImpDatesDAO(IUserImpDatesDAO userImpDatesDAO) {
		this.userImpDatesDAO = userImpDatesDAO;
	}

	public void testFindEventsByUserId() {
		List<UserEvents> userEvents = userEventsDAO.findEventsByUserId(4L);
		assertEquals(1, userEvents.size());
		assertEquals("Village level meeting with party candidates", userEvents.get(0).getDescription());
	}
	
	public void testFindEventsByUserIdAndStartDate() {
		List<UserEvents> userEvents = userEventsDAO.findEventsByUserIdAndStartDate(4L, new Date());
		assertEquals(1, userEvents.size());
		assertEquals(new Long(833), userEvents.get(0).getLocationId());
		assertEquals("Village level meeting with party candidates", userEvents.get(0).getDescription());
		log.debug(userEvents.get(0).getStartDate());
		System.out.print("locationId: " + userEvents.get(0).getLocationId());
	}
	
	public void testFinfTodaysUserImpDates() {
		List<UserImpDate> userImpDates = userImpDatesDAO.findTodayImportantEvents(1L);
		Assert.assertEquals(1, userImpDates.size());
		Assert.assertEquals("My Birthday as party candidate", userImpDates.get(0).getTitle());
	}
	

}
