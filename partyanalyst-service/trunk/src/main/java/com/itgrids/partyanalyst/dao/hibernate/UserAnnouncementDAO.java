package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserAnnouncementDAO;
import com.itgrids.partyanalyst.model.UserAnnouncement;

public class UserAnnouncementDAO extends GenericDaoHibernate<UserAnnouncement, Long> implements IUserAnnouncementDAO{

	public UserAnnouncementDAO() {
		super(UserAnnouncement.class);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findAnnouncementDetailsByUserId(Long userId)
	{
		return	getHibernateTemplate().find("select model.announcement.announcementId,model.announcement.title" +
				",model.announcement.discription,model.announcement.fromDate,model.announcement.toDate " +
				"from UserAnnouncement model where model.user.userId = ?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAnnouncement> getUserAnnouncementByAnnouncementId(Long announcementId)
	{
		return getHibernateTemplate().find("select model from UserAnnouncement model where model.announcement.announcementId = ? ",announcementId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserAnnouncementDetailsRecent(Long userId,Date today)
	{
		Query query = getSession().createQuery("select model.announcement.title,model.announcement.fromDate,model.announcement.toDate from UserAnnouncement model where model.user.userId = :userId and  date(model.announcement.fromDate) <=:today  and date(model.announcement.toDate) >= :today");
		query.setParameter("userId", userId);
		query.setDate("today", today);
		return query.list();
	}
}
