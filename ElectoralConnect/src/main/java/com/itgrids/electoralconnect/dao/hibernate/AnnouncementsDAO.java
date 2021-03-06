package com.itgrids.electoralconnect.dao.hibernate;


import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.IAnnouncementsDAO;
import com.itgrids.electoralconnect.model.Announcements;





public class AnnouncementsDAO extends GenericDaoHibernate<Announcements, Long> implements IAnnouncementsDAO{

	public AnnouncementsDAO() {
		super(Announcements.class);
	}

	/**
	 * This DAO is used to get Top Announcements
	 * @param Long announcementTypeId
	 * @param int startIndex
	 * @param int maxIndex
	 * @return List<Announcements>
	 * @date 05-07-2013
	 */
	@SuppressWarnings("unchecked")
	public List<Announcements> getTopAnnouncements(Long announcementTypeId,int startIndex,int maxIndex)
	{
		Query query = getSession().createQuery("from Announcements model where " +
				" model.announcementType.announcementTypeId = :announcementTypeId and model.isDeleted='NO' " +
				" order by model.announcementId desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		query.setParameter("announcementTypeId", announcementTypeId);
		return query.list();
	}
	
	/**
	 * This DAO is used to update the deleted announcement
	 * @param Long announcementId
	 * @return int
	 * @date 05-07-2013
	 */
	public int deleteSelAnnouncement(Long announcementId)
	{
		Query query = getSession().createQuery("update Announcements model set isDeleted = 'YES' where" +
				" model.announcementId = :announcementId");
		query.setParameter("announcementId", announcementId);
		int x = query.executeUpdate();
		getSession().flush();
		return x;
	}
}
