package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.model.UserConstituencyScope;

public class UserConstituencyScopeDAO extends GenericDaoHibernate<UserConstituencyScope, Long> implements IUserConstituencyScopeDAO{
        
	public UserConstituencyScopeDAO() {
		super(UserConstituencyScope.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAnnouncementsByConstituencyId(Long constituencyId,Date date)
	{
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyId(long announcementId){
	return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name,model.constituency.electionScope.electionType.electionType FROM UserConstituencyScope model " +
				" where model.usersAnnouncement.announcement.announcementsId = ? ",announcementId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserConstituencyScope> getUserConstituencyScopeByAnnouncementId(Long announcementId)
	{
		return getHibernateTemplate().find("select model from UserConstituencyScope model where model.userAnnouncement.announcement.announcementId = ? ",announcementId);
	}
	
}
