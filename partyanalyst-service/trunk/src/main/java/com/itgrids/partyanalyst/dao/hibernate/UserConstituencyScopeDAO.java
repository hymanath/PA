package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import com.itgrids.partyanalyst.dao.IUserConstituencyScopeDAO;
import com.itgrids.partyanalyst.model.UserConstituencyScope;

public class UserConstituencyScopeDAO extends GenericDaoHibernate<UserConstituencyScope, Long> implements IUserConstituencyScopeDAO{
        
	public UserConstituencyScopeDAO() {
		super(UserConstituencyScope.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAnnouncementsByConstituencyId(Long constituencyId,Date date)
	{
		Object[] params = {constituencyId,date,date};
		return getHibernateTemplate().find("select model.userAnnouncement.announcement.title, model.userAnnouncement.announcement.discription," +
				" model.userAnnouncement.announcement.fromDate,model.userAnnouncement.user.registrationId, model.userAnnouncement.user.firstName, " +
				" model.userAnnouncement.user.lastName FROM UserConstituencyScope model WHERE model.constituency.constituencyId = ? " +
				" and date(model.userAnnouncement.announcement.toDate) >= ? and date(model.userAnnouncement.announcement.fromDate) <= ? "+
				" order by model.userAnnouncement.announcement.fromDate desc, model.userAnnouncement.announcement.announcementId desc ",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyId(long announcementId){
	return getHibernateTemplate().find("select model.constituency.constituencyId,model.constituency.name,model.constituency.electionScope.electionType.electionType FROM UserConstituencyScope model " +
				" where model.userAnnouncement.announcement.announcementId = ? ",announcementId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserConstituencyScope> getUserConstituencyScopeByAnnouncementId(Long announcementId)
	{
		return getHibernateTemplate().find("select model from UserConstituencyScope model where model.userAnnouncement.announcement.announcementId = ? ",announcementId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAnnouncementDetailsByUserId(Long userId)
	{
		return	getHibernateTemplate().find("select model.userAnnouncement.announcement.announcementId,model.userAnnouncement.announcement.title,model.userAnnouncement.announcement.discription,model.userAnnouncement.announcement.fromDate, " +
				" model.userAnnouncement.announcement.toDate,model.constituency.constituencyId,model.constituency.name,model.constituency.electionScope.electionType.electionType from UserConstituencyScope model where model.userAnnouncement.user.registrationId = ? ",userId);
	}
	
	public List<Object[]> findAnnouncementDetailsByUserIdDateConstId(Long userId,Date fromDate,Date toDate,Long constituencyId)
    {
		StringBuilder query = new StringBuilder();
		query.append("select model.userAnnouncement.announcement.announcementId,model.userAnnouncement.announcement.title" +
				",model.userAnnouncement.announcement.discription,model.userAnnouncement.announcement.fromDate,model.userAnnouncement.announcement.toDate " +
				",model.constituency.constituencyId,model.constituency.name,model.constituency.electionScope.electionType.electionType "+
				"from UserConstituencyScope model where model.userAnnouncement.user.registrationId = :userId ");
		if(fromDate!= null)
			query.append("  and date(model.userAnnouncement.announcement.fromDate) >= :fromDate");
		if(toDate!= null)
			query.append("  and date(model.userAnnouncement.announcement.toDate) < :toDate");	
		if(constituencyId!= 0 && constituencyId!= null)
			query.append("  and model.constituency.constituencyId = :constituencyId");	
		
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong("userId", userId);
		if(fromDate!= null)
		   queryObject.setDate("fromDate", fromDate);
		if(toDate!= null)
		   queryObject.setDate("toDate", toDate);
		if(constituencyId!= 0)
		   queryObject.setLong("constituencyId", constituencyId);
		
	  return	queryObject.list();
	}
	
	
	
}
