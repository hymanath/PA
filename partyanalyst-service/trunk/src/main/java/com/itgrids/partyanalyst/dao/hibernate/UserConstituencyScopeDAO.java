package com.itgrids.partyanalyst.dao.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.model.UserAnnouncement;
import com.itgrids.partyanalyst.model.UserConstituencyScope;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserConstituencyScopeDAO extends GenericDaoHibernate<UserConstituencyScope, Long> implements IUserConstituencyScopeDAO{
        
	public UserConstituencyScopeDAO() {
		super(UserConstituencyScope.class);
	}

	
	public List findAnnouncementsByConstituencyId(Long constituencyId,Date date) {
		
		Object[] parameters = {constituencyId,date,date};
		
		return getHibernateTemplate().find("select model.usersAnnouncement.announcement.title," +
				"model.usersAnnouncement.announcement.discription," +
				"model.usersAnnouncement.user.firstName,model.usersAnnouncement.user.middleName," +
				"model.usersAnnouncement.user.lastName FROM UserConstituencyScope model WHERE " +
				"model.constituency.constituencyId =? " +
				" and date(model.usersAnnouncement.announcement.toDate) >= ?  " +
				"and date(model.usersAnnouncement.announcement.fromDate) <= ? "+
						"order by model.usersAnnouncement.announcement.fromDate desc",parameters);

		
	}
	public List getConstituencyId(long announcementId){
	List assembly =	getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name,model.constituency.electionScope.electionType.electionType,model.constituency.electionScope.state.stateId FROM UserConstituencyScope model " +
				" where model.usersAnnouncement.announcement.announcementsId = ?",announcementId);
	if(assembly.size()>0){
		return assembly;
	}
	else {
		return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name,model.constituency.electionScope.electionType.electionType FROM UserConstituencyScope model " +
				" where model.usersAnnouncement.announcement.announcementsId = ?",announcementId);
	}
	}
	
}
