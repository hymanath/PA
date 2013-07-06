package com.itgrids.electoralconnect.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.IAnnouncementFilesDAO;
import com.itgrids.electoralconnect.model.AnnouncementFiles;

public class AnnouncementFilesDAO extends GenericDaoHibernate<AnnouncementFiles, Long> implements IAnnouncementFilesDAO{

	public AnnouncementFilesDAO() {
		super(AnnouncementFiles.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<AnnouncementFiles> getAllAnnouncements(int startRecord,int maxRecord,Long userId){
		Query queryObject=getSession().createQuery("select model from AnnouncementFiles model where model.announcements.updatedBy.userId=:userId");
		
		queryObject.setParameter("userId", userId);
		queryObject.setFirstResult(startRecord);
		queryObject.setMaxResults(maxRecord);
		
		return queryObject.list();
	}
	
	public int getAllAnnouncementsCountOfUser(Long userId){
		Query queryObject=getSession().createQuery("select count(model.announcementFilesId) from AnnouncementFiles model where model.announcements.updatedBy.userId=:userId");
		
		queryObject.setParameter("userId", userId);
		return ((Long)queryObject.uniqueResult()).intValue();
	}
	
		/**
		 * This DAO is used to get the Annoncement by annoncement id
		 * @param Long announcementId
		 * @return List<Object[]>
		 * @date 04-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getAnnoncementById(Long announcementId)
		{
			Query query = getSession().createQuery("select model.announcementFilesId,model.announcements,model.file from AnnouncementFiles model " +
					" where model.announcements.announcementId = :announcementId");
			query.setParameter("announcementId", announcementId);
			return query.list();
		}
		
		/**
		 * This DAO is used to get All Annoncements
		 * @return List<Object[]>
		 * @date 04-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getAllAnnoncement(Long announcementTypeId)
		{
			Query query = getSession().createQuery("select model.announcementFilesId,model.announcements,model.file from AnnouncementFiles model " +
					" where model.announcements.announcementType.announcementTypeId = :announcementTypeId" );
			query.setParameter("announcementTypeId", announcementTypeId);
			return query.list();
		}
		
		/**
		 * This DAO is used to get Announcements By Announcement File Id
		 * @param Long announcementFileId
		 * @return List<Object[]>
		 * @date 05-07-2013
		 */
		@SuppressWarnings("unchecked")
		public List<Object[]> getAnnouncementsByAnnouncementFileId(Long announcementFileId)
		{
			Query query = getSession().createQuery("select model.announcementFilesId,model.announcements,model.file from AnnouncementFiles model " +
					" where model.announcementFilesId = :announcementFileId");
			query.setParameter("announcementFileId", announcementFileId);
			return query.list();
		}

}
