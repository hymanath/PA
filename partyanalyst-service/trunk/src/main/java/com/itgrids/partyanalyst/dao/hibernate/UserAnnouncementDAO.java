package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
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
				"from UserAnnouncement model where model.user.registrationId = ?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserAnnouncement> getUserAnnouncementByAnnouncementId(Long announcementId)
	{
		return getHibernateTemplate().find("select model from UserAnnouncement model where model.announcement.announcementId = ? ",announcementId);
	}
	
}
