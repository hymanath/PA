package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Calendar;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAnnouncementsDao;
import com.itgrids.partyanalyst.dto.AnnouncementResultsVO;
import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.Announcement;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.UserConstituencyScope;
import com.itgrids.partyanalyst.utils.IConstants;

import java.text.SimpleDateFormat;

public class AnnouncementsDAO extends GenericDaoHibernate<Announcement, Long> implements IAnnouncementsDao {

	public AnnouncementsDAO() {
		super(Announcement.class);
	}
	
	
	public List findAnnouncementsByConstituencyId(Long constituencyId) {
		
		Calendar currentDate = Calendar.getInstance();
		System.out.println(constituencyId);
		SimpleDateFormat formatter=  new SimpleDateFormat(IConstants.DATE_PATTERN);
		String dateNow = formatter.format(currentDate.getTime());
		Object[] parameters = {constituencyId,dateNow,dateNow};
		
			
		return getHibernateTemplate().find("select model.title,model.discription," +
				"model.userAnnouncements.user.firstName,model.userAnnouncements.user.middleName," +
				"model.userAnnouncements.user.lastName FROM Announcement model WHERE " +
				"model.userAnnouncements.userConstituencyScope.constituency.constituencyId ="+constituencyId);
			
	}
	
//	public void deleteAnnouncement(long announcementId){
//		Announcement announcement = new Announcement();
//		announcement.setAnnouncementsId(announcementId);
//	     getHibernateTemplate().delete(announcement);
//		getHibernateTemplate().flush();
//		
//	}
}
